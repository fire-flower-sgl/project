package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@ApiModel("服务详情")
public class ServerAdminInfo implements Serializable {

	private static final long serialVersionUID = -2541998133903620415L;

	@ApiModelProperty(value = "服务KEY", example = "10000")
	private String id;

	@ApiModelProperty(value = "服务编码", example = "YY001001")
	private String serverCode;
	
	@ApiModelProperty(value = "服务IP", example = "192.168.1.102")
    private String ip;

	@ApiModelProperty(value = "服务名称", example = "邮件服务器")
    private String serverName;

	@ApiModelProperty(value = "管理员", example = "Jack")
    private String username;
	
	@ApiModelProperty(value = "管理员中文名称", example = "Jack")
    private String user;

	@ApiModelProperty(value = "管理员邮箱", example = "1236598798@qq.com")
    private String email;

	@ApiModelProperty(value = "管理员电话", example = "1890006566")
    private String mobile;

	@ApiModelProperty(value = "上级服务", example = "30000")
    private String parentServer;
    
	@ApiModelProperty(value = "服务类型", example = "物理机")
    private String serverType; //物理机|应用
    
	@ApiModelProperty(value = "运行项目", example = "监控服务")
    private String applyProjects;
	
	@ApiModelProperty(value = "运行项目名称", example = "监控服务")
    private String applyProjectsName;
	
	private Object projects;
    
	@ApiModelProperty(value = "服务状态", example = "启用")
    private String status;
	
	private Date endTime = new Date();
	
	private Short monitorStatus = (short) 1;
    
	@ApiModelProperty(value = "服务创建时间", example = "2020-3-10")
    private Date createdTime;
    
	@ApiModelProperty(value = "服务子集", required = false)
    @JsonInclude(Include.NON_NULL) 
    private List<ServerAdminInfo> subServers;
    
	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Object getProjects() {
		return projects;
	}

	public void setProjects(Object projects) {
		this.projects = projects;
	}

	public String getApplyProjectsName() {
		return applyProjectsName;
	}

	public void setApplyProjectsName(String applyProjectsName) {
		this.applyProjectsName = applyProjectsName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Short getMonitorStatus() {
		return monitorStatus;
	}

	public void setMonitorStatus(Short monitorStatus) {
		this.monitorStatus = monitorStatus;
	}

	public String getApplyProjects() {
		return applyProjects;
	}

	public void setApplyProjects(String applyProjects) {
		this.applyProjects = applyProjects;
	}

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ServerAdminInfo> getSubServers() {
		return subServers;
	}

	public void setSubServers(List<ServerAdminInfo> subServers) {
		this.subServers = subServers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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

	public String getParentServer() {
		return parentServer;
	}

	public void setParentServer(String parentServer) {
		this.parentServer = parentServer;
	}
}
