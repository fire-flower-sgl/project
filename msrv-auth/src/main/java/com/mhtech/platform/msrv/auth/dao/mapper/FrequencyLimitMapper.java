package com.mhtech.platform.msrv.auth.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.msrv.auth.dao.model.FrequencyLimit;
@Mapper
public interface FrequencyLimitMapper {
    int deleteByPrimaryKey(String id);

    int insert(FrequencyLimit record);

    int insertSelective(FrequencyLimit record);

    FrequencyLimit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FrequencyLimit record);

    int updateByPrimaryKey(FrequencyLimit record);
    
    List<FrequencyLimit> queryAll();
}