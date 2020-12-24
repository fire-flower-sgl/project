package com.mhtech.platform.msrv.auth.service;

import java.util.List;

import com.mhtech.platform.msrv.auth.dao.model.FrequencyLimit;

public interface FrequencyLimitService {
	
	 List<FrequencyLimit> queryAll();

}
