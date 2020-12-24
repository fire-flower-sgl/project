package com.mhtech.platform.msrv.auth.service.dubbo;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.auth.ApplicationContextHolder;
import com.mhtech.platform.msrv.auth.service.AcceccTimeService;



@Service("acceccTimeService")
public class AccessTimeServiceImpl implements AcceccTimeService {

	@Override
	public Map<String, Long> acceccTimeMap() {
		return ApplicationContextHolder.ACCESS_TIME;
	}

	@Override
	public void saveAccessTime(Map<String, Long> acceccTimeMap) {
		ApplicationContextHolder.ACCESS_TIME.clear();
		ApplicationContextHolder.ACCESS_TIME.putAll(acceccTimeMap);
	}



}
