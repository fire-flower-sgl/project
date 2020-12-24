package com.mhtech.platform.msrv.gateway.controller.monitor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.log.pojo.ServerChkRuleDTO;
import com.mhtech.platform.msrv.auth.service.ServerChkRuleService;
import com.mhtech.platform.msrv.gateway.request.ReqDelServerChkRule;
import com.mhtech.platform.msrv.gateway.request.ReqServerChkRule;
import com.mhtech.platform.msrv.gateway.request.ReqServerPageableEntity;
import com.mhtech.platform.msrv.gateway.request.ReqUpdateServerChkRule;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.pojo.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description 服务自检控制层
 * @Author admin
 * @Date 2020/2/26 10:10
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/monitor/ServiceChkRule")
@Api(value = "ServiceChkRuleController", tags = "服务自检模块")
public class ServiceChkRuleController {
			
	@Autowired
	private ServerChkRuleService serverChkRule;
	
	/**
	 * 新增服务自检
	 * @param sericeChkRule
	 * @return
	 */
	@PostMapping(value = "/add")
	@ApiOperation(value = "新增")
    public RespObject<?> add(@RequestBody @Valid ReqServerChkRule sericeChkRule) {
		Integer insert = serverChkRule.insert(reqObjToDTO(sericeChkRule));
        return RespUtils.buildOKWithData(insert);
	}
	
	/**
	 * 更新
	 * @param sericeChkRule
	 * @return
	 */
	@PostMapping(value = "/modify")
	@ApiOperation(value = "根据id更新")
    public RespObject<?> modify(@RequestBody @Valid ReqUpdateServerChkRule sericeChkRule) {
		Integer insert = serverChkRule.modifyById(reqObjToDTO(sericeChkRule));
        return RespUtils.buildOKWithData(insert);
	}
	
	/**
	 * 删除
	 * @param sericeChkRule
	 * @return
	 */
	@PostMapping(value = "/del")
	@ApiOperation(value = "根据id删除")
    public RespObject<?> del(@RequestBody @Valid ReqDelServerChkRule sericeChkRule) {
		Integer insert = serverChkRule.delById(reqObjToDTO(sericeChkRule));
        return RespUtils.buildOKWithData(insert);
	}
	
	/**
	 * 分页查询
	 * @param sericeChkRule
	 * @return
	 */
	@PostMapping(value = "/queryPage")
	@ApiOperation(value = "分页查询")
    public RespObject<?> queryPage(@RequestBody @Valid ReqServerPageableEntity sericeChkRule) {
		ServerChkRuleDTO scrd = new ServerChkRuleDTO();
		scrd.setPageNo(sericeChkRule.getPageNo());
		scrd.setPageSize(sericeChkRule.getPageSize());
		scrd.setMethod(sericeChkRule.getMethod());
		Page page = serverChkRule.queryPage(scrd);
        return RespUtils.buildOKWithData(page);
	}
	
	
	
	/**
	 * 请求实体类转换成DTO实体
	 * @param rscr
	 * @return
	 */
	private ServerChkRuleDTO reqObjToDTO(ReqServerChkRule rscr) {
		ServerChkRuleDTO aChkRuleDTO = new ServerChkRuleDTO(rscr.getId(), rscr.getServerId(), rscr.getMethod(), rscr.getUri(), 
				StringUtils.isEmpty(rscr.getPort())?null:Integer.valueOf(rscr.getPort()), rscr.getStatus(), rscr.getCreatedTime(), rscr.getHttpHeaders(), rscr.getHttpRequestBody(),rscr.getJsonFieldCheck());
		return aChkRuleDTO;
	}
	private ServerChkRuleDTO reqObjToDTO(ReqUpdateServerChkRule rscr) {
		ServerChkRuleDTO aChkRuleDTO = new ServerChkRuleDTO(rscr.getId(), rscr.getServerId(), rscr.getMethod(), rscr.getUri(), 
				StringUtils.isEmpty(rscr.getPort())?null:Integer.valueOf(rscr.getPort()), rscr.getStatus(), rscr.getCreatedTime(), rscr.getHttpHeaders(), rscr.getHttpRequestBody(),rscr.getJsonFieldCheck());
		return aChkRuleDTO;
	}
	private ServerChkRuleDTO reqObjToDTO(ReqDelServerChkRule rscr) {
		ServerChkRuleDTO aChkRuleDTO = new ServerChkRuleDTO(rscr.getId());
		return aChkRuleDTO;
	}
	
}
