package com.mobile.service;

import java.util.Map;

/**
 * 线程异步处理
 * @author admini
 *
 */
public interface AsynProcesService {
	/**
	 * 手机定位关闭回调信息处理
	 * @param map
	 */
	void SMSCallback(Map<String, Object> map);
}
