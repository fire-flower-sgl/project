package com.mhtech.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mhtech.bean.User;
import com.mhtech.service.UserService;

/**
 * @author SGL
 * @title: UserServiceImpl
 * @projectName dubbo-project-provider
 * @description: TODO
 * @date 2020/12/22 22:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findUserByName(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }

}
