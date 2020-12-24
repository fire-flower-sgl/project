package com.mhtech.platform.msrv.sharing.service;

import java.util.List;
import java.util.Map;

import com.mhtech.platform.log.pojo.UserActionLogDTO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.model.UserActionLog;

public interface UserActionLogService extends IBaseService{


	//删除几天前的数据
	int delete(int day);
    //查询sp_user_action_detail表要删除的id集合
    Long[] selectIds(String topTime,String endTime);
	
	//依据条件查询
	Page selectLog(UserActionLogDTO userActionLogDTO);
	

	//查询前一天的 sp_user_action_log  sp_user_action_detail日志
	List<UserActionLog> allLog( Long max , Long min, int eachItem);
	
	//获取指定时间段内的，最大id ，最小id，总条数
	Map<String, Object> selectManMin(String topTime,String endTime);
	//删除时间段数据
  	int deleteDate(String topTime,String endTime);
  	
}
