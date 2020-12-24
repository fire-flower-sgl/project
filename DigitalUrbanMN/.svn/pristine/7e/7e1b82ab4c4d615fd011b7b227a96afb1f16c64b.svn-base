package com.mhtech.platform.msrv.auth.service;

import java.util.List;
import java.util.Map;

import com.mhtech.platform.msrv.pojo.SpUserVO;

/**
 * sp_user表 游鸽外其他服务使用
 * @author Administrator
 *
 */
public interface ISpUserService {

	int delete(Long id);

    int insertSelective(SpUserVO record);

    SpUserVO select(Long id);

    int updateByPrimaryKeySelective(SpUserVO record);

    SpUserVO select(String name,String password);
    
    int sign(String userCode,String password);

	/**
	 * TODO
	 * @param name 用户名称
	 */
	List<Map<String, Object>> selectAllUser(String name);

	/**
	 * TODO
	 * @param userCode 用户编码
	 */
	List<Map<String, Object>> selectUserInfo(String userCode);
}
