package com.mhtech.platform.msrv.gateway.request;

public class UserParams {

	private String userCode;

	public String getUserCode() {
		return userCode;
	}

	private String powerNum;
	
	public String getPowerNum() {
		return powerNum;
	}

	public void setPowerNum(String powerNum) {
		this.powerNum = powerNum;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public UserParams(String userCode) {
		super();
		this.userCode = userCode;
	}

	public UserParams(String userCode, String powerNum) {
		super();
		this.userCode = userCode;
		this.powerNum = powerNum;
	}

	public UserParams() {
		super();
	}

	@Override
	public String toString() {
		return "UserParams [userCode=" + userCode + "]";
	}
	
}
