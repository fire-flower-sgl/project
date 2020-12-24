package com.mobile.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.mobile.model.OrgInfo;
import com.mobile.model.Page;

public interface OrganizatService {

	Page findPage(JSONObject reqObject);					//分页/条件查询
	boolean addCode(JSONObject reqObject);					//增加子节点	
	int delByOrgCode(String orgCode);						//根据orgCode删除
	boolean queryByOrgCode(JSONObject reqObject);//根据orgCode查找该节点//验证唯一
	boolean updateByid(JSONObject reqObject);				//更新节点
	boolean checkIsUser(String orgCode);					//是否分配客户
	
	Map<String, Object> findByOrgCode(JSONObject reqObject);
	
	List<OrgInfo> findByTreeOrgCode(String companyCode);//获取公司下树形组织
	List<OrgInfo> treeByOrgCode(String orgCode);//获取某组织下树形组织

}
