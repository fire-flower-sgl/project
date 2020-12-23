package com.mhtech.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mhtech.bean.Order;
import com.mhtech.service.OrderService;
import com.mhtech.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SGL
 * @title: OrderServiceImpl
 * @projectName dubbo-project-provider
 * @description: TODO
 * @date 2020/12/22 22:10
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Reference
    private UserService userService;

    @Override
    public List<Order> findOrdersByUsername(String username) {
        System.out.println("username:" + userService.findUserByName(username).getUsername());
        return null;
    }

}
