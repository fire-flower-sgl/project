/**
 * 
 */
package com.tools.modules.service;

import com.alibaba.fastjson.JSONObject;


/**
 * @Desc //TODO 添加描述
 * @author jlm
 * 
 * @Date 2019年11月25日 下午4:34:50
 */
public interface SecurityService {

	/**
	 * 加密
	 * TODO
	 * @param content 待加密字符串
	 * @return
	 */
	public JSONObject encryption(String content) throws Exception;
	
	/**
	 * 解密
	 * TODO
	 * @param aesKey 加密的加盐值
	 * @param encrypted	待解密字符串
	 * @return
	 */
	public String decryption(String aesKey,String encrypted) throws Exception;
	
}
