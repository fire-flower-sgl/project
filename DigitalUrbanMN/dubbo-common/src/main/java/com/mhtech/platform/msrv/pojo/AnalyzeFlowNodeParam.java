package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@ApiModel
public class AnalyzeFlowNodeParam implements Serializable {

	@NotNull(message = "分析流ID不能为空")
	@Min(value = 0, message = "分析流ID错误")
	@ApiModelProperty("分析流ID")
	private Long flowId;

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
}
