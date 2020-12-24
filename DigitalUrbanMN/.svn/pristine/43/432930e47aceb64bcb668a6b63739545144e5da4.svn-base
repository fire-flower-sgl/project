package com.mhtech.platform.msrv.monitor.service;

import java.util.List;
import java.util.Map;

import com.mhtech.platform.msrv.pojo.MonitorStatus.ServerStatus;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerBriefVO;
import com.mhtech.platform.msrv.pojo.ServerGrpInfo;
import com.mhtech.platform.msrv.pojo.ServerInfo;
import com.mhtech.platform.msrv.pojo.ServerPageInfo;

/**
 * 服务管理
 * @author Guo
 *
 */
public interface IServerAdminService{

	/**
	 * 新增服务信息
	 * @param server
	 */
	int addEntity(ServerInfo server);
	
	/**
	 * 新增服务信息
	 * @param server
	 */
	ServerBriefVO addServerInfo(ServerInfo server);
	
	/**
	 * 新增服务组信息，包含下级子服务
	 * @param grpInfo
	 */
	void addServerGrpInfo(ServerGrpInfo grpInfo);
	
	/**
	 * 更新服务信息
	 * @param server
	 * @return 
	 */
	int updateServerInfo(ServerInfo server);
	
	/**
	 * 修改服务状态
	 * @param status {@code Activator} or {@code Disabled}
	 */
	void updateServerStatus(ServerStatus status);
	
	/**
	 * 查询服务信息
	 * @param id 服务key
	 * @return 服务信息
	 */
	ServerAdminInfo getServerInfo(long id);
	
	/**
	 * 分页查询
	 * @param spi 
	 * @return
	 */
	Page<ServerAdminInfo> listPage(ServerPageInfo spi);
	
	/**
	 * 根据服务编号查询
	 * @param serverId
	 * @return
	 */
	ServerAdminInfo getServerInfoByServerId(Long serverId);
	
	/**
	 * 根据服务编号查询
	 * @param serverId
	 * @return
	 */
	List<Map<String, Object>> findServerInfoByServerName(String serverName);
	
	/**
	 * 根据服务编号查询
	 * @param serverId
	 * @return
	 */
	List<Map<String, Object>> findServerInfoByServerNameForId(String serverName);
	
	/**
	 * 根据id删除服务信息
	 * @param id
	 */
	void delById(String id);

	/**
	 * TODO
	 * @param idList
	 * @return
	 */
	int deleteByIds(List<String> idList);

	/**
	 * TODO 根据id获取业务
	 * @param serverId
	 * @return
	 */
	ServerAdminInfo getServerInfoByPrimaryKey(Long serverId);
	
}
