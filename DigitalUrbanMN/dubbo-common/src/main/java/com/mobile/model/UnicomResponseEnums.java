package com.mobile.model;
 
/**
 * 异常情况枚举
 * @author jlm
 *
 */
public enum UnicomResponseEnums {
	
	
	Remotelogin("1002","该账号已异地登录，如非本人操作，请确保账号是否泄密"),
	No_User("1000","未获取到用户登录信息，请重新进行登录！"),
	User_Not_Logged_In("1001","登录超时"),
	SUCCESS_OPTION("200","请求成功！"),
	NULL_ARGUMENT("4001","参数为空"),
	DATABASE_ERROR("4003","数据库执行异常"),
	CONNECTION_ERROR("4004","网络连接请求失败！"),
	METHOD_NOT_ALLOWED("4005","请求异常"),
	NOT_FOUND("4006","找不到请求路径！"),
	BAD_REQUEST("4007","参数类型错误"),
	NULL_DATE("4008","查询结果为空"),
	Duplication_Data("4009","手机号已存在"),
	Duplication1_Data("4010","手机号重复"),
	INVALID_PASSWORD("003","密码错误"),
	NOT_MATCH("-007","用户名和密码不匹配"),
	NOT_PERMISSIONS("-108","无权限请求对应api接口"),
	LOGIN_TIMEOUT("-101","登陆时间过长，请重新登陆"),
	NO_USER_EXIST("002","用户不存在"),
	NULL_MAPTYPE("4011","未设置默认地图种类，请联系管理员！"),
	EXIST_USERCODE("4012","该用户已添加轨迹抽查权限，请检查！"),
	ILLEGAL_FORMAT_ARGUMENT("4002","参数格式错误"),
	NULL_CHECK_NUM("4013","抽查次数达到上限或者没有抽查权限！"),
	NULL_USER_CHECK("4013","该用户抽查权限不存在，请检查！"),
	NULL_CHECK_Person("4015","抽查人数过多，请检查！"),
	SmsSend_ERROR("4016","短信发送失败！"),
	BAD_PARM("4017","数据不正确，求和应等于1"),
	No_Power("4014","无权限，请重新登录"),
	No_Btn_Power("4018","无按钮权限"),
	Task_NOT_EXIST("4019","任务不存在！"),
	Task_ERROR("4020","任务执行错误！"),
	task_EXIST("4021","任务存在！"),
	task_EXIST2("4023","任务已存在，不能重复创建任务！"),
	task_START0("4022","任务状态禁用，该任务不能被启动！")
	;

	
	/*SYSTEM_ERROR("-001","系统异常"),
	BAD_REQUEST("-002","错误的请求参数"),
	BOUND_STATEMENT_NOT_FOUNT("-006","找不到方法！"),
	REPEAT_REGISTER("001","重复注册"),
	NO_USER_EXIST("002","用户不存在"),
	INVALID_PASSWORD("003","密码错误"),
	NO_PERMISSION("004","非法请求！"),
	NOT_MATCH("-007","用户名和密码不匹配"),
	FAIL_GETDATA("-008","获取信息失败"),
	BAD_REQUEST_TYPE("-009","错误的请求类型"),
	INVALID_MOBILE("010","无效的手机号码"),
	INVALID_EMAIL("011","无效的邮箱"),
	INVALID_GENDER("012","无效的性别"),
	REPEAT_MOBILE("014","已存在此手机号"),
	REPEAT_EMAIL("015","已存在此邮箱地址"),
	NO_RECORD("016","没有查到相关记录"),
	LOGIN_SUCCESS("017","登陆成功"),
	LOGOUT_SUCCESS("018","已退出登录"),
	SENDEMAIL_SUCCESS("019","邮件已发送，请注意查收"),
	EDITPWD_SUCCESS("020","修改密码成功"),
	No_FileSELECT("021","未选择文件"),
	FILEUPLOAD_SUCCESS("022","上传成功"),
	NOLOGIN("023","未登陆"),
	
	ERROR_IDCODE("025","验证码不正确");*/
 
	private String status;
	private String message;
	private UnicomResponseEnums(String status, String message) {
 
		this.status = status;
		this.message = message;
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