package com.mhtech.platform.msrv.monitor.service;

import java.util.List;

import com.mhtech.platform.msrv.pojo.DictTypeSearchParam;
import com.mhtech.platform.msrv.pojo.DictTypeVO;
import com.mhtech.platform.msrv.pojo.DictVO;
import com.mhtech.platform.msrv.pojo.Page;

/**
 * 字典服务远程调用
 * @author GM
 */
public interface IDictRpcCaller {

	/**
	 * 查询字典类型属性值
	 * @param type Dict_Type 类型名称
	 * @return
	 */
	List<DictVO> listTypeValue(String type);
	
	/**
	 * 查询字典类型列表
	 * @param param type/name 模糊检索
	 * @return
	 */
	Page<DictTypeVO> listType(DictTypeSearchParam param);
}
