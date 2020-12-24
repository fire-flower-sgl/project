package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.msrv.sharing.dao.model.SpPersonne;
@Mapper
public interface SpPersonneMapper extends GenericMapper{
    
    //查询人员列表
    List<SpPersonne> listSpPersonne();
    
    //查询总条数
    int selectSum();
    
    int insertSelective(SpPersonne spPersonne);
  
    /**
     * 查询人员列表
     * @param codes
     * @return
     */
    List<SpPersonne> listByCodes(@Param("codes") List<String> codes);
}