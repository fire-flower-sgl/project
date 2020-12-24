package com.mhtech.platform.msrv.gateway.controller.monitor;

import static com.mhtech.platform.msrv.gateway.response.RespCode.SUCCESS;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.log.pojo.ServerChkRuleDTO;
import com.mhtech.platform.msrv.gateway.request.ReqDelServerChkRule;
import com.mhtech.platform.msrv.gateway.request.ReqMonitorPlanAdd;
import com.mhtech.platform.msrv.gateway.request.ReqMonitorPlanCheckTaskName;
import com.mhtech.platform.msrv.gateway.request.ReqMonitorPlanDel;
import com.mhtech.platform.msrv.gateway.request.ReqMonitorPlanPageInfo;
import com.mhtech.platform.msrv.gateway.request.ReqMonitorPlanRunTask;
import com.mhtech.platform.msrv.gateway.request.ReqMonitorPlanStartTask;
import com.mhtech.platform.msrv.gateway.request.ReqMonitorPlanUpdate;
import com.mhtech.platform.msrv.gateway.request.ReqServerChkRule;
import com.mhtech.platform.msrv.gateway.request.ReqUpdateServerChkRule;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IMonitorPlansService;
import com.mhtech.platform.msrv.pojo.MonitorPlansDTO;
import com.mhtech.platform.msrv.pojo.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description 任务控制层
 * @Author admin
 * @Date 2020/2/26 10:10
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/monitor/MonitorPlans")
@Api(value = "MonitorPlansController", tags = "任务模块")
public class MonitorPlansController {
			
	@Autowired
	private IMonitorPlansService monitorPlans;
	
	/**
	 * 分页查询
	 * @param reqMP
	 * @return
	 */
	@PostMapping(value = "/queryPage")
	@ApiOperation(value = "分页查询")
    public RespObject<?> queryPage(@RequestBody @Valid ReqMonitorPlanPageInfo reqMP) {
		MonitorPlansDTO mp = new MonitorPlansDTO();
		mp.setPageNo(reqMP.getPageNo());
		mp.setPageSize(reqMP.getPageSize());
		mp.setTaskName(reqMP.getTaskName());
		mp.setStatus(reqMP.getStatus());
		Page page = monitorPlans.queryPage(mp);
        return RespUtils.buildOKWithData(page);
	}
	
	/**
	 * 新增
	 * @param reqMP
	 * @return
	 */
	@PostMapping(value = "/add")
	@ApiOperation(value = "新增")
    public RespObject<?> add(@RequestBody @Valid ReqMonitorPlanAdd reqMP) {
		MonitorPlansDTO mpDto = reqObjToDTO(reqMP);
		boolean flag = monitorPlans.isExist(mpDto);
		if(flag) {
			throw new RuntimeException("任务方法名称重复");
		}
		int add = monitorPlans.addMonitorPlan(mpDto);
        //return RespUtils.buildOKWithData(add);
		return RespUtils.build(RespCode.ADD_SUCCESS,add);
	}
	
	/**
	 * 更新
	 * @param reqMP
	 * @return
	 */
	@PostMapping(value = "/modify")
	@ApiOperation(value = "根据id更新")
    public RespObject<?> modify(@RequestBody @Valid ReqMonitorPlanUpdate reqMP) {
		MonitorPlansDTO mpDto = reqObjToDTO(reqMP);
		boolean flag = monitorPlans.isExist(mpDto);
		if(flag) {
			throw new RuntimeException("任务方法名称重复");
		}
		int modify = monitorPlans.updateMonitorPlan(mpDto);
        //return RespUtils.buildOKWithData(modify);
		return RespUtils.build(RespCode.UPDATE_SUCCESS,modify);
	}
	
	/**
	 * 删除
	 * @param reqMP
	 * @return
	 */
	@PostMapping(value = "/del")
	@ApiOperation(value = "根据id删除")
    public RespObject<?> del(@RequestBody @Valid ReqMonitorPlanDel reqMP) {
		int flag = monitorPlans.delMonitorPlan(reqMP.getId());
        return RespUtils.buildOKWithData(flag);
	}
	
	/**
	 * 开始任务
	 * @param reqMP
	 * @return
	 */
	@PostMapping(value = "/startTask")
	@ApiOperation(value = "开启任务")
    public RespObject<?> startTask(@RequestBody @Valid ReqMonitorPlanStartTask reqMP) {
		MonitorPlansDTO mpDto = reqObjToDTO(reqMP);
		monitorPlans.startTask(mpDto);
        return RespUtils.OK;
	}
	
	/**
	 * 停止任务
	 * @param reqMP
	 * @return
	 */
	@PostMapping(value = "/stopTask")
	@ApiOperation(value = "停止任务")
    public RespObject<?> stopTask(@RequestBody @Valid ReqMonitorPlanDel reqMP) {
		monitorPlans.stopTask(reqMP.getId());
		return RespUtils.OK;
	}
	
	/**
	 * 执行一次
	 * @param reqMP
	 * @return
	 */
	@PostMapping(value = "/runOneTime")
	@ApiOperation(value = "执行一次")
    public RespObject<?> action(@RequestBody @Valid ReqMonitorPlanRunTask reqMP) {
		MonitorPlansDTO mpDto = reqObjToDTO(reqMP);
		monitorPlans.runOneTime(mpDto);
        return RespUtils.OK;
	}
	
	
	/**
	 * 请求实体类转换成DTO实体
	 * @param rscr
	 * @return
	 */
	private MonitorPlansDTO reqObjToDTO(ReqMonitorPlanUpdate mp) {
		MonitorPlansDTO  mpdto = new MonitorPlansDTO(mp.getId(),mp.getTaskName(), mp.getCron(), 
				mp.getMainClass(), mp.getMethod(),	mp.getStatus(), mp.getParams(), mp.getMemo());
		return mpdto;
	}
	
	private MonitorPlansDTO reqObjToDTO(ReqMonitorPlanAdd mp) {
		MonitorPlansDTO  mpdto = new MonitorPlansDTO(mp.getTaskName(), mp.getCron(), 
				mp.getMainClass(), mp.getMethod(),	mp.getStatus(), mp.getParams(), mp.getMemo());
		return mpdto;
	}
	
	private MonitorPlansDTO reqObjToDTO(ReqMonitorPlanStartTask mp) {
		MonitorPlansDTO  mpdto = new MonitorPlansDTO(mp.getId(), mp.getCron(), mp.getMainClass(), mp.getMethod(), mp.getParams());
		return mpdto;
	}
	
	private MonitorPlansDTO reqObjToDTO(ReqMonitorPlanRunTask mp) {
		MonitorPlansDTO  mpdto = new MonitorPlansDTO(mp.getMainClass(), mp.getMethod(), mp.getParams());
		return mpdto;
	}

	
}
