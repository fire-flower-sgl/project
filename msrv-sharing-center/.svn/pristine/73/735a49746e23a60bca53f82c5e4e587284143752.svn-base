package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.IMonitorPlansService;
import com.mhtech.platform.msrv.pojo.MonitorPlansDTO;
import com.mhtech.platform.msrv.pojo.MonitorPlansVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.Task;
import com.mhtech.platform.msrv.sharing.dao.mapper.MonitorPlansMapper;
import com.mhtech.platform.msrv.sharing.dao.model.MonitorPlans;
import com.mhtech.platform.msrv.sharing.task.manager.ScheduleManager;

@Service("monitorPlansService")
public class MonitorPlansServiceImpl implements IMonitorPlansService{
	
	@Autowired
	private MonitorPlansMapper monitorPlansMapper;
	@Autowired
	IworkService iwork;
	@Autowired
	ScheduleManager scheduleConfig;
	
	private final Logger logger = LoggerFactory.getLogger(MonitorPlansServiceImpl.class);

	@Override
	public List<MonitorPlansVO> getServerAlertRuleByParam(MonitorPlansDTO mp) {
		List<MonitorPlans> mpList = monitorPlansMapper.selectEntities(mp);
		return mpList
				.stream()
				.map(mPlan -> {
					return new MonitorPlansVO(mPlan.getId(), mPlan.getTaskName(), 
							mPlan.getCron(), mPlan.getMainClass(), 
							mPlan.getMethod(), mPlan.getStatus(), 
							mPlan.getParams(), mPlan.getMemo(), 
							mPlan.getCreatedTime());
				}).collect(Collectors.toList());
	}

	@Override
	public Page queryPage(MonitorPlansDTO mp) {
		PageHelper.startPage(mp.getPageNo(),mp.getPageSize());
		List<MonitorPlans> mpList = monitorPlansMapper.selectEntities(mp);
		PageInfo<MonitorPlans> pageinfo = new PageInfo<MonitorPlans>(mpList);
		
		List<MonitorPlansVO> listVO = new ArrayList<MonitorPlansVO>();
		
		Map<Object, ScheduledTask> task = scheduleConfig.getTask();
		
		List<MonitorPlans> list = pageinfo.getList();
		for (MonitorPlans mPlan : list) {
			MonitorPlansVO monitorPlansVO = new MonitorPlansVO(mPlan.getId(), mPlan.getTaskName(), 
					mPlan.getCron(), mPlan.getMainClass(), 
					mPlan.getMethod(), mPlan.getStatus(), 
					mPlan.getParams(), mPlan.getMemo(), 
					mPlan.getCreatedTime());
			Long taskId = mPlan.getId();
			if (task.get(taskId) != null) {
				monitorPlansVO.setIsStart(1);
			} else {
				monitorPlansVO.setIsStart(0);
			}
			
			listVO.add(monitorPlansVO);
		}
		
		Page page= new Page(pageinfo.getPageSize(),Integer.parseInt(String.valueOf(pageinfo.getTotal())),pageinfo.getStartRow(),mp.getPageNo(),pageinfo.getPages(),listVO);
		return page;
	}

	@Override
	public int addMonitorPlan(MonitorPlansDTO mpDto) {
		mpDto.setId(iwork.getNextId());
		return monitorPlansMapper.insert(mpDto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateMonitorPlan(MonitorPlansDTO mpDto) {
		int flag = monitorPlansMapper.updateByPrimaryKey(mpDto);
		try {
			if(flag>0) {
				Task task = new Task();
				task.setTaskId(mpDto.getId().toString());
				task.setTaskCron(mpDto.getCron());
				task.setBeanName(mpDto.getMainClass());
				task.setMethodName(mpDto.getMethod());
				Map parseObject = JSONObject.parseObject(mpDto.getParams(), Map.class);
				task.setParameters(parseObject);
				scheduleConfig.modifyTask(task);
			}
		} catch (Exception e) {
			logger.error("任务修改失败,任务id"+mpDto.getId());
			throw new RuntimeException("任务修改失败!!!");
		}
		return flag;
	}

	@Override
	public int delMonitorPlan(Long id) {
		int flag = monitorPlansMapper.deleteByPrimaryKey(id);
		if(flag>0)
		scheduleConfig.cancelTask(id.toString());
		return flag;
	}

	@Override
	public void startTask(MonitorPlansDTO mpDto) {
		Task task = new Task();
		task.setTaskId(mpDto.getId().toString());
		task.setTaskCron(mpDto.getCron());
		task.setBeanName(mpDto.getMainClass());
		task.setMethodName(mpDto.getMethod());
		Map parseObject = JSONObject.parseObject(mpDto.getParams(), Map.class);
		task.setParameters(parseObject);
		scheduleConfig.addTask(task);
	}

	@Override
	public void stopTask(Long id) {
		scheduleConfig.cancelTask(id.toString());
	}

	@Override
	public void runOneTime(MonitorPlansDTO mpDto) {
		Task task = new Task();
		task.setBeanName(mpDto.getMainClass());
		task.setMethodName(mpDto.getMethod());
		Map parseObject = JSONObject.parseObject(mpDto.getParams(), Map.class);
		task.setParameters(parseObject);
		scheduleConfig.ExecutedImmediately(task);
		
	}

	@Override
	public boolean isExist(MonitorPlansDTO mp) {
		return monitorPlansMapper.isExist(mp)>0;
	}

}
