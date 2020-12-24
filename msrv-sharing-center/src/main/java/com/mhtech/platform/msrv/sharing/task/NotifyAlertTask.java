package com.mhtech.platform.msrv.sharing.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.mhtech.platform.msrv.monitor.service.IServerAdminService;
import com.mhtech.platform.msrv.monitor.service.IServerAlertRuleService;
import com.mhtech.platform.msrv.monitor.service.IServerNotifyLogService;
import com.mhtech.platform.msrv.monitor.service.IServerNotifyRuleService;
import com.mhtech.platform.msrv.pojo.NotifyLogVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ServerAlertRule;
import com.mhtech.platform.msrv.pojo.ServerNotifyLog;
import com.mhtech.platform.msrv.pojo.ServerNotifyRule;
import com.mhtech.platform.msrv.sharing.dao.model.SpPersonne;
import com.mhtech.platform.msrv.sharing.dao.model.SpUser;
import com.mhtech.platform.msrv.sharing.service.SpPersonneService;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;
import com.mhtech.platform.msrv.sharing.utils.SmsUtils;

/**
 * 告警通知
 * @author Guo
 *
 */
@Component
public class NotifyAlertTask {

	private final Logger logger= LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IServerAdminService serverAdminService;
	
	@Autowired
	private IServerNotifyLogService serverNotifyLogService;
	
	@Autowired
	private IServerNotifyRuleService serverNotifyRuleService;
	
	@Autowired
	private IServerAlertRuleService serverAlertRuleService;
	
	private static final String SEND_TYPE_RANGE = "range";
	private static final String SEND_TYPE_CUSTOM = "custom";
	
	private static final String NOTIFY_METHOD_EMAIL = "email";
	private static final String NOTIFY_METHOD_TEL = "tel";
	
	@Autowired
	private SpPersonneService personneService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private SmsUtils smsUtils;
	
	@Value("${task.effectiveTime}")
	private Long time;
	
	@Value("${spring.mail.username}")
	private String mailFrom;
	
	/**
	 * 每隔半分钟发送通知
	 */
	@Scheduled(fixedDelay = 30 * 1000)
	public void notifyAdmin() {
		String key = "";
		try {
			key = this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean lock = redisUtils.lock(key,time);
			if(lock) {
				List<ServerNotifyLog> notifyLogs = getUnSendLogs();
				if(CollectionUtils.isEmpty(notifyLogs)) return;
				sendNotify(notifyLogs);
			}
		} catch (Exception e) {
			logger.error("轮询发送告警通知失败", e);
		}finally {
			redisUtils.delete(key);
		}
	}
	
	/**
	 * 发送通知
	 * @param notifyLogs
	 */
	private void sendNotify(List<ServerNotifyLog> notifyLogs) {
		for (ServerNotifyLog serverNotifyLog : notifyLogs) {
			ServerAlertRule serverAlertRule = serverAlertRuleService.select(serverNotifyLog.getAlertId());
			if(Objects.isNull(serverAlertRule)) {
				logger.error("轮询发送告警通知失败 : notify_id --> " + serverNotifyLog.getId(), new RuntimeException("未找到告警规则"));
			} else {
				String notifyMethod = serverAlertRule.getNotifyMethod();
				//range：排班 sp_personne
				//custom：自定义 sp_user
				String contactsMethod = serverAlertRule.getContactsMethod();
				List<String> contacts = null;
				List<String> userCodes = Arrays.asList(serverNotifyLog.getUserCode().split(","));
				if(SEND_TYPE_RANGE.equalsIgnoreCase(contactsMethod)) {
					List<SpPersonne> list = personneService.listByCodes(userCodes);
					contacts = list.stream().map(p -> 
					{ 
						if(NOTIFY_METHOD_EMAIL.equalsIgnoreCase(notifyMethod)) {
							return p.getEmail();
						}
						return p.getMobileno(); 
						}).collect(Collectors.toList());
				} else if(SEND_TYPE_CUSTOM.equalsIgnoreCase(contactsMethod)) {
					StringBuilder sql = new StringBuilder(64);
					sql.append("select * from sp_user where user_code in (");
					userCodes.forEach(u -> {
						sql.append("\"" + u + "\"");
						sql.append(",");
					});
					List<SpUser> spUsers = jdbcTemplate.query(sql.substring(0, sql.lastIndexOf(",")) + ")", new Object[] {}, new BeanPropertyRowMapper<SpUser>(SpUser.class));
					contacts = spUsers.stream().map(u -> { 
						if(NOTIFY_METHOD_EMAIL.equalsIgnoreCase(notifyMethod)) {
							return u.getEmail();
						}
						return u.getPhone();
						
					}).collect(Collectors.toList());
				} else {
					logger.error("轮询发送告警通知失败 : notify_id --> " + serverNotifyLog.getId(), new RuntimeException("未找到告警通知方式"));
					continue;
				}
				sendNotify(serverNotifyLog, contacts, notifyMethod);
			}
		}
	}
	
