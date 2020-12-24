package com.mhtech.platform.msrv.sharing.service.dubbo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.log.pojo.ServerChkRuleDTO;
import com.mhtech.platform.log.pojo.ServerChkRuleVO;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.auth.service.ServerChkRuleService;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.mapper.ServerChkRuleMapper;
import com.mhtech.platform.msrv.sharing.dao.model.ServerChkRuleWithBLOBs;
import com.mhtech.platform.msrv.sharing.service.impl.BaseServiceImpl;

@Service("serverChkRuleService")
public class ServerChkRuleServiceImpl extends BaseServiceImpl implements ServerChkRuleService{
	
	@Autowired
	ServerChkRuleMapper serverChkRuleMapper;
	@Autowired
	IworkService iwork;

	@Override
	protected void setGenericMapper() {
		super.genericMapper = serverChkRuleMapper;
		
	}
	
	@Override
	public Integer insert(ServerChkRuleDTO serverChkRuleDTO) {
		serverChkRuleDTO.setId(iwork.getNextId());
		serverChkRuleDTO.setStatus(1);
		return serverChkRuleMapper.insertSelective(serverChkRuleDTO);
	}

	@Override
	public Integer modifyById(ServerChkRuleDTO scrDt) {
		return serverChkRuleMapper.updateByPrimaryKey(scrDt);
	}

	@Override
	public Integer delById(ServerChkRuleDTO scrDt) {
		return serverChkRuleMapper.deleteByPrimaryKey(scrDt);
	}

	@Override
	public Page queryPage(ServerChkRuleDTO scrDt) {
		PageHelper.startPage(scrDt.getPageNo(),scrDt.getPageSize());
		List<ServerChkRuleWithBLOBs> list = serverChkRuleMapper.findAll(scrDt);
		PageInfo<ServerChkRuleWithBLOBs> pageinfo = new PageInfo<ServerChkRuleWithBLOBs>(list);
		List<ServerChkRuleVO> dataList = new ArrayList<ServerChkRuleVO>();
		List<ServerChkRuleWithBLOBs> list2 = pageinfo.getList();
		for (int i = 0; i < list2.size(); i++) {
			ServerChkRuleVO scrVo = objToVoObj(list2.get(i));
			dataList.add(scrVo);
		}
		Page page= new Page(pageinfo.getPageSize(),Integer.parseInt(String.valueOf(pageinfo.getTotal())),pageinfo.getStartRow(),scrDt.getPageNo(),pageinfo.getPages(),dataList);
		return page;
	}

	private ServerChkRuleVO objToVoObj(ServerChkRuleWithBLOBs sBloBs) {
		ServerChkRuleVO serverChkRuleVO = new ServerChkRuleVO(sBloBs.getId(), sBloBs.getServerId(), sBloBs.getMethod().intValue(), 
				sBloBs.getUri(), sBloBs.getPort()==null?null:sBloBs.getPort().intValue(), sBloBs.getStatus().intValue(), sBloBs.getCreatedTime(), 
				sBloBs.getHttpHeaders(), sBloBs.getHttpRequestBody(),sBloBs.getJsonFieldCheck());
		return serverChkRuleVO;
	}

	@Override
	public List<ServerChkRuleVO> queryList() {
		List<ServerChkRuleWithBLOBs> list = serverChkRuleMapper.findList();
		List<ServerChkRuleVO> dataList = new ArrayList<ServerChkRuleVO>();
		for (int i = 0; i < list.size(); i++) {
			ServerChkRuleVO scrVo = objToVoObj(list.get(i));
			dataList.add(scrVo);
		}
		return dataList;
	}


}
