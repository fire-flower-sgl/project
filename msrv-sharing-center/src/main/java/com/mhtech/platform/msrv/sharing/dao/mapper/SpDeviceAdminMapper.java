package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mhtech.platform.msrv.pojo.SpDeviceAdmin;


@Mapper
public interface SpDeviceAdminMapper extends GenericMapper {
	/**
	 * 根据服务id查找预警指标
	 * @param serverId
	 * @return 
	 */
	List<Map<String, Object>> findParameterByParmType(String parmType);

	int deleteById(String id);
	
	SpDeviceAdmin findDeviceAdminByIp(String ip);
}