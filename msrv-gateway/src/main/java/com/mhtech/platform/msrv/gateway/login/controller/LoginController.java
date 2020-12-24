package com.mhtech.platform.msrv.gateway.login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.auth.login.service.EmailService;
import com.mhtech.platform.msrv.auth.login.service.SMSService;
import com.mhtech.platform.msrv.auth.login.service.SpUserService;
import com.mhtech.platform.msrv.auth.login.service.UserService;
import com.mhtech.platform.msrv.gateway.http.HttpMedia;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RedisUtils;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.TokenUtils;
import com.mobile.model.Email;
import com.mobile.model.EmailParameter;
import com.mobile.model.LoginUser;
import com.mobile.model.Result;
import com.mobile.model.UnicomResponseEnums;
import com.mobile.model.User;
import com.mobile.utils.EmailUtils;
import com.mobile.utils.MD5HashUtil;
import com.mobile.utils.Utilis;


/**
 * @ClassName UserLoginController
 * @Description 用户登录
 * @Author admini
 * @Date 2019/10/15 16:43
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/auth/login")
@SuppressWarnings("all")
public class LoginController {

//	@Reference(version = "1.0.0")
	@Autowired
	UserService userService;

	//@Reference(version = "1.0.0")
	@Autowired
	EmailService emailService;
	@Autowired	
	//@Reference(version = "1.0.0")
	SMSService smsService;
	
	@Autowired
	private SpUserService spUserService;
	
	@Autowired
	RedisUtils redisUtils;
	@Value("${mobile.timeout}")
	private long timeout;
	
	@PostMapping(value = "/login")
//	@Transactional(rollbackOn = Exception.class)
	public RespObject<?> loginIn(@RequestBody Map<String, String> map, HttpServletRequest request,HttpServletResponse response) {
		RespCode msg = RespCode.SUCCESS;
		String userName = "";
		String password = "";
		String platType = "";
		//用户名，密码，登录平台
		if (map == null 
				||!map.containsKey("userName")||!map.containsKey("password")||!map.containsKey("platType")
				|| map.get("userName").isEmpty() || map.get("password").isEmpty()||map.get("platType").isEmpty()) {
			msg = RespCode.NULL_ARGUMENT;
			return RespUtils.build(msg);
		}
		userName = map.get("userName");
		password = map.get("password");
		platType = map.get("platType");
		
		System.out.println("------------------------------loninInfo------------------------------------");
		System.out.println(userName + "\t" + password);

		// 验证用户名密码
		//LoginUser loginUser = userService.findUserByNameAndPwd(userName, password);
		LoginUser loginUser = userService.findUserByNameAndPwdAndPlatForm(userName, password,platType);
		if (loginUser == null) {
			msg = RespCode.NOT_MATCH;
			return RespUtils.build(msg);
		}
		//System.out.println(loginUser);
		// 用户编码
		String userCode = loginUser.getUserCode();
		//System.out.println("userCode:" + userCode);
		// 访问的ip
		String ip = request.getRemoteAddr();
		//System.out.println("IP:" + ip);
		// 浏览器id
		String browserId = getWebId(request.getCookies(), "webId");
		//System.out.println("cookie中的webId" + browserId);
		if (browserId == null) {
			browserId = request.getSession().getId();
			Cookie cookie = new Cookie("webId", browserId);
			cookie.setMaxAge(365 * 24 * 60 * 60);
			response.addCookie(cookie);
			//System.out.println("新生成的webId" + browserId);
		}

		// 提取单点登录标记:1表示是点单登录
		String multLogin = loginUser.getMultiLogin();
		if (multLogin.equals("0")) {
			// 单点登录
			boolean removeUser = userService.removeUserByUserCode(userCode);// 强退改用户
		} else {
			// 允许多点登录
			boolean isIpExist = userService.findIpIsExist(ip);// 判断是否存在
			//System.out.println("ip:" + isIpExist);
			if (isIpExist) {
				boolean isWebExist = userService.findWebIsExist(browserId);// 判断是否存在
				//System.out.println("web:" + isWebExist);
				if (isWebExist) {
					boolean removeUser = userService.removeUserByWebType(userCode, browserId);// 强退改用户
					//System.out.println("removeUser:" + removeUser);
				}
			}
		}
		// 写入登录在线表
		boolean flag = userService.addLoginUser(userCode, ip, browserId);

		// 检测账户是否异常
		boolean flag2 = userService.queryUserIsNormal(userCode, ip);
		//System.out.println("是否异常登录:" + flag2);
		String IsNormal = "";
		if (flag2) {
			IsNormal = "1";
		} else {
			IsNormal = "0";
		}
		// 记录登录历史表
		String loginLogId = Utilis.getUUID();
		boolean flag3 = userService.addLoginUserLog(loginLogId, userCode, ip, browserId, IsNormal);

		//Map<String, String> UserInfo = new HashMap<String, String>();
		JSONObject UserInfo=new JSONObject();
		UserInfo.put("userCode", loginUser.getUserCode());
		UserInfo.put("userName", loginUser.getName());
		UserInfo.put("isNormal", IsNormal);
		UserInfo.put("loginLogId", loginLogId);
		UserInfo.put("id", loginUser.getId());
		UserInfo.put("customerId", loginUser.getCompanyCode());
		//生成一个密文 放入Header
		String createToken = TokenUtils.createToken(UserInfo.toString(), 1000*60*60*30l);
        // 将token放入header请求头中	response.setHeader(HttpMedia.REQ_HEAD_AUTHOR, createToken);	
		// 直接将token放入返回的参数中
		UserInfo.put(HttpMedia.REQ_HEAD_AUTHOR, createToken);
		redisUtils.set(loginUser.getUserCode()+ip, loginUser,timeout);
		return RespUtils.buildOKWithDataYg(UserInfo);
	}

	@PostMapping(value = "/getVerCode")
	public RespObject<?> getVerCode(@RequestBody Map<String, String> map, HttpServletRequest req,
			HttpServletResponse response) {
		RespCode msg = RespCode.SUCCESS;
		if (map.get("email") == null && map.get("phoneNo") == null) {
			msg = RespCode.NULL_ARGUMENT;
			return RespUtils.build(msg);
		}
		String email = map.get("email");
		String phone = map.get("phoneNo");

		boolean verSuccess = false;
		if (email != null) {
			// 发邮箱验证
			boolean checkemailExist = userService.checkAccountExist(email);
			//System.out.println("发验证-邮箱存在" + checkemailExist);
			if (!checkemailExist) {
				msg = RespCode.NO_USER_EXIST;
				return RespUtils.build(msg);
			}
			// 发验证
			//System.out.println("发送邮箱验证");
			try {
				verSuccess = emailDr(email, req);
			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println("错误");
			}

		} else {
			// 发手机验证
			boolean checkPhoneExist = userService.checkAccountExist(phone);
			//System.out.println("发验证-手机存在" + checkPhoneExist);
			if (!checkPhoneExist) {
				msg = RespCode.NO_USER_EXIST;
				return RespUtils.build(msg);
			}
			// 发验证
			//System.out.println("发送短信验证");
			try {
				verSuccess = Dr(phone, req, response);
			} catch (Exception e) {
				e.printStackTrace();
				//System.err.println(e.getMessage());
			}
		}
		return RespUtils.buildOKWithDataYg(verSuccess);
	}

	@PostMapping(value = "/resetPwd")
	public RespObject<?> resetPwd(@RequestBody Map<String, String> map, HttpServletRequest req) {
		RespCode msg = RespCode.SUCCESS;
		if (map.get("email") == null && map.get("phoneNo") == null || map.get("verCode") == null) {
			msg = RespCode.NULL_ARGUMENT;
			return RespUtils.build(msg);
		}
		String email = map.get("email");
		String phone = map.get("phoneNo");
		String verCode = map.get("verCode");
		//System.out.println("========重置密码请求参数======\n邮箱:" + email + "\n手机:" + phone + "\n验证码" + verCode
//				+ "\n=========================");
		// 查询系统初始密码
		String originPwd = userService.queryParamByKey("originalPassword");
		//System.out.println("初始密码:" + originPwd);

		// 校对验证码
		// 校对验证码
		String vCode = email==null?(String) redisUtils.hget("vCode", phone):(String) redisUtils.hget("vCode", email);
		System.out.println("验证码:" + vCode);

		if (!map.get("verCode").equals(vCode)) {
			//System.out.println("验证码错误!!!");
			msg = RespCode.BAD_VERCODE;
			return RespUtils.build(msg);
		}

		// 更新密码
		boolean update = false;
		if (email != null) {
			// 检查账户存在
			boolean checkEmailExist = userService.checkAccountExist(email);
			//System.out.println("更新邮箱存在" + checkEmailExist);
			if (!checkEmailExist) {
				msg = RespCode.NO_USER_EXIST;
				return RespUtils.build(msg);
			}
			update = userService.updatePwd(email, originPwd);
		} else {
			// 检查账户存在
			boolean checkPhoneExist = userService.checkAccountExist(phone);
			//System.out.println("更新手机存在" + checkPhoneExist);
			if (!checkPhoneExist) {
				msg = RespCode.NO_USER_EXIST;
				return RespUtils.build(msg);
			}
			update = userService.updatePwd(phone, originPwd);
		}
		return RespUtils.buildOKWithDataYg(update);
	}

	@PostMapping(value = "/signOut")
	public RespObject<?> signOut(@RequestBody JSONObject object) {
		boolean flag = userService.addLogOffDate(object.get("loginLogId").toString());
		return RespUtils.buildOKWithDataYg(flag);
	}

	@PostMapping(value = "/updatePwd")
	public RespObject<?> updatePwd(@RequestBody JSONObject object) {
		RespCode msg = RespCode.SUCCESS;
		if (object == null || object.get("id") == null || object.get("userCode") == null
				|| object.get("oldPwd") == null && object.get("newPwd") == null) {
			msg = RespCode.NULL_ARGUMENT;
			return RespUtils.build(msg);
		}
		// 旧密码
		String reqPwd =  object.get("oldPwd").toString();

		// 新密码
		String newPwd = object.get("newPwd").toString();;

		// 0表示修改失败,1修改成功,2密码一样.4原密码不正确
		int flag = 0;
		// 取到数据用户
		User user = spUserService.selectSysPower(object.getString("id"));
		// 判断密码是否相等

		if (user.getPassword().equals(reqPwd) && reqPwd.equals(newPwd)) {
			flag = 2;
			//System.err.println("密码重复");
		} else {
			if (user.getPassword().equals(reqPwd)) {
				// 设置新密码
				user.setPassword(newPwd);
				// 调用更新方法
				flag = spUserService.updateSysPower(user);
				//System.err.println("修改密码");
			} else {
				flag = 4;
				//System.err.println("密码不正确");
			}
		}
		return RespUtils.buildOKWithDataYg(flag);
	}

	/**
	 * cookie中取值
	 * 
	 * @param cookies
	 * @param key
	 * @return
	 */
	private String getWebId(Cookie[] cookies, String key) {
		String value = null;
		if (null != cookies && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (key.equals(c.getName())) {
					value = c.getValue();
					break;
				}
			}
		}
		return value;
	}

	/**
	 * 发送邮箱验证码
	 * 
	 * @param email
	 * @param req
	 * @return
	 */
	public boolean emailDr(String email, HttpServletRequest req) {
		boolean success = true;
		Email e = emailService.find2("3");
		//System.err.println(e.toString());
		// 随机验证码
		String verCode = "";
		for (int i = 0; i < 6; i++) {
			verCode = verCode + (new Random().nextInt(9) + 1);
		}
		// 发送的内容 +加入验证码
		String content = e.getContent().replace("${password}", verCode);
		String[] emailAddress = new String[1];
		emailAddress[0] = email;
		EmailParameter emailParameter = new EmailParameter(emailAddress, e.getSubject(), content, e.getName(),
				e.getPassword());
		emailParameter.setHost("smtp.exmail.qq.com");
		emailParameter.setPort(465);
		emailParameter.setProtocol("smtp");
		System.err.println(emailParameter.toString());
		boolean flag = EmailUtils.sendSimpleMessage(emailParameter);
		//boolean flag =emailService.sendSimpleMessage(emailParameter);
		if (!flag) {
			success = false;
		} else {
			// 发送成功后，将验证码存入
			boolean lSet = redisUtils.hset("vCode", email, verCode, 60*10);

		}
		return success;
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param req
	 * @param information
	 * @return
	 * @throws ClientException
	 */
	public boolean Dr(String phoneNO, HttpServletRequest req, HttpServletResponse response) {
		boolean success = true;
		// 随机验证码
		String verCode = "";
		for (int i = 0; i < 6; i++) {
			verCode = verCode + (new Random().nextInt(9) + 1);
		}
		// 调用发送短信方法
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dest", phoneNO);
		map.put("content", verCode);
		boolean flag = smsService.sendSms(map);
		//System.out.println("====" + flag);
		if (!flag) {
			success = false;
		} else {
			// 发送成功后，将验证码存入
			boolean lSet = redisUtils.hset("vCode", phoneNO, verCode, 60*10);
			//String vCode =(String) redisUtils.hget("vCode", phoneNO);
			System.err.println(phoneNO+":"+lSet+":"+verCode);
		}
		return success;
	}

}
