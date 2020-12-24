package com.mhtech.platform.msrv.auth.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.msrv.auth.dao.model.SysBlackIps;
@Mapper
public interface SysBlackIpsMapper {
    int deleteByPrimaryKey(String ip);

    int insert(SysBlackIps record);

    int insertSelective(SysBlackIps record);

    SysBlackIps selectByPrimaryKey(String ip);

    int updateByPrimaryKeySelective(SysBlackIps record);

    int updateByPrimaryKey(SysBlackIps record);
    
    List<SysBlackIps> queryAll();
}