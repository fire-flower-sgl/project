package com.mhtech.platform.msrv.monitor.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.mhtech.common.result.entity.CustomException;
import com.mhtech.platform.msrv.pojo.SpAlertNotifyTmpl;
import com.mhtech.platform.msrv.pojo.SpParamAlert;
import com.mhtech.platform.msrv.pojo.SpUserShift;
import com.mhtech.platform.msrv.pojo.Task;
import com.mobile.model.Page;

/**
 * 告警规则设置管理
 * @author Guo
 *
 */
public interface SpParamAlertService {

	Page getList(Map<String,Object> map);//查询告警规则设置
	
	int add(SpParamAlert spParamAlert);//新增告警规则设置
	
	int modify(SpParamAlert spParamAlert);//修改告警规则设置
	
	int delete(SpParamAlert spParamAlert);//删除告警规则设置
	
	List<Map<String, Object>> getEntity(String id);//获取一条告警规则设置
	
	JSONArray getAlertType();//获取一条告警规则设置

	/**
	 * TODO 修改设置通知
	 * @param spParamAlert
	 * @return
	 */
	int modifyRule(SpParamAlert spParamAlert);

	/**
	 * TODO 获取排班
	 * @return
	 */ 
	//List<Map<String, Object>> getRange();

	/**
	 * TODO 获取排班人员
	 * @param shitfId
	 * @return
	 */
	//String getRangeUser(List<String> shitfId);

	/**
	 * TODO
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getAlermReason(String id);

	/**
	 * TODO
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getAlermDeal(String id);

	/**
	 * TODO
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getAlermHistroy(String id, String startTime, String endTime);

	/**
	 * TODO
	 * @param id
	 * @param startTime 
	 * @param endTime
	 * @return
	 */
	int changeTempStopListenTime(String id, String startTime, String endTime);
	

	/**
	 * TODO 保存排班人员信息
	 * @return 
	 */
	int saveShiftUser(SpUserShift spUserShift);

	/**
	 * TODO 更新排班人员信息
	 * @param spUserShift
	 * @return
	 */
	int updateShiftUser(SpUserShift spUserShift);

	/**
	 * TODO 根据排班日期和服务器ip获取人员
	 * @param date
	 * @param ip
	 * @return
	 */
	JSONArray getRangeUser(String date, String ip);

	/**
	 * TODO
	 * @param id
	 * @param serverType
	 * @return
	 */
	String getIpByServerType(String id, String serverType) throws CustomException ;

	/**
	 * TODO
	 * @param spUserShift
	 * @return
	 */
	int delShiftUser(SpUserShift spUserShift);

	/**
	 * TODO 根据服务器获取排班日期
	 * @param ip
	 * @return
	 */
	JSONArray getRangeByIp(String ip);

	/**
	 * TODO
	 * @param response
	 * @param request
	 */
	void downloadExcel(HttpServletResponse response, HttpServletRequest request);

	/**
	 * TODO
	 * @param idList
	 * @return
	 */
	int deleteByIds(List<String> idList);
}
