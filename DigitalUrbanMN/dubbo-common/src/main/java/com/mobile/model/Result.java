package com.mobile.model;

/**
 * @program: 测试
 * @description:返回的JSON数据结构标准
 * @author: mtb
 * @create: 2018-10-17 09:01
 **/
public class Result <T>{
	private boolean success;
	private T data;
	private String status;
	private String message;
 
	public Result(){}
 
	public Result(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
 
	@Override
	public String toString() {
		return "ResponseBean{" +
				"success=" + success +
				", data=" + data +
				", status='" + status + '\'' +
				", errMsg='" + message + '\'' +
				'}';
	}
 
	public Result(boolean success, T data, String status, String message) {
		super();
		this.success = success;
		this.data = data;
		this.status = status;
		this.message = message;
	}
 
	public Result(boolean success, String status, String message) {
		this.success = success;
		this.status = status;
		this.message = message;
	}
	public Result(boolean success,UnicomResponseEnums enums){
		this.success=success;
		this.status=enums.getStatus();
		this.message=enums.getMessage();
	}
	public Result(boolean success,T data,UnicomResponseEnums enums){
		this.success=success;
		this.data=data;
		this.status=enums.getStatus();
		this.message=enums.getMessage();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
 
}