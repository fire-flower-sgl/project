package com.mobile.service;

import java.util.List;
import java.util.Map;

/**
 * 定位关闭短信回调信息记录
 * @author admini
 *
 */
public interface SMSCallbackRecordService {
	/**
	 * 回调记录信息插入
	 * @param map
	 * @return
	 */
	int insert(Map<String,Object> map);
	/**
	 * 回调记录信息批量插入
	 * @param list
	 * @return
	 */
	int batchInsert(List<Map<String, Object>> list);
}
