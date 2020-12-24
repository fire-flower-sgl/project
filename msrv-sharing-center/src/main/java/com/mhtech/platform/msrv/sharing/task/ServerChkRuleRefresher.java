package com.mhtech.platform.msrv.sharing.task;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.mhtech.platform.msrv.sharing.dao.model.ServerMonitorLog;
import com.mhtech.platform.msrv.sharing.http.HttpClientPoolUtils;
import com.mhtech.platform.msrv.sharing.service.ServerMonitorLogService;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

@Component
public class ServerChkRuleRefresher {

	@Autowired
	private ServerChkRuleService serverChkRule;
	@Autowired
	private ServerMonitorLogService serverMLS;
	@Autowired
	IworkService iworkService;
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
	
	private final Logger logger = LoggerFactory.getLogger(ServerChkRuleRefresher.class);
	

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
						boolean flag = false;
						
						switch (serverChkRuleVO.getMethod()) {
						//ping方式
						case 10:
							 try {
								serverParam = serverParamList.get(0);
								//预警指标
								serverParamAlert = parmAlertService.getParamAlert(serverChkRuleVO.getServerId(),serverParam, ParamAlertStatus.Activator);
								InetAddress address = InetAddress.getByName(serverChkRuleVO.getUri());
								flag = address.isReachable(5000);
								alertValue = flag ? BigDecimal.valueOf(1) :serverParamAlert.getAlertValue();
							} catch (Exception e1) {
								alertValue = serverParamAlert.getAlertValue();
								logger.error(e1.getMessage());
							}
							break;
						//telnet方式
						case 20:
							Socket server = null;
					        try {
					        	serverParam = serverParamList.get(1);
								//预警指标
								serverParamAlert = parmAlertService.getParamAlert(serverChkRuleVO.getServerId(),serverParam, ParamAlertStatus.Activator);
					            server = new Socket();
					            InetSocketAddress addressT = new InetSocketAddress(serverChkRuleVO.getUri(),serverChkRuleVO.getPort());
					            server.connect(addressT, 5000);
					            flag = true;
					            alertValue = flag ? BigDecimal.valueOf(1) :serverParamAlert.getAlertValue();
					        } catch (Exception e) {
					        	alertValue = serverParamAlert.getAlertValue();
					        	logger.error(e.getMessage());
					        }finally{
					            if(server!=null)
					                try {
					                    server.close();
					                } catch (IOException e) {
					                	
					                }
					        }
							break;
						//get方式	
						case 30:
							try {
								serverParam = serverParamList.get(2);
								//预警指标
								serverParamAlert = parmAlertService.getParamAlert(serverChkRuleVO.getServerId(),serverParam, ParamAlertStatus.Activator);
								String uri = serverChkRuleVO.getUri();
								String[] split = uri.split(":", uri.indexOf(":")+1);
								uri = split[0]+":"+split[1]+":"+serverChkRuleVO.getPort()+split[2]+"?"+serverChkRuleVO.getHttpHeaders();
								String getResult = HttpClientPoolUtils.doGet(uri);
								JSONObject getObj = JSONObject.parseObject(getResult);
								if(!StringUtils.isEmpty(getObj.get("status")) && getObj.get("status").toString().equals("200")) {
									flag = true;
								}
								alertValue = flag ? BigDecimal.valueOf(1) :serverParamAlert.getAlertValue();
							} catch (Exception e) {
								alertValue = serverParamAlert.getAlertValue();
								logger.error(e.getMessage());
							}
							break;
						//post方式
						case 31:
							try {
								serverParam = serverParamList.get(3);
								//预警指标
								serverParamAlert = parmAlertService.getParamAlert(serverChkRuleVO.getServerId(),serverParam, ParamAlertStatus.Activator);
								String uri = serverChkRuleVO.getUri();
								String[] split = uri.split(":", uri.indexOf(":")+1);
								uri = split[0]+":"+split[1]+":"+serverChkRuleVO.getPort()+split[2];
								String PostResult = HttpClientPoolUtils.doPost(uri, serverChkRuleVO.getHttpRequestBody());
								JSONObject postObj = JSONObject.parseObject(PostResult);
								if(!StringUtils.isEmpty(postObj.get("status")) && postObj.get("status").toString().equals("200")) {
									flag = true;
								}
								alertValue = flag ? BigDecimal.valueOf(1) :serverParamAlert.getAlertValue();
							} catch (Exception e) {
								alertValue = serverParamAlert.getAlertValue();
								logger.error(e.getMessage());
							}
							break;
						default:
							break;
						}
						
						//alertValue为空表示是其他自检方式,跳出循环 
						if(alertValue == null) {
							continue;
						}
						
						ServerMonitorLog sml = new ServerMonitorLog(iworkService.getNextId(), serverChkRuleVO.getServerId(), serverParam, paramName, alertValue, null);
						monitorLogList.add(sml);
				
						if (serverParamAlert!=null && alertValue.compareTo(serverParamAlert.getAlertValue()) == 0 ) {
							
							//得到预警规则
							alertRuleList = AlertRuleService.getServerAlertRuleByParam(serverChkRuleVO.getServerId(), serverParam);
							logger.info("预警规则条数="+alertRuleList.size());
							
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
						alertValue = null;
					}
					if(!CollectionUtils.isEmpty(monitorLogList)) {
						serverMLS.save(monitorLogList);
					}
				}
			}
        }catch (Exception e) {
			logger.error("定时执行服务自检报错");
			logger.error(e.getMessage());		
			e.printStackTrace();
		}finally {
			redisUtils.delete(key);
		}
	}
}
