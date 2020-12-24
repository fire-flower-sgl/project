package com.mhtech.platform.msrv.gateway.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhtech.platform.msrv.auth.service.IPowerService;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.gateway.controller.handler.ControllerAspect;
import com.mhtech.platform.msrv.gateway.http.HttpMedia;
import com.mhtech.platform.msrv.gateway.http.RequestWrapper;
import com.mhtech.platform.msrv.gateway.request.UserUpdataRoleParams;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.HttpClientPoolUtils;
import com.mhtech.platform.msrv.gateway.utils.OkHttpUtils;
import com.mhtech.platform.msrv.gateway.utils.RedisUtils;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.TokenUtils;
import com.mhtech.platform.msrv.pojo.GatewayAccessLogVO;
import com.mhtech.platform.msrv.pojo.RoleDTO;
import com.mhtech.platform.msrv.pojo.RoleVO;
import com.mhtech.platform.msrv.pojo.SpePowerDTO;
import com.mhtech.platform.msrv.pojo.SpePowerVO;
import com.mhtech.platform.msrv.pojo.UserAccountDTO;

import com.mhtech.platform.msrv.pojo.UserPowerDTO;
import com.mhtech.platform.msrv.pojo.UserPowerVO;
import com.mhtech.platform.msrv.pojo.UserRoleDTO;
import com.mhtech.platform.msrv.pojo.fatherValueVo;

import freemarker.template.utility.StringUtil;

/**
 * <p>
 * 拦截需要认证的请求路径
 * <ol>
 * <li>判断请求头是否包含token，并解析是否有效</li>
 * <li>判断请求路径跟当前token所解析的用户是否匹配</li>
 * </ol>
 * </p>
 *
 * @author GM
 *
 */
@Order(6)
@Component
@WebFilter(filterName = "authen-filter", urlPatterns = "/*")
@SuppressWarnings("all")
public class AuthenFilter implements Filter {

	// 游鸽ip地址
	@Value("${youge.login.http}")
	private String yougeLoginHttp;
	// 不拦截登录
	@Value("${youge.login.url}")
	private String[] yougeLoginUrl;
	// 不检测权限的接口
	@Value("${noUrl}")
	private String[] url;
	// 下载文件的接口
	@Value("${xz}")
	private String[] xz;
	// 下载文件的接口
	@Value("${sq}")
	private String[] sq;

	@Value("${actionLogFilter.userAccessLog}")
	private String userAccessLog;
	// 游鸽get请求
	@Value("${youge.get}")
	private String[] yougeGet;

	private Logger logger = LoggerFactory.getLogger(AuthenFilter.class);

