package com.mhtech.platform.msrv.gateway.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.Utilis;
import com.mobile.model.CallRecords;
import com.mobile.model.CustomerMobiles;
import com.mobile.service.CallRecordsService;
import com.mobile.service.CustomerMobilesService;
import com.mobile.service.CustomerPackageService;
import com.mobile.service.CustomerService;
import com.mobile.service.ElementvaliDateService;
import com.mobile.service.MobileService;
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
@RequestMapping(value = "/MobileApi/api")
public class MobileLocationController {
//	@Reference(version = "1.0.0")
	@Autowired
	private MobileService mobileLocationService;
//	@Reference(version = "1.0.0")
	@Autowired
	private CustomerMobilesService customerMobilesService;
//	@Reference(version = "1.0.0")
	@Autowired
	private CustomerService customerService;
//	@Reference(version = "1.0.0")
	@Autowired
	private CallRecordsService callRecordsService;
//	@Reference(version = "1.0.0")
	@Autowired
	private ElementvaliDateService elementvaliDateService;
//	@Reference(version = "1.0.0")
	@Autowired
	private CustomerPackageService customerPackageService;
	
	private final String  regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	private final Logger log = LoggerFactory.getLogger(MobileLocationController.class);

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 手机定位
	 * 
	 * @Date 14:01 2019/8/28
	 * 
	 * @Param [mobile, customerid, customerip]
	 * 
	 * @return java.lang.String
	 **/
	@GetMapping(value = "/position")
	@ResponseBody
	public String position(HttpServletRequest request, @RequestParam("mobile") String mobile,
			@RequestParam("customerid") String customerid) {
		String customerip = IpUtil.getIpAddr(request);
		
		Map<String, String> parmat = new HashMap<>();
		parmat.put("customer_id", customerid);
		parmat.put("mobile", mobile);
		parmat.put("customer_ip", customerip);
		parmat.put("customer_status", "0");
		Map msg = new HashMap();
//		String msgIfCk=InformationCheck(parmat,mobile,customerid,"position",true);
//		//客户校验
//		if(msgIfCk!=null&&!msgIfCk.trim().isEmpty()) {
//			log.warn(msgIfCk);
//			return msgIfCk;
//		}
		String Operator = MobileLocationTool.Judge_Operator(mobile);
		String str_msg = "";
		// 移动、电信手机号调用定位
		if ("00".equals(Operator) || "02".equals(Operator)) {
			msg.put("operator", Operator);
			String mobile_type = "";
			if ("00".equals(Operator))
				mobile_type = "移动";
			else
				mobile_type = "电信";
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("phone", mobile);
			param.put("OperatorCode", Operator);
			param.put("OperatorName", mobile_type);
			
			String unicomPosition = mobileLocationService.getPosition(param);
			JSONObject jsonObject=JSON.parseObject(unicomPosition);//返回值转json获取状态信息
			String resmsg="";
		    String resid="";
		    String originalLng="";
		    String originalLat="";
		    if(jsonObject!=null) {
		    	resmsg=jsonObject.getString("resmsg");
		    	resid=jsonObject.getString("resid");
		    	originalLng=jsonObject.getString("original_lng");
		    	originalLat=jsonObject.getString("original_lat");
		    }
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobile, Operator, "手机号定位",
					"position", "手机定位", DateUtils.getDateTime(), unicomPosition,resmsg,resid,originalLng,originalLat);
			int add = callRecordsService.add(callRecords);
			return unicomPosition;
		} else if ("01".equals(Operator)) {// 联通调用定位
			msg.put("operator", Operator);
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("phone", mobile);
			param.put("OperatorCode", Operator);
			param.put("OperatorName", "联通");
			String unicomPosition = mobileLocationService.getUnicomPosition(param);
			JSONObject jsonObject=JSON.parseObject(unicomPosition);//返回值转json获取状态信息
			String resmsg="";
		    String resid="";
		    String originalLng="";
		    String originalLat="";
		    if(jsonObject!=null) {
		    	resmsg=jsonObject.getString("resmsg");
		    	resid=jsonObject.getString("resid");
		    	originalLng=jsonObject.getString("original_lng");
		    	originalLat=jsonObject.getString("original_lat");
		    }
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobile, Operator, "手机号定位",
					"position", "手机定位", DateUtils.getDateTime(), unicomPosition,resmsg,resid,originalLng,originalLat);
			int add = callRecordsService.add(callRecords);
			return unicomPosition;
			// str_msg = mobileLocationTool.getUnicomPosition(mobile);
		} else {
			msg.put("resid", "-30");
			msg.put("mobile", mobile);
			msg.put("resmsg", "手机号不合法，不属于有效的手机号");
		}		
