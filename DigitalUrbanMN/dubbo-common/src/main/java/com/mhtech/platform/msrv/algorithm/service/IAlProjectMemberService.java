package com.mhtech.platform.msrv.algorithm.service;

import java.util.List;


import com.mhtech.platform.msrv.pojo.AlProjectMemberDTO;
import com.mhtech.platform.msrv.pojo.AlProjectMemberVO;

/**
 * 项目成员
 * 
 * @author mjl
 *
 */
public interface IAlProjectMemberService {
		
	// 删除
	int delete(Long id);
	// 新增
	int insert(AlProjectMemberDTO record);
	// 查询
	AlProjectMemberVO selectId(Long id);
	// 更新
	int update(AlProjectMemberDTO record);
	
	// 删除项目下所有成员
    int delectList( String prjCode);
    // 删除成员的所有项目信息
    int delectUserCode( String userCode);
    //批量新增
    int insertList(List<AlProjectMemberDTO> list);
    //验证项目编号-用户编码唯一（一个人在一个项目里，不会出现2次）
    boolean priUserOnly(String prjCode,String userCode);
}
