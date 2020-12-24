package com.mhtech.platform.msrv.monitor.service;

import java.util.List;
import java.util.Map;

import com.mobile.model.Page;
import com.mobile.model.SysParameter;


/**
 * 参数字典管理
 * @author Guo
 *
 */
public interface ISysParameterService {

	/**
	 * 根据参数字典编码查询相应的参数
	 * @param parmType 参数字典类型
	 */
	List<Map<String, Object>> findParameterByParmType(String parmType);

	/**
	 * TODO
	 * @param parmType
	 * @param parmCode
	 * @param parmName
	 * @return
	 */
	List<Map<String, Object>> findParameterByParmCodeOrParmName(String parmType, String parmCode, String parmName);
	
	int add(SysParameter sysParameter);//新增
    int delete(String id);//删除
    int update(SysParameter sysParameter);//更新
    
    SysParameter fingSysParameter(String id);//根据id查询对象
    Page findAllPage(Map<String,String> map);//分页
    int findPageCount(String sql_count);  //分页总条数
    boolean findTypeCode(String parmType,String parmCode);//参数类型+参数编码是否唯一 
    List<SysParameter> fingList(String parm_func);//取参数字典对应业务参数
    String fingName(String parm_type);//更具parm_type查询 parm_desc 
	String getParmName(String parmType,String parmCode);
	
	List<Map<String, Object>>  getCodeType(String parmType);
	List<Map<String, Object>>  fingParmCode(String parmType);//依据parmType查询parmCode
	
}
