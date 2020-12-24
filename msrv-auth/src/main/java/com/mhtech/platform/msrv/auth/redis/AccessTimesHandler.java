package com.mhtech.platform.msrv.auth.redis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mhtech.platform.msrv.auth.dao.model.SysAccessCount;
import com.mhtech.platform.msrv.auth.service.AccessCountService;
import com.mhtech.platform.msrv.auth.utils.RedisUtils;
import com.mhtech.platform.msrv.auth.utils.Utils;



public class AccessTimesHandler {
	
	@Autowired
	AccessCountService accessCountService;
	@Autowired
	RedisUtils redisUtils;
	
	public void accessTimesHandler(String ip) {	
		
		
		//查询数据
		SysAccessCount accessCount = accessCountService.findByIp(ip);
		
		Map<String, Object> accessInfo = (Map<String, Object>) redisUtils.hget("accessCount", ip);
		redisUtils.hdel("accessCount", ip);
		
		if(accessInfo!=null) {		
			//判断为空
			if(null==accessCount) {
				//新建
				accessCount = new SysAccessCount();
				accessCount.setId(Utils.getUUID());
				accessCount.setIp(accessInfo.get("ip").toString());
				accessCount.setTotal(Integer.parseInt(accessInfo.get("total").toString()));
				accessCount.setIntercept(Integer.parseInt(accessInfo.get("intercept").toString()));
				accessCountService.save(accessCount);
			}else {
				//更新
				accessCount.setTotal(accessCount.getTotal()+Integer.parseInt(accessInfo.get("total").toString()));
				accessCount.setIntercept(accessCount.getIntercept()+Integer.parseInt(accessInfo.get("intercept").toString()));
				accessCountService.updateByid(accessCount);
			}
		}
	}

}
