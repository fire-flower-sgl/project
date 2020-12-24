package com.mhtech.platform.msrv.algorithm.service;

import java.util.List;

import com.mhtech.platform.msrv.pojo.AlProjectLogDTO;
import com.mhtech.platform.msrv.pojo.AlProjectLogVO;

/**
 * al_project_log 日志
 * 
 * @author mjl
 *
 */
public interface IAlProjectLogMapperService {

	int delete(Long id);

	int insert(AlProjectLogDTO record);

	AlProjectLogVO selectByPrimaryKey(Long id);

	int update(AlProjectLogDTO record);
	
	//查询所有日志集合
	List<AlProjectLogVO> list(AlProjectLogDTO record);
    //批量新增
    int insertList(List<AlProjectLogDTO> list);
	
}
