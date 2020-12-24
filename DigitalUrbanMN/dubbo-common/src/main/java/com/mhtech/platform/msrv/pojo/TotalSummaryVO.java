package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "汇总占比使用量", description = "汇总占比使用量")
public class TotalSummaryVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288068802710324909L;

	private List<SummaryVO> summaries;

	public List<SummaryVO> getSummaries() {
		return summaries;
	}

	public void setSummaries(List<SummaryVO> summaries) {
		this.summaries = summaries;
	}
}
