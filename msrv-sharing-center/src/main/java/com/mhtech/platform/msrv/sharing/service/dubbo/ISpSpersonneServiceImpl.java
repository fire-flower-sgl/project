package com.mhtech.platform.msrv.sharing.service.dubbo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.log.pojo.SpPersonneDTO;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.monitor.service.ISpSpersonneService;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.SpPersonneVO;
import com.mhtech.platform.msrv.sharing.dao.model.SpPersonne;
import com.mhtech.platform.msrv.sharing.service.SpPersonneService;

@Service("iSpSpersonneService")
public class ISpSpersonneServiceImpl implements ISpSpersonneService {

	@Autowired
	private SpPersonneService spPersonneService;
	@Autowired
	private IworkService iworkService;
	
	
	@Override
	public List<SpPersonneVO> listSpPersonne() {
		// TODO Auto-generated method stub
		List<SpPersonne> listSpPersonne = spPersonneService.listSpPersonne();
		List<SpPersonneVO> list=new ArrayList<SpPersonneVO>();
		listSpPersonne.forEach(sp->{
			list.add(new SpPersonneVO(sp.getPname(), sp.getMobileno(), sp.getCreateDate(), sp.getShiftId(),sp.getEmail()));
		});
		return list;
	}


	@Override
	public Page selectSpPersonne(SpPersonneDTO spPersonneDTO) {
		return spPersonneService.selectSpPersonne(spPersonneDTO);
	}


	@Override
	public int add(SpPersonneDTO dto) {
	
		SpPersonne sp=new SpPersonne(iworkService.getNextId().toString(),dto.getPcode(),dto.getPname(),dto.getMobileno(),
				dto.getUnitId(),dto.getUnitName(),dto.getCreateDate(),dto.getValidFlag(),dto.getShiftId(),dto.getIsLocalton()
				,dto.getEmail());
	
		return spPersonneService.insertSelective(sp);
	}


	@Override
	public int update(SpPersonneDTO dto) {
		SpPersonne sp=new SpPersonne(dto.getId(),dto.getPcode(),dto.getPname(),dto.getMobileno(),
				dto.getUnitId(),dto.getUnitName(),dto.getCreateDate(),dto.getValidFlag(),dto.getShiftId(),
				dto.getIsLocalton(),dto.getEmail());
		return spPersonneService.modifyEntityByKey(sp);
	}

}
