package com.mhtech.platform.msrv.auth.service.dubbo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.auth.ApplicationContextHolder;
import com.mhtech.platform.msrv.auth.service.IBlackIpsService;



@Service("iblackIpsService")
public class BBlackIpsServiceImpl implements IBlackIpsService {


	@Override
	public List<String> blackIpsList() {
		return ApplicationContextHolder.BLACK_IPS_LIST;
	}

}
