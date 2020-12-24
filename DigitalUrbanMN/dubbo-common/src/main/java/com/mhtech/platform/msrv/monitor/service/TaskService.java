package com.mhtech.platform.msrv.monitor.service;

import java.util.List;
import java.util.Map;

import com.mhtech.platform.msrv.pojo.Task;
import com.mobile.model.Page;


/**
 * 参数字典管理
 * @author Guo
 *
 */
public interface TaskService {

	List<Map<String,Object>> queryList(Map<String,Object> map);
	Page queryPage(Map<String,Object> map);
	int updateTask(Task task);
	int insertTask(Task task);
	int deleteTask(Task task);
	int queryTaskCount(Task task);
	void addTask(Task task);
	void modifyTask(Task task);
	void cancelTask(String taskId);
	boolean existTask(String taskId);
	boolean ExecutedImmediately(Task task);
	
}
