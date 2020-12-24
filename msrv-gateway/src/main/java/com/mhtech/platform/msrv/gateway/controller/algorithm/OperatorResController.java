package com.mhtech.platform.msrv.gateway.controller.algorithm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.algorithm.service.OperatorRpcCaller;
import com.mhtech.platform.msrv.gateway.http.HttpMedia;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.TokenUtils;
import com.mhtech.platform.msrv.pojo.DataSetVO;
import com.mhtech.platform.msrv.pojo.ListableOperatorParam;
import com.mhtech.platform.msrv.pojo.OperatorResListBO;
import com.mhtech.platform.msrv.pojo.OperatorRunner;

@Api(value = "OperatorResController", tags = "5G算法中心算子")
@RestController
@RequestMapping("algorithm")
public class OperatorResController {

	private Logger log =LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OperatorRpcCaller operatorRpcCaller;
	
	@ApiOperation(value = "算子列表", response = OperatorResListBO[].class)
	@PostMapping(value = "operator/list", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> operatorList(@RequestHeader(HttpMedia.REQ_HEAD_AUTHOR) String token, @RequestBody @Validated ListableOperatorParam param) {
		param.setUserCode(TokenUtils.getCurrentUser(token).getUserCode());
		return RespUtils.buildOKWithData(operatorRpcCaller.grpOperators(param));
	}
	
	@ApiOperation(value = "运行算子", response = DataSetVO.class)
	@PostMapping(value = "operator/run", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> operatorRun(@RequestHeader(HttpMedia.REQ_HEAD_AUTHOR) String token, @RequestBody @Validated OperatorRunner or) {
		or.setUserCode(TokenUtils.getCurrentUser(token).getUserCode());
		return RespUtils.buildOKWithData(operatorRpcCaller.execOperator(or));
	}
}
