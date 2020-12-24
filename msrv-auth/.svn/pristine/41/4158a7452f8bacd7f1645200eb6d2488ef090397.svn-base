package com.mhtech.platform.msrv.auth.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.auth.dao.mapper.SpUserMapper;
import com.mhtech.platform.msrv.auth.dao.model.SpUser;
import com.mhtech.platform.msrv.auth.service.SpUserService;


@Service
public class SpUserServiceImpl implements SpUserService {
	
	@Autowired
	private SpUserMapper  spUserMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return spUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SpUser record) {
		// TODO Auto-generated method stub
		return spUserMapper.insert(record);
	}

	@Override
	public int insertSelective(SpUser record) {
		// TODO Auto-generated method stub
		return spUserMapper.insertSelective(record);
	}

	@Override
	public SpUser selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return spUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SpUser record) {
		// TODO Auto-generated method stub
		return spUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(SpUser record) {
		// TODO Auto-generated method stub
		return spUserMapper.updateByPrimaryKeySelective(record);
	}

	

}
