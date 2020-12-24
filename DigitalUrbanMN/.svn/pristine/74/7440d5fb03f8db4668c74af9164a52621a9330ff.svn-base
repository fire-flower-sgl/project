package com.mobile.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mobile.model.Page;
import com.mobile.model.SpePower;
import com.mobile.model.SysPower;
import com.mobile.model.SysRolePower;

public interface SysPowerService {
	
	//依据编码查询权限
	SysPower selectSysPower (String id);
	//新增权限
	int insertSysPower (SysPower sysPower);
	//修改权限
	int updateSysPower (SysPower sysPower);
	
	//依据编码删除权限
	int deleteSysPower (String id);
	
	//依据编码删除权限
	boolean deletePowerNum (String powerNum);
	//显示所有
	List<SysPower> list(); 
	//显示所有数据data权限
	List<SysPower> list2();
	//显示所有-分页+模糊
	Page findSysConImplPage(Map<String, String> map);//分页模糊查询
	//显示下拉权限
	List<SysPower> list3(); 
	
	List<Map<String, Object>> spePowerList();
	
	//权限编码唯一性判断
	boolean findSysPowerOne(String powerNum);
	
	//显示对应用户下的menu与html权限
	List<Map<String, Object>> list4(String userCode); 
	
	//jpa	
	List<SysPower> findByPowerTypeAndPowerFather();
		
	JSONObject getPowerRole(String roleNum);
	
	void savePowerRole(JSONArray rolePower, String roleNum);//保存角色权限
	
	List<SysRolePower> findByRoleNum(String roleNum);//查询角色权限
	
	void deleteByRoleNum(String roleNum);//删除角色权限
	
	void save(List<Object[]> list);//保存角色权限
	String speAuthority(String userCode, String speNum, String sql);//数据权限验证修改sql
	SpePower findSpePowerByUserCodeAndPowerNum(String userCode, String speNum);//根据用户编号和权限编号查询特殊权限
	String speAuthorityForCount(String userCode , String speNum);


}
