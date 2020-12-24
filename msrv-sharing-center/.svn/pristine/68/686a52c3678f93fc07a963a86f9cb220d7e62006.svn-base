package com.mhtech.platform.msrv.sharing.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mhtech.platform.msrv.pojo.GatewayAccessLogVO;
import com.mhtech.platform.msrv.sharing.dao.mapper.GatewayAccessLogMapper;
import com.mhtech.platform.msrv.sharing.dao.model.GatewayAccessLog;
import com.mhtech.platform.msrv.sharing.service.IGatewayAccessLogService;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

@Component
public class RequestAccessLogRefresher {

	@Autowired
	private IGatewayAccessLogService iGatewayAccessLogService;
	@Autowired
	private RedisUtils redisUtils;
	@Value("${task.effectiveTime}")
	private Long time;
	
	private int maxLen = 200;

	private final Logger logger = LoggerFactory.getLogger(RequestAccessLogRefresher.class);
	
	@Scheduled(fixedDelay = 1000 * 60 * 60)
//	@Scheduled(fixedDelay = 1000 * 5)
	@Transactional
	public void getBlackIpsList() {
		String key = "";
		try {
			key = this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean lock = redisUtils.lock(key,time);
			if(lock) {
				Map<Object, Object> userAccessLogMaps = new HashMap<Object, Object>();
				Set<Object> $keys = redisUtils.hmgetKeys("userAccessLog");
				if ($keys.size() > 0) {
					int len = maxLen;
					int idx = 0;
					for (Object _key : $keys) {
						if (idx >= ($keys.size() > len ? len : $keys.size())) {
							break;
						}
						idx++;
						userAccessLogMaps.put(_key, redisUtils.hget("userAccessLog", _key.toString()));
					}
				}	
				if (null != userAccessLogMaps && userAccessLogMaps.size() > 0) {
					List<GatewayAccessLog> galDTOList = new ArrayList<GatewayAccessLog>();
					for (Object ip : userAccessLogMaps.keySet()) {
						GatewayAccessLogVO galVO = (GatewayAccessLogVO) userAccessLogMaps.get(ip);
						GatewayAccessLog gatewayAccessLog = new GatewayAccessLog();
						gatewayAccessLog.setId(galVO.getId());
						gatewayAccessLog.setIp(galVO.getIp());
						gatewayAccessLog.setUrl(galVO.getUrl());
						gatewayAccessLog.setParams(galVO.getParams());
						gatewayAccessLog.setCreatedTime(galVO.getCreatedTime());
						gatewayAccessLog.setRequestBody(galVO.getRequestBody());
						gatewayAccessLog.setResponseBody(galVO.getResponseBody());
						galDTOList.add(gatewayAccessLog);
					}
					// 保存到数据库
					int sum = iGatewayAccessLogService.save(galDTOList);
					// 清空redis缓存中的消息对象
					galDTOList.forEach(GatewayAccessLog -> {
						redisUtils.hdel("userAccessLog", GatewayAccessLog.getId().toString());
					});
					logger.info("redis定时器保存请求日志条数==" + sum);
				}
			}
		} catch (Exception e) {
			logger.error("redis定时器保存请求日志报错");
			logger.error(e.getMessage());
		}finally {
			redisUtils.delete(key);
		}
	}
}
