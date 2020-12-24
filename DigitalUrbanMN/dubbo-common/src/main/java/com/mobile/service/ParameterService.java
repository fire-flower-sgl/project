package com.mobile.service;

import java.util.List;
import java.util.Map;

import com.mobile.model.Page;
import com.mobile.model.SysParameter;

public interface ParameterService {
	List findParameterList(String types, String status);
	 boolean findTypeCode(String parmType,String parmCode);//参数类型+参数编码是否唯一
	 /**
	  * 参数字典数据查询
	  * @param map
	  * @return
	  */
	 List<Map<String, Object>> findParameter(Map<String,Object> map);
	 
	 
	 List<Map<String, Object>> queryParmList(String string);//获取参数列表
	 Page findAllPage(Map<String, String> map);//分页查询
	int add(SysParameter sysParameter);//新增
	int delete(String string);//删除
	int update(SysParameter sysParameter);//修改
	/**
	 * 根据类型、code 获取参数字典信息
	 * @param types
	 * @param code
	 * @return
	 */
	Map<String, Object> findParameter(String parmType,String parmCode);
}
