package com.mhtech.platform.msrv.monitor.service;

import com.mhtech.platform.msrv.pojo.SpDeviceAdmin;


/**
 * 参数字典管理
 * @author Guo
 *
 */
public interface SpDeviceAdminService extends IBaseService{

	SpDeviceAdmin findDeviceAdminByIp(String ip);
}
