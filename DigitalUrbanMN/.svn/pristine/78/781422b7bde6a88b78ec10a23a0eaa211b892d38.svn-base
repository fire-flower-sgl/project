package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class DetailAlertVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6492232221152157793L;

	@ApiModelProperty("业务处理量列表")
	private List<DailyAlertsVO> dailyAlerts;
	
	@ApiModelProperty("告警分类占比列表")
	private List<AlertRateVO> alertRate;

	public List<DailyAlertsVO> getDailyAlerts() {
		return dailyAlerts;
	}

	public void setDailyAlerts(List<DailyAlertsVO> dailyAlerts) {
		this.dailyAlerts = dailyAlerts;
	}

	public List<AlertRateVO> getAlertRate() {
		return alertRate;
	}

	public void setAlertRate(List<AlertRateVO> alertRate) {
		this.alertRate = alertRate;
	}

	@Override
	public String toString() {
		return "DetailAlertVO [dailyAlerts=" + dailyAlerts + ", alertRate=" + alertRate + "]";
	}
	
	
}
