package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@ApiModel(value = "监控服务的所属指标（同一服务可配置相同含义的监控指标，但需区分paramName，并以alertCode及alertType标识为同一含义），作用于同一属性的多重监控指标。", description = "999999")
public class ServerAlertParamVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1622697880981671342L;

	@ApiModelProperty(value = "监测字段", required = true, example = "123987")
	@NotBlank(message = "监测字段不能为空")
	@Length(max = 64, message = "监测字段错误")
	private String paramName;

	@ApiModelProperty("监测字段别名")
	@NotBlank(message = "监测字段别名不能为空")
	@Length(max = 64, message = "监测字段别名长度过长")
	private String paramAlias;
	
	@ApiModelProperty("预警值")
	@Min(value = 0, message = "预警值错误")
	private BigDecimal alertValue;
	
	@ApiModelProperty(value = "预警编码", example = "xncs")
	@Length(max = 64, message = "预警编码长度过长")
	private String alertCode;
	
	@ApiModelProperty(value = "预警类型", example = "性能参数")
	@Length(max = 64, message = "预警编码长度过长")
	private String alertType;

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
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

	public String getAlertCode() {
		return alertCode;
	}

	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}
}
