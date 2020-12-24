package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "首页图表结果集", description = "首页图表结果集")
public class IndexChartVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6602554956625496587L;

	@ApiModelProperty("告警分类统计")
	private ParamAlertTotalVO paramAlertTotal;

	@ApiModelProperty("告警分类占比")
	private AlertRatioVO alertRatio;

	@ApiModelProperty("时间区内业务处理量趋势")
	private DailyAlertsTotalVO dailyAlertsTotal;
	
	@ApiModelProperty("资源使用量")
	private ComponentWrapperVO totalComponentUsage;
	
	@ApiModelProperty("汇总使用量")
	private TotalSummaryVO totalSummaryVO;

	public TotalSummaryVO getTotalSummaryVO() {
		return totalSummaryVO;
	}

	public void setTotalSummaryVO(TotalSummaryVO totalSummaryVO) {
		this.totalSummaryVO = totalSummaryVO;
	}

	public ComponentWrapperVO getTotalComponentUsage() {
		return totalComponentUsage;
	}

	public void setTotalComponentUsage(ComponentWrapperVO totalComponentUsage) {
		this.totalComponentUsage = totalComponentUsage;
	}

	public ParamAlertTotalVO getParamAlertTotal() {
		return paramAlertTotal;
	}

	public void setParamAlertTotal(ParamAlertTotalVO paramAlertTotal) {
		this.paramAlertTotal = paramAlertTotal;
	}

	public AlertRatioVO getAlertRatio() {
		return alertRatio;
	}

	public void setAlertRatio(AlertRatioVO alertRatio) {
		this.alertRatio = alertRatio;
	}

	public DailyAlertsTotalVO getDailyAlertsTotal() {
		return dailyAlertsTotal;
	}

	public void setDailyAlertsTotal(DailyAlertsTotalVO dailyAlertsTotal) {
		this.dailyAlertsTotal = dailyAlertsTotal;
	}

	@Override
	public String toString() {
		return "IndexChartVO [paramAlertTotal=" + paramAlertTotal + ", alertRatio=" + alertRatio + ", dailyAlertsTotal="
				+ dailyAlertsTotal + ", totalComponentUsage=" + totalComponentUsage + ", totalSummaryVO="
				+ totalSummaryVO + "]";
	}
	
	
	
	
}
