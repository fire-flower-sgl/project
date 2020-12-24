package com.mhtech.platform.msrv.gateway.controller.monitor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mhtech.platform.log.pojo.DelelAlertRuleDTO;
import com.mhtech.platform.msrv.gateway.request.ReqPropertyPage;
import com.mhtech.platform.msrv.gateway.request.ServerNotifyRulePage;
import com.mhtech.platform.msrv.gateway.request.ServerNotifyRuleParams;
import com.mhtech.platform.msrv.gateway.request.ServerNotifyRuleUpdate;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IServerAlertRuleService;
import com.mhtech.platform.msrv.monitor.service.IServerNotifyRuleService;
import com.mhtech.platform.msrv.pojo.AlertInfoUpdate;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.PropertyDTO;
import com.mhtech.platform.msrv.pojo.PropertyVO;
import com.mhtech.platform.msrv.pojo.ServerNotifyRule;
import com.mhtech.platform.msrv.pojo.ServerNotifyRuleVO;

/**
 * _通知规则
 * 
 * @author _mjl
 *
 */

@RestController
@RequestMapping("monitor/serverNotifyRule")
@Api(value = "ServerNotifyRuleController", tags = "通知规则")
public class ServerNotifyRuleController {
	
	private final Logger logger= LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IServerNotifyRuleService iServerNotifyRuleService;
	
	@Autowired
	private IServerAlertRuleService serverAlertRuleService;
	
	
	@PostMapping("/addServerNotifyRule")
	@Transactional
	@ApiOperation(value = "新增通知规则")
	public RespObject<?> addServerNotifyRule(@RequestBody @Valid  ServerNotifyRuleParams sp) {
		
		ServerNotifyRule dx=new ServerNotifyRule(sp.getServerId(),sp.getIsEnable(),sp.getRecvStartTime(),
				sp.getRecvEndTime(),sp.getRefuseStartTime(),sp.getRefuseEndTime());
		String info =null;
		try {
			info=iServerNotifyRuleService.addServerInfo(dx);

		} catch (Exception e) {
			logger.error("新增通知规则异常", e);
			throw new RuntimeException("新增通知规则异常");
		}
		return RespUtils.buildOKWithData(info);
	}
	
	@PostMapping("/updateServerNotifyRule")
	@ApiOperation(value = "修改通知规则")
	public RespObject<?> updateServerNotifyRule(@RequestBody @Valid  AlertInfoUpdate aip) {
		if(aip.getRefuseEndTime().compareTo(aip.getRefuseStartTime()) <= 0) {
			throw new RuntimeException("暂停时间配置错误，请确认参数");
		}
		serverAlertRuleService.updateAlertTime(aip);
		return RespUtils.OK;
	}
	
	/**
	 * 删除通知规则
	 * @param id
	 * @return
	 */
	@GetMapping("/del")
	@ApiOperation(value = "删除")
	public RespObject<?> delelAlertRule(@RequestParam("id") @NotNull Long id) {
		int del =0;
		try {
			iServerNotifyRuleService.remove(id);
		} catch (Exception e) {
			logger.error("删除通知规则异常", e);
			throw new RuntimeException("删除预警规则异常");
		}
		return  RespUtils.buildOKWithData(del);
	}
	
	/**
	 * 分页查询
	 * @param 
	 * @return
	 */
	@PostMapping(value = "/queryPage")
	@ApiOperation(value = "分页查询")
    public RespObject<Page<ServerNotifyRuleVO>> queryPage(@RequestBody @Valid ServerNotifyRulePage rulePage) {
		ServerNotifyRule rule = new ServerNotifyRule();
		rule.setPageNo(rulePage.getPageNo());
		rule.setPageSize(rulePage.getPageSize());
		Page<ServerNotifyRuleVO> page = iServerNotifyRuleService.queryPage(rule);
        return RespUtils.buildOKWithData(page);
	}
	
	/**
	 * 更新
	 * @param 
	 * @return
	 */
	@PostMapping(value = "/update")
	@ApiOperation(value = "更新通知规则")
    public RespObject<?> update(@RequestBody @Valid ServerNotifyRuleUpdate sp) {
		ServerNotifyRule dx=new ServerNotifyRule(sp.getId(),sp.getServerId(),sp.getIsEnable(),sp.getRecvStartTime(),
				sp.getRecvEndTime(),sp.getRefuseStartTime(),sp.getRefuseEndTime());
		iServerNotifyRuleService.update(dx);
		
        return RespUtils.OK;
	}
	
	/**
	 * 根据id查询通知规则
	 * @param 
	 * @return
	 */
	@GetMapping(value = "/findById")
	@ApiOperation(value = "根据id查询通知规则")
    public RespObject<ServerNotifyRuleVO> findById(@RequestParam("id") @NotNull Long id) {
        return RespUtils.buildOKWithData(iServerNotifyRuleService.getServerNotifyRuleById(id));
	}
	
	
	
}
