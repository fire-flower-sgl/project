package com.mhtech.platform.msrv.sharing.service.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.sharing.config.IdWorker;

@Service("iworkService")
public class IworkServiceImpl implements IworkService{

	@Autowired
	IdWorker IdWorker;
	
	@Override
	public Long getNextId() {
		return IdWorker.nextId();
		
	}

}
