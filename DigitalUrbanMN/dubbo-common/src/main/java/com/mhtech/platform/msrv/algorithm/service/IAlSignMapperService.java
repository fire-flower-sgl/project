package com.mhtech.platform.msrv.algorithm.service;

import java.util.List;

import com.mhtech.platform.msrv.pojo.AlSignMapperDTO;
import com.mhtech.platform.msrv.pojo.AlSignMapperVO;

public interface IAlSignMapperService {

	// 更新
	int update(AlSignMapperDTO dto);
	
    // 查询所有项目标记情况
    List<AlSignMapperVO> listSignPrjCode();
    
    // 项目标记是否存在
    boolean selectPrjCode(String prjCode);
    
    // 新增
    String insertSelective(AlSignMapperDTO dto);
}
