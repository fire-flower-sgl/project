package com.mhtech.platform.msrv.auth.service;

import java.util.List;

import com.mhtech.platform.log.pojo.ServerChkRuleDTO;
import com.mhtech.platform.log.pojo.ServerChkRuleVO;
import com.mhtech.platform.msrv.pojo.Page;

public interface ServerChkRuleService{
	
		
	/**
	 * 插入服务自检
	 * @param serverChkRuleDTO
	 * @return 插入条数
	 */
	Integer insert(ServerChkRuleDTO scrDt);
	/**
	 * 更新服务自检
	 * @param scrDt
	 * @return 更新条数
	 */
	Integer modifyById(ServerChkRuleDTO scrDt);
	/**
	 * 删除服务自检
	 * @param scrDt
	 * @return 删除条数
	 */
	Integer delById(ServerChkRuleDTO scrDt);
	/**
	 * 分页模糊查找
	 * @param serverChkRuleDTO
	 * @return 服务自检分页集合
	 */
	Page queryPage(ServerChkRuleDTO scrDt);
	/**
	 * 查找all
	 * @param 
	 * @return 服务自检规则集合
	 */
	List<ServerChkRuleVO> queryList();

}
