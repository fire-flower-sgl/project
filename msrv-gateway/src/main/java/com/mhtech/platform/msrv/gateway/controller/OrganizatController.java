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
import com.mobile.model.OrgInfo;
import com.mobile.model.Page;
import com.mobile.model.UnicomResponseEnums;
import com.mobile.service.OrganizatService;

/**
 * @ClassName OrganizatController
 * @Description 组织管理控制类
 * @Author admin
 * @Date 2019/8/13 14:10
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/MobileApi/org")
//@Api(value = "OrganizatController", tags = "组织管理")
public class OrganizatController {
			
	@Autowired
	OrganizatService organizatService;

	@PostMapping(value = "/queryPage")
//	@ApiOperation(value = "分页+模糊查询")
    public RespObject<?> queryEventPage(@RequestBody JSONObject reqObject) {
		String userCode = reqObject.getString("userCode");
		String speNum = reqObject.getString("speNum");
		if (StringUtils.isEmpty(userCode)||StringUtils.isEmpty(speNum)) {
			return RespUtils.build(RespCode.NULL_ARGUMENT);
		}
		//拿数据
        Page pageList = organizatService.findPage(reqObject);
        return RespUtils.buildOKWithDataYg(pageList);
    }
	
	
	@PostMapping(value = "/add")
//	@ApiOperation(value = "新增组织")
    public RespObject<?> addParentCode(@RequestBody JSONObject reqObject) {
        boolean flag = organizatService.addCode(reqObject);
        return RespUtils.buildOKWithDataYg(flag);
    }
	
	/**
	 * @param reqObject
	 * @return		0:执行异常,	1 删除成功,	2 有分支, 	3有分配用户
	 */
	@PostMapping(value = "/delByOrgCode")
//	@ApiOperation(value = "删除组织")
    public RespObject<?> delById(@RequestBody JSONObject reqObject) {
		String orgCode = reqObject.getString("orgCode");
		if(StringUtils.isEmpty(orgCode)) {
			return RespUtils.build(RespCode.NULL_ARGUMENT);
		}
        int flag = organizatService.delByOrgCode(orgCode);
        return RespUtils.buildOKWithDataYg(flag);
    }

	@PostMapping(value = "/queryIsExist")
//	@ApiOperation(value = "判断组织Code唯一性")
    public RespObject<?> queryIsExist(@RequestBody JSONObject reqObject) {
        boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION; 
		//true 存在 false 不存在
        boolean flag = organizatService.queryByOrgCode(reqObject);
        return RespUtils.buildOKWithDataYg(flag);
	}
	
	@PostMapping(value = "/updateById")
//	@ApiOperation(value = "更新组织")
    public RespObject<?> updateByid(@RequestBody JSONObject reqObject) {
        boolean flag = organizatService.updateByid(reqObject);
        return RespUtils.buildOKWithDataYg(flag);
    }
			
	@PostMapping(value = "/queryByOrgCode")
//	@ApiOperation(value = "去修改页面")
    public RespObject<?> queryByOrgCode(@RequestBody JSONObject reqObject) {
		Map<String, Object> orgCode = organizatService.findByOrgCode(reqObject);
		return RespUtils.buildOKWithDataYg(orgCode);
    }
	
	@PostMapping(value = "/queryTreeByCompanyCode")
//	@ApiOperation(value = "获取公司下树形组织")
    public RespObject<?> queryTreeByCompanyCode(@RequestBody JSONObject reqObject) {
		String companyCode = reqObject.getString("companyCode");
		if(StringUtils.isEmpty(companyCode)) {
			return RespUtils.build(RespCode.NULL_ARGUMENT);
		}
		List<OrgInfo> findByParentOrgCode = organizatService.findByTreeOrgCode(companyCode);
		return RespUtils.buildOKWithDataYg(findByParentOrgCode);
    }
	
	@PostMapping(value = "/queryTreeByOrgCode")
//	@ApiOperation(value = "获取某组织下树形组织")
    public RespObject<?> treeByOrgCode(@RequestBody JSONObject reqObject) {
		String orgCode = reqObject.getString("orgCode");
		if(StringUtils.isEmpty(orgCode)) {
			return RespUtils.build(RespCode.NULL_ARGUMENT);
		}
		List<OrgInfo> dataList = organizatService.treeByOrgCode(orgCode);		
		return RespUtils.buildOKWithDataYg(dataList);
    }

}
