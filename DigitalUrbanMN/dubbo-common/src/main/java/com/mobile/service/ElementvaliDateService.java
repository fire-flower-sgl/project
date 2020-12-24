package com.mobile.service;

import java.util.Map;

public interface ElementvaliDateService {
	/**
	 * 三要素验证
	 * @param map
	 * @return
	 */
	String threedimensionvalidate(Map map);
	/**
	 * 二要素验证
	 * @param map
	 * @return
	 */
	String twoelementvalidte(Map map);
	
	/**
	 * 三要素验证
	 * @param map
	 * @return
	 */
	String threedimensionvalidateMD5(Map map);
	/**
	 * MD5三要素验证
	 * @param map
	 * @return
	 */
	String APICarrierValidMd5(Map map);
	/**
	 * 二要素验证
	 * @param map
	 * @return
	 */
	String twoelementvalidteMD5(Map map);
	
	
	/**
	 * 在网时长
	 * @param map
	 * @return
	 */
	String phoneOnNetTime(Map map );
	/**
	 * 身份验证
	 * @param map
	 * @return
	 */
	String nameCardValidate(Map map);
}