//		String position = mobileLocationService.getPosition(mobile, customerid, customerip);
		return JSON.toJSON(msg).toString();
	}

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 开通手机定位授权
	 * 
	 * @Date 14:01 2019/8/28
	 * 
	 * @Param [mobile, customerid, customerip]
	 * 
	 * @return java.lang.String
	 **/
	@GetMapping(value = "/openPosition")
	@ResponseBody
	public String openPosition(HttpServletRequest request, @RequestParam("mobile") String mobile,
			@RequestParam("customerid") String customerid) {
		String customerip = IpUtil.getIpAddr(request);
		Map<String, String> parmat = new HashMap<>();
		parmat.put("customer_id", customerid);
		parmat.put("mobile", mobile);
		parmat.put("customer_ip", customerip);
		parmat.put("customer_status", "0");
		Map msg = new HashMap();
//		String msgIfCk=InformationCheck(parmat,mobile,customerid,"operPosition",true);
//		//客户校验
//		if(msgIfCk!=null&&!msgIfCk.trim().isEmpty()) {
//			log.warn(msgIfCk);
//			return msgIfCk;
//		}
		String Operator = MobileLocationTool.Judge_Operator(mobile);// 判断手机号运营商
		if ("00".equals(Operator) || "02".equals(Operator)) {// 移动、电信手机号定位授权
			String mobile_type = "";
			if ("00".equals(Operator))
				mobile_type = "移动";
			else
				mobile_type = "电信";
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("phone", mobile);
			param.put("OperatorCode", Operator);
			param.put("OperatorName", mobile_type);
			String unicomPosition = mobileLocationService.openPosition(param);
			JSONObject jsonObject=JSON.parseObject(unicomPosition);//返回值转json获取状态信息
			String resmsg="";
		    String resid="";
		    if(jsonObject!=null) {
		    	resmsg=jsonObject.getString("resmsg");
		    	resid=jsonObject.getString("resid");
		    }
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobile, Operator, "手机号定位授权",
					"openPosition", "手机定位授权", DateUtils.getDateTime(), unicomPosition,resmsg,resid);
			int add = callRecordsService.add(callRecords);
			return unicomPosition;
		} else {
			msg.put("resmsg", "当前手机号已在白名单内");
			msg.put("mobile", mobile);
			msg.put("resid", "0");
			String unicomPosition = JSON.toJSON(msg).toString();
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobile, Operator, "手机号定位授权",
					"openPosition", "手机定位授权", DateUtils.getDateTime(), unicomPosition,msg.get("resmsg").toString(),msg.get("resid").toString());
			int add = callRecordsService.add(callRecords);
			return unicomPosition;
		}
		
