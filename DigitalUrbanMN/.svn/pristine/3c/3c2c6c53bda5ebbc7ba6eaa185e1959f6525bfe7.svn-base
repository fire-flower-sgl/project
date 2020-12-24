package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class OperatorExecParams implements Serializable {

	// in, out or setting 
	@NotNull(message = "参数类型不能为空")
	private Short paramType;
	
	@NotNull(message = "运行参数不能为空")
	@Size(min = 1, max = 20, message = "参数个数超出限制")
	private List<OperatorExecParam> params;

	public Short getParamType() {
		return paramType;
	}

	public void setParamType(Short paramType) {
		this.paramType = paramType;
	}

	public List<OperatorExecParam> getParams() {
		return params;
	}

	public void setParams(List<OperatorExecParam> params) {
		this.params = params;
	}
}
