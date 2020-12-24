package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.IAlertNotifyTmplService;
import com.mhtech.platform.msrv.monitor.service.IServerAdminService;
import com.mhtech.platform.msrv.monitor.service.IServerNotifyLogService;
import com.mhtech.platform.msrv.monitor.service.SpDeviceAdminService;
import com.mhtech.platform.msrv.pojo.AlertNotifyLogCounterBO;
import com.mhtech.platform.msrv.pojo.AlertNotifyLogVO;
import com.mhtech.platform.msrv.pojo.AlertNotifyTmpl;
import com.mhtech.platform.msrv.pojo.AlertRateVO;
import com.mhtech.platform.msrv.pojo.DailyAlertsVO;
import com.mhtech.platform.msrv.pojo.MonitorStatus.NotifyLogStatus;
import com.mhtech.platform.msrv.pojo.DailyAlertsTotalVO;
import com.mhtech.platform.msrv.pojo.DetailAlertVO;
import com.mhtech.platform.msrv.pojo.DictVO;
import com.mhtech.platform.msrv.pojo.NotifyLogBO;
import com.mhtech.platform.msrv.pojo.NotifyLogVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerNotifyLog;
import com.mhtech.platform.msrv.pojo.SpDeviceAdmin;
import com.mhtech.platform.msrv.sharing.dao.mapper.DictMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerNotifyLogMapper;

@Service("serverNotifyLogService")
public class ServerNotifyLogServiceImpl implements IServerNotifyLogService {
	private final Logger logger= LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ServerNotifyLogMapper serverNotifyLogMapper;

	@Autowired
	private IAlertNotifyTmplService alertNotifyTmplService;

	@Autowired
	private SpDeviceAdminService spDeviceAdminService;
	
	@Autowired
	private IworkService workService;

	@Autowired
	private IServerAdminService serverAdminService;
	
	@Autowired
	private DictMapper dictMapper;

	@Override
	public void addNotifyLog(ServerNotifyLog snl) {
		serverNotifyLogMapper.insert(snl);
	}

	@Override
	public void addNotifyLog(long serverId, List<Long> logIds) {
		AlertNotifyTmpl notifyTmpl = alertNotifyTmplService
				.getServerUsingNotifyTmpl(serverId);
		ServerNotifyLog snl = new ServerNotifyLog();
		snl.setId(workService.getNextId());
		StringBuilder alertLogs = new StringBuilder();
		logIds.forEach(logId -> {
			alertLogs.append(logId);
			alertLogs.append(",");
		});
		alertLogs.deleteCharAt(alertLogs.length() - 1);
		snl.setAlertLogs(alertLogs.toString());
		snl.setContent(notifyTmpl.getTmplConent());
		snl.setCreatedTime(new Date());
		snl.setIsSend(false);
		snl.setServerId(serverId);
		snl.setStatus(NotifyLogStatus.Unprocessed.getCode());
		ServerAdminInfo sai = serverAdminService.getServerInfo(serverId);
		snl.setUsername(sai.getUsername());
		serverNotifyLogMapper.insert(snl);
	}
	
	@Override
	public void addNotifyLog(NotifyLogBO nlbo) {
		try {
			ServerNotifyLog snl = new ServerNotifyLog();
			snl.setId(nlbo.getId());
			snl.setParamName(nlbo.getParamName());
			snl.setUserCode(nlbo.getContacts());
			snl.setContent(nlbo.getContent());
			snl.setCreatedTime(new Date());
			snl.setIsSend(false);
			snl.setAlertId(nlbo.getAlertRuleId());
			snl.setServerId(nlbo.getServerId());
			snl.setStatus(NotifyLogStatus.Unprocessed.getCode());
			//可能是硬件也可能是软件
			ServerAdminInfo sai = serverAdminService.getServerInfo(nlbo.getServerId());
			String userName="";
			System.err.println("sai==null:"+sai==null);
			if (sai==null) {//如果软件查找不到，查找硬件
				SpDeviceAdmin spDeviceAdmin=spDeviceAdminService.findEntityByKey(nlbo.getServerId());
				userName=spDeviceAdmin.getUsername();
			}else {
				userName=sai.getUsername();
			}
			System.err.println("userName:"+userName);
			//snl.setUsername(sai.getUsername());
			snl.setUsername(userName);
			serverNotifyLogMapper.insert(snl);
		} catch (Exception e) {
			logger.error("告警通知发送失败", e);
		}
	}

	@Override
	public Page<ServerNotifyLog> listServerAlertNotifyLog(NotifyLogVO nlvo) {
		PageHelper.startPage(nlvo.getPageNo(), nlvo.getPageSize(),
				nlvo.getOrderBy());
		com.github.pagehelper.Page<ServerNotifyLog> listPage = (com.github.pagehelper.Page<ServerNotifyLog>) serverNotifyLogMapper
				.listAlertNotifyLog(nlvo);
		Page<ServerNotifyLog> page = new Page<ServerNotifyLog>();
		page.setData(listPage.getResult());
		page.setPageNo(listPage.getPageNum());
		page.setPageSize(listPage.getPageSize());
		page.setTotalCount((int) listPage.getTotal());
		return page;
	}
	
