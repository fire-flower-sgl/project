package com.mhtech.platform.msrv.gateway.controller.monitor;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mhtech.platform.msrv.auth.service.ISpUserService;
import com.mhtech.platform.msrv.gateway.request.SpUserParams;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.pojo.SpUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value = "/user/spUser")
@Api(value = "SpUserController", tags = "用户")
public class SpUserController {

	@Reference(version="0.0.1",group="youge")
	private ISpUserService iSpUserService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/selectUser")
	@ApiOperation(value = "查询用户信息")
	public RespObject<?> selectUser(@RequestBody @Valid SpUserParams sp) {
		SpUserVO select = null;
		try {
			select = iSpUserService.select(sp.getName(), sp.getPassword());
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("查询user信息异常", e);
			throw new RuntimeException("查询user信息异常");
		}
		return RespUtils.buildOKWithData(select);
	}

	@PostMapping("/add")
	@ApiOperation(value = "新增用户信息")
	public RespObject<?> add(@RequestBody @Valid SpUserParams sp) {

		SpUserVO dto = new SpUserVO(sp.getUpdateUser(), sp.getName(), sp.getPassword(), sp.getCompanyCode(),
				sp.getOrgCode(), sp.getEmail(), sp.getPhone(), sp.getMultiLogin(), sp.getUserType(), sp.getUpdateUser(),
				sp.getUpdateTime());
		int select = 0;
		try {
			select = iSpUserService.insertSelective(dto);
		} catch (Exception e) {
			logger.error("新增user信息异常", e);
			throw new RuntimeException("新增user信息异常");
		}
		return RespUtils.buildOKWithData(select);
	}

	@PostMapping("/update")
	@ApiOperation(value = "修改用户信息")
	public RespObject<?> update(@RequestBody @Valid SpUserParams sp) {
		SpUserVO dto = new SpUserVO(sp.getId(),sp.getUpdateUser(), sp.getName(), sp.getPassword(), sp.getCompanyCode(),
				sp.getOrgCode(), sp.getEmail(), sp.getPhone(), sp.getMultiLogin(), sp.getUserType(), sp.getUpdateUser(),
				sp.getUpdateTime());
		int select = 0;
		try {
			select = iSpUserService.updateByPrimaryKeySelective(dto);
		} catch (Exception e) {
			logger.error("修改user信息异常", e);
			throw new RuntimeException("修改user信息异常");
		}
		return RespUtils.buildOKWithData(select);
	}
	

	@PostMapping("/delect")
	@ApiOperation(value = "删除用户信息")
	public RespObject<?> delect(@RequestBody @Valid SpUserParams sp) {
		
		int select = 0;
		try {
			select = iSpUserService.delete(sp.getId());
		} catch (Exception e) {
			logger.error("删除user信息异常", e);
			throw new RuntimeException("删除user信息异常");
		}
		return RespUtils.buildOKWithData(select);
	}
	
	
	
	
	

}
