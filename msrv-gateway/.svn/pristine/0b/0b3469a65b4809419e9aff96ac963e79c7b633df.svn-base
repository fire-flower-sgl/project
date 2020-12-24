package com.mhtech.platform.msrv.gateway.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mobile.model.Page;
import com.mobile.service.CompanyService;

/**
 * @ClassName CompanyController
 * @Description 企业控制类
 * @Author admin
 * @Date 2019/8/13 14:10
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/MobileApi/company")
//@Api(value = "CompanyController", tags = "企业管理")
public class CompanyController {
			
	@Autowired
	CompanyService companyService;

	@PostMapping(value = "/queryPage")
//	@ApiOperation(value = "分页查询")
    public RespObject<?> queryEventPage(@RequestBody JSONObject reqObject) {
		String userCode = reqObject.getString("userCode");
		String speNum = reqObject.getString("speNum");
		if (StringUtils.isEmpty(userCode)||StringUtils.isEmpty(speNum)) {
			return RespUtils.build(RespCode.NULL_ARGUMENT);
		}
        Page eventPage = companyService.findPage(reqObject);
        return RespUtils.buildOKWithDataYg(eventPage);
    }
	
	
	@PostMapping(value = "/add")
//	@ApiOperation(value = "新增")
    public RespObject<?> addParentCode(@RequestBody JSONObject reqObject) {
		Integer flag = companyService.add(reqObject);
        return RespUtils.buildOKWithDataYg(flag);
    }
	
	@PostMapping(value = "/updateById")
//	@ApiOperation(value = "更新公司")
    public RespObject<?> updateByid(@RequestBody JSONObject reqObject) {
        int flag = companyService.updateByid(reqObject);
        return RespUtils.buildOKWithDataYg(flag);
    }
	

	/**
	 * @param reqObject
	 * @return		0:执行异常,	1 删除成功,	2 待定 	3 待定
	 */
	@PostMapping(value = "/delByCompanyCode")
//	@ApiOperation(value = "删除公司")
	//@ApiImplicitParam(name = "orgCode", value = "节点编号", required = false, dataType = "String")
    public RespObject<?> delById(@RequestBody JSONObject reqObject) {
		String companyCode = reqObject.getString("companyCode");
		if (StringUtils.isEmpty(companyCode)) {
			return RespUtils.build(RespCode.NULL_ARGUMENT);
		}
        int flag = companyService.delByCompanyCode(companyCode);
        return RespUtils.buildOKWithDataYg(flag);
    }
	
	@PostMapping(value = "/companyCodeIsExist")
//	@ApiOperation(value = "判断companyCode唯一性")
    public RespObject<?> companyCodeIsExist(@RequestBody JSONObject reqObject) {
        boolean flag = companyService.companyCodeIsExist(reqObject);
        return RespUtils.buildOKWithDataYg(flag);
    }
	
	
	@PostMapping(value = "/companyNameIsExist")
//	@ApiOperation(value = "判断companyName唯一性")
    public RespObject<?> companyNameIsExist(@RequestBody JSONObject reqObject) {
        boolean flag = companyService.companyNameIsExist(reqObject);
        return RespUtils.buildOKWithDataYg(flag);
    }
	
	
	@PostMapping(value = "/queryByCompanyCode")
//	@ApiOperation(value = "去修改页面")
    public RespObject<?> queryByCompanyCode(@RequestBody JSONObject reqObject) {
        Map<String, Object> dataMap = companyService.queryByCompanyCode(reqObject);
        return RespUtils.buildOKWithDataYg(dataMap);
    }
    
	
	@PostMapping(value = "/queryArea")
//	@ApiOperation(value = "省级联动,根据parentCode查找")
    public RespObject<?> queryArea(@RequestBody JSONObject reqObject) {
		String parentCode = "0";
		if(null!=reqObject && null!=reqObject.get("parentCode")&&""!=reqObject.get("parentCode")) {
			parentCode = reqObject.getString("parentCode");
		}
		List<Map<String, Object>> areaList = companyService.queryAreaListByParentCode(parentCode);
		 return RespUtils.buildOKWithDataYg(areaList);
    }
	
	@PostMapping(value = "/companyList")
//	@ApiOperation(value = "企业list,新增组织用")
    public RespObject<?> companyList(@RequestBody JSONObject reqObject){
		String userCode = reqObject.getString("userCode");
		String speNum = reqObject.getString("speNum");
		if (StringUtils.isEmpty(userCode)||StringUtils.isEmpty(speNum)) {
			return RespUtils.build(RespCode.NULL_ARGUMENT);
		}
		List<Map<String, Object>> companyList = companyService.queryCompanyList(userCode,speNum);
		 return RespUtils.buildOKWithDataYg(companyList);
    }
	
}
