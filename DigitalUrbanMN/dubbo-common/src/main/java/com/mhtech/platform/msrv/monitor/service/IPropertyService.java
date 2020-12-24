package com.mhtech.platform.msrv.monitor.service;

import java.util.List;
import java.util.Map;

import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.PropertyDTO;
import com.mhtech.platform.msrv.pojo.PropertyHardwareVO;
import com.mhtech.platform.msrv.pojo.PropertyVO;
import com.mhtech.platform.msrv.pojo.PropertyVOId;

public interface IPropertyService extends IBaseService{
	
	
	/**
	 * 分页查询资产-服务器
	 * @param mt
	 * @return
	 */
	Page<PropertyVOId> queryPage(PropertyDTO mt);
	
	/**
	 * 查看资产编号是否存在
	 * @param mt
	 * @return
	 */
	boolean isExist(PropertyDTO mt);

	/**
	 * 根据id查询服务器资产
	 * @param id
	 * @return
	 */
	PropertyVO findById(Long id);
	
	/**
	 * 根据资产编号查询硬件集合
	 * @param id
	 * @return
	 */
	List<PropertyHardwareVO> getHardwareListById(Long id,String Code);

	/**
	 * 根据服务器编号和硬件名称集合删除
	 * @param mt
	 * @return
	 */
	int delPreHardware(PropertyDTO mt);
	
	/**
	 * 给服务器添加硬件
	 * @param mt
	 * @return
	 */
	int addPreHardware(PropertyDTO mt);
	
	/**
	 * 删除服务器
	 * @param id
	 * @return
	 */
	int del(Long id);

	/**
	 * TODO 根据名称模糊查询硬件下拉列表
	 * @param serverName
	 * @return
	 */
	List<Map<String, Object>> getProperty(String serverName);
	
	
	/**
	 * 查看ip是否存在
	 * @param mt
	 * @return
	 */
	boolean ipIsExist(PropertyDTO mt);

	/**
	 * TODO 获取硬件名称和ip
	 * @param serverName 模糊查询名称和ip
	 * @return
	 */
	List<Map<String, Object>> getPropertyNameAndIp(String serverName);
	
	
	int deleteByIds(List<String> list);



}
