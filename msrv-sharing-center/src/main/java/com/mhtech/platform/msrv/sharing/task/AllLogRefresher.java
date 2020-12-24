//package com.mhtech.platform.msrv.sharing.task;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.formula.functions.T;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.mhtech.platform.log.pojo.MsrvLogDTO;
//import com.mhtech.platform.msrv.sharing.dao.model.AlertLog;
//import com.mhtech.platform.msrv.sharing.dao.model.GatewayAccessLog;
//import com.mhtech.platform.msrv.sharing.dao.model.MonitorPlans;
//import com.mhtech.platform.msrv.sharing.dao.model.MsrvLog;
//import com.mhtech.platform.msrv.sharing.dao.model.ServerMonitorLog;
//import com.mhtech.platform.msrv.sharing.dao.model.SpUserLog;
//import com.mhtech.platform.msrv.sharing.dao.model.UserActionLog;
//import com.mhtech.platform.msrv.sharing.request.AlertLogVO;
//import com.mhtech.platform.msrv.sharing.request.AllLogVO;
//import com.mhtech.platform.msrv.sharing.request.GatewayAccessLogVO;
//import com.mhtech.platform.msrv.sharing.request.LogDTO;
//import com.mhtech.platform.msrv.sharing.request.MonitorPlansVO;
//import com.mhtech.platform.msrv.sharing.request.MsrvLogVO;
//import com.mhtech.platform.msrv.sharing.request.ServerMonitorLogVO;
//import com.mhtech.platform.msrv.sharing.service.AlertLogService;
//import com.mhtech.platform.msrv.sharing.service.IGatewayAccessLogService;
//import com.mhtech.platform.msrv.sharing.service.MonitorPlansService;
//import com.mhtech.platform.msrv.sharing.service.MsrvLogService;
//import com.mhtech.platform.msrv.sharing.service.ServerMonitorLogService;
//import com.mhtech.platform.msrv.sharing.service.UserActionDetailService;
//import com.mhtech.platform.msrv.sharing.service.UserActionLogService;
//import com.mhtech.platform.msrv.sharing.utils.ExcelUtil;
//import com.mhtech.platform.msrv.sharing.utils.RedisUtils;
//import com.mhtech.platform.msrv.sharing.utils.ZipUtils;
//
//@SuppressWarnings("all")
//@Component
//public class AllLogRefresher {
//
//	@Autowired
//	RedisUtils redisUtils;
//	@Autowired
//	UserActionLogService uals;
//	@Autowired
//	UserActionDetailService uasd;
//	@Autowired
//	IGatewayAccessLogService igals;
//	@Autowired
//	MsrvLogService mls;
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
//		//一天执行一次，数据没有问题，一天多次执行数据如果发生改变，ecexl将会有之前保存的遗留数据，压缩文件内容也有出入
//		 
//		
//		String url = "D:\\log\\";// 本地路径
//		String ysUrl = "D:\\log\\CompressedLog\\";// 压缩包存放路径
//		fileCj(url);
//		fileCj(ysUrl);
//		String format =ExcelUtil.fingTime2();// 昨天日期yyyy-MM-dd 用于设置文件名称
//		String fingTime = ExcelUtil.fingTime();// 昨天日期HH:mm:ss.ss
//		String fingDayTime = ExcelUtil.fingDayTime();// 今天日期
//	
//		
//        //查询数据 生成_ecexl文件
//		Map<String, Object> ualsMap = uals.selectManMin(fingTime, fingDayTime);
//		List<String> ualsfiles = log(url, ualsMap, "UserAction_",mapAllLog());
//		Map<String, Object> igalsMap = igals.selectManMin(fingTime, fingDayTime);
//		List<String> igalsfiles = log(url, igalsMap, "GatewayAccess_",mapGaLog());
//		Map<String, Object> alsMap = als.selectManMin(fingTime, fingDayTime);
//		List<String> alsfiles = log(url, alsMap, "Alert_",mapAlertLog());
//		Map<String, Object> mpsMap = mps.selectManMin(fingTime, fingDayTime);
//		List<String> mpsfiles = log(url, mpsMap, "MonitorPlans_",mapMonitorPlansLog());
//		Map<String, Object> selectManMin = mls.selectManMin(new LogDTO(fingTime, fingDayTime));
//		List<String> msrvfiles = log(url, selectManMin, "Msrv_",mapMsrcLog());
//		
//		Map<String, Object> smlsMap = smls.selectManMin(fingTime, fingDayTime);
//		List<String> smlsMapfiles = log(url, smlsMap, "ServerMonitor_",mapServerMonitorLog());
//		
//		// 压缩 并删除数据库数据
//		if (ualsfiles.size() > 0) {
//			ZipUtils.zipFileAndEncrypt(ualsfiles, ysUrl + "UserAction日志"+format+".rar", "userAction");
//			
////			Long[] ids = uals.selectIds(fingTime,fingDayTime);
////			if (ids.length>0) {
////				uasd.deleteId(ids);//删除关联表数据
////			}
////			uals.deleteDate(fingTime, fingDayTime);
//			
//		}
//		if (igalsfiles.size() > 0) {
//			ZipUtils.zipFileAndEncrypt(igalsfiles, ysUrl + "GatewayAccess日志"+format+".rar", "gateway");
////			igals.deleteDate(fingTime, fingDayTime);
//		}
//		if (alsfiles.size() > 0) {
//			ZipUtils.zipFileAndEncrypt(alsfiles, ysUrl + "Alert日志"+format+".rar", "alert");
////			als.deleteDate(fingTime, fingDayTime);
//		}
//		if (mpsfiles.size() > 0) {
//			ZipUtils.zipFileAndEncrypt(mpsfiles, ysUrl + "MonitorPlans日志"+format+".rar", "monitor");
////			mps.deleteDate(fingTime, fingDayTime);
//		}
//		if (msrvfiles.size() > 0) {
//			ZipUtils.zipFileAndEncrypt(msrvfiles, ysUrl + "Msrv日志"+format+".rar", "msrv");
////			mls.deleteDate(fingTime, fingDayTime);
//		}
//        if (smlsMapfiles.size()>0) {
//        	ZipUtils.zipFileAndEncrypt(smlsMapfiles, ysUrl + "ServerMonitor日志"+format+".rar", "serverMonitor");
////        	smls.deleteDate(fingTime, fingDayTime);
//		}
//		
//	}
//
//	
//	/**
//	 * 保存日志 每到60万创建一个_ecexl，每个ecexl_生成10页，每页保存6万行数据
//	 * @param url 保存路径
//	 * @param map 查询条件（）
//	 * @param logName 指定生成的日志文件名
//	 * @param interpretation 英文字段的中文解释
//	 * @return 日志文件名称集合
//	 * @throws Exception
//	 */
//	public List<String> log(String url, Map<String, Object> map, String logName,Map<String, Object> interpretation) throws Exception {
//
//		List<String> list = new ArrayList<>();// 返回日志保存url
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
//		String format =ExcelUtil.fingTime2();//昨天日期
//
//		if (map.get("max") == null && map.get("min") == null) {
//			return list;
//		}
//		Long maxId = (Long) map.get("max");// 要查询的最大值
//		Long minId = (Long) map.get("min");// 要查询的最小值
//		int sum = Integer.parseInt(map.get("size").toString()); // 要查询的总条数
//
//		int count = 60000;// 每次查询条数
//		int topSize = 0; // 累积查询条数
//		int cishu = 0; // Execl页码
//
//		do {
//			Map<String, Object> maps = findlist(logName, maxId, minId, sum, sdf, count);
//			List<? extends Object> findlist = (List<? extends Object>) maps.get("list");
//
//			if (topSize == 0 || topSize % 600000 == 0) {
//				if (topSize % 600000 == 0) {
//					cishu = 0;// 重置 确保下面追加数据页码真确
//				}
//				// 生成ecexl
//				String name = ExcelUtil.createExcel(findlist, url, logName + topSize / 600000 + "日志" + format,interpretation);
//				list.add(name);
//			} else {
//				String fileUrl = url + logName + topSize / 600000 + "日志" + format + ".xls";// 文件路径
//				int row = 0;
//				// 追加数据 -不让它一次性插入-避免堆溢出
//				while (row < 60000) {
//					if (sum < 30000) {
//						ExcelUtil.addExcel(fileUrl, findlist, cishu);
//						row += 30000;
//					} else {
//						ExcelUtil.addExcel(fileUrl,
//								findlist.subList(row, (sum - row) < 30000 ? row + (sum - 30000) : row + 30000), cishu);
//						row += 30000;
//					}
//				}
//			}
//			topSize = topSize + findlist.size();
//			sum = sum - count; 
//			cishu++; // 更新页码
//			minId = (Long) maps.get("minId");// 更新要查询的最小值
//			System.err.println("----------"+logName+"-----还有多少条未插入 :" + sum);
//			findlist.clear();
//		} while (sum > 0);
//		return list;
//	}
//
//	/**
//	 * 查询日志数据
//	 * @param logName 名称-对应生成的日志文件名
//	 * @param maxId 最大id
//	 * @param minId 最小id
//	 * @param sum 总条数
//	 * @param sdf 时间字段处理
//	 * @param count 查询条数
//	 * @return map：返回集合对象与minId
//	 */
//	public Map<String, Object> findlist(String logName, Long maxId, Long minId, int sum, SimpleDateFormat sdf,
//			int count) {
//
//		Map<String, Object> map = new HashMap<>();
//
//		switch (logName) {
//		case "UserAction_":
//			List<AllLogVO> uaLog = fingUserAction(uals.allLog(maxId, minId, (sum - count) < 0 ? sum : count), sdf);
//			Long id = uaLog.get(uaLog.size() - 1).getId();
//			map.put("minId", id);
//			map.put("list", uaLog);
//			return map;
//		case "GatewayAccess_":
//			List<GatewayAccessLogVO> gaLog = findGatewayAccess(
//					igals.allLog(maxId, minId, (sum - count) < 0 ? sum : count), sdf);
//			Long id2 = gaLog.get(gaLog.size() - 1).getId();
//			map.put("minId", id2);
//			map.put("list", gaLog);
//			return map;
//		case "Alert_":
//			List<AlertLogVO> alertLog = findAlertLog(als.allLog(maxId, minId, (sum - count) < 0 ? sum : count), sdf);
//			Long id3 = alertLog.get(alertLog.size() - 1).getId();
//			map.put("minId", id3);
//			map.put("list", alertLog);
//			return map;
//		case "MonitorPlans_":
//			List<MonitorPlansVO> mpLog = findMonitorPlans(mps.allLog(maxId, minId, (sum - count) < 0 ? sum : count),
//					sdf);
//			Long id4 = mpLog.get(mpLog.size() - 1).getId();
//			map.put("minId", id4);
//			map.put("list", mpLog);
//			return map;
//		case "Msrv_":
//			List<MsrvLogVO> msrcLog = findMsrvLog(mls.allLog(maxId, minId, (sum - count) < 0 ? sum : count), sdf);// 查询数据
//			Long logId = msrcLog.get(msrcLog.size() - 1).getLogId();
//			map.put("minId", logId);
//			map.put("list", msrcLog);
//			return map;
//		case "ServerMonitor_":
//			List<ServerMonitorLogVO> serverMonitorLog = fingServerMonitor(smls.allLog(maxId, minId, (sum - count) < 0 ? sum : count), sdf);// 查询数据
//			Long id5 = serverMonitorLog.get(serverMonitorLog.size()-1).getId();
//			map.put("minId", id5);
//			map.put("list", serverMonitorLog);
//			return map;
//		default:
//			return null;
//		}
//	}
//
//	/**
//	 * 文件夹是否存在，不存在创建
//	 */
//	public void fileCj(String name) {
//		File file =new File(name);    
//		//如果文件夹不存在则创建    
//		if  (!file .exists()  && !file .isDirectory()){       
//		    file .mkdir();    
//		} else  {  
//			//目录存在
//		}  
//	}
//	
//	
//	// ServerMonitorLog格式转化
//	public List<ServerMonitorLogVO> fingServerMonitor(List<ServerMonitorLog> list, SimpleDateFormat sdf) {
//		List<ServerMonitorLogVO> log = new ArrayList<>();
//		list.forEach(sp -> {
//			log.add(new ServerMonitorLogVO(sp.getId(),sp.getServerId(),sp.getParamName(),sp.getParamAlias(),
//			        sp.getAlterValue(),sp.getMemo(),sdf.format(sp.getCreatedTime())));
//		});
//		list.clear();
//		return log;
//	}
//	
//	// UserActionLog格式转化
//	public List<AllLogVO> fingUserAction(List<UserActionLog> list, SimpleDateFormat sdf) {
//		List<AllLogVO> log = new ArrayList<>();
//		list.forEach(sp -> {
//			log.add(new AllLogVO(sp.getId(), sp.getUserCode(), sp.getActionModule(), sp.getActionNum(),
//					sdf.format(sp.getActionStartTime()), sdf.format(sp.getActionEndTime()), sp.getSpUserLog()));
//		});
//		list.clear();
//		return log;
//	}
//
//	// GatewayAccessLog格式转化
//	public List<GatewayAccessLogVO> findGatewayAccess(List<GatewayAccessLog> list, SimpleDateFormat sdf) {
//		List<GatewayAccessLogVO> log = new ArrayList<>();
//		list.forEach(sp -> {
//			log.add(new GatewayAccessLogVO(sp.getId(), sp.getIp(), sp.getUrl(), sp.getParams(),
//					sdf.format(sp.getCreatedTime()), sp.getRequestBody()));
//		});
//		list.clear();
//		return log;
//	}
//
//	// MsrvLog格式转化
//	public List<MsrvLogVO> findMsrvLog(List<MsrvLog> list, SimpleDateFormat sdf) {
//		List<MsrvLogVO> log = new ArrayList<>();
//		list.forEach(sp -> {
//			log.add(new MsrvLogVO(sp.getLogId(), sp.getTraceId(), sp.getSide(), sp.getApplication(),
//					sp.getInterfaceName(), sp.getMethods(), sp.getHost(), sdf.format(sp.getCreatedTime()),
//					sp.getArguments()));
//		});
//		list.clear();
//		return log;
//	}
//
//	// AlertLog格式转化
//	public List<AlertLogVO> findAlertLog(List<AlertLog> list, SimpleDateFormat sdf) {
//		List<AlertLogVO> log = new ArrayList<>();
//		list.forEach(sp -> {
//			log.add(new AlertLogVO(sp.getId(), sp.getServerId(), sp.getParamName(), sp.getParamAlias(),
//					sp.getAlterValue(), sdf.format(sp.getAlertTime()), sp.getIsAlerting()));
//		});
//		list.clear();
//		return log;
//	}
//
//	// MonitorPlansLog格式转化
//	public List<MonitorPlansVO> findMonitorPlans(List<MonitorPlans> list, SimpleDateFormat sdf) {
//		List<MonitorPlansVO> log = new ArrayList<>();
//		list.forEach(sp -> {
//			log.add(new MonitorPlansVO(sp.getId(), sp.getTaskName(), sp.getCron(), sp.getMainClass(), sp.getMethod(),
//					sp.getStatus(), sp.getParams(), sp.getMemo(), sdf.format(sp.getCreatedTime())));
//		});
//		list.clear();
//		return log;
//	}
//
//	// 设置GatewayAccessLog字段中文释义
//	public Map<String, Object> mapGaLog() {
//		Map<String, Object> mapGaLog = new HashMap<String, Object>();
//		mapGaLog.put("id", "主键编号");
//		mapGaLog.put("ip", "客户端地址");
//		mapGaLog.put("url", "请求路径");
//		mapGaLog.put("params", "路径尾部参数");
//		mapGaLog.put("createdTime", "创建时间");
//		mapGaLog.put("requestBody", "消息体");
//		return mapGaLog;
//	}
//
//	// 设置MsrcLog字段中文释义
//	public Map<String, Object> mapMsrcLog() {
//		Map<String, Object> MapMsrcLog = new HashMap<String, Object>();
//		MapMsrcLog.put("logId", "主键编号");
//		MapMsrcLog.put("traceId", "跟踪ID");
//		MapMsrcLog.put("side", "0：消费者; 1：提供者");
//		MapMsrcLog.put("application", "应用名称");
//		MapMsrcLog.put("interfaceName", "接口名称");
//		MapMsrcLog.put("methods", "调用方法名");
//		MapMsrcLog.put("host", "地址");
//		MapMsrcLog.put("createdTime", "创建时间");
//		MapMsrcLog.put("arguments", "参数");
//		return MapMsrcLog;
//	}
//
//	// 设置AllLog字段中文释义
//	public Map<String, Object> mapAllLog() {
//		Map<String, Object> MapAllLog = new HashMap<String, Object>();
//		MapAllLog.put("id", "主键编号");
//		MapAllLog.put("userCode", "用户编码");
//		MapAllLog.put("actionModule", "行为所属模块,登录模块、图表模块等");
//		MapAllLog.put("actionNum", "行为代码,即权限代码");
//		MapAllLog.put("actionStartTime", "行为开始时间");
//		MapAllLog.put("actionEndTime", "行为结束时间");
//		MapAllLog.put("spUserLog", "用户操作信息 ");
//		return MapAllLog;
//	}
//
//	// 设置AlertLog字段中文释义
//	public Map<String, Object> mapAlertLog() {
//		Map<String, Object> alertLog = new HashMap<String, Object>();
//		alertLog.put("id", "主键编码");
//		alertLog.put("serverId", "服务主键");
//		alertLog.put("paramName", "监测字段");
//		alertLog.put("paramAlias", "字段别名");
//		alertLog.put("alterValue", "预警值");
//		alertLog.put("alertTime", "预警时间");
//		alertLog.put("isAlerting", "是否产生告警 ");
//		return alertLog;
//	}
//
//	// 设置MonitorPlansLog字段中文释义
//	public Map<String, Object> mapMonitorPlansLog() {
//		Map<String, Object> monitorPlan = new HashMap<String, Object>();
//		monitorPlan.put("id", "主键编码");
//		monitorPlan.put("taskName", "任务名称");
//		monitorPlan.put("cron", "执行周期");
//		monitorPlan.put("mainClass", "任务主体 TaskJobs");
//		monitorPlan.put("method", "执行函数入口");
//		monitorPlan.put("status", "启用 / 禁用");
//		monitorPlan.put("params", "自定义参数 ");
//		monitorPlan.put("memo", "备注");
//		monitorPlan.put("createdTime", "创建时间 ");
//		return monitorPlan;
//	}
//	
//	// 设置ServerMonitorLog字段中文释义
//	public Map<String, Object> mapServerMonitorLog() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", "主键编码");
//		map.put("serverId", "服务主键");
//		map.put("paramName", "监测字段");
//		map.put("paramAlias", "字段别名");
//		map.put("alterValue", "预警值");
//		map.put("memo", "备注");
//		map.put("createdTime", "创建时间 ");
//		return map;
//	}
//	
//
//}
