package com.mhtech.platform.msrv.sharing.redis;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mhtech.platform.msrv.pojo.GatewayAccessLogVO;
import com.mhtech.platform.msrv.sharing.dao.model.GatewayAccessLog;
import com.mhtech.platform.msrv.sharing.service.IGatewayAccessLogService;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;


public class AccessLogsHandler {
	
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	private IGatewayAccessLogService iGatewayAccessLogService;
	
	private final Logger logger= LoggerFactory.getLogger(AccessLogsHandler.class);
	
	public void accessLogHandler(GatewayAccessLogVO gatewayAccessLogVO) {
		GatewayAccessLog  gatewayAccessLog  = new GatewayAccessLog();
		try {
			gatewayAccessLog.setId(gatewayAccessLogVO.getId());
			gatewayAccessLog.setIp(gatewayAccessLogVO.getIp());
			gatewayAccessLog.setUrl(gatewayAccessLogVO.getUrl());
			gatewayAccessLog.setParams(gatewayAccessLogVO.getParams());
			gatewayAccessLog.setRequestBody(gatewayAccessLogVO.getRequestBody());
			gatewayAccessLog.setCreatedTime(gatewayAccessLogVO.getCreatedTime());
			gatewayAccessLog.setResponseBody(gatewayAccessLogVO.getResponseBody());
			int save = iGatewayAccessLogService.save(Arrays.asList(gatewayAccessLog));
			logger.info("请求日志插入数据库=="+save);
			logger.info("数据=="+gatewayAccessLog);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return;
		}
		redisUtils.hdel("userAccessLog", gatewayAccessLog.getId().toString());	
	}

}
