package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="业务图表响应类")
public class ServerDiagramsVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6887164971000508526L;


	@ApiModelProperty(value="流程图集合")
	private List<ServerDiagramVO> diagramList;
	

	@ApiModelProperty(value="流程图告警等级集合")
	private List<ServerDiagramLevelVO> diagramLevelList;


	public List<ServerDiagramVO> getDiagramList() {
		return diagramList;
	}


	public void setDiagramList(List<ServerDiagramVO> diagramList) {
		this.diagramList = diagramList;
	}


	public List<ServerDiagramLevelVO> getDiagramLevelList() {
		return diagramLevelList;
	}


	public void setDiagramLevelList(List<ServerDiagramLevelVO> diagramLevelList) {
		this.diagramLevelList = diagramLevelList;
	}
	
	
    
}