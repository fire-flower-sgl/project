package com.mhtech.platform.msrv.algorithm.service;

import java.util.List;


import com.mhtech.platform.msrv.pojo.AlTeamMemberDTO;
import com.mhtech.platform.msrv.pojo.AlTeamMemberVO;

/**
 * 团队成员
 * 
 * @author mjl
 *
 */
public interface IAlTeamMemberService {

	//删除
	int delete(Long id);
	//新增
	int insert(AlTeamMemberDTO record);
	//查询
	AlTeamMemberVO selectId(Long id);
	//更新
	int update(AlTeamMemberDTO record);
	
	//按名称模糊查询所有项目成员
    List<AlTeamMemberVO> listName(String nickname);
    
    //名称唯一性确定
    boolean nameOnly(String userCode);
    //按项目id查询所有人员图片
  	List<AlTeamMemberVO> listProjectImg(Long id);
    //查询所有团员（用户编码与名称）数据 
  	List<AlTeamMemberVO> listProject();
  	
	//依据名称查询对象
  	AlTeamMemberVO vo( String userCode );
  	
}
