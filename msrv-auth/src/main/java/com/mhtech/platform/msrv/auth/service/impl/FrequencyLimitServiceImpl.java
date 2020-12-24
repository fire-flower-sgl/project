package com.mhtech.platform.msrv.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.auth.dao.mapper.FrequencyLimitMapper;
import com.mhtech.platform.msrv.auth.dao.model.FrequencyLimit;
import com.mhtech.platform.msrv.auth.service.FrequencyLimitService;

@Service
public class FrequencyLimitServiceImpl implements FrequencyLimitService {
	
	@Autowired
	private FrequencyLimitMapper frequencyLimitMapper;

	@Override
	public List<FrequencyLimit> queryAll() {
		return frequencyLimitMapper.queryAll();
	}

	
	
}
