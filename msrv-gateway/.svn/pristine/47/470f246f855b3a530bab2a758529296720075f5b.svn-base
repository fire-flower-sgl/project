package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mhtech.common.result.entity.CustomException;
import com.mhtech.common.result.entity.Result;
import com.mhtech.common.result.entity.UnicomResponseEnums;
import com.mhtech.platform.msrv.auth.service.ISpUserService;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.ISysParameterService;
import com.mhtech.platform.msrv.monitor.service.SpAlertModelService;
import com.mhtech.platform.msrv.monitor.service.SpParamAlertService;
import com.mhtech.platform.msrv.pojo.SpAlertModel;
import com.mhtech.platform.msrv.pojo.SpParamAlert;
import com.mhtech.platform.msrv.pojo.SpUserShift;
import com.mobile.model.Page;
import com.mobile.utils.DateUtils;
import com.mobile.utils.ExcelUtil;
import com.mobile.utils.StringUtils;
import com.sun.mail.handlers.text_html;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value = "/monitor/spParamAlert")
@Api(value = "SpParamAlertController", tags = "告警规则设置")
public class SpParamAlertController {
	
	@Autowired
	private SpParamAlertService spParamAlertService;
	
	@Autowired
	private IworkService iworkService;
	
	@Autowired
	private SpAlertModelService  spAlertModelService;
	
	@Autowired
	ISysParameterService iSysParameterService;
	
	@Reference(version="0.0.1",group="monitor")
	private ISpUserService monitorISpUserService;
	
