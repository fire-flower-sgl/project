package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class ServerPageInfo implements Serializable {

	private static final long serialVersionUID = 8438358495631221814L;

	@Max(value = 500, message = "查询条数过大")
	@Min(value = 1, message = "查询条数错误")
	@NotNull(message = "查询条数错误")
	private Integer pageSize = 10;//每页显示条数
	
	@Min(value = 1, message = "页码错误")
	@NotNull(message = "查询页码错误")
    private Integer pageNo = 1;//当前页
	
	@NotBlank(message = "排序方式错误")
	private String orderBy = "id desc";
	
	@Range(min = 0, message = "服务KEY值错误")
	private Long id;
	
	@Length(max = 15, message = "服务ip错误")
    private String ip;

	@Length(max = 64, message = "服务KEY值错误")
    private String serverName;

	@Length(max = 64, message = "管理员名称错误")
    private String username;
	
    private String user;

	@Length(max = 64, message = "管理员邮箱错误")
    private String email;

	@Length(max = 64, message = "管理员电话错误")
    private String mobile;

	@Range(min = 0, message = "上级服务编码错误")
    private Long parentServer;
	
	//@Min(value = 1, message = "服务状态错误")
	private Short status;
	
	@Min(value = 1, message = "服务类型错误")
	private Short serverType;
	
	@Length(max = 64, message = "所属服务错误")
	private String applyProjects;
	
	private Short monitorStatus; //是否监控
	
	public Short getMonitorStatus() {
		return monitorStatus;
	}

	public void setMonitorStatus(Short monitorStatus) {
		this.monitorStatus = monitorStatus;
	}

	public Short getServerType() {
		return serverType;
	}

	public void setServerType(Short serverType) {
		this.serverType = serverType;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getApplyProjects() {
		return applyProjects;
	}

	public void setApplyProjects(String applyProjects) {
		this.applyProjects = applyProjects;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getParentServer() {
		return parentServer;
	}

	public void setParentServer(Long parentServer) {
		this.parentServer = parentServer;
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
}