package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.IAlertNotifyTmplService;
import com.mhtech.platform.msrv.pojo.AlertNotifyTmpl;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.sharing.dao.mapper.AlertNotifyTmplMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerAdminMapper;

@Service("alertNotifyTmplService")
public class AlertNotifyTmplServiceImpl implements IAlertNotifyTmplService {

	@Autowired
	private AlertNotifyTmplMapper alertNotifyTmplMapper;
	@Autowired
	private IworkService iworkService;
	@Autowired
	private ServerAdminMapper serverAdminMapper;
	
	@Override
	public AlertNotifyTmpl getServerUsingNotifyTmpl(long serverId) {
		AlertNotifyTmpl condition = new AlertNotifyTmpl();
		condition.setServerId(serverId);
		List<AlertNotifyTmpl> tmpls = alertNotifyTmplMapper.selectEntities(condition);
		if(!CollectionUtils.isEmpty(tmpls) && tmpls.size() == 1) {
			return tmpls.get(0);
		}
		throw new RuntimeException("未找到通知模板");
	}

	@Override
	public String addServerInfo(AlertNotifyTmpl server) {
		
		ServerAdminInfo info = serverAdminMapper.findServerDetail(server.getServerId());
		
		if (info.getId()!=null) {
			server.setId(iworkService.getNextId());
			int selective = alertNotifyTmplMapper.insertSelective(server);
			if (selective>0) {
				return "新增成功";
			}
		} 
		return "服务编码无效";
	}
}
