package com.mhtech.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author SGL
 * @title: Good
 * @projectName dubbo-project-provider
 * @description: 商品表
 * @date 2020/12/22 19:43
 */
@Data
public class Good implements Serializable {

    private Integer goodId;         // 商品id
    private String goodName;        // 商品名
    private String goodDesc;        // 商品描述
    private Double goodPrice;       // 商品单价
    private Integer goodNum;        // 库存数量
    private String goodUrl;         // 商品图片地址
    private String goodVideoUr;     // 商品视频地址
    private Integer commentId;      // 评价id
    private Shop shop;              // 所属商家

}
