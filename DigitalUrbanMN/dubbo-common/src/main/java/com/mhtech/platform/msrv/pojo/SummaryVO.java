package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "汇总占比使用量", description = "汇总占比使用量")
public class SummaryVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5330305056239606605L;

	@ApiModelProperty("汇总类型")
	private String paramName;
	
	@ApiModelProperty("汇总类型编码")
	private String paramCode;
	
	@ApiModelProperty("总数")
	private double count;
	
	@ApiModelProperty("所占值")
	private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}
}
