package com.mhtech.platform.msrv.auth.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.msrv.auth.dao.model.SysBlackList;
@Mapper
public interface SysBlackListMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysBlackList record);

    int insertSelective(SysBlackList record);

    SysBlackList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysBlackList record);

    int updateByPrimaryKey(SysBlackList record);
    
    List<SysBlackList> queryAll();
}