package com.mhtech.platform.msrv.monitor.service;

import java.util.List;

import com.mhtech.platform.msrv.pojo.AlertLog;
import com.mhtech.platform.msrv.pojo.AlertLogVO;
import com.mhtech.platform.msrv.pojo.AlertRatioVO;
import com.mhtech.platform.msrv.pojo.ParamAlertTotalVO;
import com.mhtech.platform.msrv.pojo.ServerAlertLogQty;
import com.mhtech.platform.msrv.pojo.ServerAlertRule;
import com.mhtech.platform.msrv.pojo.TypeAlter;

public interface IAlertLogService {

	void addAlertLog(AlertLogVO alvo);
	
	int countAlertLogs(AlertLogVO alvo);
	
	/**
	 * 分组统计服务的预警数量
	 * @param alvo
	 * @return
	 */
	List<ServerAlertLogQty> countGrpAlertLogs(AlertLogVO alvo);
	
	/**
	 * 根据条件查询全部  不分页
	 * @param alvo
	 * @return
	 */
	List<AlertLog> listAlertLog(AlertLogVO alvo);
	
	/**
	 * 更新记录的预警状态并发出告警日志
	 * @param logs 预警日志
	 * @param serverAlertRule 预警规则
	 */
	void updateAlertStatusAndNotify(List<AlertLog> logs, ServerAlertRule serverAlertRule);
	
	/**
	 * 告警类型统计
	 * @param ta
	 * @return
	 */
	ParamAlertTotalVO totalParamAlert(TypeAlter ta);
	
	/**
	 * 告警类型占比统计
	 * @param ta
	 * @return
	 */
	AlertRatioVO totalRateParamAlert(TypeAlter ta);
	/**
	 * 更新isAlert
	 * @param listAlertLog
	 */
	void updateIsAlert(List<AlertLog> listAlertLog);
}
