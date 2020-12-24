package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OperatorUploader implements Serializable {

	private String resPath;
	private String operatorName;
	private Boolean isOpen;
	private Short operatorType;
	private String teamCode;
	private String logo;
	private String memo;
	private String creator;
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getResPath() {
		return resPath;
	}
	public void setResPath(String resPath) {
		this.resPath = resPath;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	public Short getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(Short operatorType) {
		this.operatorType = operatorType;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
}
