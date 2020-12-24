package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

public class ParamType implements Serializable {

	private static final long serialVersionUID = 7794301470261302202L;

	private String paramType;
	private String paramDesc;
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getParamDesc() {
		return paramDesc;
	}
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}
}
