package com.mhtech.platform.msrv.gateway.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mobile.model.Page;
import com.mobile.model.Result;
import com.mobile.model.SysPower;
import com.mobile.model.UnicomResponseEnums;
import com.mobile.model.User;
import com.mobile.service.ParameterService;
import com.mobile.service.SysPowerService;
import com.mobile.utils.StringUtils;

/**
 * mjl_ SysPowerController 系统权限控制类 2019-10.22
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/MobileApi/SpsPower")
//@Api(value = "SysPowerController", tags = "权限管理")
@SuppressWarnings("all")
public class MobilePowerController {

	@Autowired
	private SysPowerService sysPowerService;

	@Autowired
	private ParameterService sysPSImpl;

	@PostMapping(value = "/selectSpsPower")
//	@ApiOperation(value = "依据id查询权限")
	public RespObject<?> selectSpsPower(@RequestBody SysPower sysPower) {
		SysPower sum = sysPowerService.selectSysPower(sysPower.getId());
		return RespUtils.buildOKWithDataYg(sum);
	}
	
	@PostMapping(value = "/insertSpsPower")
//	@ApiOperation(value = "新增权限------添加url后的接口")
	public RespObject<?> insertSpsPowers(@RequestBody @Valid SysPower sysPower,BindingResult br) {
		boolean success = true;
		int code=200;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		String sum = null;
		
		String powerType = sysPower.getPowerType().toString();
		String powerNum = sysPower.getPowerNum().toString();
		String name = powerNum.substring(0, powerNum.indexOf(":"));

		//验证权限编码 html:xxx 与权限类型 html 一致性
		if (name.equals(powerType)) {
			if (powerType.equals("menu")) {
				if (sysPower.getPowerFather()!=null && !"".equals(sysPower.getPowerFather())) {
					 success = false;
					 sum="权限上级输入有误";		
				}else {
					
					 if (sysPowerService.insertSysPower(sysPower)>0) {
						 sum="权限新增成功";
					 }
				}
			}else {
				if (sysPower.getPowerFather()!=null && !"".equals(sysPower.getPowerFather())) {
					
					 if (sysPowerService.insertSysPower(sysPower)>0) {
						 sum="权限新增成功";
					 }
				}else {
					 success = false;
					 sum="权限上级输入有误";		
				}					
			}	
		}else {
			 success = false;
			 sum="权限编码  与权限类型 不一致性";
		}	
		if(!success)
			code=334;
		//权限类型与权限编码输入不一致
		return RespUtils.build(code,success,sum);
	}
	
	@PostMapping(value = "/updateSpsPower")
//	@ApiOperation(value = "修改权限------添加url后的接口")
	public RespObject<?> updateSpsPowers(@RequestBody @Valid SysPower sysPower,BindingResult br) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		if (br.hasErrors()) {
			return RespUtils.build(334,false, br.getAllErrors().get(0).getDefaultMessage());
		}
		int sum = sysPowerService.updateSysPower(sysPower);
		return RespUtils.buildOKWithDataYg(sum);
	}

	@PostMapping(value = "/deleteSpsPower")
//	@ApiOperation(value = "删除权限")
	public RespObject<?> deleteSpsPower(@RequestBody SysPower sysPower) {
		boolean sum = sysPowerService.deletePowerNum(sysPower.getPowerNum());
		return RespUtils.buildOKWithDataYg(sum);
	}

	@PostMapping(value = "/queryPage")
//	@ApiOperation(value = "分页/条件查询")
	public RespObject<?> queryPage(@RequestBody Map<String, String> map) {
		// 拿数据
		Page sysConfigPage = sysPowerService.findSysConImplPage(map);
		return RespUtils.buildOKWithDataYg(sysConfigPage);
	}

	@PostMapping(value = "/treePower")
//	@ApiOperation(value = "树形权限")
	public RespObject<?> treePower(@RequestBody JSONObject jsonObject) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		JSONObject data = new JSONObject();
		// 角色代码不能为空
		if (jsonObject.get("roleNum") == null || StringUtils.isBlank(jsonObject.get("roleNum").toString())) {
			return RespUtils.build(RespCode.NULL_ARGUMENT);
		}
		data = sysPowerService.getPowerRole(jsonObject.get("roleNum").toString());
		return RespUtils.buildOKWithDataYg(data);
	}
	
	@PostMapping(value = "/treePowerList")
//	@ApiOperation(value = "树形权限(新增权限选择父级)")
	public RespObject<?> treePowerList() {
		return RespUtils.buildOKWithDataYg(sysPowerService.findByPowerTypeAndPowerFather());
	}

	@PostMapping(value = "/saveRolePower")
//	@ApiOperation(value = "保存树形权限")
	public RespObject<?> saveRolePower(@RequestBody JSONObject jsonObject) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		JSONObject data = new JSONObject();
		// 角色代码不能为空
		if (jsonObject.get("roleNum") == null || StringUtils.isBlank(jsonObject.get("roleNum").toString())) {
			success = false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
		} else {
			JSONArray rolePower = jsonObject.getJSONArray("rolePower");
			String roleNum = jsonObject.getString("roleNum");
			sysPowerService.savePowerRole(rolePower, roleNum);
		}
		return RespUtils.buildOKWithDataYg(data);
	}
	
	@PostMapping(value="/findSysPowerOne")
//	@ApiOperation(value = "权限编码唯一性")
	public RespObject<?> findSysPowerOne(@RequestBody SysPower sysPower) {			
	    boolean success = true;
	    UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
	    String sum ="0";  
	    String powerNum= sysPower.getPowerNum();   
	    //不包含： 
        if (powerNum.indexOf(":")!=-1) {
			//截取前缀
        	String name=powerNum.substring(0,powerNum.indexOf(":"));
        	if (sysPSImpl.findTypeCode("powerType",name)) {
    			if (sysPowerService.findSysPowerOne(powerNum)) {
    				//唯一性错误
    				sum = "2";
    			}
			}else {
				//格式错误 格式例子：html
				sum="1";
			}	   
		}else {
			   //格式错误 缺少： 或不为英文 ：
			   sum="1";
		}	    
	    return  RespUtils.buildOKWithDataYg(sum);
	}
	
	@PostMapping(value = "/selectPower")
//	@ApiOperation(value = "查询用户权限（不包含数据与按钮权限）--mjl新改")
	public RespObject<?> selectPower( @RequestBody User spUser) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		
		//结果
		List<Map<String, Object>> list = sysPowerService.list4(spUser.getUserCode());
		
		//没有权限
		if(list.size()==0) {
			return RespUtils.build(RespCode.NO_USER_RIGHTS);
		}
	
		//定义父级权限列表
		List<Map<String, Object>> fatheList = new ArrayList();

		for (int i = 0; i < list.size(); i++) {	
			if (("menu").equals(list.get(i).get("powerType"))) {
				String powerNum = list.get(i).get("index").toString();
				List<Map<String, Object>> xx=findPower(powerNum,list);
				if(xx.size()>0 && xx!=null) {
					list.get(i).put("subs", xx);
				}
				fatheList.add(list.get(i));
			}
		}
//		System.err.println("=========导航权限=======");
//		System.err.println(fatheList);
		 return  RespUtils.buildOKWithDataYg(fatheList);
	}
	
	private List<Map<String, Object>> findPower(String powerNum, List<Map<String, Object>> list) {
		List<Map<String, Object>> sonList = new ArrayList<>();	
		//循环传入的集合
		for (int i = 0; i < list.size(); i++) {
			//当powerFather不为空的时候
			if( list.get(i).get("powerFather")!=null) {
				if(list.get(i).get("powerFather").toString().equals(powerNum)) {	
					List<Map<String, Object>> xx = findPower(list.get(i).get("index").toString(),list);
					if (xx!=null&&xx.size()>0) {
						list.get(i).put("subs", xx);
					}		
					sonList.add(list.get(i));
				}
			}	
		}
		return sonList;
	}

	
//	@PostMapping(value = "/selectAll")
//	@ApiOperation(value = "查询所有")
//	public Result selectAll() {
//		boolean success = true;
//		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//		List<SysPower> list = sysPowerService.list();
//		return new Result(success, list, msg);
//	}

//	@PostMapping(value = "/selectPowerType")
//	@ApiOperation(value = "查询所有权限（不包含数据权限）")
//	public Result selectPowerType() {
//		boolean success = true;
//		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//		List<SysPower> list = sysPowerService.list3();
//		return new Result(success, list, msg);
//	}

//	@PostMapping(value = "/selectDataAll")
//	@ApiOperation(value = "查询数据权限")
//	public Result selectDataAll() {
//		boolean success = true;
//		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//		List<SysPower> list = sysPowerService.list2();
//		return new Result(success, list, msg);
//	}
	
	
	
	
	
}
