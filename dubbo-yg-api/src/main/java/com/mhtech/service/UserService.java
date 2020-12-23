package com.mhtech.service;

import com.mhtech.bean.User;

/**
 * @author SGL
 * @title: LoginService
 * @projectName dubbo-project-provider
 * @description: TODO
 * @date 2020/12/22 19:20
 */
public interface UserService {

    /**
     * 根据用户名获取User对象
     * @param username
     * @return
     */
    User findUserByName(String username);

}
