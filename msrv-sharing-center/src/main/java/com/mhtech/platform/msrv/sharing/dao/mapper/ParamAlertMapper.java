package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.log.pojo.ParamAlertDTO;
import com.mhtech.platform.msrv.sharing.dao.model.ParamAlert;

@Mapper
public interface ParamAlertMapper extends GenericMapper {
	/**
	 * 根据服务id查找预警指标
	 * @param serverId
	 * @return 
	 */
	List<ParamAlert> selectByPrimaryServerId(Long serverId);
	
	//依据条件查询+分页
	List<ParamAlert> selectParamAlert(ParamAlertDTO dto);
	//总条数
	int selectSum(ParamAlertDTO dto);

	
	
}