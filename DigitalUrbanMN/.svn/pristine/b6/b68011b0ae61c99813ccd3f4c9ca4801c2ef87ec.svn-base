package com.mhtech.platform.msrv.monitor.service;

import com.mhtech.platform.msrv.pojo.HardwareTypeDTO;
import com.mhtech.platform.msrv.pojo.Page;

public interface IHardwareTypeService extends IBaseService{

	/**
	 * 分页查询硬件类型
	 * @param mp
	 * @return
	 */
	Page queryPage(HardwareTypeDTO mp);
	/**
	 * 删除硬件类型
	 * @param typeCode
	 * @return
	 */
	int remove(String typeCode);
	/**
	 * 检查硬件编码是否存在
	 * @param mt
	 * @return
	 */
	boolean isExist(HardwareTypeDTO mt);

}
