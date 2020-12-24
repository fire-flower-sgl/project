package com.mhtech.platform.msrv.gateway.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialArray;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mobile.model.CustomerMobiles;
import com.mobile.model.Page;
import com.mobile.model.Result;
import com.mobile.service.CustomerMobilesService;
import com.mobile.service.ParameterService;
import com.mobile.utils.DateUtils;
import com.mobile.utils.ExcelUtil;
import com.mobile.utils.IdCardExpUtil;
import com.mobile.utils.StringUtils;
import com.mobile.utils.UploadUtils;
import com.mobile.utils.Utilis;

/**
 * @ClassName CustomerMobilesController
 * @Description TODO 客户对应手机号
 * @Author admini
 * @Date 2019/8/29 13:09
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/MobileApi/CustomerMobiles")
@SuppressWarnings("all")
public class CustomerMobilesController {
//    @Reference(version = "1.0.0")
	@Autowired
	CustomerMobilesService customerMobilesService;
//    @Reference(version = "1.0.0")
	@Autowired
	ParameterService parameter;
	String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 新增
	 * 
	 * @Date 13:48 2019/8/29
	 * 
	 * @Param [map]
	 * 
	 * @return com.mhtech.common.result.entity.Result
	 **/
	@PostMapping(value = "/add")
	@ResponseBody
	public RespObject<?> saveCustomerMobiles(@RequestBody Map<String, String> map) {
		CustomerMobiles customerMobiles = null;
		if (map != null) {
			if (map.get("customerId") == null || "".equals(map.get("customerId"))) {
				return RespUtils.build(RespCode.NULL_ARGUMENT);
			}
			if (map.get("mobileno") == null || "".equals(map.get("mobileno"))) {
				return RespUtils.build(RespCode.NULL_ARGUMENT);
			}
			String remark = "";
			if (map.get("remark") != null && !"".equals(map.get("remark")))
				remark = map.get("remark");
			String name = null;
			if (map.get("name") != null && !map.get("name").toString().trim().isEmpty())
				name = map.get("name").toString();
			String idcard = null;
			if (map.get("idcard") != null && !map.get("idcard").toString().trim().isEmpty())
				idcard = map.get("idcard").toString();
			if (idcard != null && !IdCardExpUtil.IDCardValidate(idcard).isEmpty()) {
				return RespUtils.build(307, false, IdCardExpUtil.IDCardValidate(idcard));
			}
			if (!Utilis.ValidateMobile(map.get("mobileno"))) {
				return RespUtils.build(RespCode.ILLEGAL_FORMAT_ARGUMENT);
			}
//            customerMobiles = new CustomerMobiles(Utilis.getUUID(), map.get("customerid"), map.get("mobileno"), "0", "0", remark, DateUtils.getDateTime());
			customerMobiles = new CustomerMobiles(Utilis.getUUID(), map.get("customerId"), map.get("mobileno"),
					map.get("state"), map.get("callnumber"), map.get("remark"), map.get("ctime"), map.get("name"),
					map.get("idcard"));
			if (customerMobilesService.findCustomerMobilesCount(customerMobiles) > 0) {
				return RespUtils.build(RespCode.DUPLICATION_DATA);
			}
		}
		int add = customerMobilesService.add(customerMobiles);
		if (add == 0) {
			return RespUtils.build(RespCode.DATABASE_ERROR);
		}
		RespObject<?> build = RespUtils.build(RespCode.SUCCESS);
		build.setSuccess(true);
		return build;
	}

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 删除
	 * 
	 * @Date 13:48 2019/8/29
	 * 
	 * @Param [map]
	 * 
	 * @return com.mhtech.common.result.entity.Result
	 **/
	@PostMapping(value = "/del")
	@ResponseBody
	public RespObject<?> del(@RequestBody Map<String, String> map) {
		if (map != null) {
			String id = map.get("Id");
			int delete = customerMobilesService.delete(id);
			if (delete == 0) {
				return RespUtils.build(RespCode.DATABASE_ERROR);
			}
		}
		return RespUtils.build(RespCode.SUCCESS);
	}

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 分页查询
	 * 
	 * @Date 13:49 2019/8/29
	 * 
	 * @Param [map]
	 * 
	 * @return com.mhtech.common.result.entity.Result
	 **/
	@PostMapping(value = "/queryPage")
	@ResponseBody
	public RespObject<?> queryPage(@RequestBody Map<String, String> map) {
		// 获取客户对应的手机号
		Page customerMobilesPage = customerMobilesService.findCustomerMobilesPage(map);
		return RespUtils.buildOKWithDataYg(customerMobilesPage);
	}

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 查询当前客户下所有手机号
	 * 
	 * @Date 13:49 2019/8/29
	 * 
	 * @Param [map]
	 * 
	 * @return com.mhtech.common.result.entity.Result
	 **/
	@PostMapping(value = "/queryAll")
	@ResponseBody
	public RespObject<?> queryAll(@RequestBody Map<String, String> map) {
		List status = parameter.findParameterList("status", "0");
		List customerMobilesList = customerMobilesService.findCustomerMobilesList(map);
		Map maps = new Hashtable();
		maps.put("customerMobilesList", customerMobilesList);
		maps.put("state", status);
		return RespUtils.buildOKWithDataYg(maps);
	}

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 修改
	 * 
	 * @Date 13:49 2019/8/29
	 * 
	 * @Param [map]
	 * 
	 * @return com.mhtech.common.result.entity.Result
	 **/
	@PostMapping(value = "/modify")
	@ResponseBody
	public RespObject<?> modify(@RequestBody Map<String, String> map) {
		if (map != null) {
			CustomerMobiles customerMobiles = new CustomerMobiles();
			customerMobiles.setId(map.get("id"));
			customerMobiles.setCustomerId(map.get("customerId"));
			customerMobiles.setMobileno(map.get("mobileno"));
			customerMobiles.setRemark(map.get("remark"));
			customerMobiles.setState(map.get("state"));
			customerMobiles.setName(map.get("name"));
			customerMobiles.setIdcard(map.get("idcard"));

			if (map.get("idcard") != null && !map.get("idcard").toString().isEmpty()
					&& !IdCardExpUtil.IDCardValidate(map.get("idcard")).isEmpty()) {
				return RespUtils.build(307, false, IdCardExpUtil.IDCardValidate(map.get("idcard")));
			}
			if (!Utilis.ValidateMobile(customerMobiles.getMobileno()))
				return RespUtils.build(307, false, "手机号错误");

			int update = customerMobilesService.update(customerMobiles);
			if (update == 0) {
				return RespUtils.build(RespCode.DATABASE_ERROR);
			}
		}
		RespObject<?> build = RespUtils.build(RespCode.SUCCESS);
		build.setSuccess(true);
		return build;
	}

	/*
	 * @Author admini
	 * 
	 * @Description //TODO 批量上传电话
	 * 
	 * @Date 13:49 2019/8/29
	 * 
	 * @Param [file, uploadUtils, map]
	 * 
	 * @return com.mhtech.common.result.entity.Result
	 **/
