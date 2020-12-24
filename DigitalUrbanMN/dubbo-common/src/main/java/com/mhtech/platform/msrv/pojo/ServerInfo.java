package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@ApiModel(value = "监控服务信息")
public class ServerInfo implements Serializable {

	private static final long serialVersionUID = -2541998133903620415L;

	@ApiModelProperty(hidden = true)
	private Long id;
	
	@ApiModelProperty(value = "服务所在IP", example = "192.168.1.102")
	@Length(max = 15, message = "服务ip错误")
	@NotNull(message = "服务ip错误")
	@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String ip;

	@ApiModelProperty(value = "服务编码", required = true, example = "FTP服务器")
	/*@NotBlank(message = "服务为名称不能为空")*/
    private String serverCode;
	
	@ApiModelProperty(value = "服务名称", required = true, example = "FTP服务器")
	@NotBlank(message = "服务为名称不能为空")
    private String serverName;

	@ApiModelProperty(value = "管理员名称", required = true, example = "Jack")
	@NotBlank(message = "管理员名称不能为空")
	@Length(max = 64, message = "管理员名称错误")
    private String username;

	@ApiModelProperty(value = "管理员联系邮箱", required = true, example = "1216578@qq.com")
//	@NotBlank(message = "管理员邮箱不能为空")
	@Length(max = 64, message = "管理员邮箱错误")
	@Email(message = "管理员邮箱错误")
    private String email;

	@ApiModelProperty(value = "管理员电话", required = true, example = "18900000536")
	/*@NotBlank(message = "管理员电话不能为空")*/
	@Length(max = 64, message = "管理员电话错误")
    private String mobile;

	@ApiModelProperty(value = "上级服务KEY，不存在时为零", required = true, example = "10000")
	/*@Min(value = 0, message = "所属上级服务错误")*/
	/*@NotNull(message = "所属上级服务错误")*/
    private String parentServer;
	
	@ApiModelProperty(value = "服务类型 //100[硬件服务], 200[软件服务]", required = true, allowableValues = "100[硬件服务], 200[软件服务]", example = "100")
	@Min(value = 0, message = "服务类型错误")
	/*@NotNull(message = "服务类型错误")*/
	private Integer serverType; //物理机|应用
	
	/*@Length(max = 64, message = "应用项目错误")*/
	private String applyProjects;
	
	private Object projects;
	
	@ApiModelProperty(value = "服务监控指标")
	@Size(max = 50, message = "监控指标错误")
	private List<ServerAlertParamVO> params;
  
	private String createdTime;
	 
	private String status;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getApplyProjects() {
		return applyProjects;
	}

	public void setApplyProjects(String applyProjects) {
		this.applyProjects = applyProjects;
	}

	public List<ServerAlertParamVO> getParams() {
		return params;
	}

	public void setParams(List<ServerAlertParamVO> params) {
		this.params = params;
	}

	public Integer getServerType() {
		return serverType;
	}

	public void setServerType(Integer serverType) {
		this.serverType = serverType;
	}

	
	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Object getProjects() {
		return projects;
	}

	public void setProjects(Object projects) {
		this.projects = projects;
	}
	
	
}
