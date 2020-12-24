package com.mhtech.platform.log.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="查询预警规则结果集",description="查询预警规则结果集")
public class ServerAlertRuleDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long serverId;

	private String paramName;

	private Short alertLimit;

	private Long duration;

	private Date createdTime;

	private Date stopListenStart;

	private Date stopListenEnd;

	private Short level;

	private String contacts;
   
	@ApiModelProperty(value = "查询条数", example = "10", required = true)
	@Max(value = 500, message = "查询条数过大")
	@Min(value = 1, message = "查询条数错误")
	@NotNull(message = "查询条数错误")
	private Integer pageSize = 10;//每页显示条数

	@ApiModelProperty(value = "查询页码", example = "10", required = true)
	@Min(value = 1, message = "页码错误")
	@NotNull(message = "查询页码错误")
    private Integer pageNo = 1;//当前页
   	
    private Date topTime;//开始时间
    
    private Date endTime;//结束时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Short getAlertLimit() {
		return alertLimit;
	}

	public void setAlertLimit(Short alertLimit) {
		this.alertLimit = alertLimit;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getStopListenStart() {
		return stopListenStart;
	}

	public void setStopListenStart(Date stopListenStart) {
		this.stopListenStart = stopListenStart;
	}

	public Date getStopListenEnd() {
		return stopListenEnd;
	}

	public void setStopListenEnd(Date stopListenEnd) {
		this.stopListenEnd = stopListenEnd;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

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

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ServerAlertRuleDTO() {
		super();
	}
   	
   	
   	
   	
   	
}
