package com.mhtech.platform.msrv.sharing.service.dubbo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.IAlertLogService;
import com.mhtech.platform.msrv.monitor.service.IParamAlertService;
import com.mhtech.platform.msrv.monitor.service.IPropertyService;
import com.mhtech.platform.msrv.monitor.service.IServerAdminService;
import com.mhtech.platform.msrv.monitor.service.IServerMonitorLog;
import com.mhtech.platform.msrv.monitor.service.SpDeviceAdminService;
import com.mhtech.platform.msrv.pojo.AlertLogVO;
import com.mhtech.platform.msrv.pojo.ComponentUsageVO;
import com.mhtech.platform.msrv.pojo.MonitorStatus.ParamAlertStatus;
import com.mhtech.platform.msrv.pojo.MonitorSyncResult;
import com.mhtech.platform.msrv.pojo.PropertyVOId;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerMonitorInfo;
import com.mhtech.platform.msrv.pojo.ServerParamAlert;
import com.mhtech.platform.msrv.pojo.SpDeviceAdmin;
import com.mhtech.platform.msrv.pojo.SummaryVO;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerMonitorLogMapper;
import com.mhtech.platform.msrv.sharing.dao.model.ServerMonitorLog;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

/**
 * 监控日志管理
 * 
 * @author Guo
 *
 */
@Service("serverMonitorLog")
public class MonitorLogServiceImpl implements IServerMonitorLog {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 服务预警消息通道名称
	 */
	@Value("${monitor-server-alerts}")
	private String monitorServerAlerts;

	@Autowired
	private ServerMonitorLogMapper monitorMapper;

	@Autowired
	private IServerAdminService serverAdminService;
	
	@Autowired
	private SpDeviceAdminService deviceAdminService;

	@Autowired
	private IParamAlertService paramAlertService;

	@Autowired
	private IworkService workService;

	@Autowired
	private IAlertLogService alertLogService;

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private IPropertyService propertyService;;
	
	@Override
	public void addMonitorLog(ServerMonitorInfo smi) {
		saveLog(smi);
	}

	@Override
	public MonitorSyncResult addServerMonitorLogList(List<ServerMonitorInfo> infos) {
		StringBuilder result = new StringBuilder();
		int fails = 0;
		for (ServerMonitorInfo serverMonitorInfo : infos) {
			try {
				//插入日志
				saveLog(serverMonitorInfo);
			} catch (Exception e) {
				result.append(e.getMessage());
				result.append(";");
				fails++;
			}
		}
		return MonitorSyncResult.build().message(result.length() > 0 ?  result.substring(0, result.length() - 1) : "success")
				.success(infos.size() - fails)
				.total(infos.size());
//		ServerAdminInfo sai = serverAdminService.getServerInfo(log
//				.getServerId());
//		if (sai == null) {
//			throw new RuntimeException("不存在的服务编号");
//		}
//		List<ServerParamAlert> paramAlerts = paramAlertService
//				.listAlerts4Server(log.getServerId(),
//						ParamAlertStatus.Activator);
//		if (CollectionUtils.isEmpty(paramAlerts)) {
//			throw new RuntimeException("服务未配置预警指标");
//		}
//		StringBuilder result = new StringBuilder();
//		int fails = 0;
//		List<MonitorParam> params = log.getParams();
//		for (MonitorParam monitorParam : params) {
//			ServerMonitorInfo smi = new ServerMonitorInfo();
//			smi.setServerId(log.getServerId().toString());
//			smi.setParamName(monitorParam.getParamName());
//			smi.setParamValue(monitorParam.getParamValue());
//			try {
//				ServerParamAlert curntParamAlertInfo = getCurntParamAlertInfo(
//						paramAlerts, smi);
//				monitorMapper.insert(buildServerMonitorLog(smi,
//						curntParamAlertInfo.getParamAlias()));
//			} catch (Exception e) {
//				result.append(e.getMessage());
//				result.append(";");
//				fails++;
//			}
//		}
//		return MonitorSyncResult.build().message(result.substring(0, result.length() - 1))
//				.success(params.size() - fails)
//				.total(params.size());
	}
	
	@Override
	public List<ComponentUsageVO> totalComponentUsage(Date startTime,
			Date endTime, int tops) {
		return monitorMapper.totalComponentUsage(startTime, endTime, tops);
	}

	private void saveLog(ServerMonitorInfo smi) {
		ServerMonitorLog log = buildIfServerHasParamAlert(smi);
		monitorMapper.insert(log);
	}

