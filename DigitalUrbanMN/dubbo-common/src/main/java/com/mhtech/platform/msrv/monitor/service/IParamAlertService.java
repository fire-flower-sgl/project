package com.mhtech.platform.msrv.monitor.service;

import java.util.List;

import com.mhtech.platform.msrv.pojo.MonitorStatus.ParamAlertStatus;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.log.pojo.ParamAlertDTO;
import com.mhtech.platform.log.pojo.ParamAlertSelectVO;
import com.mhtech.platform.msrv.pojo.ParamAlertInfo;
import com.mhtech.platform.msrv.pojo.ServerParamAlert;

/**
 * 预警指标管理
 * @author Guo
 *
 */
public interface IParamAlertService {

	/**
	 * 添加服务预警参数及阈值
	 * @param pai
	 */
	String addParamAlert(ParamAlertInfo pai);
	
	/**
	 * 查询服务的预警指标
	 * @param serverId 服务KEY
	 * @param pas 记录状态
	 * @return 指标
	 */
	List<ServerParamAlert> listAlerts4Server(long serverId, ParamAlertStatus pas);
	
	/**
	 * 修改预警指标状态
	 * @param pas
	 */
	void updateAlertingStatus(ParamAlertStatus pas);

	/**
	 * 查询服务的预警指标
	 * @param serverId 服务编号
	 * @param activator 记录状态
	 * @return 预警指标
	 */
	ServerParamAlert getParamAlert(Long serverId,String paramName,ParamAlertStatus activator);
	
	//依据条件查询+分页
	Page selectParamAlert(ParamAlertDTO dto);
	//总条数
	int selectSum(ParamAlertDTO dto);
}
