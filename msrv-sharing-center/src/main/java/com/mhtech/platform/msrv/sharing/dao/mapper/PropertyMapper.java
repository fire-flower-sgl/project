package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.msrv.pojo.PropertyDTO;
import com.mhtech.platform.msrv.pojo.PropertyVOId;



@Mapper
public interface PropertyMapper extends GenericMapper{
	/**
	 * 查看资产编号是否存在
	 * @param mt
	 * @return
	 */
	int isExist(PropertyDTO mt);
	
	/**
	 * 根据id查询警报条数
	 * @param id
	 * @return
	 */
	int findNotifyCountById(Long id);
	
	/**
	 * 根据查看详情
	 * @param id
	 * @return
	 */
	PropertyVOId findById(Long id);

	/**
	 * 查询服务id
	 * @param id
	 * @return
	 */
	Long findServerId(Long id);

	/**
	 * TODO 根据名称模糊查询硬件下拉列表
	 * @param serverName
	 * @return
	 */
	List<Map<String, Object>> getProperty(String serverName);

	/**
	 * TODO
	 * @param mt
	 * @return
	 */
	int ipIsExist(PropertyDTO mt);

	/**
	 * TODO
	 * @param serverName
	 * @return
	 */
	List<Map<String, Object>> getPropertyNameAndIp(@Param(value = "serverName") String serverName);
	
	int deleteByIds(List<String> list);
}