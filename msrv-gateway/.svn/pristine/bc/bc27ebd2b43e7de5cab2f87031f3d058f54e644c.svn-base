package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.log.pojo.SpPersonneDTO;
import com.mhtech.platform.log.pojo.SpPersonneNewVO;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.ISpSpersonneService;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.SpPersonneVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description 人员排班，告警通知联系人提供支持
 * @Author admin
 * @Date 2020/2/26 10:10
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/monitor/SpPersonne")
@Api(value = "SpPersonneController", tags = "人员排班，告警通知联系人提供支持")
public class SpPersonneController {
	
	private final Logger logger= LoggerFactory.getLogger(getClass());
	
	@Autowired
	ISpSpersonneService iSpSpersonneService;

	@PostMapping(value = "/select")
	@ApiOperation(value = "查询全部人员")
	public RespObject<?> select() {
		List<SpPersonneVO> list = iSpSpersonneService.listSpPersonne();
		return RespUtils.buildOKWithData(list);
	}

	@PostMapping(value = "/selectPage")
	@ApiOperation(value = "查询人员+分页")
	public RespObject<?> selectPage(@RequestBody @Valid SpPersonneVO sp) {

		SpPersonneDTO dto = new SpPersonneDTO(sp.getPageSize(), sp.getPageNo());
		
		Page selectSpPersonne;
		try {
			selectSpPersonne = iSpSpersonneService.selectSpPersonne(dto);
		} catch (Exception e) {
			logger.error("查询人员+分页", e);
			throw new RuntimeException("查询人员+分页");
		}
		return RespUtils.buildOKWithData(selectSpPersonne);
	}

	@PostMapping(value = "/add")
	@ApiOperation(value = "添加人员")
	public RespObject<?> add(@RequestBody @Valid SpPersonneNewVO sp) {

		SpPersonneDTO dto = new SpPersonneDTO(sp.getPcode(), sp.getPname(), sp.getMobileno(), sp.getUnitId(),
				sp.getUnitName(), sp.getCreateDate(), sp.getValidFlag(), sp.getShiftId(), sp.getIsLocalton()
				,sp.getEmail());
		int add;
		try {
			 add = iSpSpersonneService.add(dto);

		} catch (Exception e) {
			logger.error("添加人员异常", e);
			throw new RuntimeException("添加人员异常");
		}
		
		return RespUtils.buildOKWithData(add);
	}

	@PostMapping(value = "/update")
	@ApiOperation(value = "修改人员")
	public RespObject<?> update(@RequestBody @Valid SpPersonneNewVO sp) {

		SpPersonneDTO dto = new SpPersonneDTO(sp.getId(), sp.getPcode(), sp.getPname(), sp.getMobileno(),
				sp.getUnitId(), sp.getUnitName(), sp.getCreateDate(), sp.getValidFlag(), sp.getShiftId(),
				sp.getIsLocalton(),sp.getEmail());
		int update;
		try {
			update = iSpSpersonneService.update(dto);

		} catch (Exception e) {
			logger.error("修改人员异常", e);
			throw new RuntimeException("修改人员异常");
		}
		
		return RespUtils.buildOKWithData(update);
		
	}
}
