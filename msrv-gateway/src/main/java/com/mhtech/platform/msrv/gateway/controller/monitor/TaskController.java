package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.Utilis;
import com.mhtech.platform.msrv.monitor.service.ISysParameterService;
import com.mhtech.platform.msrv.monitor.service.TaskService;
import com.mhtech.platform.msrv.pojo.Task;
import com.mobile.model.Page;
import com.mobile.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Controller
@CrossOrigin
@RequestMapping(value = "/monitor/task")
@Api(value = "taskController", tags = "定时任务")
public class TaskController {
	/*@Autowired
	ScheduleManager scheduleConfig;*/
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	ISysParameterService iSysParameterService;
	
	/**
	 * 新增或者修改任务
	 * 
	 * @param task
	 */
	@PostMapping(value = "/addAndModify")
	@ApiOperation(value = "添加任务")
	@ResponseBody
	public Result<UnicomResponseEnums> addAndModify(@RequestBody Task task) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		String taskId=task.getTaskId();
		int insertTask =0;
			if (StringUtils.isEmpty(taskId)) {
				msg = UnicomResponseEnums.ADD_SUCCESS;
				task.setTaskId(Utilis.getUUID());
				insertTask = taskService.insertTask(task);
				if (insertTask > 0) {
					//scheduleConfig.addTask(task);
					taskService.addTask(task);
				}else {
					success=false;
					msg=UnicomResponseEnums.DATABASE_ERROR;
				}
			}else {
				msg = UnicomResponseEnums.UPDATE_SUCCESS;
				int updateTask = taskService.updateTask(task);
				if (updateTask > 0) {
					//scheduleConfig.modifyTask(task);
					taskService.modifyTask(task);
				}else {
					success=false;
					msg=UnicomResponseEnums.DATABASE_ERROR;
				}
			}
		Result result = new Result(success, null, msg);
		return result;
	}
	
	
	/**
	 * 添加任务 并添加到任务注册表
	 * 
	 * @param task
	 */
	@PostMapping(value = "/addTask")
	@ApiOperation(value = "添加任务")
	@ResponseBody
	public Result<UnicomResponseEnums> addTask(@RequestBody Task task) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.ADD_SUCCESS;
		task.setTaskId(Utilis.getUUID());
		try {
			int insertTask = taskService.insertTask(task);
			if (insertTask > 0) {
				//scheduleConfig.addTask(task);
				taskService.addTask(task);
			}else {
				success=false;
				msg=UnicomResponseEnums.DATABASE_ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			success=false;
			msg=UnicomResponseEnums.METHOD_NOT_ALLOWED;
		}
		Result result = new Result(success, null, msg);
		return result;
	}
	
	/**
	 * 添加任务 并添加到任务注册表
	 * 
	 * @param task
	 */
	@PostMapping(value = "/startTask")
	@ApiOperation(value = "开始任务")
	@ResponseBody
	public Result<UnicomResponseEnums> startTask(@RequestBody Task task) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.ACTION_SUCCESS;
