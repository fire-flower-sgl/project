package com.mhtech.platform.msrv.monitor.service;

import java.util.List;

public interface IBaseService {

	int remove(long... keys);

	<T> int save(List<T> entities);

	<T> T findEntityByKey(long id);

	<T> int modifyEntityByKey(T entity);

}