//		
//		String position = mobileLocationService.openPosition(mobile, customerid, customerip);
//		return position;
	}

	/**
	 * 根据客户编码+客户ip 当前客户手机号状态正常 手机号开通授权
	 * 
	 * @param request
	 * @param customerip
	 * @param customerid
	 * @return
	 */
	@GetMapping(value = "/sysOpenPosition")
	@ResponseBody
	public RespObject<?> sysOpenPosition(HttpServletRequest request, @RequestParam("customerip") String customerip,
			@RequestParam("customerid") String customerid) {
//        String customerip=IpUtil.getIpAddr(request);
		if (customerip.trim().isEmpty()) {
			customerip = IpUtil.getIpAddr(request);
		}
		log.info(customerip);
		boolean success = true;
		RespCode msg = RespCode.SUCCESS;

		if (customerid.trim().isEmpty() || customerip.trim().isEmpty()) {
			msg = RespCode.NULL_ARGUMENT;
			return RespUtils.build(msg);
		}
		Map<String, String> map = new HashMap<String, String>();
//        map.put("customerid", "13036ecd2d2845ad");
		map.put("customerid", customerid);
		map.put("state", "0");
		List<Map<String, String>> maps = customerMobilesService.findCustomerMobilesList(map);
		if (maps == null || maps.size() == 0) {
			success = false;
			msg = RespCode.NULL_DATE;
			return RespUtils.build(msg);
		}
		for (Map<String, String> mapt : maps) {
			String mobileno = mapt.get("mobileno");
			
			Map<String, String> parmat = new HashMap<>();
			parmat.put("customer_id", customerid);
			parmat.put("mobile", mobileno);
			parmat.put("customer_ip", customerip);
			parmat.put("customer_status", "0");
			
			String Operator = MobileLocationTool.Judge_Operator(mobileno);// 判断手机号运营商
			if ("00".equals(Operator) || "02".equals(Operator)) {// 移动、电信手机号定位授权
				String mobile_type = "";
				if ("00".equals(Operator))
					mobile_type = "移动";
				else
					mobile_type = "电信";
				Map<String, Object> param=new HashMap<String, Object>();
				param.put("phone", mobileno);
				param.put("OperatorCode", Operator);
				param.put("OperatorName", mobile_type);
				String unicomPosition = mobileLocationService.openPosition(param);
				JSONObject jsonObject=JSON.parseObject(unicomPosition);//返回值转json获取状态信息
				String resmsg="";
			    String resid="";
			    if(jsonObject!=null) {
			    	resmsg=jsonObject.getString("resmsg");
			    	resid=jsonObject.getString("resid");
			    }
				CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobileno, Operator, "手机号定位授权",
						"openPosition", "手机定位授权", DateUtils.getDateTime(), unicomPosition,resmsg,resid);
				int add = callRecordsService.add(callRecords);
			} else {
				Map<String,Object> msgx=new HashMap<String, Object>();
				msgx.put("resmsg", "当前手机号已在白名单内");
				msgx.put("mobile", mobileno);
				msgx.put("resid", "0");
				String unicomPosition = JSON.toJSON(msg).toString();
				CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobileno, Operator, "手机号定位授权",
						"openPosition", "手机定位授权", DateUtils.getDateTime(), unicomPosition,msgx.get("resmsg").toString(),msgx.get("resid").toString());
				int add = callRecordsService.add(callRecords);
			}
			
//			String position = mobileLocationService.openPosition(mobileno, customerid, customerip);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		RespObject<?> build = RespUtils.build(msg);
		build.setSuccess(true);
		return build;
	}

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 定位关闭
	 * 
	 * @Date 14:01 2019/8/28
	 * 
	 * @Param [mobile, customerid, customerip]
	 * 
	 * @return java.lang.String
	 **/
	@GetMapping(value = "/closePosition")
	@ResponseBody
	public String closePosition(HttpServletRequest request, @RequestParam("mobile") String mobile,
			@RequestParam("customerid") String customerid) {
		String customerip = IpUtil.getIpAddr(request);
		
		Map<String, String> parmat = new HashMap<>();
		parmat.put("customer_id", customerid);
		parmat.put("mobile", mobile);
		parmat.put("customer_ip", customerip);
		parmat.put("customer_status", "0");
		Map msg = new HashMap();
//		String msgIfCk=InformationCheck(parmat,mobile,customerid,"closePosition",true);
//		//客户校验
//		if(msgIfCk!=null&&!msgIfCk.trim().isEmpty()) {
//			log.warn(msgIfCk);
//			return msgIfCk;
//		}
		String Operator = MobileLocationTool.Judge_Operator(mobile);// 判断手机号运营商
		if ("00".equals(Operator) || "02".equals(Operator)) {// 移动、电信手机号定位授权
			String mobile_type = "";
			if ("00".equals(Operator))
				mobile_type = "移动";
			else
				mobile_type = "电信";
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("phone", mobile);
			param.put("OperatorCode", Operator);
			param.put("OperatorName", mobile_type);
			String unicomPosition = mobileLocationService.closePosition(param);
			JSONObject jsonObject=JSON.parseObject(unicomPosition);//返回值转json获取状态信息
			String resmsg="";
		    String resid="";
		    if(jsonObject!=null) {
		    	resmsg=jsonObject.getString("resmsg");
		    	resid=jsonObject.getString("resid");
		    }
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobile, Operator, "手机号定位关闭",
					"closePosition", "手机定位关闭", DateUtils.getDateTime(), unicomPosition,resmsg,resid);
			int add = callRecordsService.add(callRecords);
			return unicomPosition;
		} else {
			msg.put("resmsg", "解绑成功");
			msg.put("mobile", mobile);
			msg.put("resid", "0");
			String unicomPosition = JSON.toJSON(msg).toString();
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobile, Operator, "手机号定位关闭",
					"closePosition", "手机定位关闭", DateUtils.getDateTime(), unicomPosition,msg.get("resmsg").toString(),msg.get("resid").toString());
			int add = callRecordsService.add(callRecords);
			return unicomPosition;
		}
		
