package com.mhtech.platform.msrv.sharing.service;

import java.util.List;

import com.github.pagehelper.IPage;

public interface IBaseService {

	int remove(long... keys);

	<T> int save(List<T> entities);

	<T> T findEntityByKey(long id);

	<T> int modifyEntityByKey(T entity);
	
	/**
	 * 分页查询
	 * @param param 条件
	 * @return list
	 */
	<T, P extends IPage> List<T> listPageable(P param);
}
