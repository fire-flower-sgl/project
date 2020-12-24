package com.mhtech.platform.msrv.auth.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.auth.dao.mapper.SysBlackIpsMapper;
import com.mhtech.platform.msrv.auth.dao.model.SysBlackIps;
import com.mhtech.platform.msrv.auth.exception.BizException;
import com.mhtech.platform.msrv.auth.service.BlackIpsService;
import com.mhtech.platform.msrv.auth.service.IBlackIpsService;

@Service
public class BlackIpsServiceImpl implements BlackIpsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysBlackIpsMapper sysBlackIpsMapper;

	@Override
	public List<SysBlackIps> blackIPsList() throws BizException {
		return sysBlackIpsMapper.queryAll();
	}

}
