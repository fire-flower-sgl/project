package com.mhtech.platform.msrv.sharing.service;

import com.mhtech.platform.log.pojo.UserActionLogDetailDTO;
import com.mhtech.platform.msrv.pojo.Page;

public interface UserActionDetailService extends IBaseService{

	//删除id集合
	int deleteId(Long[] ids);
	
	//依据条件查询
	Page selectLog(UserActionLogDetailDTO userActionLogDetailDTO);
}
