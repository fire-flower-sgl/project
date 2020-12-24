package com.mhtech.common.result.entity;
 
/**
 * 异常情况枚举
 * @author jlm
 *
 */
public enum UnicomResponseEnums {
 
	SUCCESS_OPTION("200","请求成功！"),
	ADD_SUCCESS("200","新增成功！"),
	UPDATE_SUCCESS("200","修改成功！"),
	SAVE_SUCCESS("200","保存成功！"),
	DEL_SUCCESS("200","删除成功！"),
	EXPORT_SUCCESS("200","导入成功！"),
	DOWNLOAD_SUCCESS("200","下载成功！"),
	ACTION_SUCCESS("200","执行成功！"),
	NULL_ARGUMENT("4001","参数为空"),
	DATABASE_ERROR("4003","数据库执行异常"),
	CONNECTION_ERROR("4004","网络连接请求失败！"),
	METHOD_NOT_ALLOWED("4005","请求异常"),
	NOT_FOUND("4006","找不到请求路径！"),
	BAD_REQUEST("4007","参数错误"),
	NULL_DATE("4008","查询结果为空"),
	ILLEGAL_FORMAT_ARGUMENT("4002","参数格式错误"),
	BAD_NUM("4009","输入的不是数字，请检查！"),
	NILL_CAL("4010","没有需要计算的数据，请检查！"),
	IN_CAL("4014","有数据正在处理中，请稍后！"),
	FAIL_CAL("4011","调用计算程序出错，请联系管理员！"),
	BAD_TIME("4012","时间格式错误！正确时间范围为00-23，并且要小于当前小时时间，例如当前时间为10点，则只能输入00-10（例如：02）"),
	BAD_DIS("4013","错误的距离格式！正确距离范围为（0-50）千米，例如：0.5"),
	LONG_DIS("4013","错误的距中心点距离格式！正确距离范围为（0-100）千米，例如：10"),
	BAD_POINT("4015","错误的坐标格式！正确坐标格式为：经度,纬度，例如：104.91127042593978,26.2575"),
	ON_CAL("4016","该dateId对应数据正在处理中，请稍后!"),
	LIMIT_NUM("4017","接口调用次数到达上限!"),
	NULL_KEY("4018","用户key不能为空，请检查!"),
	NOT_VALID_AK("4019","该秘钥过期，请检查!"),
	INVALID_IP("4020","您的ip不在白名单内，请检查!"),
	BAD_AK("4021","解密信息失败，请检查您的私钥是否正确!"),
	BAD_KEY("4022","用户key错误，请检查!"),
	IS_VALID_AK("4023","该秘钥在有效期内，请检查!"),
	NULL_LEVEL("4024","未设置聚合等级或者聚合坐标点数，请检查!"),
	BAD_POINT_TYPE("4026","错误的坐标种类，正确格式为（百度:bd;高德:gd;GPS:gps），请检查!"),
	BAD_LEVEL("4025","设置的聚合等级聚合后超过10000个点或者聚合坐标点数超过10000，请检查!"),
	NULL_DATA("4027","没有生成统计数据，请先进行生成！"),
	NULL_DATA_IN("4028","该范围所属区域内没有源数据！"),
	UPLOAD_FILE_ERR("4029","文件上传错误，请联系管理员！"),
	DATA_ON_CAL("4030","统计数据正在计算中，请稍后再试！"),
	BAD_FILE_SHP("4031","上传文件格式不正确，上传文件必须包含shp文件、shx文件和dbf文件，请检查！"),
	NOT_IN_CHINA("4032","该坐标点不在中国境内，请检查！"),
	BAD_FILE("4031","上传文件格式不正确"),
	NOT_VIP_USER("4032","您没有访问权限，请联系管理员!"),
	BAD_JSON("4032","您的请求示例不是json格式，请检查!"),
	BAD_TXT("4032","文件内格式错误，请检查!"),
	Task_NOT_EXIST("4033","任务不存在！"),
	Task_ERROR("4034","任务执行错误！"),
	task_EXIST("4035","任务存在！"),
	task_EXIST2("4036","任务已存在，不能重复创建任务！"),
	task_START0("4037","任务状态禁用，该任务不能被启动！"),
	ALERT_NULL("4038","此告警通知规则不存在"),
	USER_NULL("4039","人员不能为空"),
	NULL_FILE("4040","上传文件内容为空，请检查！"),
	NULL_CHANGE_DATA("4041","修改的信息不存在，请刷新后重新操作！"),
	No_Btn_Power("4043","无按钮权限"),
	No_Power("4044","无权限，请重新登录");
	
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