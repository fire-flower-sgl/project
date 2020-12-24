package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.log.pojo.UserActionLogDTO;
import com.mhtech.platform.msrv.sharing.dao.model.UserActionLog;

@Mapper
public interface UserActionLogMapper  extends GenericMapper {
	

	//删除几天前的数据
    int delete(int day);
    //查询sp_user_action_detail表要删除的id集合
    Long[] selectIds(@Param("topTime")String topTime,@Param("endTime")String endTime);
    
	
	//依据条件查询+分页
	List<UserActionLog> selectLog(UserActionLogDTO userActionLogDTO);
	//总条数
	int selectLogSum(UserActionLogDTO userActionLogDTO);
	
	//查询前一天的 sp_user_action_log  sp_user_action_detail日志
	List<UserActionLog> allLog(@Param("max") Long max ,@Param("min") Long min,@Param("eachItem") int eachItem);
	
	//获取指定时间段内的，最大id ，最小id，总条数
	Map<String, Object> selectManMin(@Param("topTime")String topTime,@Param("endTime")String endTime);
	//删除时间段数据
	int deleteDate(@Param("topTime")String topTime,@Param("endTime")String endTime);

}