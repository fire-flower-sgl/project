package com.mhtech.platform.msrv.monitor.service;

import java.util.List;
import java.util.Map;

import com.mhtech.platform.msrv.pojo.SpAlertNotifyTmpl;
import com.mhtech.platform.msrv.pojo.Task;
import com.mobile.model.Page;

/**
 * 参数字典管理
 * @author Guo
 *
 */
public interface SpAlertNotifyTmplService {

	Page getList(Map<String,Object> map);//查询告警通知模板
	
	int add(SpAlertNotifyTmpl spAlertNotifyTmpl);//新增告警通知模板
	
	int modify(SpAlertNotifyTmpl spAlertNotifyTmpl);//修改告警通知模板
	
	int delete(SpAlertNotifyTmpl spAlertNotifyTmpl);//删除告警通知模板
	
	List<Map<String, Object>> getEntity(String id);//获取一条告警通知模板
	
}
