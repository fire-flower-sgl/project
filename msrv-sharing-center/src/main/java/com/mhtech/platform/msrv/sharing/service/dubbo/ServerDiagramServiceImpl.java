package com.mhtech.platform.msrv.sharing.service.dubbo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mhtech.platform.msrv.monitor.service.IServerDiagramService;
import com.mhtech.platform.msrv.pojo.AlertLogVO;
import com.mhtech.platform.msrv.pojo.AlertRuleVO;
import com.mhtech.platform.msrv.pojo.ServerAdminVO;
import com.mhtech.platform.msrv.pojo.ServerDiagramLevelVO;
import com.mhtech.platform.msrv.pojo.ServerDiagramVO;
import com.mhtech.platform.msrv.pojo.ServerDiagramsVO;
import com.mhtech.platform.msrv.sharing.dao.mapper.AlertLogMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerAdminMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerAlertRuleMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerDiagramMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerNotifyLogMapper;
import com.mhtech.platform.msrv.sharing.dao.model.ServerAdmin;
import com.mhtech.platform.msrv.sharing.dao.model.ServerAlertRule;
import com.mhtech.platform.msrv.sharing.dao.model.ServerDiagram;
import com.mhtech.platform.msrv.sharing.dao.model.ServerNotifyLog;
import com.mhtech.platform.msrv.sharing.service.impl.BaseServiceImpl;

@Service("serverDiagramService")
public class ServerDiagramServiceImpl extends BaseServiceImpl implements IServerDiagramService{
	
	@Autowired
	private ServerDiagramMapper diagraMapper;
	
	@Autowired
	private ServerNotifyLogMapper notifyLogMapper;
	
	@Autowired
	private ServerAlertRuleMapper alertRuleMapper;
	
	@Autowired
	private AlertLogMapper alertLogMapper;
	
	@Autowired
	private ServerAdminMapper serverAdminMapper;
	
	@Value("${alert.state.ok}")
	private Short noAlartState;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	@Override
	protected void setGenericMapper() {
		super.genericMapper = diagraMapper;
		
	}

	@Override
	public ServerDiagramsVO findServerDiagram(Long businessId) {
		ServerDiagramsVO datas= new ServerDiagramsVO(); 
		List<ServerDiagramVO> dataList =  new ArrayList<ServerDiagramVO>();
		List<ServerDiagramLevelVO> dataLevelList =  new ArrayList<ServerDiagramLevelVO>();
		List<ServerDiagram> list = diagraMapper.selectEntities(businessId);
		List<Long> serverIdList = new ArrayList<Long>();
		if(!CollectionUtils.isEmpty(list)) {
			list.forEach(data ->{
				ServerDiagramVO temp = new ServerDiagramVO(data.getId().toString(), businessId.toString(), data.getServerId().toString(), data.getServerDesc(), data.getNextId(), data.getWorkDesc());
				temp.setStatus(data.getStatus());
				temp.setStatusName(data.getStatusName());
				temp.setNextName(data.getNextName());
				temp.setServerName(data.getServerDesc());
				int index = list.indexOf(data);
				if(data.getNextId()!=null) {
					//按照id降序，最后一个为结束节点，倒数第二个为前端页面结束节点
					if (index==list.size()-2) {
						temp.setEnd(true);
					}else {
						temp.setEnd(false);
					}
					dataList.add(temp);
					//加入指向节点
					Long nextId = data.getNextId();
					if(!serverIdList.contains(nextId)) {
						 serverIdList.add(nextId);
						 ServerDiagramLevelVO dLevelVO = new ServerDiagramLevelVO();
						 dLevelVO.setServerId(nextId.toString());
						 dLevelVO.setLevel(findLevel(nextId));
						 dLevelVO.setServerDesc(data.getNextName());
						 dataLevelList.add(dLevelVO);
					}
				}
				Long serverId = data.getServerId();
				//加入节点
				if(!serverIdList.contains(serverId)) {
					 serverIdList.add(serverId);
					 ServerDiagramLevelVO dLevelVO = new ServerDiagramLevelVO();
					 dLevelVO.setServerId(serverId.toString());
					 dLevelVO.setLevel(findLevel(serverId));
					 dLevelVO.setServerDesc(data.getServerDesc());
					 dataLevelList.add(dLevelVO);
				}
			});
			datas.setDiagramList(dataList);
			datas.setDiagramLevelList(dataLevelList);
		}
		return datas;
	}
	
	public Short findLevel(Long serverId) {
		//查询当天警告通知 
		ServerNotifyLog notifyLog = notifyLogMapper.findByServerId(serverId);		
		if( notifyLog != null && notifyLog.getId() != null ) {
			//查询对于notifyId的条数
			AlertLogVO alvo = new AlertLogVO();
			alvo.setNotifyId(notifyLog.getId());
			int count = alertLogMapper.count(alvo);
			//查询该服务的通知规则
			AlertRuleVO arv = new AlertRuleVO();
			arv.setServerId(serverId);
			arv.setParamName(notifyLog.getParamName());
			List<ServerAlertRule> selectEntities = alertRuleMapper.selectEntities(arv);
			for (ServerAlertRule rule : selectEntities) {
				//比较得到告警level值
				if(count>=rule.getAlertLimit()) {
					return rule.getLevel();		
				}
			}
		}
		return noAlartState;
	}

	@Override
	public void delByBusinessId(Long businessId) {
		diagraMapper.delByBusinessId(businessId);
	}

	@Override
	public ServerAdminVO getServerList() {
		List<ServerAdmin> saList = serverAdminMapper.getServerList();
		List<ServerAdminVO> dataList = new ArrayList<ServerAdminVO>();
		saList.forEach(sa -> {
			ServerAdminVO saVo = new ServerAdminVO(sa.getId().toString(),sa.getServerName());
			dataList.add(saVo);
		});
		return new ServerAdminVO(dataList);
	}

	@Override
	public ServerAdminVO getServerListForTrue() {
		List<ServerAdmin> saList = serverAdminMapper.getServerListForTrue();
		List<ServerAdminVO> dataList = new ArrayList<ServerAdminVO>();
		saList.forEach(sa -> {
			ServerAdminVO saVo = new ServerAdminVO(sa.getId().toString(),sa.getServerName());
			dataList.add(saVo);
		});
		return new ServerAdminVO(dataList);
	}

	@Override
	public List<ServerDiagramVO> findServerDiagramByServerId(Long ServerId) {
		return null;
	}

}
