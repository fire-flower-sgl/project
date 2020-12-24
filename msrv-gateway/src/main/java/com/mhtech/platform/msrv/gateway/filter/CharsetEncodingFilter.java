package com.mhtech.platform.msrv.gateway.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mhtech.platform.msrv.gateway.http.HttpMedia;

@Order(1)
@Component
@WebFilter(filterName = "charset-encoding-filter", urlPatterns = "/*")
public class CharsetEncodingFilter implements Filter {

	private String encoding = "UTF-8"; // 定义变量接收初始化的值
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse $response = (HttpServletResponse) response;
		HttpServletRequest $request = (HttpServletRequest) request;
		$response.setHeader("Access-Control-Allow-Credentials", "true");       
		$response.setHeader("Access-Control-Allow-Headers", $request.getHeader("Access-Control-Request-Headers"));
		$response.setHeader("Access-Control-Allow-Origin", $request.getHeader("Origin"));
		$response.setHeader("Access-Control-Max-Age", "1800");
		if("OPTIONS".equals(((HttpServletRequest) request).getMethod())) {
			return;
		}
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType("application/json; charset=utf-8");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
}
