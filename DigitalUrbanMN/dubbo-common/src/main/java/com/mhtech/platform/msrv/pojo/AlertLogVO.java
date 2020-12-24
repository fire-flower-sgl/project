package com.mhtech.platform.msrv.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class AlertLogVO {
	
	private Long id;

	private Long serverId;

    private String paramName;

    private String paramAlias;

    private BigDecimal alterValue;

    private Date alertTime;
    
    private Date startAlertTime;
    
    private Date endAlertTime;
    
    private Boolean isAlerting;
    
    private Long notifyId;
    
	public Long getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(Long notifyId) {
		this.notifyId = notifyId;
	}

	public Boolean getIsAlerting() {
		return isAlerting;
	}

	public void setIsAlerting(Boolean isAlerting) {
		this.isAlerting = isAlerting;
	}

	public Date getStartAlertTime() {
		return startAlertTime;
	}

	public void setStartAlertTime(Date startAlertTime) {
		this.startAlertTime = startAlertTime;
	}

	public Date getEndAlertTime() {
		return endAlertTime;
	}

	public void setEndAlertTime(Date endAlertTime) {
		this.endAlertTime = endAlertTime;
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

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AlertLogVO [id=" + id + ",serverId=" + serverId + ", paramName=" + paramName + ", paramAlias=" + paramAlias
				+ ", alterValue=" + alterValue + ", alertTime=" + alertTime + ", startAlertTime=" + startAlertTime
				+ ", endAlertTime=" + endAlertTime + ", isAlerting=" + isAlerting + "]";
	}
	
	
}
