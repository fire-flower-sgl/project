package com.mhtech.platform.msrv.sharing.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.IAlertLogService;
import com.mhtech.platform.msrv.monitor.service.IAlertNotifyTmplService;
import com.mhtech.platform.msrv.monitor.service.IParamAlertService;
import com.mhtech.platform.msrv.monitor.service.IServerAdminService;
import com.mhtech.platform.msrv.monitor.service.IServerNotifyLogService;
import com.mhtech.platform.msrv.pojo.AlertLog;
import com.mhtech.platform.msrv.pojo.AlertLogVO;
import com.mhtech.platform.msrv.pojo.AlertNotifyTmpl;
import com.mhtech.platform.msrv.pojo.AlertRateVO;
import com.mhtech.platform.msrv.pojo.AlertRatioVO;
import com.mhtech.platform.msrv.pojo.MonitorStatus.ParamAlertStatus;
import com.mhtech.platform.msrv.pojo.NotifyLogBO;
import com.mhtech.platform.msrv.pojo.ParamAlertTotalVO;
import com.mhtech.platform.msrv.pojo.ParamAlertVO;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerAlertLogQty;
import com.mhtech.platform.msrv.pojo.ServerAlertRule;
import com.mhtech.platform.msrv.pojo.ServerParamAlert;
import com.mhtech.platform.msrv.pojo.SpDeviceAdmin;
import com.mhtech.platform.msrv.pojo.SpUserShift;
import com.mhtech.platform.msrv.pojo.TypeAlter;
import com.mhtech.platform.msrv.sharing.dao.mapper.AlertLogMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.SpDeviceAdminMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.SpShiftMapper;

@Service("alertLogService")
public class AlertLogServiceImpl implements IAlertLogService {

	@Autowired
	private AlertLogMapper alertLogMapper;

	@Autowired
	private IworkService workService;

	@Autowired
	private IServerNotifyLogService serverNotifyLogService;
	
	@Autowired
	private IAlertNotifyTmplService alertNotifyTmplService;
	
	@Autowired
	private IServerAdminService serverAdminService;
	
	@Autowired
	private IParamAlertService paramAlertService;
	
	@Autowired
	private SpDeviceAdminMapper spDeviceAdminMapper;
	
	@Autowired
	private SpShiftMapper shiftMapper;

	@Override
	public void addAlertLog(AlertLogVO alvo) {
		AlertLog al = new AlertLog();
		al.setId(workService.getNextId());
		al.setAlertTime(alvo.getAlertTime());
		al.setAlterValue(alvo.getAlterValue());
		al.setIsAlerting(alvo.getIsAlerting());
		al.setParamAlias(alvo.getParamAlias());
		al.setParamName(alvo.getParamName());
		al.setServerId(alvo.getServerId());
		al.setNotifyId(alvo.getNotifyId());
		alertLogMapper.insert(al);
	}

	@Override
	public int countAlertLogs(AlertLogVO alvo) {
		return alertLogMapper.count(alvo);
	}

