package com.tools.modules.service;

import java.util.List;
import java.util.Map;

import com.mobile.model.Page;

/**
 * 
 * @ClassName:  EventDao   
 * @Description:事件管理接口  
 * @author: admin_
 * @date:  2019  22 11:10  
 */
public interface EventService {

	Page findEventPage(Map<String,String> map);				//分页/条件查询
	int findEventPageCount(String sql_count);				//查询数量
	Map<String, Object> findEventById(String eventId);		//根据id查询
	List<Map<String, Object>> findSourseList();				//查询数据源
	List<Map<String, Object>> findTypeList();				//查询类型
	List<Map<String, Object>> findAreaList(String userCode,String speNum);				//查询区域
	List<Map<String, Object>> findCompanyList();			//查询城管公司
	List<Map<String, Object>> findDepartmentList();			//查找处理列表
	List<Map<String, Object>> findStateList();				//查找状态列表
	List<Map<String, Object>> findShiftList();				//查询班次列表
	List<Map<String, Object>> findInterfaceNameList();		//接口名称列表

	Map<String, Object> querySource(String businessName);//业务配置表拿来源参数
	Map<String, Object> queryArea();//业务配置表拿行政区参数
	List<Map<String, Object>> queryDataListByArea(String startTime,String endTime,String parmType,List areaName);//拿数据 
	List<Map<String, Object>> queryDataListByAreaForMb();//拿数据 
	
	Map<String, Object> queryType();//业务配置表拿类型参数
	List<Map<String, Object>> queryDataListByTpye(String stratTime,String endTime,String eventSource,List areaName);
	List<Map<String, Object>> queryDataListByTpye();
	
	Map<String, Object> queryState();//业务配置表拿状态参数
	List<Map<String, Object>> queryDataListByState(String startTime,String endTime,String eventsource,String areaCode ); 
	
	
	Map<String, Object> queryUnit();//业务配置表拿状态参数
	List<Map<String, Object>> queryDataListByStateAndUnit(String areaName);
	List<Map<String, Object>> queryDataListByStateAndUnit(String areaName,String startTime,String endTime,String eventsource);
	
	List<Map<String, Object>> queryDataListByUnitAndArea(String startTime,String endTime,String eventsource,List areaName);
	
	Map<String, Object> queryAreaAll();//业务配置表拿行政区参数(含全部)
	Map<String, Object> queryTimeZone();//业务配置表拿时区参数
	
	List<Map<String, Object>> queryDataListByTimeZone();
	List<Map<String, Object>> queryDataListByTimeZone(String stratTime,String endTime, String eventSource ,List areaName);
	
	List<Map<String, Object>> queryDataListByUnitId(String eventSource,String eventarea,String stratTime,String endTime,String unitName);
	
	List<Map<String, Object>> queryDate();//从数据库获取最近7天title
	List<Map<String, Object>> queryDateByTime(String endTime);//从数据库获取最近7天title
	List<Map<String, Object>> queryDate2();//从数据库获取所有 日期 ，月，日
	List<Map<String, Object>> queryDataListByDate(String startTime,String endTime,String eventsource,List areaName);
	//时间段
	List<Map<String, Object>> queryDataListByDate1(String startTime,String endTime,String eventsource,List areaName);

	List<Map<String, Object>> findCountbyUnit(Map<String, String> map);//根据状态和区域查询事件数量
	
	Map<String, Object> findColor(Map<String, String> map);//取颜色
	
	Map<String, Object> findParmaBy(Map<String, String> map);//取自定义参数
	
	List<Map<String, Object>> queryMapList();//查找地图list
	Map<String, Object> queryMapType(Map<String,String> map);//查找地图类型
	Integer updateMapType(Map<String, String> map);//更新地图选项
	
	Map<String, Object> queryParmByBusinessNameAndParmKey(String businessName,String parmKey);//查找参数
	
	
	List<Map<String, Object>> queryParmList(String parmType);//查找参数list
	List<Map<String, Object>> queryareanameList(List areaCode);//查找参数list
	
	List<Map<String, Object>> querySubtypeList(String stratTime,String endTime, String eventSource ,List areaName);//统计12345平台子类型数据
	
	
	String findValueByParmType(String parmType);
	
	

	
}
