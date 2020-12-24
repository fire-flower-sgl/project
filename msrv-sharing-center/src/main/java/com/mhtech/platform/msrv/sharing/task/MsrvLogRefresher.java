package com.mhtech.platform.msrv.sharing.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.pojo.MsrvCode;
import com.mhtech.platform.msrv.pojo.MsrvLogVO;
import com.mhtech.platform.msrv.pojo.MsrvMessage;
import com.mhtech.platform.msrv.sharing.dao.model.MsrvLog;
import com.mhtech.platform.msrv.sharing.service.MsrvLogService;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

@Component
public class MsrvLogRefresher {

	@Autowired
	private MsrvLogService msrvLogService;
	
	@Autowired
	private IworkService iworkService;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${task.effectiveTime}")
	private Long time;
	
	@Value("${task.addMsrvLog.max}")
	private int maxLen;

	private final Logger logger = LoggerFactory.getLogger(MsrvLogRefresher.class);


//	@Scheduled(cron = "0 */5 * * * ?")
	@Scheduled(fixedDelay = 1000 * 60)
	@Transactional(rollbackFor = Exception.class)
	public void getMsrvLogRefresher() {
		logger.info("come into MsrvLogRefresher....");
		String key = "";
		try {
			key = this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean lock = redisUtils.lock(key,time);
			if(lock) {
				List<MsrvLogVO> msrvLogVO = new ArrayList<MsrvLogVO>();
				List<String> keys = new ArrayList<String>();
				
				Set<Object> $keys = redisUtils.hmgetKeys("MsrvLog");
				if ($keys.size() > 0) {
					int len = maxLen;
					int idx = 0;
					for (Object _key : $keys) {
						if (idx >= ($keys.size() > len ? len : $keys.size())) {
							break;
						}
						MsrvMessage msrvMessage = (MsrvMessage) redisUtils.hget("MsrvLog", _key.toString());
						if(msrvMessage!=null && !StringUtils.isEmpty(msrvMessage.getStatus()) && msrvMessage.getStatus().equals(MsrvCode.COMPLETE.getCode())) {
							List<MsrvLogVO> msrvLogList = msrvMessage.getMsrvLogList();
							if(msrvLogList.size()>0) {
								msrvLogList.forEach(mesrvlogV ->{
									msrvLogVO.add(mesrvlogV);
								});
								keys.add(_key.toString());
								idx++;	
							}
						}
					}
					int sum = 0;
					int aFlag = 0;
					List<MsrvLog> msrvLogList = new ArrayList<MsrvLog>();
					if(msrvLogVO.size()>0) {
						for (MsrvLogVO mesrvlogV : msrvLogVO) {
							sum++;
							aFlag++;
							// 处理日志操作
							MsrvLog msrvLog = new MsrvLog(iworkService.getNextId(), mesrvlogV.getTraceId(), mesrvlogV.getSide(), mesrvlogV.getApplication(), mesrvlogV.getInterfaceName(), mesrvlogV.getMethods(), mesrvlogV.getHost(), mesrvlogV.getCreatedTime(), mesrvlogV.getArguments());
							msrvLogList.add(msrvLog);		
							if (aFlag >= 1000) {
								msrvLogService.save(msrvLogList);
								msrvLogList.clear();
								aFlag = 0;
							}
						}	
					}
					// 保存到数据库
					if (msrvLogVO.size() > 0) {
						msrvLogService.save(msrvLogList);
					}
					// 删除对应的缓存
					if(!CollectionUtils.isEmpty(keys))
					redisUtils.hdel("MsrvLog", keys.toArray());
					logger.info("redis定时器保存服务调用日志==" + sum);
				}else {
					logger.info("无日志 ....");
				}
			}
		} catch (Exception e) {
			logger.error("redis定时器保存-保存服务调用日志-报错");
			logger.error(e.getMessage());
			e.printStackTrace();
		}finally {
			redisUtils.delete(key);
		}
	}
}
