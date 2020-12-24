package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.log.pojo.ParamAlertDTO;
import com.mhtech.platform.msrv.gateway.request.ParamAlertParams;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IParamAlertService;
import com.mhtech.platform.msrv.monitor.service.IServerAlertRuleService;
import com.mhtech.platform.msrv.pojo.AlertRuleInfo;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ParamAlertInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * _监控指标
 * 
 * @author _mjl
 *
 */

@RestController
@RequestMapping("monitor/paramAlert")
@Api(value = "ParamAlertController", tags = "监控指标")
public class ParamAlertController {

	@Autowired
	private IParamAlertService iParamAlertService;
	@Autowired
	private IServerAlertRuleService iServerAlertRuleService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	@PostMapping("/addParamAlert")
	@Transactional
	@ApiOperation(value = "新增监控指标与监控规则")
	public RespObject<?> addParamAlert(@RequestBody @Valid ParamAlertParams kk) {
		
		ParamAlertInfo pp = kk.getParamAlertInfo();
		AlertRuleInfo ar = kk.getAlertRuleInfo();
		
		Date date = new Date();
		String addParamAlert =null;
		int rule=0;
		
		ParamAlertInfo pai = new ParamAlertInfo(pp.getServerId(), pp.getParamName(), pp.getParamAlias(),
				pp.getAlertValue(), pp.getAlertCode(), pp.getAlertType(), pp.getStatus(),date);
		try {
			 addParamAlert = iParamAlertService.addParamAlert(pai);
		} catch (Exception e) {
			logger.error("新增监控指标异常", e);
			throw new RuntimeException("新增监控指标异常");
		}
		
		AlertRuleInfo ari = new AlertRuleInfo(ar.getServerId(), ar.getParamName(), ar.getAlertLimit(), ar.getDuration(),
				ar.getLevel(), ar.getContacts(), date);
		try {
			 rule = iServerAlertRuleService.addAlertRule(ari);
		} catch (Exception e) {
			logger.error("新增监控指标规则异常", e);
			throw new RuntimeException("新增监控指标规则异常");
		}
		if (rule>0 && "新增成功".equals(addParamAlert)) {
				return RespUtils.buildOKWithData("添加监控指标"+addParamAlert+"添加监控指标规则"+rule);
		}
		return RespUtils.buildOKWithData("添加失败");
	}
	

	@PostMapping("/findParamAlert")
	@ApiOperation(value = "查询监控信息",notes="监控信息+条件+分页")
	public RespObject<?> findParamAlert(@RequestBody @Valid ParamAlertDTO dto) {
		
		Page page = iParamAlertService.selectParamAlert(dto);
		return RespUtils.buildOKWithData(page);
	}
	
	
	
	
	
	
}
