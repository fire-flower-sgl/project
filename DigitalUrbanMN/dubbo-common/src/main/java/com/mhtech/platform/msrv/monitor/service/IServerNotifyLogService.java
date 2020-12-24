package com.mhtech.platform.msrv.monitor.service;

import java.util.List;

import com.mhtech.platform.msrv.pojo.AlertNotifyLogCounterBO;
import com.mhtech.platform.msrv.pojo.AlertNotifyLogVO;
import com.mhtech.platform.msrv.pojo.DailyAlertsTotalVO;
import com.mhtech.platform.msrv.pojo.DailyAlertsVO;
import com.mhtech.platform.msrv.pojo.DetailAlertVO;
import com.mhtech.platform.msrv.pojo.DictVO;
import com.mhtech.platform.msrv.pojo.NotifyLogBO;
import com.mhtech.platform.msrv.pojo.NotifyLogVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ServerNotifyLog;

public interface IServerNotifyLogService {

	/**
	 * 新增告警日志
	 * @param snl
	 */
	void addNotifyLog(com.mhtech.platform.msrv.pojo.ServerNotifyLog snl);
	
	/**
	 * 根据预警记录，生成告警通知
	 * @param serverId
	 * @param logIds
	 */
	void addNotifyLog(long serverId, List<Long> logIds);
	
	/**
	 * 告警通知
	 * @param nlbo
	 */
	void addNotifyLog(NotifyLogBO nlbo);
	
	/**
	 * 查询未发送的服务告警日志
	 * @param nlvo
	 * @return
	 */
	Page<com.mhtech.platform.msrv.pojo.ServerNotifyLog> listServerNotifyLog(NotifyLogVO nlvo);
	
	Page<com.mhtech.platform.msrv.pojo.ServerNotifyLog> listServerAlertNotifyLog(NotifyLogVO nlvo);
	
	/**
	 * 统计时间段内处理数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	DailyAlertsTotalVO totalDailyNotifyLog(AlertNotifyLogCounterBO param);
	
	DetailAlertVO detailNotifyLog(AlertNotifyLogCounterBO param);
	
	/**
	 * 新增告警日志
	 * @param snfl
	 */
	int addNotifyLog(AlertNotifyLogVO snfl);
	
	/**
	 * 更新告警通知
	 * @param vo
	 * @return
	 */
	int updateNotifyLog(com.mhtech.platform.msrv.pojo.ServerNotifyLog vo);
	
	com.mhtech.platform.msrv.pojo.ServerNotifyLog getNotifyLogById(Long id);
	
	/**
	 * 告警查询字典信息
	 * @param types
	 * @return
	 */
	List<DictVO> listDictByTypes(List<String> types);
	

	//告警信息的详细分类，按错误编码分类-新
	List<DailyAlertsVO> listNotifyStatistics(String topTime,String endTime,Long id);

	/**
	 * TODO
	 * @param status
	 * @param id
	 * @param tempStoptimeStart
	 * @param tempStoptimeEnd
	 */
	void changeStatus(String status, Long id);

	/**  根据条件获取日志列表
	 * TODO
	 * @param nlvo
	 * @return
	 */
	List<ServerNotifyLog> getServerNotifyLogList(NotifyLogVO nlvo);
	
	
}
