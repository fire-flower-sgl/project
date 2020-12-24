package com.mhtech.platform.msrv.gateway.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.gateway.utils.RedisUtils;
import com.mhtech.platform.msrv.pojo.GatewayAccessLogVO;

@ControllerAdvice
public class ResponseBodySaver implements ResponseBodyAdvice {
	
	@Autowired
	private RedisUtils redisUtils;	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Value("${actionLogFilter.userAccessLog}")
	private String userAccessLog;
	
	private final Logger logger= LoggerFactory.getLogger(ResponseBodySaver.class);

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		try {
			if(body!=null) {
				String responseBody = JSONObject.toJSONString(body);
				if (responseBody.contains("data")) {
					GatewayAccessLogVO accessLog = ControllerAspect.gateWayLog.get();
					accessLog.setResponseBody(responseBody);
					//放入放入redis缓存
					redisUtils.hset("userAccessLog", accessLog.getId().toString(), accessLog);
					//放入redis消息通道
					redisTemplate.convertAndSend(userAccessLog, accessLog);
					ControllerAspect.gateWayLog.remove();
				}
			}
		} catch (Exception e) {
			logger.error(body.toString());
			logger.error(e.getMessage());
		}
		return body;
	}




	
	

}
