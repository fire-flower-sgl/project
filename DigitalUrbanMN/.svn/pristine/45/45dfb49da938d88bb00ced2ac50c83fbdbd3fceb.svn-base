package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel("历史事件数量")
public class DailyAlertsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6300743395813122057L;

	@ApiModelProperty("发生时间")
	@JsonFormat(pattern="MM-dd")
	private Date date;
	
	@ApiModelProperty("业务数量")
	private int count;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "DailyAlertsVO [date=" + date + ", count=" + count + "]";
	}
	
	
	
}