	/**
	 * 查询告警规则设置
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/getList")
	@ApiOperation(value = "查询告警规则设置")
	@ResponseBody
	public Result<UnicomResponseEnums> getList(@RequestBody Map<String,Object> map) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		Page list=spParamAlertService.getList(map);
		return new Result(success, list, msg);
	}
	
	/**
	 * 模糊查询告警规则设置模板
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/getModel")
	@ApiOperation(value = "查询告警规则设置")
	@ResponseBody
	public Result<UnicomResponseEnums> getModel(@RequestBody SpAlertModel spAlertModel) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		List<Map<String, Object>> list=spAlertModelService.getModelList(spAlertModel.getModelName());
		return new Result(success, list, msg);
	}
	
	/**
	 * 新增告警规则设置模板
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/addModel")
	@ApiOperation(value = "新增告警规则设置模板")
	@ResponseBody
	public Result<UnicomResponseEnums> addModel(@RequestBody SpAlertModel spAlertModel) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.ADD_SUCCESS;
		spAlertModel.setId(iworkService.getNextId()+"");
		int list=spAlertModelService.add(spAlertModel);
		return new Result(success, list, msg);
	}
	
	/**
	 * 查询告警规则设置模板
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/getModelEntity")
	@ApiOperation(value = "查询告警规则模板")
	@ResponseBody
	public Result<UnicomResponseEnums> getModelEntity(@RequestBody SpAlertModel spAlertModel) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		List<Map<String, Object>> list=spAlertModelService.getEntity(spAlertModel.getId());
		return new Result(success, list.get(0), msg);
	}
	
	/**
	 * 所需参数字典
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/dic")
	@ApiOperation(value = "所需参数字典")
	@ResponseBody
	public Result<UnicomResponseEnums> dic() {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		//告警指标
		List<Map<String, Object>> paramName = iSysParameterService.findParameterByParmType("paramName");
		//告警级别
		List<Map<String, Object>> level = iSysParameterService.findParameterByParmType("level");
		//状态
		List<Map<String, Object>> status = iSysParameterService.findParameterByParmType("paramAlertStatus");
		//获取用户列表
		List<Map<String, Object>> user =monitorISpUserService.selectAllUser("");
		//预警类型
		JSONArray alertType =spParamAlertService.getAlertType();
		//频次
		List<Map<String, Object>> duration = iSysParameterService.findParameterByParmType("duration");
		//通知方式
		List<Map<String, Object>> notifyMethod = iSysParameterService.findParameterByParmType("notifyMethod");
		//频次通知人员获取方式
		List<Map<String, Object>> contactsMethod = iSysParameterService.findParameterByParmType("contactsMethod");
		//通知屏蔽状态
		List<Map<String, Object>> notifyStatus = iSysParameterService.findParameterByParmType("status");
		//排班
		//List<Map<String, Object>> shift = spParamAlertService.getRange();
		//服务器状态(硬件还是软件)
		List<Map<String, Object>> serverType = iSysParameterService.findParameterByParmType("serverType2");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paramName", paramName);
		map.put("level", level);
		map.put("user", user);
		map.put("status", status);
		map.put("alertType", alertType);
		map.put("duration", duration);
		map.put("notifyMethod", notifyMethod);
		map.put("contactsMethod", contactsMethod);
		map.put("notifyStatus", notifyStatus);
		//map.put("shift", shift);
		map.put("serverType", serverType);
		return new Result(success, map, msg);
	}
	
	/**
	 * 新增或者修改告警规则设置
	 * 
	 * @param task
	 */
	@PostMapping(value = "/addAndModify")
	@ApiOperation(value = "新增或者修改告警规则设置")
	@ResponseBody
	public Result<UnicomResponseEnums> addAndModify(@RequestBody @Valid SpParamAlert spParamAlert) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		String id=spParamAlert.getId();
		int flg =0;
		if (id==null||StringUtils.isBlank(id)) {
			msg=UnicomResponseEnums.ADD_SUCCESS;
			spParamAlert.setId(iworkService.getNextId()+"");
			spParamAlert.setCreatedTime(DateUtils.getDate2());
			flg = spParamAlertService.add(spParamAlert);
		}else {
			msg=UnicomResponseEnums.UPDATE_SUCCESS;
			List<Map<String, Object>> list=spParamAlertService.getEntity(id);
			if (list.size()!=1) {
				success=false;
				msg=UnicomResponseEnums.NULL_CHANGE_DATA;
				return new Result(success, msg);
			}
			flg = spParamAlertService.modify(spParamAlert);
		}
		JSONObject js=new JSONObject();
		if (flg <= 0) {
			success=false;
			msg=UnicomResponseEnums.DATABASE_ERROR;
		}else {
			js.put("id", spParamAlert.getId()+"");
			js.put("code", spParamAlert.getServerId());
		}
		Result result = new Result(success, js, msg);
		return result;
	}
	
	/**
	 * 删除任务告警规则设置
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/del")
	@ApiOperation(value = "删除任务告警规则设置")
	@ResponseBody
	public Result<UnicomResponseEnums> del(@RequestBody SpParamAlert spParamAlert) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.DEL_SUCCESS;
		List<Map<String, Object>> spAlertNotifyTmplOld=spParamAlertService.getEntity(spParamAlert.getId().toString());
		if (spAlertNotifyTmplOld.size()<=0) {//告警规则设置不存在
			success=false;
			msg=UnicomResponseEnums.ALERT_NULL;
			return new Result(success, null, msg);
		}
		int delete = 0;
		try {
			delete = spParamAlertService.delete(spParamAlert);
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
	
	
	/**
	 * 设置通知规则
	 * 
	 * @param task
	 * @return
	 */
