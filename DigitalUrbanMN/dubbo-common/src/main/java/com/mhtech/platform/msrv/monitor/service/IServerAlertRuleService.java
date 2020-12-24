package com.mhtech.platform.msrv.monitor.service;

import java.util.List;

import com.mhtech.platform.log.pojo.ServerAlertRuleDTO;
import com.mhtech.platform.msrv.pojo.AlertInfoUpdate;
import com.mhtech.platform.msrv.pojo.AlertRuleInfo;
import com.mhtech.platform.msrv.pojo.AlertRuleVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ServerAlertRule;

/**
 * 预警规则管理
 * @author Guo
 *
 */
public interface IServerAlertRuleService {

	/**
	 * 新增预警规则
	 * @param rule
	 */
	int addAlertRule(AlertRuleInfo rule);
	
	/**
	 * 修改预警规则
	 * @param rule
	 */
	int updateAlertRule(AlertRuleInfo rule);
	
	/**
	 * 修改告警监控时间
	 * @param aip
	 */
	void updateAlertTime(AlertInfoUpdate aip);
	
	/**
	 * 删除预警规则
	 * @param ruleId 规则ID
	 */
	int removeAlertRule(Long ruleId);
	
	/**
	 * 查询服务特定指标的预警规则
	 * @param serverId 服务编码
	 * @param paramName 预警字段
	 * @return 预警规则
	 */
	List<ServerAlertRule> getServerAlertRuleByParam(long serverId, String paramName);
	/**
	 * 查询同一频率的的预警规则
	 * @param rule
	 * @return
	 */
	List<ServerAlertRule> getAlertRuleByDuration(AlertRuleVO rule);

	ServerAlertRule select(Long id);
	
	//依据条件查询
	Page selectList(ServerAlertRuleDTO dto);
	//总条数
	int selectSum(ServerAlertRuleDTO dto);
}
