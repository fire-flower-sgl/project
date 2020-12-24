package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="监控指标结果集",description="监控指标结果集")
public class ParamAlertInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4617905638741694515L;

	@ApiModelProperty("服务主键")
	@Length(min=0, message = "字段无法为负数")
	@NotBlank(message = "所属服务KEY错误")
	private Long serverId;
	
	@ApiModelProperty("监测字段")
	@Length(max = 64, message = "指标名称错误")
	@NotBlank(message = "指标名称错误")
	private String paramName;
	@ApiModelProperty("监测字段别名")
	@Length(max = 64, message = "指标别名错误")
	@NotBlank(message = "指标别名错误")
	private String paramAlias;
	@ApiModelProperty("预警值")
	@Min(0)    
	@NotNull(message = "指标预警值错误")
	private BigDecimal alertValue;
	
	
	public ParamAlertInfo() {
		super();
	}
	@ApiModelProperty("预警编码")
	private String alertCode;
	@ApiModelProperty("预警类型")
	private String alertType;
	@ApiModelProperty("状态")
	private Short status;
	@ApiModelProperty("创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;
	
	
	public String getAlertCode() {
		return alertCode;
	}

	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getParamAlias() {
		return paramAlias;
	}

	public void setParamAlias(String paramAlias) {
		this.paramAlias = paramAlias;
	}

	public BigDecimal getAlertValue() {
		return alertValue;
	}

	public void setAlertValue(BigDecimal alertValue) {
		this.alertValue = alertValue;
	}

	public ParamAlertInfo(@Length(min = 0, message = "字段无法为负数") @NotBlank(message = "所属服务KEY错误") Long serverId,
			@Length(max = 64, message = "指标名称错误") @NotBlank(message = "指标名称错误") String paramName,
			@Length(max = 64, message = "指标别名错误") @NotBlank(message = "指标别名错误") String paramAlias,
			@Min(0) @NotNull(message = "指标预警值错误") BigDecimal alertValue, String alertCode, String alertType,
			Short status, Date createdTime) {
		super();
		this.serverId = serverId;
		this.paramName = paramName;
		this.paramAlias = paramAlias;
		this.alertValue = alertValue;
		this.alertCode = alertCode;
		this.alertType = alertType;
		this.status = status;
		this.createdTime = createdTime;
	}
}
