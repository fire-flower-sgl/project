package com.mhtech.platform.msrv.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.auth.dao.mapper.SysAccessCountMapper;
import com.mhtech.platform.msrv.auth.dao.model.SysAccessCount;
import com.mhtech.platform.msrv.auth.service.AccessCountService;

@Service
public class AccessCountServiceImpl implements AccessCountService {

	@Autowired
	private SysAccessCountMapper sysAccessCountMapper;
	
	@Override
	public SysAccessCount findByIp(String ip) {
		return sysAccessCountMapper.selectByIp(ip);
	}
	
	@Override
	public int save(SysAccessCount sysAccessCount) {
		return sysAccessCountMapper.insertNew(sysAccessCount);
	}
		
	@Override
	public int updateByid(SysAccessCount sysAccessCount) {
		return sysAccessCountMapper.updateByid(sysAccessCount);
	}
}
