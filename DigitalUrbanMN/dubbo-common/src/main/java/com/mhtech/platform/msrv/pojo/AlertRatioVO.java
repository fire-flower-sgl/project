package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 告警分类占比
 * @author Guo
 *
 */
@ApiModel(value = "告警分类占比", description = "告警分类占比")
public class AlertRatioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5432448291941459600L;

	@ApiModelProperty("告警分类占比列表")
	private List<AlertRateVO> alertRate;

	public List<AlertRateVO> getAlertRate() {
		return alertRate;
	}

	public void setAlertRate(List<AlertRateVO> alertRate) {
		this.alertRate = alertRate;
	}
}
