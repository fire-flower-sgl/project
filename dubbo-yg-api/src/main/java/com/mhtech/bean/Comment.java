package com.mhtech.bean;

import java.io.Serializable;

/**
 * @author SGL
 * @title: Comment
 * @projectName dubbo-project-provider
 * @description: 商品评价表
 * @date 2020/12/22 20:37
 */
public class Comment implements Serializable {

    private Integer commentId;      // 评价id
    private String headUrl;         // 用户头像地址
    private String username;        // 用户昵称
    private String content;         // 评价内容
    private Integer like;           // 点赞数
    private Integer goodId;         // 商品id

}
