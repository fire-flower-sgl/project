package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.sharing.dao.mapper.MonitorPlansMapper;
import com.mhtech.platform.msrv.sharing.dao.model.MonitorPlans;
import com.mhtech.platform.msrv.sharing.service.MonitorPlansService;

@Service
public class MonitorPlansServicesImpl implements MonitorPlansService {

	@Autowired
	private MonitorPlansMapper monitorPlansMapper;

	@Override
	public List<MonitorPlans> dayLog(int day) {
		// TODO Auto-generated method stub
		return monitorPlansMapper.dayLog(day);
	}

	@Override
	public int delete(int day) {
		// TODO Auto-generated method stub
		return monitorPlansMapper.delete(day);
	}

	@Override
	public List<MonitorPlans> allLog(Long max, Long min, int eachItem) {
		// TODO Auto-generated method stub
		return monitorPlansMapper.allLog(max, min, eachItem);
	}

	@Override
	public Map<String, Object> selectManMin(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return monitorPlansMapper.selectManMin(topTime, endTime);
	}

	@Override
	public int deleteDate(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return monitorPlansMapper.deleteDate(topTime, endTime);
	}

}
