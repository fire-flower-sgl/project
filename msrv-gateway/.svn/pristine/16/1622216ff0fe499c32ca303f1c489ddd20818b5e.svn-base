package com.mhtech.platform.msrv.gateway.controller.monitor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.ExcelUtil;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IServerAdminService;
import com.mhtech.platform.msrv.monitor.service.IServerNotifyLogService;
import com.mhtech.platform.msrv.monitor.service.SpParamAlertService;
import com.mhtech.platform.msrv.pojo.AlertNotifyLogCounterBO;
import com.mhtech.platform.msrv.pojo.AlertRateVO;
import com.mhtech.platform.msrv.pojo.AlertRatioVO;
import com.mhtech.platform.msrv.pojo.DailyAlertsTotalVO;
import com.mhtech.platform.msrv.pojo.DailyAlertsVO;
import com.mhtech.platform.msrv.pojo.DetailAlertVO;
import com.mhtech.platform.msrv.pojo.IndexChartVO;
import com.mhtech.platform.msrv.pojo.MonitorStatus.NotifyLogStatus;
import com.mhtech.platform.msrv.pojo.NotifyLogVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerNotifyLog;
import com.mhtech.platform.msrv.pojo.ServerNotifyVO;

/**
 * 告警日志管理入口
 * @author Guo
 *
 */
@Api(tags = "告警记录管理")
@RestController
@RequestMapping("monitor/alertLog")
@SuppressWarnings("rawtypes")
public class ServerAlertLogController {

	@Autowired
	private IServerNotifyLogService serverNotifyLogService;
	
	@Autowired
	private IServerAdminService serverAdminService;
	
	@Autowired
	private IServerNotifyLogService serverNotifyService;
	
	@Value("${local.download.path}")
	private String localDownloadPath;
	
	@Autowired
	private SpParamAlertService spParamAlertService;
	
	
	/**
	 * 告警记录查询
	 * @param a
	 * @return
	 */
	@ApiOperation("告警记录列表查询")
	@ApiResponse(code = 200, message = "请求成功")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<Page<ServerNotifyLog>> get(@Validated NotifyLogVO nlvo) {
		//级别
		//所在服务器
		//处理状态
		//是否通知
		if(nlvo.getStatus() != null && NotifyLogStatus.valueOf(nlvo.getStatus()) == null) {
			throw new RuntimeException("告警处理状态错误");
		}
		if(nlvo.getServerId() != null) {
			ServerAdminInfo serverInfo = serverAdminService.getServerInfo(nlvo.getServerId());
			if(serverInfo == null) {
				throw new RuntimeException("服务器编码错误");
			}
		}
		if(!StringUtils.isEmpty(nlvo.getServerBiz())) {
			nlvo.setServerName(nlvo.getServerBiz());
		}
		Page<ServerNotifyLog> listServerNotifyLog = serverNotifyLogService.listServerAlertNotifyLog(nlvo);
		return RespUtils.buildOKWithData(listServerNotifyLog);
	}
	