	@Autowired
	private IPowerService ipower;
	@Autowired
	private IworkService iworkService;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	//无需登录路径
	@Value("${apiUrl}")
	private String apiUrl;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURI();
        // 获取请求参数
        RequestWrapper rw = new RequestWrapper(request);
        String param = rw.getBody();
        String postParam = null;
        //System.err.println(!apiUrl.contains(request.getRequestURI())+"========="+request.getRequestURI());
        // 判断手机定位平台请求直接放行-----不验证密文，权限    api调用接口也直接放行
        if (!url.startsWith("/MobileApi/")&&!apiUrl.contains(url)) {

            if (!yougeLoginUrl[0].equals(url)) {
                String method = request.getMethod();
                // 获取请求参数
                if (method.equals("GET")) {
                    param = request.getQueryString();
                } else if (method.equals("POST")) {
                    postParam = request.getQueryString();
                }

                // 不需要解析密文的游鸽接口-转发游鸽-else其他服务所有接口需要密文
                if (isNeedFilter(url)) {
                    try {
                        String data = OkHttpUtils.builder().doPostHttpRequest(yougeLoginHttp + url, param);// okhttp方式
                        insertLog(data);
                        // String data = HttpClientPoolUtils.doPost(yougeLoginHttp+url,param);
                        OutData(response, data);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Out(response, RespCode.UNKNOWN);
                    }
                } else {

                    // 获取前端传入的密文
                    String token = request.getHeader(HttpMedia.REQ_HEAD_AUTHOR);
                    try {
                        // 解析密文
                        if (!StringUtils.isEmpty(token)&&!token.equals("null"))  {
                            // 从密文中获取登录信息
                            String $token = TokenUtils.verifyToken(token);
                            JSONObject jsonObject = JSONObject.parseObject($token);
                            if (jsonObject.get("userCode") != null) {
                                // 验证访问路径是否在用户权限里面
                                if (!power(jsonObject.get("userCode").toString(), url)) {
                                    // 无权限
                                    Out(response, RespCode.NO_USER_RIGHTS);
                                } else {
                                    if (postParam != null) {
                                        url = url + "?" + postParam;// 拼接请求参数
                                    }
                                    // 具有权限下，进行转发，与数据处理
                                    OutJk(request, response, param, url, chain);
                                }
                            }
                        } else if (token.equals("null")) {//token为null  浏览器token被清除的情况
                            Out(response, RespCode.SING_FAILURE);

                        }else {
                            // 密文空-未登录/或异常访问
                            Out(response, RespCode.NO_TOKEN);
                        }
                    } catch (TokenExpiredException e) {//登录验证过期
                        e.printStackTrace();
                        logger.error(e.getMessage());
                        // 密文错误
                        Out(response, RespCode.SING_FAILURE);

                    }catch (Exception e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                        // 密文错误
                        Out(response, RespCode.KEY_FAILURE);
                    }
                }
                return;// 不可去除
            }
            chain.doFilter(request, response);
        } else {
            if (rw == null) {
                chain.doFilter(request, response);
            } else {
                chain.doFilter(rw, response);
            }
        }

