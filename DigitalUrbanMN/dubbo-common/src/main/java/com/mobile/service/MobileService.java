package com.mobile.service;

import java.util.Map;

public interface MobileService {
	/**
	 * 移动、电信号 手机定位授权 开启
	 * 
	 * @param map
	 * @return 返回json数据
	 */
	public String openPosition(Map<String, Object> map);

	/**
	 * 移动、电信号 实时定位 返回经纬度相关信息
	 * 
	 * @param map
	 * @return 返回json数据
	 */
	public String getPosition(Map<String, Object> map);

	/**
	 * 移动、电信号码 查询手机号状态是否开通授权
	 * 
	 * @param map
	 * @return 返回json数据
	 */
	public String getStatus(Map<String, Object> map);
	/**
	 * 移动、电信号码 查询手机号状态是否开通授权
	 * 
	 * @param map
	 * @return 返回json数据
	 */
	public String authlbsstatus(Map<String, Object> map);
	/**
	 * 移动、电信号码定位关闭
	 * 
	 * @param map
	 * @return 返回json数据
	 */
	public String closePosition(Map<String, Object> map);

	/**
	 * 调用联通号码定位方法
	 * 
	 * @param map
	 * @return 返回json数据
	 */
	public String getUnicomPosition(Map<String, Object> map);

	/**
	 * 移动、电信号 实时定位 返回经纬度相关信息
	 * 
	 * @param map
	 * @return 返回json数据
	 */
	public String APIposition(Map<String, Object> map);
	/**
	 * 移动、电信号 手机定位授权 开启
	 * 
	 * @param map
	 * @return 返回json数据
	 */
	public String APIopenPosition(Map<String, Object> map);
	/**
	 * 移动、电信号码定位关闭
	 * 
	 * @param map
	 * @return 返回json数据
	 */
	public String APIclosePosition(Map<String, Object> map);
	/**
	 * MD5三要素验证
	 * @param map
	 * @return
	 */
	String APICarrierValidMd5(Map map);
	/**
	 * 短信发送
	 * @param map
	 * @return
	 */
	String APIsendSms(Map<String,Object> map);
}
