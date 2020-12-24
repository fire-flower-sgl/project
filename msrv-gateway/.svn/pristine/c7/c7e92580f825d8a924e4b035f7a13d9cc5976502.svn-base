package com.mhtech.platform.msrv.gateway.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@ApiModel
public class RespObject<T> {

	@ApiModelProperty(value = "状态码", example = "200")
	@JsonProperty("status")
	@JSONField(name = "status")
	private int code;
	
	@ApiModelProperty(hidden = true)
	private boolean success=true;

	@ApiModelProperty(value = "返回状态描述", example = "请求成功")
	private String message;
	
	@ApiModelProperty("结果集")
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
