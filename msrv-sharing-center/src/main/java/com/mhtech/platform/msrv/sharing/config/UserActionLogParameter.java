package com.mhtech.platform.msrv.sharing.config;

import java.util.Date;

public class UserActionLogParameter {

	private int pageSize;// 分页-每页条数
	private int pageNo;// 分页-当前页

	private Long id;

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	private String userCode;

	private String actionModule;

	private String actionNum;

	private Date actionStartTime;
	private Date actionEndTime;

}
