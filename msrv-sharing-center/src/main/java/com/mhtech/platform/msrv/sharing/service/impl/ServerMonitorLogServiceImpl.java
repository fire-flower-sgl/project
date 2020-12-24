package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.sharing.dao.mapper.ServerMonitorLogMapper;
import com.mhtech.platform.msrv.sharing.dao.model.ServerMonitorLog;
import com.mhtech.platform.msrv.sharing.service.ServerMonitorLogService;

@Service
public class ServerMonitorLogServiceImpl extends BaseServiceImpl implements ServerMonitorLogService{
	
	@Autowired
	private ServerMonitorLogMapper smlMapper;

	@PostConstruct
	@Override
	protected void setGenericMapper() {
		super.genericMapper = smlMapper;
	}

	@Override
	public List<ServerMonitorLog> allLog(Long max, Long min, int eachItem) {
		// TODO Auto-generated method stub
		return smlMapper.allLog(max, min, eachItem);
	}

	@Override
	public Map<String, Object> selectManMin(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return smlMapper.selectManMin(topTime, endTime);
	}

	@Override
	public int deleteDate(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return smlMapper.deleteDate(topTime, endTime);
	}

}
