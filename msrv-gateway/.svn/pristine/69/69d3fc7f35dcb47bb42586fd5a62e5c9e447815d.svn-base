package com.mhtech.platform.msrv.gateway.controller.algorithm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.Objects;

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

import com.mhtech.platform.msrv.algorithm.exception.AlgorithmException;
import com.mhtech.platform.msrv.algorithm.service.DataSetRpcCaller;
import com.mhtech.platform.msrv.gateway.http.HttpMedia;
import com.mhtech.platform.msrv.gateway.request.algorithm.DataSetUploadParam;
import com.mhtech.platform.msrv.gateway.request.algorithm.PageableDataSetParam;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.TokenUtils;
import com.mhtech.platform.msrv.pojo.CurrentUser;
import com.mhtech.platform.msrv.pojo.DataSetFile;
import com.mhtech.platform.msrv.pojo.DataSetRecordParam;
import com.mhtech.platform.msrv.pojo.DataSetRunner;
import com.mhtech.platform.msrv.pojo.DataSetUploadResult;
import com.mhtech.platform.msrv.pojo.DataSetVO;
import com.mhtech.platform.msrv.pojo.PageableDataSetBO;
import com.mobile.utils.JsonUtils;

@Api(value = "DataSetController", tags = "5G算法中心数据集")
@RestController
@RequestMapping("algorithm")
public class DataSetController {
	
	private Logger log =LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DataSetRpcCaller dataSetRpcCaller;

	@ApiOperation(value = "上传数据集文件")
	@PostMapping(value = "dataset/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> dataSetUpload(
			@RequestHeader(HttpMedia.REQ_HEAD_AUTHOR) String token, @Validated DataSetUploadParam param) {
		CurrentUser user = TokenUtils.getCurrentUser(token);
		if(Objects.isNull(user)) {
			return RespUtils.build(RespCode.NO_TOKEN);
		}
		String fileName = param.getUploadFile().getOriginalFilename();
		if(!fileName.endsWith(".csv") && !fileName.endsWith(".xlsx")) {
			return RespUtils.build(RespCode.ILLEGAL_ARGUMENT);
		}
		int idx = fileName.lastIndexOf(".");
		fileName = fileName.substring(0, idx) + "_" + System.currentTimeMillis() + fileName.substring(idx);
		DataSetFile file = new DataSetFile();
		try {
			file.setContent(param.getUploadFile().getBytes());
			file.setCreator(user.getUserCode());
			file.setIsOpen(false);
			file.setFileName(fileName);
			DataSetUploadResult result = dataSetRpcCaller.dataSetUpload(file);
			return RespUtils.buildOKWithData(result);
		} catch (IOException e) {
			log.error("数据集文件不存在", e);
		}
		return RespUtils.build(RespCode.ILLEGAL_ARGUMENT);
	}
	
	@ApiOperation(value = "运行数据集")
	@PostMapping(value = "dataset/run", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> loadDataSet(@RequestHeader(HttpMedia.REQ_HEAD_AUTHOR) String token, @RequestBody @Validated DataSetRunner dsr) {
		dsr.setUserCode(
				TokenUtils.getCurrentUser(token).getUserCode()
				);
		try {
			dataSetRpcCaller.loadDataSet(dsr);
		} catch (AlgorithmException e) {
			log.error("运行数据集失败", e);
			throw e;
		}
		return RespUtils.OK;
	}
	
	@ApiOperation(value = "数据集列表", response = DataSetVO.class)
	@PostMapping(value = "dataset/list", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> listDataSet(@RequestHeader(HttpMedia.REQ_HEAD_AUTHOR) String token, @RequestBody @Validated PageableDataSetParam param) {
		PageableDataSetBO dsbo = new PageableDataSetBO();
		dsbo.setPageNo(param.getPageNo());
		dsbo.setDataName(param.getDataName());
		dsbo.setPageSize(param.getPageSize());
		return RespUtils.buildOKWithData(dataSetRpcCaller.pageableDataSet(dsbo, TokenUtils.getCurrentUser(token)));
	}
	
	@ApiOperation(value = "数据集记录查询")
	@PostMapping(value = "dataset/record", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> listRecord(@RequestHeader(HttpMedia.REQ_HEAD_AUTHOR) String token, @RequestBody @Validated DataSetRecordParam param) {
		param.setUserCode(TokenUtils.getCurrentUser(token).getUserCode());
		return RespUtils.buildOKWithData(dataSetRpcCaller.dataSetRecord(param));
	}
}