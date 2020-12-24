package com.mhtech.platform.msrv.gateway.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "业务流程图新增请求实体")
public class ServerDiagrams {
	
	
	@ApiModelProperty(value="流程图新增实体集合",required=true)
	@Size(min = 1,message = "流程图数据为空")
	@Valid
	private List<ServerDiagramAdd> serverDiagramList;

	public List<ServerDiagramAdd> getServerDiagramList() {
		return serverDiagramList;
	}

	public void setServerDiagramList(List<ServerDiagramAdd> serverDiagramList) {
		this.serverDiagramList = serverDiagramList;
	}
	
	
}