package com.mhtech.platform.msrv.auth.login.service;

import java.util.List;
import java.util.Map;

import com.mobile.model.Page;
import com.mobile.model.SysPower;

/**
 *  mjl_
 *  SysPowerDao
 *  系统权限类接口
 *  2019-10.22
 */
public interface SysPowerService {
	
	//依据编码查询权限
	SysPower selectSysPower (String id);
	//新增权限
	int insertSysPower (SysPower sysPower);
	//新增权限-------添加url的接口
	int insertSysPowers (SysPower sysPower);
	//修改权限
	int updateSysPower (SysPower sysPower);
	
	//修改权限-----------添加url的接口
	int updateSysPowers (SysPower sysPower);
	
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
	
	
}


