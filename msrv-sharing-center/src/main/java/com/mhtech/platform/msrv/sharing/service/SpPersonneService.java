package com.mhtech.platform.msrv.sharing.service;

import java.util.List;

import com.mhtech.platform.log.pojo.SpPersonneDTO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.model.SpPersonne;

public interface SpPersonneService extends IBaseService{
	
	//查询人员列表
    List<SpPersonne> listSpPersonne();
    
   //依据条件查询
  	Page selectSpPersonne(SpPersonneDTO spPersonneDTO);

    int insertSelective(SpPersonne spPersonne);
    
    List<SpPersonne> listByCodes(List<String> codes);
}
