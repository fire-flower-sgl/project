package com.mobile.service;

import java.util.List;
import java.util.Map;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

import com.mobile.model.Page;



public interface TaskService {
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	Page queryPage(Map<String,Object> map);
	/**
	 * 根据条件查询所有任务
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryTaskAll(Map<String,Object> map);
	/**
	 * 新增任务
	 * @param map
	 * @return
	 */
	boolean addTask(Map<String,Object> map);
	/**
	 * 删除任务
	 * @param taskId
	 * @return
	 */
	boolean delTask(String taskId);
	/**
	 * 根据id查询任务
	 * @param taskId
	 * @return
	 */
	Map<String,Object> queryTaskById(String taskId);
	/**
	 * 修改任务
	 * @param map
	 * @return
	 */
	boolean modifyTask(Map<String,Object> map);
	/**
	 * map数据转换为jobDataMap
	 * @param map
	 * @return
	 */
	JobDataMap getJobDataMap(Map<String,Object> map);
	/**
	 * 获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
	 * @param jobKey
	 * @param description
	 * @param map
	 * @return
	 */
	JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map) ;
	/**
	 * 获取Trigger (Job的触发器,执行规则)
	 * @param map
	 * @return
	 */
	Trigger getTrigger(Map<String,Object> map) ;
	/**
	 * 获取JobKey,包含Name和Group
	 * @param map
	 * @return
	 */
	JobKey getJobKey(Map<String,Object> map);
	/**
	 * 初始化任务
	 */
	boolean initializeJob();
	/**
	 * 根据主键重新启动任务
	 * @param taskId
	 * @return
	 */
	boolean restartJob(String taskId);
	/**
	 * 停止任务
	 * @param map
	 * @return
	 */
	boolean stopJob(String taskId);
	/**
	 * 获取正在运行的任务
	 * @return
	 */
	Map<String,String> runningJob();
	/**
	 * 立即执行
	 * @param map
	 * @return
	 */
	boolean ExecutedImmediately(Map<String,Object> map);
	/**
	 * 新增任务校验 是否已存在 执行类+执行方法
	 * @param map
	 * @return
	 */
	boolean validationTask(Map<String,Object> map);
}
