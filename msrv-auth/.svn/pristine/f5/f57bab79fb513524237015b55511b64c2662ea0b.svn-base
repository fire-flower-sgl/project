package com.mhtech.platform.msrv.auth.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.msrv.auth.dao.model.SysRole;
@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(String roleNum);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    //查询所有角色-模糊查询-分页
    List<SysRole> listRole(SysRole record);
    //总条数
    int totalCount(SysRole record);
    
    //角色编号唯一新，判断
    boolean findSysPowerOne(String roleNum);
  
    //依据role_user查询role——num
    String selectRoleNum(String roleUser);
   
    
    
    
    
    
}