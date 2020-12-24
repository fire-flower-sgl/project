package com.mhtech.platform.msrv.sharing.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ServerMonitorLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long serverId;

    private String paramName;

    private String paramAlias;
    
    private BigDecimal paramValue;

    private BigDecimal alterValue;
    
    private String memo;

    private Date createdTime;

    public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public BigDecimal getParamValue() {
		return paramValue;
	}

	public void setParamValue(BigDecimal paramValue) {
		this.paramValue = paramValue;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

	public ServerMonitorLog(Long id, Long serverId, String paramName, String paramAlias, BigDecimal alterValue,
			Date createdTime) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.paramAlias = paramAlias;
		this.alterValue = alterValue;
		this.createdTime = createdTime;
	}

	public ServerMonitorLog() {
		super();
	}

	@Override
	public String toString() {
		return "ServerMonitorLog [id=" + id + ", serverId=" + serverId + ", paramName=" + paramName + ", paramAlias="
				+ paramAlias + ", alterValue=" + alterValue + ", createdTime=" + createdTime + "]";
	}
    
}