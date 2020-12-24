package com.mhtech.platform.msrv.gateway.controller.monitor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RedisUtils;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IAlertLogService;
import com.mhtech.platform.msrv.monitor.service.IServerMonitorLog;
import com.mhtech.platform.msrv.monitor.service.IServerNotifyLogService;
import com.mhtech.platform.msrv.pojo.AlertNotifyLogCounterBO;
import com.mhtech.platform.msrv.pojo.AlertRatioVO;
import com.mhtech.platform.msrv.pojo.ComponentUsageVO;
import com.mhtech.platform.msrv.pojo.ComponentWrapperVO;
import com.mhtech.platform.msrv.pojo.DailyAlertsTotalVO;
import com.mhtech.platform.msrv.pojo.DailyAlertsVO;
import com.mhtech.platform.msrv.pojo.IndexChartVO;
import com.mhtech.platform.msrv.pojo.ParamAlertTotalVO;
import com.mhtech.platform.msrv.pojo.SummaryVO;
import com.mhtech.platform.msrv.pojo.TotalComponentUsage;
import com.mhtech.platform.msrv.pojo.TotalSummaryVO;
import com.mhtech.platform.msrv.pojo.TypeAlter;
import com.mhtech.platform.msrv.pojo.UsageVO;

/**
 * 首页图表数据
 * @author Guo
 *
 */
@Api(tags = "首页模块", description = "首页接口管理")
@RestController
@RequestMapping("monitor/index")
public class IndexController {

	@Autowired
	private IAlertLogService alertLogService;
	
	@Autowired
	private IServerNotifyLogService serverNotifyService;
	
	@Autowired
	private IServerMonitorLog serverMonitorLog;
	
	@Value("${alert.total.types}")
	private String[] totalTypes;
	
	@Value("${alert.rate.types}")
	private String[] rateTypes;
	
	@Value("${hardware.summaries.title}")
	private String hardwareSummariesTitle;

	@Value("${bigdata.summaries.title}")
	private String bigdataSummariesTitle;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@ApiOperation("首页查询接口")
	@ApiResponse(code = 200, message = "请求成功")
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<IndexChartVO> get() {
		IndexChartVO vo = new IndexChartVO();
		vo.setParamAlertTotal(getTotalParamAlert());
		vo.setAlertRatio(getRateParamAlert());
		vo.setDailyAlertsTotal(getDailyAlerts());
		vo.setTotalComponentUsage(totalComponentUsage());
		vo.setTotalSummaryVO(getTotalSummaryVO());
		return RespUtils.buildOKWithData(vo);
	}
	
	private TotalSummaryVO getTotalSummaryVO() {
		Map<Object, Object> hardwareTitle = redisUtils.hmget(hardwareSummariesTitle);
		List<String> paramCodes = hardwareTitle.keySet().stream().map(key -> {
			return key.toString();
		}).collect(Collectors.toList());
		
		Map<Object, Object> bigdataTitle = redisUtils.hmget(bigdataSummariesTitle);
		List<String> alertCodes = bigdataTitle.keySet().stream().map(key -> {
			return key.toString();
		}).collect(Collectors.toList());
		
		final List<SummaryVO> totalSummary = serverMonitorLog.totalSummary(
				getCurntDateBeginning(), new Date(), paramCodes, alertCodes);
		hardwareTitle.putAll(bigdataTitle);
		hardwareTitle.forEach((key, val) -> {
			boolean flag = setSummaryName(totalSummary, key.toString(), val.toString());
			if(!flag) {
				SummaryVO svo = new SummaryVO();
				Random r = new Random();
				int count = r.nextInt(10000);
				svo.setCount(count);
				svo.setValue(r.nextInt(count));
				svo.setParamCode(key.toString());
				svo.setParamName(val.toString());
				totalSummary.add(svo);
			}
		});
		TotalSummaryVO vo = new TotalSummaryVO();
		vo.setSummaries(totalSummary);
		return vo;
	}
	
	private boolean setSummaryName(List<SummaryVO> totalSummary, String paramCode, String paramName) {
		boolean flag = false;
		for (SummaryVO summaryVO : totalSummary) {
			if(summaryVO.getParamCode().equals(paramCode)) {
				summaryVO.setParamName(paramName);
				flag = true;
				return flag;
			}
		}
		return false;
	}

	/**
	 * 统计一个星期时间段的数据
	 * @return
	 */
	private DailyAlertsTotalVO getDailyAlerts() {
		int idx = 7;
		Date endTime = new Date();
		Date startTime = getDateBeforeDays(1 - idx);
		AlertNotifyLogCounterBO param = new AlertNotifyLogCounterBO();
		param.setIsSend(true);
		param.setStatus((short) 20);
		param.setEndTime(endTime);
		param.setStartTime(startTime);
		DailyAlertsTotalVO vo = serverNotifyService.totalDailyNotifyLog(param);
		if(vo == null) {
			vo = new DailyAlertsTotalVO();
			vo.setDailyAlerts(new ArrayList<DailyAlertsVO>());
		}
		List<DailyAlertsVO> dailyAlerts = vo.getDailyAlerts();
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
		return vo;
	}
	
	private ParamAlertTotalVO getTotalParamAlert() {
		TypeAlter ta = new TypeAlter();
		ta.setTypes(Arrays.asList(totalTypes));
		ta.setIsAlerting(true);
		ta.setStartAlertTime(getCurntDateBeginning());
		ta.setEndAlertTime(new Date());
		return alertLogService.totalParamAlert(ta);
	}
	
	private ComponentWrapperVO totalComponentUsage() {
		final ComponentWrapperVO cwvo = new ComponentWrapperVO();
		List<UsageVO> cpu = new ArrayList<UsageVO>();
		List<UsageVO> disk = new ArrayList<UsageVO>();
		List<UsageVO> ram = new ArrayList<UsageVO>();
		cwvo.setCpu(cpu);
		cwvo.setDisk(disk);
		cwvo.setRam(ram);
		
		List<ComponentUsageVO> componentUsage = serverMonitorLog.totalComponentUsage(getCurntDateBeginning(), new Date(), 5);
		componentUsage.forEach(vo -> {
			UsageVO cpuvo = new UsageVO();
			cpuvo.setCompntName(vo.getCompntName());
			cpuvo.setCount(vo.getCpu());
			cpu.add(cpuvo);
			
			UsageVO diskvo = new UsageVO();
			diskvo.setCompntName(vo.getCompntName());
			diskvo.setCount(vo.getDisk());
			disk.add(diskvo);
			
			UsageVO ramvo = new UsageVO();
			ramvo.setCompntName(vo.getCompntName());
			ramvo.setCount(vo.getRam());
			ram.add(ramvo);
		});
		return cwvo;
	}
	
	private Date getCurntDateBeginning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);      
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
	}
	
	private Date getDateBeforeDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);      
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	private AlertRatioVO getRateParamAlert() {
		TypeAlter ta = new TypeAlter();
		ta.setTypes(Arrays.asList(rateTypes));
		ta.setIsAlerting(true);
		ta.setStartAlertTime(getCurntDateBeginning());
		ta.setEndAlertTime(new Date());
		return alertLogService.totalRateParamAlert(ta);
	}
}