	private void sendNotify(ServerNotifyLog serverNotifyLog, List<String> contacts, String sendMtd) {
		if(NOTIFY_METHOD_EMAIL.equalsIgnoreCase(sendMtd)) {
			sendEmail(serverNotifyLog, contacts);
		} else if(NOTIFY_METHOD_TEL.equalsIgnoreCase(sendMtd)) {
			sendSms(serverNotifyLog, contacts);
		} else {
			logger.error("轮询发送告警通知失败 : notify_id --> " + serverNotifyLog.getId(), new RuntimeException("未找到告警通知方式"));
		}
	}
	
	/**
	 * 判断服务时候可以接收通知
	 * @param notifyRule 通知规则
	 */
	private boolean chkServerValuable(ServerNotifyRule notifyRule) {
		if(!notifyRule.getIsEnable()) return false;
		return canRecvNow(notifyRule, !StringUtils.isEmpty(notifyRule.getRecvStartTime()));
	}
	
	/**
	 * 当前时间是否可以接收
	 * @param notifyRule
	 * @return
	 */
	private boolean canRecvNow(ServerNotifyRule notifyRule, boolean recvOrRefuse) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		try {
			String startPattern = String.format("%s %s", dateFormat.format(new Date()), 
					recvOrRefuse ? notifyRule.getRecvStartTime() : notifyRule.getRefuseStartTime()
					);
			Date startTime = timeFormat.parse(startPattern);
			
			String endPattern = String.format("%s %s", dateFormat.format(new Date()), 
					recvOrRefuse ? notifyRule.getRecvEndTime() : notifyRule.getRefuseEndTime());
			Date endTime = timeFormat.parse(endPattern);
			if(startTime.before(now) && endTime.after(now)) {
				return recvOrRefuse;
			}
		} catch (Exception e) {
			logger.error("轮询发送告警通知失败", e);
		}
		return !recvOrRefuse;
	}
	
	/**
	 * 邮件通知
	 * @param notifyRule
	 */
	private void sendEmail(ServerNotifyLog serverNotifyLog, List<String> contacts) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
			mmh.setFrom(mailFrom);
			mmh.setSubject("服务告警通知");
			mmh.setText(serverNotifyLog.getContent(), true);
			mmh.setTo(contacts.toArray(new String[] {}));
			mailSender.send(mimeMessage);
			markNotifyLog(serverNotifyLog);
		} catch (MessagingException e) {
			logger.error("告警通知邮件发送失败", e);
		}
	}
	
	private void sendSms(ServerNotifyLog serverNotifyLog, List<String> contacts) {
		for (String contact : contacts) {
			String content = String.format("管理员请注意：您所负责的服务器【 %s】 正产生告警, 告警指标为【%s】。 请即刻处理!", 
					serverNotifyLog.getServerName(),
					serverNotifyLog.getParamName(),
					serverNotifyLog.getLevel().toString());
			boolean flag = smsUtils.sendSms(contact, content);
			if(flag) {
				markNotifyLog(serverNotifyLog);
			} else {
				logger.error("轮询发送告警通知失败 : notify_id --> " + serverNotifyLog.getId(), new RuntimeException("短信发送失败"));
			}
		}
	}
	
	/**
	 * 标记告警通知
	 * @param serverNotifyLog
	 */
	private void markNotifyLog(ServerNotifyLog serverNotifyLog) {
		serverNotifyLog.setIsSend(true);
		serverNotifyLogService.updateNotifyLog(serverNotifyLog);
	}
	
	/**
	 * 查询未发送的告警通知
	 * @return
	 */
	private List<ServerNotifyLog> getUnSendLogs() {
		NotifyLogVO nlvo = new NotifyLogVO();
		nlvo.setStartTime(getCurntDateBeginning());
		nlvo.setEndTime(new Date());
		nlvo.setIsSend(false);
		nlvo.setPageSize(100);
		nlvo.setPageNo(1);
		Page<ServerNotifyLog> listServerNotifyLog = serverNotifyLogService.listServerNotifyLog(nlvo);
		return listServerNotifyLog.getData();
	}
	
	/**
	 * 获取当天零时零分零秒
	 * @return
	 */
	private Date getCurntDateBeginning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);      
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
	}
}
