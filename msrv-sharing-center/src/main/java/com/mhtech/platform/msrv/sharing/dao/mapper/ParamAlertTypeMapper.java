package com.mhtech.platform.msrv.sharing.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.msrv.sharing.dao.model.ParamAlertType;

@Mapper
public interface ParamAlertTypeMapper extends GenericMapper {
	
    int insert(ParamAlertType record);

    int insertSelective(ParamAlertType record);
    
    //查询typeCode是的存在
    boolean findAlertType(String typeCode);
    
    ParamAlertType getParamAlertType(String typeCode);
}