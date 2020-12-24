package com.mhtech.platform.msrv.monitor.service;

import java.util.List;

import com.mhtech.platform.msrv.pojo.ServerAdminVO;
import com.mhtech.platform.msrv.pojo.ServerDiagramVO;
import com.mhtech.platform.msrv.pojo.ServerDiagramsVO;

public interface IServerDiagramService extends IBaseService{
	/**
	 * 根据业务编号查找业务流程图
	 * @param businessId
	 * @return
	 */
	ServerDiagramsVO findServerDiagram(Long businessId);

	/**
	 * 根据业务编号删除业务流程图
	 * @param businessId
	 */
	void delByBusinessId(Long businessId);

	/**
	 * 获取服务下拉列表
	 * @return
	 */
	ServerAdminVO getServerList();

	/**
	 * 获取可选择的服务下拉列表
	 * @return
	 */
	ServerAdminVO getServerListForTrue();

	
	/**
	 * 根据业务编号ServerId查找业务流程图
	 * @param ServerId
	 * @return
	 */
	List<ServerDiagramVO> findServerDiagramByServerId(Long ServerId);
	
}
