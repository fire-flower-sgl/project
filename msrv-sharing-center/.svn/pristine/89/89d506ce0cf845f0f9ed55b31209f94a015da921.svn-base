package com.mhtech.platform.msrv.sharing.redis;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import com.mhtech.platform.msrv.monitor.service.IAlertLogService;
import com.mhtech.platform.msrv.monitor.service.IServerAlertRuleService;
import com.mhtech.platform.msrv.pojo.AlertLog;
import com.mhtech.platform.msrv.pojo.AlertLogVO;
import com.mhtech.platform.msrv.pojo.ServerAlertRule;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

public class MonitorAlertHandler {

private final Logger logger= LoggerFactory.getLogger(getClass());
	
	@Value("${monitor.alert.switcher}")
	private String monitorAlertSwitcher;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private IAlertLogService alertLogService;
	
	@Autowired
	private IServerAlertRuleService serverAlertRuleService;
	
	public void alert(AlertLogVO alvo) {
		try {
//			if(!chkAlertSwitch()) return;
			updateAlerts(alvo);
		} catch (Exception e) {
			logger.error("监听预警消息失败", e);
		}
	}
	
	/**
	 * 更新预警日志
	 * @param alvo 服务监控信息
	 */
	private void updateAlerts(AlertLogVO alvo) {
		AlertLogVO conditions = new AlertLogVO();
		conditions.setServerId(alvo.getServerId());
		conditions.setParamName(alvo.getParamName());
		//获取匹配基本日志的预警规则
		List<ServerAlertRule> serverAlertRules = serverAlertRuleService.getServerAlertRuleByParam(
				alvo.getServerId(), alvo.getParamName());
		//规则排序，优先比较级别高的规则
		Collections.sort(serverAlertRules, new Comparator<ServerAlertRule>() {
			@Override
			public int compare(ServerAlertRule o1, ServerAlertRule o2) {
				return o2.getLevel() - o1.getLevel();
			}
		});
		for (ServerAlertRule serverAlertRule : serverAlertRules) {
			long now = System.currentTimeMillis();
			System.err.println(now+"serverAlertRule.getDuration():"+serverAlertRule.getDuration());
			conditions.setStartAlertTime(new Date(now - serverAlertRule.getDuration() * 1000));
			conditions.setEndAlertTime(new Date(now));
			conditions.setIsAlerting(false);//未产生告警通知的预警记录
			//查找基础日志里面符合条件的日志是否达到预警标准
			List<AlertLog> logs = alertLogService.listAlertLog(conditions);
			System.err.println("=====查找基础日志里面符合条件的日志是否达到预警标准======"+logs.size());
			if(CollectionUtils.isEmpty(logs)) {
				//如果值为-1,获得-1的那条日志，报警；否则结束报警进程
				if (alvo.getAlterValue().compareTo(new BigDecimal(-1))==0) {
//					AlertLog newLog=new AlertLog();
//					BeanUtils.copyProperties(alvo, newLog);
					conditions.setAlertTime(alvo.getAlertTime());
					conditions.setStartAlertTime(null);
					conditions.setEndAlertTime(null);
					conditions.setParamAlias(alvo.getParamAlias());
					conditions.setAlterValue(alvo.getAlterValue());
					logs = alertLogService.listAlertLog(conditions);
				}else {
					continue;
				}
				
			}
			//匹配到最高级别后则跳过其他规则  预警值为-1时直接报警
			System.err.println("=====预警值是否为-1======"+alvo.getAlterValue());
			if(logs.size() >= serverAlertRule.getAlertLimit()||alvo.getAlterValue().compareTo(new BigDecimal(-1))==0) {
				System.err.println(serverAlertRule.getAlertLimit()+"=====达到预警标准======"+serverAlertRule.getParamName());
				alertLogService.updateAlertStatusAndNotify(logs, serverAlertRule);
				break;
			}
		}
		/*long now = System.currentTimeMillis();
		conditions.setStartAlertTime(new Date(now - 1000 * 10));
		conditions.setEndAlertTime(new Date(now));
		conditions.setIsAlerting(false);//未产生告警通知的预警记录
		List<AlertLog> logs = alertLogService.listAlertLog(conditions);
		if(CollectionUtils.isEmpty(logs)) return;
		
		updateIfReachRule(logs, serverAlertRules);*/
	}
	
	/**
	 * 达到预警规则更新记录状态及发出预警通知
	 * @param logs
	 * @param serverAlertRule
	 */
	private void updateIfReachRule(List<AlertLog> logs, List<ServerAlertRule> serverAlertRules) {
		Collections.sort(serverAlertRules, new Comparator<ServerAlertRule>() {
			@Override
			public int compare(ServerAlertRule o1, ServerAlertRule o2) {
				return o2.getLevel() - o1.getLevel();
			}
		});
		for (ServerAlertRule sar : serverAlertRules) {
			if(logs.size() >= sar.getAlertLimit()) {
				alertLogService.updateAlertStatusAndNotify(logs, sar);
				break;
			}
		}
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
	
	/**
	 * 检查监听器状态， 开启或停止
	 * @return
	 */
	private boolean chkAlertSwitch() {
		Boolean alertRun = (Boolean) redisUtils.get(monitorAlertSwitcher);
		if(alertRun == null) {
			throw new RuntimeException("预警监控状态丢失");
		}
		return alertRun;
	}
}
