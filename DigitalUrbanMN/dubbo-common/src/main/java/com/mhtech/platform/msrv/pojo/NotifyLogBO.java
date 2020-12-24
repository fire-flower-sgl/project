package com.mhtech.platform.msrv.pojo;

public class NotifyLogBO {

	private Long id;
	
	private Long serverId;
	
	private String paramName;
	
	private String contacts;
	
	private String content;
	
	private Long alertRuleId;
	
	public Long getAlertRuleId() {
		return alertRuleId;
	}

	public void setAlertRuleId(Long alertRuleId) {
		this.alertRuleId = alertRuleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		this.paramName = paramName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
}
