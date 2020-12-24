package com.springboot.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "myTest")
public class MyTestServiceImpl {

    public void test(){
        System.out.println("这是我的测试方法~");
    }

}
