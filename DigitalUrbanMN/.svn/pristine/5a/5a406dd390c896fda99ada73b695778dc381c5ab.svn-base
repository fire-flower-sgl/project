/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.mobile.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

/**
 * HTTP客户端工具类（支持HTTPS）
 * @author ThinkGem
 * @version 2017-3-27
 */
public class HttpClientTryUtils {
	
	/**
	 * http的get请求
	 * @param url
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String get(String url) throws ClientProtocolException, IOException {
		return get(url, "UTF-8");
	}
	
	/**
	 * http的get请求
	 * @param url
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String get(String url, String charset) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);
		return executeRequest(httpGet, charset);
	}
	
	/**
	 * http的get请求，增加异步请求头参数
	 * @param url
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String ajaxGet(String url) throws ClientProtocolException, IOException {
		return ajaxGet(url, "UTF-8");
	}
	
	/**
	 * http的get请求，增加异步请求头参数
	 * @param url
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String ajaxGet(String url, String charset) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000*50).setConnectTimeout(1000*50).build();//设置请求和传输超时时间
		httpGet.setConfig(requestConfig);
		httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
		return executeRequest(httpGet, charset);
	}

	/**
	 * http的post请求，传递map格式参数
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String post(String url, Map<String, String> dataMap) throws ClientProtocolException, IOException {
		return post(url, dataMap, "UTF-8");
	}

	/**
	 * http的post请求，传递map格式参数
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String post(String url, Map<String, String> dataMap, String charset) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(url);
		//设置超时数据
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000*50).setConnectTimeout(1000*50).build();//设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try {
			if (dataMap != null){
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : dataMap.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, charset);
				formEntity.setContentEncoding(charset);
				httpPost.setEntity(formEntity);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return executeRequest(httpPost, charset);
	}

	/**
	 * http的post请求，增加异步请求头参数，传递map格式参数
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String ajaxPost(String url, Map<String, String> dataMap) throws ClientProtocolException, IOException {
		return ajaxPost(url, dataMap, "UTF-8");
	}

	/**
	 * http的post请求，增加异步请求头参数，传递map格式参数
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String ajaxPost(String url, Map<String, String> dataMap, String charset) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		try {
			if (dataMap != null){
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : dataMap.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, charset);
				formEntity.setContentEncoding(charset);
				httpPost.setEntity(formEntity);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return executeRequest(httpPost, charset);
	}

	/**
	 * http的post请求，增加异步请求头参数，传递json格式参数
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String ajaxPostJson(String url, String jsonString) throws ClientProtocolException, IOException {
		return ajaxPostJson(url, jsonString, "UTF-8");
	}

	/**
	 * http的post请求，增加异步请求头参数，传递json格式参数
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String ajaxPostJson(String url, String jsonString, String charset) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
//		try {
			StringEntity stringEntity = new StringEntity(jsonString, charset);// 解决中文乱码问题
			stringEntity.setContentEncoding(charset);
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		return executeRequest(httpPost, charset);
	}

	/**
	 * 执行一个http请求，传递HttpGet或HttpPost参数
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String executeRequest(HttpUriRequest httpRequest) throws ClientProtocolException, IOException {
		return executeRequest(httpRequest, "UTF-8");
	}

	/**
	 * 执行一个http请求，传递HttpGet或HttpPost参数
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String executeRequest(HttpUriRequest httpRequest, String charset) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient;
		if ("https".equals(httpRequest.getURI().getScheme())){
			httpclient = createSSLInsecureClient();
		}else{
			httpclient = HttpClients.createDefault();
		}
		String result = "";
		
			try {
				CloseableHttpResponse response = httpclient.execute(httpRequest);
				HttpEntity entity = null;
				try {
					entity = response.getEntity();
					result = EntityUtils.toString(entity, charset);
				} finally {
					EntityUtils.consume(entity);
					response.close();
				}
			} finally {
				httpclient.close();
			}
		
		return result;
	}
	
	/**
	 * 创建 SSL连接
	 */
	public static CloseableHttpClient createSSLInsecureClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (GeneralSecurityException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
