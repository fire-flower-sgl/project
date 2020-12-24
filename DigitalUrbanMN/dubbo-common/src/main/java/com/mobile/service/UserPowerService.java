package com.mobile.service;

import java.util.List;
import java.util.Map;

import com.mobile.model.SpePower;
import com.mobile.model.SysPower;

public interface UserPowerService {

		//用户角色相关
		boolean addrole(String userCode,List<String> roleList);//用户增加角色
		boolean updateRole(String userCode, List<String> roleList);//更新用户角色
		boolean deleteRole(String userCode);//删除用户角色
		List<Map<String, Object>> queryRole(String userCode);//查询用户角色list
		
		boolean addPower(String roleNum, List<String> powerList);//角色增加权限
		boolean updatePower(String roleNum, List<String> powerList);//更新角色权限
		boolean deletePower(String roleNum);//删除角色权限
		List<Map<String, Object>> queryPower(String roleNum);//查询角色权限list

				
		//特殊权限
		boolean addSpePower(String userCode, List<Map<String, Object>> spePowerList);//用户增加超级权限

		boolean updateSpePower(String userCode, List<Map<String, Object>> spePowerList);//更新特殊权限
		
		boolean deleteSpePower(String userCode);//删除特殊权限
		
		List<SpePower> querySpeRole(String userCode);//查询用户特殊权限list
		List<Map<String, Object>> querySpeRoleListMap(String userCode);//查询用户特殊权限listMap
		
		List<Map<String, Object>> userInfo();//验证账户是否存在

		
		//查询用户所有的menu权限和html权限集合
		List<SysPower> queryMenuAndHtmlPower(String userCode);
		//查询用户html权限下的btn权限集合
		List<SysPower> queryBtnPower(String userCode,String htmlPowerNum);
		//查询所有特殊权限集合
		List<SpePower> querySpePower(String userCode);
		
		List<Map<String, Object>> querySpePower2(String userCode);
		//根据权限编号取到单条特殊权限
		Map<String, Object> getSpePowerInfo(String powerNum);
		//根据特殊权限的value执行的sql
		List<Map<String, Object>> querySpePowerValue(String speValue);
		
		boolean addFunc(String userCode, List<String> funcList);//增加用户职能
		boolean updateFunc(String userCode, List<String> funcList);//更新用户职能
		boolean deleteFunc(String userCode);//删除职能
		




}
