package com.mobile.service;


import java.util.List;
import java.util.Map;

import com.mobile.model.CustomerPackage;
import com.mobile.model.Page;

public interface CustomerPackageService {
    int add(CustomerPackage customerPackage);//新增
    int delete(String id);//删除
    int update(CustomerPackage customerPackage);//更新
    List<Map<String, Object>> findCustomerPackageList(Map<String,String> map);//条件查询
    CustomerPackage findCustomerPackage(String id);
    Map<String, Object> findCustomerPackageMap(String id);
    List<Map<String, Object>> findInformationList(String id);//根据套餐id查询包含的接口信息
    
    int findRemaining(String customerId);//查询客户剩余使用量
    int updateTotal(String customerId);//更新客户使用数量
    int updateTopup(Map<String,String> map);//充值
    /**
     * 分页查询 充值表
     * @param map
     * @return
     */
    Page findInformationPage(Map<String,Object> map);
    /**
     * 条件查询充值信息
     * @param map
     * @return
     */
    Map<String, Object> findCustomerPackageMap(Map<String,Object> map);
    /**
     * 充值记录表
     * @param map
     * @return
     */
    Page findPrepaidRecords(Map<String,Object> map);
    /**
     * 客户充值并记录充值信息
     * @param map
     * @return
     */
    int customerTopUp(Map<String,Object> map);
    /**
     * 客户编码+类型查询 并且剩余额度大于0
     * @param map
     * @return
     */
    boolean findCustomerPackage(String customerId,String topupType);
    /**
     * 更新客户使用数量
     * @param customerId
     * @param topupType
     * @return
     */
    int updateTotal(String customerId,String topupType);
    /**
     * 客户是否有剩余使用量
     * @param customerId
     * @param topupType
     * @return
     */
    boolean findRemaining(String customerId,String topupType);
    /**
     * 客户充值卡
     * @param map
     * @return
     */
	Page findCustomerCardPage(Map<String, Object> map);
    
}
