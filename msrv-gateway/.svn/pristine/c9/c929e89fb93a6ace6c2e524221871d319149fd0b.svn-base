package com.mhtech.platform.msrv.gateway.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mobile.model.Page;
import com.mobile.model.Result;
import com.mobile.model.SysRole;
import com.mobile.model.UnicomResponseEnums;
import com.mobile.service.SysRoleService;


/**
 *  mjl_
 *  SysRoleController
 *  用户角色控制类
 *  2019-10.22
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/MobileApi/SysRole")
//@Api(value = "SysRoleController", tags = "角色管理")
@SuppressWarnings("all")
public class MobileRoleController {


	@Autowired
	private SysRoleService sysRoleService;

		
	@PostMapping(value="/selectSysRole")
//    @ApiOperation(value = "依据id查询角色")
    public RespObject<?> selectSysRole(@RequestBody SysRole sysRole) {
        SysRole sum= sysRoleService.selectSysPower(sysRole.getId());
        return RespUtils.buildOKWithDataYg(sum);
    }
	
		
	@PostMapping(value="/findSysPowerOne")
//	@ApiOperation(value = "角色唯一性")
	public RespObject<?> findSysPowerOne(@RequestBody  SysRole sysRole) {
	    boolean sum= sysRoleService.findSysPowerOne(sysRole.getRoleNum());
        return RespUtils.buildOKWithDataYg(sum);
	}
	
	
	
	@PostMapping(value="/addRole")
//	@ApiOperation(value = "新增角色")
	public RespObject<?> addRole(@RequestBody  SysRole sysRole) {
	    boolean success = true;
	    UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
	    // 返回0 异常 1 成功 2角色名称重复 
	   Integer result = -1;
	    //查询是否有该角色编号
	    int sum= sysRoleService.powerNameIsExist(sysRole);
	    if(sum>0) {
	        return RespUtils.buildOKWithDataYg(2); 
	    }
	     //根据用户类型查询当前编号id+1
	    String	id = sysRoleService.getIdByUserType(sysRole.getRoleUser());
	    //插入数据库
	    sysRole.setRoleNum(id);
	    result= sysRoleService.addSysPower(sysRole);
        return RespUtils.buildOKWithDataYg(result);
	}

	@PostMapping(value="/updateSysRole")
//	@ApiOperation(value = "修改角色")
	public RespObject<?> updateSysRole2(@RequestBody  SysRole sysRole) {
	    // 返回0 异常 1 成功 2角色名称重复 
	   Integer result = -1;
	    //查询是否有该角色编号
	    int sum= sysRoleService.powerNameIsExist(sysRole);
	    if(sum>0) {
	        return RespUtils.buildOKWithDataYg(2);
	    }
	    //插入数据库
	    result= sysRoleService.updateSysPower(sysRole);
        return RespUtils.buildOKWithDataYg(result); 
	}
		
	
	@PostMapping(value="/deleteSysRole")
//	@ApiOperation(value = "删除角色")
	public RespObject<?> deleteSysRole(@RequestBody  SysRole sysRole) {
	    int sum= sysRoleService.deleteSysPower(sysRole); 
        return RespUtils.buildOKWithDataYg(sum); 
	}
	
	@PostMapping(value = "/queryPage")
//	@ApiOperation(value = "分页/条件查询角色")
    public RespObject<?> queryPage(@RequestBody Map<String,String> map ){
		//拿数据
	    Page sysConfigPage = sysRoleService.findSysConImplPage(map);
        return RespUtils.buildOKWithDataYg(sysConfigPage); 
    }

//	@PostMapping(value="/selectAll")
//	@ApiOperation(value = "查询所有角色")
//	public Result selectAll() {
//	    boolean success = true;
//	    UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//	    List<SysRole> list= sysRoleService.list();
//	    return  new Result(success,list, msg);  
//	}
	

//	//查询角色权限
//	@PostMapping(value="/selectPowerRole")
//	@ApiOperation(value = "查询角色权限")
//	public Result selectPowerRole(@RequestBody  SysRole sysRole) {
//	    boolean success = true;
//	    UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//	  
//	    if ("".equals(sysRole.getRoleNum())&& sysRole.getRoleNum()==null) {
//	    	//当传入的为null的时候，默认为第1次进入，默认显示超级管理员，对应编码-001
//	    	sysRole.setRoleNum("001");
//		}
//	    List<Map<String, Object>> list= sysRoleService.powerList(sysRole.getRoleNum());
//	    return  new Result(success,list, msg);  
//	}


	
	
	
	
	
	
	
	
	
	
	
}