//    @PostMapping(value = "/updateExcel")
	@RequestMapping(value = "/updateExcel", method = RequestMethod.POST)
	@ResponseBody
	public RespObject<?> updateExcel(@RequestParam("file") MultipartFile file, UploadUtils uploadUtils,
			@RequestParam("customerId") String customerId) throws Exception {
		RespCode msg = RespCode.SUCCESS;
		List<String[]> list = uploadUtils.updateExcelUtils(file, 1);
		List<CustomerMobiles> cmList = new ArrayList<>();
		boolean ismsg = false;
		String tmp = "";
		int i = 2;
		List<String> mobileList = new ArrayList<String>();
		List<String> msgs = new ArrayList<String>();
		for (String[] str : list) {
			String mobile = str[0].trim().replaceAll(" ", "");
			String name = "";
			String idCard = "";
			String remark = "";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(mobile);
			boolean isMatch = m.matches();
			if (!isMatch || mobile.length() != 11) {
				msgs.add("第" + i + "行 手机号错误!");
				msg = RespCode.ILLEGAL_FORMAT_ARGUMENT;
				ismsg = true;
				break;
			}
			if (str.length >= 2) {
				name = str[1].trim().toUpperCase().equals("NULL") ? "" : str[1].trim();
			}
			if (str.length >= 3) {
				idCard = str[2].trim().toUpperCase().equals("NULL") ? "" : str[2].trim();
			}
			if (str.length == 4) {
				remark = str[3].trim().toUpperCase().equals("NULL") ? "" : str[3].trim();
			}
			if (!StringUtils.isBlank(idCard)) {
				String idCardValidateMSG = IdCardExpUtil.IDCardValidate(idCard);
				if (!StringUtils.isBlank(idCardValidateMSG)) {
					msgs.add("第" + i + "行  " + idCardValidateMSG);
					msg = RespCode.ILLEGAL_FORMAT_ARGUMENT;
					ismsg = true;
					i++;
					continue;
				}
			}
			CustomerMobiles cm = new CustomerMobiles(Utilis.getUUID(), customerId, str[0].trim(), "1", "0", remark,
					DateUtils.getDateTime(), name, idCard);

//			if (tmp.equals(cm.getMobileno())) {
			if (mobileList.contains(cm.getMobileno())) {
				msg = RespCode.DUPLICATION_DATA;
				msgs.add("第" + i + "行 " + " 手机号有重复");
				ismsg = true;
				i++;
				continue;
			}
			mobileList.add(cm.getMobileno());
			if (customerMobilesService.findCustomerMobilesCountByMobileNo(cm.getMobileno()) > 0) {
				msgs.add("第" + i + "行 " + " 手机号已存在");
				msg = RespCode.DUPLICATION_DATA;
				ismsg = true;
				i++;
				continue;
			}
			cmList.add(cm);
			tmp = cm.getMobileno();
			i++;
		}
		if (ismsg) {
			return RespUtils.build(300, "请求参数错误", msgs);
		}
		int add = customerMobilesService.add(cmList);
		if (add == 0) {
			msg = RespCode.DATABASE_ERROR;
		}
		RespObject<?> build = RespUtils.build(RespCode.SUCCESS);
		build.setSuccess(true);
		return build;
	}

	/**
	 * 校验客户下用户是否存在
	 * 
	 * @param map id,customer_id,Faceclass
	 * @return
	 */
	@PostMapping(value = "/validationCustomerMobiles")
	@ResponseBody
	public RespObject<?> therefindeCustomerInterface(@RequestBody CustomerMobiles customerMobiles) {
		boolean findCustomerMobilesCount = customerMobilesService.findCustomerMobilesCount(customerMobiles) > 0;
		if (findCustomerMobilesCount) {
			return RespUtils.build(307, !findCustomerMobilesCount, "用户已存在");
		}
		return RespUtils.build(307, !findCustomerMobilesCount, "用户不存在");
	}
}
