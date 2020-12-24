package com.mhtech.platform.msrv.sharing.service;

import java.util.List;
import java.util.Map;

import com.mhtech.platform.msrv.sharing.dao.model.AlertLog;


public interface AlertLogService {
	

	//删除几天前的数据
    int delete(int day);
    
    //查询数据
   	List<AlertLog> allLog(Long max,Long min,int eachItem);
   	
   	//获取指定时间段内的，最大id ，最小id，总条数
   	Map<String, Object> selectManMin(String topTime,String endTime);
   	
    //删除时间段数据
  	int deleteDate(String topTime,String endTime);
  	
  	
}
