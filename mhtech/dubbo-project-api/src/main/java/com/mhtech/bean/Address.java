package com.mhtech.bean;

import java.io.Serializable;

/**
 * @author SGL
 * @title: Address
 * @projectName dubbo-project-provider
 * @description: 地址表
 * @date 2020/12/22 19:54
 */
public class Address implements Serializable {

    private Integer addressId;      // 地址id
    private String province;        // 省
    private String city;            // 市
    private String detailAddress;   // 详细地址

}
