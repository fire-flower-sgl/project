package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@ApiModel("告警记录查询条件")
public class NotifyLogVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7696012236618684675L;

	@ApiModelProperty(value = "查询条数", example = "10", required = true)
	@Max(value = 500, message = "查询条数过大")
	@Min(value = 1, message = "查询条数错误")
	@NotNull(message = "查询条数错误")
	private Integer pageSize = 10;//每页显示条数

	@ApiModelProperty(value = "查询页码", example = "10", required = true)
	@Min(value = 1, message = "页码错误")
	@NotNull(message = "查询页码错误")
    private Integer pageNo = 1;//当前页
	
	@ApiModelProperty(value = "排序方式")
	@NotBlank(message = "排序方式错误")
	private String orderBy = "id desc";
	
	@ApiModelProperty(value = "告警级别", example = "3")
	private Short level;//告警级别
	
	@ApiModelProperty(value = "记录编码", hidden = true)
	private Long id;

	@ApiModelProperty(value = "服务KEY", example = "10000")
	@Min(value = 1, message = "服务器编码错误")
    private Long serverId;

	@ApiModelProperty(value = "管理员名称", example = "Jack")
    private String username;

	@ApiModelProperty(value = "**", hidden = true)
    private String alertLogs;
	
	@ApiModelProperty(value = "所属业务", example = "Jack")
	private String serverBiz;

	@ApiModelProperty(value = "是否发送", example = "true")
    private Boolean isSend;
	
	private Boolean notified;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startHappenTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endHappenTime;
	
	public String getServerBiz() {
		return serverBiz;
	}

	public void setServerBiz(String serverBiz) {
		this.serverBiz = serverBiz;
	}

	public Date getStartHappenTime() {
		return startHappenTime;
	}

	public void setStartHappenTime(Date startHappenTime) {
		this.startHappenTime = startHappenTime;
	}

	public Date getEndHappenTime() {
		return endHappenTime;
	}

	public void setEndHappenTime(Date endHappenTime) {
		this.endHappenTime = endHappenTime;
	}

	public Boolean getNotified() {
		return notified;
	}

	public void setNotified(Boolean notified) {
		this.notified = notified;
	}

	@ApiModelProperty(value = "**", hidden = true)
    private Short status;
    
	@ApiModelProperty(value = "发生时间*", example = "2020年3月09日16:17:45")
    private Date startTime;

	@ApiModelProperty(value = "截止生时间*", example = "2020年3月10日16:17:45")
    private Date endTime;
	
	@ApiModelProperty(value = "所在服务器", example = "192.168.1.101")
	private String ip;
	
	@ApiModelProperty(value = "所属业务", example = "流程管理")
	private String serverName;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAlertLogs() {
		return alertLogs;
	}

	public void setAlertLogs(String alertLogs) {
		this.alertLogs = alertLogs;
	}

	public Boolean getIsSend() {
		return isSend;
	}

	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
