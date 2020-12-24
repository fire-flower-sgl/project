package com.mhtech.platform.msrv.gateway.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.model.Page;
import com.mobile.model.Result;
import com.mobile.model.UnicomResponseEnums;
import com.mobile.service.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value = "/MobileApi/task")
@SuppressWarnings("all")
@Api(value = "MobileTaskController", tags = "定时任务管理")
public class MobileTaskController {
	@Autowired
	TaskService taskService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// 初始化启动所有的Job
//	@PostConstruct
//	public void initialize() {
//
//		boolean initializeJob = taskService.initializeJob();
//		if (initializeJob) {
//			log.info("init success");
//		} else {
//			log.info("init error");
//		}
//	}
	@PostMapping(value = "/initialize")
	@ApiOperation(value = "启动任务")
	public Result initialize(@RequestBody Map<String, Object> map) {
		boolean initializeJob = taskService.initializeJob();
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		if(!initializeJob) {
			msg = UnicomResponseEnums.Task_ERROR;
		}
		return new Result(initializeJob, initializeJob, msg);
	}
	@PostMapping(value = "/restartJob")
	@ApiOperation(value = "启动任务")
	public Result restartJob(@RequestBody Map<String, Object> map) {
		boolean success = true;
		boolean restartJob = taskService.restartJob(map.get("taskId").toString());
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		if(!restartJob) {
			msg = UnicomResponseEnums.Task_ERROR;
		}
		return new Result(restartJob, restartJob, msg);
	}
	@PostMapping(value = "/stopJob")
	@ApiOperation(value = "停止任务")
	public Result stopJob(@RequestBody Map<String, Object> map) {
		boolean success = true;
		boolean stopJob = taskService.stopJob(map.get("taskId").toString());
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		if(!stopJob) {
			msg = UnicomResponseEnums.Task_ERROR;
		}
		return new Result(stopJob, stopJob, msg);
	}
	@PostMapping(value = "/queryPage")
	@ApiOperation(value = "分页查询任务")
	public Result querPage(@RequestBody Map<String, Object> map) {
		boolean success = true;
		Page queryPage = taskService.queryPage(map);
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		return new Result(success, queryPage, msg);
	}
	@PostMapping(value = "/addTask")
	@ApiOperation(value = "新增任务")
	public Result addTask(@RequestBody Map<String, Object> map) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		if(map==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("taskName")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("taskGroup")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("taskCron")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("methodName")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("beanName")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("status")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		boolean addTask = taskService.addTask(map);
		if(!addTask) {
			success=false;
			msg = UnicomResponseEnums.DATABASE_ERROR;
		}
		return new Result(success, addTask, msg);
	}
	@PostMapping(value = "/modifyTask")
	@ApiOperation(value = "修改任务")
	public Result modifyTask(@RequestBody Map<String, Object> map) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		if(map==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("taskId")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("taskName")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("taskGroup")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("taskCron")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("methodName")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("beanName")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("status")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		boolean modifyTask = taskService.modifyTask(map);
		if(!modifyTask) {
			success=false;
			msg = UnicomResponseEnums.DATABASE_ERROR;
		}
		return new Result(success, modifyTask, msg);
	}
	/**
	 * 立即执行任务
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/ExecutedImmediately")
	@ApiOperation(value = "立即执行任务")
	@ResponseBody
	public Result<UnicomResponseEnums>  ExecutedImmediately(@RequestBody Map<String,Object> map) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		
		if(map.get("taskId")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("taskName")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("taskGroup")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("taskCron")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("methodName")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		if(map.get("beanName")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		
		success = taskService.ExecutedImmediately(map);
		if(!success)
			msg=UnicomResponseEnums.Task_ERROR;
		Result result = new Result(success, null, msg);
		return result;
	}
	@PostMapping(value = "/delTask")
	@ApiOperation(value = "删除任务")
	public Result delTask(@RequestBody Map<String, Object> map) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		if(map.get("taskId")==null) {
			success=false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		success = taskService.delTask(map.get("taskId").toString());
		if(!success)
			msg=UnicomResponseEnums.Task_ERROR;
		Result result = new Result(success, null, msg);
		return result;
	}
	@PostMapping(value = "/validationTask")
	@ApiOperation(value = "任务校验")
	public Result validationTask(@RequestBody Map<String,Object> map) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		success=taskService.validationTask(map);
		if(!success)
			msg = UnicomResponseEnums.task_EXIST2;
		return new Result(success, success, msg);
	}
}
