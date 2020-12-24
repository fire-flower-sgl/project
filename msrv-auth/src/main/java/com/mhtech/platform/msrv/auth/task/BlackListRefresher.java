package com.mhtech.platform.msrv.auth.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mhtech.platform.msrv.auth.dao.model.SysBlackList;
import com.mhtech.platform.msrv.auth.service.BlackListService;
import com.mhtech.platform.msrv.auth.utils.RedisUtils;

import io.lettuce.core.GeoArgs.Sort;

@Component
public class BlackListRefresher {

	@Autowired
	private BlackListService blackListService;
	@Autowired
	private RedisUtils redisUtils;
	@Value("${task.effectiveTime}")
	private Long time;

	private final Logger logger= LoggerFactory.getLogger(BlackListRefresher.class);
	
	@Scheduled(fixedDelay = 1000 * 60 * 60 * 2)
	public void getBlackIpsList() {
		String key = "";
		try {
			key = this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean lock = redisUtils.lock(key,time);
			if(lock) {
				//清除缓存
				redisUtils.del("blackIpList");
				//查询数据库黑名单集合
				List<SysBlackList> blackIPsModelList = blackListService.getBlackIpsList();
				//提供ip集合
				if(blackIPsModelList.size()>0) {
					List<Object> ipList = new ArrayList<Object>();
					for (int i = 0; i < blackIPsModelList.size(); i++) {
						ipList.add(blackIPsModelList.get(i).getIp());
					}
					//存入redis
					redisUtils.lSet("blackIpList",ipList);	
					logger.info("黑名单加载完毕!!!");
				}else {
					logger.info("黑名单加载完毕, 无黑名单用户!!!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("黑名单加载失败!!!");
		}finally {
			redisUtils.delete(key);
		}
		
	}
	
	@Scheduled(fixedDelay = 1000 * 60 )
	public void updateBlackIpsList() {
		String key = "";
		try {
			key = this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean lock = redisUtils.lock(key,time);
			if(lock) {
				//获取所有黑名单集合
				List<SysBlackList> blackIPsModelList = blackListService.getBlackIpsList();
				List<Object> dbIpList = new ArrayList<Object>();
				int update = 0;
				for (int i = 0; i < blackIPsModelList.size(); i++) {
					dbIpList.add(blackIPsModelList.get(i).getIp());
					long date = new Date().getTime();
					long lockedTime = blackIPsModelList.get(i).getLockedTime().getTime();				
					//判断时间差,更新黑名单
					if(date>lockedTime||date==lockedTime) {
						blackIPsModelList.get(i).setIsLocked(0);
						blackListService.updateById(blackIPsModelList.get(i));
						update++;
					}
				}
				if(update>0) {
					logger.info("数据库更新黑名单数量=="+update);
					getBlackIpsList();
				}else{
					List<Object> blackIpsList = redisUtils.lGet("blackIpList", 0, -1);
					blackIpsList.sort(Comparator.comparing(Object::hashCode));  
					dbIpList.sort(Comparator.comparing(Object::hashCode)); 
					if(!blackIpsList.toString().equals(dbIpList.toString())) {
						logger.info("数据库黑名单有变动, 更新黑名单redis");
						getBlackIpsList();
					}else {
						logger.info("数据库黑名单无变动!!!");
					}			
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新黑名单失败!!!");
		}finally {
			redisUtils.delete(key);
		}
	}
}
