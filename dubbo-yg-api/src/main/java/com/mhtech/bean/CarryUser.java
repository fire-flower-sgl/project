package com.mhtech.bean;

import java.io.Serializable;

/**
 * @author SGL
 * @title: CarryUser
 * @projectName dubbo-project-provider
 * @description: 配送员表
 * @date 2020/12/22 20:25
 */
public class CarryUser implements Serializable {

    private Integer carryUesrId;        // id
    private String company;             // 所属快递公司（如：韵达）
    private String carryUserName;       // 配送员姓名
    private String carryUserphone;      // 配送员手机号

}
