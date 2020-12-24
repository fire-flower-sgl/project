package com.mhtech.platform.msrv.gateway.controller.algorithm;

import static com.mhtech.platform.msrv.gateway.response.RespCode.SUCCESS;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.algorithm.service.IAlSignMapperService;
import com.mhtech.platform.msrv.gateway.request.algorithm.UpdateSignParams;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.pojo.AlSignMapperDTO;
import com.mhtech.platform.msrv.pojo.AlSignMapperVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *  标记
 * 
 * @author mjl
 * 
 */

@RestController
@RequestMapping(value = "/algorithm/Sign")
@Api(value = "AlSignMapperController", tags = "标记")
public class AlSignMapperController {
	
	@Autowired
	IAlSignMapperService iAlSignMapperService;
	
	@PostMapping("/findProjectSign")
	@ApiOperation(value = "查询所有项目标记")
	public RespObject<?> findProjectSign() {
		List<AlSignMapperVO> vo;
		try {
			vo = iAlSignMapperService.listSignPrjCode();
		} catch (Exception e) {
			throw new RuntimeException("查询所有项目标记异常出错");
		}
		return RespUtils.buildOKWithDataYg(vo);
	}



	@PostMapping("/updateSign")
	@ApiOperation(value = "修改Sign", notes = "依据id修改Sign")
	public RespObject<?> updateSign(@RequestBody @Valid  UpdateSignParams up) {
		
		boolean prjCode = iAlSignMapperService.selectPrjCode(up.getPrjCode());
		if (prjCode!=true) {
			AlSignMapperDTO dto=new AlSignMapperDTO(up.getPrjCode(),up.getSign());
			String selective = iAlSignMapperService.insertSelective(dto);
			if ("新增成功".equals(selective)) {
				return RespUtils.buildOKWithDataYg(selective);
			}
			return RespUtils.build(SUCCESS.getCode(), false, SUCCESS.getMessage(),selective);
		}
		AlSignMapperDTO dto=new AlSignMapperDTO(up.getPrjCode(),up.getSign());
		int update = 0;
		try {
			update = iAlSignMapperService.update(dto);
		} catch (Exception e) {
			throw new RuntimeException("依据id修改Sign异常出错");
		}
		return RespUtils.buildOKWithDataYg(update);
	}

}
