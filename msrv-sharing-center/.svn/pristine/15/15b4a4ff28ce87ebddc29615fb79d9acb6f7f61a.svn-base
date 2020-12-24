package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
public interface SysParameterMapper extends GenericMapper {
	/**
	 * 根据服务id查找预警指标
	 * @param serverId
	 * @return 
	 */
	List<Map<String, Object>> findParameterByParmType(String parmType);

	List<Map<String, Object>> findParameterByParmCodeOrParmName(@Param("parmType") String parmType, @Param("parmCode") String parmCode,@Param("parmName") String parmName);
	
}