package com.mobile.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.mobile.model.LoginUser;
import com.mobile.model.Page;
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
    boolean queryUserIsNormal(String userCode, String ip);
  
    
  	/**
  	 * 根据usercode 查询用户权限
  	 * @param userCode
  	 * @return
  	 */
  	boolean fingQx(String userCode);
  	/**
  	 * 查询用户登录时长
  	 * @param userCode
  	 * @param ip
  	 * @return
  	 */
  	boolean fingLogin(String userCode, String ip);
  	/**
  	 * 更新用户登录时间
  	 * @param userCode
  	 * @param ip
  	 * @return
  	 */
  	int updateOnlineTime(String userCode, String ip);
  	/**
  	 * 获取用户上次登录ip地址
  	 * @param userCode
  	 * @return
  	 */
  	String fingLoginIp(String userCode);
  	/**
  	 * 查询用户是否单点登录
  	 * @param userCode
  	 * @return
  	 */
  	boolean fingMultiLogin(String userCode);
  	
  //查询
  	User selectSysPower (String id);
  	//修改
  	int updateSysPower (User spUser);
  	//修改用户时间
  	int updateUser (User spUser);
  	//修改用户信息
  	int updateUserInfo (User spUser);


	//新增多个用户下-多个角色-角色对应系统权限与特殊权限      （一个批处理事务）
	int[] insertAllUser(List<User> spUsers);

	List<User> EmailList();//查询邮件列表
	
	Page findPage(JSONObject object);//分页查找
	boolean add(JSONObject reqObject);//增加新用户信息(用户+角色+特殊权限+职能)
  	String addNewUser(User spUser);//增加新用户
  	boolean update(JSONObject reqObject);//更新用户信息

  	int delete(String userCode);//删除用户

  	int isAccountExist(String phoneNo,String email,String userCode);//验证手机号/邮箱是否存在

  	boolean isUserCodeExist(String contect,String userCode);//验证用户账号是否存在
}
