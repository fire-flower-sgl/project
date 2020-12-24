package com.mhtech.platform.msrv.sharing.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.log.pojo.ServerAlertRuleDTO;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.IServerAlertRuleService;
import com.mhtech.platform.msrv.pojo.AlertInfoUpdate;
import com.mhtech.platform.msrv.pojo.AlertRuleInfo;
import com.mhtech.platform.msrv.pojo.AlertRuleVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ServerAlertRule;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerAlertRuleMapper;

@Service("serverAlertRuleService")
public class ServerAlertRuleServiceImpl implements IServerAlertRuleService {

	@Autowired
	private ServerAlertRuleMapper serverAlertRuleMapper;
	@Autowired
	private IworkService iworkService;

	@Override
	public int addAlertRule(AlertRuleInfo rule) {

		Date date = new Date();
		Timestamp endDate=null;
		Timestamp startDate=null;
		if (rule.getStopListenStart()!=null) {
			Date start = rule.getStopListenStart();
			startDate = new Timestamp(start.getTime());
		}
		if (rule.getStopListenEnd()!=null) {
			Date end = rule.getStopListenEnd();
			endDate = new Timestamp(end.getTime());
		}
		
		com.mhtech.platform.msrv.sharing.dao.model.ServerAlertRule sd = new
		com.mhtech.platform.msrv.sharing.dao.model.ServerAlertRule(iworkService.getNextId(), rule.getServerId
		(), rule.getParamName(), rule.getAlertLimit(), rule.getDuration(), date, rule.getLevel(), rule.getContacts
		(), startDate, endDate);
	
		return  serverAlertRuleMapper.insertSelective(sd);
	}

	@Override
	public int updateAlertRule(AlertRuleInfo rule) {
		return serverAlertRuleMapper.updateByPrimaryKeySelective(rule);
	}

	@Override
	public int removeAlertRule(Long ruleId) {
		return serverAlertRuleMapper.deleteByPrimaryKey(ruleId);
	}

	@Override
	public List<ServerAlertRule> getServerAlertRuleByParam(long serverId, String paramName) {
		AlertRuleVO arv = new AlertRuleVO();
		arv.setServerId(serverId);
		arv.setParamName(paramName);
		List<com.mhtech.platform.msrv.sharing.dao.model.ServerAlertRule> rules = serverAlertRuleMapper
				.selectEntities(arv);
		return rules.stream().map(serverAlertRule -> {
			return new ServerAlertRule(serverAlertRule.getId(), serverAlertRule.getServerId(),
					serverAlertRule.getParamName(), serverAlertRule.getAlertLimit(), serverAlertRule.getDuration(),
					serverAlertRule.getCreatedTime(), serverAlertRule.getContacts(),
					serverAlertRule.getStopListenStart(), serverAlertRule.getStopListenEnd(),serverAlertRule.getLevel()
					, serverAlertRule.getNotifyMethod(), serverAlertRule.getContactsMethod(), serverAlertRule.getNotifyStatus()
					, serverAlertRule.getTempStopListenStart(), serverAlertRule.getTempStopListenEnd());
		}).collect(Collectors.toList());
	}

	@Override
	public List<ServerAlertRule> getAlertRuleByDuration(AlertRuleVO rule) {
		List<com.mhtech.platform.msrv.sharing.dao.model.ServerAlertRule> rules = serverAlertRuleMapper
				.selectByDuration(rule);
		return rules.stream().map(serverAlertRule -> {
			return new ServerAlertRule(serverAlertRule.getId(), serverAlertRule.getServerId(),
					serverAlertRule.getParamName(), serverAlertRule.getAlertLimit(), serverAlertRule.getDuration(),
					serverAlertRule.getCreatedTime(), serverAlertRule.getContacts(),
					serverAlertRule.getStopListenStart(), serverAlertRule.getStopListenEnd());
		}).collect(Collectors.toList());
	}

	@Override
	public ServerAlertRule select(Long id) {
		 com.mhtech.platform.msrv.sharing.dao.model.ServerAlertRule serverAlertRule = serverAlertRuleMapper.selectByPrimaryKey(id);
		 return new ServerAlertRule(serverAlertRule.getId(), serverAlertRule.getServerId(),
				serverAlertRule.getParamName(), serverAlertRule.getAlertLimit(), serverAlertRule.getDuration(),
				serverAlertRule.getCreatedTime(), serverAlertRule.getContacts(),
				serverAlertRule.getStopListenStart(), serverAlertRule.getStopListenEnd(), serverAlertRule.getLevel(), serverAlertRule.getNotifyMethod()
				, serverAlertRule.getContactsMethod(), serverAlertRule.getNotifyStatus(), serverAlertRule.getTempStopListenStart(), serverAlertRule.getTempStopListenEnd());
	}
	@Override
	public Page selectList(ServerAlertRuleDTO dto) {
		//分页		
		PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
		List<com.mhtech.platform.msrv.sharing.dao.model.ServerAlertRule> selectList = serverAlertRuleMapper.selectList(dto);
		List<ServerAlertRule> list=new ArrayList<ServerAlertRule>();
		
		selectList.forEach(sp->{
			list.add(new ServerAlertRule(sp.getId(),sp.getServerId(),sp.getParamName(),sp.getAlertLimit(),
					sp.getDuration(),sp.getCreatedTime(),sp.getContacts(),sp.getStopListenStart(),sp.getStopListenEnd()));
		});
		
		//分页的数据
	  	PageInfo<ServerAlertRule> pageInfo = new PageInfo<>(list);
	  	int total=0;
	  	int count = serverAlertRuleMapper.selectSum(dto);//总条数
	  	if (count>0) {
	  		total=count;
		}
	  	int pageNo = dto.getPageNo();//页码
	  	
		return new Page(dto.getPageSize(),total,pageInfo.getStartRow(),pageNo,pageInfo.getPages(),list);
		
	}

	
	
	@Override
	public int selectSum(ServerAlertRuleDTO dto) {
		return serverAlertRuleMapper.selectSum(dto);
	}
	
	@Override
	public void updateAlertTime(AlertInfoUpdate aip) {
		int effects = serverAlertRuleMapper.updateAlertTime(aip);
		if(effects < 1) {
			throw new RuntimeException("未匹配到服务中对应的监控指标，请确认参数正确");
		}
	}
}
