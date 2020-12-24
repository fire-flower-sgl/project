package com.mhtech.platform.msrv.gateway.controller.algorithm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.algorithm.service.IAlProjectLogMapperService;
import com.mhtech.platform.msrv.gateway.request.FindAllProjectLog;
import com.mhtech.platform.msrv.gateway.request.InsertProjectLog;
import com.mhtech.platform.msrv.gateway.request.UpdateProjectLog;
import com.mhtech.platform.msrv.gateway.request.algorithm.FindAlTeamProjectParams;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.pojo.AlProjectLogDTO;
import com.mhtech.platform.msrv.pojo.AlProjectLogVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 5G算法中心-日志
 * 
 * @author Administrator
 * 
 */

@RestController
@RequestMapping(value = "/algorithm/projectLog")
@Api(value = "AlProjectLogMapperController", tags = "5G算法中心-日志")
public class AlProjectLogMapperController {

	@Autowired
	IAlProjectLogMapperService projectLog;

	@PostMapping("/findProjectLog")
	@ApiOperation(value = "查询日志", notes = "依据id查询日志")
	public RespObject<?> findProjectLog(@RequestBody @Valid FindAlTeamProjectParams fs) {
		AlProjectLogVO vo;
		try {
			vo = projectLog.selectByPrimaryKey(fs.getId());
		} catch (Exception e) {
			throw new RuntimeException("依据id查询日志异常出错");
		}
		return RespUtils.buildOKWithDataYg(vo);
	}

	@PostMapping("/insertProjectLog")
	@ApiOperation(value = "新增Log", notes = "新增Log")
	public RespObject<?> insertProjectLog(@RequestBody @Valid InsertProjectLog ip) {
		AlProjectLogDTO dto = new AlProjectLogDTO(ip.getModelId(), ip.getUserCode(), ip.getContent(),
				ip.getPrjCode());
		int insert = 0;
		try {
			insert = projectLog.insert(dto);
		} catch (Exception e) {
			throw new RuntimeException("新增Log异常出错");
			//e.printStackTrace();
		}
		return RespUtils.buildOKWithDataYg(insert);
	}

	@PostMapping("/delectProjectLog")
	@ApiOperation(value = "删除Log", notes = "依据id删除Log")
	public RespObject<?> delectProjectLog(@RequestBody @Valid FindAlTeamProjectParams ip) {
		int insert = 0;
		try {
			insert = projectLog.delete(ip.getId());
		} catch (Exception e) {
			throw new RuntimeException("依据id删除Log异常出错");
		}
		return RespUtils.buildOKWithDataYg(insert);
	}

	@PostMapping("/updateProjectLog")
	@ApiOperation(value = "修改Log", notes = "依据id修改Log")
	public RespObject<?> updateProjectLog(@RequestBody @Valid UpdateProjectLog up) {
		AlProjectLogDTO dto = new AlProjectLogDTO(up.getId(), up.getModelId(), up.getUserCode(), up.getContent(),
				up.getCreatedTime());
		int update = 0;
		try {
			update = projectLog.update(dto);
		} catch (Exception e) {
			throw new RuntimeException("依据id修改Log异常出错");
		}
		return RespUtils.buildOKWithDataYg(update);
	}

	@PostMapping("/findAllProjectLog")
	@ApiOperation(value = "查询所有日志", notes = "查询所有日志")
	public RespObject<?> findAllProjectLog(@RequestBody @Valid FindAllProjectLog fpj) {
		AlProjectLogDTO dto = new AlProjectLogDTO(fpj.getId(), fpj.getModelId(), fpj.getUserCode(), fpj.getContent(),
				fpj.getPrjCode(), fpj.getEndTime());
		List<AlProjectLogVO> vo;
		try {
			vo = projectLog.list(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查询所有日志异常出错");
		}
		return RespUtils.buildOKWithDataYg(vo);
	}

}
