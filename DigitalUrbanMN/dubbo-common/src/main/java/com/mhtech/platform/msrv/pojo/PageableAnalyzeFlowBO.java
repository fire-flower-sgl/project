package com.mhtech.platform.msrv.pojo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("项目分析流查询")
@SuppressWarnings("serial")
public class PageableAnalyzeFlowBO extends PageBO {

	@ApiModelProperty("项目编码")
	@NotNull(message = "项目编码不能为空")
	@NotBlank(message = "项目编码不能为空")
	@Length(max = 32, message = "项目编码错误")
	private String prjCode;
	
	private String teamCode;
	
	private List<Long> analyzeIds;

	public List<Long> getAnalyzeIds() {
		return analyzeIds;
	}

	private String flowName;
	
	public void setAnalyzeIds(List<Long> analyzeIds) {
		this.analyzeIds = analyzeIds;
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

	

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
	}
}