//		
//		String position = mobileLocationService.closePosition(mobile, customerid, customerip);
//		return position;
	}

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 获取定位状态
	 * 
	 * @Date 14:01 2019/8/28
	 * 
	 * @Param [mobile, customerid, customerip]
	 * 
	 * @return java.lang.String
	 **/
	@GetMapping(value = "/authlbsstatus")
	@ResponseBody
	public String authlbsstatus(HttpServletRequest request, @RequestParam("mobile") String mobile,
			@RequestParam("customerid") String customerid) {
		String customerip = IpUtil.getIpAddr(request);
		
		Map<String, String> parmat = new HashMap<>();
		parmat.put("customer_id", customerid);
		parmat.put("mobile", mobile);
		parmat.put("customer_ip", customerip);
		parmat.put("customer_status", "0");
		Map msg = new HashMap();
//		String msgIfCk=InformationCheck(parmat,mobile,customerid,"authlbsstatus",true);
//		//客户校验
//		if(msgIfCk!=null&&!msgIfCk.trim().isEmpty()) {
//			log.warn(msgIfCk);
//			return msgIfCk;
//		}
		String Operator = MobileLocationTool.Judge_Operator(mobile);// 判断手机号运营商
		if ("00".equals(Operator) || "02".equals(Operator)) {// 移动、电信手机号定位授权
			String mobile_type = "";
			if ("00".equals(Operator))
				mobile_type = "移动";
			else
				mobile_type = "电信";
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("phone", mobile);
			param.put("OperatorCode", Operator);
			param.put("OperatorName", mobile_type);
			String unicomPosition = mobileLocationService.getStatus(param);
			JSONObject jsonObject=JSON.parseObject(unicomPosition);//返回值转json获取状态信息
			String resmsg="";
		    String resid="";
		    if(jsonObject!=null) {
		    	resmsg=jsonObject.getString("resmsg");
		    	resid=jsonObject.getString("resid");
		    }
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobile, Operator, "手机号状态",
					"getStatus", "手机号状态", DateUtils.getDateTime(), unicomPosition,resmsg,resid);
			int add = callRecordsService.add(callRecords);
			return unicomPosition;
		} else {
			msg.put("resmsg", "白名单用户,已开通定位");
			msg.put("mobile", mobile);
			msg.put("resid", "1");
			String unicomPosition = JSON.toJSON(msg).toString();
			JSONObject jsonObject=JSON.parseObject(unicomPosition);//返回值转json获取状态信息
			String resmsg="";
		    String resid="";
		    if(jsonObject!=null) {
		    	resmsg=jsonObject.getString("resmsg");
		    	resid=jsonObject.getString("resid");
		    }
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobile, Operator, "手机号状态",
					"getStatus", "手机号状态", DateUtils.getDateTime(), unicomPosition,resmsg,resid);
			int add = callRecordsService.add(callRecords);
			return unicomPosition;
		}
