package com.mhtech.platform.msrv.sharing.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RespObject<T> {

	@JsonProperty("status")
	private int code;
	
	private boolean success;

	private String message;
	
	@JsonInclude(Include.NON_NULL) 
	private T data;
	
	public RespObject() {}
	
	
	public RespObject(int code, boolean success, String message) {
		super();
		this.code = code;
		this.success = success;
		this.message = message;
	}


	public RespObject(int code, boolean success, String message, T data) {
		super();
		this.code = code;
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public RespObject(int code, String message, T data) {
		super();
		this.code = code;
		this.data = data;
		this.message = message;
	}
	
	public RespObject(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