	@SuppressWarnings("deprecation")
//	@GetMapping(value = "/export/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@PostMapping(value = "/export")
	public void export(@RequestBody @Validated NotifyLogVO nlvo, HttpServletResponse response,HttpServletRequest request) {
		if(nlvo.getStatus() != null && NotifyLogStatus.valueOf(nlvo.getStatus()) == null) {
			throw new RuntimeException("告警处理状态错误");
		}
		if(nlvo.getServerId() != null) {
			ServerAdminInfo serverInfo = serverAdminService.getServerInfo(nlvo.getServerId());
			if(serverInfo == null) {
				throw new RuntimeException("服务器编码错误");
			}
		}
		if(nlvo.getStartHappenTime() == null || nlvo.getEndHappenTime() == null) {
			throw new RuntimeException("告警时间段不能为空");
		}
		int rows = 1;
		nlvo.setPageNo(1);
		nlvo.setPageSize(rows);
		Page<ServerNotifyLog> listServerNotifyLog = serverNotifyLogService.listServerAlertNotifyLog(nlvo);
		try {
			if(listServerNotifyLog.getTotalCount() == 0 || listServerNotifyLog.getTotalCount() > 60 * 1000) {
				response.setHeader("Content-Type", "application/json;charset=UTF-8");
				throw new RuntimeException("文件导出失败");
			} else {
				String fileName = getFilename();
				List<ServerNotifyLog> list = listServerNotifyLog.getData();
				while(true) {
					Page<ServerNotifyLog> data = serverNotifyLogService.listServerAlertNotifyLog(nlvo);
					list.addAll(data.getData());
					if(data.getTotalPages() == data.getPageNo()) {
						break;
					}
					nlvo.setPageNo(nlvo.getPageNo() + 1);
				}
				String fileUrl=request.getContextPath();//获取项目绝对路径根目录
				ExcelUtil.createExcel(list, fileUrl+localDownloadPath, fileName, createExcelHeader());
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName) + ".xls");
				response.setHeader("Content-Type", "application/vnd.ms-excel;charset=utf-8");
				response.getOutputStream().write(FileUtils.readFileToByteArray(new File(localDownloadPath + fileName + ".xls")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, Object> createExcelHeader() {
		Map<String, Object> header = new HashMap<>();
		header.put("id", "记录ID");
		header.put("serverId", "服务ID");
		header.put("level", "告警级别");
		header.put("serverBiz", "所属业务名称");
		header.put("serverName", "所属服务名");
		header.put("happenTime", "发生时间");
		header.put("lastTimes", "持续时间");
		header.put("username", "管理员");
		header.put("paramName", "告警指标");
		header.put("userCode", "通知联系人编码");
		header.put("isSend", "是否发送");
		header.put("notified", "是否通知");
		header.put("createdTime", "发生时间");
		header.put("content", "告警内容");
		return header;
	}
	
	private String getFilename() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return "告警记录_" + df.format(new Date());
	}
	
	@ApiOperation("告警记录详情")
	@ApiResponse(code = 200, message = "请求成功")
	@GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> detail(@Validated @NotNull(message = "告警ID不能为空") @RequestParam("id") Long id) {
		AlertNotifyLogCounterBO param = new AlertNotifyLogCounterBO();
		//获取告警基本信息
		ServerNotifyLog serverNotifyLog = serverNotifyLogService.getNotifyLogById(id);
		if(serverNotifyLog == null) {
			throw new RuntimeException("告警ID错误");
		}
		
		//取值 近7天该告警次数
		String topTime = fingDayTime(-6);
		String endTime = fingDayTime(0);
		List<DailyAlertsVO> dailyAlerts = serverNotifyLogService.listNotifyStatistics(topTime, endTime, id);
	
		
		param.setParamName(serverNotifyLog.getParamName());
		param.setServerId(serverNotifyLog.getServerId());
		param.setNotifyId(id);
		DetailAlertVO detailAlertVO = getDailyAlerts(param,dailyAlerts);//改，传入集合
		
		IndexChartVO vo = new IndexChartVO();
		AlertRatioVO arvo = new AlertRatioVO();
		List<AlertRateVO> alertRate = detailAlertVO.getAlertRate();
		double total = 0;
		for (AlertRateVO alertRateVO : alertRate) {
			total += alertRateVO.getAlertRate();
		}
		final double $total = total;
		detailAlertVO.getAlertRate().forEach(davo -> {
			double rate = davo.getAlertRate() * 100.00 / $total;
			BigDecimal b = new BigDecimal(rate);
			rate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			davo.setAlertRate(rate);
		});
		arvo.setAlertRate(detailAlertVO.getAlertRate());
		vo.setAlertRatio(arvo);
		DailyAlertsTotalVO datvo = new DailyAlertsTotalVO();
		datvo.setDailyAlerts(dailyAlerts);
		vo.setDailyAlertsTotal(datvo);
		return RespUtils.buildOKWithData(vo);
	}
	
	
	
    /**
     * 今天日期
     * @param i 天数 0表示今天
     * @return
     */
	public  String fingDayTime(int i) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, i);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String dateString = formatter.format(date);
		return dateString;
	}

	
	/**
	 * 统计一个星期时间段的数据
	 * @return
	 */
	private DetailAlertVO getDailyAlerts(AlertNotifyLogCounterBO param,List<DailyAlertsVO> dailyAlerts ) {
		int idx = 7;
		Date endTime = new Date();
		Date startTime = getDateBeforeDays(1 - idx);
		param.setEndTime(endTime);
		param.setStartTime(startTime);
		DetailAlertVO detailAlertVO = serverNotifyService.detailNotifyLog(param);
		
		dailyAlerts.sort(new Comparator<DailyAlertsVO>() {
			@Override
			public int compare(DailyAlertsVO o1, DailyAlertsVO o2) {
				return o1.getDate().after(o2.getDate()) ? 1 : -1;
			}
		});
		Date date = new Date(startTime.getTime());
		for (int i = 0; i < idx; i++) {
			if(i >= dailyAlerts.size()) {
				DailyAlertsVO davo = new DailyAlertsVO();
				davo.setCount(0);
				davo.setDate(new Date(date.getTime()));
				dailyAlerts.add(davo);
				date = new Date(date.getTime() + 24 * 3600 * 1000);
				continue;
			}else if(date.getTime() == dailyAlerts.get(i).getDate().getTime()) {
				date = new Date(date.getTime() + 24 * 3600 * 1000);
				continue;
			} else {
				DailyAlertsVO davo = new DailyAlertsVO();
				davo.setCount(0);
				davo.setDate(new Date(date.getTime()));
				dailyAlerts.add(i, davo);
				date = new Date(date.getTime() + 24 * 3600 * 1000);
				continue;
			}
		}
		return detailAlertVO;
	}
	

//	/**
//	 * 统计一个星期时间段的数据
//	 * @return
//	 */
//	private DetailAlertVO getDailyAlerts(AlertNotifyLogCounterBO param) {
//		int idx = 7;
//		Date endTime = new Date();
//		Date startTime = getDateBeforeDays(1 - idx);
//		param.setEndTime(endTime);
//		param.setStartTime(startTime);
//		DetailAlertVO detailAlertVO = serverNotifyService.detailNotifyLog(param);
//		List<DailyAlertsVO> dailyAlerts = detailAlertVO.getDailyAlerts();
//		
//		dailyAlerts.sort(new Comparator<DailyAlertsVO>() {
//			@Override
//			public int compare(DailyAlertsVO o1, DailyAlertsVO o2) {
//				return o1.getDate().after(o2.getDate()) ? 1 : -1;
//			}
//		});
//		Date date = new Date(startTime.getTime());
//		for (int i = 0; i < idx; i++) {
//			if(i >= dailyAlerts.size()) {
//				DailyAlertsVO davo = new DailyAlertsVO();
//				davo.setCount(0);
//				davo.setDate(new Date(date.getTime()));
//				dailyAlerts.add(davo);
//				date = new Date(date.getTime() + 24 * 3600 * 1000);
//				continue;
//			}else if(date.getTime() == dailyAlerts.get(i).getDate().getTime()) {
//				date = new Date(date.getTime() + 24 * 3600 * 1000);
//				continue;
//			} else {
//				DailyAlertsVO davo = new DailyAlertsVO();
//				davo.setCount(0);
//				davo.setDate(new Date(date.getTime()));
//				dailyAlerts.add(i, davo);
//				date = new Date(date.getTime() + 24 * 3600 * 1000);
//				continue;
//			}
//		}
//		return detailAlertVO;
//	}
	
