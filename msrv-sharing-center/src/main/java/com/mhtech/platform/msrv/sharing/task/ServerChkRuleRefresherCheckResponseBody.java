package com.mhtech.platform.msrv.sharing.task;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.log.pojo.ServerChkRuleVO;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.auth.service.ServerChkRuleService;
import com.mhtech.platform.msrv.monitor.service.IAlertLogService;
import com.mhtech.platform.msrv.monitor.service.IParamAlertService;
import com.mhtech.platform.msrv.monitor.service.IServerAlertRuleService;
import com.mhtech.platform.msrv.pojo.AlertLogVO;
import com.mhtech.platform.msrv.pojo.MonitorStatus.ParamAlertStatus;
import com.mhtech.platform.msrv.pojo.ServerAlertRule;
import com.mhtech.platform.msrv.pojo.ServerParamAlert;
import com.mhtech.platform.msrv.sharing.dao.model.GatewayAccessLog;
import com.mhtech.platform.msrv.sharing.dao.model.ServerMonitorLog;
import com.mhtech.platform.msrv.sharing.service.IGatewayAccessLogService;
import com.mhtech.platform.msrv.sharing.service.ServerMonitorLogService;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

@Component
public class ServerChkRuleRefresherCheckResponseBody {

	@Autowired
	private ServerChkRuleService serverChkRule;
	@Autowired
	private ServerMonitorLogService serverMLS;
	@Autowired
	IworkService iworkService;
	@Autowired
	private IGatewayAccessLogService gatewayService;
	@Autowired
	private IServerAlertRuleService AlertRuleService;
	@Autowired
	private IParamAlertService parmAlertService;
	@Autowired
	private IAlertLogService alertLogService;
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${task.effectiveTime}")
	private Long time;
	
	@Value("${servleSelfCheck.Name}")
	private String paramName;
	@Value("${servleSelfCheck.AliasName}")
	private String paramAlias;
	@Value("#{'${servleSelfCheck.serverParam}'.split(',')}")
	private List<String> serverParamList;;
	
	private static String serverParam;
	
	private final Logger logger = LoggerFactory.getLogger(ServerChkRuleRefresherCheckResponseBody.class);

	/**
	 * 
	 */
	@Scheduled(fixedDelay = 1000 * 60 * 2)
//	@Scheduled(cron = "0 0 0-6 * * ?")
	@Transactional(rollbackFor = Exception.class)
	public void getServerChkRuleRefresher() {
		logger.info("come into getServerChkRuleRefresher....");
		String key = "";
		try {
			key = this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean lock = redisUtils.lock(key,time);
			if(lock) {
				List<ServerChkRuleVO> list = serverChkRule.queryList();
				logger.info("===list.size==="+list.size());
				if(!CollectionUtils.isEmpty(list)) {
					BigDecimal alertValue = null;
					List<ServerMonitorLog> monitorLogList = new ArrayList<ServerMonitorLog>();
					for (int i = 0; i < list.size(); i++) {
						ServerChkRuleVO serverChkRuleVO = list.get(i);
						//预警规则
						List<ServerAlertRule> alertRuleList = null;
						//预警指标
						ServerParamAlert serverParamAlert = null;
						
						//Json_Gateway方式
						if (serverChkRuleVO.getMethod().equals(40)) {
							serverParam = serverParamList.get(4);
							//预警指标
							serverParamAlert = parmAlertService.getParamAlert(serverChkRuleVO.getServerId(),serverParam, ParamAlertStatus.Activator);
							logger.info("服务id="+serverChkRuleVO.getServerId()+"JsonField预警指标="+serverParamAlert.getAlertValue());
							String jsonFieldCheck = serverChkRuleVO.getJsonFieldCheck();
							String[] rule = jsonFieldCheck.substring(0, jsonFieldCheck.indexOf("=")).trim().split("\\.");
							String checkValue = jsonFieldCheck.substring(jsonFieldCheck.indexOf("==")+2).trim();
							logger.info("Json check = "+Arrays.toString(rule)+"=="+checkValue);
							List<GatewayAccessLog> dataList = gatewayService.selectLogByServerId(serverChkRuleVO.getServerId());
							logger.info("==日志size=="+dataList.size());
							//得到预警规则
							alertRuleList = AlertRuleService.getServerAlertRuleByParam(serverChkRuleVO.getServerId(), serverParam);
							logger.info("=预警规则="+alertRuleList.size());
							
							if(!CollectionUtils.isEmpty(dataList)) {
								int num = 0 ;
								for (GatewayAccessLog gatewayAccessLog : dataList) {
									boolean flag = false;
									String responseBody = gatewayAccessLog.getResponseBody();
									if(responseBody!=null) {
										JSONObject obj = null;
										//根据检查的字段等到对应的JSONObject
										for (int j = 1; j < rule.length; j++) {
											obj = JSONObject.parseObject(responseBody);
											if(obj!=null && obj.containsKey(rule[j])) {
												responseBody = obj.get(rule[j]).toString();
											}else {
												continue;
											}
										}
										//取到
										Object object = obj.get(rule[rule.length-1]);
										if(object!=null) {
											String result = object.toString();
											if(result.equals(checkValue)) {
												flag = true;
											}
										}
									}	
									//给出结论值
									alertValue = flag ? BigDecimal.valueOf(1) :serverParamAlert.getAlertValue();
									
									ServerMonitorLog sml = new ServerMonitorLog(iworkService.getNextId(), serverChkRuleVO.getServerId(), serverParam, paramName , alertValue, null);
									monitorLogList.add(sml);
									
									
									if (serverParamAlert!=null && alertValue.compareTo(serverParamAlert.getAlertValue()) == 0 ) {
										//写入预警日志数据库
										AlertLogVO alvo = new AlertLogVO();
										alvo.setServerId(serverChkRuleVO.getServerId());
										alvo.setParamName(serverParam);
										alvo.setParamAlias(serverParam);
										alvo.setAlterValue(alertValue);
										long currentTime = System.currentTimeMillis();			
										
										Date nowTime = new Date(currentTime);
										DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置精度
										String format2 = format.format(nowTime);
										nowTime = format.parse(format2);
										alvo.setAlertTime(nowTime);
										
										//如果在暂停监控时间内,设置值为已提示 1
										Date stopListenStart = alertRuleList.get(0).getStopListenStart();
										Date stopListenEnd = alertRuleList.get(0).getStopListenEnd();
										
										boolean isAlert = false;
										if(!StringUtils.isEmpty(stopListenStart)&&!StringUtils.isEmpty(stopListenEnd)&&(stopListenStart.before(nowTime) && stopListenEnd.after(nowTime)))
											isAlert = true;
										alvo.setIsAlerting(isAlert);
										alvo.setNotifyId(0l);
										alertLogService.addAlertLog(alvo);
									}			
								}	
							}	
						}
					}
					if(!CollectionUtils.isEmpty(monitorLogList)) {
						serverMLS.save(monitorLogList);
					}
				}
			}
        }catch (Exception e) {
			logger.error("定时执行服务自检(JsonField)报错");
			logger.error(e.getMessage());		
			e.printStackTrace();
		}finally {
			redisUtils.delete(key);
		}
	}
}
