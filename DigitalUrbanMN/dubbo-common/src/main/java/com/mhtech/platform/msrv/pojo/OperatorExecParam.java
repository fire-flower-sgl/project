package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@SuppressWarnings("serial")
public class OperatorExecParam implements Serializable {

	@NotNull(message = "参数名不能为空")
	@Length(max = 32, message = "参数名长度错误")
	private String paramName;
	@NotNull(message = "参数值不能为空")
	@Length(max = 128, message = "参数值长度错误")
	private String paramValue;
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
}
