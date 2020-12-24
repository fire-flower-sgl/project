package com.tools.modules.service;



import java.util.List;
import java.util.Map;

import com.mobile.model.Page;
import com.tools.modules.entity.SysChart;


public interface SysChartService {

	int add(SysChart sysChart);//新增
    int delete(String chartId);//删除
    int update(SysChart SysChart);//更新
    
    boolean findByName(String chartName);//名称是否唯一
    boolean findById(String chartId);//编码是否唯一
    
    Page findAllPage(Map<String,String> map);//分页
    int findPageCount(String sql_count); //分页总条数
    
    List<Map<String,Object>>  fingByName();//查询所有模板名称
    String selectId(String chartName);//根据名称查询id
    String selectType(String chartId);//根据chartid查询类型
    String selectType2(String chartName);//根据chartid查询类型
    String selectChartName(String chartId);//根据chartid查询模板名
    
    
}
