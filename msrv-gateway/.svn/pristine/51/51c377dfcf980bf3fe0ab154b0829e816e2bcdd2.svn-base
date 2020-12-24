package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mhtech.common.result.entity.CustomException;
import com.mhtech.common.result.entity.Result;
import com.mhtech.common.result.entity.UnicomResponseEnums;
import com.mhtech.platform.msrv.auth.service.ISpUserService;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.gateway.request.ReqPropertyUpdate;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.GeneralConvertor;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IPropertyService;
import com.mhtech.platform.msrv.monitor.service.IServerAdminService;
import com.mhtech.platform.msrv.monitor.service.SpDeviceAdminService;
import com.mhtech.platform.msrv.monitor.service.SpParamAlertService;
import com.mhtech.platform.msrv.pojo.PropertyDTO;
import com.mhtech.platform.msrv.pojo.PropertyVOId;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerInfo;
import com.mhtech.platform.msrv.pojo.SpDeviceAdmin;
import com.mhtech.platform.msrv.pojo.SpParamAlert;
import com.mhtech.platform.msrv.pojo.SpUserShift;
import com.mobile.utils.DateUtils;
import com.mobile.utils.ExcelUtil;
import com.mobile.utils.FileUtil;
import com.mobile.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Controller
@CrossOrigin
@RequestMapping(value = "/monitor/api")
@Api(value = "taskController", tags = "定时任务")
@Transactional
public class MonitorApiController {

	@Autowired
	private IworkService iworkService;
	
	@Autowired
	private IServerAdminService serverAdminService;
	
	@Reference(version="0.0.1",group="monitor")
	private ISpUserService monitorISpUserService;
	
	@Autowired
    private GeneralConvertor dozer ;
	
	@Autowired
	private IPropertyService propertyService;
	
	@Autowired
	private SpDeviceAdminService spDeviceAdminService;
	
	@Autowired
	private SpParamAlertService spParamAlertService;
	
