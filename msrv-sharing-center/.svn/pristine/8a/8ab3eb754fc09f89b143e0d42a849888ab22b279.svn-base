package com.mhtech.platform.msrv.sharing.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.monitor.service.SpDeviceAdminService;
import com.mhtech.platform.msrv.pojo.SpDeviceAdmin;
import com.mhtech.platform.msrv.sharing.dao.mapper.SpDeviceAdminMapper;

@Service("spDeviceAdminService")
public class SpDeviceAdminServiceImpl extends BaseServiceImpl implements SpDeviceAdminService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SpDeviceAdminMapper spDeviceAdminMapper;

	@PostConstruct
	@Override
	protected void setGenericMapper() {
		super.genericMapper = spDeviceAdminMapper;
	}

	@Override
	public SpDeviceAdmin findDeviceAdminByIp(String ip) {
		SpDeviceAdmin spDeviceAdmin = null;
		try {
			spDeviceAdmin = spDeviceAdminMapper.findDeviceAdminByIp(ip);
		} catch (Exception e) {
			logger.error("error when find device admin by ip", e);
		}
		return spDeviceAdmin;
	}
}