	@Override
	public List<ServerAlertLogQty> countGrpAlertLogs(AlertLogVO alvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlertLog> listAlertLog(AlertLogVO alvo) {
		return alertLogMapper.selectEntities(alvo);
	}

	@Transactional
	@Override
	public void updateAlertStatusAndNotify(List<AlertLog> logs,
			ServerAlertRule serverAlertRule) {
		System.err.println("====插入=======updateAlertStatusAndNotify======="+logs.size());
		Long notifyId = workService.getNextId();
		logs.forEach(log -> {
			AlertLog al = new AlertLog();
			al.setId(log.getId());
			al.setIsAlerting(true);
			al.setNotifyId(notifyId);
			alertLogMapper.updateByPrimaryKeySelective(al);
		});
		Date now = new Date();
		//contact_method is 排班 then search user from user_shift
		
		//通知启用, 则判断监控暂停时间
		if("1".equals(serverAlertRule.getNotifyStatus())) {
			if(
					(!Objects.isNull(serverAlertRule.getStopListenStart()) && serverAlertRule.getStopListenStart().before(now) &&
							(!Objects.isNull(serverAlertRule.getStopListenEnd()) && serverAlertRule.getStopListenEnd().after(now)))
					) {
				return;
			}
		}
		//处于临时暂停监控期间
		if(
				(!Objects.isNull(serverAlertRule.getTempStopListenStart()) && serverAlertRule.getTempStopListenStart().before(now) &&
						(!Objects.isNull(serverAlertRule.getTempStopListenEnd()) && serverAlertRule.getTempStopListenEnd().after(now)))
				) {
			return;
		}
		String username, serverName, ip, contacts = "";
		NotifyLogBO nlbo = new NotifyLogBO();
		nlbo.setServerId(serverAlertRule.getServerId());
		ServerParamAlert paramAlert = paramAlertService.getParamAlert(serverAlertRule.getServerId(), serverAlertRule.getParamName(), ParamAlertStatus.Activator);
		if("1".equals(paramAlert.getServerType())) {
			SpDeviceAdmin deviceAdmin = spDeviceAdminMapper.selectByPrimaryKey(serverAlertRule.getServerId());
			username = deviceAdmin.getUsername();
			serverName = deviceAdmin.getServerName();
			ip = deviceAdmin.getIp();
		} else {
			ServerAdminInfo serverInfo = serverAdminService.getServerInfo(serverAlertRule.getServerId());
			username = serverInfo.getUsername();
			serverName = serverInfo.getServerName();
			ip = serverInfo.getIp();
		}
		//排班的方式获取人员
		if("range".equals(serverAlertRule.getContactsMethod())) {
			//从user_shift 关联当班用户
			List<SpUserShift> listUserOnDuty = shiftMapper.listUserOnDuty(ip);
			if(!CollectionUtils.isEmpty(listUserOnDuty)) {
				contacts = String.join(",", listUserOnDuty.toArray(new String[] {}));
			}
		}
		//自定义方式获取，或者没有排班人员
		if(!StringUtils.isEmpty(contacts)) {
			nlbo.setContacts(contacts);
		} else {
			nlbo.setContacts(serverAlertRule.getContacts());
		}
		nlbo.setParamName(serverAlertRule.getParamName());
		nlbo.setId(notifyId);
		nlbo.setAlertRuleId(serverAlertRule.getId());
//		serverNotifyLogService.addNotifyLog(serverAlertRule.getServerId(), logs
//				.stream().map(log -> log.getId()).collect(Collectors.toList()));
		AlertNotifyTmpl alertNotifyTmpl = alertNotifyTmplService.getServerUsingNotifyTmpl(serverAlertRule.getServerId());
		String content = alertNotifyTmpl.getTmplConent();
		if(!StringUtils.isEmpty(alertNotifyTmpl.getExt1())) {
			content = content.replace(alertNotifyTmpl.getExt1(), username);
		}
		if(!StringUtils.isEmpty(alertNotifyTmpl.getExt2())) {
			content = content.replace(alertNotifyTmpl.getExt2(), serverName);
		}
		if(!StringUtils.isEmpty(alertNotifyTmpl.getExt3())) {
			content = content.replace(alertNotifyTmpl.getExt3(), ip);
		}
		if(!StringUtils.isEmpty(alertNotifyTmpl.getExt4())) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			content = content.replace(alertNotifyTmpl.getExt4(), df.format(new Date()));
		}
		if(!StringUtils.isEmpty(alertNotifyTmpl.getExt5())) {
			String levelName="红色";
			Short level=serverAlertRule.getLevel();
			if (level<2) {
				levelName="绿色";
			}else if (level<4) {
				levelName="黄色";
			}
			content = content.replace(alertNotifyTmpl.getExt5(), String.valueOf(levelName));
		}
		if(!StringUtils.isEmpty(alertNotifyTmpl.getExt6())) {
			content = content.replace(alertNotifyTmpl.getExt6(), serverAlertRule.getParamName());
		}
		if(!StringUtils.isEmpty(alertNotifyTmpl.getExt7())) {
			content = content.replace(alertNotifyTmpl.getExt7(), String.valueOf(logs.get(0).getAlterValue().doubleValue()));
		}
		nlbo.setContent(content);
		System.err.println("==============新增=====================");
		serverNotifyLogService.addNotifyLog(nlbo);
	}

	@Override
	public ParamAlertTotalVO totalParamAlert(TypeAlter ta) {
		if (Objects.isNull(ta.getStartAlertTime())
				|| Objects.isNull(ta.getEndAlertTime())) {
			throw new RuntimeException("告警统计查询时间段不能为空");
		}
		List<ParamAlertVO> vos = alertLogMapper.totalParamAlert(ta);
		if (CollectionUtils.isEmpty(vos)) {
			return null;
		}
		ParamAlertTotalVO pat = new ParamAlertTotalVO();
		pat.setParamAlert(vos);
		vos.forEach(vo -> {
			pat.setTotal(pat.getTotal() + vo.getCount());
		});
		return pat;
	}

	@Override
	public AlertRatioVO totalRateParamAlert(TypeAlter ta) {
		if (Objects.isNull(ta.getStartAlertTime())
				|| Objects.isNull(ta.getEndAlertTime())) {
			throw new RuntimeException("告警统计查询时间段不能为空");
		}
		List<ParamAlertVO> vos = alertLogMapper.totalRateParamAlert(ta);
		if (CollectionUtils.isEmpty(vos)) {
			return null;
		}
		AlertRatioVO arvo = new AlertRatioVO();
		int $total = 0;
		for (ParamAlertVO vo : vos) {
			$total += vo.getCount();
		}
		final int total = $total;
		List<AlertRateVO> alertRates = vos.stream().map(paramAlert -> {
			AlertRateVO ar = new AlertRateVO();
			ar.setAlertType(paramAlert.getParamName());
			double rate = total == 0 ? 0 : paramAlert.getCount() * 100.00 / total;
			BigDecimal b = new BigDecimal(rate);
			rate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			ar.setAlertRate(rate);
			return ar;
		}).collect(Collectors.toList());
		arvo.setAlertRate(alertRates);
		return arvo;
	}

	@Override
	public void updateIsAlert(List<AlertLog> listAlertLog) {
		alertLogMapper.batchUpdate(listAlertLog);
		
	}
}