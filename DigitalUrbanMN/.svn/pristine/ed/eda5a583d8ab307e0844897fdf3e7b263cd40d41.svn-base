package com.mobile.service;

import java.util.List;
import java.util.Map;

import com.mobile.model.Customer;
import com.mobile.model.Page;

public interface CustomerService {
    int add(Customer customer);//新增
    int delete(String id);//删除
    int update(Customer customer);//更新
//    List<Map<String, Object>> findCustomerList(Customer customer);
    List<Map<String, Object>> findCustomerList(Map<String,Object> map);//条件查询 显示客户编码，客户id
    Customer findCustomer(String customer_id);//根据id查询
    Map<String, Object> findCustomerMap(String customerId,String state);//根据id查询
    List<Map<String, Object>> findCorCustomerrList(String customer_id);//条件查询
    int findCustomerCount(Map<String,String> map);//根据条件查询count
    int findCustomerPermission(String customer_id, String fasename);//根据条件查询权限
    Page findCustomerPage(Map<String,String> map);//条件查询 分页
    int findCustomerPageCount(String sql_count);
    /**
     * 查询客户是否存在
     * @param map
     * @return
     */
    boolean findCustomer(Map<String,Object> map);
    /**
	  * 根据手机号 获取客户信息(客户编码，是否发送回调信息)
	  * @param map
	  * @return
	  */
	 List<Map<String, Object>> findCustomerByMobile(Map<String, Object> map);
}
