package com.mhtech.platform.msrv.auth.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.msrv.auth.dao.model.SysAccessCount;
@Mapper
public interface SysAccessCountMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysAccessCount record);

    int insertSelective(SysAccessCount record);

    SysAccessCount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysAccessCount record);

    int updateByPrimaryKey(SysAccessCount record);
    
    SysAccessCount selectByIp(String ip);
    
    int insertNew(SysAccessCount record);
    
    int updateByid(SysAccessCount record);
    
}