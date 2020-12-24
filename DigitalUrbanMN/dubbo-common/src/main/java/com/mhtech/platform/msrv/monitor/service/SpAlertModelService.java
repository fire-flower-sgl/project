package com.mhtech.platform.msrv.monitor.service;

import java.util.List;
import java.util.Map;

import com.mhtech.platform.msrv.pojo.SpAlertModel;
import com.mhtech.platform.msrv.pojo.SpAlertNotifyTmpl;
import com.mhtech.platform.msrv.pojo.SpParamAlert;
import com.mhtech.platform.msrv.pojo.Task;
import com.mobile.model.Page;

/**
 * 告警规则模板
 * @author Guo
 *
 */
public interface SpAlertModelService {

	Page getList(Map<String,Object> map);//查询告警规则模板
	
	int add(SpAlertModel spAlertModel);//新增告警规则模板
	
	int modify(SpAlertModel spAlertModel);//修改告警规则模板
	
	int delete(SpAlertModel spAlertModel);//删除告警规则模板
	
	List<Map<String, Object>> getEntity(String id);//获取一条告警规则模板
	
	List<Map<String, Object>> getModelList(String modelName);//获取告警规则模板id,value列表
	
}
