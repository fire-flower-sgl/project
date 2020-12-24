package com.mhtech.platform.msrv.gateway.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhtech.platform.msrv.gateway.http.RequestWrapper;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RedisUtils;
import com.mobile.model.UnicomResponseEnums;
import com.mobile.service.UserService;

/**
 * 登录过滤
 * 
 * @author mjl_ 2019-10.28
 */
@Order(7)
@WebFilter(filterName = "MobileApiloginFiltering", urlPatterns = "/MobileApi/*")
//@SuppressWarnings("all")
//@ServletComponentScan
//@Component
public class MobileLoginFiltering implements Filter{

	@Autowired
	UserService userService;

	@Autowired
	RedisUtils redisUtils;
	
	@Value("${mobile.timeout}")
	private long timeout;
	
	// 不需要登录就可以访问的路径(比如:注册登录等)
	String[] includeUrls = new String[] { "/MobileApi/UserController/login","/MobileApi/task",
			"/UserController/signOut", "" + "/UserController/getVerCode", "/UserController/resetPwd","/MobileApi/apiNew/" };
	
	// 是否需要过滤 请求路径url
	public boolean isNeedFilter(String uri) {
		for (String includeUrl : includeUrls) {
			if (includeUrl.equals(uri)||uri.contains(includeUrl)) {
				return false;
			}
		}
		return true;
	}

	// 查询用户半小时内是否登录
	private boolean fingLogin(String userCode, String ip) {
		return userService.fingLogin(userCode, ip);
	}

	// 更新用户在线时间
	@Transactional
	public int fingSj(String userCode, String ip) {
		return  userService.updateOnlineTime(userCode, ip);
	}

	// 判断用户是否有权限
	public boolean fingQx(String userCode) {
		return userService.fingQx(userCode);
	}

	//获取用户上次登录的 ip
	public String fingLoginIp(String userCode) {
		return userService.fingLoginIp(userCode);
	}
	
	// 判断用户是否是单点权限
	public boolean fingMultiLogin(String userCode) {
		return userService.fingMultiLogin(userCode);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession(false);
		//用于get方式取值
		RequestWrapper requestWrapper = new RequestWrapper(request);
		//System.err.println("requestWrapper.getHeader(\"Authorization\")"+requestWrapper.getHeader("Authorization"));
		String body = requestWrapper.getBody();
			
		//访问的ip和路径
		String ip = request.getRemoteAddr();
		String uri = request.getRequestURI();

//		System.err.println(ip+"\t"+uri);
       
		//是否需要过滤
		if (isNeedFilter(uri)) { //需要	
			String dString = request.getMethod();
			String userCode = request.getParameter("user");
			JSONObject jsonObject = JSONObject.parseObject(body);
			if (StringUtils.isEmpty(userCode) && jsonObject != null && jsonObject.containsKey("user") && jsonObject.get("user") != null) {
				userCode = jsonObject.get("user").toString();
				if (StringUtils.isEmpty(userCode)) {
					userCode = request.getHeader("user");
				}
			}
			if (userCode != null&&!"null".equals(userCode)&&!userCode.trim().isEmpty()) {// 是否传递用户编码
				// 是否具有权限
//				if (fingQx(userCode)) {
//					LoginUser user = new LoginUser();
//					user.setUserCode(userCode);
//					ApplicationContextHolder.setCurrtUser(user);
//					ApplicationContextHolder.setRequestContext(requestWrapper);
//					boolean dr = fingLogin(userCode, ip);
					Object object = redisUtils.get(userCode+ip);
					if (object !=null) {// 是否登录失效
						// 判断是否在线半小时，半小时内有数据，表示在登录状态
						if (requestWrapper == null) {
							filterChain.doFilter(request, response);
						} else {
							filterChain.doFilter(requestWrapper, response);
						}
						redisUtils.expire(userCode+ip, timeout);
						// 更新登录时间
//						this.fingSj(userCode, ip);
					} else {
						String userIp=fingLoginIp(userCode);
						//判断单点还是多点权限
						if (fingMultiLogin(userCode)) {		
								if (!userIp.equals(ip)) {
									UnicomResponseEnums msg = UnicomResponseEnums.Remotelogin;
//									findOut("该账号已在异地登录", msg, servletRequest, servletResponse);
									Out(servletResponse,"该账号已在异地登录");
								}else {
									UnicomResponseEnums msg = UnicomResponseEnums.User_Not_Logged_In;
//									findOut("登录失效，登录超时", msg, servletRequest, servletResponse);
									Out(servletResponse,"登录失效，登录超时，登录超时");
								}
						}else {	//多点不用验证ip直接放行	
								UnicomResponseEnums msg = UnicomResponseEnums.User_Not_Logged_In;
//								findOut("登录失效，登录超时", msg, servletRequest, servletResponse);
								Out(servletResponse,"登录失效，登录超时");
						}
					}		
//				} else {
//					// 无权限--------暂时无用
//					UnicomResponseEnums msg = UnicomResponseEnums.No_Power;
//					findOut("无权限", msg, servletRequest, servletResponse);
//				}
			} else {
				// 无用户
				UnicomResponseEnums msg = UnicomResponseEnums.No_User;
//				findOut("无用户", msg, servletRequest, servletResponse);
				Out(servletResponse,"无用户");
			}
		} else {
			//需要过滤器---跳过验证
			if (requestWrapper == null) {
				filterChain.doFilter(request, response);
			} else {
				filterChain.doFilter(requestWrapper, response);
			}
		}
	}

	
	// 返回状态给前端
	public void findOut(String find, UnicomResponseEnums msg, ServletRequest servletRequest, ServletResponse servletResponse)
			throws IOException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		boolean success = false;
		int code=201;
		RespObject<?> ro=new RespObject(code,success,find);
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		String origin = request.getHeader("Origin");
		if (StringUtils.isNotBlank(origin)) {
			response.addHeader("Access-Control-Allow-Origin", origin);
		}
		// 允许带有cookie访问
		response.addHeader("Access-Control-Allow-Credentials", "true");
		// 告诉浏览器允许跨域访问的方法
		response.addHeader("Access-Control-Allow-Methods", "*");
		// 告诉浏览器允许带有Content-Type,header1,header2头的请求访问
		// resp.addHeader("Access-Control-Allow-Headers",
		// "Content-Type,header1,header2");
		// 设置支持所有的自定义请求头
		String headers = request.getHeader("Access-Control-Request-Headers");
		if (StringUtils.isNotBlank(headers)) {
			response.addHeader("Access-Control-Allow-Headers", headers);
		}
		// 告诉浏览器缓存OPTIONS预检请求1小时,避免非简单请求每次发送预检请求,提升性能
		response.addHeader("Access-Control-Max-Age", "3600");
		String userJson = convertObjectToJson(ro);
		OutputStream out = response.getOutputStream();
		out.write(userJson.getBytes("UTF-8"));
		out.flush();

	}
	
	
	// 返回自定义数据给前端
	public void Out(ServletResponse response, Object object) throws IOException {
		
		boolean success = false;
		int code=201;
		RespObject<?> ro=new RespObject(code,success,object.toString());
		
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setCharacterEncoding("UTF-8");
		String userJson = convertObjectToJson(ro);
		OutputStream out = response.getOutputStream();
		// 写入输出流
		out.write(userJson.getBytes("UTF-8"));
		out.flush();
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
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