	private Date getDateBeforeDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);      
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	/**
	 * 告警记录状态修改
	 * @param a
	 * @return
	 */
	@ApiOperation("告警记录状态修改")
	@ApiResponse(code = 200, message = "请求成功")
	@PostMapping(value = "/changeStatus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> changeStatus(@RequestBody ServerNotifyLog serverNotifyLog1) {
		RespCode respCode=RespCode.UPDATE_SUCCESS;
		//获取告警基本信息
		ServerNotifyLog serverNotifyLog = serverNotifyLogService.getNotifyLogById(serverNotifyLog1.getId());
		if(serverNotifyLog == null) {
			throw new RuntimeException("告警ID错误");
		}
		String status="";
		if(serverNotifyLog1.getStatus()==null) {
			throw new RuntimeException("状态不能为空");
		}else {
			status=serverNotifyLog1.getStatus().toString();
			if (status.equals("10")) {//如果状态为“未处理”，则修改状态为“处理中”
				status="15";
			}else if (status.equals("15")){//如果状态为“处理中”，则修改状态为“处理完成”,清除临时暂停时间
				status="20";
				spParamAlertService.changeTempStopListenTime(serverNotifyLog.getAlertId().toString(),null, null);
			}else {
				throw new RuntimeException("状态编码传输错误，请检查");
			}
		}
		serverNotifyLogService.changeStatus(status, serverNotifyLog1.getId());
		return RespUtils.build(respCode);
	}
	
	/**
	 * 修改告警记录对应的告警规则临时暂停时间
	 * @param a
	 * @return
	 */
	@ApiOperation("修改告警记录对应的告警规则临时暂停时间")
	@ApiResponse(code = 200, message = "请求成功")
	@PostMapping(value = "/changeTempStopListenTime", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> changeTempStopListenTime(@RequestBody ServerNotifyLog serverNotifyLog1 ) {
		//获取告警基本信息
		ServerNotifyLog serverNotifyLog = serverNotifyLogService.getNotifyLogById(serverNotifyLog1.getId());
		if(serverNotifyLog == null) {
			throw new RuntimeException("告警ID错误");
		}
//		String startTime=null;
//		String endTime=null;
//		if (serverNotifyLog1.getTempStoptimeStart()!=null&&serverNotifyLog1.getTempStoptimeStart()!="") {
//			startTime="'"+ serverNotifyLog1.getTempStoptimeStart()+"'";
//		}
//		if (serverNotifyLog1.getTempStoptimeEnd()!=null&&serverNotifyLog1.getTempStoptimeEnd()!="") {
//			endTime="'"+serverNotifyLog1.getTempStoptimeEnd()+"'";
//		}
		String startTime=serverNotifyLog1.getTempStoptimeStart();
		String endTime=serverNotifyLog1.getTempStoptimeEnd();
		if (serverNotifyLog.getAlertId()!=null) {
			spParamAlertService.changeTempStopListenTime(serverNotifyLog.getAlertId().toString(),startTime,endTime );
		}else {
			throw new RuntimeException("对应告警规则不存在，请检查");
		}
		return RespUtils.buildOKWithData("");
	}
	
	/**
	 * 获取告警记录对应的告警规则临时暂停时间
	 * @param a
	 * @return
	 */
	@ApiOperation("获取告警记录对应的告警规则临时暂停时间")
	@ApiResponse(code = 200, message = "请求成功")
	@PostMapping(value = "/getTempStopListenTime", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> getTempStopListenTime(@RequestBody ServerNotifyLog serverNotifyLog1 ) {
		//获取告警基本信息
		ServerNotifyLog serverNotifyLog = serverNotifyLogService.getNotifyLogById(serverNotifyLog1.getId());
		if(serverNotifyLog == null) {
			throw new RuntimeException("告警ID错误");
		}
		String tempStoptimeStart="";
		String tempStoptimeEnd="";
		if (serverNotifyLog.getAlertId()!=null) {
			List<Map<String, Object>> list=spParamAlertService.getEntity(serverNotifyLog.getAlertId().toString());
			tempStoptimeStart=list.get(0).get("tempStoptimeStart")==null?"":list.get(0).get("tempStoptimeStart").toString().replace(".0", "");
			tempStoptimeEnd=list.get(0).get("tempStoptimeEnd")==null?"":list.get(0).get("tempStoptimeEnd").toString().replace(".0", "");
			System.err.println(serverNotifyLog.getAlertId()+"========="+list.get(0).get("tempStoptimeStart")+"========"+list.get(0).get("tempStoptimeEnd"));
		}else {
			throw new RuntimeException("对应告警规则不存在，请检查");
		}
		JSONObject js=new JSONObject();
		js.put("tempStoptimeStart", tempStoptimeStart);
		js.put("tempStoptimeEnd", tempStoptimeEnd);
		return RespUtils.buildOKWithData(js);
	}
}
