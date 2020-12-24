package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.List;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.mhtech.platform.msrv.sharing.dao.mapper.GenericMapper;
import com.mhtech.platform.msrv.sharing.service.IBaseService;

public abstract class BaseServiceImpl implements IBaseService {

	protected GenericMapper genericMapper;

	@Override
	public int remove(long... keys) {
		return genericMapper.deleteByPrimaryKey(keys);
	}

	@Override
	public <T> int save(List<T> entities) {
		return genericMapper.batchInsert(entities);
	}

	@Override
	public <T> T findEntityByKey(long id) {
		return genericMapper.selectByPrimaryKey(id);
	}

	@Override
	public <T> int modifyEntityByKey(T entity) {
		return genericMapper.updateByPrimaryKey(entity);
	}
	
	@Override
	public <T, P extends IPage> List<T> listPageable(P param) {
		PageHelper.startPage(param);
		return genericMapper.selectEntities(param);
	}
	
	/**
	 * 设置MAPPER实际处理对象
	 */
	protected abstract void setGenericMapper();
}
