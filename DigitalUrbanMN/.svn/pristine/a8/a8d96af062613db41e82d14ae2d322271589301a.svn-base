package com.mobile.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mobile.model.CustomerMobiles;
import com.mobile.service.CustomerMobilesService;
import com.mobile.service.CustomerPackageService;
import com.mobile.service.CustomerService;
import com.mobile.service.MobileService;

public class ValidationApi {
	/**
	 * key+seed+md5(customerId+md5(seed)) 信息校验
	 * 
	 * @param parmat
	 * @return
	 */
	public static Map<String, Object> validationKey(Map<String, Object> parmat) {
		Map<String, Object> msg = new HashMap<>();
		Object key = parmat.get("key");
		Object seed = parmat.get("seed");
		Object customerId = parmat.get("customerId");
		if (key == null) {
			msg.put("resmsg", "key值不能为空");
			msg.put("resid", "-11");
			return msg;
		}
		if (seed == null) {
			msg.put("resmsg", "seed值不能为空");
			msg.put("resid", "-11");
			return msg;
		}
		if (!DateUtils.check(seed.toString())) {
			msg.put("resmsg", "seed格式不正确");
			msg.put("resid", "-11");
			return msg;
		}
		long numberSeconds = DateUtils.getNumberSeconds(seed.toString(), DateUtils.getDateTime("yyyyMMddHHmmss"));
		// 判断传入时间和当前系统时间
		if (numberSeconds > Long.parseLong(parmat.get("timeLimit").toString())) {
			msg.put("resmsg", "seed不合法");
			msg.put("resid", "-11");
			return msg;
		}
		String keys = MD5HashUtil.md5LowerCase(MD5HashUtil.md5LowerCase(customerId.toString()) + seed);
		System.err.println(keys);
		if (!key.equals(keys)) {
			msg.put("resmsg", "key错误");
			msg.put("resid", "-11");
			return msg;
		}
		return null;
	}
	/**
	 * 客户相关信息校验
	 * @param customerPackageService 接口对象
	 * @param customerMobilesService 接口对象
	 * @param customerService 接口对象
	 * @param parmat 参数值
	 * @param mobile
	 * @param customerid
	 * @param type
	 * @return
	 */
	public static Map<String, Object> InformationCheck(CustomerPackageService customerPackageService,
			CustomerMobilesService customerMobilesService, CustomerService customerService,MobileService mobileLocationService, Map<String, Object> parmat,boolean isboolean) {
		// 存放消息信息
		Map<String, Object> msg = new HashMap<>();
		Object mobile=parmat.get("mobile");
		Object customerid=parmat.get("customerId");
		Object className=parmat.get("className");
		if (customerid==null||"".equals(customerid)) {
			msg.put("resmsg", "客户编码不能为空，请检查！");
			msg.put("resid", "-12");
			return msg;
		}
		boolean validateMobile = Utilis.ValidateMobile(mobile.toString());
		// 手机号验证
		if (!"APICarrierValidMd5".equals(className.toString())&&!validateMobile) {
			msg.put("mobile", mobile);
			msg.put("resmsg", "手机号错误");
			msg.put("resid", "-11");
			return msg;
		}
		// 客户编码客户ip验证
		if (!customerService.findCustomer(parmat)) {
			msg.put("mobile", mobile);
			msg.put("resmsg", "客户不存在或IP地址错误，请联系管理员");
			msg.put("resid", "-11");
			return msg;
		}
		// 查看是否有权限
		if (customerService.findCustomerPermission(customerid.toString(), className.toString()) == 0) {
			msg.put("mobile", mobile);
			msg.put("resmsg", "您没有购买当前产品");
			msg.put("resid", "-40");
			return msg;
		}
//		if("openPosition".equals(className)) {
//			Map<String, String> judgeOperator = MobileLocationTool.JudgeOperator(mobile.toString());// 判断手机号运营商
//			String OperatorCode=judgeOperator.get("code");
//			String OperatorName=judgeOperator.get("name");
//		}
		
		// 二要素排除移动号
		if ("twoelementvalidte".equals(className)) {
			// 移动号排除
			String Operator = MobileLocationTool.Judge_Operator(mobile.toString());// 判断手机号运营商
			if ("00".equals(Operator)) {
				msg.put("mobile", mobile);
				msg.put("resmsg", "暂不支持移动手机号验证！！");
				msg.put("resid", "-11");
				return msg;
			}
		}
		//分项接口资费+总项资费校验
		if (isboolean&&!customerPackageService.findRemaining(customerid.toString(), className.toString())
				&& !customerPackageService.findRemaining(customerid.toString(), "total")) {
			msg.put("mobile", mobile);
			msg.put("resmsg", "资费不足,请充值！");
			msg.put("resid", "6001");
			return msg;
		}
		//客户手机号校验
		CustomerMobiles customerMobiles = new CustomerMobiles();
		customerMobiles.setCustomerId(customerid.toString());
		customerMobiles.setMobileno(mobile.toString());
		if (isboolean&&!"APIopenPosition".equals(className.toString())&&!"APIsendSms".equals(className.toString())&&!"APICarrierValidMd5".equals(className.toString())&&customerMobilesService.findCustomerMobilesCount(customerMobiles) == 0) {
			msg.put("resmsg", "当前不是白名单用户,请先调用开通定位接口！");
			msg.put("mobile", mobile);
			msg.put("resid", "-1");
			return msg;
		}
		//定位授权校验
		if("APIopenPosition".equals(className.toString())) {
			if(customerMobilesService.findCustomerMobilesCount(customerMobiles) == 0) {//手机号不存在插入
				Map<String, String> judgeOperator = MobileLocationTool.JudgeOperator(mobile.toString());// 判断手机号运营商
				if(judgeOperator!=null)
					customerMobiles.setOperators(judgeOperator.get("code"));
				customerMobiles.setState("1");
				customerMobilesService.add(customerMobiles);
			}else {
				String Operator = MobileLocationTool.Judge_Operator(mobile.toString());// 判断手机号运营商
				if(!"01".equals(Operator)) {//不为联通
					Map<String, Object> param=new HashMap<String, Object>();
					param.put("phone", mobile);
					param.put("OperatorCode", "");
					param.put("OperatorName", "");
					param.put("customerId", customerid.toString());
					String unicomPosition = mobileLocationService.authlbsstatus(param);
					JSONObject jsonObject=JSON.parseObject(unicomPosition);
					if(!"1".equals(jsonObject.get("resid").toString())) {//状态不为1
						return null;
					}
				}
				msg.put("resmsg", "白名单用户,已开通定位!");
				msg.put("mobile", mobile);
				msg.put("resid", "1");
				return msg;
			}
		}
		return null;
	}
}
