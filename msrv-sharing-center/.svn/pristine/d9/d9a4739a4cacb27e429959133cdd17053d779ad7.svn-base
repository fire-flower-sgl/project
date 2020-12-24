package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.log.pojo.MsrvLogDTO;
import com.mhtech.platform.msrv.sharing.dao.model.MsrvLog;
import com.mhtech.platform.msrv.sharing.request.LogDTO;

@Mapper
public interface MsrvLogMapper  extends GenericMapper {

	List<MsrvLog> allLog(@Param("max") Long max ,@Param("min") Long min,@Param("eachItem") int eachItem);
	//时间段总条数
	int sjSize(@Param("topTime")String topTime,@Param("endTime")String endTime);
	//删除几天前的数据
    int delete(int day);
	//依据条件查询+分页
	List<MsrvLog> selectLog(MsrvLogDTO msrvLogDTO);
	//总条数
	int selectLogSum(MsrvLogDTO msrvLogDTO);
	//获取指定时间段内的，最大log_id 与最小log_id
	Map<String, Object> selectManMin(LogDTO dto);
	
	//删除时间段数据
	int deleteDate(@Param("topTime")String topTime,@Param("endTime")String endTime);
}