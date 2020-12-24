package com.mhtech.platform.msrv.gateway.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IDictRpcCaller;
import com.mhtech.platform.msrv.pojo.DictTypeSearchParam;

@Api("参数字典")
@RestController
@RequestMapping("/dict")
public class DictController {

//	@Autowired
	private IDictRpcCaller dictRpcCaller;
	
	@ApiOperation("字典属性列表")
	@PostMapping(value = "/type",  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> type(@RequestBody @Validated DictTypeSearchParam param) {
		if(StringUtils.isAllEmpty(param.getMemo(), param.getType())) {
			return RespUtils.OK;
		}
		return RespUtils.buildOKWithData(dictRpcCaller.listType(param));
	}
	
	@ApiOperation("字典值")
	@PostMapping(value = "/values",  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> listTypeValue(@RequestParam(value = "type", required = false) String type) {
		if(StringUtils.isEmpty(type)) {
			return RespUtils.OK;
		}
		return RespUtils.buildOKWithData(dictRpcCaller.listTypeValue(type));
	}
}
