package com.mhtech.platform.msrv.gateway.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhtech.platform.msrv.gateway.http.RequestWrapper;
import com.mobile.model.CustomerMobiles;
import com.mobile.service.CustomerMobilesService;
import com.mobile.service.CustomerPackageService;
import com.mobile.service.CustomerService;
import com.mobile.service.MobileService;
import com.mobile.utils.DateUtils;
import com.mobile.utils.IpUtil;
import com.mobile.utils.MD5HashUtil;
import com.mobile.utils.MobileLocationTool;
import com.mobile.utils.ValidationApi;

/**
 * <p>
 * 手机API过滤验证
 * <ol>
 * <li>拦截/MobileApi/api控制层的请求</li>
 * </ol>
 * </p>
 * 
 * @author GM
 *
 */
@Order(8)
//@Component
@WebFilter(filterName = "apiFilter1", urlPatterns = { "/MobileApi/apiNew/*" })
public class MobileApiFilter implements Filter {

	@Autowired
	CustomerService customerService;
	@Autowired
	CustomerMobilesService customerMobilesService;
	@Autowired
	CustomerPackageService customerPackageService;
	@Autowired
	MobileService mobileLocationService;
	@Value(value = "${mobile.time.limit}")
	private long timeLimit;
	
	@Value("${customer.check}")
	private String[] customerCheck;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Value("${mobile.includeUrls.callback}")
	String[] includeUrls;
//	// 不需要登录就可以访问的路径(比如:注册登录等)
//		String[] includeUrls = new String[] { "/MobileApi/UserController/login",
//				"/UserController/signOut", "" + "/UserController/getVerCode", "/UserController/resetPwd","/MobileApi/apiNew/" };

	// 是否需要过滤 请求路径url
	public boolean isNeedFilter(String uri) {
		for (String includeUrl : includeUrls) {
			if (includeUrl.equals(uri) || uri.contains(includeUrl)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		RequestWrapper requestWrapper = new RequestWrapper(request);
		// 获取请求ip
//		String ip = request.getRemoteAddr();
		String url = request.getRequestURI();
		String webName = request.getContextPath();
//		logger.info("@@@@@@@@@@@@@@@@@@@@@************************************************************************");
		logger.info("MobileApiFilter url:" + url + " ay:" + webName);
		String className = "";
		try {
			String[] split = url.substring(1, url.length()).split("/");
			logger.info("String[] split = " + Arrays.toString(split));
			className = split[2];
		} catch (Exception e) {
			logger.info(url + " = = = = = = = =");
		}

		if (isNeedFilter(url)) {

			// 获取url后面的请求参数
			String getParms = "";
			Map<String, String[]> parameterMap = requestWrapper.getParameterMap();
			Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
			Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
			// 存放参数key+value
			Map<String, Object> parms = new HashMap<String, Object>();
			while (iterator.hasNext()) {
				Map.Entry<String, String[]> next = iterator.next();
				// paramsMaps.put(next.getKey(), next.getValue()[0]);
				getParms += next.getKey() + "=" + next.getValue()[0] + "&";
				parms.put(next.getKey(), next.getValue()[0]);
			}
			if (getParms.length() > 0) {
				getParms = getParms.substring(0, getParms.length() - 1);
			}
			// 获取post请求参数
			String actionLogBodyParm = "";
			if ("POST".equals(request.getMethod().toUpperCase())) {
				actionLogBodyParm = requestWrapper.getBody();
			}

			Object customerId = "";
			Object mobile = "";
			Object name = "";
			Object idcard = "";
			Object seed = null;
			Object key = null;
			if (parms != null && parms.get("customerId") != null) {
				customerId = parms.get("customerId");
				mobile = parms.get("mobile");
				name = parms.get("name");
				idcard = parms.get("idcard");
				seed = parms.get("seed");
				key = parms.get("key");
			} else {
				JSONObject parseObject = JSON.parseObject(actionLogBodyParm);
				customerId = parseObject.get("customerId");
				mobile = parseObject.get("mobile");
				name = parseObject.get("name");
				idcard = parseObject.get("idcard");
				seed = parseObject.get("seed");
				key = parseObject.get("key");
			}
			String customerip = IpUtil.getIpAddr(request);
			Map<String, Object> parmat = new HashMap<>();
			parmat.put("customerId", customerId);
			parmat.put("mobile", mobile);
			parmat.put("customerIp", customerip);
			parmat.put("customerStatus", "1");
			parmat.put("name", name);
			parmat.put("idcard", idcard);
			parmat.put("seed", seed);
			parmat.put("key", key);
			parmat.put("className", className);
			parmat.put("timeLimit", timeLimit);
			System.err.println("========111===========" + parmat.toString());
			boolean isboolean=true;
			for(String str:customerCheck) {
				if(str.equals(customerId)) {
					isboolean=false;
					break;
				}
			}
			// 客户相关信息校验
			Map<String, Object> informationCheck = ValidationApi.InformationCheck(customerPackageService,
					customerMobilesService, customerService, mobileLocationService, parmat,isboolean);
			if (informationCheck != null) {
				Out(response, informationCheck);
				return;
			}
		}
		if (requestWrapper == null) {
			chain.doFilter(request, response);
		} else {
			chain.doFilter(requestWrapper, response);
		}

	}

	// 返回状态给前端-处理result
	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	// 返回游鸽数据给前端
	public void OutData(ServletResponse response, String data) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setCharacterEncoding("UTF-8");
		OutputStream out = response.getOutputStream();
		// 写入输出流
		out.write(data.getBytes("UTF-8"));
		out.flush();
	}

	// 返回自定义数据给前端
	public void Out(ServletResponse response, Object object) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setCharacterEncoding("UTF-8");
		String userJson = convertObjectToJson(object);
		OutputStream out = response.getOutputStream();
		// 写入输出流
		out.write(userJson.getBytes("UTF-8"));
		out.flush();
	}

	@Override
	public void destroy() {

	}

}
