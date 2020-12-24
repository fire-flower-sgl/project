package com.mhtech.platform.msrv.gateway.controller.monitor;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.log.pojo.DelelAlertRuleDTO;
import com.mhtech.platform.log.pojo.ServerAlertRuleDTO;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IServerAlertRuleService;
import com.mhtech.platform.msrv.pojo.AlertRuleInfo;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ServerAlertRule;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("monitor/alertRule")
public class ServerAlertRuleController {
	
	@Autowired
	private IServerAlertRuleService iServerAlertRuleService;
	
	private final Logger logger= LoggerFactory.getLogger(getClass());
	
	

	@PostMapping("/add")
	@ApiOperation(value = "新增",notes="新增预警规则")
	public RespObject<?> addAlertRule(@RequestBody @Valid  AlertRuleInfo ari) {
		int rule =0;
		try {
			 rule = iServerAlertRuleService.addAlertRule(ari);
		} catch (Exception e) {
			logger.error("新增预警规则异常", e);
			throw new RuntimeException("新增预警规则异常");
		}
		return  RespUtils.buildOKWithData(rule);
	}
	
	@PostMapping("/update")
	@ApiOperation(value = "修改",notes="修改预警规则")
	public RespObject<?> updateAlertRule(@RequestBody @Valid  AlertRuleInfo ari) {
		int rule =0;
		try {
			 rule = iServerAlertRuleService.updateAlertRule(ari);
		} catch (Exception e) {
			logger.error("修改预警规则异常", e);
			throw new RuntimeException("修改预警规则异常");
		}
		return  RespUtils.buildOKWithData(rule);
	}
	
	@PostMapping("/delel")
	@ApiOperation(value = "删除",notes="删除预警规则,传入删除id")
	public RespObject<?> delelAlertRule(@RequestBody @Valid  DelelAlertRuleDTO dto) {
		int rule =0;
		try {
			 rule = iServerAlertRuleService.removeAlertRule(dto.getId());
		} catch (Exception e) {
			logger.error("删除预警规则异常", e);
			throw new RuntimeException("删除预警规则异常");
		}
		return  RespUtils.buildOKWithData(rule);
	}
	
	@PostMapping("/selectId")
	@ApiOperation(value = "查询",notes="依据id查询对象")
	public RespObject<?> selectIdAlertRule(@RequestBody @Valid  DelelAlertRuleDTO ari) {
		ServerAlertRule rule ;
		try {
			 rule = iServerAlertRuleService.select(ari.getId());
		} catch (Exception e) {
			logger.error("依据id查询预警规则异常", e);
			throw new RuntimeException("依据id查询预警规则异常");
		}
		return  RespUtils.buildOKWithData(rule);
	}
	
	@PostMapping("/selectPageList")
	@ApiOperation(value = "查询集合",notes="依据条件查询分页集合")
	public RespObject<?> selectPageList(@RequestBody @Valid  ServerAlertRuleDTO dto) {
		
		Page selectList = iServerAlertRuleService.selectList(dto);
		return  RespUtils.buildOKWithData(selectList);
	}
	
	
	
}
