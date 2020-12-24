package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@ApiModel(value = "监控指标参数", description = "监控指标参数")
public class ServerMonitorInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6269799089957583779L;

	@ApiModelProperty(value = "服务键值", example = "10000（由监控平台注册分配）", required = true)
	@Length(max = 64, message = "所属服务KEY错误")
	@NotBlank(message = "所属服务KEY错误")
	private String serverId;
	
	@Length(max = 32, message = "服务IP错误")
	private String ip;

	@ApiModelProperty(value = "指标名称", example = "CPU（由监控平台配置的监控指标参数）", required = true)
	@Length(max = 64, message = "指标名称错误")
	@NotBlank(message = "指标名称错误")
	private String paramName;

	@ApiModelProperty(value = "指标监听值", example = "89（客户端实际发生值）", required = true)
	@NotNull(message = "指标预警值错误")
	private BigDecimal paramValue;
	
	@ApiModelProperty(value = "监控发生时间，为空则以服务器当前时间为准", example = "2020-3-11 15:54:20")
	private Date monitorTime = new Date(System.currentTimeMillis());
	
	@ApiModelProperty(value = "备注信息", example = "FTP服务组")
	@Length(max = 255, message = "备注信息长度错误")
	private String memo;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setMonitorTime(Date monitorTime) {
		this.monitorTime = monitorTime;
	}

	public Date getMonitorTime() {
		return monitorTime;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public BigDecimal getParamValue() {
		return paramValue;
	}

	public void setParamValue(BigDecimal paramValue) {
		this.paramValue = paramValue;
	}
}
