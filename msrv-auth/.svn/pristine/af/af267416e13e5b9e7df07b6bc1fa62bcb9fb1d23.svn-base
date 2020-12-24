package com.mhtech.platform.msrv.auth.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.auth.dao.mapper.SysBlackListMapper;
import com.mhtech.platform.msrv.auth.dao.model.SysBlackList;
import com.mhtech.platform.msrv.auth.exception.BizException;
import com.mhtech.platform.msrv.auth.service.BlackListService;


@Service
public class BlackListServiceImpl implements BlackListService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysBlackListMapper sysBlackListMapper;

	@Override
	public List<SysBlackList> getBlackIpsList() throws BizException {
		return sysBlackListMapper.queryAll();
	}

	@Override
	public int updateById(SysBlackList sysBlackList) {
		return sysBlackListMapper.updateByPrimaryKeySelective(sysBlackList);
	}

}