	/**
	 * 若监控服务有改指标则创建日志
	 * 
	 * @param smi
	 * @return
	 */
	private ServerMonitorLog buildIfServerHasParamAlert(ServerMonitorInfo smi) {
		ServerMonitorLog sml = null;
		try {
			SpDeviceAdmin deviceAdmin = null;
			//软件是否存在
			ServerAdminInfo sai =  null;
			//存在IP则判断是由硬件采集终端发起, 根据IP替换为硬件服务器serverId
			if(!StringUtils.isEmpty(smi.getIp())) {
				deviceAdmin = deviceAdminService.findDeviceAdminByIp(smi.getIp());
			} else {
				//API对接形式以serverId认证
				long serverId = Long.parseLong(smi.getServerId());
				sai = serverAdminService.getServerInfo(serverId);
				deviceAdmin = deviceAdminService.findEntityByKey(serverId);
			}
			if (sai == null && deviceAdmin == null) {
				throw new RuntimeException("不存在的服务编号");
			}
			sml = buildServerMonitorLog(smi);
		} catch (NumberFormatException e) {
			logger.error("服务ID错误", e);
			throw new RuntimeException("服务ID错误");
		} catch (Exception e) {
			logger.error("保存监控日志失败", e);
			throw new RuntimeException(e);
		}
		return sml;
	}

	//获取日志相关信息
	private ServerMonitorLog buildServerMonitorLog(ServerMonitorInfo smi) {
		//获取激活的预警规则
		ServerParamAlert serverParamAlert = getCurntParamAlertInfo(smi);
		ServerMonitorLog sml = new ServerMonitorLog();
		sml.setId(workService.getNextId());
		sml.setAlterValue(smi.getParamValue());
		sml.setParamName(smi.getParamName());
		sml.setParamAlias(serverParamAlert.getParamAlias());
		sml.setServerId(Long.parseLong(smi.getServerId()));
		sml.setCreatedTime(smi.getMonitorTime());
		return sml;
	}

	private ServerParamAlert getCurntParamAlertInfo(
			List<ServerParamAlert> paramAlerts, ServerMonitorInfo smi) {
		if (CollectionUtils.isEmpty(paramAlerts)) {
			throw new RuntimeException("服务未配置预警指标");
		}
		System.err.println(paramAlerts.size()+"====paramAlerts======"+paramAlerts.toString());
		System.err.println("ServerMonitorInfo:"+smi.toString());
		for (ServerParamAlert serverParamAlert : paramAlerts) {
			System.err.println("serverParamAlert.getParamName():"+serverParamAlert.getParamName());
			System.err.println("smi.getParamName():"+smi.getParamName());
			if (serverParamAlert.getParamName().equals(smi.getParamName())) {
				System.err.println(smi.getParamValue()+"==========判断需不需要预警============="+serverParamAlert.getAlertValue());
				// 判断是否需要预警，大于预警阈值或者阈值为-1都进行报警
				System.err.println("=====预警值是否为-1======"+smi.getParamValue());
				if (smi.getParamValue().compareTo(
						serverParamAlert.getAlertValue()) > -1||smi.getParamValue().compareTo(new BigDecimal(-1))==0) {
					System.err.println(smi.getParamValue()+"==========需要预警============="+serverParamAlert.getAlertValue());
					alert(smi, serverParamAlert.getParamAlias());
				}
				return serverParamAlert;
			}
		}
		throw new RuntimeException("服务未配置预警指标");
	}

	//获取激活的预警规则
	private ServerParamAlert getCurntParamAlertInfo(ServerMonitorInfo smi) {
		long serverId = Long.parseLong(smi.getServerId());
		List<ServerParamAlert> paramAlerts = paramAlertService
				.listAlerts4Server(serverId, ParamAlertStatus.Activator);
		return getCurntParamAlertInfo(paramAlerts, smi);
	}

	/**
	 * 预警
	 * 
	 * @param smi
	 *            监控日志
	 * @param alertAlias
	 *            预警字段实际别名
	 */
	private void alert(ServerMonitorInfo smi, String alertAlias) {
		AlertLogVO alvo = new AlertLogVO();
		alvo.setAlertTime(smi.getMonitorTime());
		alvo.setAlterValue(smi.getParamValue());
		alvo.setParamAlias(alertAlias);
		alvo.setParamName(smi.getParamName());
		alvo.setServerId(Long.parseLong(smi.getServerId()));
		alvo.setIsAlerting(false);
		alvo.setNotifyId(0l);
		alertLogService.addAlertLog(alvo);
		// 预警记录新增，则推送消息查看是否到达告警的级别
		System.err.println("===========触发redis=============");
		redisUtils.convertAndSend(monitorServerAlerts, alvo);
	}
	
	@Override
	public List<SummaryVO> totalSummary(Date startTime, Date endTime,
			List<String> paramNames, List<String> alertCodes) {
//		List<SummaryVO> list = new ArrayList<SummaryVO>();
		List<SummaryVO> hardwareSummary = monitorMapper.totalHardwareSummary(startTime, endTime, paramNames);
//		List<SummaryVO> bizSummary = monitorMapper.totalBizSummary(startTime, endTime, alertCodes);
//		if(CollectionUtils.isEmpty(hardwareSummary)) {
//			list = bizSummary;
//		} else {
//			hardwareSummary.addAll(bizSummary);
//			list = hardwareSummary;
//		}
		return hardwareSummary;
	}
}
