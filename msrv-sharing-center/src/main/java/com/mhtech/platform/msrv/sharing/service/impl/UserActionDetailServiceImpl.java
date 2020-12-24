package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.log.pojo.MsrvLogVO;
import com.mhtech.platform.log.pojo.UserActionLogDetailDTO;
import com.mhtech.platform.log.pojo.UserActionLogDetailVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.mapper.UserActionDetailMapper;
import com.mhtech.platform.msrv.sharing.dao.model.UserActionDetail;
import com.mhtech.platform.msrv.sharing.service.UserActionDetailService;
@Service
public class UserActionDetailServiceImpl extends BaseServiceImpl implements UserActionDetailService {

	@Autowired
	UserActionDetailMapper userActionDetailMapper;
	
	@PostConstruct
	@Override
	protected void setGenericMapper() {
		super.genericMapper = userActionDetailMapper;
		
	}

	@Override
	public Page selectLog(UserActionLogDetailDTO detaDto) {
		//分页		
		PageHelper.startPage(detaDto.getPageNo(), detaDto.getPageSize());
		List<UserActionDetail> selectLog = userActionDetailMapper.selectLog(detaDto);
		
		List<UserActionLogDetailVO> list=new ArrayList<UserActionLogDetailVO>();
		selectLog.forEach(zh->{
			list.add(new UserActionLogDetailVO(zh.getId(),zh.getUsrActId(),zh.getActionType(),zh.getActionSqlId(),zh.getSqlParams()));
		});
		
		//分页的数据
	  	PageInfo<UserActionLogDetailVO> pageInfo = new PageInfo<>(list);
	  	int total=0;
	  	int count = userActionDetailMapper.selectLogSum(detaDto);//总条数
	  	if (count>0) {
	  		total=count;
		}
	  	int pageNo = detaDto.getPageNo();//页码
	  	
	  	Page page= new Page(detaDto.getPageSize(),total,pageInfo.getStartRow(),pageNo,pageInfo.getPages(),list);
		return page;
	}

	@Override
	public int deleteId(Long[] ids) {
		// TODO Auto-generated method stub
		return userActionDetailMapper.deleteId(ids);
	}

}
