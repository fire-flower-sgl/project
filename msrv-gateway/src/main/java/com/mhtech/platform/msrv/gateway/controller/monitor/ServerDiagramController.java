package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.gateway.request.ServerDiagramAdd;
import com.mhtech.platform.msrv.gateway.request.ServerDiagrams;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IServerDiagramService;
import com.mhtech.platform.msrv.pojo.ServerAdminVO;
import com.mhtech.platform.msrv.pojo.ServerDiagramDTO;
import com.mhtech.platform.msrv.pojo.ServerDiagramsVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description 业务流程图管理
 * @Author admin
 * @Date 2020/4/1 15:10
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/monitor/diagram")
@Api(value = "ServerDiagramController", tags = "业务流程图管理",hidden = false)
public class ServerDiagramController {
	
	@Autowired
	private IServerDiagramService diagramService;
	
	@Autowired
	private IworkService iworkService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	/**
	 * 根据业务编号查询流程图
	 * @param businessId
	 * @return
	 */
	@GetMapping(value = "/findById")
	@ApiOperation(value = "根据业务查询流程图")
    public RespObject<ServerDiagramsVO> findById(@RequestParam("businessId") @NotNull Long businessId ) {
		ServerDiagramsVO diagramVO = null;
		try {
			diagramVO = diagramService.findServerDiagram(businessId);
		} catch (Exception e) {
			logger.error("查询流程图异常", e);
			throw new RuntimeException("查询流程图失败");
		}
		return RespUtils.buildOKWithData(diagramVO);
	}
	
	/**
	 * 服务列表(新增选择用)
	 * @param businessId
	 * @return
	 */
	@GetMapping(value = "/serverList")
	@ApiOperation(value = "服务列表")
    public RespObject<ServerAdminVO> serverList(){
		return RespUtils.buildOKWithData(diagramService.getServerListForTrue());
	}
	
	/**
	 * 添加流程图
	 * @param reqObjPage
	 * @return
	 */
	@PostMapping(value = "/add")
	@ApiOperation(value = "添加业务流程图")
    public RespObject<?> save(@RequestBody @Valid ServerDiagrams obj) {
		try {
			List<ServerDiagramAdd> serverDiagramList = obj.getServerDiagramList();
			if(!CollectionUtils.isEmpty(serverDiagramList)) {
				List<ServerDiagramDTO> dataList = new ArrayList<ServerDiagramDTO>();
				for (ServerDiagramAdd diagram : serverDiagramList) {
					ServerDiagramDTO diaDTO = new ServerDiagramDTO(diagram.getBusinessId(), diagram.getServerId(), diagram.getNextId(), diagram.getWorkDesc());
					diaDTO.setId(iworkService.getNextId());
					if(diagram.isEnd()) {
						ServerDiagramDTO diaDTOend = new ServerDiagramDTO(diagram.getBusinessId(), diagram.getNextId());
						diaDTOend.setId(iworkService.getNextId());
						dataList.add(diaDTOend);
					}
					dataList.add(diaDTO);
				}
				diagramService.delByBusinessId(dataList.get(0).getBusinessId());
				diagramService.save(dataList);
			}
		} catch (Exception e) {
			logger.error("新增业务流程图异常", e);
			throw new RuntimeException("添加业务流图失败");
		}
		//return RespUtils.OK;
		return RespUtils.build(RespCode.SAVE_SUCCESS);
	}
	
	/**
	 * 根据id删除业务流程图的一条
	 * @param businessId
	 * @return
	 */
	@GetMapping(value = "/del")
	@ApiOperation(value = "删除业务流程图")
    public RespObject<?> delById(@RequestParam("businessId") @NotNull Long businessId ) {
		diagramService.remove(businessId);
		return RespUtils.OK;
	}
	
	/**
	 * 更新流程图
	 * @param reqObjPage
	 * @return
	 */
	@PostMapping(value = "/modify")
	@ApiOperation(value = "更新业务流程图")
	@Transactional(rollbackFor =  Exception.class)
    public RespObject<?> modify(@RequestBody @Valid ServerDiagrams obj) {
		try {
			List<ServerDiagramAdd> serverDiagramList = obj.getServerDiagramList();
			if(!CollectionUtils.isEmpty(serverDiagramList)) {
				List<ServerDiagramDTO> dataList = new ArrayList<ServerDiagramDTO>();
				for (ServerDiagramAdd diagram : serverDiagramList) {
					ServerDiagramDTO diaDTO = new ServerDiagramDTO(diagram.getBusinessId(), diagram.getServerId(), diagram.getNextId(), diagram.getWorkDesc());
					diaDTO.setId(iworkService.getNextId());
					if(diagram.isEnd()) {
						ServerDiagramDTO diaDTOend = new ServerDiagramDTO(diagram.getBusinessId(), diagram.getNextId());
						diaDTOend.setId(iworkService.getNextId());
						dataList.add(diaDTOend);
					}
					dataList.add(diaDTO);
				}
				diagramService.delByBusinessId(dataList.get(0).getBusinessId());
				diagramService.save(dataList);
			}
		} catch (Exception e) {
			logger.error("更新业务流程图异常", e);
			throw new RuntimeException("更新业务流图失败");
		}
		return RespUtils.OK;
	}
	
	
	
}
