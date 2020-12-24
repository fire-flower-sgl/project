package com.mobile.service;

import java.util.List;
import java.util.Map;

import com.mobile.model.Page;

/**
 * @ClassName CustomerInterfaceDao
 * @Description TODO
 * @Author admini
 * @Date 2019/8/2 13:09
 * @Version 1.0
 */
public interface CustomerInterfaceService {
    int add(List<Map<String,String>> list, String customer_id);
    int add(Map<String,String> map);
    int update(Map<String,String> map);
    int delete(String id);
    int findeCustomerInterfaceForRemaining(String customerId,String faceclass);//根据客户编号，接口查询接口剩余
    int updateCustomerInterfaceForRemaining(String customerId,String faceclass);//根据客户编号，接口查询接口更新使用次数，剩余次数
    List<Map<String,String>> findCustomerInterfaceList(String customerId);
    //验证客户接口是否存在
    boolean therefindeCustomerInterface(Map<String,Object> map);
    /* 客户接口分页查询
    * @param map
    * @return
    */
   Page findCustomerInterfacePage(Map<String,String>map);
   /**
          * 根据客户编码查询对应的客户接口信息
    * @param customerId
    * @return
    */
   List<Map<String,Object>> CustomerInterfaceList(String customerId);
}
