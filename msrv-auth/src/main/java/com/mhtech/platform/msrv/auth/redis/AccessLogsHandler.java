package com.mhtech.platform.msrv.auth.redis;

import org.springframework.beans.factory.annotation.Autowired;

import com.mhtech.platform.msrv.auth.dao.mapper.SysGatewayAccessLogMapper;
import com.mhtech.platform.msrv.auth.dao.model.SysGatewayAccessLog;
import com.mhtech.platform.msrv.pojo.GatewayAccessLogVO;


public class AccessLogsHandler {
	
	@Autowired
	private SysGatewayAccessLogMapper accessLogMapper;
	
	public void accessLogHandler(GatewayAccessLogVO gatewayAccessLog) {	
		
		SysGatewayAccessLog sysGatewayAccessLog = new SysGatewayAccessLog(gatewayAccessLog.getId(), gatewayAccessLog.getIp(), gatewayAccessLog.getUrl(), gatewayAccessLog.getParams(), gatewayAccessLog.getRequestBody());
		accessLogMapper.insert(sysGatewayAccessLog);

	}

}
