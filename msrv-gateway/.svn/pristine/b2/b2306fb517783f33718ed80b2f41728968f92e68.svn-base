package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mhtech.common.result.entity.Result;
import com.mhtech.common.result.entity.UnicomResponseEnums;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.Utilis;
import com.mhtech.platform.msrv.monitor.service.ISysParameterService;
import com.mhtech.platform.msrv.monitor.service.SpAlertNotifyTmplService;
import com.mhtech.platform.msrv.monitor.service.TaskService;
import com.mhtech.platform.msrv.pojo.SpAlertNotifyTmpl;
import com.mhtech.platform.msrv.pojo.Task;
import com.mobile.model.Page;
import com.mobile.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Controller
@CrossOrigin
@RequestMapping(value = "/monitor/spAlertNotifyTmpl")
@Api(value = "SpAlertNotifyTmplController", tags = "告警通知模板")
public class SpAlertNotifyTmplController {
	
	@Autowired
	private SpAlertNotifyTmplService spAlertNotifyTmplService;
	
	@Autowired
	private IworkService iworkService;
	
	/**
	 * 查询告警通知模板
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/getList")
	@ApiOperation(value = "查询告警通知模板")
	@ResponseBody
	public Result<UnicomResponseEnums> getList(@RequestBody Map<String,Object> map) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		Page list=spAlertNotifyTmplService.getList(map);
		return new Result(success, list, msg);
	}
	
	/**
	 * 新增或者修改告警通知模板
	 * 
	 * @param task
	 */
	@PostMapping(value = "/addAndModify")
	@ApiOperation(value = "新增或者修改告警通知模板")
	@ResponseBody
	public Result<UnicomResponseEnums> addAndModify(@RequestBody @Valid SpAlertNotifyTmpl spAlertNotifyTmpl) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		Long id=spAlertNotifyTmpl.getId();
		int flg =0;
		if (id==null) {
			msg=UnicomResponseEnums.ADD_SUCCESS;
			spAlertNotifyTmpl.setId(iworkService.getNextId());
			flg = spAlertNotifyTmplService.add(spAlertNotifyTmpl);
		}else {
			msg=UnicomResponseEnums.UPDATE_SUCCESS;
			flg = spAlertNotifyTmplService.modify(spAlertNotifyTmpl);
		}
		if (flg <= 0) {
			success=false;
			msg=UnicomResponseEnums.DATABASE_ERROR;
		}
		Result result = new Result(success, null, msg);
		return result;
	}
	
	/**
	 * 删除任务告警通知模板
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/del")
	@ApiOperation(value = "删除任务告警通知模板")
	@ResponseBody
	public Result<UnicomResponseEnums> del(@RequestBody SpAlertNotifyTmpl spAlertNotifyTmpl) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.DEL_SUCCESS;
		List<Map<String, Object>> spAlertNotifyTmplOld=spAlertNotifyTmplService.getEntity(spAlertNotifyTmpl.getId().toString());
		if (spAlertNotifyTmplOld.size()<=0) {//告警通知模板不存在
			success=false;
			msg=UnicomResponseEnums.ALERT_NULL;
			return new Result(success, null, msg);
		}
		int delete = 0;
		try {
			delete = spAlertNotifyTmplService.delete(spAlertNotifyTmpl);
			if (delete <= 0) {
				success=false;
				msg=UnicomResponseEnums.DATABASE_ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			success=false;
			msg=UnicomResponseEnums.METHOD_NOT_ALLOWED;
		}
		return new Result(success, null, msg);
	}
	
	
}
