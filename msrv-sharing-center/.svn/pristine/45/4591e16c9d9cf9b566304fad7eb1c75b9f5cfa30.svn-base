package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.msrv.pojo.ComponentUsageVO;
import com.mhtech.platform.msrv.pojo.SummaryVO;
import com.mhtech.platform.msrv.sharing.dao.model.ServerMonitorLog;

@Mapper
public interface ServerMonitorLogMapper extends GenericMapper {

	/**
	 * 统计组件资源占用情况
	 * 
	 * @param startTime
	 * @param endTime
	 * @param tops
	 * @return
	 */
	List<ComponentUsageVO> totalComponentUsage(
			@Param("startTime") Date startTime, @Param("endTime") Date endTime,
			@Param("tops") int tops);

	/**
	 * 硬件指标汇总占比
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<SummaryVO> totalHardwareSummary(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,
			@Param("paramNames") List<String> paramNames);
	/**
	 * 业务指标汇总占比
	 * @param startTime
	 * @param endTime
	 * @param alertCodes
	 * @return
	 */
	List<SummaryVO> totalBizSummary(@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,
			@Param("alertCodes") List<String> alertCodes);
	
	
	 /**
     * 查询最大最小id之间所有数据
     * @param max 最大id
     * @param min 最小id
     * @param eachItem 查询条数
     * @return
     */
	List<ServerMonitorLog> allLog(@Param("max") Long max ,@Param("min") Long min,@Param("eachItem") int eachItem);
	
	/**
	 * 获取时间段内的，总条数，最大id,最小id
	 * @param topTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	Map<String, Object> selectManMin(@Param("topTime")String topTime,@Param("endTime")String endTime);
	//删除时间段数据
	int deleteDate(@Param("topTime")String topTime,@Param("endTime")String endTime);	
	
}