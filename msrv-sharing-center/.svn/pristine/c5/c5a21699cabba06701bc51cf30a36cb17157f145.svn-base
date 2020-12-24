package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 通用（泛型）CRUD
 * @author GM
 */
public interface GenericMapper {

	int deleteByPrimaryKey(@Param("keys") long... keys);

	<T> int insert(T entity);
	
	<T> int batchInsert(List<T> entities);

	<T> int insertSelective(T entity);

	<T> T selectByPrimaryKey(long id);

	<T> int updateByPrimaryKeySelective(T entity);

	<T> int updateByPrimaryKey(T entity);
	
	<T, P> List<T> selectEntities(P param);
}
