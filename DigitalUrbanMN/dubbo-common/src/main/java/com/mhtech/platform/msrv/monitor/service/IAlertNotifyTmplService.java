package com.mhtech.platform.msrv.monitor.service;

import com.mhtech.platform.msrv.pojo.AlertNotifyTmpl;

public interface IAlertNotifyTmplService {

	/**
	 * 获取使用中的通知模板
	 * @param serverId 服务KEY
	 * @return
	 */
	AlertNotifyTmpl getServerUsingNotifyTmpl(long serverId);
	
	/**
	 * 新增通知模板
	 * @param server
	 */
	String addServerInfo(AlertNotifyTmpl server);
}
