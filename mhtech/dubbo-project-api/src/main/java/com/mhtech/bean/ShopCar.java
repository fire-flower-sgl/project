package com.mhtech.bean;

import java.io.Serializable;

/**
 * @author SGL
 * @title: ShopCar
 * @projectName dubbo-project-provider
 * @description: 购物车表
 * @date 2020/12/22 20:00
 */
public class ShopCar implements Serializable {

    private Integer shopCarId;
    private Good good;
    private User user;

}
