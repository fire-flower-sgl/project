package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.msrv.pojo.DictVO;
import com.mhtech.platform.msrv.sharing.dao.model.Dict;

@Mapper
public interface DictMapper {
    int insert(Dict record);

    int insertSelective(Dict record);
    
    List<DictVO> listDictByTypes(List<String> types);
}