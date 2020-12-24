package com.mhtech.platform.msrv.sharing.dao.model;

import java.util.Date;

public class ServerAlertRule {
    private Long id;

    private Long serverId;

    private String paramName;

    private Short alertLimit;

    private Long duration;

    private Date createdTime;
    
    private Date stopListenStart;
    
    private Date stopListenEnd;

    private Short level;
    
    private String contacts;
    
    private String notifyMethod;
    private String contactsMethod;
    private String notifyStatus;
    private Date tempStopListenStart;
    private Date tempStopListenEnd;
    
    public String getNotifyMethod() {
		return notifyMethod;
	}

	public void setNotifyMethod(String notifyMethod) {
		this.notifyMethod = notifyMethod;
	}

	public String getContactsMethod() {
		return contactsMethod;
	}

	public void setContactsMethod(String contactsMethod) {
		this.contactsMethod = contactsMethod;
	}

	public String getNotifyStatus() {
		return notifyStatus;
	}

	public void setNotifyStatus(String notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

	public Date getTempStopListenStart() {
		return tempStopListenStart;
	}

	public void setTempStopListenStart(Date tempStopListenStart) {
		this.tempStopListenStart = tempStopListenStart;
	}

	public Date getTempStopListenEnd() {
		return tempStopListenEnd;
	}

	public void setTempStopListenEnd(Date tempStopListenEnd) {
		this.tempStopListenEnd = tempStopListenEnd;
	}

	public Date getStopListenStart() {
		return stopListenStart;
	}

	public void setStopListenStart(Date stopListenStart) {
		this.stopListenStart = stopListenStart;
	}

	public Date getStopListenEnd() {
		return stopListenEnd;
	}

	public void setStopListenEnd(Date stopListenEnd) {
		this.stopListenEnd = stopListenEnd;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
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

    public Short getAlertLimit() {
        return alertLimit;
    }

    public void setAlertLimit(Short alertLimit) {
        this.alertLimit = alertLimit;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

	public ServerAlertRule(Long id, Long serverId, String paramName, Short alertLimit, Long duration, Date createdTime,
			Short level, String contacts, Date stopListenStart, Date stopListenEnd) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.alertLimit = alertLimit;
		this.duration = duration;
		this.createdTime = createdTime;
		this.level = level;
		this.contacts = contacts;
		this.stopListenStart = stopListenStart;
		this.stopListenEnd = stopListenEnd;
	}

	@Override
	public String toString() {
		return "ServerAlertRule [id=" + id + ", serverId=" + serverId + ", paramName=" + paramName + ", alertLimit="
				+ alertLimit + ", duration=" + duration + ", createdTime=" + createdTime + ", level=" + level
				+ ", contacts=" + contacts + ", stopListenStart=" + stopListenStart + ", stopListenEnd=" + stopListenEnd
				+ "]";
	}

	public ServerAlertRule() {
		super();
	}
}