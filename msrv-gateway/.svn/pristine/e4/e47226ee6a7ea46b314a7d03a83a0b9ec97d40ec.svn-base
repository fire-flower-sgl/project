package com.mhtech.platform.msrv.gateway.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;





import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.gateway.http.HttpMedia;
import com.mhtech.platform.msrv.gateway.http.RequestWrapper;
import com.mhtech.platform.msrv.gateway.utils.HttpClientPoolUtils;
import com.mhtech.platform.msrv.gateway.utils.HttpClientUtils;
import com.mhtech.platform.msrv.gateway.utils.TokenUtils;

/**
 * @ 登录过滤器
 * @ 拦截登录接口-如果身份正确-生成密文返回给前端
 * @author Administrator
 *
 */
@Order(5)
@Component
@WebFilter(filterName = "login-verification-filter", urlPatterns = "/*")
public class LoginFilter implements Filter {

	private final Logger logger = LoggerFactory
			.getLogger(getClass());
	
	// 登录路径
	@Value("${loginUrl}")
	private String loginUrl;
    // 游鸽项目地址ip
	@Value("${youge.login.http}")
	private String yougeLoginHttp;
	// 游鸽登录接口路径
	@Value("${youge.login.url}")
	private String[] yougeLoginUrl;

	//无需登录路径
	@Value("${apiUrl}")
	private String apiUrl;
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		RequestWrapper rw = new RequestWrapper(request);
		//判断手机定位平台请求直接放行  api调用接口也直接放行   无需登录
		if(!request.getRequestURI().startsWith("/MobileApi/")&&!apiUrl.contains(request.getRequestURI())) {
			
			//拦截url为登录接口--跳转到游鸽平台
			if (yougeLoginUrl[0].equals(request.getRequestURI())) {
				// 获取请求参数
//				RequestWrapper rw = new RequestWrapper(request);
				String param = rw.getBody();
				// 执行转发
			    String OutputName = "";
				try {
					OutputName = HttpClientPoolUtils.doPost(yougeLoginHttp+yougeLoginUrl[0], param);
				} catch (Exception e) {
					logger.error("do youge login error", e);
				}
			    // 获取返回值
			    JSONObject jsonObject=JSONObject.parseObject(OutputName);
			    //判断返回值中具有status值
				if (jsonObject.containsKey("status")&&jsonObject.get("status")!=null) {
					//判断返回状态为200
					if (jsonObject.get("status").equals("200")) {			 
						//生成一个密文 放入Header
						String createToken = TokenUtils.createToken(jsonObject.getString("data"), 1000*60*60*30l);
							
	                    // 将token放入header请求头中	response.setHeader(HttpMedia.REQ_HEAD_AUTHOR, createToken);	
						// 直接将token放入返回的参数中
						jsonObject.getJSONObject("data").put(HttpMedia.REQ_HEAD_AUTHOR, createToken);
					}
				}
				
	//			    response.setHeader(HttpMedia.RESP_ACAO, "*");       
	//		        response.setHeader(HttpMedia.RESP_ACAM, HttpMedia.RESP_PPGOD);
	//		        //设置过期时间
	//		        response.setHeader(HttpMedia.RESP_ACMA, "3600");
	//		        response.setHeader(HttpMedia.RESP_ACAH, HttpMedia.RESP_OXCACUA);
	//		        // 支持HTTP 1.1.
	//		        response.setHeader(HttpMedia.RESP_CC,HttpMedia.RESP_NNM);
	//		        // 支持HTTP 1.0. response.setHeader("Expires", "0");
	//		        response.setHeader(HttpMedia.RESP_PRAGMA,HttpMedia.RESP_NO_CACHE);
	//		        // 编码
	//		        response.setCharacterEncoding(HttpMedia.CHARSET_UTF8);
					
				//放回取出的数据
	//			OutputStream out = response.getOutputStream();
	//			out.write(OutputName.getBytes(HttpMedia.CHARSET_UTF8));
				PrintWriter writer = response.getWriter();
				
				writer.write(jsonObject.toJSONString());
				
			} else {
				chain.doFilter(request, response);
			}
		
		}else {
			if (rw == null) {
				chain.doFilter(request, response);
			} else {
				chain.doFilter(rw, response);
			}
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
