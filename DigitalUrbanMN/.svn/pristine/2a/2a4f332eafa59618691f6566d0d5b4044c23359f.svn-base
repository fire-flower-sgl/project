package com.mobile.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author gary
 * @title: MD5HashUtil
 * @description: MD5加密
 * @date 2019/10/10
 */
public class MD5HashUtil {
	public static SimpleHash md5hash(String userName,String password){
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
        return new SimpleHash("MD5",password,credentialsSalt);
    }
	/**
	 * MD5加密小写
	 * @param plainText
	 * @return
	 */
	public static String md5LowerCase(String plainText)  {
	        String md5Hex = DigestUtils
	                .md5Hex(plainText).toUpperCase();
	        return md5Hex.toLowerCase();
	}
	/**
	 * MD5加密大写
	 * @param plainText
	 * @return
	 */
	public static String md5UpperCase(String plainText)  {
        String md5Hex = DigestUtils
                .md5Hex(plainText).toUpperCase();
        return md5Hex;
}
	public static void main(String[] args) {
//		SimpleHash md5hash = md5hash("szvg47k2","20191112173311");
//	  	System.out.println(md5hash);
//		System.err.println(md5(md5("szvg47k2")+"20191112173311"));
//		System.err.println(md5LowerCase("b4a281e245f73a5752798e2fb18812cc20191112173311"));
		System.err.println(md5hash("System","123456"));
	}
//3480e6777867c318245ff45f0fbf4bde
}
