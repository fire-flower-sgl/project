//package com.mhtech.platform.msrv.sharing.task;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.mhtech.platform.msrv.sharing.dao.model.GatewayAccessLog;
//import com.mhtech.platform.msrv.sharing.dao.model.MsrvLog;
//import com.mhtech.platform.msrv.sharing.dao.model.UserActionLog;
//import com.mhtech.platform.msrv.sharing.request.AllLogVO;
//import com.mhtech.platform.msrv.sharing.request.GatewayAccessLogVO;
//import com.mhtech.platform.msrv.sharing.request.MsrvLogVO;
//import com.mhtech.platform.msrv.sharing.service.AlertLogService;
//import com.mhtech.platform.msrv.sharing.service.IGatewayAccessLogService;
//import com.mhtech.platform.msrv.sharing.service.MonitorPlansService;
//import com.mhtech.platform.msrv.sharing.service.MsrvLogService;
//import com.mhtech.platform.msrv.sharing.service.ServerMonitorLogService;
//import com.mhtech.platform.msrv.sharing.service.UserActionDetailService;
//import com.mhtech.platform.msrv.sharing.service.UserActionLogService;
//import com.mhtech.platform.msrv.sharing.utils.ExcelUtil;
//import com.mhtech.platform.msrv.sharing.utils.RedisUtils;
///**
// * 定时删除日志数据
// * @author Administrator
// *
// */
//@SuppressWarnings("all")
//@Component
//public class AllLogDeltelRefresher {
//
//	@Autowired
//	UserActionLogService uals;
//	@Autowired
//	IGatewayAccessLogService igals;
//	@Autowired
//	MsrvLogService mls;
//	@Autowired
//	UserActionDetailService uasd;
//	
//	@Autowired
//	AlertLogService als;
//	@Autowired
//	MonitorPlansService mps;
//	@Autowired
//	ServerMonitorLogService smls;	
//	
//	@Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
//	@Transactional(rollbackFor = Exception.class)
//	public void getBlackIpsList() throws Exception {
//
//	
//		//删除sp_user_action_detail表的日志数据
//		Long[] ids = uals.selectIds("2020-03-06 17:23:17","2020-03-06 17:23:30");
//		if (ids.length>0) {
//			uasd.deleteId(ids);
//		}
//		//删除sp_user_action_log
//		uals.deleteDate("2020-03-06 17:23:17","2020-03-06 17:23:30");	
//		
//		//删除sys_msrv_log表日志数据
//		mls.deleteDate("2020-04-01 19:42:28","2020-04-01 19:43:28");
//		
//		//删除sys_gateway_access_log表日志数据
//		igals.deleteDate("2020-04-01 18:41:37","2020-04-01 18:42:37");
//
//		//删除sp_alert_log表日志数据
//		als.deleteDate("2020-04-01 16:38:48","2020-04-01 16:39:48");
//		
//		//删除sp_monitor_plans表日志数据
//		mps.deleteDate("2020-04-01 16:40:53","2020-04-01 16:41:53");
//		//删除sp_monitor_plans表日志数据
//		smls.deleteDate("2020-04-01 21:12:53", "2020-04-01 22:15:53");
//		
//	}
//	
//}
