package com.mhtech.platform.msrv.sharing.request;

import java.math.BigDecimal;

public class AlertLogVO {

	private Long id;

	private Long serverId;

	private String paramName;

	private String paramAlias;

	private BigDecimal alterValue;

	private String alertTime;

	private Boolean isAlerting;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamAlias() {
		return paramAlias;
	}

	public void setParamAlias(String paramAlias) {
		this.paramAlias = paramAlias;
	}

	public BigDecimal getAlterValue() {
		return alterValue;
	}

	public void setAlterValue(BigDecimal alterValue) {
		this.alterValue = alterValue;
	}

	public String getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}

	public Boolean getIsAlerting() {
		return isAlerting;
	}

	public void setIsAlerting(Boolean isAlerting) {
		this.isAlerting = isAlerting;
	}

	public AlertLogVO(Long id, Long serverId, String paramName, String paramAlias, BigDecimal alterValue,
			String alertTime, Boolean isAlerting) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.paramAlias = paramAlias;
		this.alterValue = alterValue;
		this.alertTime = alertTime;
		this.isAlerting = isAlerting;
	}

	public AlertLogVO() {
		super();
	}
	
	
	
}
