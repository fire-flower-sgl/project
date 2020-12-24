package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.log.pojo.UserActionLogDetailDTO;
import com.mhtech.platform.msrv.sharing.dao.model.UserActionDetail;

@Mapper
public interface UserActionDetailMapper extends GenericMapper {
	
	//依据条件查询+分页
	List<UserActionDetail> selectLog(UserActionLogDetailDTO userActionLogDetailDTO);
	//总条数
	int selectLogSum(UserActionLogDetailDTO userActionLogDetailDTO);
	
	//删除id集合
	int deleteId(Long[] ids);
}