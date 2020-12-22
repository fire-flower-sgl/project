package com.mhtech.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SGL
 * @title: Order
 * @projectName dubbo-project-provider
 * @description: 订单表
 * @date 2020/12/22 19:26
 */
@Data
public class Order implements Serializable {

    private Integer orderId;        // 订单id
    private String orderName;       // 订单名称
    private Double orderPrice;      // 单价
    private Integer orderNum;       // 数量
    private Double orderTotal;      // 总价
    private String orderAddress;    // 送货地址
    private String success;         // 是否交易完成（true/false）
    private Date createTime;        // 下单时间
    private Date endTime;           // 签收时间
    private Good good;              // 商品信息
    private User user;              // 收件人

}
