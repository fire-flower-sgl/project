package com.mobile.model;

public class SpePower {

	private String id;
	private String userCode;
	private String speNum;
	private String speName;
	private String speKey;
	private String speVal;
	private String speType;
	private String speUse;
	private String speRemark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getSpeNum() {
		return speNum;
	}
	public void setSpeNum(String speNum) {
		this.speNum = speNum;
	}
	public String getSpeName() {
		return speName;
	}
	public void setSpeName(String speName) {
		this.speName = speName;
	}
	public String getSpeKey() {
		return speKey;
	}
	public void setSpeKey(String speKey) {
		this.speKey = speKey;
	}
	public String getSpeVal() {
		return speVal;
	}
	public void setSpeVal(String speVal) {
		this.speVal = speVal;
	}
	public String getSpeType() {
		return speType;
	}
	public void setSpeType(String speType) {
		this.speType = speType;
	}
	public String getSpeUse() {
		return speUse;
	}
	public void setSpeUse(String speUse) {
		this.speUse = speUse;
	}
	public String getSpeRemark() {
		return speRemark;
	}
	public void setSpeRemark(String speRemark) {
		this.speRemark = speRemark;
	}
	@Override
	public String toString() {
		return "SpePower [id=" + id + ", userCode=" + userCode + ", speNum=" + speNum + ", speName=" + speName
				+ ", speKey=" + speKey + ", speVal=" + speVal + ", speType=" + speType + ", speUse=" + speUse
				+ ", speRemark=" + speRemark + "]";
	}
	
	
	
}
