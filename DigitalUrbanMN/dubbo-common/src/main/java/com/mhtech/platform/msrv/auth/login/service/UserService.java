package com.mhtech.platform.msrv.auth.login.service;

import com.mobile.model.LoginUser;
import com.mobile.model.User;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author admini
 * @Date 2019/8/29 17:17
 * @Version 1.0
 */
public interface UserService {
    /*
     * @Author admini
     * @Description //TODO id+password查询登录用户信息
     * @Date 17:18 2019/8/29
     * @Param [id, password]
     * @return com.mhtech.modules.entity.User
     **/
    User findUser(String name);
    
    
    LoginUser findUserByNameAndPwd(String userName,String password);//找用户
    LoginUser findUserByNameAndPwdAndPlatForm(String userName,String password,String platForm);//找用户
	boolean addLoginUser(String userCode, String ip, String browserName);//插入登录用户
	boolean addLoginUserLog(String loginLogId,String userCode, String ip, String browserName, String string);//插入登录历史表
	
	boolean findIpIsExist(String ip);//判断该ip是否存在
	boolean findWebIsExist(String browserName);//判断该浏览器类型是否存在

	boolean removeUserByUserCode(String userCode);//清除该用户在线(单方登录用户)
	boolean addLogOffDate(String loginLogId);//插入退出时间
	boolean removeUserByWebType(String browserName, String userCode);//清除该浏览器的用户(多方登录用户)
	boolean updatePwd(String userAccount,String newPwd);//重置密码
	String findUserCode(String account);//查找用户code
	boolean checkAccountExist(String account);//检查账户是否存在email/phone
	String queryParamByKey(String key);//查询初始密码

    String userEmail (String userCode);//依据用于编码查询用户邮箱


	/**
	 * TODO  检测账户是否异常
	 * @param userCode
	 * @param ip
	 * @return
	 */
	boolean queryUserIsNormal(String userCode, String ip);










}
