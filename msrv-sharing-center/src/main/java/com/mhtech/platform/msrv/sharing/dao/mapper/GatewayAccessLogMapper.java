package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.log.pojo.GatewayAccessLogDTO;
import com.mhtech.platform.msrv.sharing.dao.model.GatewayAccessLog;

@Mapper
public interface GatewayAccessLogMapper extends GenericMapper {
	

	//删除几天前的数据
    int delete(int day);
	//依据条件查询+分页
	List<GatewayAccessLog> selectLog(GatewayAccessLogDTO geAccessLog);
	//总条数
	int selectLogSum(GatewayAccessLogDTO geAccessLog);
	//根据应用名称查询该应用当天的请求日志
	List<GatewayAccessLog> selectTodayLogByApplication(List<String> applicationName);
	//根据服务id查询日志
	List<GatewayAccessLog> selectLogByServerId(@Param("params") Map<String,Object> params);
	
	
    
    //查询数据
	List<GatewayAccessLog> allLog(@Param("max") Long max ,@Param("min") Long min,@Param("eachItem") int eachItem);
	
	//获取指定时间段内的，最大id ，最小id，总条数
	Map<String, Object> selectManMin(@Param("topTime")String topTime,@Param("endTime")String endTime);
	//删除时间段数据
	int deleteDate(@Param("topTime")String topTime,@Param("endTime")String endTime);
}