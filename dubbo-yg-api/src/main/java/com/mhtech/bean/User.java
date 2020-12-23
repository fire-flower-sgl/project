package com.mhtech.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author SGL
 * @title: User
 * @projectName dubbo-project-provider
 * @description: 用户表
 * @date 2020/12/22 19:14
 */
@Data
public class User implements Serializable {

    private Integer userId;     // 用户id
    private String username;    // 用户名
    private String password;    // 密码
    private Double money;       // 账户余额
    private String phone;       // 手机号
    private String email;       // 邮箱
    private String status;      // 状态（true/false）
    private String gender;      // 性别
    private String signature;   // 个性签名
    private String headUrl;     // 头像地址

}
