//package com.mhtech.platform.msrv.gateway.filter;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mhtech.platform.msrv.gateway.http.HttpMedia;
//import com.mhtech.platform.msrv.gateway.http.RequestWrapper;
//import com.mhtech.platform.msrv.gateway.response.RespCode;
//import com.mhtech.platform.msrv.gateway.response.RespObject;
//import com.mhtech.platform.msrv.gateway.utils.RedisUtils;
//import com.mhtech.platform.msrv.gateway.utils.RespUtils;
//
///**
// * 访问频次拦截，超出频次范围的请求视为无效，直接返回信息不流转到业务层
// * @author GM
// */
//@Order(3)
//@Component
//@WebFilter(filterName = "req-freq-filter", urlPatterns = "/*")
//public class RequestFrequencyFilter implements Filter {
//	
//	@Autowired
//	RedisUtils redisUtils;
//	
//	private Integer flag = 0;
//	
//	@Value("${requestFrequencyFilter.criterion}")
//	private long criterion;
//	
//	@Value("${requestFrequencyFilter.userAccessTimes}")
//	private String userAccessTimes;
//	
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
//	
//	private final Logger logger= LoggerFactory.getLogger(BlackIPListFilter.class);
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		
//	}
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		HttpServletResponse response = (HttpServletResponse) servletResponse;
//		//用于post方式取值
//		RequestWrapper requestWrapper = new RequestWrapper(request);
//		String body = requestWrapper.getBody();
//		
//		//判断手机定位平台请求直接放行
//		if(!request.getRequestURI().startsWith("/MobileApi/")) {
//		
//			//获取请求ip
//			String ip = request.getRemoteAddr();
//			
//			//获取请求时间
//			Long accTime = new Date().getTime();
//			//获取上次访问毫秒
//			Object oldTimeObjec= redisUtils.hget("accessTimelog", ip);
//			//把本次请求时间放入redis
//			redisUtils.hset("accessTimelog", ip, accTime,2);
//			if(null==oldTimeObjec) {
//				logger.info(ip+":第一次登录");
//				if (requestWrapper == null) {
//					chain.doFilter(request, response);
//				} else {
//					chain.doFilter(requestWrapper, response);
//				}
//			}else {
//				//计算时间差
//				Long oldTime = Long.parseLong(oldTimeObjec.toString());
//				Long result= accTime - oldTime;
//				logger.info(ip+":与上次请求间隔=="+result);
//				
//				//获取redis该用户的请求阈值
//				Object rate = redisUtils.hget("frequencyList", ip);
//				//判断阈值用
//				boolean isFlag = false;
//				if(null!=rate) {
//					criterion = Long.valueOf(rate.toString());
//				}
//				if(criterion!=-1) {
//					criterion = (long)1000/criterion;
//					if(criterion>result) {
//						isFlag = true;
//					}
//				}
//				
//				logger.info("当前用户"+ip+"阈值为:"+criterion);
//				logger.info(isFlag+" -- (true:请求过快   flase:请求正常)");
//				
//				//判断请求频率
//				if(isFlag) {
//					flag = 1;
//					logger.info("频率过快....");
//					RespObject<?> object = RespUtils.build(RespCode.REQUEST_FAST);
//					response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//					response.setCharacterEncoding(HttpMedia.CHARSET_UTF8);
//					String userJson = convertObjectToJson(object);
//					OutputStream out = response.getOutputStream();
//					out.write(userJson.getBytes(HttpMedia.CHARSET_UTF8));
//					out.flush();
//				}else {
//					logger.info("频率正常...");
//					if (requestWrapper == null) {
//						chain.doFilter(request, response);
//					} else {
//						chain.doFilter(requestWrapper, response);
//					}
//				}
//			}
//			//把请求次数存入redis
//			Map<String, Object> accessinfo = new HashMap<String, Object>();
//			accessinfo.put("ip", ip);
//			accessinfo.put("total", 1);
//			accessinfo.put("intercept", flag);
//			
//			Object hget = redisUtils.hget("accessCount", ip);
//			//判断是否第一次
//			if(hget!=null) {
//				Map<String, Object> redisinfo= (Map<String, Object>) hget;
//				Integer total = (Integer) redisinfo.get("total");
//				Integer intercept = (Integer) redisinfo.get("intercept");
//				accessinfo.put("total",total +1);
//				accessinfo.put("intercept", intercept+flag);
//			}
//			//存入
//			redisUtils.hset("accessCount", ip, accessinfo);		
//			
//			//放入消息通道
//			redisTemplate.convertAndSend(userAccessTimes, ip);
//		}else {
//			if (requestWrapper == null) {
//				chain.doFilter(request, response);
//			} else {
//				chain.doFilter(requestWrapper, response);
//			}
//		}
//	}
//	
//	//返回状态给前端-处理result
//	public String convertObjectToJson(Object object) throws JsonProcessingException {
//		if (object == null) {
//			return null;
//		}
//		ObjectMapper mapper = new ObjectMapper();
//		return mapper.writeValueAsString(object);
//	}
//
//	@Override
//	public void destroy() {
//		
//	}
//}
