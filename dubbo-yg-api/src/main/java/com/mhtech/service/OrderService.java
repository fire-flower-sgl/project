package com.mhtech.service;

import com.mhtech.bean.Order;

import java.util.List;

/**
 * @author SGL
 * @title: OrderService
 * @projectName dubbo-project-provider
 * @description: TODO
 * @date 2020/12/22 19:49
 */
public interface OrderService {

    List<Order> findOrdersByUsername(String username);

}
