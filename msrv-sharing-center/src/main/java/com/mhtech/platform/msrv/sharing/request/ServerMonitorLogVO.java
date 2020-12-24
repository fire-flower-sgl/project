package com.mhtech.platform.msrv.sharing.request;

import java.math.BigDecimal;
import java.util.Date;

public class ServerMonitorLogVO {
	private Long id;

    private Long serverId;

    private String paramName;

    private String paramAlias;
    
    private BigDecimal paramValue;

    private BigDecimal alterValue;
    
    private String memo;

    private String createdTime;

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

	public BigDecimal getParamValue() {
		return paramValue;
	}

	public void setParamValue(BigDecimal paramValue) {
		this.paramValue = paramValue;
	}

	public BigDecimal getAlterValue() {
		return alterValue;
	}

	public void setAlterValue(BigDecimal alterValue) {
		this.alterValue = alterValue;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public ServerMonitorLogVO() {
		super();
	}

	public ServerMonitorLogVO(Long id, Long serverId, String paramName, String paramAlias, BigDecimal paramValue,
			BigDecimal alterValue, String memo, String createdTime) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.paramAlias = paramAlias;
		this.paramValue = paramValue;
		this.alterValue = alterValue;
		this.memo = memo;
		this.createdTime = createdTime;
	}

	public ServerMonitorLogVO(Long id, Long serverId, String paramName, String paramAlias, BigDecimal alterValue,
			String memo, String createdTime) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.paramAlias = paramAlias;
		this.alterValue = alterValue;
		this.memo = memo;
		this.createdTime = createdTime;
	}
    
    

}
