package com.mhtech.platform.msrv.sharing.service;

import java.util.List;
import java.util.Map;


import com.mhtech.platform.log.pojo.MsrvLogDTO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.model.MsrvLog;
import com.mhtech.platform.msrv.sharing.request.LogDTO;

public interface MsrvLogService extends IBaseService{

	//查询前一天日志集合sys_msrv_log
	List<MsrvLog> allLog(Long maxId ,Long minId,int eachItem);
	//时间段总条数
	int sjSizeString (String topTime,String endTime);
	//删除几天前的数据
	int delete(int day);
	//依据条件查询
	Page selectLog(MsrvLogDTO msrvLogDTO);
	//总条数
    int selectLogSum(MsrvLogDTO msrvLogDTO);
	//获取指定时间段内的，最大log_id 与最小log_id
    Map<String, Object> selectManMin(LogDTO dto);
    //删除时间段数据
  	int deleteDate(String topTime,String endTime);
  	
}
