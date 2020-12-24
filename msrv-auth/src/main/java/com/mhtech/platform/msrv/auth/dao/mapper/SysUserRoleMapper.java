package com.mhtech.platform.msrv.auth.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.mhtech.platform.msrv.auth.dao.model.SysUserRole;
import com.mhtech.platform.msrv.pojo.UserRoleDTO;

@Mapper
public interface SysUserRoleMapper {

	int deleteByPrimaryKey(String id);

	int insert(SysUserRole record);

	int insertSelective(SysUserRole record);

	SysUserRole selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysUserRole record);

	int updateByPrimaryKey(SysUserRole record);
	
	/**
	 * 查询用户角色代码
	 * @param userCode
	 * @return
	 */
	List<String> selectRoleNumsByUserCode(String userCode);
	//删除用户下所有角色关联
	int deleteUser(String userCode);
	
	//批量新增
	int insertList(List<SysUserRole> list);
	
	
}