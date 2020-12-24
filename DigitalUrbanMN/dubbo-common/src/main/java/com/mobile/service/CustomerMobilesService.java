package com.mobile.service;



import java.util.List;
import java.util.Map;

import com.mobile.model.CustomerMobiles;
import com.mobile.model.Page;

/**
 * @ClassName CustomerMobilesDao
 * @Description TODO
 * @Author admini
 * @Date 2019/8/29 9:13
 * @Version 1.0
 */
public interface CustomerMobilesService {
	/**
	 * 批量新增
	 * @param customerMobiles
	 * @return
	 */
    int add(List<CustomerMobiles> customerMobiles);
    /**
     * 新增
     * @param customerMobiles
     * @return
     */
    int add(CustomerMobiles customerMobiles);
    /**
     * 更新
     * @param customerMobiles
     * @return
     */
    int update(CustomerMobiles customerMobiles);
    /**
     * 删除
     * @param id
     * @return
     */
    int delete(String id);
    /**
     * 根据用户id查询用户下所有手机号
     * @param map
     * @return
     */
    Page findCustomerMobilesPage(Map<String,String>map);
    /**
     * 根据用户id查询用户下所有手机号
     * @param map
     * @return
     */
    List<Map<String, String>> findCustomerMobilesList(Map<String,String>map);
    /**
     * 根据sql查询count条数
     * @param sqlCount
     * @return
     */
    int findCustomerMobilesCount(String sqlCount);
    /**
     * 根据sql查询count条数
     * @param customerMobiles
     * @return
     */
    int findCustomerMobilesCount(CustomerMobiles customerMobiles);
    /**
     * 根据电话号码查询count条数
     * @param mobileNO
     * @return
     */
    int findCustomerMobilesCountByMobileNo(String mobileNo);

}
