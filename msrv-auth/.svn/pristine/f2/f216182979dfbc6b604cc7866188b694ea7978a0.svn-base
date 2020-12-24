package com.mhtech.platform.msrv.auth.login.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.auth.login.service.SMSService;
import com.mobile.utils.DateUtils;
import com.mobile.utils.HttpClientTryUtils;
import com.mobile.utils.MD5HashUtil;
import com.mobile.utils.SmsUtils;


@Service(value = "loginSMSService")
public class SMSServiceImpl implements SMSService{

//	@Value("${SmsSend.url}")
//	String url;//URL
//	@Value("${SmsSend.name}")
//	String name;//账号
//	@Value("${SmsSend.pwd}")
//	String pwd;//密码
//	@Value("${SmsSend.signature}")
//	String signature;//签名
//	 
//	@Override
//	public boolean sendSms(Map<String, Object> map) {
//		if(map==null||map.get("content")==null||map.get("dest")==null)
//			return false;
//		boolean flag=true;
//		String seed = DateUtils.getDateTime("yyyyMMddHHmmss");
//		String pwdMD5 = MD5HashUtil.md5LowerCase(pwd);
//		String key = MD5HashUtil.md5LowerCase(pwdMD5 + seed);
//		String content=map.get("content").toString();//内容
//		String dest=map.get("dest").toString();//手机号
//		try {
//			//乱码处理
//			signature = new String(signature.getBytes("ISO8859-1"), "UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}//将ISO8859-1编码格式转换成UTF-8
//		try {
////			url,"80035", seed, key, "15298736167", "【墨煌】今天阴有时有小雨", null)
//			String sendSms = SmsUtils.sendSms(url, name, seed, key, dest, signature+content, null);
//			System.err.println(sendSms);
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//			flag=false;
//		} catch (IOException e) {
//			e.printStackTrace();
//			flag=false;
//		}
//		return flag;
//	}
	
	@Value("${Smsurl}")
	String url;//URL
	@Value("${customerId}")
	String customerId;//账号
	@Value("${signature}")
	String signature;//签名
	 
	@Override
	public boolean sendSms(Map<String, Object> map) {
		if(map==null||map.get("content")==null||map.get("dest")==null)
			return false;
		boolean flag=true;
		String content=map.get("content").toString();//内容
		String dest=map.get("dest").toString();//手机号
		System.err.println(signature);
		try {
			//乱码处理
			signature = new String(signature.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			 //TODO Auto-generated catch block
			e1.printStackTrace();
		}//将ISO8859-1编码格式转换成UTF-8
		try {
			JSONObject dataMap = new JSONObject();
			dataMap.put("customerId", customerId);
			dataMap.put("signature", signature);
			dataMap.put("content", content);
			dataMap.put("mobile", dest);
			System.err.println("进入短信发送：url="+url+" 参数："+dataMap.toString()+" 调用时间："+DateUtils.getDateTime());
			//调取地址发送
			//String sendSms= HttpClientTryUtils.ajaxPost(url, dataMap);
			String sendSms= HttpClientTryUtils.ajaxPostJson(url, dataMap.toJSONString());
			System.err.println(sendSms);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			flag=false;
		} catch (IOException e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}

}
