package com.mhtech.platform.msrv.sharing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.sharing.dao.model.SpPersonne;
import com.mhtech.platform.msrv.sharing.request.SpPersonneVO;
import com.mhtech.platform.msrv.sharing.response.RespObject;
import com.mhtech.platform.msrv.sharing.service.SpPersonneService;
import com.mhtech.platform.msrv.sharing.utils.RespUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("SpPersonne")
@Api(value = "SpPersonneController", tags = "人员")
public class SpPersonneController {

	@Autowired
	private SpPersonneService spPersonneService;
	
	// 查询用户日志+条件分页
	@PostMapping(value = "/fingList")
	@ApiOperation(value = "查询用户排班+分页")
	public RespObject<?> fingList(@RequestBody SpPersonneVO  zh) {
		List<SpPersonne> spPersonne = spPersonneService.listSpPersonne();
		List<SpPersonneVO> list=new ArrayList<SpPersonneVO>();
		spPersonne.forEach(sp->{
			list.add(new SpPersonneVO(sp.getPname(),sp.getMobileno(),sp.getCreateDate(),sp.getShiftId()));
		});
		return RespUtils.buildOKWithData(list);
	}

}
