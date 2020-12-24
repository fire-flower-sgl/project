package com.tools.modules.service;

import java.util.List;
import java.util.Map;

import com.mobile.model.Page;


/**
 * 
 * @ClassName:  ChartDao   
 * @Description:	图表配置接口   
 * @Author: admin
 * @Date:   Sep 11, 2019 6:49:06 PM   
 * @Version: 1.0
 */
public interface ChartService {

	List<Map<String, Object>> queryParm(String businessName);//拿自定义参数	
	Map<String, Object> queryColorTemplate(String chartName);//拿颜色模板	
	Map<String, Object> queryTemplate(String businessName);//根据业务名称拿模板

	Page findChartPage(Map<String, String> map);//分页查找
	int findPageCount(String sql_count);//查询数量
	int updatechartConfiguration(Map<String, Object> newParm, String businessName);//小齿轮更新数据

	

	
	
	
	

	
}
