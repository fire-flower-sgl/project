package com.mhtech.platform.msrv.sharing.service.impl;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.IServerNotifyRuleService;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.PropertyVO;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerNotifyRule;
import com.mhtech.platform.msrv.pojo.ServerNotifyRuleVO;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerAdminMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerNotifyRuleMapper;
import com.mhtech.platform.msrv.sharing.dao.model.Property;

@Service("serverNotifyRuleService")
public class ServerNotifyRuleServiceImpl extends BaseServiceImpl implements IServerNotifyRuleService {

	@Autowired
	private ServerNotifyRuleMapper serverNotifyRuleMapper;
	@Autowired
	private IworkService iworkService;
	@Autowired
	private ServerAdminMapper serverAdminMapper;
	
	@PostConstruct
	@Override
	protected void setGenericMapper() {
		super.genericMapper = serverNotifyRuleMapper;
		
	}
	
	
	@Override
	public ServerNotifyRule getServerNotifyRule(long serverId) {
		ServerNotifyRule condition = new ServerNotifyRule();
		condition.setServerId(serverId);
		List<ServerNotifyRule> rules = serverNotifyRuleMapper.selectEntities(condition);
		if(!CollectionUtils.isEmpty(rules) && rules.size() == 1) {
			return rules.get(0);
		}
		throw new RuntimeException("未找到服务预警通知规则");
	}

	@Override
	public String addServerInfo(ServerNotifyRule server) {
		
		ServerAdminInfo info = serverAdminMapper.findServerDetail(server.getServerId());

		//验证规则 <<  >>
		int rsTime=Integer.parseInt(server.getRecvStartTime());
		int reTime=Integer.parseInt(server.getRecvEndTime());
		int fsTime=Integer.parseInt(server.getRefuseStartTime());
		int feTime=Integer.parseInt(server.getRefuseEndTime());
		boolean sj=false;
		if (rsTime<fsTime&&reTime<fsTime) {
			sj=true;
		}else if (rsTime>feTime&&reTime>feTime) {
			sj=true;
		}else if (rsTime>fsTime&&reTime<feTime) {
			sj=true;
		}
		if (sj==true) {
			if (info.getId()!=null) {
				server.setId(iworkService.getNextId());
				int selective = serverNotifyRuleMapper.insertSelective(server);
				if (selective>0) {
					return "新增通知规则成功";
				}
			}
			return "服务编码无效";		
		}
		return "每日接收通知的时间段与每日拒收通知的时间段有冲突";
	}


	@Override
	public Page<ServerNotifyRuleVO> queryPage(ServerNotifyRule rule) {
		PageHelper.startPage(rule.getPageNo(),rule.getPageSize());
		List<com.mhtech.platform.msrv.sharing.dao.model.ServerNotifyRule> list = serverNotifyRuleMapper.selectEntities(rule);
		PageInfo<com.mhtech.platform.msrv.sharing.dao.model.ServerNotifyRule> pageinfo = new PageInfo<com.mhtech.platform.msrv.sharing.dao.model.ServerNotifyRule>(list);
		List<ServerNotifyRuleVO> dataList = new ArrayList<ServerNotifyRuleVO>();
		pageinfo.getList().forEach(rl ->{
			ServerNotifyRuleVO ruleVO = new ServerNotifyRuleVO(rl.getId(), rl.getServerId(), rl.getIsEnable(), rl.getRecvStartTime(), rl.getRecvEndTime(), rl.getRefuseStartTime(), rl.getRefuseEndTime());
			dataList.add(ruleVO);
		});
		Page page= new Page(pageinfo.getPageSize(),Integer.parseInt(String.valueOf(pageinfo.getTotal())),pageinfo.getStartRow(),rule.getPageNo(),pageinfo.getPages(),dataList);
		return page;
	}


	@Override
	public void update(ServerNotifyRule server) {
		ServerAdminInfo info = serverAdminMapper.findServerDetail(server.getServerId());

		//验证规则 <<  >>
		int rsTime=Integer.parseInt(server.getRecvStartTime());
		int reTime=Integer.parseInt(server.getRecvEndTime());
		int fsTime=Integer.parseInt(server.getRefuseStartTime());
		int feTime=Integer.parseInt(server.getRefuseEndTime());
		boolean sj=false;
		if (rsTime<fsTime&&reTime<fsTime) {
			sj=true;
		}else if (rsTime>feTime&&reTime>feTime) {
			sj=true;
		}else if (rsTime>fsTime&&reTime<feTime) {
			sj=true;
		}
		if (sj==true) {
			if (info!=null) {
				serverNotifyRuleMapper.updateByPrimaryKeySelective(server);
			}
			throw new RuntimeException("服务编码无效");
		}
		throw new RuntimeException("每日接收通知的时间段与每日拒收通知的时间段有冲突");		
	}


	@Override
	public ServerNotifyRuleVO getServerNotifyRuleById(Long id) {
		ServerNotifyRule condition = new ServerNotifyRule();
		condition.setId(id);
		List<com.mhtech.platform.msrv.sharing.dao.model.ServerNotifyRule> rules = serverNotifyRuleMapper.selectEntities(condition);
		if(!CollectionUtils.isEmpty(rules) && rules.size() == 1) {
			com.mhtech.platform.msrv.sharing.dao.model.ServerNotifyRule rl = rules.get(0);
			ServerNotifyRuleVO ruleVO = new ServerNotifyRuleVO(rl.getId(), rl.getServerId(), rl.getIsEnable(), rl.getRecvStartTime(), rl.getRecvEndTime(), rl.getRefuseStartTime(), rl.getRefuseEndTime());
			return ruleVO;
		}
		throw new RuntimeException("未找到服务预警通知规则");
	}
}
