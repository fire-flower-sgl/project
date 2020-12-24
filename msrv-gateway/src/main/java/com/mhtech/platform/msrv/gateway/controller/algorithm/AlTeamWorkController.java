package com.mhtech.platform.msrv.gateway.controller.algorithm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.algorithm.service.IAlTeamWorkService;
import com.mhtech.platform.msrv.gateway.request.algorithm.InsertWorkParams;
import com.mhtech.platform.msrv.gateway.request.algorithm.findId;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.pojo.AlTeamWorkDTO;
import com.mhtech.platform.msrv.pojo.AlTeamWorkVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 5G算法中心-项目详情-团队信息
 * 
 * @author mjl
 * 
 */

@Api(value = "AlTeamWorkController", tags = "5G算法中心项目详情-团队信息")
@RestController
@RequestMapping(value = "/algorithm/alTeamWork")
public class AlTeamWorkController {

	@Autowired
	IAlTeamWorkService iAlTeamWorkService;

	@PostMapping("/findTeamWork")
	@ApiOperation("查询团队信息")
	public RespObject<?> findTeamWork(@RequestBody @Valid findId id) {
		AlTeamWorkVO vo;
		try {
			vo = iAlTeamWorkService.selectId(id.getTeamCode());
		} catch (Exception e) {
			throw new RuntimeException("查询团队信息异常出错");
		}
		return RespUtils.buildOKWithDataYg(vo);
	}

	@PostMapping("/findAllTeamWork")
	@ApiOperation("查询所有团队信息")
	public RespObject<?> findAllTeamWork() {
		List<AlTeamWorkVO> vo;
		try {
			vo = iAlTeamWorkService.list();
		} catch (Exception e) {
			throw new RuntimeException("查询所有团队信息异常出错");
		}
		return RespUtils.buildOKWithDataYg(vo);
	}

	@PostMapping("/delectWork")
	@ApiOperation("删除团队信息")
	public RespObject<?> delectWork(@RequestBody @Valid findId fs) {
		int key;
		try {
			key = iAlTeamWorkService.delete(fs.getTeamCode());
		} catch (Exception e) {
			throw new RuntimeException("删除团队信息异常出错");
		}
		return RespUtils.buildOKWithDataYg(key);
	}

	@PostMapping("/insertWork")
	@ApiOperation("新增团队信息")
	public RespObject<?> insertWork(@RequestBody @Valid InsertWorkParams iw) {
		AlTeamWorkDTO dto = new AlTeamWorkDTO(iw.getTeamCode(), iw.getTeamName(), iw.getMobile(), iw.getEmail());

		int insert = 0;
		try {
			insert = iAlTeamWorkService.insert(dto);
		} catch (Exception e) {
			throw new RuntimeException("新增团队信息异常出错");
		}
		return RespUtils.buildOKWithDataYg(insert);
	}

	@PostMapping("/updateWork")
	@ApiOperation("修改团队信息")
	public RespObject<?> updateWork(@RequestBody @Valid InsertWorkParams iw) {
		AlTeamWorkDTO dto = new AlTeamWorkDTO(iw.getTeamCode(), iw.getTeamName(), iw.getMobile(), iw.getEmail(),
				iw.getCreatedTime());

		int insert = 0;
		try {
			insert = iAlTeamWorkService.update(dto);
		} catch (Exception e) {
			throw new RuntimeException("修改团队信息异常出错");
		}
		return RespUtils.buildOKWithDataYg(insert);
	}

	
	
}
