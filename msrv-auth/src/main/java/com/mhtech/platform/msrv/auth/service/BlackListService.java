package com.mhtech.platform.msrv.auth.service;

import java.util.List;

import com.mhtech.platform.msrv.auth.dao.model.SysBlackList;
import com.mhtech.platform.msrv.auth.exception.BizException;

public interface BlackListService {
	
	/**
	 * 	查询所有黑名单
	 */
	List<SysBlackList> getBlackIpsList() throws BizException;
	
	int updateById(SysBlackList sysBlackList);

}
