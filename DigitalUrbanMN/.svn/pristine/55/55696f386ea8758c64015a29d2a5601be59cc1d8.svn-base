package com.mhtech.platform.msrv.monitor.service;

import java.util.List;

import com.mhtech.platform.msrv.pojo.MonitorPlansDTO;
import com.mhtech.platform.msrv.pojo.MonitorPlansVO;
import com.mhtech.platform.msrv.pojo.Page;

/**
 * 任务服务
 * @author 
 *
 */
public interface IMonitorPlansService {

	/**
	 * 条件查询监控集合
	 * @param mp
	 * @return
	 */
	List<MonitorPlansVO> getServerAlertRuleByParam(MonitorPlansDTO mp);
	
	/**
	 * 分页查询监控集合
	 * @param mp
	 * @return
	 */
	Page queryPage(MonitorPlansDTO mp);
	
	/**
	 * 新增监控计划
	 * @param mpDto
	 * @return
	 */
	int addMonitorPlan(MonitorPlansDTO mpDto);
	/**
	 * 更新监控计划
	 * @param mpDto
	 * @return
	 */
	int updateMonitorPlan(MonitorPlansDTO mpDto);
	/**
	 * 删除监控计划
	 * @param id
	 * @return
	 */
	int delMonitorPlan(Long id);
	
	/**
	 * 开启任务
	 * @param mpDto
	 */
	void startTask(MonitorPlansDTO mpDto);
	/**
	 * 停止任务
	 * @param id
	 * @return
	 */
	void stopTask(Long id);
	/**
	 * 立即执行
	 * @param mpDto
	 */
	void runOneTime(MonitorPlansDTO mpDto);
	/**
	 * 检查任务名称是否存在
	 * @param mp
	 * @return
	 */
	boolean isExist(MonitorPlansDTO mp);

}
