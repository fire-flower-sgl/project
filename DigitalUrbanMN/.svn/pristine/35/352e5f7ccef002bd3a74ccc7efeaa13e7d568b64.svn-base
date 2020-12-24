package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "告警分类统计", description = "告警分类统计")
public class ParamAlertTotalVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3545267245417019086L;

	@ApiModelProperty("告警集合")
	private List<ParamAlertVO> paramAlert;
	
	@ApiModelProperty("告警总数")
	private int total;

	public List<ParamAlertVO> getParamAlert() {
		return paramAlert;
	}

	public void setParamAlert(List<ParamAlertVO> paramAlert) {
		this.paramAlert = paramAlert;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
