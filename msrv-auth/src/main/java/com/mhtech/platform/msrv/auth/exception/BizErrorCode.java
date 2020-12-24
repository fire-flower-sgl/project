package com.mhtech.platform.msrv.auth.exception;

public enum BizErrorCode {
	
	UNKNOWN(-10000, "系统异常"),
	//认证失败
	AUTHEN_FAILED(-100, "用户认证失败"),
	
	TOKEN_ILLEGAL(-200, "Token不合法"),
	TOKEN_EXPIRED(-201, "Token已过期"),
	
	NON_USER_ROLES(-300, "用户未分配角色"),
	
	NON_ROLE_POWERS(-301, "角色未分配权限"),
	
	//服务调度失败
	SVR_DISPATCH_FAILED(-500, "服务调度失败");
	
	private int code;
	
	private String message;
	
	private BizErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
