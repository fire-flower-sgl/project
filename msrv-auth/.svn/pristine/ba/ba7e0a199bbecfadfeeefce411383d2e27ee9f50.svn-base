package com.mhtech.platform.msrv.auth.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.mhtech.platform.msrv.auth.dao.model.SysRolePower;

@Mapper
public interface SysRolePowerMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRolePower record);

    int insertSelective(SysRolePower record);

    SysRolePower selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRolePower record);

    int updateByPrimaryKey(SysRolePower record);
    
    /**
     * 查询角色权限代码
     * @param roleNums
     * @return
     */
    List<String> selectPowerNumsByRoles(@Param("roleNums") List<String> roleNums);
    //删除角色下的关联权限。
    int deleteRower(SysRolePower record);
    //批量修片角色关联权限
    int insertAll(List<SysRolePower> record);

    
    
}