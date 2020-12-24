package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.sharing.dao.mapper.AlertLogMapper;
import com.mhtech.platform.msrv.sharing.dao.model.AlertLog;
import com.mhtech.platform.msrv.sharing.service.AlertLogService;

/**
 * 日志sp_alert_log 实现类
 * 
 * @author _mjl
 *
 */
@Service
public class AlertLogServiceSImpl implements AlertLogService {
	@Autowired
	private AlertLogMapper alertLogMapper;



	@Override
	public int delete(int day) {
		// TODO Auto-generated method stub
		return alertLogMapper.delete(day);
	}



	@Override
	public List<AlertLog> allLog(Long max, Long min, int eachItem) {
		return alertLogMapper.allLog(max, min, eachItem);
	}


	@Override
	public Map<String, Object> selectManMin(String topTime, String endTime) {
		return alertLogMapper.selectManMin(topTime, endTime);
	}



	@Override
	public int deleteDate(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return alertLogMapper.deleteDate(topTime, endTime);
	}








}
