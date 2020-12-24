package com.mhtech.platform.msrv.sharing.task;

import java.util.ArrayList;
import java.util.Date;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.sharing.dao.model.UserActionDetail;
import com.mhtech.platform.msrv.sharing.dao.model.UserActionDetailWithBLOBs;
import com.mhtech.platform.msrv.sharing.dao.model.UserActionLog;
import com.mhtech.platform.msrv.sharing.service.UserActionDetailService;
import com.mhtech.platform.msrv.sharing.service.UserActionLogService;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

@Component
public class UserActionLogRefresher {

	@Autowired
	private UserActionLogService userActionLogService;
	@Autowired
	private UserActionDetailService userActionDetailService;
	@Autowired
	private IworkService iworkService;
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${task.effectiveTime}")
	private Long time;

	private final Logger logger = LoggerFactory.getLogger(UserActionLogRefresher.class);

	@Scheduled(fixedDelay = 1000 * 60)
//	@Scheduled(cron = "0 */5 * * * ?")
	@Transactional(rollbackFor = Exception.class)
	public void getUserActionLogRefresher() {
		logger.info("come into UserActionLogRefresher....");
		String key = "";
		try {
			key = this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean lock = redisUtils.lock(key,time);
			if(lock) {
				Map<Object, Object> actionMaps = new HashMap<Object, Object>();// redisUtils.hmget("userActionLog");
				Set<Object> $keys = redisUtils.hmgetKeys("userActionLog");
				if ($keys.size() > 0) {
					int len = 500;
					int idx = 0;
					for (Object _key : $keys) {
						if (idx >= ($keys.size() > len ? len : $keys.size())) {
							break;
						}
						idx++;
						actionMaps.put(_key, redisUtils.hget("userActionLog", _key.toString()));
					}
	
					List<UserActionLog> actionLogList = new ArrayList<UserActionLog>();
					List<UserActionDetail> actionDetailList = new ArrayList<UserActionDetail>();
					List<String> keys = new ArrayList<String>();
					int sum = 0;
					int detailSum = 0;
					int aFlag = 0;
					int bFlag = 0;
					for (Object id : actionMaps.keySet()) {
						sum++;
						aFlag++;
						// 处理日志操作
						JSONObject actionObj = JSON.parseObject(actionMaps.get(id).toString());
						Long newId = iworkService.getNextId();
						keys.add(actionObj.getString("id"));
						String userCode = actionObj.getString("userCode");
						String actionNum = actionObj.getString("actionNum");
						String actionModule = actionObj.getString("actionModule");
						Date actionStartTime = actionObj.getDate("actionStartTime");
						Date actionEndTime = actionObj.getDate("actionEndTime");
						UserActionLog userActionLog = new UserActionLog(newId, userCode, actionModule, actionNum,
								actionStartTime, actionEndTime);
						actionLogList.add(userActionLog);
						if (aFlag >= 1000) {
							userActionLogService.save(actionLogList);
							actionLogList.clear();
							aFlag = 0;
						}
						// 处理日志详细行为
						JSONArray jsonArray = actionObj.getJSONArray("details");
	
						if (jsonArray != null && jsonArray.size() > 0) {
							for (int j = 0; j < jsonArray.size(); j++) {
								bFlag++;
								detailSum++;
								JSONObject logDetails = JSON.parseObject(jsonArray.get(j).toString());
								UserActionDetailWithBLOBs userActionDetail = new UserActionDetailWithBLOBs(
								logDetails.getString("actionSqlId"), logDetails.getString("sqlParams"));
								userActionDetail.setId(iworkService.getNextId());
								userActionDetail.setUsrActId(newId);
								userActionDetail.setActionType(logDetails.getString("actionType"));
								actionDetailList.add(userActionDetail);
								if (bFlag >= 1000) {
									userActionDetailService.save(actionDetailList);
									actionDetailList.clear();
									bFlag = 0;
								}
							}
						}
					}
					// 保存到数据库
					if (actionLogList.size() > 0) {
						userActionLogService.save(actionLogList);
					}
					if (actionDetailList.size() > 0) {
						userActionDetailService.save(actionDetailList);
					}
					// 删除对应的缓存
					redisUtils.hdel("userActionLog", keys.toArray());
					logger.info("redis定时器保存行为日志==" + sum);
					logger.info("redis定时器保存行为日志详细==" + detailSum);
				}else {
					logger.info("无日志 ....");
				}
			}
		} catch (Exception e) {
			logger.error("redis定时器保存-行为日志-报错");
			logger.error(e.getMessage());
		}finally {
			redisUtils.delete(key);
		}
	}
}