        // 测试时把这个打开就好...
        // chain.doFilter(servletRequest,response);
	}


	// 是否需要过滤 请求路径_url
	public boolean isNeedFilter(String url) {
		for (String includeUrl : yougeLoginUrl) {
			if (includeUrl.equals(url) && !yougeLoginUrl[0].equals(url)) {
				return true;
			}
		}
		return false;
	}

	// 是否是游鸽的get请求
	public boolean yougeGet(String url) {
		for (String includeUrl : yougeGet) {
			if (includeUrl.equals(url))
				return true;
		}
		return false;
	}

	// 返回状态给前端
	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object != null) {
			// ObjectMapper是一个使用 Swift编写的用于model对象：用于JSON之间转换的框架
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
		}
		return null;
	}

	// 判断url_是否在权限列表中
	public boolean power(String userCode, String url) {

		List<UserPowerVO> powerList = ipower.selectPowerNumUrl(new UserPowerDTO(userCode));
		// 过滤不需要拦截的接口(没有配置权限的数据接口)-直接添加到权限路径里面-让下面的查询可以查询到
		for (int i = 0; i < this.url.length; i++) {
			powerList.add(new UserPowerVO(this.url[i]));
		}

		// 判断url是否在权限url中-存在(有权限)-不存在(无权限)
		for (int i = 0; i < powerList.size(); i++) {
			if (url.equals(powerList.get(i).getUrl())) {
				return true;
			}
		}
		return false;
	}

	// 返回自定义数据给前端
	public void Out(ServletResponse response, RespCode respCode) throws IOException {
		RespObject<?> object = RespUtils.build(respCode);
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setCharacterEncoding(HttpMedia.CHARSET_UTF8);
		String userJson = convertObjectToJson(object);
		OutputStream out = response.getOutputStream();
		// 写入输出流
		out.write(userJson.getBytes(HttpMedia.CHARSET_UTF8));
		out.flush();
	}

	// 返回游鸽数据给前端
	public void OutData(ServletResponse response, String data) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setCharacterEncoding(HttpMedia.CHARSET_UTF8);
		OutputStream out = response.getOutputStream();
		// 写入输出流
		out.write(data.getBytes(HttpMedia.CHARSET_UTF8));
		out.flush();
	}

	// 判断是get还是post，在判断是上传还是下载还是普通接口--执行不同处理
	public void OutJk(HttpServletRequest request, ServletResponse response, String param, String url, FilterChain chain)
			throws IOException, Exception {
		// get请求
		if (request.getMethod().equals("GET")) {
			// 是游鸽的get，转发游鸽
			if (yougeGet(url)) {
				// String data=HttpClientPoolUtils.doGet(yougeLoginHttp+url+"?"+param);
				String data = OkHttpUtils.builder().doGetHttpRequest(yougeLoginHttp + url + "?" + param);
				insertLog(data);
				OutData(response, data.toString());
			} else {
				// 其他服务get接口-直接放行
				chain.doFilter(request, response);
			}
		} else {
			//System.err.println(request.getContentType()+"=========request.getContentType()========");
			// post请求，如果是文件上传-执行文件上传
			if (request.getContentType()!=null&&request.getContentType().indexOf(MediaType.MULTIPART_FORM_DATA_VALUE) > -1) {
				String str1="";
				if (url.indexOf("?")>0) {
					 str1=url.substring(0, url.indexOf("?"));
				}
                if (findUrl(sq,str1)) {
    				try {
    					//游鸽的上传文件接口-转发游鸽
    					String result = HttpClientPoolUtils.forwardMultipartReqeust(yougeLoginHttp + url, request);
    					insertLog(result);
    					OutData(response, result);
    				} catch (Exception e) {
    					e.printStackTrace();
    					logger.error(e.getMessage());
    				}
				}else {
					//其他服务上传文件-放行
					chain.doFilter(request, response);
				}
			} else {
				if (findUrl(xz,url)) {
					// 文件下载
					HttpClientPoolUtils.copyMultipartResponse(yougeLoginHttp + url, param,
							(HttpServletResponse) response);
				} else {
					// 角色与权限接口-由权限服务受理--如果存在缓存中---是角色and权限接口
					if (redisUtils.hget("ygPower", url) != null) {
						// redis中获取映射-转发对应服务
						System.err.println("======="+redisUtils.hget("ygPower", url).toString());
						request.getRequestDispatcher(redisUtils.hget("ygPower", url).toString()).forward(request,
								response);
					} else {
						if (url.startsWith("/webServer/")) {
							// 转发到游鸽项目-调用游鸽接口-拿回返回数据，再将数据返回
							try {
								String data = OkHttpUtils.builder().doPostHttpRequest(yougeLoginHttp + url, param);
								insertLog(data);
								// 游鸽特殊接口-有权限数据-有权限数据，又有自己的数据，两头获取数据整合后，返回
								data = userPower(url, data, param);
								OutData(response, data);
							} catch (Exception e) {
								e.printStackTrace();
								logger.error(e.getMessage());
							}
						} else {
							// 其他服务post接口-直接放行
							chain.doFilter(request, response);
						}
					}
				}

			}

		}

	}

	/**
	 * url_是否存在集合中
	 * @param str 集合
	 * @param url 值
	 * @return 存在true 不存在 false
	 */
	public boolean findUrl(String [] str,String url) {
		boolean out = false;
		for (int i = 0; i < str.length; i++) {
			if (url.equals(str[i])) {
				out = true;
			}
		}
		return out;
	}

	// 存入日志
	public void insertLog(String data) {
		GatewayAccessLogVO accessLog = ControllerAspect.gateWayLog.get();
		accessLog.setResponseBody(data);
		redisUtils.hset("userAccessLog", accessLog.getId().toString(), accessLog);
		redisTemplate.convertAndSend(userAccessLog, accessLog);
	}

	// 新增与修改用户的查询----角色列表，特殊权限
	public String userPower(String url, String data, String param) throws IOException, Exception {

		if (!data.contains("success"))
			return data;

		JSONObject jsdata = JSONObject.parseObject(data);
		JSONObject paramData = JSONObject.parseObject(param);
		switch (url) {
		case "/webServer/userPower/goAddUserUi":
			// 新增用户点击按钮
			data = goAddUserUi(jsdata, paramData);
			break;
		case "/webServer/userPower/queryRole":
			// 修改用户点击按钮
			data = queryRole(jsdata, paramData);
			break;
		case "/webServer/userPower/addRole":
			// 游鸽新增后，权限平台保存用户信息--不需要重置data
			addRole(jsdata, paramData);
			break;
		case "/webServer/SysRole/findSysPowerOne":
			// 角色编号唯一性验证
			data = findSysPowerOne(jsdata, paramData);
			break;
		case "/webServer/userPower/updateRole":
			// 当为修改用户角色的时候，调用权限平台接口，修改关联关系
			Object newUser = paramData.get("newUser");
			JSONObject user = JSONObject.parseObject(newUser.toString());
			String userCode = user.getString("userCode");// 用户编码
			Object roleList = paramData.get("roleList");

			String str[] = roleList.toString().replace("[", "").replace("]", "").split(",");// 角色编码集合

			List<UserRoleDTO> list = new ArrayList<>();
			for (int i = 0; i < str.length; i++) {
				String zhi = str[i].toString();
				list.add(new UserRoleDTO(iworkService.getNextId(), userCode, JSON.parse(zhi).toString()));
			}
			ipower.updataUserRole(userCode, list);
			break;
		default:
			break;
		}
		return data;
	}

	// 角色编号唯一性验证
	public String findSysPowerOne(JSONObject jsdata, JSONObject paramData) {
		// 角色编号唯一性验证
		String roleNum = "";
		// 判断是否存在data存在-获取data中的id
		if (jsdata.containsKey("data")) {
			roleNum = paramData.getString("roleNum");
			jsdata.remove("data");
		}
		boolean sum = ipower.findSysPowerOne(new RoleDTO(roleNum));
		jsdata.put("data", sum);
		return jsdata.toJSONString();
	}

	// 查询角色列表-处理格式
	public List<Map<String, Object>> selectRole(String userCode) {

		List<Map<String, Object>> roleMap = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<RoleVO> roleListVo = ipower.listRole(new RoleDTO()).getData();
		// 查询角色用户关联表
		List<String> list = ipower.selectRoleNumsByUserCode(userCode);

		roleListVo.forEach(x -> {
			Map<String, Object> h3 = new HashMap<>();
			h3.put("roleNum", x.getRoleNum());
			h3.put("roleName", x.getRoleName());
			if (userCode != null) {
				h3.put("state", list.contains(x.getRoleNum()) ? 1 : 0);
			}
			roleMap.add(h3);
		});
		return roleMap;
	}

	// 查询特殊权限-处理格式
	public List<Map<String, Object>> selectDataPower() {

		List<Map<String, Object>> dataList = new ArrayList<>();
		// 获取数据
		List<UserPowerVO> listDataPower = ipower.listDataPower();
		listDataPower.forEach(x -> {
			Map<String, Object> h4 = new HashMap<>();
			h4.put("id", x.getId());
			h4.put("powerNum", x.getPowerNum());
			h4.put("powerName", x.getPowerName());
			h4.put("specialPowerKey", x.getSpecialPowerKey());
			h4.put("specialPowerValue", x.getSpecialPowerValue());
			dataList.add(h4);
		});

		return dataList;
	}

	// 依据id去游鸽，查询user对象
	public String userCode(String id, String user) throws IOException, Exception {

		JSONObject kk = new JSONObject();
		kk.put("id", id);
		kk.put("user", user);
		String userYg = HttpClientPoolUtils.doPost(yougeLoginHttp + "/webServer/SpUser/selectSpUser", kk.toString());
		JSONObject userData = JSONObject.parseObject(userYg);
		String userdx = userData.getString("data");
		if (userdx != null) {
			JSONObject userDatadx = JSONObject.parseObject(userdx);
			return userDatadx.getString("userCode");
		}
		return null;
	}

	// 修改用户点击按钮
	public String queryRole(JSONObject jsdata, JSONObject paramData) throws IOException, Exception {
		// 判断是否存在
		if (!jsdata.containsKey("data")) {
			return jsdata.toJSONString();
		}
		// 请求参数id user
		String id = paramData.getString("userCode");
		String user = paramData.getString("user");
		// 转发游鸽接口--查询user,获取userCode
		String userCode = userCode(id, user);

		// 原数据体----替换权限，特殊权限集合
		JSONObject xx = JSONObject.parseObject(jsdata.get("data").toString());

		// 处理----角色集合
		List<Map<String, Object>> selectRole = selectRole(userCode);
		// 替换角色列表
		xx.put("roleList", selectRole);
		List<Map<String, Object>> mapDataPower = selectDataPower();
		// 处理----特殊权限 sql_
		for (int i = 0; i < mapDataPower.size(); i++) {
			String value = mapDataPower.get(i).get("specialPowerValue").toString();
			String speNum = mapDataPower.get(i).get("powerNum").toString();
			// 转发游鸽-执行sql_
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sql", value);
			map.put("user", user);// 游鸽过滤器中请求参数user
			String shuju = HttpClientPoolUtils.doPost(yougeLoginHttp + "/webServer/userPower/selectTsPower",
					JSONObject.toJSONString(map));
			SpePowerVO powerInfo = ipower.selectSpePower(new SpePowerDTO(userCode, speNum));

			if (shuju != null && powerInfo != null) {
				List<fatherValueVo> list = new ArrayList<>();
				List<fatherValueVo> gameList = JSONObject.parseArray(shuju, fatherValueVo.class);
				for (int j = 0; j < gameList.size(); j++) {
					list.add(new fatherValueVo(gameList.get(i).getAreaCode(), gameList.get(i).getAreaName(),
							gameList.get(i).getId(), gameList.get(i).getParentCode()));
				}
				// 往powerInfo对象中添加 sql_查询的集合
				powerInfo.setFatherValue(list);
			}
			if (powerInfo == null) {
				powerInfo = new SpePowerVO();
			}
			// 添加powerInfo对象
			mapDataPower.get(i).put("powerInfo", powerInfo);

		}
		// 替换特殊权限
		xx.put("spePowerList", mapDataPower);
		// 添加到data
		jsdata.put("data", xx);
		return jsdata.toJSONString();
	}

	// 游鸽新增后，权限平台保存用户信息
	public void addRole(JSONObject jsdata, JSONObject paramData) {

		String userId = "";
		// 判断是否存在data存在-获取data中的id
		if (jsdata.containsKey("data") && jsdata.get("data") != null) {
			userId = jsdata.get("data").toString();
		}
		JSONObject newUser = JSONObject.parseObject(paramData.getString("newUser"));
		String zhanghao = "";// 账号
		String name = "";// 姓名
		if (newUser.containsKey("userCode")) {
			zhanghao = newUser.get("userCode").toString();
		}
		if (newUser.containsKey("name")) {
			name = newUser.get("name").toString();
		}

		// 新增到权限平台用户表 id为游鸽接口，返回的主键id
		ipower.insertUserAccount(new UserAccountDTO(iworkService.getNextId(), userId, zhanghao, name, "1"));


	}

	// 新增用户点击按钮
	public String goAddUserUi(JSONObject jsdata, JSONObject paramData) throws IOException, Exception {
		// 删除返回数据
		jsdata.remove("data");
		// 新建data-返回数据--权限由改平台受理
		Map<String, Object> power = new HashMap<>();

		// 角色集合处理
		List<Map<String, Object>> rolelist = selectRole(null);
		power.put("roleList", rolelist);

		// 特殊权限集合
		List<Map<String, Object>> dataList = selectDataPower();
		// 处理 特殊权限sql_
		for (int i = 0; i < dataList.size(); i++) {
			String value = dataList.get(i).get("specialPowerValue").toString();
			// 格式处理
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sql", value);
			// 转发游鸽_执行sql_获取数据
			String shuju = HttpClientPoolUtils.doPost(yougeLoginHttp + "/webServer/userPower/selectTsPower",
					JSONObject.toJSONString(map));
			// 返回值替换
			if (shuju != null) {
				Object parse = JSON.parse(shuju);
				dataList.get(i).put("specialPowerValue", parse);
			}
		}
		power.put("spePowerList", dataList);
		// 转换格式-返回新的data数据
		jsdata.put("data", power);
		return jsdata.toJSONString();
	}

	@Override
	public void destroy() {

	}
}
