package com.mhtech.platform.msrv.sharing.service;

import java.util.List;
import java.util.Map;


import com.mhtech.platform.msrv.sharing.dao.model.ServerMonitorLog;

public interface ServerMonitorLogService extends IBaseService{

	 /**
     * 查询最大最小id之间所有数据
     * @param max 最大id
     * @param min 最小id
     * @param eachItem 查询条数
     * @return
     */
	List<ServerMonitorLog> allLog( Long max ,Long min, int eachItem);
	
	/**
	 * 获取时间段内的，总条数，最大id,最小id
	 * @param topTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	Map<String, Object> selectManMin(String topTime,String endTime);
	//删除时间段数据
  	int deleteDate(String topTime,String endTime);
  	
}
