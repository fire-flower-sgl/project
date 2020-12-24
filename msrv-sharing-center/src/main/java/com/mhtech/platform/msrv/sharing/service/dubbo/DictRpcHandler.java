package com.mhtech.platform.msrv.sharing.service.dubbo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.mhtech.platform.msrv.monitor.service.IDictRpcCaller;
import com.mhtech.platform.msrv.pojo.DictTypeSearchParam;
import com.mhtech.platform.msrv.pojo.DictTypeVO;
import com.mhtech.platform.msrv.pojo.DictVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.sharing.dao.mapper.DictTypeMapper;
import com.mhtech.platform.msrv.sharing.dao.model.DictType;

/***
 * @see IDictRpcCaller
 * @author GM
 */
@Service("dictRpcHandler")
public class DictRpcHandler implements IDictRpcCaller {

	@Autowired
	private DictTypeMapper dictTypeMapper;
	
	@Override
	public List<DictVO> listTypeValue(String type) {
		return dictTypeMapper.listTypeValue(type);
	}

	@Override
	public Page<DictTypeVO> listType(DictTypeSearchParam param) {
		PageHelper.startPage(param.getPageNo(), param.getPageSize(),
				param.getOrderBy());
		com.github.pagehelper.Page<DictType> page = (com.github.pagehelper.Page<DictType>) dictTypeMapper.listDictType(param);
		Page<DictTypeVO> vo = new Page<DictTypeVO>();
		vo.setPageNo(page.getPageNum());
		vo.setPageSize(page.getPageSize());
		vo.setTotalCount((int) page.getTotal());
		vo.setData(page.stream().map(d -> {
			DictTypeVO v = new DictTypeVO();
			v.setCode(d.getCode());
			v.setDictType(d.getDictType());
			v.setIsDeleted(d.getIsDeleted());
			v.setMemo(d.getMemo());
			return v;
		}).collect(Collectors.toList()));
		return vo;
	}
}