//	@PostMapping(value = "/setAlertNotifyRule")
//	@ApiOperation(value = "设置通知规则")
//	@ResponseBody
//	public Result<UnicomResponseEnums> setAlertNotifyRule(@RequestBody SpParamAlert spParamAlert) {
//		boolean success = true;
//		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//		List<Map<String, Object>> spAlertNotifyTmplOld=spParamAlertService.getEntity(spParamAlert.getId().toString());
//		if (spAlertNotifyTmplOld.size()<=0) {//告警规则设置不存在
//			success=false;
//			msg=UnicomResponseEnums.ALERT_NULL;
//			return new Result(success, null, msg);
//		}
//		//把人员列表拼接成字符串
//		String contactsMethod=spParamAlert.getContactsMethod();
//		String contactArray="";
//		if (contactsMethod.equals("custom")) {
//			List<String> contacts=(List<String>) spParamAlert.getContacts();
//			if (contacts.size()>0) {
//				for (int i = 0; i < contacts.size(); i++) {
//					String contact=contacts.get(i);
//					contactArray+=","+contact;
//				}
//				contactArray=contactArray.substring(1);
//			}
//		}else if (contactsMethod.equals("range")) {
//			List<String> shiftId=(List<String>) spParamAlert.getShiftId();
//			if (shiftId.size()>0) {
//				contactArray=spParamAlertService.getRangeUser(shiftId);
//			}
//		}
//		if (StringUtils.isBlank(contactArray)) {
//			success=false;
//			msg=UnicomResponseEnums.USER_NULL;
//			return new Result(success, null, msg);
//		} 
//		if (StringUtils.isBlank(spParamAlert.getStopListenStart())) {
//			spParamAlert.setStopListenStart(null);
//		}else {
//			spParamAlert.setStopListenStart("'"+spParamAlert.getStopListenStart()+"'");
//		}
//		if (StringUtils.isBlank(spParamAlert.getStopListenEnd())) {
//			spParamAlert.setStopListenEnd(null);
//		}else {
//			spParamAlert.setStopListenEnd("'"+spParamAlert.getStopListenEnd()+"'");
//		}
//		spParamAlert.setContactArray(contactArray);
//		int flg=spParamAlertService.modifyRule(spParamAlert);
//		if (flg <= 0) {
//			success=false;
//			msg=UnicomResponseEnums.DATABASE_ERROR;
//		}
//		return new Result(success, msg);
//	}
	
	/**
	 * 设置通知规则
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/setAlertNotifyRule")
	@ApiOperation(value = "设置通知规则")
	@ResponseBody
	public Result<UnicomResponseEnums> setAlertNotifyRule(@RequestBody SpParamAlert spParamAlert) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SAVE_SUCCESS;
		List<Map<String, Object>> spAlertNotifyTmplOld=spParamAlertService.getEntity(spParamAlert.getId().toString());
		if (spAlertNotifyTmplOld.size()<=0) {//告警规则设置不存在
			success=false;
			msg=UnicomResponseEnums.ALERT_NULL;
			return new Result(success, null, msg);
		}
		//把自定义人员列表拼接成字符串
		String contactsMethod=spParamAlert.getContactsMethod();
		String contactArray="";
		List<String> contacts=(List<String>) spParamAlert.getContacts();
		if (contacts.size()>0) {
			for (int i = 0; i < contacts.size(); i++) {
				String contact=contacts.get(i);
				contactArray+=","+contact;
			}
			contactArray=contactArray.substring(1);
		}else {//自定义人员不能为空
			success=false;
			msg=UnicomResponseEnums.USER_NULL;
			return new Result(success, null, msg);
		} 
		//排班人员修改（当天之后的排班有效）排班人员自己寻找
//		List<Map<String, Object>> shiftId=(List<Map<String, Object>>) spParamAlert.getShiftId();
//		if (contactsMethod.equals("range")) {//如果选择排班则显示排班，选择自定义则用自定义人员
//			if (shiftId.size()>0) {
//				//首先更新排班表（根据ip和日期）
//				
//				//更新所有ip相关的预警规则
//				
//			}
//		}
		
		
		//暂停监控时间
		if (StringUtils.isBlank(spParamAlert.getStopListenStart())) {
			spParamAlert.setStopListenStart(null);
		}else {
			spParamAlert.setStopListenStart(spParamAlert.getStopListenStart());
		}
		if (StringUtils.isBlank(spParamAlert.getStopListenEnd())) {
			spParamAlert.setStopListenEnd(null);
		}else {
			spParamAlert.setStopListenEnd(spParamAlert.getStopListenEnd());
		}
		spParamAlert.setContactArray(contactArray);
		int flg=spParamAlertService.modifyRule(spParamAlert);
		if (flg <= 0) {
			success=false;
			msg=UnicomResponseEnums.DATABASE_ERROR;
		}
		return new Result(success, msg);
	}
	
	
	/**
	 * 获取历史回溯
	 * 
	 * @param task
	 * @return
	 */
	@PostMapping(value = "/getAlermHistroy")
	@ApiOperation(value = "获取历史回溯")
	@ResponseBody
	public Result<UnicomResponseEnums> getAlermHistroy(@RequestBody SpParamAlert spParamAlert) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		JSONObject date=new JSONObject();
		//告警原因
		List<Map<String, Object>> alermReason=spParamAlertService.getAlermReason(spParamAlert.getId());
		date.put("alermReason", alermReason);
		//告警处理结果
		List<Map<String, Object>> alermDeal=spParamAlertService.getAlermDeal(spParamAlert.getId());
		date.put("alermDeal", alermDeal);
		//按照日，周，月统计数据
		String curDate=DateUtils.getDate();//当前日期
		//循环获取7天数据
		JSONArray days=new JSONArray();
		String startTime=curDate+" 00:00:00";
		String endTime=DateUtils.addDateTime(startTime, 0, 0, 1);
		for (int i = 0; i < 7; i++) {
			JSONObject day=new JSONObject();
			int count=spParamAlertService.getAlermHistroy(spParamAlert.getId(),startTime,endTime);
			day.put("time", startTime.split(" ")[0]);//时间下标
			day.put("count", count);//告警次数
			days.add(day);
			endTime=startTime;
			startTime=DateUtils.addDateTime(endTime, 0, 0, -1);
		}
		date.put("days", days);
		//循环获取4周数据
		JSONArray weeks=new JSONArray();
		String weekDays[]=DateUtils.getWeekDay(curDate).split(",");
		startTime=weekDays[0]+" 00:00:00";
		endTime=DateUtils.addDateTime(startTime, 0, 0, 7);
		for (int i = 0; i < 4; i++) {
			JSONObject week=new JSONObject();
			int count=spParamAlertService.getAlermHistroy(spParamAlert.getId(),startTime,endTime);
			week.put("time", startTime.split(" ")[0]+"~"+DateUtils.addDateTime(endTime, 0, 0, -1).split(" ")[0]);//时间下标
			week.put("count", count);//告警次数
			weeks.add(week);
			endTime=startTime;
			startTime=DateUtils.addDateTime(endTime, 0, 0, -7);
		}
		date.put("weeks", weeks);
		//循环获取12月的数据
		JSONArray months=new JSONArray();
		String monthFirstDay=DateUtils.getFirstDayOfMonth(Integer.parseInt(DateUtils.getYear()),Integer.parseInt(DateUtils.getMonth()));
		startTime=monthFirstDay+" 00:00:00";
		endTime=DateUtils.addDateTime(startTime, 0, 1, 0);
		for (int i = 0; i < 12; i++) {
			JSONObject month=new JSONObject();
			int count=spParamAlertService.getAlermHistroy(spParamAlert.getId(),startTime,endTime);
			Date time=DateUtils.parseDate(startTime);
			month.put("time",DateUtils.formatDate(time,"yyyy")+"年"+DateUtils.formatDate(time,"MM")+"月");//时间下标
			month.put("count", count);//告警次数
			months.add(month);
			endTime=startTime;
			startTime=DateUtils.addDateTime(endTime, 0, -1, 0);
		}
		date.put("months", months);
		int count=spParamAlertService.getAlermHistroy(spParamAlert.getId(),startTime,endTime);
		return new Result(success, date, msg);
	}
	
	/**
	 * 返回画图所需数据
	 * TODO
	 * @return
	 * @throws CustomException 
	 * @throws ParseException 
	 */
	@RequestMapping(value="/shiftExport")
	@ResponseBody
	@ApiOperation(value="导入排班数据", notes="导入排班数据",produces = "application/json")
	//@Transactional(rollbackFor = {ClassCastException.class,Exception.class,CustomException.class, ParseException.class}) //事务回滚
	public String shiftExport(@RequestParam(required=false) MultipartFile file) throws CustomException, ParseException {
		long start = System.currentTimeMillis();
		UnicomResponseEnums msg=UnicomResponseEnums.EXPORT_SUCCESS;
		boolean success=true;
		JSONObject date=new JSONObject();
		//Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();//回滚点
		try {
		//判断文件不能为空
		if (file.isEmpty()) {
			throw new CustomException(UnicomResponseEnums.UPLOAD_FILE_ERR);
        }
		String fileName = file.getOriginalFilename();//文件原始名称
		String filePath =System.getProperty("user.dir")+"/temp/";//项目相对路径
        File path=new File(filePath);
        System.err.println(path.isDirectory()+" "+!path.exists());
        if (!path.exists()) {
        	path.mkdirs();
		}
        File dest = new File(filePath + fileName);
        //try {
        	file.transferTo(dest);
            System.err.println(dest.getAbsolutePath());
            System.err.println("上传成功");
            //return dest.getAbsolutePath();
            //获取Excel中的数据
            List<Object[]> list =ExcelUtil.importExcel(dest.getAbsolutePath());
            System.err.println("==========="+list.toString());
            if (list.size()>0) {
            	SpUserShift spUserShift=new SpUserShift();
            	spUserShift.setStatus("0");
            	spParamAlertService.delShiftUser(spUserShift);//更新历史信息状态为历史
                for (int i = 0; i < list.size(); i++) {
                	Object[] objects=list.get(i);
        			String userCode=objects[0].toString();
        			String ip=objects[1].toString();
        			Date startDate=(Date) objects[2];
        			Date endDate=(Date) objects[3];
        			spUserShift.setIp(ip);//ip
        			spUserShift.setStatus("1");//状态
        			spUserShift.setUserCode(userCode);//用户编码
        			String startTime=DateUtils.formatDateTime(startDate);
        			String endTime=DateUtils.formatDateTime(endDate);
        			String nextTime=startTime;
        			if (DateUtils.compareDate(endTime,nextTime)) {//前面的日期大于后面的日期
        				while (true) {//根据时间循环加入排班,首先执行一次保存
            				spUserShift.setId(iworkService.getNextId());//id
            				spUserShift.setUpdateTime(DateUtils.getDate2());//更新时间
            				spUserShift.setShiftTime(nextTime);//排班时间
            				spParamAlertService.saveShiftUser(spUserShift);
            				nextTime=DateUtils.addDateTime(nextTime, 0, 0, 1);//开始时间+1天
            				if (DateUtils.compareDate(nextTime,endTime)) {//下一天大于结束时间就结束循环
								break;
							}
        				}
					}else {
						throw new CustomException("0000","第"+(i+1)+"行，结束日期小于开始日期，请检查！");
					}
        			
        			//System.err.println(userCode+"==========="+ip+"========="+DateUtils.formatDate(startDate,"yyyy-MM-dd HH:mm:ss")+"==========="+endDate);
        		}
			}else {//文件内容为空
				throw new CustomException(UnicomResponseEnums.NULL_FILE);
			}
            
            long end = System.currentTimeMillis();
    	    System.out.println((end-start)/1000+"秒");
    		Result result=new Result(success,date,msg);
    		return result.toString();
        } catch (IOException e) {
        	//TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);//接收到异常就回滚
    	    System.err.println(e.toString());
    	    throw new CustomException(UnicomResponseEnums.UPLOAD_FILE_ERR);
       // }catch (Exception e) {
			// TODO: handle exception
        	//TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);//接收到异常就回滚
        	//throw new CustomException(UnicomResponseEnums.METHOD_NOT_ALLOWED);
		}
		
	}
	
	/**
	 * 根据排班日期和服务器id获取人员
	 * 
	 * @param task
	 * @return
	 * @throws CustomException 
	 */
	@PostMapping(value = "/getRangeUser")
	@ApiOperation(value = "根据排班日期和服务器id获取人员")
	@ResponseBody
	public Result<UnicomResponseEnums> getRangeUser(@RequestBody JSONObject parm) throws CustomException {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		String date=parm.getString("date");
		String id=parm.getString("id");
		String serverType=parm.getString("serverType");
		//根据id和serverType获取服务器ip
		String ip=spParamAlertService.getIpByServerType(id,serverType);
		JSONArray rangeUser=new JSONArray();
		if (!StringUtils.isBlank(ip)) {
			rangeUser= spParamAlertService.getRangeUser(date,ip);
		}
		return new Result(success,rangeUser, msg);
	}
	
	/**
	 * 保存排班表修改
	 * @param parm
	 * @return
	 * @throws CustomException 
	 */
	@PostMapping(value = "/updateRange")
	@ApiOperation(value = "保存排班表修改")
	@ResponseBody
	public Result<UnicomResponseEnums> updateRange(@RequestBody JSONObject parm) throws CustomException {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SAVE_SUCCESS;
		String date=parm.getString("date");
		String id=parm.getString("id");
		String serverType=parm.getString("serverType");
		List<String> userList=(List<String>) parm.get("userList");
		//根据id和serverType获取服务器ip
		String ip=spParamAlertService.getIpByServerType(id,serverType);
		JSONArray rangeUser=new JSONArray();
		if (!StringUtils.isBlank(ip)) {
			//删除该服务当天所有排班，插入新排班
			SpUserShift spUserShift=new SpUserShift();
			spUserShift.setIp(ip);
			spUserShift.setShiftTime(date);
			int flg=spParamAlertService.delShiftUser(spUserShift);
			//if (flg>0) {//如果是新增返回的也是0
				spUserShift.setStatus("1");
				for (int i = 0; i < userList.size(); i++) {
					spUserShift.setId(iworkService.getNextId());//id
    				spUserShift.setUpdateTime(DateUtils.getDate2());//更新时间
    				spUserShift.setUserCode(userList.get(i));
    				spParamAlertService.saveShiftUser(spUserShift);
				}
			//}
		}
		return new Result(success,"", msg);
	}
	
	
	
	/**
	 * 获取某个服务器的所有排班
	 * @param parm
	 * @return
	 * @throws CustomException 
	 */
	@PostMapping(value = "/getRangeByIp")
	@ApiOperation(value = "获取某个服务器的所有排班")
	@ResponseBody
	public Result<UnicomResponseEnums> getRangeByIp(@RequestBody JSONObject parm) throws CustomException {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//		System.err.println("===getRangeByIp:"+parm.get("serverType"));
//		System.err.println("===getRangeByIp:"+!parm.containsKey("id")+"=========");
//		System.err.println("===getRangeByIp:"+!parm.containsKey("serverType")+"=========");
//		System.err.println("===getRangeByIp:"+parm.get("serverType")=="null"+"=========");
//		System.err.println("===getRangeByIp:"+parm.get("id")=="null"+"=========");
//		System.err.println("===getRangeByIp:"+parm.get("serverType")==""+"=========");
//		System.err.println("===getRangeByIp:"+parm.get("id")==""+"=========");
		if (!parm.containsKey("id")||!parm.containsKey("serverType")
				||parm.get("serverType")=="null"||parm.get("id")=="null"
				||parm.get("serverType")==""||parm.get("id")=="") {
			success = false;
			msg = UnicomResponseEnums.NULL_ARGUMENT;
			return new Result(success, msg);
		}
		String id=parm.getString("id");
		String serverType=parm.getString("serverType");
		//根据id和serverType获取服务器ip
		String ip=spParamAlertService.getIpByServerType(id,serverType);
		JSONArray re=new JSONArray();
		if (!StringUtils.isBlank(ip)) {
			re=spParamAlertService.getRangeByIp(ip);
		}
		return new Result(success,re, msg);
	}
	
	/**
	 * 下载模板
	 * @param parm
	 * @return
	 * @throws CustomException 
	 */
	@PostMapping(value = "/downloadExcel")
	@ApiOperation(value = "下载模板")
	@ResponseBody
	public void downloadExcel(HttpServletResponse response,HttpServletRequest request){
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.DOWNLOAD_SUCCESS;
		 try {
            //获取文件的路径
            //String excelPath = request.getSession().getServletContext().getRealPath("/Excel/"+"模板.xlsx");
			String excelPath =  System.getProperty("user.dir")+"/Excel/"+"模板.xls";  
		 	String fileName = "模板.xls".toString(); // 文件的默认保存名
            // 读到流中
            FileInputStream inStream = new FileInputStream(excelPath);
            //InputStream inStream = new FileInputStream(excelPath);//文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("模板.xls", "UTF-8"));
            response.addHeader("Access-Control-Allow-Origin", "*");
            //response.setContentType("application/x-download");
            //response.setCharacterEncoding("UTF-8");
            // 循环取出流中的数据
            byte[] b = new byte[2048];
            int len;
 
            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		//return new Result(success,"", msg);
	}
	
//	// 下载导入模板
//	@PostMapping(value = "/fingDownloadMb")
//	@ApiOperation(value = "下载导入模板")
//	public void fingDownloadMb(HttpServletResponse response) throws IOException {
//		// response.setContentType(MIME)的作用是使客户端浏览器，区分不同种类的数据，并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据。
//		response.setContentType("application/json;charset=gb2312");
//		// 指定浏览器看这份数据使用的码表
//		response.setHeader("Content-Disposition",
//				"attachment;filename=" + new String("模板".getBytes("utf-8"), "ISO-8859-1"));
//		// 用与读取缓存中的数据
//		String excelPath =  System.getProperty("user.dir")+"/Excel/"+"模板.xls";  
//		try {
//			ServletOutputStream outputStream = response.getOutputStream();
//			byte[] buff = FileUtils.readFileToByteArray(new File(excelPath));
//			outputStream.write(buff);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
}
