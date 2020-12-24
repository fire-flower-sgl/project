package com.mhtech.platform.msrv.pojo;


@SuppressWarnings("serial")
public class PageableOperatorResBO extends PageBO {

	private String operatorName;
	
	private Short operatorType;
	
	private String teamCode;
	
	private Boolean isOpen;

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
}
