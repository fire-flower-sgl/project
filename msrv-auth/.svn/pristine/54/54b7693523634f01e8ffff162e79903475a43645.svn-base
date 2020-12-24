package com.mhtech.platform.msrv.auth.service.dubbo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageInfo;
import com.mhtech.platform.msrv.auth.bean.pojo.PowerSearchDTO;
import com.mhtech.platform.msrv.auth.dao.mapper.SysPowerMapper;
import com.mhtech.platform.msrv.auth.dao.model.SysPower;
import com.mhtech.platform.msrv.auth.service.IPowerService;
import com.mhtech.platform.msrv.auth.service.IPrivilegeService;

import com.mhtech.platform.msrv.pojo.UserPowerVO;
import com.mhtech.platform.msrv.pojo.UserRoleDTO;
import com.mhtech.platform.msrv.pojo.UserVO;
import com.mhtech.platform.msrv.pojo.HtmlBtnVO;
import com.mhtech.platform.msrv.pojo.HtmlMenuPowerVo;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.RoleDTO;
import com.mhtech.platform.msrv.pojo.RolePowerDTO;
import com.mhtech.platform.msrv.pojo.RoleVO;
import com.mhtech.platform.msrv.pojo.SpePowerDTO;
import com.mhtech.platform.msrv.pojo.SpePowerVO;
import com.mhtech.platform.msrv.pojo.UserAccountDTO;
import com.mhtech.platform.msrv.pojo.UserDTO;
import com.mhtech.platform.msrv.pojo.UserPowerDTO;

@Service("iPowerService")
public class SysPowerServiceImpl implements IPowerService {

	@Autowired
	private SysPowerMapper sysPowerMapper;
	@Autowired
	private IPrivilegeService iPrivilegeService;
	

	@Override
	public List<UserPowerVO> listPower() {
		PowerSearchDTO search = new PowerSearchDTO();
		List<SysPower> powerList = sysPowerMapper.selectPowerList(search);
		return CollectionUtils.isEmpty(powerList) ? Collections.emptyList() : convert2PowerVOs(powerList);
	}
	
	private List<UserPowerVO> convert2PowerVOs(List<SysPower> powerList) {
		List<UserPowerVO> list = new ArrayList<UserPowerVO>();
		for (SysPower sysPower : powerList) {
			list.add(createVO(sysPower));
		}
		return list;
	}
	
	private UserPowerVO createVO(SysPower sysPower) {
		UserPowerVO vo = new UserPowerVO();
		vo.setPowerModule(sysPower.getPowerModule());
		vo.setPowerName(sysPower.getPowerName());
		vo.setPowerNum(sysPower.getPowerNum());
		vo.setPowerFather(sysPower.getPowerFather());
		vo.setPowerType(sysPower.getPowerType());
		vo.setRemoteUrl(sysPower.getRemoteUrl());
		vo.setSort(sysPower.getSort());
		vo.setUrl(sysPower.getUrl());
		return vo;
	}

	@Override
	public List<UserPowerVO> selectPowerNumUrl(UserPowerDTO userPowerDTO) {
		
		List<UserPowerVO> listPowerVo=new ArrayList<>();	
		List<SysPower> powerList = sysPowerMapper.selectPowerNumUrl(userPowerDTO.getUserCode());
		
		for (int i = 0; i < powerList.size(); i++) {	
			//url为空的排除掉
			if( powerList.get(i)!=null && !StringUtils.isEmpty(powerList.get(i).getUrl())) {
                //往listPowerVo里面放PowerNum与url
				listPowerVo.add(new UserPowerVO(powerList.get(i).getPowerNum(),powerList.get(i).getUrl()));
			}
		}		
		return listPowerVo;
	}

	@Override
	public Page listSysPower(UserPowerDTO userPowerDTO) {

		return iPrivilegeService.listSysPower(userPowerDTO);
	}

	@Override
	public boolean findSysPowerOne(RoleDTO reDto) {
		return iPrivilegeService.findSysPowerOne(reDto);
	}

	@Override
	public Page listRole(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.listRole(roleDTO);
	}

	@Override
	public List<UserPowerVO> listDataPower() {
		// TODO Auto-generated method stub
		return iPrivilegeService.listDataPower();
	}

	@Override
	public SpePowerVO selectSpePower(SpePowerDTO spePowerDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.selectSpePower(spePowerDTO);
	}

