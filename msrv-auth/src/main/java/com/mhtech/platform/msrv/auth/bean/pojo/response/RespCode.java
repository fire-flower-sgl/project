package com.mhtech.platform.msrv.auth.bean.pojo.response;

public enum RespCode {

	UNKNOWN(-10000, "系统异常"),
	SUCCESS(200,"请求成功！"),
	TIMEOUT(500, "请求超时"),
	ILLEGAL_ARGUMENT(600,"参数为空");
	
	private int code;
	
	private String message;
	
	public static RespCode valueOf(int code) {
		RespCode[] vls = values();
		for (RespCode respCode : vls) {
			if(respCode.getCode() == code) {
				return respCode;
			}
		}
		return UNKNOWN;
	}
	
	private RespCode(int code, String message) {
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
