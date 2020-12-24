package com.mobile.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.mobile.model.Page;

public interface OrgFuncService {
	
	Page findPage(JSONObject reqObject);					//分页/条件查询
	boolean add(JSONObject reqObject);						//增加组织职能
	int delByOrgCode(String funcCode);						//根据funcCode删除
	Map<String, Object> queryByFuncCode(String funcCode);	//根据funcCode查找该节点//验证唯一
	boolean updateByid(JSONObject reqObject);				//更新节点
	
	List<Map<String, Object>> queryCompanyList();					//查找公司集合
	List<Map<String, Object>> queryFuncList(JSONObject reqObject);	//职能list
	List<Map<String, Object>> queryFuncListByCompanycode(String companyCode);	//职能listByCompanyCode
	List<Map<String, Object>> funcListByUserCode(String userCode);	//查找该用户的职能

}
