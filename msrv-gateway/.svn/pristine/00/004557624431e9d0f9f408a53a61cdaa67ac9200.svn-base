package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.common.result.entity.Result;
import com.mhtech.common.result.entity.UnicomResponseEnums;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.mhtech.platform.msrv.monitor.service.ISysParameterService;
import com.mobile.model.Page;
import com.mobile.model.SysParameter;

/**
 * @ClassName SysParameterController
 * @Description 图表参数模块控制类
 * @Author admin
 * @Date 2019/8/28 13:51
 * @Version 1.0
 */


@CrossOrigin
@RequestMapping(value = "/monitor/sysParameter")
@RestController
@Api(value = "TemplateController", tags = "图表参数模块管理")
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class SysParameterController {

	
	@Autowired
	private ISysParameterService iSysParameterService;
	
	@PostMapping(value="/addAndModify")
    @ApiOperation(value = "添加")
    public Result<UnicomResponseEnums> addAndModify(@RequestBody SysParameter sysParameter){	
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		String id=sysParameter.getId();
		int flg=0;
		if (StringUtils.isBlank(id)) {
			msg = UnicomResponseEnums.ADD_SUCCESS;
			flg=  iSysParameterService.add(sysParameter);
		}else {
			msg = UnicomResponseEnums.UPDATE_SUCCESS;
			flg=iSysParameterService.update(sysParameter);
		}
		Result result = new Result(success, flg, msg);
        return result;	
    }

	@PostMapping(value="/add")
    @ApiOperation(value = "添加")
    public Result<UnicomResponseEnums> add(@RequestBody SysParameter sysParameter){	
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		int sum=  iSysParameterService.add(sysParameter);
		Result result = new Result(success, sum, msg);
        return result;	
    }
	
	@PostMapping(value="/del")
    @ApiOperation(value = "根据id删除")
    public Result<UnicomResponseEnums> del(@RequestBody Map<String,String> map){
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.DEL_SUCCESS;
		int sum= iSysParameterService.delete(map.get("id"));
		Result result = new Result(success, sum, msg);
        return result;	
    }
	
	@PostMapping(value="/update")
	@ApiOperation(value = "根据id修改")
	public Result<UnicomResponseEnums> update (@RequestBody SysParameter sysParameter) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		int sum=iSysParameterService.update(sysParameter);
		Result result = new Result(success, sum, msg);
        return result;	
	}
	
	@PostMapping(value = "/queryPage")
	@ApiOperation(value = "分页+条件查询") 	
    public Result<UnicomResponseEnums> queryEventPage(@RequestBody Map<String,String> map ) {		
        boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        Page eventPage = iSysParameterService.findAllPage(map);
        Result result = new Result(success, eventPage, msg);
        return result;
    }
	
	@PostMapping(value="/queryTypeCode")
    @ApiOperation(value = "参数类型+参数编码是否唯一",notes="ture 存在 false 不存在")
    public Result<UnicomResponseEnums> queryTypeCode(@RequestBody SysParameter sysParameter){
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		boolean sum= iSysParameterService.findTypeCode(sysParameter.getParmType(),sysParameter.getParmCode());
		Result result = new Result(success, sum, msg);
        return result;
    }
	
	@PostMapping(value="/getParmName")
	@ApiOperation(value = "依据parmType,parmCode查询ParmName")
	public Result<UnicomResponseEnums> getParmName (@RequestBody @Valid SysParameter sysParameter,BindingResult br) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		if (br.hasErrors()) {
			return new Result(success,br.getFieldError().getDefaultMessage(),msg);
		}
		String s=iSysParameterService.getParmName(sysParameter.getParmType(),sysParameter.getParmCode());
        return new Result(success, s, msg);
	}
	
	@PostMapping(value="/getCodeType")
	@ApiOperation(value = "查询shift的parmCode,ParmName")
	public Result<UnicomResponseEnums> getCodeType (@RequestBody SysParameter sysParameter) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;	
		List<Map<String, Object>> list=iSysParameterService.getCodeType(sysParameter.getParmType());
        return new Result(success, list, msg);
	}
	
	
}