	/**
	 * 新增监控服务
	 * @param info
	 * @return 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@ApiOperation("新增和修改监控服务")
	@ApiResponse(code = 200, message = "请求成功")
	@PostMapping(value = "/serverInfo/addAndModify",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> post(@RequestBody @Validated ServerInfo info) {
		int flag=0;
		if (StringUtils.isEmpty(info.getParentServer())) {
			info.setParentServer("0");
		}
		//根据上级服务编码是否为空，判断有没有上级业务
		if(StringUtils.isEmpty(info.getIp()) && info.getParentServer().equals("0")) {
			throw new RuntimeException("IP跟父服务器KEY不能同时为空");
		} 
		String username=info.getUsername();
		//获取用户
		List<Map<String, Object>> user =monitorISpUserService.selectUserInfo(username);
		if (user.size()==1) {
			info.setMobile(user.get(0).get("phone").toString());
			info.setEmail(user.get(0).get("email").toString());
			List<String> pros=(List<String>) info.getProjects();
			//JSONArray pros=JSONArray.parseArray(info.getProjects().toString());
			String applyProjects="";
			if (pros.size()>0) {
				for (int i = 0; i < pros.size(); i++) {
					String parmCode=pros.get(i);
					//List<Map<String, Object>> list=iSysParameterService.findParameterByParmCodeOrParmName("projectName",parmCode,null);
					//System.err.println(list.get(0).get("value"));
					applyProjects+=parmCode+",";
				}
				applyProjects=applyProjects.substring(0,applyProjects.length()-1);
			}
			info.setApplyProjects(applyProjects);
			//根据是否有id判断是新增还是修改
			if (info.getId()==null||StringUtils.isEmpty(info.getId().toString())) {//为空则新增 
				info.setId(iworkService.getNextId());
				//随机生成编码：种类+时间+一位随机小数
				info.setServerCode("RJ"+DateUtils.getDate("yyyyMMddHHmmss")+(int)(Math.random()*11));
				info.setCreatedTime(DateUtils.getDate2());
				flag=serverAdminService.addEntity(info);
			}else {
				ServerAdminInfo infoOld=serverAdminService.getServerInfo(info.getId());
				info.setServerCode(infoOld.getServerCode());
				flag=serverAdminService.updateServerInfo(info);
			}
		}else {
			throw new RuntimeException("该管理人员不存在，请刷新页面！");
		}
		RespCode respCode=RespCode.SUCCESS;
		if (flag <= 0) {
			respCode=RespCode.DATABASE_ERROR;
			return RespUtils.build(respCode.getCode(),respCode.getMessage(),null);
		}
		JSONObject js=new JSONObject();
		js.put("id", info.getId()+"");
		js.put("code", info.getServerCode());
        return RespUtils.build(respCode.getCode(),respCode.getMessage(),js);
	}
	
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/serverInfo/delById")
	@ApiOperation(value = "根据id删除")
	@ResponseBody
    public RespObject<?> delServerInfo(@RequestBody ServerAdminInfo id ) {
		int flag =1;
		if (id.getId()!=null&&id.getId()!="") {
			String[] ids=id.getId().split(",");
			List<String> idList = new ArrayList<String>();
			Collections.addAll(idList, ids);
			flag =serverAdminService.deleteByIds(idList);
		}
        return RespUtils.build(RespCode.DEL_SUCCESS,flag);
	}
	
	
	/**
	 * 修改和更新资产-服务器
	 * @param reqObj
	 * @return
	 */
	@PostMapping(value = "/property/addAndModify")
	@ApiOperation(value = "更新")
	@ResponseBody
    public RespObject<?> modify(@RequestBody @Valid ReqPropertyUpdate reqObj) {
		//PropertyDTO mt = reqObjToDTO(reqObj);
		PropertyDTO mt = dozer.convertor(reqObj, PropertyDTO.class);
		Long id=reqObj.getId();
		int flg =0;
		if (id==null||StringUtils.isBlank(id+"")) {//id为空为新增
			mt.setId(iworkService.getNextId());
			//随机生成编码：种类+时间+一位随机小数
			mt.setCode("YJ"+DateUtils.getDate("yyyyMMddHHmmss")+(int)(Math.random()*11));
			//维护时间插入当前时间，每次状态变更则改变维护时间，系统更改
			mt.setFixTime(DateUtils.parseDate(DateUtils.getDate2()));
			//插入硬件基本信息表
			flg = propertyService.save(Arrays.asList(mt));
		}else {//id有值为修改
			PropertyVOId oldMap=propertyService.findEntityByKey(reqObj.getId());
			if(oldMap==null||oldMap.getId().equals("")) {
				throw new RuntimeException("这条信息不存在，请刷新页面！");
			}
			String state=mt.getState().toString();
			String stateOld=oldMap.getState();
			Date fixTime=oldMap.getFixTime();
			//维护时间插入当前时间，每次状态变更则改变维护时间，系统更改
			if (!state.equals(stateOld)) {
				fixTime=DateUtils.parseDate(DateUtils.getDate2());
			}
			mt.setFixTime(fixTime);
			flg = propertyService.modifyEntityByKey(mt);
		}
		
		//根据人员判断是否进入硬件监控管理表
		String userName=reqObj.getUserName();
		if (!StringUtils.isBlank(userName)) {
			//获取用户
			List<Map<String, Object>> user =monitorISpUserService.selectUserInfo(userName);
			String isMonitor=reqObj.getIsMonitor();
			if (!StringUtils.isBlank(isMonitor)&&user.size()==1) {
				SpDeviceAdmin spDeviceAdmin=new SpDeviceAdmin();
				spDeviceAdmin.setUsername(userName);
				spDeviceAdmin.setMobile(user.get(0).get("phone").toString());
				spDeviceAdmin.setEmail(user.get(0).get("email").toString());
				spDeviceAdmin.setStatus(isMonitor);
				spDeviceAdmin.setCreatedTime(DateUtils.getDate2());
				spDeviceAdmin.setIp(mt.getIp());
				spDeviceAdmin.setServerCode(mt.getCode());
				spDeviceAdmin.setServerName(mt.getName());
				spDeviceAdmin.setServerType(mt.getType());
				spDeviceAdmin.setId(mt.getId()+"");
				//根据id判断保存或者修改信息
				if (id==null||StringUtils.isBlank(id+"")) {
					flg=spDeviceAdminService.save(Arrays.asList(spDeviceAdmin));
				}else {
					flg=spDeviceAdminService.modifyEntityByKey(spDeviceAdmin);
				}
			}
		}
		RespCode respCode=RespCode.SUCCESS;
		if (flg <= 0) {
			respCode=RespCode.DATABASE_ERROR;
			return RespUtils.build(respCode.getCode(),respCode.getMessage(),null);
		}
		JSONObject js=new JSONObject();
		js.put("id", mt.getId()+"");
		js.put("code", mt.getCode());
        return RespUtils.build(respCode.getCode(),respCode.getMessage(),js);
	}
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/property/delById")
	@ApiOperation(value = "根据id删除")
	@ResponseBody
    public RespObject<?> delProperty(@RequestBody PropertyVOId id ) {
		int flag =1;
		if (id.getId()!=null&&id.getId()!="") {
			String[] ids=id.getId().split(",");
			List<String> idList = new ArrayList<String>();
			Collections.addAll(idList, ids);
			flag =propertyService.deleteByIds(idList);
		}
        return RespUtils.build(RespCode.DEL_SUCCESS,flag);
	}
	