//		
//		String position = mobileLocationService.getStatus(mobile, customerid, customerip);
//		return position;
	}

	/**
	 * 输入客户编码+ip地址获取当前客户下手机号状态正常 所有定位授权开通状态信息
	 * 
	 * @param request
	 * @param customerip
	 * @param customerid
	 * @return
	 */
	@GetMapping(value = "/sysAuthlbsstatus")
	@ResponseBody
	public RespObject<?> sysAuthlbsstatus(HttpServletRequest request, @RequestParam("customerip") String customerip,
			@RequestParam("customerid") String customerid) {
		RespCode msg = RespCode.SUCCESS;
		if (customerid.trim().isEmpty() || customerip.trim().isEmpty()) {
			msg = RespCode.NULL_ARGUMENT;
			return RespUtils.build(msg);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("customerid", customerid);
		map.put("state", "0");
		List<Map<String, String>> maps = customerMobilesService.findCustomerMobilesList(map);
		if (maps == null || maps.size() == 0) {
			msg = RespCode.NULL_DATE;
			return RespUtils.build(msg);
		}
		List data = new ArrayList<>();
		for (Map<String, String> mapt : maps) {
			String mobileno = mapt.get("mobileno");
			String unicomPosition="";
			Map<String, String> parmat = new HashMap<>();
			parmat.put("customer_id", customerid);
			parmat.put("mobile", mobileno);
			parmat.put("customer_ip", customerip);
			parmat.put("customer_status", "0");
			
			String Operator = MobileLocationTool.Judge_Operator(mobileno);// 判断手机号运营商
			if ("00".equals(Operator) || "02".equals(Operator)) {// 移动、电信手机号定位授权
				String mobile_type = "";
				if ("00".equals(Operator))
					mobile_type = "移动";
				else
					mobile_type = "电信";
				
				Map<String, Object> param=new HashMap<String, Object>();
				param.put("phone", mobileno);
				param.put("OperatorCode", Operator);
				param.put("OperatorName", mobile_type);
				
				unicomPosition = mobileLocationService.getStatus(param);
				JSONObject jsonObject=JSON.parseObject(unicomPosition);//返回值转json获取状态信息
				String resmsg="";
			    String resid="";
			    if(jsonObject!=null) {
			    	resmsg=jsonObject.getString("resmsg");
			    	resid=jsonObject.getString("resid");
			    }
				CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobileno, Operator, "手机号状态",
						"getStatus", "手机号状态", DateUtils.getDateTime(), unicomPosition,resmsg,resid);
				int add = callRecordsService.add(callRecords);
			} else {
				Map<String,Object> msgx=new HashMap<String, Object>();
				msgx.put("resmsg", "白名单用户,已开通定位");
				msgx.put("mobile", mobileno);
				msgx.put("resid", "1");
				unicomPosition = JSON.toJSON(msg).toString();
//				JSONObject jsonObject=JSON.parseObject(unicomPosition);//返回值转json获取状态信息
				
				CallRecords callRecords = new CallRecords(Utilis.getUUID(), customerid, mobileno, Operator, "手机号状态",
						"getStatus", "手机号状态", DateUtils.getDateTime(), unicomPosition,msgx.get("resmsg").toString(),map.get("resid").toString());
				int add = callRecordsService.add(callRecords);
			}
//			String position = mobileLocationService.getStatus(mobileno, customerid, customerip);
			data.add(JSON.parseObject(unicomPosition));
		}
		return RespUtils.buildOKWithDataYg(data);
	}

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 三要素验证
	 * 
	 * @Date 14:01 
	 * 
	 * @Param [mobile, customerid, customerip]
	 * 
	 * @return java.lang.String
	 **/
	@PostMapping(value = "/threedimensionvalidateMD5")
	@ResponseBody
	public String threedimensionvalidateMD5(HttpServletRequest request, @RequestBody Map map) {
		String customerip = IpUtil.getIpAddr(request);
		log.info(customerip);
		Map msg = new HashMap<String, Object>();
		msg.put("code", "-11");

		if (map == null) {
			msg.put("msg", "参数不能为空");
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
		if (map.get("customerid") == null || map.get("customerid").toString().isEmpty()) {
			msg.put("resmsg", "客户标识不能为空");
			return JSON.toJSONString(msg);
		}
		String mobile = map.get("mobile").toString();
		String name = map.get("name").toString();
		String idcard = map.get("idcard").toString();
		String customer_id = map.get("customerid").toString();
		String customer_ip = customerip;
		
		Map<String, String> parmat = new HashMap<>();
		parmat.put("customer_id", customer_id);
		parmat.put("mobile", mobile);
		parmat.put("name", name);
		parmat.put("idcard", idcard);
		parmat.put("customer_ip", customer_ip);
		parmat.put("customer_status", "0");
		
//		String msgIfCk=InformationCheck(parmat,mobile,customer_id,"threedimensionvalidate",false);
//		//客户校验
//		if(msgIfCk!=null&&!msgIfCk.trim().isEmpty()) {
//			JSONObject jsonObject=JSON.parseObject(msgIfCk);//返回值转json获取状态信息
////			JSONObject jsonObject=null;
//			if(jsonObject.get("resmsg")!=null) {
//				jsonObject.put("msg", jsonObject.get("resmsg"));
//				jsonObject.put("code", jsonObject.get("resid"));
//				jsonObject.put("name", parmat.get("name"));
//				jsonObject.put("idcard", parmat.get("idcard"));
//				jsonObject.remove("resmsg");
//				jsonObject.remove("resid");
//			}
//			log.warn(jsonObject.toJSONString());
//			return jsonObject.toJSONString();
//		}
		try {
			String threedimensionvalidate = elementvaliDateService.threedimensionvalidateMD5(parmat);
			JSONObject jsonObject=JSON.parseObject(threedimensionvalidate);//返回值转json获取状态信息
			Object object = jsonObject.get("data");
			String resmsg="";
			String resid="";
			String originalLng="";
			String originalLat="";
			boolean isUpRem=false;
			if(object!=null&&!object.toString().isEmpty()) {
				JSONObject jsonData=JSON.parseObject(object.toString());
				resmsg=jsonData.getString("validateMsg");
				resid=jsonData.getString("validateCode");
				jsonObject.put("code",resid);
				jsonObject.put("msg", resmsg);
				isUpRem=true;
			}else {
				resid=jsonObject.get("code").toString();
				resmsg=jsonObject.get("msg").toString();
			}
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customer_id, mobile, "", "三要素验证",
					"threedimensionvalidate", "三要素验证", DateUtils.getDateTime(), threedimensionvalidate,resmsg,resid,originalLng,originalLat);
			int add = callRecordsService.add(callRecords);
			if(isUpRem) {
//				int updateCustomerInterfaceForRemaining = customerInterfaceDao.updateCustomerInterfaceForRemaining(customer_id,"threedimensionvalidate");
				int updateTotal = customerPackageService.updateTotal(customer_id);
			}
			return threedimensionvalidate;
		} catch (Exception e) {
			e.printStackTrace();
			msg.put("code", "-30");
			msg.put("mobile", mobile);
			msg.put("name", name);
			msg.put("idcard", idcard);
			msg.put("msg", "系统错误,请联系管理员");
		}
		
//		String position = mobileLocationService.threedimensionvalidate(mobile, name, idcard, customer_id, customer_ip);
		return JSON.toJSONString(msg);
	}

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 二要素验证
	 * 
	 * @Date 
	 * 
	 * @Param [mobile, customerid, customerip]
	 * 
	 * @return java.lang.String
	 **/
	@PostMapping(value = "/twoelementvalidteMD5")
	@ResponseBody
	public String twoelementvalidteMD5(HttpServletRequest request, @RequestBody Map map) {
		String customerip = IpUtil.getIpAddr(request);
		log.info(customerip);
		Map msg = new HashMap<String, Object>();
		msg.put("code", "-11");

		if (map == null) {
			msg.put("msg", "参数不能为空");
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
		if (map.get("customerid") == null || map.get("customerid").toString().isEmpty()) {
			msg.put("resmsg", "客户标识不能为空");
			return JSON.toJSONString(msg);
		}
		String mobile = map.get("mobile").toString();
		String name = map.get("name").toString();
		String customer_id = map.get("customerid").toString();
		String customer_ip = customerip;
		
		
		Map<String, String> parmat = new HashMap<>();
		parmat.put("customer_id", customer_id);
		parmat.put("mobile", mobile);
		parmat.put("name", name);
		parmat.put("customer_ip", customer_ip);
		parmat.put("customer_status", "0");
		
//		String msgIfCk=InformationCheck(parmat,mobile,customer_id,"twoelementvalidte",false);
//		//客户校验
//		if(msgIfCk!=null&&!msgIfCk.trim().isEmpty()) {
//			JSONObject jsonObject=JSON.parseObject(msgIfCk);//返回值转json获取状态信息
////			JSONObject jsonObject=null;
//			if(jsonObject.get("resmsg")!=null) {
//				jsonObject.put("msg", jsonObject.get("resmsg"));
//				jsonObject.put("code", jsonObject.get("resid"));
//				jsonObject.put("name", parmat.get("name"));
//				jsonObject.remove("resmsg");
//				jsonObject.remove("resid");
//			}
//			log.warn(jsonObject.toJSONString());
//			return jsonObject.toJSONString();
//		}
		try {
			 String twoelementvalidte = elementvaliDateService.twoelementvalidteMD5(parmat);
			JSONObject jsonObject=JSON.parseObject(twoelementvalidte);//返回值转json获取状态信息
			Object object = jsonObject.get("data");
			String resmsg="";
			String resid="";
			String originalLng="";
			String originalLat="";
			boolean isUpRem=false;
			if(object!=null&&!object.toString().isEmpty()) {
				JSONObject jsonData=JSON.parseObject(object.toString());
				resmsg=jsonData.getString("validateMsg");
				resid=jsonData.getString("validateCode");
				jsonObject.put("code",resid);
				jsonObject.put("msg", resmsg);
				isUpRem=true;
			}else {
				resid=jsonObject.get("code").toString();
				resmsg=jsonObject.get("msg").toString();
			}
			CallRecords callRecords = new CallRecords(Utilis.getUUID(), customer_id, mobile, "", "二要素验证",
					"twoelementvalidte", "二要素验证", DateUtils.getDateTime(), twoelementvalidte,resmsg,resid,originalLng,originalLat);
			int add = callRecordsService.add(callRecords);
			if(isUpRem) {
//				int updateCustomerInterfaceForRemaining = customerInterfaceDao.updateCustomerInterfaceForRemaining(customer_id,"threedimensionvalidate");
				int updateTotal = customerPackageService.updateTotal(customer_id);
			}
			return twoelementvalidte;
		} catch (Exception e) {
			e.printStackTrace();
			msg.put("code", "-30");
			msg.put("mobile", mobile);
			msg.put("name", name);
			msg.put("msg", "系统错误,请联系管理员");
		}
		
//		String position = mobileLocationService.twoelementvalidte(mobile, name, customer_id, customer_ip);
		return JSON.toJSON(msg).toString();
	}
	
	/**
	 * 获取调用方ip地址
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
//	@RequestMapping(value = "/getIP")
//	@ResponseBody
//	public String getIP(HttpServletRequest request, ModelMap map) {
//		return IpUtil.getIpAddr(request);
//	}
	/**
	 * 客户相关信息校验
	 * @param parmat
	 * @param mobile
	 * @param customer_id
	 * @param type
	 * @param isPerform 是否校验手机号是否存在
	 * @return
	 */
	private String InformationCheck(Map<String, String> parmat,String mobile,String customer_id,String type,boolean isPerform) {
		int customerCount = customerService.findCustomerCount(parmat);
		Map msg = new HashMap();
		
		 Pattern p = Pattern.compile(regex);
         Matcher m = p.matcher(mobile);
         boolean isMatch = m.matches();
         if (isPerform&&!isMatch) {
        	msg.put("mobile", mobile);
 			msg.put("resmsg", "手机号错误");
 			msg.put("resid", "-11");
 			return JSON.toJSON(msg).toString();
         }
		
		if (customerCount == 0) {
			msg.put("mobile", mobile);
			msg.put("resmsg", "客户不存在或IP地址错误，请联系管理员");
			msg.put("resid", "-11");
			return JSON.toJSON(msg).toString();
		}

		int customerPermission = customerService.findCustomerPermission(customer_id, type);// 查看是否有权限
		if (customerPermission == 0) {
			msg.put("mobile", mobile);
			msg.put("resmsg", "您没有购买当前产品");
			msg.put("resid", "-40");
			return JSON.toJSON(msg).toString();
		}
		//二要素排除移动号
		if(!isPerform&&"twoelementvalidte".equals(type)) {
			//移动号排除
			String Operator = MobileLocationTool.Judge_Operator(mobile);// 判断手机号运营商
			if("00".equals(Operator)) {
				msg.put("mobile", mobile);
				msg.put("resmsg", "暂不支持移动手机号验证！！");
				msg.put("resid", "-11");
				return JSON.toJSON(msg).toString();
			}
		}
//		int count = customerInterfaceDao.findeCustomerInterfaceForRemaining(customer_id, type);
//		int count = customerPackageDao.findRemaining(customer_id);
		if(!isPerform&&customerPackageService.findRemaining(customer_id)==0) {
			msg.put("mobile", mobile);
			msg.put("resmsg", "资费不足,请充值！");
			msg.put("resid", "6001");
			return JSON.toJSON(msg).toString();
		}
		CustomerMobiles customerMobiles = new CustomerMobiles();
		customerMobiles.setCustomerId(customer_id);
		customerMobiles.setMobileno(mobile);
		if (isPerform&&customerMobilesService.findCustomerMobilesCount(customerMobiles) == 0) {
			msg.put("resmsg", "该手机号不在客户手机号列表中，请添加在操作！");
			msg.put("mobile", mobile);
			msg.put("resid", "-22");
			String unicomPosition = JSON.toJSON(msg).toString();
			return unicomPosition;
		}
		return "";
	}
}
