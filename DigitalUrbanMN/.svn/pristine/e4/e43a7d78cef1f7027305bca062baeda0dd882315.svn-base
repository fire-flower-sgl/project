package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

@ApiModel("算子列表查询")
@SuppressWarnings("serial")
public class ListableOperatorParam extends PageBO implements Serializable {

	@ApiModelProperty(hidden = true)
	private String userCode;
	
	@ApiModelProperty(value = "算子名称(关键词)")
	@Length(max = 32, message = "算子名称过长")
	private String operatorName;

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