	@Override
	public Page<ServerNotifyLog> listServerNotifyLog(NotifyLogVO nlvo) {
		PageHelper.startPage(nlvo.getPageNo(), nlvo.getPageSize(),
				nlvo.getOrderBy());
		com.github.pagehelper.Page<ServerNotifyLog> listPage = (com.github.pagehelper.Page<ServerNotifyLog>) serverNotifyLogMapper
				.listNotifyLog(nlvo);
		Page<ServerNotifyLog> page = new Page<ServerNotifyLog>();
		page.setData(listPage.getResult());
		page.setPageNo(listPage.getPageNum());
		page.setPageSize(listPage.getPageSize());
		page.setTotalCount((int) listPage.getTotal());
		return page;
	}

	@Override
	public DailyAlertsTotalVO totalDailyNotifyLog(AlertNotifyLogCounterBO param) {
		checkDateSection(param.getStartTime(), param.getEndTime());
		List<DailyAlertsVO> totalDailyNotifyLog = serverNotifyLogMapper
				.totalDailyNotifyLog(param);
		if (CollectionUtils.isEmpty(totalDailyNotifyLog)) {
			return null;
		}
		DailyAlertsTotalVO vo = new DailyAlertsTotalVO();
		vo.setDailyAlerts(totalDailyNotifyLog);
		return vo;
	}
	
	@Override
	public DetailAlertVO detailNotifyLog(AlertNotifyLogCounterBO param) {
		checkDateSection(param.getStartTime(), param.getEndTime());
		List<DailyAlertsVO> totalDailyNotifyLog = serverNotifyLogMapper
				.totalDailyNotifyLog(param);
		DetailAlertVO vo = new DetailAlertVO();
		vo.setDailyAlerts(totalDailyNotifyLog);
		List<AlertRateVO> alertType = serverNotifyLogMapper.detailAlertType(param.getNotifyId());
		vo.setAlertRate(alertType);
		return vo;
	}

	private void checkDateSection(Date startTime, Date endTime) {
		if (Objects.isNull(startTime) || Objects.isNull(endTime)) {
			throw new RuntimeException("统计时间段不能为空");
		}
		long differ = endTime.getTime() - startTime.getTime();
		long days = differ / 1000 / 60 / 60 / 24;
		if (days > 7) {
			throw new RuntimeException("统计时间段不能超过七天");
		}
	}
	
	@Override
	public int addNotifyLog(AlertNotifyLogVO snfl) {
		return serverNotifyLogMapper.insertNotifyLog(snfl);
	}
	
	@Override
	public int updateNotifyLog(ServerNotifyLog serverNotifyLog) {
		com.mhtech.platform.msrv.sharing.dao.model.ServerNotifyLog snl = new com.mhtech.platform.msrv.sharing.dao.model.ServerNotifyLog();
		snl.setContent(serverNotifyLog.getContent());
		snl.setId(serverNotifyLog.getId());
		snl.setIsSend(serverNotifyLog.getIsSend());
		snl.setParamName(serverNotifyLog.getParamName());
		snl.setServerId(serverNotifyLog.getServerId());
		snl.setStatus(serverNotifyLog.getStatus());
		snl.setUserCode(serverNotifyLog.getUserCode());
		snl.setUsername(serverNotifyLog.getUsername());
		return serverNotifyLogMapper.updateByPrimaryKeySelective(serverNotifyLog);
	}
	
	@Override
	public ServerNotifyLog getNotifyLogById(Long id) {
		com.mhtech.platform.msrv.sharing.dao.model.ServerNotifyLog snl = serverNotifyLogMapper.selectByPrimaryKey(id);
		if(snl == null) return null;
		ServerNotifyLog log = new ServerNotifyLog();
		log.setContent(snl.getContent());
		log.setCreatedTime(snl.getCreatedTime());
		log.setId(snl.getId());
		log.setIsSend(snl.getIsSend());
		log.setParamName(snl.getParamName());
		log.setServerId(snl.getServerId());
		log.setStatus(snl.getStatus());
		log.setUserCode(snl.getUserCode());
		log.setUsername(snl.getUsername());
		log.setAlertId(snl.getAlertId());
		return log;
	}
	
	@Override
	public List<DictVO> listDictByTypes(List<String> types) {
		return dictMapper.listDictByTypes(types);
	}
	@Override
	public List<DailyAlertsVO> listNotifyStatistics(String topTime, String endTime, Long id) {
		// TODO Auto-generated method stub
		return serverNotifyLogMapper.listNotifyStatistics(topTime, endTime, id);
	}
	
	@Override
	public void changeStatus(String status,Long id) {
		serverNotifyLogMapper.changeStatus(status,id);
	}

	@Override
	public List<ServerNotifyLog> getServerNotifyLogList(NotifyLogVO nlvo) {
		return null;
	}
}
