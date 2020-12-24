package com.mhtech.platform.msrv.sharing.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class AlertLog {
    private Long id;

    private Long serverId;

    private String paramName;

    private String paramAlias;

    private BigDecimal alterValue;

    private Date alertTime;

    private Boolean isAlerting;

    private String notifyId;
    public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

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
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getParamAlias() {
        return paramAlias;
    }

    public void setParamAlias(String paramAlias) {
        this.paramAlias = paramAlias == null ? null : paramAlias.trim();
    }

    public BigDecimal getAlterValue() {
        return alterValue;
    }

    public void setAlterValue(BigDecimal alterValue) {
        this.alterValue = alterValue;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public Boolean getIsAlerting() {
        return isAlerting;
    }

    public void setIsAlerting(Boolean isAlerting) {
        this.isAlerting = isAlerting;
    }
}