package com.mhtech.platform.msrv.auth.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.msrv.auth.dao.model.SpUser;


@Mapper
public interface SpUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpUser record);

    int insertSelective(SpUser record);

    SpUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpUser record);

    int updateByPrimaryKey(SpUser record);
    
    SpUser select(@Param("name") String name, @Param("password") String password);
    
    int sign(@Param("userCode") String userCode, @Param("password") String password);
    
    List<Map<String, Object>> selectAllUser(@Param("name")String name);

	/**
	 * TODO
	 * @param userCode
	 * @return
	 */
	List<Map<String, Object>> selectUserInfo(String userCode);
    
}