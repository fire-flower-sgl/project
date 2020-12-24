package com.mhtech.platform.msrv.gateway.controller.algorithm;

import java.util.Objects;

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

import com.mhtech.platform.msrv.algorithm.exception.ErrorRelatedFlowNodeException;
import com.mhtech.platform.msrv.algorithm.service.AnalyzeFlowRpcCaller;
import com.mhtech.platform.msrv.gateway.http.HttpMedia;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.TokenUtils;
import com.mhtech.platform.msrv.pojo.AnalyzeFlowBO;
import com.mhtech.platform.msrv.pojo.AnalyzeFlowNodeParam;
import com.mhtech.platform.msrv.pojo.AnalyzeFlowVO;
import com.mhtech.platform.msrv.pojo.CurrentUser;
import com.mhtech.platform.msrv.pojo.PageVO;
import com.mhtech.platform.msrv.pojo.PageableAnalyzeFlowBO;
import com.mhtech.platform.msrv.pojo.PanelNode;

@Api(value = "AnalyzeFlowController", tags = "5G算法中心分析流")
@RestController
@RequestMapping("algorithm")
public class AnalyzeFlowController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AnalyzeFlowRpcCaller analyzeFlowRpcCaller;
	
	@ApiOperation(value = "分析流节点查询")
	@PostMapping(value = "analyze/nodes", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> analyze(@RequestBody @Validated  AnalyzeFlowNodeParam param) {
		PanelNode panelNode = analyzeFlowRpcCaller.findPanelNode(param);
		return RespUtils.buildOKWithData(panelNode);
	}
	
	@ApiOperation("保存分析流")
	@PostMapping(value = "analyze/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> addAnalyzeFlow(@RequestHeader(HttpMedia.REQ_HEAD_AUTHOR) String token, @RequestBody @Validated AnalyzeFlowBO afbo) {
		try {
			long count = afbo.getFlowNodes().stream().map(node -> node.getNodeCode()).distinct().count();
			if(count != afbo.getFlowNodes().size()) {
				throw new ErrorRelatedFlowNodeException();
			}
			afbo.setCreator(TokenUtils.getCurrentUser(token).getUserCode());
			analyzeFlowRpcCaller.addAnalyzeFlow(afbo);
		} catch (Exception e) {
			log.error("分析流保存失败", e);
			throw e;
		}
		return RespUtils.OK;
	}
	
	@ApiOperation("项目分析流查询")
	@PostMapping(value = "analyze/list", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> analyzeList(@RequestHeader(HttpMedia.REQ_HEAD_AUTHOR) String token, @RequestBody @Validated PageableAnalyzeFlowBO pageable) {
		CurrentUser user = TokenUtils.getCurrentUser(token);
		if(Objects.isNull(user)) {
			return RespUtils.build(RespCode.NO_TOKEN);
		}
		PageVO<AnalyzeFlowVO> pageVO = analyzeFlowRpcCaller.listAnalyzeFlow(pageable, user);
		return RespUtils.buildOKWithData(pageVO);
	}
}
