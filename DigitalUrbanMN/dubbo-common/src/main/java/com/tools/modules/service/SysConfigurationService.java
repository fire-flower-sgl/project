package com.tools.modules.service;

import java.util.List;
import java.util.Map;

import com.mobile.model.Page;
import com.tools.modules.entity.SysConfiguration;

/**
 * 
 * @ClassName:  SysConfigurationDao   
 * @Description:图表参数配置管理接口
 * @author: admin
 * @date:   Aug 28, 2019 10:32:17 AM
 */
public interface SysConfigurationService {

	Page findSysConImplPage(Map<String, String> map);//分页模糊查询
	int findCount(String sql_count);//查询数量
	int addSysConfin(SysConfiguration sysConfiguration);//增加新配置
	int updateSysConfin(SysConfiguration sysConfiguration);//更新配置
	int delById(SysConfiguration sysConfiguration);//删除
	Map<String, Object> findById(String sysConfigId);//查找单个
	
	int updateOne(String businessName,String value);
	
	int[] findAdd(List<SysConfiguration> list);//批量增加
	int[] findUpdate(List<SysConfiguration> list,String businessName);//批量，先删除，在新增
	int[] findUpdate(List<SysConfiguration> list);//批量修改
	int[] findDel(String businessName);//批量刪除
	boolean fingByName(String businessName); //判断名称是否存在
	

 
    List<SysConfiguration> mapListYw(String businessName); //根据业务名称查询，对应图表参数。
    String findBusinessName(String chartId); //根据chartid查找模板名称
    boolean fingfungType(String businessName);//判断业务名是否是模板
    // 依据模板，业务名称，name获取value
    String fingValue(String businessName,String name);
	/**
	 * TODO
	 * @param map
	 * @return
	 */
	Page findConImplPageTemplate(Map<String, String> map);
   
}
