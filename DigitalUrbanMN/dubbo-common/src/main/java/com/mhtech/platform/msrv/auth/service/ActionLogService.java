package com.mhtech.platform.msrv.auth.service;

import com.mhtech.platform.msrv.pojo.GatewayAccessLogVO;

public interface ActionLogService {
	
	/**
	 * 	操作日志保存到数据库
	 * @param GatewayAccessLogVO
	 */
	void saveActionLog(GatewayAccessLogVO gatewayAccessLog);

}
