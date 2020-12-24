package com.mhtech.platform.msrv.sharing.service.dubbo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.msrv.monitor.service.IHardwareTypeService;
import com.mhtech.platform.msrv.pojo.HardwareTypeDTO;
import com.mhtech.platform.msrv.pojo.HardwareTypeVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.mapper.HardwareTypeMapper;
import com.mhtech.platform.msrv.sharing.dao.model.HardwareType;
import com.mhtech.platform.msrv.sharing.service.impl.BaseServiceImpl;

@Service("hardwareTypeService")
public class HardwearTypeServiceImpl extends BaseServiceImpl implements IHardwareTypeService {
	
	@Autowired
	private HardwareTypeMapper hardwareTypeMapper;

	@PostConstruct
	@Override
	protected void setGenericMapper() {
		super.genericMapper = hardwareTypeMapper;
		
	}

	@Override
	public Page queryPage(HardwareTypeDTO mp) {
		PageHelper.startPage(mp.getPageNo(),mp.getPageSize());
		List<HardwareType> list = hardwareTypeMapper.selectEntities(mp);
		PageInfo<HardwareType> pageinfo = new PageInfo<HardwareType>(list);
		List<HardwareTypeVO> listVO = new ArrayList<HardwareTypeVO>();
		if(!CollectionUtils.isEmpty(pageinfo.getList())) {
			for (HardwareType hw : pageinfo.getList()) {
				HardwareTypeVO ht = new HardwareTypeVO(hw.getTypeCode(), hw.getTypeAlias(), hw.getUnit(), hw.getMemo());
				listVO.add(ht);
			}
		}
		Page page= new Page(pageinfo.getPageSize(),Integer.parseInt(String.valueOf(pageinfo.getTotal())),pageinfo.getStartRow(),mp.getPageNo(),pageinfo.getPages(),listVO);
		return page;
	}

	@Override
	public int remove(String typeCode) {
		return hardwareTypeMapper.deleteByPrimaryKey(typeCode);
	}

	@Override
	public boolean isExist(HardwareTypeDTO mt) {
		return hardwareTypeMapper.isExist(mt)>0;
	}


}
