package com.mhtech.platform.msrv.auth.dao.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.mhtech.platform.msrv.auth.dao.model.SysSpePower;

@Mapper
public interface SysSpePowerMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysSpePower record);

    int insertSelective(SysSpePower record);

    SysSpePower selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysSpePower record);

    int updateByPrimaryKey(SysSpePower record);
    
    //依据userCode,speNum查询
    SysSpePower selectSpePower (SysSpePower spePower);
}