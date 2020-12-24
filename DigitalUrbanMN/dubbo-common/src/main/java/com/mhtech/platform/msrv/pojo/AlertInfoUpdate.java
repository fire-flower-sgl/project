package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@ApiModel(value = "告警规则修改", description = "告警规则修改")
public class AlertInfoUpdate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -688464451795127951L;

	@NotBlank(message = "服务ID不能为空")
	@Length(max = 64, message = "服务ID错误")
	@ApiModelProperty("服务ID")
	private Long serverId;
	
	@NotBlank(message = "监控属性不能为空")
	@Length(max = 64, message = "监控属性错误")
	@ApiModelProperty("监控属性")
	private String paramName;
	
	@ApiModelProperty("暂停告警监控结束时间")
	@NotNull(message = "暂停时间不合法")
	private Date refuseEndTime;

	@ApiModelProperty("暂停告警监控开始时间")
	@NotNull(message = "暂停时间不合法")
	private Date refuseStartTime;

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

	public Date getRefuseEndTime() {
		return refuseEndTime;
	}

	public void setRefuseEndTime(Date refuseEndTime) {
		this.refuseEndTime = refuseEndTime;
	}

	public Date getRefuseStartTime() {
		return refuseStartTime;
	}

	public void setRefuseStartTime(Date refuseStartTime) {
		this.refuseStartTime = refuseStartTime;
	}
}
