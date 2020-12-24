package com.mhtech.platform.msrv.auth.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mhtech.platform.msrv.auth.dao.model.FrequencyLimit;
import com.mhtech.platform.msrv.auth.dao.model.SysBlackList;
import com.mhtech.platform.msrv.auth.service.FrequencyLimitService;
import com.mhtech.platform.msrv.auth.utils.RedisUtils;

@Component
public class RequestFrequencyRefresher {

	@Autowired
	private FrequencyLimitService frequencyLimitService;
	@Autowired
	private RedisUtils redisUtils;
	@Value("${task.effectiveTime}")
	private Long time;

	private final Logger logger= LoggerFactory.getLogger(RequestFrequencyRefresher.class);
	
	@Scheduled(fixedDelay = 1000 * 60 * 60 * 2)
	public void getBlackIpsList() {
		String key = "";
		try {
			key = this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean lock = redisUtils.lock(key,time);
			if(lock) {
				//获取所有用户请求阈值集合
				List<FrequencyLimit> frequencyList = frequencyLimitService.queryAll();
				//清除
				redisUtils.del("frequencyList");
				//提供ip集合
				List<Object> ipList = new ArrayList<Object>();
				for (int i = 0; i < frequencyList.size(); i++) {
					redisUtils.hset("frequencyList", frequencyList.get(i).getIp(), frequencyList.get(i).getRate());
				}
				logger.info("用户阈值加载完毕!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户阈值加载失败!!!");
		}finally {
			redisUtils.delete(key);
		}
		
	}

}
