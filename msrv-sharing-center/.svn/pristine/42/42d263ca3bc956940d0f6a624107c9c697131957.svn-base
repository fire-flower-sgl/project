package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.log.pojo.ParamAlertDTO;
import com.mhtech.platform.log.pojo.ParamAlertSelectVO;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.IParamAlertService;
import com.mhtech.platform.msrv.pojo.MonitorStatus.ParamAlertStatus;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.ParamAlertInfo;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerParamAlert;
import com.mhtech.platform.msrv.sharing.dao.mapper.ParamAlertMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ParamAlertTypeMapper;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerAdminMapper;
import com.mhtech.platform.msrv.sharing.dao.model.ParamAlert;

@Service("iParamAlertService")
public class ParamAlertServiceImpl implements IParamAlertService {

	@Autowired
	private ParamAlertMapper paramAlertMapper;
	@Autowired
	private IworkService iworkService;
	@Autowired
	private ServerAdminMapper serverAdminMapper;
	@Autowired
	private ParamAlertTypeMapper paramAlertTypeMapper;
	
	@Override
	public String addParamAlert(ParamAlertInfo pai) {
		
		ParamAlert paramAlert=null;
		
		//数据校验  server_code        type_code 是否存在
		ServerAdminInfo info = serverAdminMapper.findServerDetail(pai.getServerId());

		boolean type = paramAlertTypeMapper.findAlertType(pai.getAlertCode());
		if (info.getId().isEmpty()||type) {
			paramAlert=new ParamAlert(iworkService.getNextId(), pai.getServerId(),pai.getParamName(),
					pai.getParamAlias(),pai.getAlertValue(),pai.getAlertCode(),pai.getAlertType(),pai.getStatus()
					,pai.getCreatedTime());
			//添加
			int selective = paramAlertMapper.insertSelective(paramAlert);
			return selective>0? "新增成功":"新增失败" ;
		}	
		return "数据校验失败:未找到serverId/typeCode";
	}

	@Override
	public List<ServerParamAlert> listAlerts4Server(long serverId,
			final ParamAlertStatus pas) {
		List<ServerParamAlert> spas = new ArrayList<ServerParamAlert>();
		List<ParamAlert> paramAlerts = paramAlertMapper.selectByPrimaryServerId(serverId);
		if(CollectionUtils.isEmpty(paramAlerts)) {
			return Collections.emptyList();
		}
		paramAlerts.forEach(paramAlert -> {
			if(ParamAlertStatus.valueOf(paramAlert.getStatus()) == pas) {
				ServerParamAlert spa = new ServerParamAlert();
				spa.setId(paramAlert.getId().toString());
				spa.setServerId(paramAlert.getServerId().toString());
				spa.setAlertValue(paramAlert.getAlterValue());
				spa.setParamAlias(paramAlert.getParamAlias());
				spa.setParamName(paramAlert.getParamName());
				spa.setStatus(pas.getAlias());
				spas.add(spa);
			}
		});
		return CollectionUtils.isEmpty(spas) ? Collections.emptyList()
				: spas;
	}

	@Override
	public void updateAlertingStatus(ParamAlertStatus pas) {

	}

	@Override
	public ServerParamAlert getParamAlert(Long serverId, String paramName, ParamAlertStatus pas) {
		List<ParamAlert> paramAlerts = paramAlertMapper.selectByPrimaryServerId(serverId);
		if(CollectionUtils.isEmpty(paramAlerts)) {
			return null;
		}
		ServerParamAlert spa = new ServerParamAlert();
		paramAlerts.forEach(paramAlert -> {
			if(ParamAlertStatus.valueOf(paramAlert.getStatus()) == pas && paramAlert.getParamName().equals(paramName)) {
				spa.setId(paramAlert.getId().toString());
				spa.setServerId(paramAlert.getServerId().toString());
				spa.setAlertValue(paramAlert.getAlterValue());
				spa.setParamAlias(paramAlert.getParamAlias());
				spa.setParamName(paramAlert.getParamName());
				spa.setStatus(pas.getAlias());
				spa.setServerType(paramAlert.getServerType());
			}
		});
		return spa;
	}

	

	@Override
	public int selectSum(ParamAlertDTO dto) {
		// TODO Auto-generated method stub
		return paramAlertMapper.selectSum(dto);
	}

	@Override
	public Page selectParamAlert(ParamAlertDTO dto) {
		PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
		List<ParamAlert> alert = paramAlertMapper.selectParamAlert(dto);
		
		List<ParamAlertSelectVO> list=new ArrayList<>();
		alert.forEach(sp->{
			list.add(new ParamAlertSelectVO(sp.getId(),sp.getServerId(),sp.getParamName(),sp.getParamAlias(),
					sp.getAlterValue(),sp.getAlertCode(),sp.getAlertType(),sp.getStatus(),sp.getCreatedTime()));
		});
		
		//分页的数据
	  	PageInfo<ParamAlertSelectVO> pageInfo = new PageInfo<>(list);
	  	int total=0;
	  	int count = paramAlertMapper.selectSum(dto);
	  	if (count>0) {
	  		total=count;
		}
	  	int pageNo = dto.getPageNo();//页码
	  	Page page= new Page(dto.getPageSize(),total,pageInfo.getStartRow(),pageNo,pageInfo.getPages(),list);
		return page;
	
	}
}
