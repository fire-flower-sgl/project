package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.log.pojo.ServerChkRuleDTO;
import com.mhtech.platform.msrv.sharing.dao.model.ServerChkRuleWithBLOBs;

@Mapper
public interface ServerChkRuleMapper extends GenericMapper {

	Integer deleteByPrimaryKey(ServerChkRuleDTO scrDt);

	List<ServerChkRuleWithBLOBs> findAll(ServerChkRuleDTO scrDt);

	List<ServerChkRuleWithBLOBs> findList();

}