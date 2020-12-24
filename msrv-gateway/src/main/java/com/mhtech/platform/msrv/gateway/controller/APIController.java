package com.mhtech.platform.msrv.gateway.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.gateway.utils.Utilis;
import com.mobile.model.CallRecords;
import com.mobile.service.AsynProcesService;
import com.mobile.service.CallRecordsService;
import com.mobile.service.ElementvaliDateService;
import com.mobile.service.MobileService;
import com.mobile.service.SMSCallbackRecordService;
import com.mobile.service.SMSService;
import com.mobile.utils.DateUtils;
import com.mobile.utils.IpUtil;
import com.mobile.utils.MobileLocationTool;

/**
 * @ClassName MobileLocationController
 * @Description TODO 手机定位及开通定位
 * @Author admini
 * @Date 2019/7/24 17:32
 * @Version 1.0
 */
@RestController
@CrossOrigin
@SuppressWarnings("all")
@RequestMapping(value = "/MobileApi/apiNew")
public class APIController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MobileService mobileLocationService;
	@Autowired
	private CallRecordsService callRecordsService;
	@Autowired
	private ElementvaliDateService elementvaliDateService;
	@Autowired
	private AsynProcesService asynProcesService;
	@Value("${customer.operator}")
	private String[] customerOperator;
	@Value("${customer.check}")
	private String[] customerCheck;

	/**
	 * 获取定位信息
	 * 
	 * @param request
	 * @param mobile
	 * @param customerId
	 * @return
	 */
	@PostMapping(value = "/APIposition")
	@ResponseBody
	public String position(@RequestBody Map<String, Object> map) {
		Object mobile = map.get("mobile");
		Object customerId = map.get("customerId");
		long currentTimeMillis = System.currentTimeMillis();
		Map<String, String> judgeOperator = MobileLocationTool.JudgeOperator(mobile.toString());
		Map msg = new HashMap();
		if (judgeOperator == null) {
			msg.put("resmsg", "手机号码错误");
			msg.put("mobile", mobile);
			msg.put("resid", "-1");
			String unicomPosition = JSON.toJSON(msg).toString();
			return unicomPosition;
		} else {
			String OperatorCode = judgeOperator.get("code");
			String OperatorName = judgeOperator.get("name");
			msg=checkMobile(mobile.toString(),customerId.toString(),OperatorName,OperatorCode);
			if(msg!=null&&!msg.isEmpty()) {
				String unicomPosition = JSON.toJSON(msg).toString();
				return unicomPosition;
			}
			msg.put("operator", OperatorCode);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("phone", mobile);
			param.put("OperatorCode", OperatorCode);
			param.put("OperatorName", OperatorName);
			param.put("customerId", customerId);
			String unicomPosition = mobileLocationService.APIposition(param);
			JSONObject jsonObject = JSON.parseObject(unicomPosition);// 返回值转json获取状态信息
			JSONObject jsonResult = JSON.parseObject(unicomPosition);// 返回值转json获取状态信息
			String resmsg = "";
			String resid = "";
			String originalLng = "";
			String originalLat = "";
			if (jsonObject != null) {
				resmsg = jsonObject.getString("resmsg");
				resid = jsonObject.getString("resid");
				originalLng = jsonObject.getString("original_lng");
				originalLat = jsonObject.getString("original_lat");
			}
			long currentTimeMillis1 = System.currentTimeMillis();
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerId.toString(), mobile.toString(),
					OperatorCode, "实时定位", "APIposition", "实时定位", DateUtils.getDateTime(), unicomPosition, resmsg, resid,
					originalLng, originalLat);
			callRecords.setResponse_time(currentTimeMillis1 - currentTimeMillis + "");
			int add = callRecordsService.add(callRecords);
			jsonResult.remove("original_lng");
			jsonResult.remove("original_lat");
			jsonResult.remove("OperatorCode");
			jsonResult.remove("OperatorName");
			return jsonResult.toJSONString();
		}
	}

	/**
	 * 定位授权开通
	 * 
	 * @param request
	 * @param mobile
	 * @param customerId
	 * @return
	 */
	@PostMapping(value = "/APIopenPosition")
	@ResponseBody
	public String openPosition(@RequestBody Map<String, Object> map) {
//		String customerip = IpUtil.getIpAddr(request);
		Object mobile = map.get("mobile");
		Object customerId = map.get("customerId");
		long currentTimeMillis = System.currentTimeMillis();
		Map msg = new HashMap();
		Map<String, String> judgeOperator = MobileLocationTool.JudgeOperator(mobile.toString());// 判断手机号运营商
		if (judgeOperator == null) {
			msg.put("resmsg", "手机号码错误");
			msg.put("mobile", mobile);
			msg.put("resid", "-1");
			String unicomPosition = JSON.toJSON(msg).toString();
			return unicomPosition;
		} else {
			String OperatorCode = judgeOperator.get("code");
			String OperatorName = judgeOperator.get("name");
			if ("01".equals(OperatorCode)) {
				msg.put("resmsg", "定位开通成功！");
				msg.put("mobile", mobile);
				msg.put("resid", "0");
				String unicomPosition = JSON.toJSON(msg).toString();
				CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerId.toString(), mobile.toString(),
						OperatorCode, "定位授权", "APIopenPosition", "定位授权", DateUtils.getDateTime(), unicomPosition,
						msg.get("resmsg").toString(), msg.get("resid").toString());
				long currentTimeMillis1 = System.currentTimeMillis();
				callRecords.setResponse_time(currentTimeMillis1 - currentTimeMillis + "");
				int add = callRecordsService.add(callRecords);
				return unicomPosition;
			} else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("phone", mobile);
				param.put("OperatorCode", OperatorCode);
				param.put("OperatorName", OperatorName);
				param.put("customerId", customerId);
				String unicomPosition = mobileLocationService.openPosition(param);
				JSONObject jsonObject = JSON.parseObject(unicomPosition);// 返回值转json获取状态信息
				JSONObject jsonResult = JSON.parseObject(unicomPosition);// 返回值转json获取状态信息
				String resmsg = "";
				String resid = "";
				if (jsonObject != null) {
					resmsg = jsonObject.getString("resmsg");
					resid = jsonObject.getString("resid");
				}
				long currentTimeMillis1 = System.currentTimeMillis();
				CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerId.toString(), mobile.toString(),
						OperatorCode, "定位授权", "APIopenPosition", "定位授权", DateUtils.getDateTime(), unicomPosition,
						resmsg, resid);
				callRecords.setResponse_time(currentTimeMillis1 - currentTimeMillis + "");
				int add = callRecordsService.add(callRecords);
				jsonResult.remove("OperatorCode");
				jsonResult.remove("OperatorName");
				return jsonResult.toJSONString();
			}
		}
	}

	/**
	 * 定位授权关闭
	 * 
	 * @param request
	 * @param mobile
	 * @param customerid
	 * @return
	 */
	@PostMapping(value = "/APIclosePosition")
	@ResponseBody
	public String closePosition(@RequestBody Map<String, Object> map) {
		Object mobile = map.get("mobile");
		Object customerId = map.get("customerId");
//		String customerip = IpUtil.getIpAddr(request);
		long currentTimeMillis = System.currentTimeMillis();
		Map msg = new HashMap();
		Map<String, String> judgeOperator = MobileLocationTool.JudgeOperator(mobile.toString());// 判断手机号运营商
		if (judgeOperator == null) {
			msg.put("resmsg", "手机号码错误");
			msg.put("mobile", mobile);
			msg.put("resid", "-1");
			String unicomPosition = JSON.toJSON(msg).toString();
			return unicomPosition;
		} else {
			String OperatorCode = judgeOperator.get("code");
			String OperatorName = judgeOperator.get("name");
			if ("01".equals(OperatorCode)) {
				msg.put("resmsg", "解绑成功");
				msg.put("mobile", mobile);
				msg.put("resid", "0");
				String unicomPosition = JSON.toJSON(msg).toString();
				CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerId.toString(), mobile.toString(),
						OperatorCode, "定位关闭", "APIclosePosition", "定位关闭", DateUtils.getDateTime(), unicomPosition,
						msg.get("resmsg").toString(), msg.get("resid").toString());
				long currentTimeMillis1 = System.currentTimeMillis();
				callRecords.setResponse_time(currentTimeMillis1 - currentTimeMillis + "");
				int add = callRecordsService.add(callRecords);
				return unicomPosition;
			} else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("phone", mobile);
				param.put("OperatorCode", OperatorCode);
				param.put("OperatorName", OperatorName);
				param.put("customerId", customerId);
				String unicomPosition = mobileLocationService.closePosition(param);
				JSONObject jsonObject = JSON.parseObject(unicomPosition);// 返回值转json获取状态信息
				JSONObject jsonResult = JSON.parseObject(unicomPosition);// 返回值转json获取状态信息

				String resmsg = "";
				String resid = "";
				if (jsonObject != null) {
					resmsg = jsonObject.getString("resmsg");
					resid = jsonObject.getString("resid");
				}
				CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerId.toString(), mobile.toString(),
						OperatorCode, "定位关闭", "APIclosePosition", "定位关闭", DateUtils.getDateTime(), unicomPosition,
						resmsg, resid);
				long currentTimeMillis1 = System.currentTimeMillis();
				callRecords.setResponse_time(currentTimeMillis1 - currentTimeMillis + "");
				int add = callRecordsService.add(callRecords);
				jsonResult.remove("OperatorCode");
				jsonResult.remove("OperatorName");
				return unicomPosition;
			}
		}
	}

	/**
	 * 手机定位状态
	 * 
	 * @param request
	 * @param mobile
	 * @param customerId
	 * @return
	 */
	@PostMapping(value = "/authlbsstatus")
	@ResponseBody
	public String authlbsstatus(@RequestBody Map<String, Object> map) {
//		String customerip = IpUtil.getIpAddr(request);
		Object mobile = map.get("mobile");
		Object customerId = map.get("customerId");
		long currentTimeMillis = System.currentTimeMillis();
		Map msg = new HashMap();
		Map<String, String> judgeOperator = MobileLocationTool.JudgeOperator(mobile.toString());// 判断手机号运营商
		if (judgeOperator == null) {
			msg.put("resmsg", "手机号码错误");
			msg.put("mobile", mobile);
			msg.put("resid", "-1");
			String unicomPosition = JSON.toJSON(msg).toString();
			return unicomPosition;
		} else {
			String OperatorCode = judgeOperator.get("code");
			String OperatorName = judgeOperator.get("name");
//			01 联通
			if ("01".equals(OperatorCode)) {
				msg.put("resmsg", "白名单用户,已开通定位");
				msg.put("mobile", mobile);
				msg.put("resid", "1");
				String unicomPosition = JSON.toJSON(msg).toString();
				JSONObject jsonObject = JSON.parseObject(unicomPosition);// 返回值转json获取状态信息
				String resmsg = "";
				String resid = "";
				if (jsonObject != null) {
					resmsg = jsonObject.getString("resmsg");
					resid = jsonObject.getString("resid");
				}
				long currentTimeMillis1 = System.currentTimeMillis();
				CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerId.toString(), mobile.toString(),
						OperatorCode, "手机定位状态", "authlbsstatus", "手机定位状态", DateUtils.getDateTime(), unicomPosition,
						resmsg, resid, (currentTimeMillis1 - currentTimeMillis) + "");
				int add = callRecordsService.add(callRecords);
				return unicomPosition;
			} else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("phone", mobile);
				param.put("OperatorCode", OperatorCode);
				param.put("OperatorName", OperatorName);
				param.put("customerId", customerId);
				String unicomPosition = mobileLocationService.authlbsstatus(param);
				JSONObject jsonObject = JSON.parseObject(unicomPosition);// 返回值转json获取状态信息
				JSONObject jsonResult = JSON.parseObject(unicomPosition);// 返回值转json获取状态信息
				String resmsg = "";
				String resid = "";
				if (jsonObject != null) {
					resmsg = jsonObject.getString("resmsg");
					resid = jsonObject.getString("resid");
				}
				long currentTimeMillis1 = System.currentTimeMillis();
				CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerId.toString(), mobile.toString(),
						OperatorCode, "手机定位状态", "authlbsstatus", "手机定位状态", DateUtils.getDateTime(), unicomPosition,
						resmsg, resid, (currentTimeMillis1 - currentTimeMillis) + "");
				int add = callRecordsService.add(callRecords);
				jsonResult.remove("OperatorCode");
				jsonResult.remove("OperatorName");
				return jsonResult.toJSONString();
			}
		}
	}

	/**
	 * 手机定位状态
	 * 
	 * @param request
	 * @param mobile
	 * @param customerId
	 * @return
	 */
	@PostMapping(value = "/APICarrierValidMd5")
	@ResponseBody
	public String APICarrierValidMd5(@RequestBody Map<String, Object> map) {
		Map msg = new HashMap<String, Object>();
		long currentTimeMillis = System.currentTimeMillis();
		msg.put("resid", "-11");
		if (map == null) {
			msg.put("resmsg", "参数不能为空");
			return JSON.toJSONString(msg);
		}
		if (map.get("mobile") == null || map.get("mobile").toString().isEmpty()) {
			msg.put("resmsg", "手机号不能为空");
			return JSON.toJSONString(msg);
		}
		if (map.get("name") == null || map.get("name").toString().isEmpty()) {
			msg.put("resmsg", "姓名不能为空");
			return JSON.toJSONString(msg);
		}
		if (map.get("idcard") == null || map.get("idcard").toString().isEmpty()) {
			msg.put("resmsg", "身份证号不能为空");
			return JSON.toJSONString(msg);
		}
		if (map.get("customerId") == null || map.get("customerId").toString().isEmpty()) {
			msg.put("resmsg", "客户标识不能为空");
			return JSON.toJSONString(msg);
		}
		String mobile = map.get("mobile").toString();
		String name = map.get("name").toString();
		String idcard = map.get("idcard").toString();
		String customerId = map.get("customerId").toString();
		Map<String, String> parmat = new HashMap<>();
		parmat.put("customerId", customerId);
		parmat.put("mobile", mobile);
		parmat.put("name", name);
		parmat.put("idcard", idcard);
		parmat.put("customerStatus", "0");
		try {
			String threedimensionvalidate = mobileLocationService.APICarrierValidMd5(parmat);
			JSONObject jsonObject = JSON.parseObject(threedimensionvalidate);// 返回值转json获取状态信息
			Object object = jsonObject.get("data");
			String resmsg = "";
			String resid = "";
			String originalLng = "";
			String originalLat = "";
			boolean isUpRem = false;
			if (object != null && !object.toString().isEmpty()) {
				JSONObject jsonData = JSON.parseObject(object.toString());
				resmsg = jsonData.getString("checkMessage");
				resid = jsonData.getString("resid");
			} else {
				resid = jsonObject.get("resid").toString();
				resmsg = jsonObject.get("resmsg").toString();
			}
			long currentTimeMillis1 = System.currentTimeMillis();
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerId, mobile, "APICarrierValidMd5",
					"三要素验证", "APICarrierValidMd5", "三要素验证", DateUtils.getDateTime(), jsonObject.toJSONString(), resmsg,
					resid, (currentTimeMillis1 - currentTimeMillis) + "");
			int add = callRecordsService.add(callRecords);
			return jsonObject.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			msg.put("resid", "-30");
			msg.put("mobile", mobile);
			msg.put("name", name);
			msg.put("idcard", idcard);
			msg.put("resmsg", "系统错误,请联系管理员");
		}
		return JSON.toJSONString(msg);
	}

	/**
	 * 手机短信信息发送
	 * 
	 * @param map
	 * @return
	 ** @param mobile    手机号码（多个号码用“逗号”分开，GET方式最多50个号码，POST方式最多1000个号码）
	 * @param content   短信内容。最多500个字符
	 * @param signature 签名
	 */
	@PostMapping(value = "/APIsendSms")
	@ResponseBody
	public String APIsendSms(@RequestBody Map<String, Object> map) {
		Map msg = new HashMap<String, Object>();
		long currentTimeMillis = System.currentTimeMillis();
		msg.put("resid", "-11");
		if (map == null) {
			msg.put("resmsg", "参数不能为空");
			return JSON.toJSONString(msg);
		}
		if (map.get("mobile") == null || map.get("mobile").toString().isEmpty()) {
			msg.put("resmsg", "手机号不能为空");
			return JSON.toJSONString(msg);
		}
		if (map.get("content") == null || map.get("content").toString().isEmpty()) {
			msg.put("resmsg", "短信内容不能为空");
			return JSON.toJSONString(msg);
		}
		if (map.get("signature") == null || map.get("signature").toString().isEmpty()) {
			msg.put("resmsg", "签名不能为空");
			return JSON.toJSONString(msg);
		}
		if (map.get("customerId") == null || map.get("customerId").toString().isEmpty()) {
			msg.put("resmsg", "客户标识不能为空");
			return JSON.toJSONString(msg);
		}
		try {
			String apIsendSms = mobileLocationService.APIsendSms(map);
			JSONObject jsonObject = JSON.parseObject(apIsendSms);// 返回值转json获取状态信息
			long currentTimeMillis1 = System.currentTimeMillis();
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), map.get("customerId").toString(),
					map.get("mobile").toString(), "APIsendSms", "短信发送", "APIsendSms", "短信发送", DateUtils.getDateTime(),
					jsonObject.toJSONString(), jsonObject.get("resmsg").toString(), jsonObject.get("resid").toString(),
					(currentTimeMillis1 - currentTimeMillis) + "");
			int add = callRecordsService.add(callRecords);
			return apIsendSms;
		} catch (Exception e) {
			msg.put("resid", "-30");
			msg.put("resmsg", "系统错误,请联系管理员");
		}
		return JSON.toJSONString(msg);
	}

	/**
	 * 手机短信信息发送
	 * 
	 * @param map
	 * @return
	 ** @param mobile    手机号码（多个号码用“逗号”分开，GET方式最多50个号码，POST方式最多1000个号码）
	 * @param content   短信内容。最多500个字符
	 * @param signature 签名
	 */
	@GetMapping(value = "/callback")
	@ResponseBody
	public void callback(@RequestParam(name = "mobileno", required = false) String mobileno,
			@RequestParam(name = "action", required = false) String action,
			@RequestParam(name = "sign", required = false) String sign,
			@RequestParam(name = "msg", required = false) String msg,
			@RequestParam(name = "content", required = false) String content) {
		log.info("mobileno:{},action:{},sign:{},msg:{},content:{}", mobileno, action, sign, msg, content);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mobileno", mobileno);
		param.put("action", action);
		param.put("sign", sign);
		param.put("msg", msg);
		param.put("content", content);
		asynProcesService.SMSCallback(param);
	}
	/**
	 * 判断客户是否有运营商定位权限
	 * @param mobile
	 * @param customerId
	 * @param OperatorName
	 * @param OperatorCode
	 * @return
	 */
	private Map checkMobile(String mobile, String customerId, String OperatorName, String OperatorCode) {
		boolean isCustomer = false;
		boolean isOperator = true;
		for (int i = 0; customerCheck != null && i < customerCheck.length; i++) {
			String customer = customerCheck[i];
			if (customerId.equals(customer)) {
				isCustomer = true;
				if (i < customerOperator.length) {
					String operator = customerOperator[i];
					String operators[] = operator.split(";");
					for (String ot : operators) {
						if (OperatorCode.equals(ot)) {
							isOperator = false;
							break;
						}
					}
				}
				break;
			}
		}
		Map msg = new HashMap();
		if (isCustomer && isOperator) {
			msg.put("resmsg", "暂不支持运营商【"+OperatorName+"】定位！");
			msg.put("mobile", mobile);
			msg.put("resid", "-50");
		}
		return msg;
	}
}
