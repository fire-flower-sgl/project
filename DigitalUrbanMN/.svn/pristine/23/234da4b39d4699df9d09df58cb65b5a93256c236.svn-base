package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@ApiModel("分析流新建参数")
@SuppressWarnings("serial")
public class AnalyzeFlowBO implements Serializable {

	@ApiModelProperty("分析流名称")
	@NotNull(message = "分析流名称不能为空")
	@NotBlank(message = "分析流名称不能为空")
	@Length(min = 1, max = 32, message = "分析流名称错误")
	private String flowName;
	
	@ApiModelProperty(value = "所属团队编码", hidden = true)
//	@NotBlank(message = "所属团队不能为空")
//	@NotNull(message = "所属团队不能为空")
//	@Length(min = 1, max = 32, message = "团队编码错误")
	private String teamCode;
	
	@ApiModelProperty("所属项目编码")
	@NotNull(message = "项目编码不能为空")
	@NotBlank(message = "项目编码不能为空")
	@Length(min = 1, max = 32, message = "项目编码超长")
	private String prjCode;
	
	@ApiModelProperty(hidden = true)
	private String creator;
	
	@ApiModelProperty("分析流描述")
	@NotNull(message = "分析流描述不能为空")
	@NotBlank(message = "分析流描述不能为空")
	@Length(min = 1, max = 255, message = "分析流描述内容超长")
	private String memo;
	
	@ApiModelProperty("流程节点集合")
	@NotNull(message = "节点不能为空")
	@Size(min = 1, max = 100, message = "节点数量不符合规范")
	private List<FlowNodeBO> flowNodes;

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getPrjCode() {
		return prjCode;
	}

	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
	}

	public List<FlowNodeBO> getFlowNodes() {
		return flowNodes;
	}

	public void setFlowNodes(List<FlowNodeBO> flowNodes) {
		this.flowNodes = flowNodes;
	}
}