	/**
	 * 设置通知规则
	 * 
	 * @param task
	 * @return
	 */
//	@PostMapping(value = "/spParamAlert/setAlertNotifyRule")
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
	@PostMapping(value = "/spParamAlert/setAlertNotifyRule")
	@ApiOperation(value = "设置通知规则")
	@ResponseBody
	public Result<UnicomResponseEnums> setAlertNotifyRule(@RequestBody SpParamAlert spParamAlert) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
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
		//排班人员修改（当天之后的排班有效）
		List<Map<String, Object>> shiftId=(List<Map<String, Object>>) spParamAlert.getShiftId();
		if (contactsMethod.equals("range")) {//如果选择排班则显示排班，选择自定义则用自定义人员
			if (shiftId.size()>0) {
				//首先更新排班表（根据ip和日期）
				
				//更新所有ip相关的预警规则
				
			}
		}
		
		
		//暂停监控时间
		if (StringUtils.isBlank(spParamAlert.getStopListenStart())) {
			spParamAlert.setStopListenStart(null);
		}else {
			spParamAlert.setStopListenStart("'"+spParamAlert.getStopListenStart()+"'");
		}
		if (StringUtils.isBlank(spParamAlert.getStopListenEnd())) {
			spParamAlert.setStopListenEnd(null);
		}else {
			spParamAlert.setStopListenEnd("'"+spParamAlert.getStopListenEnd()+"'");
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
	 * 新增或者修改告警规则设置
	 * 
	 * @param task
	 */
	@PostMapping(value = "/spParamAlert/addAndModify")
	@ApiOperation(value = "新增或者修改告警规则设置")
	@ResponseBody
	public Result<UnicomResponseEnums> addAndModify(@RequestBody @Valid SpParamAlert spParamAlert) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		String id=spParamAlert.getId();
		int flg =0;
		if (id==null||StringUtils.isBlank(id)) {
			spParamAlert.setId(iworkService.getNextId()+"");
			spParamAlert.setCreatedTime(DateUtils.getDate2());
			flg = spParamAlertService.add(spParamAlert);
		}else {
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
	@PostMapping(value = "/spParamAlert/del")
	@ApiOperation(value = "删除任务告警规则设置")
	@ResponseBody
	public Result<UnicomResponseEnums> delSpParamAlert(@RequestBody SpParamAlert spParamAlert) {
		boolean success = true;
		UnicomResponseEnums msg = UnicomResponseEnums.DEL_SUCCESS;
		int flag =1;
		if (spParamAlert.getId()!=null&&spParamAlert.getId()!="") {
			String[] ids=spParamAlert.getId().split(",");
			List<String> idList = new ArrayList<String>();
			Collections.addAll(idList, ids);
			flag =spParamAlertService.deleteByIds(idList);
		}
		return new Result(success, flag, msg);
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
	@Transactional(rollbackFor = Exception.class) //事务回滚
	public String shiftExport(@RequestBody JSONArray list) throws CustomException, ParseException {
		long start = System.currentTimeMillis();
		UnicomResponseEnums msg=UnicomResponseEnums.SUCCESS_OPTION;
		boolean success=true;
		JSONObject date=new JSONObject();
		//Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();//回滚点
		//try {
            if (list.size()>0) {
            	SpUserShift spUserShift=new SpUserShift();
            	spUserShift.setStatus("0");
            	spParamAlertService.updateShiftUser(spUserShift);//更新历史信息状态为历史
                for (int i = 0; i < list.size(); i++) {
                	JSONObject objects=list.getJSONObject(i);
        			String userCode=objects.get("name").toString();
        			String startIp=objects.get("startIp").toString();
        			String endIp=objects.get("endIp").toString();
        			String startTime=DateUtils.formatDate(DateUtils.parseDate(objects.get("startTime").toString()), "yyyy-MM-dd HH:mm:ss");
        			String endTime=DateUtils.formatDate(DateUtils.parseDate(objects.get("endTime").toString()), "yyyy-MM-dd HH:mm:ss");
        			String status=objects.get("pause").toString();
        			spUserShift.setStatus(status);//状态
        			spUserShift.setUserCode(userCode);//用户编码
        			String nextTime=startTime;
        			if (DateUtils.compareDate(endTime,nextTime)) {//前面的日期大于后面的日期
        				while (true) {//根据时间循环加入排班,首先执行一次保存
            				spUserShift.setShiftTime(startTime);//排班时间
            				String ip=startIp.substring(0,startIp.lastIndexOf("."));
            				int ipNumStart=Integer.parseInt(startIp.substring(startIp.lastIndexOf(".")+1));
            				int ipNumStartEnd=Integer.parseInt(endIp.substring(endIp.lastIndexOf(".")+1));
            				if (ipNumStartEnd>=ipNumStart) {
            					while(ipNumStartEnd>=ipNumStart) {
            						spUserShift.setId(iworkService.getNextId());//id
                    				spUserShift.setUpdateTime(DateUtils.getDate2());//更新时间
            						spUserShift.setIp(ip+"."+ipNumStart);//ip
                					spParamAlertService.saveShiftUser(spUserShift);
                					ipNumStart++;
                				}
							}else {//ip大小错误
								//TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);//接收到异常就回滚
								throw new CustomException("0000","第"+(i+1)+"行，结束ip小于开始日期ip，请检查！");
							}
            				
            				nextTime=DateUtils.addDateTime(startTime, 0, 0, 1);//开始时间+1天
            				if (DateUtils.compareDate(endTime,nextTime)) {//下一天大于结束时间就结束循环
								break;
							}
        				}
					}else {
						//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						//TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);//接收到异常就回滚
						throw new CustomException("0000","第"+(i+1)+"行，结束日期小于开始日期，请检查！");
					}
        			
        			//System.err.println(userCode+"==========="+ip+"========="+DateUtils.formatDate(startDate,"yyyy-MM-dd HH:mm:ss")+"==========="+endDate);
        		}
			}else {//文件内容为空
				//TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);//接收到异常就回滚
				throw new CustomException(UnicomResponseEnums.NULL_FILE);
			}
            
            long end = System.currentTimeMillis();
    	    System.out.println((end-start)/1000+"秒");
    		Result result=new Result(success,date,msg);
    		return result.toString();
        //} catch (IOException e) {
        	//TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);//接收到异常就回滚
    	   // System.err.println(e.toString());
    	   // throw new CustomException(UnicomResponseEnums.UPLOAD_FILE_ERR);
        //}catch (Exception e) {
			// TODO: handle exception
        	//TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);//接收到异常就回滚
        	//System.err.println(e.toString());
        	//throw new CustomException(UnicomResponseEnums.METHOD_NOT_ALLOWED);
		//}
		
	}
	
	
}
