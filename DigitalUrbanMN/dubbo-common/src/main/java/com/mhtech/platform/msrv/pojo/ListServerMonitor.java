package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

@ApiModel(value = "批量监控指标参数", description = "批量监控指标参数")
public class ListServerMonitor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4128613287389097725L;

	@ApiModelProperty(value = "监控信息", required = true)
	@Size(min = 1, max = 100, message = "监控信息参数不合法")
	private List<MonitorParam> params;

	public List<MonitorParam> getParams() {
		return params;
	}

	public void setParams(List<MonitorParam> params) {
		this.params = params;
	}
}
