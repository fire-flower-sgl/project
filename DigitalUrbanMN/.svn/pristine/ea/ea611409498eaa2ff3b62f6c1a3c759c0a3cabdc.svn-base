package com.mhtech.platform.msrv.monitor.service;

import java.util.Date;
import java.util.List;

import com.mhtech.platform.msrv.pojo.ComponentUsageVO;
import com.mhtech.platform.msrv.pojo.MonitorSyncResult;
import com.mhtech.platform.msrv.pojo.ServerMonitorInfo;
import com.mhtech.platform.msrv.pojo.SummaryVO;

/**
 * 服务监控日志管理
 * @author Guo
 */
public interface IServerMonitorLog {

	/**
	 * 添加监控日志
	 * @param smi
	 */
	void addMonitorLog(ServerMonitorInfo smi);
	
	/**
	 * 批量新增监控信息，同一服务的各种指标集合
	 * @param infos
	 * @return 处理结果，成功数、异常信息
	 */
	MonitorSyncResult addServerMonitorLogList(List<ServerMonitorInfo> infos);
	
	/**
	 * 统计各服务的资源使用
	 * @param startTime
	 * @param endTime
	 * @param tops
	 * @return
	 */
	List<ComponentUsageVO> totalComponentUsage(Date startTime, Date endTime, int tops);
	
	/**
	 * 汇总占比使用量
	 * @param startTime
	 * @param endTime
	 * @param paramCodes
	 * @param alertCodes
	 * @return
	 */
	List<SummaryVO> totalSummary(Date startTime, Date endTime, List<String> paramCodes, List<String> alertCodes);
}
