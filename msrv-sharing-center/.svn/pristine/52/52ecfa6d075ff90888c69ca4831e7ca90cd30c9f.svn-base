package com.mhtech.platform.msrv.sharing.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class ParamAlert {
    private Long id;

    private Long serverId;

    private String paramName;

    private String paramAlias;

    private BigDecimal alterValue;
    
    private String alertCode;
    
    private String alertType;

    private Short status;

    private Date createdTime;
    
    private String serverType;

    public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getAlertCode() {
		return alertCode;
	}

	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

	public ParamAlert(Long id, Long serverId, String paramName, String paramAlias, BigDecimal alterValue,
			String alertCode, String alertType, Short status, Date createdTime) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.paramAlias = paramAlias;
		this.alterValue = alterValue;
		this.alertCode = alertCode;
		this.alertType = alertType;
		this.status = status;
		this.createdTime = createdTime;
	}

	public ParamAlert() {
		super();
	}
}