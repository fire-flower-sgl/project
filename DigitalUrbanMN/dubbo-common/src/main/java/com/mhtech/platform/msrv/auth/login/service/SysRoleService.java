package com.mhtech.platform.msrv.auth.login.service;


import java.util.List;
import java.util.Map;

import com.mobile.model.Page;
import com.mobile.model.SysRole;

public interface SysRoleService {

	//查询
	SysRole selectSysPower (String id);
	//新增  
	int insertSysPower (SysRole sysRole);
	//修改
	int updateSysPower (SysRole sysRole);
	//删除
	int deleteSysPower (SysRole sysRole);
	//查询所有
	List<SysRole> list(); 
	//查询所有
	List<Map<String, Object>> roleList(); 
	//查询角色所具有的权限
	List<Map<String, Object>> powerList(String roleNum); 
	
	//显示所有-分页+模糊
	Page findSysConImplPage(Map<String, String> map);//分页模糊查询
	//角色编码唯一性判断
	boolean findSysPowerOne(String roleNum);
	//角色名称唯一性判断
	Integer powerNameIsExist(SysRole sysRole);
	
	String getIdByUserType(String type);
	
	Integer addSysPower(SysRole sysRole);
	
}
