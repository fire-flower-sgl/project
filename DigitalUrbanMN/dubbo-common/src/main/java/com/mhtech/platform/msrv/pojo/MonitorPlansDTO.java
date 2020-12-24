package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

public class MonitorPlansDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  int pageSize;
	
	private  int pageNo;
	
	private Long id;

    private String taskName;

    private String cron;

    private String mainClass;

    private String method;

    private Integer status;

    private String params;

    private String memo;

    private Date createdTime;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public MonitorPlansDTO() {
		super();
	}

	public MonitorPlansDTO(String taskName, String cron, String mainClass, String method, Integer status, String params,
			String memo) {
		super();
		this.taskName = taskName;
		this.cron = cron;
		this.mainClass = mainClass;
		this.method = method;
		this.status = status;
		this.params = params;
		this.memo = memo;
	}

	public MonitorPlansDTO(Long id, String taskName, String cron, String mainClass, String method, Integer status,
			String params, String memo) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.cron = cron;
		this.mainClass = mainClass;
		this.method = method;
		this.status = status;
		this.params = params;
		this.memo = memo;
	}

	
	
	public MonitorPlansDTO(Long id, String cron, String mainClass, String method, String params) {
		super();
		this.id = id;
		this.cron = cron;
		this.mainClass = mainClass;
		this.method = method;
		this.params = params;
	}

	public MonitorPlansDTO(String mainClass, String method, String params) {
		super();
		this.mainClass = mainClass;
		this.method = method;
		this.params = params;
	}

	@Override
	public String toString() {
		return "MonitorPlansDTO [pageSize=" + pageSize + ", pageNo=" + pageNo + ", id=" + id + ", taskName=" + taskName
				+ ", cron=" + cron + ", mainClass=" + mainClass + ", method=" + method + ", status=" + status
				+ ", params=" + params + ", memo=" + memo + ", createdTime=" + createdTime + "]";
	}
    
    

}
