package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "资源使用量", description = "资源使用量")
public class TotalComponentUsage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6998641779976476194L;

	@ApiModelProperty("各组件使用量")
	private List<ComponentUsageVO> usage;

	public List<ComponentUsageVO> getUsage() {
		return usage;
	}

	public void setUsage(List<ComponentUsageVO> usage) {
		this.usage = usage;
	}
}
