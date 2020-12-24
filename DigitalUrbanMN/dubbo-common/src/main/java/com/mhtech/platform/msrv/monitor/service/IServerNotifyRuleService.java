package com.mhtech.platform.msrv.monitor.service;

import javax.validation.constraints.NotNull;

import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ServerNotifyRule;
import com.mhtech.platform.msrv.pojo.ServerNotifyRuleVO;

public interface IServerNotifyRuleService extends IBaseService{

	/**
	 * 查询服务通知的接收规则
	 * @param serverId
	 * @return
	 */
	ServerNotifyRule getServerNotifyRule(long serverId);
	
	
	
	/**
	 * 新增通知模板
	 * @param server
	 */
	String addServerInfo(ServerNotifyRule server);


	/**
	 * 分页查询通知规则
	 * @param rule
	 * @return
	 */
	Page<ServerNotifyRuleVO> queryPage(ServerNotifyRule rule);


	/**
	 * 更新通知规则
	 * @param dx
	 */
	void update(ServerNotifyRule dx);


	/**
	 * 根据id查询通知规则
	 * @param id
	 * @return
	 */
	ServerNotifyRuleVO getServerNotifyRuleById(@NotNull Long id);
	
}
