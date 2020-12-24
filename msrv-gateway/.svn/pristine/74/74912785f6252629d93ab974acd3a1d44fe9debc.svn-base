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
import com.mobile.model.UnicomResponseEnums;
import com.mobile.service.OrgFuncService;
import com.mobile.utils.Utilis;


/**
 * @ClassName OrgFuncController
 * @Description 组织职能控制类
 * @Author admin
 * @Date 2019/8/13 14:10
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/MobileApi/orgFunc")
//@Api(value = "OrgFuncController", tags = "职能管理")
public class OrgFuncController {
			
	@Autowired
	OrgFuncService orgFuncService;

	@PostMapping(value = "/queryPage")
//	@ApiOperation(value = "分页+模糊查询")
    public RespObject<?> queryEventPage(@RequestBody JSONObject reqObject) {
        boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		String userCode = reqObject.getString("userCode");
		String speNum = reqObject.getString("speNum");
		if (StringUtils.isEmpty(userCode)||StringUtils.isEmpty(speNum)) {
			return RespUtils.build(RespCode.NULL_ARGUMENT);
		}
		//拿数据
		Page eventPage = orgFuncService.findPage(reqObject);
		return RespUtils.buildOKWithDataYg(eventPage);
    }
	
	
	@PostMapping(value = "/add")
//	@ApiOperation(value = "新增职能")
    public RespObject<?> addParentCode(@RequestBody JSONObject reqObject) {
        boolean flag = orgFuncService.add(reqObject);
        return RespUtils.buildOKWithDataYg(flag);
    }
	

	@PostMapping(value = "/delByOrgFunc")
//	@ApiOperation(value = "删除职能")
    public RespObject<?> delById(@RequestBody JSONObject reqObject) {
		String OrgFuncCode = reqObject.get("funcCode").toString();
        int flag = orgFuncService.delByOrgCode(OrgFuncCode);
        return RespUtils.buildOKWithDataYg(flag);
    }
	


	@PostMapping(value = "/queryIsExist")
//	@ApiOperation(value = "判断唯一性")
    public RespObject<?> queryIsExist(@RequestBody JSONObject reqObject) {
		String funcCode = reqObject.get("funcCode").toString();
		//true 存在 false 不存在
        boolean flag = false;
        Map<String, Object> funcMap = orgFuncService.queryByFuncCode(funcCode);
        if(null !=funcMap) {
        	flag = true;
        }
        return RespUtils.buildOKWithDataYg(flag);
    }
	
	
	@PostMapping(value = "/queryByFuncCode")
//	@ApiOperation(value = "根据funcCode查找")
    public RespObject<?> queryByOrgCode(@RequestBody JSONObject reqObject) {
		String funcCode = reqObject.get("funcCode").toString();
        Map<String, Object> funcMap = orgFuncService.queryByFuncCode(funcCode);
        Map<String, String> mapObjToString = Utilis.mapObjToString2(funcMap);
        return RespUtils.buildOKWithDataYg(mapObjToString);
    }
	
	
	@PostMapping(value = "/updateByid")
//	@ApiOperation(value = "更新节点")
    public RespObject<?> updateByid(@RequestBody JSONObject reqObject) {
        boolean flag = orgFuncService.updateByid(reqObject);
        return RespUtils.buildOKWithDataYg(flag);
    }
	
//	@PostMapping(value = "/companyList")
//	@ApiOperation(value = "公司下拉列表")
//    public RespObject<?> orgCodeList() {
//        List<Map<String, Object>> companyList = orgFuncService.queryCompanyList();
//        boolean success = true;
//		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//        Result result = new Result(success, companyList, msg);
//        return result;
//    }
	
	@PostMapping(value = "/funcList")
//	@ApiOperation(value = "职能下拉列表-新增用户事使用")
    public RespObject<?> funcList(@RequestBody JSONObject reqObject) {
        List<Map<String, Object>> funcList = orgFuncService.queryFuncList(reqObject);
        return RespUtils.buildOKWithDataYg(funcList);
    }


}
