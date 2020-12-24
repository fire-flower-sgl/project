package com.mhtech.platform.msrv.sharing.response;

public enum RespCode {

	UNKNOWN(100, "系统异常"),
	SUCCESS(200,"请求成功！"),
	ILLEGAL_ARGUMENT(300,"请求参数错误"),
	NULL_ARGUMENT(301,"参请求数为空"),
	ILLEGAL_FORMAT_ARGUMENT(302,"请求参数类型错误"),
	DUPLICATION_DATA(303,"手机号已存在"),
	DATABASE_ERROR(304,"数据库执行异常"),
	BAD_REQUEST(305,"请求参数类型错误"),
	NULL_DATE(306,"查询结果为空"),
	
	
	BLACK_IP(400,"黑名单用户"),
	REQUEST_FAST(500,"请求频率过快"),
	KEY_FAILURE(601,"密文验证未通过"),
    NO_USER_RIGHTS(602,"无权限"),
    NO_TOKEN(603,"账号未登录");
	
	
	
	private int code;
	
	private String message;

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
