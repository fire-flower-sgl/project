package com.mhtech.platform.msrv.auth.bean.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mhtech.platform.msrv.auth.utils.JsonUtils;

public class RespObject<T> {

	private int code;
	
	private String message;
	
	@JsonInclude(Include.NON_NULL) 
	private T data;
	
	public RespObject() {}
	
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
	
	public String serialize() {
		return JsonUtils.object2DefaultJson(this);
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
}
