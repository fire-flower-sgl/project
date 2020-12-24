package com.mobile.service;

import java.util.Map;

/**
 * 手机短信发送接口
 * @author admini
 *
 */
public interface SMSService {
	/**
	 * 短信发送
	 * @param map
	 * @return
	 */
	boolean sendSms(Map<String,Object> map);
	/**
	 * 短信发送
	 * @param map
	 * @return
	 */
	String APIsendSms(Map<String,Object> map);
}
