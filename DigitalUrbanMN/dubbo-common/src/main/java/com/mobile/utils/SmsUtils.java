package com.mobile.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 手机短信工具类
 * 
 * @author admini
 *
 */
@Component
public class SmsUtils {
	/**
	 * 短信发送
	 * @param url   url地址
	 * @param name    账号
	 * @param seed    当前时间，格式：YYYYMMDD HHMISS，例如：20130806102030
	 * @param key     md5( md5(password) + seed) )
	 * @param dest    手机号码（多个号码用“逗号”分开，GET方式最多50个号码，POST方式最多1000个号码）
	 * @param content 短信内容。最多500个字符
	 * @param ext     扩展号码（视通道是否支持扩展，可以为空或不填）
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String sendSms(String url,String name, String seed, String key, String dest, String content, String ext) throws ClientProtocolException, IOException {
//		String url = "http://223.223.179.37:8080/eums/sms/utf8/send.do";
		//参数值
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("name", name);
		dataMap.put("seed", seed);
		dataMap.put("key", key);
		dataMap.put("dest", dest);
		dataMap.put("content", content);
		dataMap.put("ext", ext);
		System.err.println("进入短信发送：url="+url+" 参数："+dataMap.toString()+" 调用时间："+DateUtils.getDateTime());
		//调取地址发送
		return HttpClientTryUtils.ajaxPost(url, dataMap);
//		String results[]=ajaxPost.split(":");
//		System.err.println(ajaxPost+"=="+results.length);
//		if (ajaxPost != null && !ajaxPost.trim().isEmpty()&&results.length==2 && Double.parseDouble(results[1]) > 999) {
//			return true;
//		} else {
//			return false;
//		}
	}
	/**
	 * 短信发送
	 * @param seed 当前时间，格式：YYYYMMDD HHMISS，例如：20130806102030
	 * @param key md5( md5(password) + seed) )
	 * @param dest 手机号码（多个号码用“逗号”分开，GET方式最多50个号码，POST方式最多1000个号码）
	 * @param content 短信内容。最多500个字符
	 * @param ext  扩展号码（视通道是否支持扩展，可以为空或不填）
	 * @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
//	public String sendSms(String seed,String key,String dest,String content, String ext) throws ClientProtocolException, IOException {
//		return sendSms(url,name,seed,key,dest,content,ext);
//	}

	public static void main(String[] args) {
		String url = "http://223.223.179.37:8080/eums/sms/utf8/send.do";
		System.err.println(DateUtils.getDateTime("yyyyMMddHHmmss"));
//    	[{"key":"name","value":"80035","equals":true,"description":"","enabled":true}]
		String seed = DateUtils.getDateTime("yyyyMMddHHmmss");
		String pwd = MD5HashUtil.md5LowerCase("szvg47k2");
		String key = MD5HashUtil.md5LowerCase(pwd + seed);
//		System.err.println(sendSms(url,"80035", seed, key, "15298736167", "【墨煌】今天阴有时有小雨", null));
	}
}
