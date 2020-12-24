package com.mhtech.platform.msrv.gateway.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhtech.platform.msrv.gateway.http.HttpMedia;
import com.mhtech.platform.msrv.gateway.http.RequestWrapper;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RedisUtils;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;

@Order(2)
@Component
@WebFilter(filterName = "black-iplist-filter", urlPatterns = "/*")
public class BlackIPListFilter implements Filter {
	
	
	@Autowired
	RedisUtils redisUtils;

	@Value("${requestFrequencyFilter.userAccessTimes}")
	private String userAccessTimes;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private final Logger logger= LoggerFactory.getLogger(BlackIPListFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,			
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		//用于post方式取值
		RequestWrapper requestWrapper = new RequestWrapper(request);
		String body = requestWrapper.getBody();
		
		String requestURI = request.getRequestURI();
		
		//判断手机定位平台请求直接放行
		if(!requestURI.startsWith("/MobileApi/")) {
		
			//获取redis中黑名单集合
			List<Object> blackIpsList = redisUtils.lGet("blackIpList", 0, -1);
			logger.info("黑名单size=="+blackIpsList.size());
			//获取请求ip
			String ip = request.getRemoteAddr();
			boolean flag = blackIpsList.contains(ip);
			if(flag) {
				//黑名单用户
				//把该无效请求保存到数据库
				Map<String, String> accessinfo = new HashMap<String, String>();
				accessinfo.put("ip", ip);
				accessinfo.put("flag", "1");			
				redisTemplate.convertAndSend(userAccessTimes, accessinfo);
				
				logger.info("黑名单访问IP=="+ip);
				RespObject<?> object = RespUtils.build(RespCode.BLACK_IP);
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				response.setCharacterEncoding(HttpMedia.CHARSET_UTF8);
				String userJson = convertObjectToJson(object);
				OutputStream out = response.getOutputStream();
				out.write(userJson.getBytes(HttpMedia.CHARSET_UTF8));
				out.flush();
			}else {
				logger.info("白名单访问IP=="+ip);
				//白名单用户
				if (requestWrapper == null) {
					chain.doFilter(request, response);
				} else {
					chain.doFilter(requestWrapper, response);
				}
			}
		}else {
			if (requestWrapper == null) {
				chain.doFilter(request, response);
			} else {
				chain.doFilter(requestWrapper, response);
			}
		}
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
