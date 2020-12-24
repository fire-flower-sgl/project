package com.mhtech.platform.msrv.auth.service;

import java.util.Map;

public interface AcceccTimeService {

	Map<String,Long> acceccTimeMap();

	void saveAccessTime(Map<String, Long> acceccTimeMap);
}
