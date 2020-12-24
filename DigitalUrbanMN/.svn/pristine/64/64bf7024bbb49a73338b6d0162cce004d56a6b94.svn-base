package com.mobile.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.mobile.model.Page;

public interface CompanyService {
	
	Page findPage(JSONObject reqObject);						//分页/条件查询
	int add(JSONObject reqObject);							//增加企业	
	int delByCompanyCode(String companyCode);					//根据companyCode删除
	boolean companyCodeIsExist(JSONObject reqObject);	//根据companyId查找该节点//验证唯一
	Map<String, Object> queryByCompanyCode(JSONObject reqObject);//根据companyId查找该节点
	boolean companyNameIsExist(JSONObject reqObject);//验证companyName唯一
	int updateByid(JSONObject reqObject);				//更新企业

	
	List<Map<String, Object>> queryAreaListByParentCode(String parentCode);//查找所属区域
	List<Map<String, Object>> queryCompanyList(String userCode,String speNum);//企业list


}
