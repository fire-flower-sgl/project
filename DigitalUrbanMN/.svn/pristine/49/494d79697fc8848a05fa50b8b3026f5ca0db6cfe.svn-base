package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 历史告警数
 * @author Guo
 *
 */
@ApiModel(value = "时间区内业务处理量趋势", description = "时间区内业务处理量趋势")
public class DailyAlertsTotalVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7306669147667519536L;

	@ApiModelProperty("业务处理量列表")
	private List<DailyAlertsVO> dailyAlerts;

	public List<DailyAlertsVO> getDailyAlerts() {
		return dailyAlerts;
	}

	public void setDailyAlerts(List<DailyAlertsVO> dailyAlerts) {
		this.dailyAlerts = dailyAlerts;
	}

	@Override
	public String toString() {
		return "DailyAlertsTotalVO [dailyAlerts=" + dailyAlerts + "]";
	}
	
	
}
