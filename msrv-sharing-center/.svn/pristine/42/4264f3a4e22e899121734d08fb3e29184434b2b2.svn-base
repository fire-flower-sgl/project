package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.log.pojo.UserActionLogDTO;
import com.mhtech.platform.log.pojo.UserActionLogVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.mapper.UserActionLogMapper;
import com.mhtech.platform.msrv.sharing.dao.model.UserActionLog;
import com.mhtech.platform.msrv.sharing.service.UserActionLogService;

@Service
public class UserActionLogServiceImpl extends BaseServiceImpl implements UserActionLogService {

	@Autowired
	private UserActionLogMapper userActionLogMapper;
	
	@PostConstruct
	@Override
	protected void setGenericMapper() {
		super.genericMapper = userActionLogMapper;
	}


	@Override
	public Page selectLog(UserActionLogDTO userActionLogDTO) {
		//分页		
		PageHelper.startPage(userActionLogDTO.getPageNo(), userActionLogDTO.getPageSize());
		List<UserActionLog> selectLog = userActionLogMapper.selectLog(userActionLogDTO);
		
		List<UserActionLogVO> list=new ArrayList<UserActionLogVO>();
		selectLog.forEach(zh->{
			list.add(new UserActionLogVO(zh.getId(),zh.getUserCode(),zh.getActionModule(),zh.getActionNum(),
					zh.getActionEndTime(),zh.getActionEndTime()));
		});
		//分页的数据
	  	PageInfo<UserActionLogVO> pageInfo = new PageInfo<>(list);
	  	int total=0;
	  	int count = userActionLogMapper.selectLogSum(userActionLogDTO);//总条数
	  	if (count>0) {
	  		total=count;
		}
	  	int pageNo = userActionLogDTO.getPageNo();//页码
	  	
	  	Page page= new Page(userActionLogDTO.getPageSize(),total,pageInfo.getStartRow(),pageNo,pageInfo.getPages(),list);
		return page;
	}

	@Override
	public int delete(int day) {
		// TODO Auto-generated method stub
		return userActionLogMapper.delete(day);
	}

	@Override
	public Long[] selectIds(String topTime,String endTime) {
		// TODO Auto-generated method stub
		return userActionLogMapper.selectIds(topTime, endTime);
	}


	@Override
	public List<UserActionLog> allLog(Long max, Long min, int eachItem) {
		// TODO Auto-generated method stub
		return userActionLogMapper.allLog(max, min, eachItem);
	}


	@Override
	public Map<String, Object> selectManMin(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return userActionLogMapper.selectManMin(topTime, endTime);
	}


	@Override
	public int deleteDate(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return userActionLogMapper.deleteDate(topTime, endTime);
	}



	

}
