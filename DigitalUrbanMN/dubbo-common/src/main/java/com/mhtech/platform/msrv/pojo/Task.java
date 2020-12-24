package com.mhtech.platform.msrv.pojo;
import java.io.Serializable;
import java.util.Map;

public class Task implements Serializable{
	String taskId;//任务编码
	String taskName;//任务名称
	String taskCron;//执行规则
	String taskDesc;//说明
	String beanName;//执行类
	String methodName;//执行类方法
	String status;//状态
	String lastStatus;//最后执行状态
	String creatTime;//创建时间
	String updateTime;//更新时间
	String lastTime;//最后执行时间
	private Map parameters;//任务调用的方法传入的参数,统一使用String JSON
	String creatUserCode;
	String updateUserCode;
	public Task() {
		super();
	}
/**
 * 执行更新执行状态+执行时间 
 * @param taskId
 * @param lastStatus
 * @param lastTime
 */
	public Task(String taskId, String lastStatus, String lastTime) {
		super();
		this.taskId = taskId;
		this.lastStatus = lastStatus;
		this.lastTime = lastTime;
	}


	
	public Task(String taskId, String taskName, String taskCron, String taskDesc, String beanName, String methodName,
		String status, String lastStatus, String creatTime, String updateTime, String lastTime, Map parameters,
		String creatUserCode, String updateUserCode) {
	super();
	this.taskId = taskId;
	this.taskName = taskName;
	this.taskCron = taskCron;
	this.taskDesc = taskDesc;
	this.beanName = beanName;
	this.methodName = methodName;
	this.status = status;
	this.lastStatus = lastStatus;
	this.creatTime = creatTime;
	this.updateTime = updateTime;
	this.lastTime = lastTime;
	this.parameters = parameters;
	this.creatUserCode = creatUserCode;
	this.updateUserCode = updateUserCode;
}
	public String getCreatUserCode() {
		return creatUserCode;
	}
	public void setCreatUserCode(String creatUserCode) {
		this.creatUserCode = creatUserCode;
	}
	public String getUpdateUserCode() {
		return updateUserCode;
	}
	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}
	public Map getParameters() {
		return parameters;
	}
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskCron() {
		return taskCron;
	}
	public void setTaskCron(String taskCron) {
		this.taskCron = taskCron;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastStatus() {
		return lastStatus;
	}
	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskCron=" + taskCron + ", taskDesc=" + taskDesc
				+ ", beanName=" + beanName + ", methodName=" + methodName + ", status=" + status + ", lastStatus="
				+ lastStatus + ", creatTime=" + creatTime + ", updateTime=" + updateTime + ", lastTime=" + lastTime
				+ ", parameters=" + parameters + "]";
	}

	
}
