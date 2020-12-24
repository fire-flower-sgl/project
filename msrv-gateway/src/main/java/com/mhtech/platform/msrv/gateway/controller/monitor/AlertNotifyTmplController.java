package com.mhtech.platform.msrv.gateway.controller.monitor;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.gateway.request.AlertNotifyTmplParams;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IAlertNotifyTmplService;
import com.mhtech.platform.msrv.pojo.AlertNotifyTmpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * _告警通知模板
 * 
 * @author _mjl
 *
 */

@RestController
@RequestMapping("monitor/alertNotitfTmpl")
@Api(value = "AlertNotifyTmplController", tags = "告警通知模板")
public class AlertNotifyTmplController {

	@Autowired
	private IAlertNotifyTmplService iAlertNotifyTmplService;
	
	private final Logger logger= LoggerFactory.getLogger(getClass());
	
	
	@PostMapping("/addAlertNotitfTmpl")
	@Transactional
	@ApiOperation(value = "新增告警通知模板")
	public RespObject<?> addAlertNotitfTmpl(@RequestBody @Valid  AlertNotifyTmplParams antp) {
		
		AlertNotifyTmpl dx=new AlertNotifyTmpl(antp.getServerId(),antp.getTmplCode(),antp.getExt1(),antp.getExt2(),
				                antp.getExt3(),antp.getExt4(),antp.getExt5(),antp.getStatus(),antp.getTmplConent());
		String info =null;
		try {
			info = iAlertNotifyTmplService.addServerInfo(dx);
		} catch (Exception e) {
			logger.error("新增告警通知模板异常", e);
			throw new RuntimeException("新增告警通知模板异常");
		}
		return RespUtils.buildOKWithData(info);
	}
}