	@Override
	public UserVO selectUser(UserDTO userDTO) {
		
		return iPrivilegeService.selectByPrimaryKey(userDTO);
	}

	@Override
	public int insertUserAccount(UserAccountDTO record) {
		// TODO Auto-generated method stub
		return iPrivilegeService.insertUserAccount(record);
	}

	@Override
	public int insertRole(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.insertRole(roleDTO);
	}

	@Override
	public int deleteRole(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.deleteRole(roleDTO);
	}

	@Override
	public RoleVO selectRoleId(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.selectRoleId(roleDTO);
	}

	@Override
	public int updateRoleId(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.updateRoleId(roleDTO);
	}

	@Override
	public List<UserPowerVO> listRolePower(RoleDTO reDto) {
		// TODO Auto-generated method stub
		return iPrivilegeService.listRolePower(reDto);
	}

	@Override
	public List<HtmlBtnVO> listMenuHtmlBtn() {
		// TODO Auto-generated method stub
		return iPrivilegeService.listMenuHtmlBtn();
	}

	@Override
	public int deleteRoleAndRower(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.deleteRoleAndRower(roleDTO);
	}

	@Override
	public UserPowerVO selectfingPower(UserPowerDTO userPowerDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.selectfingPower(userPowerDTO);
	}

	@Override
	public int updataSysPowerId(UserPowerDTO userPowerDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.updataSysPowerId(userPowerDTO);
	}

	@Override
	public int insertPower(UserPowerDTO userPowerDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.insertPower(userPowerDTO);
	}

	@Override
	public int delectPowerNumPower(UserPowerDTO userPowerDTO) {
		// TODO Auto-generated method stub
		return iPrivilegeService.delectPowerNumPower(userPowerDTO);
	}

	@Override
	public int insertRolePowerAll(List<RolePowerDTO> record) {
		// TODO Auto-generated method stub
		return iPrivilegeService.insertAll(record);
	}

	@Override
	public int updataListRolePower(List<RolePowerDTO> record, RoleDTO roleDTO) {
		
		return iPrivilegeService.updataListRolePower(record, roleDTO);
	}

	@Override
	public boolean powerNum(String powerNum) {
		// TODO Auto-generated method stub
		return iPrivilegeService.powerNum(powerNum);
	}


	@Override
	public int[] updataUserRole(String userCode, List<UserRoleDTO> list) {
		// TODO Auto-generated method stub
		return iPrivilegeService.updataUserRole(userCode, list);
	}

	@Override
	public List<String> selectRoleNumsByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return iPrivilegeService.selectRoleNumsByUserCode(userCode);
	}

	@Override
	public int insertSysRolePower(RolePowerDTO record) {
		// TODO Auto-generated method stub
		return iPrivilegeService.insertSysRolePower(record);
	}

	@Override
	public List<UserPowerVO> selectHtmlMenu(String userCode) {
		 List<SysPower> list = iPrivilegeService.selectHtmlMenu(userCode);
		 List<UserPowerVO> listHtmlMenu=new ArrayList<>();
		 list.forEach(sp->{
			 UserPowerVO userPowerVO = new UserPowerVO();
			 userPowerVO.setPowerNum(sp.getPowerNum());
			 listHtmlMenu.add(userPowerVO);
		 });
		 
		// TODO Auto-generated method stub
		return listHtmlMenu;
	}

	@Override
	public List<HtmlBtnVO> listBtnSpecial(UserPowerDTO userPowerDTO) {
		// TODO Auto-generated method stub
		List<SysPower> listBtnSpecial = iPrivilegeService.listBtnSpecial(userPowerDTO);
		List<HtmlBtnVO> list=new ArrayList<>();
		listBtnSpecial.forEach(sp->{
			HtmlBtnVO dm=new HtmlBtnVO();
			dm.setPowerNum(sp.getPowerNum());
			dm.setPowerUrl(sp.getUrl());
			list.add(dm);
		});
		
		return list;
	}

	@Override
	public List<HtmlMenuPowerVo> listPower(String userCode) {
		// TODO Auto-generated method stub
		return iPrivilegeService.listPower(userCode);
	}

	@Override
	public List<HtmlMenuPowerVo> listAllPower(String userCode) {
		// TODO Auto-generated method stub
		return iPrivilegeService.listAllPower(userCode);
	}

	



	
}
