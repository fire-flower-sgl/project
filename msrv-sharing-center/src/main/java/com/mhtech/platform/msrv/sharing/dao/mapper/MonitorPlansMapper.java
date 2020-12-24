package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.msrv.pojo.MonitorPlansDTO;
import com.mhtech.platform.msrv.sharing.dao.model.MonitorPlans;

@Mapper
public interface MonitorPlansMapper extends GenericMapper{
	
	/**
	 * 任务名称是否存在
	 * @param mp
	 * @return
	 */
	int isExist(MonitorPlansDTO mp);
	
	/**
	 * 查询几天前的日志数据
	 * @param day 天数
	 * @return
	 */
	List<MonitorPlans> dayLog(int day);
	/**
	 * 删除几天前的数据
	 * @param day 天数
	 * @return
	 */
    int delete(int day);
    
    //查询数据
   	List<MonitorPlans> allLog(@Param("max") Long max ,@Param("min") Long min,@Param("eachItem") int eachItem);
   	
   	//获取指定时间段内的，最大id ，最小id，总条数
   	Map<String, Object> selectManMin(@Param("topTime")String topTime,@Param("endTime")String endTime);
     	
	//删除时间段数据
	int deleteDate(@Param("topTime")String topTime,@Param("endTime")String endTime);
}