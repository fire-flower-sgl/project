package com.mhtech.platform.msrv.auth.login.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.mobile.model.Page;
import com.mobile.model.User;


/**
 *  mjl_
 *  SpUserDao
 *  用户表接口
 *  2019-10.22
 */
public interface SpUserService {

	//查询
	User selectSysPower (String id);
	//新增  新增一个用户下-多个角色-角色对应系统权限与特殊权限
	String insertSysPower (JSONObject object);
	//修改
	int updateSysPower (User spUser);
	//修改
	int updateSysPowerInfo (User spUser);
	//删除
	int deleteSysPower (String userCode);
	
	//显示所有
	List<User> list(); 
	
	
	//新增多个用户下-多个角色-角色对应系统权限与特殊权限      （一个批处理事务）
	int[] insertAllUser(List<User> spUsers);
	
	//分页查找+模糊
	Page findPage(JSONObject object);
	//查找数量
	int findEventPageCount(String sql_count);
	
	//增加新用户
	String addNewUser(User spUser);
	/**
	 * TODO
	 * @return
	 */
	List<User> listCheck();

}
