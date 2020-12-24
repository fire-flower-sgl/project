package com.mhtech.platform.msrv.exception;

import java.util.Map;

@SuppressWarnings("serial")
public abstract class ServiceException extends RuntimeException {

	private int code;
	
	private String errorMsg;
	
	protected Map<String, Object> errorData;
	
	protected ServiceException(int code, String errorMsg) {
		super();
		this.code = code;
		this.errorMsg = errorMsg;
	}

	public int getCode() {
		return code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	
	@Override
	public String getMessage() {
		return "{\"code\":"+ code +",\"errorMsg\":"+errorMsg+"}";
	}
}
