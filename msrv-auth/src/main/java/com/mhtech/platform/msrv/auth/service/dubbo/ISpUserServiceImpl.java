package com.mhtech.platform.msrv.auth.service.dubbo;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhtech.platform.msrv.auth.dao.mapper.SpUserMapper;
import com.mhtech.platform.msrv.auth.dao.model.SpUser;
import com.mhtech.platform.msrv.auth.service.ISpUserService;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.pojo.SpUserVO;

@Service("iSpUserService")
public class ISpUserServiceImpl implements ISpUserService {
	
	@Autowired
	private SpUserMapper spUserMapper;
	@Autowired
	private IworkService iworkService;
	

	@Override
	public int insertSelective(SpUserVO record) {
		SpUser sp=new SpUser(iworkService.getNextId(),record.getUserCode(),record.getName(),
				record.getPassword(),record.getCompanyCode(),record.getOrgCode(),record.getEmail(),
				record.getPhone(),record.getMultiLogin(),record.getUserType(),record.getUpdateUser(),
				record.getUpdateTime());
		return spUserMapper.insertSelective(sp);
	}



	@Override
	public int delete(Long id) {
		return spUserMapper.deleteByPrimaryKey(id);
	}



	@Override
	public SpUserVO select(Long id) {
		Date d=new Date();
		SpUser key = spUserMapper.selectByPrimaryKey(id);
		SpUserVO vo=new SpUserVO(key.getId(),key.getUserCode(),key.getName(),key.getPassword(),key.getCompanyCode(),
				key.getOrgCode(),key.getEmail(),key.getPhone(),key.getMultiLogin(),key.getUserType(),key.getUpdateUser()
				,d);
		return vo;
	}



	@Override
	public int updateByPrimaryKeySelective(SpUserVO record) {
		SpUser sp=new SpUser(record.getId(),record.getUserCode(),record.getName(),
				record.getPassword(),record.getCompanyCode(),record.getOrgCode(),record.getEmail(),
				record.getPhone(),record.getMultiLogin(),record.getUserType(),record.getUpdateUser(),
				record.getUpdateTime());
		return spUserMapper.updateByPrimaryKeySelective(sp);
	}



	@Override
	public SpUserVO select(String name, String password) {
		SpUser key = spUserMapper.select(name, password);
		
		SpUserVO vo=new SpUserVO(key.getId(),key.getUserCode(),key.getName(),key.getPassword(),key.getCompanyCode(),
				key.getOrgCode(),key.getEmail(),key.getPhone(),key.getMultiLogin(),key.getUserType(),key.getUpdateUser()
				,key.getUpdateTime());
		
		return vo;
	}



	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.auth.service.ISpUserService#selectAllUser(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> selectAllUser(String name) {
		// TODO 自动生成的方法存根
		return spUserMapper.selectAllUser(name);
	}



	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.auth.service.ISpUserService#selectUserInfo(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> selectUserInfo(String userCode) {
		return spUserMapper.selectUserInfo(userCode);
	}



	@Override
	public int sign(String userCode, String password) {
		// TODO Auto-generated method stub
		return spUserMapper.sign(userCode, password);
	}



	
	
}
