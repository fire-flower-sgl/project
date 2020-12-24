package com.mhtech.platform.log.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserActionLogVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

    private String userCode;

    private String actionModule;

    private String actionNum;

    private Date actionStartTime;
    private Date actionEndTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserActionLogVO(Long id, String userCode, String actionModule, String actionNum, Date actionStartTime,
			Date actionEndTime) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.actionModule = actionModule;
		this.actionNum = actionNum;
		this.actionStartTime = actionStartTime;
		this.actionEndTime = actionEndTime;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getActionModule() {
		return actionModule;
	}
	public void setActionModule(String actionModule) {
		this.actionModule = actionModule;
	}
	public String getActionNum() {
		return actionNum;
	}
	public void setActionNum(String actionNum) {
		this.actionNum = actionNum;
	}
	public Date getActionStartTime() {
		return actionStartTime;
	}
	public void setActionStartTime(Date actionStartTime) {
		this.actionStartTime = actionStartTime;
	}
	public Date getActionEndTime() {
		return actionEndTime;
	}
	public void setActionEndTime(Date actionEndTime) {
		this.actionEndTime = actionEndTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public UserActionLogVO() {
		super();
	}
    
}
