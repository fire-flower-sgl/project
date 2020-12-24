package com.mhtech.platform.msrv.sharing.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.http.client.ClientProtocolException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.mobile.utils.HttpClientTryUtils;

/**
 * 手机短信工具类
 *  
 *
 */
@Component
@ConfigurationProperties(prefix = "smssend")
public class SmsUtils {
	
	
	private  String url;//URL
	private  String customerId;//客户id
	private  String signature;//签名

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * 发送短信
	 * @param mobile	手机号码
	 * @param content	短信内容
	 * @return 			true:成功,false:失败
	 */
	public boolean sendSms(String mobile,String content) {
			if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(content))
				return false;
			boolean flag=false;
			try {
				//参数值
				Map<String, String> dataMap = new HashMap<String, String>();
				dataMap.put("mobile", mobile);
				dataMap.put("content", content);
				dataMap.put("signature", signature);
				dataMap.put("customerId", customerId);
				String result = HttpClientTryUtils.ajaxPostJson(url, JSONObject.toJSONString(dataMap));
				if(!StringUtils.isEmpty(result)) {
					JSONObject object = JSONObject.parseObject(result);
					if(!StringUtils.isEmpty(object.get("resid"))) {
						if (object.get("resid").toString().equals("0"))flag = true;
					}
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
	 
	 public void main(String[] args) {
		 boolean sendSms = sendSms("15925623471", "测试aaa");
		 System.err.println(sendSms);
		 
	}
	 
//	 @PostConstruct
//	 private void pub() {
//		System.err.println("******************************"+url);
//
//	}
}