//		task.setStatus("1");
		try {
//			int insertTask = taskService.updateTask(task);
			if(task!=null&&task.getTaskId()!=null&&!task.getStatus().isEmpty()&&"0".equals(task.getStatus())) {
				success=false;
				msg=UnicomResponseEnums.task_START0;
			}else if (task!=null&&task.getTaskId()!=null&&!task.getTaskId().isEmpty()) {
				//scheduleConfig.addTask(task);
				taskService.addTask(task);
			}else {
				success=false;
				msg=UnicomResponseEnums.DATABASE_ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			success=false;
			msg=UnicomResponseEnums.METHOD_NOT_ALLOWED;
		}
		Result result = new Result(success, null, msg);
		return result;
	}

	/**
	 * 取消任务
	 * 
	 * @param task
	 */
	@PostMapping(value = "/cancelTask")
	@ApiOperation(value = "停止任务")
	@ResponseBody
	public Result<UnicomResponseEnums> cancelTask(@RequestBody Task task) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.ACTION_SUCCESS;
		task.setStatus("0");
		try {
//			int insertTask = taskService.updateTask(task);
//			if (insertTask > 0) {
			taskService.cancelTask(task.getTaskId());
//			}else {
//				success=false;
//				msg=UnicomResponseEnums.DATABASE_ERROR;
//			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			success=false;
			msg=UnicomResponseEnums.METHOD_NOT_ALLOWED;
		}
		Result result = new Result(success, null, msg);
		return result;
	}

	/**
	 * 查看任务是否存在
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/existTask")
	@ApiOperation(value = "查看任务是否存在")
	@ResponseBody
	public Result<UnicomResponseEnums>  existTask(@RequestBody Task task) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.task_EXIST;
		success=taskService.existTask(task.getTaskId());
		if(!success)
			msg = UnicomResponseEnums.Task_NOT_EXIST;
		Result result = new Result(success, null, msg);
		return result;
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
	public Result<UnicomResponseEnums>  ExecutedImmediately(@RequestBody Task task) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.ACTION_SUCCESS;
		success=taskService.ExecutedImmediately(task);
		if(!success)
			msg=UnicomResponseEnums.Task_ERROR;
		Result result = new Result(success, null, msg);
		return result;
	}

	/**
	 * 修改任务
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/modifyTask")
	@ApiOperation(value = "修改任务")
	@ResponseBody
	public Result<UnicomResponseEnums>  modifyTask(@RequestBody Task task) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.UPDATE_SUCCESS;
		Result result = new Result(success, null, msg);
		try {
			int updateTask = taskService.updateTask(task);
			if (updateTask > 0) {
				//scheduleConfig.modifyTask(task);
				taskService.modifyTask(task);
				return new Result(success, null, msg);
			} else {
				msg=UnicomResponseEnums.DATABASE_ERROR;
				return new Result(success, null, msg);
			}

		} catch (Exception e) {
			// TODO: handle exception
			msg=UnicomResponseEnums.Task_ERROR;
			return new Result(success, null, msg);
		}
	}
	/**
	 * 查询任务
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/findTask")
	@ApiOperation(value = "查询任务")
	@ResponseBody
	public Result<UnicomResponseEnums> findTask(@RequestBody Map<String,Object> map) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//		 Map<Object, ScheduledTask> task = scheduleConfig.getTask();
//		 List<Map<String,Object>> queryList = taskService.queryList(map);
		 Page queryPage = taskService.queryPage(map);
//		 List<Map<String,Object>> results=new ArrayList<Map<String,Object>>();
//		 for(Map<String,Object> mp:queryList) {
//			 String taskId=mp.get("taskId").toString();
//			 if(task.get(taskId)!=null) {
//				 mp.put("runStatus", "1");
//			 }else {
//				 mp.put("runStatus", "0");
//			 }
//			 results.add(mp);
//		 }		 
		return new Result(success, queryPage, msg);
	}
	/**
	 * 查询任务
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/dropTask")
	@ApiOperation(value = "删除任务")
	@ResponseBody
	public Result<UnicomResponseEnums> dropTask(@RequestBody Task task) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.DEL_SUCCESS;
		/*boolean existTask=taskService.existTask(task.getTaskId());
		if (!existTask) {//任务不存在
			success=false;
			msg=UnicomResponseEnums.Task_NOT_EXIST;
			return new Result(success, null, msg);
		}*/
		try {
			int deleteTask = taskService.deleteTask(task);
			if (deleteTask > 0) {
				taskService.cancelTask(task.getTaskId());
			}else {
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
	@PostMapping(value = "/TaskCount")
	@ApiOperation(value = "条件查询任务数量")
	@ResponseBody
	public Result<UnicomResponseEnums> queryTaskCount(@RequestBody Task task) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		if(task==null||task.getBeanName().trim().isEmpty()||task.getMethodName().trim().isEmpty()) {
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, null, msg);
		}
		int queryTaskCount = taskService.queryTaskCount(task);
		if(queryTaskCount>0) {
			success=false;
			msg = UnicomResponseEnums.task_EXIST2;
		}
		
		return new Result(success, null, msg);
	}
	
	@ApiOperation("字典常量")
	@ApiResponse(code = 200, message = "请求成功")
	@GetMapping(value = "/dict")
	@ResponseBody
	public Result<UnicomResponseEnums> get() {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		Map<String, List<Map<String, Object>>> dict = new HashMap<>();
		//任务状态
		List<Map<String, Object>> monitorStatus = iSysParameterService.findParameterByParmType("taskStatus");
		dict.put("taskStatus", monitorStatus);
		//当前状态
		List<Map<String, Object>> operator = iSysParameterService.findParameterByParmType("performStatus");
		dict.put("performStatus", operator);
		//运行状态
		List<Map<String, Object>> runStatus = iSysParameterService.findParameterByParmType("runStatus");
		dict.put("runStatus", runStatus);
		
		return new Result(success, dict, msg);
	}
}
