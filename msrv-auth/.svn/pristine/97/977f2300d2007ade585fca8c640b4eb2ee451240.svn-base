package com.mhtech.platform.msrv.auth.service.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.auth.dao.mapper.SysGatewayAccessLogMapper;
import com.mhtech.platform.msrv.auth.dao.model.SysGatewayAccessLog;
import com.mhtech.platform.msrv.auth.elasticsearch.ElasticSearchClient;
import com.mhtech.platform.msrv.auth.service.ActionLogService;
import com.mhtech.platform.msrv.pojo.GatewayAccessLogVO;



@Service("actionLogService")
public class ActionLogServiceImpl implements ActionLogService {
	
	@Autowired
	private SysGatewayAccessLogMapper accessLogMapper;
	@Autowired
	private ElasticSearchClient client;

	@Override
	public void saveActionLog(GatewayAccessLogVO gatewayAccessLog) {
//		SysGatewayAccessLog sysGatewayAccessLog = new SysGatewayAccessLog(gatewayAccessLog.getId(), gatewayAccessLog.getIp(), gatewayAccessLog.getUrl(), gatewayAccessLog.getParams(), gatewayAccessLog.getRequestBody());
//		accessLogMapper.insert(sysGatewayAccessLog);
//		client.addOrUpdateDocuments(Arrays.asList(sysGatewayAccessLog));	
	}
}
