package com.mhtech.platform.msrv.gateway.controller.handler;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhtech.platform.msrv.gateway.utils.RedisUtils;
import com.mhtech.platform.msrv.pojo.GatewayAccessLogVO;
import com.mhtech.platform.msrv.pojo.MsrvCode;
import com.mhtech.platform.msrv.pojo.MsrvMessage;

@Component
@Aspect
public class ControllerAspect {
	
	public static ThreadLocal<Long> traceId = new ThreadLocal<>();
	
	public static ThreadLocal<GatewayAccessLogVO> gateWayLog = new ThreadLocal<>();
	

	@Autowired
	RedisUtils redisUtils;
	

	
	@Pointcut("execution(* com.mhtech.platform.msrv.gateway.controller..*.*(..))")
	public void pointCut() {}
	
	@Before("pointCut()")
	public void before() {
		if(traceId.get() == null) return;
	}
	
	
	@After("pointCut()")
	public void after() {
		//结束
		if(traceId.get() == null) return;
		String id = traceId.get().toString();
		Object obj = redisUtils.hget("MsrvLog",id);
		MsrvMessage msrvMessage =  obj == null ? null : (MsrvMessage) obj;
		if (msrvMessage!=null) {
			//状态码，设置为20
			msrvMessage.setStatus(MsrvCode.COMPLETE.getCode());
			redisUtils.hset("MsrvLog", id, msrvMessage);
		}
	}
}
