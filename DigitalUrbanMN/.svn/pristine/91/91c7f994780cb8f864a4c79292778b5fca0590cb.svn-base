package com.mhtech.platform.msrv.auth.login.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.mobile.model.Page;

/**
 * 
 * @ClassName:  OrganizatDao   
 * @Description:组织管理接口  
 * @author: admin_
 * @date:  2019-11-11 17:102  
 */
public interface OrgFuncService {

	Page findPage(JSONObject reqObject);					//分页/条件查询
	int findCount(String sql_count);						//查询数量
	boolean add(JSONObject reqObject);						//增加组织职能
	int delByOrgCode(String funcCode);						//根据funcCode删除
	String updateAllByid(List<String> funcCodeList);		//删除选中的funcCode
	List<Map<String, Object>> queryById(String funcCode);	//根据funcCode查找所有子节点
	Map<String, Object> queryByOrgCode(String funcCode);//根据funcCode查找该节点//验证唯一
	boolean updateByid(JSONObject reqObject);				//更新节点
	
	List<Map<String, Object>> queryCompanyList();			//查找公司集合
	List<Map<String, Object>> queryFuncList(JSONObject reqObject);	//职能list
	List<Map<String, Object>> queryFuncList(String companyCode);	//职能listByCompanyCode
	List<Map<String, Object>> FuncListByUserCode(String userCode);	//查找该用户的职能
}
