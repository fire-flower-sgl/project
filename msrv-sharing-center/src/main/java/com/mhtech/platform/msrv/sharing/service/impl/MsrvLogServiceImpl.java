package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.log.pojo.GatewayAccessLogVO;
import com.mhtech.platform.log.pojo.MsrvLogDTO;
import com.mhtech.platform.log.pojo.MsrvLogVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.mapper.MsrvLogMapper;
import com.mhtech.platform.msrv.sharing.dao.model.MsrvLog;
import com.mhtech.platform.msrv.sharing.request.LogDTO;
import com.mhtech.platform.msrv.sharing.service.MsrvLogService;

@Service
public class MsrvLogServiceImpl extends BaseServiceImpl implements MsrvLogService {

	@Autowired
	private MsrvLogMapper msrvLogMapper;

	@PostConstruct
	@Override
	protected void setGenericMapper() {
		super.genericMapper = msrvLogMapper;
	}

	

	@Override
	public Page selectLog(MsrvLogDTO msrvLogDTO) {
		
		//分页		
		PageHelper.startPage(msrvLogDTO.getPageNo(), msrvLogDTO.getPageSize());
		List<MsrvLog> selectLog = msrvLogMapper.selectLog(msrvLogDTO);
		
		List<MsrvLogVO> list=new ArrayList<MsrvLogVO>();
		selectLog.forEach(zh->{
			list.add(new MsrvLogVO(zh.getLogId(),zh.getTraceId(),zh.getSide(),zh.getApplication(),zh.getInterfaceName()
					,zh.getMethods(),zh.getHost(),zh.getCreatedTime(),zh.getArguments()));
		});
		
		//分页的数据
	  	PageInfo<MsrvLogVO> pageInfo = new PageInfo<>(list);
	  	int total=0;
	  	int count = msrvLogMapper.selectLogSum(msrvLogDTO);//总条数
	  	if (count>0) {
	  		total=count;
		}
	  	int pageNo = msrvLogDTO.getPageNo();//页码
	  	
	  	Page page= new Page(msrvLogDTO.getPageSize(),total,pageInfo.getStartRow(),pageNo,pageInfo.getPages(),list);
		return page;
	}

	@Override
	public int delete(int day) {
		// TODO Auto-generated method stub
		return msrvLogMapper.delete(day);
	}





	@Override
	public int selectLogSum(MsrvLogDTO msrvLogDTO) {
		// TODO Auto-generated method stub
		return msrvLogMapper.selectLogSum(msrvLogDTO);
	}



	@Override
	public int sjSizeString(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return msrvLogMapper.sjSize(topTime, endTime);
	}



	@Override
	public  Map<String, Object> selectManMin(LogDTO dto) {
		// TODO Auto-generated method stub
		return msrvLogMapper.selectManMin(dto);
	}



	@Override
	public List<MsrvLog> allLog(Long maxId, Long minId, int eachItem) {
		// TODO Auto-generated method stub
		return msrvLogMapper.allLog(maxId, minId, eachItem);
	}



	@Override
	public int deleteDate(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return msrvLogMapper.deleteDate(topTime, endTime);
	}




}
