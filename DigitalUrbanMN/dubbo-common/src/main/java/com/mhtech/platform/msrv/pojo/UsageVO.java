package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "资源使用量", description = "资源使用量")
public class UsageVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4056426573129858967L;

	@ApiModelProperty("组件名称")
	private String compntName;
	
	@ApiModelProperty("使用量")
	private double count;

	public String getCompntName() {
		return compntName;
	}

	public void setCompntName(String compntName) {
		this.compntName = compntName;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}
}
