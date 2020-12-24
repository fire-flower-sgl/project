package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.msrv.pojo.AlertNotifyLogCounterBO;
import com.mhtech.platform.msrv.pojo.AlertNotifyLogVO;
import com.mhtech.platform.msrv.pojo.AlertRateVO;
import com.mhtech.platform.msrv.pojo.DailyAlertsVO;
import com.mhtech.platform.msrv.pojo.NotifyLogVO;
import com.mhtech.platform.msrv.pojo.ServerNotifyLog;

@Mapper
public interface ServerNotifyLogMapper extends GenericMapper {
	
	/**
	 * 统计时间段内处理数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
//	List<DailyAlertsVO> totalDailyNotifyLog(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
	List<DailyAlertsVO> totalDailyNotifyLog(AlertNotifyLogCounterBO param);
	
	/**
	 * 分页记录
	 * @param nlvo
	 * @return
	 */
	List<ServerNotifyLog> listNotifyLog(NotifyLogVO nlvo);
	
	List<ServerNotifyLog> listAlertNotifyLog(NotifyLogVO nlvo);
	
	int insertNotifyLog(AlertNotifyLogVO snfl);
	
	/**
	 * 告警信息的详细分类，按错误编码分类
	 * @param notifyId
	 * @return
	 */
	List<AlertRateVO> detailAlertType(Long notifyId);

	/**
	 * 查询告警日志
	 * @param serverId
	 * @return 
	 */
	com.mhtech.platform.msrv.sharing.dao.model.ServerNotifyLog findByServerId(Long serverId);
	
	
	
	//告警信息图表计数
	List<DailyAlertsVO> listNotifyStatistics( @Param("topTime")String topTime,@Param("endTime")String endTime,
			@Param("id")Long id);

	/**
	 * TODO 根据传的状态编码，修改状态
	 * @param status
	 * @param id
	 */
	void changeStatus(@Param("status")String status,@Param("id")Long id);
	
}