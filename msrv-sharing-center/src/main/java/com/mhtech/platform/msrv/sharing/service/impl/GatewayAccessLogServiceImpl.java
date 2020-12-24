package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.log.pojo.GatewayAccessLogDTO;
import com.mhtech.platform.log.pojo.GatewayAccessLogVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.mapper.GatewayAccessLogMapper;
import com.mhtech.platform.msrv.sharing.dao.model.GatewayAccessLog;
import com.mhtech.platform.msrv.sharing.service.IGatewayAccessLogService;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

@Service
public class GatewayAccessLogServiceImpl extends BaseServiceImpl implements IGatewayAccessLogService {

	@Autowired
	private GatewayAccessLogMapper gatewayAccessLogMapper;
	@Autowired
	RedisUtils redisUtils;
	@Value("${servleSelfCheck.Limit}")
	private Integer limit;

	@PostConstruct
	@Override
	protected void setGenericMapper() {
		super.genericMapper = gatewayAccessLogMapper;
	}

	@Override
	public Page selectLog(GatewayAccessLogDTO geAccessLog) {
		//分页		
		PageHelper.startPage(geAccessLog.getPageNo(), geAccessLog.getPageSize());
		List<GatewayAccessLog> selectLog = gatewayAccessLogMapper.selectLog(geAccessLog);
		
		List<GatewayAccessLogVO> list=new ArrayList<GatewayAccessLogVO>();
		selectLog.forEach(zh->{
			list.add(new GatewayAccessLogVO(zh.getId(),zh.getIp(),zh.getUrl(),zh.getParams(),zh.getCreatedTime(),zh.getRequestBody()));
		});
		//分页的数据
	  	PageInfo<GatewayAccessLogVO> pageInfo = new PageInfo<>(list);
	  	int total=0;
	  	int count = gatewayAccessLogMapper.selectLogSum(geAccessLog);//总条数
	  	if (count>0) {
	  		total=count;
		}
	  	int pageNo = geAccessLog.getPageNo();//页码
	  	Page page= new Page(geAccessLog.getPageSize(),total,pageInfo.getStartRow(),pageNo,pageInfo.getPages(),list);
		return page;
	}

	@Override
	public int delete(int day) {
		// TODO Auto-generated method stub
		return gatewayAccessLogMapper.delete(day);
	}

	@Override
	public List<GatewayAccessLog> selectTodayLogByApplication(List<String> applicationName) {
		return gatewayAccessLogMapper.selectTodayLogByApplication(applicationName);
	}

	@Override
	public List<GatewayAccessLog> selectLogByServerId(Long serverId) {
		
		Map<String,Object> param = (Map<String, Object>) redisUtils.get("selfCheckParam");
		if (param==null) {
			param = new HashedMap();
			param.put("limit", limit);
			param.put("serverId", serverId);
		}else {
			Integer object = (Integer) param.get("limit");
			if (object!=limit) {
				param.put("limit", limit);
			}
		}
		List<GatewayAccessLog> selectLogByServerId = gatewayAccessLogMapper.selectLogByServerId(param);
		if (selectLogByServerId.size()>0) {
			param.put("id", selectLogByServerId.get(selectLogByServerId.size()-1).getId());
			redisUtils.set("selfCheckParam", param);
		}
		return selectLogByServerId;
	}

	@Override
	public List<GatewayAccessLog> allLog(Long max, Long min, int eachItem) {
		// TODO Auto-generated method stub
		return gatewayAccessLogMapper.allLog(max, min, eachItem);
	}

	@Override
	public Map<String, Object> selectManMin(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return gatewayAccessLogMapper.selectManMin(topTime, endTime);
	}

	@Override
	public int deleteDate(String topTime, String endTime) {
		// TODO Auto-generated method stub
		return gatewayAccessLogMapper.deleteDate(topTime, endTime);
	}


}
