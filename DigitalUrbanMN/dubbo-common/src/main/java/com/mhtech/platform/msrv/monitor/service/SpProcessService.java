package com.mhtech.platform.msrv.monitor.service;

import java.util.Map;

import com.mhtech.platform.msrv.pojo.SpProcess;
import com.mobile.model.Page;

/**
 * 流程操作管理
 * @author Guo
 *
 */
public interface SpProcessService{

	/**
	 * TODO
	 * @param spProcess
	 * @return
	 */
	int insertSpProcess(SpProcess spProcess);

	/**
	 * TODO
	 * @param spProcess
	 * @return
	 */
	int updateSpProcess(SpProcess spProcess);

	/**
	 * TODO
	 * @param map
	 * @return
	 */
	Page queryPage(Map<String, Object> map);

	/**
	 * TODO
	 * @param spProcess
	 * @return
	 */
	int deleteSpProcess(SpProcess spProcess);

	/**
	 * TODO 根据id查询流程
	 * @param businessId
	 * @return
	 */
	Map<String, Object> findSpProcess(String businessId);

	
	
	
	
	
}
