package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerPageInfo;
import com.mhtech.platform.msrv.sharing.dao.model.ServerAdmin;

@Mapper
public interface ServerAdminMapper extends GenericMapper {
	
	ServerAdminInfo findServerDetail(@Param("id")long id);
	
	List<ServerAdminInfo> listServerAdmin(ServerPageInfo info);
	
	ServerAdmin findServerByServerId(Long serverId);

	List<ServerAdmin> getServerList();

	/**
	 * 获取正常下拉
	 * @return
	 */
	List<ServerAdmin> getServerListForTrue();

	/**
	 * TODO
	 * @param serverName
	 */
	List<Map<String, Object>> findServerInfoByServerName(String serverName);
	
	void delById(Long id);
	
	List<Map<String, Object>> findServerInfoByServerNameForId(String serverName);

	/**
	 * TODO
	 * @param list
	 * @return
	 */
	int deleteByIds(List<String> list);
}