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
@Order(99999)
@Component
@WebFilter(filterName = "cross-domain-filter", urlPatterns = "/*")
public class CrossDomainFilter implements Filter {
	
	private String urlPrefix = "/webServer";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getRequestURI().startsWith(urlPrefix)) {
			chain.doFilter(request, resp);
			return;
		}
		HttpServletResponse response = (HttpServletResponse) resp;
        //解决跨域访问报错	
        response.setHeader(HttpMedia.RESP_ACAO, "*");   
        response.setHeader(HttpMedia.RESP_ACAM, HttpMedia.RESP_PPGOD);
        //设置过期时间
        response.setHeader(HttpMedia.RESP_ACMA, "3600");
        response.setHeader(HttpMedia.RESP_ACAH, HttpMedia.RESP_OXCACUA);
        // 支持HTTP 1.1.
        response.setHeader(HttpMedia.RESP_CC,HttpMedia.RESP_NNM);
        // 支持HTTP 1.0. response.setHeader("Expires", "0");
        response.setHeader(HttpMedia.RESP_PRAGMA,HttpMedia.RESP_NO_CACHE);
        // 编码
        // response.setCharacterEncoding(HttpMedia.CHARSET_UTF8);
        chain.doFilter(request, resp);
	}

	@Override
	public void destroy() {
		
	}
}
