package com.mhtech.platform.msrv.gateway.controller.monitor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IServerMonitorLog;
import com.mhtech.platform.msrv.pojo.MonitorSyncResult;
import com.mhtech.platform.msrv.pojo.ServerMonitorInfo;

@Api(tags = "监控日志同步", description = "监控日志同步")
@RestController
@RequestMapping("monitor/log")
public class ServerMonitorLogController {

	private final Logger logger= LoggerFactory.getLogger(getClass());
			
	@Autowired
	private IServerMonitorLog monitorServer;
	
	@Value("${alert.monitorinfo-size}")
	private int monitorInfoSize;
	
	/**
	 * 新增监控日志
	 * @param log
	 * @return
	 */
	@ApiOperation("同步监控信息")
	@ApiResponse(code = 200, message = "请求成功")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> post(@RequestBody @Validated ServerMonitorInfo log) {
		try {
			monitorServer.addMonitorLog(log);
		} catch (Exception e) {
			logger.error("新增监控日志失败", e);
			throw new RuntimeException("新增监控日志失败");
		}
		return RespUtils.OK;
	}
	
	@ApiOperation("硬件信息采集同步")
	@PostMapping(value = "collector", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
	produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> hardwareCollector(@RequestBody @Validated ServerMonitorInfo log) {
		try {
			monitorServer.addMonitorLog(log);
		} catch (Exception e) {
			logger.error("新增监控日志失败", e);
			throw new RuntimeException("新增监控日志失败");
		}
		return RespUtils.OK;
	}
	
	@ApiOperation("批量同步监控信息")
	@ApiResponse(code = 200, message = "请求成功")
	@PostMapping(value = "batch", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> post(@RequestBody @Validated @NotNull List<ServerMonitorInfo> infos) {
		if(CollectionUtils.isEmpty(infos) || infos.size() > monitorInfoSize) {
			throw new RuntimeException("监控信息列表数据错误");
		}
		MonitorSyncResult result = null;
		try {
			result = monitorServer.addServerMonitorLogList(infos);
		} catch (Exception e) {
			logger.error("新增监控日志失败", e);
			throw new RuntimeException("新增监控日志失败");
		}
		return RespUtils.buildOKWithData(result);
	}
}
