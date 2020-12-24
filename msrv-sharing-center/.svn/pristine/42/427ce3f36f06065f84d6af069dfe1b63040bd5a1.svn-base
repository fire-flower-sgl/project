package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.msrv.pojo.DictTypeSearchParam;
import com.mhtech.platform.msrv.pojo.DictVO;
import com.mhtech.platform.msrv.sharing.dao.model.DictType;

@Mapper
public interface DictTypeMapper {
    int deleteByPrimaryKey(Integer code);

    int insert(DictType record);

    int insertSelective(DictType record);

    DictType selectByPrimaryKey(Integer code);

    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);
    
    /**
     * 字典类型列表
     * @param param
     * @return
     */
    List<DictType> listDictType(DictTypeSearchParam param);
    
    /**
     * 字典值列表
     * @param type
     * @return
     */
    List<DictVO> listTypeValue(String type);
}