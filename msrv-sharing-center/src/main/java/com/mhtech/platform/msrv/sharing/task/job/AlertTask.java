package com.mhtech.platform.msrv.sharing.task.job;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.IAlertLogService;
import com.mhtech.platform.msrv.monitor.service.IServerAdminService;
import com.mhtech.platform.msrv.monitor.service.IServerAlertRuleService;
import com.mhtech.platform.msrv.monitor.service.IServerNotifyLogService;
import com.mhtech.platform.msrv.pojo.AlertLog;
import com.mhtech.platform.msrv.pojo.AlertLogVO;
import com.mhtech.platform.msrv.pojo.AlertNotifyLogVO;
import com.mhtech.platform.msrv.pojo.AlertRuleVO;
import com.mhtech.platform.msrv.pojo.ServerAlertRule;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

@Component("AlertTask")
public class AlertTask {
	
	@Autowired
	private IServerAlertRuleService AlertRuleService;
	@Autowired
	private IAlertLogService alertLogService;
	@Autowired
	private IServerNotifyLogService notifyLogService;
	@Autowired
	private IworkService iworkService;
	@Autowired
	private IServerAdminService adminService;
	@Autowired
	private RedisUtils redisUtils;

	@Value("${task.effectiveTime}")
	private Long time;
	
	
	private final Logger logger = LoggerFactory.getLogger(AlertTask.class);
		
	public void alert(Map param) throws Exception {
		String key = "";
		try {
			key = this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean lock = redisUtils.lock(key,time);
			if(lock) {
				//预警规则
				if(param.get("type")==null)return;
				List<ServerAlertRule> alertRuleList = null;
				String type = param.get("type").toString();
				Integer startPort = null;
				Integer endPort = null;
				switch (type) {
				case "second":
					startPort = 1;
					endPort = 60;
					break;
				case "minute":
					startPort = 60;
					endPort = 3600;
					break;
				case "hour":
					startPort = 3600;
					endPort = 3600 * 24 ;
					break;	
				default:
					break;
				}
				
				if(startPort==null)return;
				AlertRuleVO alertRule =  new AlertRuleVO();
				alertRule.setStartPort(startPort);
				alertRule.setEndPort(endPort);
				//根据间隔级别得到预警规则
				alertRuleList = AlertRuleService.getAlertRuleByDuration(alertRule);
				System.err.println("预警规则size=="+alertRuleList.size());
				
				if(!CollectionUtils.isEmpty(alertRuleList)) {
					String paramName = "";
					for (int i = 0; i < alertRuleList.size(); i++) {
						System.err.println(alertRuleList.get(i).getContacts());
						AlertLogVO alvo = new AlertLogVO();
						alvo.setServerId(alertRuleList.get(i).getServerId());
						alvo.setParamName(alertRuleList.get(i).getParamName());
						long currentTime = System.currentTimeMillis();					
						Date nowTime = new Date(currentTime);				
						long startTime = currentTime-1000*alertRuleList.get(i).getDuration();
						Date startAlertTime = new Date(startTime);
						alvo.setStartAlertTime(startAlertTime);
						alvo.setEndAlertTime(nowTime);
						alvo.setIsAlerting(false);
						
						//查询日志count
						List<AlertLog> listAlertLog = alertLogService.listAlertLog(alvo);
						
						Integer alertLimit = Integer.valueOf(alertRuleList.get(i).getAlertLimit().toString());
						Integer count = listAlertLog.size();
						
						
						if(count>=alertLimit) {
							logger.info("目前警告日志count="+count+"\t阈值="+alertLimit);
							AlertNotifyLogVO snfl = new AlertNotifyLogVO();
							snfl.setId(iworkService.getNextId());
							snfl.setServerId(alertRuleList.get(i).getServerId());
							snfl.setUsername(adminService.getServerInfoByServerId(alertRuleList.get(i).getServerId()).getUsername());
							snfl.setParamName(alertRuleList.get(i).getParamName());
							snfl.setUsercode(alertRuleList.get(i).getContacts());
							snfl.setStatus(1);
							snfl.setIsSend(0);
							snfl.setContent("content");
							if(i==0 || ( i>0 && !alertRuleList.get(i).getParamName().equals(paramName)) ) {
								int insert = notifyLogService.addNotifyLog(snfl);
								paramName = alertRuleList.get(i).getParamName();
								//把已经统计的警告日志设为已警告
								alertLogService.updateIsAlert(listAlertLog);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("AlertTask任务执行报错");
			e.printStackTrace();
		} finally {
			redisUtils.delete(key);
		}
	}

}
