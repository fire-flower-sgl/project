package com.mhtech.platform.msrv.sharing.dao.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ServerNotifyLog {
    private Long id;

    private Long serverId;

    private String username;

    private String alertLogs;
    
    private String paramName;
    
    private String userCode;

    private Boolean isSend;

    private Short status;

    private Date createdTime;

    private String content;
    
    private Long alertId;;
    

	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAlertLogs() {
        return alertLogs;
    }

    public void setAlertLogs(String alertLogs) {
        this.alertLogs = alertLogs == null ? null : alertLogs.trim();
    }

    public Boolean getIsSend() {
        return isSend;
    }

    public void setIsSend(Boolean isSend) {
        this.isSend = isSend;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	@Override
	public String toString() {
		return "ServerNotifyLog [id=" + id + ", serverId=" + serverId + ", username=" + username + ", alertLogs="
				+ alertLogs + ", paramName=" + paramName + ", userCode=" + userCode + ", isSend=" + isSend + ", status="
				+ status + ", createdTime=" + createdTime + ", content=" + content + "]";
	}
    
    
}