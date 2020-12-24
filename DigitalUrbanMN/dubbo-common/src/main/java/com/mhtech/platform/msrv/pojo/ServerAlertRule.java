package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ServerAlertRule implements Serializable , Comparable<ServerAlertRule>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6944703662129899326L;

	private Long id;

    private Long serverId;

    private String paramName;

    private Short alertLimit;

    private Long duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    
    private String contacts;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date stopListenStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date stopListenEnd;
    
    private Short level;
    
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

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
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

	public ServerAlertRule(Long id, Long serverId, String paramName, Short alertLimit, Long duration,
			Date createdTime,String contacts,Date stopListenStart,Date stopListenEnd,Short level) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.alertLimit = alertLimit;
		this.duration = duration;
		this.createdTime = createdTime;
		this.contacts = contacts;
		this.stopListenStart = stopListenStart;
		this.stopListenEnd = stopListenEnd;
		this.level = level;
		
	}
	
	public ServerAlertRule(Long id, Long serverId, String paramName,
			Short alertLimit, Long duration, Date createdTime, String contacts,
			Date stopListenStart, Date stopListenEnd, Short level,
			String notifyMethod, String contactsMethod, String notifyStatus,
			Date tempStopListenStart, Date tempStopListenEnd) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.alertLimit = alertLimit;
		this.duration = duration;
		this.createdTime = createdTime;
		this.contacts = contacts;
		this.stopListenStart = stopListenStart;
		this.stopListenEnd = stopListenEnd;
		this.level = level;
		this.notifyMethod = notifyMethod;
		this.contactsMethod = contactsMethod;
		this.notifyStatus = notifyStatus;
		this.tempStopListenStart = tempStopListenStart;
		this.tempStopListenEnd = tempStopListenEnd;
	}

	public ServerAlertRule(Long id, Long serverId, String paramName, Short alertLimit, Long duration,
			Date createdTime,String contacts,Date stopListenStart,Date stopListenEnd) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.alertLimit = alertLimit;
		this.duration = duration;
		this.createdTime = createdTime;
		this.contacts = contacts;
		this.stopListenStart = stopListenStart;
		this.stopListenEnd = stopListenEnd;
	}

	public ServerAlertRule() {
		super();
	}





	@Override
	public String toString() {
		return "ServerAlertRule [id=" + id + ", serverId=" + serverId + ", paramName=" + paramName + ", alertLimit="
				+ alertLimit + ", duration=" + duration + ", createdTime=" + createdTime + ", contacts=" + contacts
				+ ", stopListenStart=" + stopListenStart + ", stopListenEnd=" + stopListenEnd + ", level=" + level
				+ "]";
	}

	@Override
	public int compareTo(ServerAlertRule o) {
		// TODO Auto-generated method stub
		return alertLimit-o.getAlertLimit();
	}


    
    
}