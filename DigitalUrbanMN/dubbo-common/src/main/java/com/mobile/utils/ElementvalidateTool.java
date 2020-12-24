package com.mobile.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ElementvalidateTool {
	
    private static final String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;

        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    private static String byteToString(byte[] bByte) {
        StringBuilder sBuffer = new StringBuilder();
        for (byte aBByte : bByte) {
            sBuffer.append(byteToArrayString(aBByte));
        }
        return sBuffer.toString();
    }

    public static String encode(String strObj) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    /**
     * 站点PoolingHttpClientConnectionManager管理器
     */
    private static PoolingHttpClientConnectionManager manager;

    static {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);

            Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", sslSocketFactory)
                    .build();

            manager = new PoolingHttpClientConnectionManager(reg);
            //handshake timeout，采用默认值10s
            manager.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(1000*30).build());
            manager.setDefaultMaxPerRoute(200);
            manager.setMaxTotal(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  /**
   * 三要素验证
   * @param dxurl url地址
   * @param appSecret 机构密钥
   * @param appId 客户编码
   * @param type 类型MD5 或 其他
   * @param map
   * @return
   * @throws Exception
   */
	public static String threedimensionvalidate(String dxurl, String appSecret, String appId,String type, Map map)
			throws Exception {
//    	/phonethreemd5validate
//    	/phonethreedimensionvalidate
		String url = dxurl + "/phonethreedimensionvalidate";
		switch (type) {
		case "MD5":
			url = dxurl + "/carrier/valid/md5";
			break;
		default:
			url = dxurl + "/phonethreedimensionvalidate";
			break;
		}
		return post(map, url, appSecret, appId);
	}
  /**
   * 二要素验证
   * @param dxurl url地址
   * @param appSecret 机构秘钥
   * @param appId 客户编码
   * @param type 类型 md5
   * @param map
   * @return
   * @throws Exception
   */
    public static String twoelementvalidte(String dxurl,String appSecret,String appId,String type,Map map) throws Exception {
    	 String url = dxurl+"/phonetwoelementvalidte";
    	 switch (type) {
		case "MD5":
			url = dxurl+"/phoneTwoElementsMd5";
			break;
		default:
			url = dxurl+"/phonetwoelementvalidte";
			break;
		}
    	 
    	return post(map, url,appSecret,appId);
    }
    /**
     * 在网时长
     * @param dxurl
     * @param appSecret
     * @param appId
     * @param type
     * @param map
     * @return
     * @throws Exception
     */
    public static String onNetTime(String dxurl,String appSecret,String appId,String type,Map map) throws Exception {
   	 String url = dxurl+"/phoneOnNetTimeMd5";
   	 switch (type) {
		case "MD5":
			url = dxurl+"/phoneOnNetTimeMd5";
			break;
		default:
			url = dxurl+"/";
			break;
		}
   	 
   	return post(map, url,appSecret,appId);
   }
    /**
     * 身份验证
     * @param dxurl
     * @param appSecret
     * @param appId
     * @param type
     * @param map
     * @return
     * @throws Exception
     */
    public static String nameCardValidate(String dxurl,String appSecret,String appId,String type,Map map) throws Exception {
      	 String url = dxurl+"/nameCardValidate";
      	 switch (type) {
   		case "MD5":
   			url = dxurl+"/nameCardValidateMD5";
   			break;
   		default:
   			url = dxurl+"/nameCardValidate";
   			break;
   		}
      	 
      	return post(map, url,appSecret,appId);
      }
    /**
     * POST 请求方法
     * @param map 参数
     * @param url 地址
     * @return jsonstring
     * @throws Exception
     */
    private static String post(Map map,String url,String appSecret,String appId) throws Exception {
        String timestamp = "" + System.currentTimeMillis() / 1000;
        String sign = encode(appSecret + appId + timestamp + appSecret);
        CloseableHttpClient client = HttpClientBuilder.create().setConnectionManager(manager).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("customerId", appId);
        httpPost.setHeader("timestamp", timestamp);
        httpPost.setHeader("sign", sign);
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        //参数(请根据接口文档填写参数)
        JSONObject param = new JSONObject();
        param.put("name", map.get("name"));
        param.put("idcard", map.get("idcard"));
        param.put("mobile", map.get("mobile"));
        httpPost.setEntity(new StringEntity(param.toString(), Charset.forName("UTF-8")));
        String result=null;
        String resultJSONString=null;
        try (CloseableHttpResponse response = client.execute(httpPost)) {
            result = EntityUtils.toString(response.getEntity());
            if(result!=null) {
            	JSONObject jsonObject=JSON.parseObject(result);//返回值转json获取状态信息
            	//code访问状态
            	//status为数据状态
            	String resid=jsonObject.get("code").toString();
            	String resmsg=jsonObject.get("msg").toString();
            	Object object = jsonObject.get("data");
            	String status=null;
            	if(object!=null&&!object.toString().isEmpty()) {
    				JSONObject jsonData=JSON.parseObject(object.toString());
    				status=jsonData.getString("status");
            	}
            	if("200".equals(resid)&&!"-2".equals(status)) {
            		resid="0";
            	}else if("-2".equals(status)) {
            		resid=status;
            	}
            	jsonObject.put("resid", resid);
            	jsonObject.put("resmsg", resmsg);
            	jsonObject.remove("msg");
            	resultJSONString=jsonObject.toJSONString();
            }else {
            	resultJSONString=result;
            }
        } finally {
            httpPost.releaseConnection();
        }
        return resultJSONString;
    }

    public static void main(String[] args) throws Exception {
//        post();
    }
	
}
