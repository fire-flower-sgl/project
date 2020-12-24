package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AlertLog implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6174238538375512933L;

	private Long id;

    private Long serverId;

    private String paramName;

    private String paramAlias;

    private BigDecimal alterValue;

    private Date alertTime;

    private Boolean isAlerting;
    
    private Long notifyId;

    public Long getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(Long notifyId) {
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

	public AlertLog() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AlertLog(Long id, String paramName, String paramAlias) {
		super();
		this.id = id;
		this.paramName = paramName;
		this.paramAlias = paramAlias;
	}

	public AlertLog(String paramName, String paramAlias) {
		super();
		this.paramName = paramName;
		this.paramAlias = paramAlias;

	}

	@Override
	public String toString() {
		return "AlertLog [id=" + id + ", serverId=" + serverId + ", paramName=" + paramName + ", paramAlias="
				+ paramAlias + ", alterValue=" + alterValue + ", alertTime=" + alertTime + ", isAlerting=" + isAlerting
				+ "]";
	}
}