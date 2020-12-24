package com.mhtech.platform.msrv.pojo;


public class AlertRuleVO {

	private Long serverId;
	
	private String paramName;
	
	private Integer startPort; 
	
	private Integer endPort; 
	
	public Integer getStartPort() {
		return startPort;
	}

	public void setStartPort(Integer startPort) {
		this.startPort = startPort;
	}

	public Integer getEndPort() {
		return endPort;
	}

	public void setEndPort(Integer endPort) {
		this.endPort = endPort;
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
}
