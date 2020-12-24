package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.log.pojo.SpPersonneDTO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.mapper.SpPersonneMapper;
import com.mhtech.platform.msrv.sharing.dao.model.SpPersonne;
import com.mhtech.platform.msrv.sharing.service.SpPersonneService;

@Service
public class SpPersonneServiceImpl implements SpPersonneService {

	@Autowired
	private SpPersonneMapper spPersonneMapper;

	@Override
	public int remove(long... keys) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int save(List<T> entities) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T findEntityByKey(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> int modifyEntityByKey(T entity) {
		// TODO Auto-generated method stub
		return spPersonneMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public <T, P extends IPage> List<T> listPageable(P param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpPersonne> listSpPersonne() {

		return spPersonneMapper.listSpPersonne();
	}

	@Override
	public Page selectSpPersonne(SpPersonneDTO spPersonneDTO) {
		// 分页
		PageHelper.startPage(spPersonneDTO.getPageNo(),
				spPersonneDTO.getPageSize());
		List<SpPersonne> listSpPersonne = spPersonneMapper.listSpPersonne();
		// 分页的数据
		PageInfo<SpPersonne> pageInfo = new PageInfo<>(listSpPersonne);
		int total = 0;
		int count = spPersonneMapper.selectSum();
		if (count > 0) {
			total = count;
		}
		int pageNo = spPersonneDTO.getPageNo();// 页码

		Page page = new Page(spPersonneDTO.getPageSize(), total,
				pageInfo.getStartRow(), pageNo, pageInfo.getPages(),
				listSpPersonne);
		return page;

	}

	@Override
	public int insertSelective(SpPersonne spPersonne) {
		// TODO Auto-generated method stub
		return spPersonneMapper.insertSelective(spPersonne);
	}

	@Override
	public List<SpPersonne> listByCodes(List<String> codes) {
		return spPersonneMapper.listByCodes(codes);
	}
}
