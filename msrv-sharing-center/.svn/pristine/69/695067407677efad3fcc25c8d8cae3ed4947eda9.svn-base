package com.mhtech.platform.msrv.sharing.service;

import java.util.List;
import java.util.Map;


import com.mhtech.platform.log.pojo.GatewayAccessLogDTO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.model.GatewayAccessLog;

public interface IGatewayAccessLogService extends IBaseService {
	
	
	//删除几天前的数据
	int delete(int day);
	
	//依据条件查询
	Page selectLog(GatewayAccessLogDTO geAccessLog);
	
	/**
	 * 根据应用名称查询该应用当天的请求日志
	 * @param applicationName 服务名称集合
	 * @return	日志集合
	 */
	List<GatewayAccessLog> selectTodayLogByApplication(List<String> applicationName);
	/**
	 * 根据服务id查询日志
	 * @param serverId
	 * @return 日志集合
	 */
	List<GatewayAccessLog> selectLogByServerId(Long serverId);
	
	
    //查询数据
	List<GatewayAccessLog> allLog(Long max,Long min,int eachItem);
	//获取指定时间段内的，最大id ，最小id，总条数
	Map<String, Object> selectManMin(String topTime,String endTime);
	//删除时间段数据
  	int deleteDate(String topTime,String endTime);
  			
}
