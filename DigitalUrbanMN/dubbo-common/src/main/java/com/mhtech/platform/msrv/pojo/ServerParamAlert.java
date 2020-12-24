package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ServerParamAlert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String serverId;
	
	private String paramName;
	
	private String paramAlias;
	
	private BigDecimal alertValue;
	
	private String status;
	
	private String serverType;

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
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

	public BigDecimal getAlertValue() {
		return alertValue;
	}

	public void setAlertValue(BigDecimal alertValue) {
		this.alertValue = alertValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
