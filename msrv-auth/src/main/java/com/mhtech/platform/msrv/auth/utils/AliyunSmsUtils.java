package com.mhtech.platform.msrv.auth.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 短信发送类
 * 阿里云
 * @author mjl_
 * @version 2019-10.10
 */
public class AliyunSmsUtils
{
    //产品名称:云通信短信API产品,开发者无需替换
	private static  String product = "Dysmsapi";
    //产品域名,开发者无需替换
	private static  String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private static  String accessKeyId ;
	private static  String accessKeySecret;
	private static  String ConnectTimeout;
	private static  String ReadTimeout;

     
    //配置阿里云api
    public static void init(String product, String domain, String accessKeyId, String accessKeySecret,
            String ConnectTimeout,String ReadTimeout) {
    	    AliyunSmsUtils.product=product;
    	    AliyunSmsUtils.domain=domain;   	    
    	    AliyunSmsUtils.accessKeyId=accessKeyId;
    	    AliyunSmsUtils.accessKeySecret=accessKeySecret;
    	    AliyunSmsUtils.ConnectTimeout=ConnectTimeout;
    	    AliyunSmsUtils.ReadTimeout=ReadTimeout;
     }
    public static void init(String accessKeyId, String accessKeySecret,
            String ConnectTimeout,String ReadTimeout) {	    
    	    AliyunSmsUtils.accessKeyId=accessKeyId;
    	    AliyunSmsUtils.accessKeySecret=accessKeySecret;
    	    AliyunSmsUtils.ConnectTimeout=ConnectTimeout;
    	    AliyunSmsUtils.ReadTimeout=ReadTimeout;
     }
    

	/**
     * 发送短信-单挑发送
     * map里的参数
     * @param phoneNumber 接收者手机号
     * @param name 短信签名
     * @param code 短信模板编码 
     * @param param 模板+数据
     * return true 成功 false失败
     */
    public static  boolean sendSms(String phoneNumber,String name,String code,String param) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", ConnectTimeout);
        System.setProperty("sun.net.client.defaultReadTimeout", ReadTimeout);

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
 				accessKeyId, accessKeySecret);
 		try {
 			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
 					domain);
 		} catch (ClientException e1) {
 			e1.printStackTrace();
 		}
        
        IAcsClient acsClient = new DefaultAcsClient(profile);
          
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号        
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(name);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(code);
        //可选:模板中的变量替换JSON串     
                             
        request.setTemplateParam(param);
      
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
              
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);   
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
        	return true;
            
        }else {
        	return false;
         
        }              
    }
    
    
  
    //格式化数据库字段驼峰式        下划线转峰驼
    public static String format(String name) {
    	StringBuffer mjl=new StringBuffer();
    	 //修改峰驼前
    	String[] x2 = name.split(",");
    	
        if (name.contains("_")) {
            String[] split = name.split("_");
            name = split[0];
            for (int m = 1; m < split.length; m++) {
                name +=split[m].substring(0, 1).toUpperCase() + split[m].substring(1).toLowerCase(); 	
            }
        }
        //修改峰驼后
        String[] x3 = name.split(","); 
        
        //
        for (int i = 0; i < x2.length; i++) {
        	 //判断前后是否一样,不一样,追加峰驼命名
			if (!x2[i].equals(x3[i])) {
				x2[i]+=" as "+x3[i];
			}	
			mjl.append(x2[i]+",");
		}
        //删除最后，
        String mjlname= mjl.substring(0, mjl.length() - 1);     
        return mjlname;
    }

    
    
}

