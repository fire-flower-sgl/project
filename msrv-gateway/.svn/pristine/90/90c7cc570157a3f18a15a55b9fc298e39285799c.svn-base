package com.mhtech.platform.msrv.gateway.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.gateway.controller.handler.ControllerAspect;
import com.mhtech.platform.msrv.gateway.http.RequestWrapper;
import com.mhtech.platform.msrv.gateway.utils.RedisUtils;
import com.mhtech.platform.msrv.pojo.GatewayAccessLogVO;

/**
 * 访问ip 路径	请求参数拦截记录到数据库 用户操作日志表
 * @author GM
 */
@Order(4)
@Component
@WebFilter(filterName = "action-log-filter", urlPatterns = "/*")
public class ActionLogFilter implements Filter {
	
	@Autowired
	private IworkService  iworkService;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Value("${actionLogFilter.userAccessLog}")
	private String userAccessLog;
	
	private final Logger logger= LoggerFactory.getLogger(ActionLogFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		RequestWrapper requestWrapper = new RequestWrapper(request);
		//获取请求ip
		String ip = request.getRemoteAddr();
		String url = request.getRequestURI();		
		Long nextId = null ;
//		//判断手机定位平台请求直接放行
//		if(!url.startsWith("/MobileApi/")) {
			//获取url后面的请求参数
			String getParms = "";
			 Map<String, String[]> parameterMap = requestWrapper.getParameterMap();
	         Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
	         Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
	         while (iterator.hasNext()) {
	             Map.Entry<String, String[]> next = iterator.next();
	             //paramsMaps.put(next.getKey(), next.getValue()[0]);
	             getParms += next.getKey()+"="+next.getValue()[0]+"&";
	         }
	         if(getParms.length()>0) {
	        	 getParms = getParms.substring(0,getParms.length()-1);
	         }
	 		//获取post请求参数
	 		String actionLogBodyParm="";
			if ("POST".equals(request.getMethod().toUpperCase())) {
				actionLogBodyParm = requestWrapper.getBody();
	        } 
			 nextId = iworkService.getNextId();
			GatewayAccessLogVO accessLog = new GatewayAccessLogVO(nextId,ip,url,getParms,actionLogBodyParm,new Date());
			logger.info("ActionLogFilter=requestParms=:"+accessLog);
			ControllerAspect.gateWayLog.set(accessLog);
			
//			//放入放入redis缓存
//			redisUtils.hset("userAccessLog", accessLog.getId().toString(), accessLog);
//			//放入redis消息通道
//			redisTemplate.convertAndSend(userAccessLog, accessLog);
			if (requestWrapper != null) {	
				ControllerAspect.traceId.remove();
//				System.err.println("----------spring过滤器---记录日志-生成treatid--"+nextId);
				ControllerAspect.traceId.set(nextId);
				chain.doFilter(requestWrapper, response);		
			}else {
				  chain.doFilter(request, response);
			}
//		}else {
//			 chain.doFilter(request, response);
//		}		
	
	}

	//返回状态给前端-处理result
	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	@Override
	public void destroy() {
		
	}
}
