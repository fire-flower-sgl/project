package com.mhtech.platform.msrv.auth.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.msrv.auth.bean.pojo.PowerSearchDTO;
import com.mhtech.platform.msrv.auth.bean.pojo.PowerVO;
import com.mhtech.platform.msrv.auth.bean.pojo.UserPowerSearchDTO;
import com.mhtech.platform.msrv.auth.dao.mapper.SpUserMapper;
import com.mhtech.platform.msrv.auth.dao.mapper.SysPowerMapper;
import com.mhtech.platform.msrv.auth.dao.mapper.SysRoleMapper;
import com.mhtech.platform.msrv.auth.dao.mapper.SysRolePowerMapper;
import com.mhtech.platform.msrv.auth.dao.mapper.SysSpePowerMapper;
import com.mhtech.platform.msrv.auth.dao.mapper.SysUserAccountMapper;
import com.mhtech.platform.msrv.auth.dao.mapper.SysUserRoleMapper;
import com.mhtech.platform.msrv.auth.dao.model.SpUser;
import com.mhtech.platform.msrv.auth.dao.model.SysPower;
import com.mhtech.platform.msrv.auth.dao.model.SysRole;
import com.mhtech.platform.msrv.auth.dao.model.SysRolePower;
import com.mhtech.platform.msrv.auth.dao.model.SysSpePower;
import com.mhtech.platform.msrv.auth.dao.model.SysUserAccount;
import com.mhtech.platform.msrv.auth.dao.model.SysUserRole;
import com.mhtech.platform.msrv.auth.exception.BizErrorCode;
import com.mhtech.platform.msrv.auth.exception.BizException;
import com.mhtech.platform.msrv.auth.service.IPrivilegeService;
import com.mhtech.platform.msrv.auth.service.IworkService;
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

@Service
public class PrivilegeServiceImpl implements IPrivilegeService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysUserRoleMapper usrRolMapper;
	@Autowired
	private SysRolePowerMapper rolPowerMapper;
	@Autowired
	private SysPowerMapper sysPowerMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysSpePowerMapper sysSpePower;
	@Autowired
	private SpUserMapper spUserMapper;
	@Autowired
	private SysUserAccountMapper sysUserAccountMapper;
	@Autowired
	private SysRolePowerMapper sysRolePowerMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private IworkService  iworkService;
	

	
	@Override
	public List<PowerVO> listUserPrivis(UserPowerSearchDTO ups) throws BizException {
		List<PowerVO> list = getUsrPowers(ups);
		setChildPower(list);
		list.sort(new Comparator<PowerVO>() {
			@Override
			public int compare(PowerVO o1, PowerVO o2) {
				return o1.getSort() - o2.getSort();
			}
		});
		return list;
	}
	
	/**
	 * 查询用户权限
	 * @param ups
	 * @return
	 */
	private List<PowerVO> getUsrPowers(UserPowerSearchDTO ups) {
		List<PowerVO> list = Collections.emptyList();
		try {
			List<String> roleNums = usrRolMapper.selectRoleNumsByUserCode(ups.getUserCode());
			if(CollectionUtils.isEmpty(roleNums)) {
				throw new BizException(BizErrorCode.NON_USER_ROLES);
			}
			List<String> powerNums = rolPowerMapper.selectPowerNumsByRoles(roleNums);
			if(CollectionUtils.isEmpty(powerNums)) {
				throw new BizException(BizErrorCode.NON_ROLE_POWERS);
			}
			PowerSearchDTO search = new PowerSearchDTO();
			search.setPowerNums(powerNums);
			search.setPowerTypes(ups.getPowerTypes());
			List<SysPower> powerList = sysPowerMapper.selectPowerList(search);
			if(CollectionUtils.isEmpty(powerList)) {
				throw new BizException(BizErrorCode.UNKNOWN);
			}
			list = convert2PowerVOs(powerList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return list;
	}
	
	private void setChildPower(List<PowerVO> list) {
		//从第一个位置查找父子节点
		int defaultIdx = 0;
		PowerVO vo = list.get(defaultIdx);
		setChildPower(list, vo);
	}
	
	/**
	 * 递归查找父节点，并将自身填充到其子节点下
	 * @param powerList
	 * @param child 当前执行的子节点（递归游标）
	 *  树形结构
	 */
	private void setChildPower(List<PowerVO> vos, PowerVO child) {
		for (int j = 0; j < vos.size(); j++) {
			PowerVO father = vos.get(j);
			if(father.getPowerNum().equals(child.getPowerFather())) {
				addChildPower(father, child);
				vos.remove(child);
				setChildPower(vos, vos.get(j));
			} else {
				//如果没有找到父节点，则遍历当前节点的子节点里面是否存在其父节点
				if(!CollectionUtils.isEmpty(father.getChildren())) {
					setChildPower(father.getChildren(), child);
				}
			}
		}
		//游标向下一位
		int idx = vos.indexOf(child);
		if(idx != -1 && idx != vos.size() - 1) {
			setChildPower(vos, vos.get(++idx));
		}
	}
	
	private void addChildPower(PowerVO father, PowerVO child) {
		if(CollectionUtils.isEmpty(father.getChildren())) {
			father.setChildren(Arrays.stream(new PowerVO[] { child }).collect(Collectors.toList()));
		} else {
			father.getChildren().add(child);
		}
	}
	
	private List<PowerVO> convert2PowerVOs(List<SysPower> powerList) {
		List<PowerVO> list = new ArrayList<PowerVO>();
		for (SysPower sysPower : powerList) {
			list.add(createVO(sysPower));
		}
		return list;
	}
	
	private PowerVO createVO(SysPower sysPower) {
		PowerVO vo = new PowerVO();
		vo.setPowerModule(sysPower.getPowerModule());
		vo.setPowerName(sysPower.getPowerName());
		vo.setPowerNum(sysPower.getPowerNum());
		vo.setPowerFather(sysPower.getPowerFather());
		vo.setPowerType(sysPower.getPowerType());
		vo.setRemoteUrl(sysPower.getRemoteUrl());
		vo.setSort(sysPower.getSort());
		return vo;
	}
	
	@Override
	public List<PowerVO> selectPowerNumUrl(UserPowerDTO userPowerDTO) {
		
		List<SysPower> list = sysPowerMapper.selectPowerNumUrl(userPowerDTO.getUserCode());
		List<PowerVO>  power=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {		
			power.add(new PowerVO(list.get(i).getUrl()));
		}	
		return power;
	}

	@Override
	public Page listSysPower(UserPowerDTO userPowerDTO) {
		//分页
		PageHelper.startPage(userPowerDTO.getPageNo(), userPowerDTO.getPageSize()).setOrderBy(" c.power_num ");
		List<SysPower> list = sysPowerMapper.listSysPower(userPowerDTO);
		List<UserPowerVO> pvList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			pvList.add(new UserPowerVO(list.get(i).getId(),list.get(i).getPowerNum(),list.get(i).getPowerName(),
					 list.get(i).getPowerModule(),list.get(i).getPowerType(),list.get(i).getPowerLevel(),
					 list.get(i).getPowerFather(),list.get(i).getIsUse(),list.get(i).getPowerUpdateTime(),
					 list.get(i).getUrl(),list.get(i).getIcon(),list.get(i).getSrc(),list.get(i).getPowerTypeVal(),
					 list.get(i).getPowerLevelVal(),list.get(i).getFatherPowerName()));
		}		
		
		
		//分页的数据
	  	PageInfo<UserPowerVO> pageInfo = new PageInfo<>(pvList);
	  	  
	  	int count = sysPowerMapper.count(userPowerDTO);//总条数
	  	int pageNo = userPowerDTO.getPageNo();//页码
	  	
	  	Page page= new Page(userPowerDTO.getPageSize(),count,pageInfo.getStartRow(),pageNo,pageInfo.getPages(),list);
		return page;
	}

	@Override
	public boolean findSysPowerOne(RoleDTO reDto) {
		return sysRoleMapper.findSysPowerOne(reDto.getRoleNum());
	}

	@Override
	public int insertSelective(SysPower record) {	
		return sysPowerMapper.insertSelective(record);
	}
    
	
	@Override
	public Page listRole(RoleDTO roleDTO) {
		PageHelper.startPage(roleDTO.getPageNo(),roleDTO.getPageSize());	
	  	List<SysRole> listRole = sysRoleMapper.listRole(new SysRole(roleDTO.getRoleNum()));
	  	List<RoleVO>  list=new ArrayList<>();  
	  	listRole.forEach(role -> {	  		
	  		list.add(new RoleVO(role.getId(),role.getRoleNum(),role.getRoleName(),role.getRoleUser(),role.getRoleUpdateTime(),
	  				            role.getRoleUpdateUser(),role.getIsUse()));
		});
	  	
	  	//分页的数据
	  	PageInfo<RoleVO> pageInfo = new PageInfo<>(list);
	  	int count = sysRoleMapper.totalCount(new SysRole(roleDTO.getRoleNum()));
	  	int total=0;
	  	if (count>0) {
	      	//依据取值
		  	 total=new Long(count).intValue();//总条数 long转int    	
		}
	  	int pageNo = roleDTO.getPageNo();//页码
	  	
	  	Page page= new Page(roleDTO.getPageSize(),total,pageInfo.getStartRow(),pageNo,pageInfo.getPages(),list);
	  	
		return page;
	}

	@Override
	public List<UserPowerVO> listDataPower() {
		List<UserPowerVO>  list=new ArrayList<>();
		List<SysPower> listDataPower = sysPowerMapper.listDataPower();
		
		listDataPower.forEach(power ->{
			list.add(new UserPowerVO(power.getId(),power.getPowerNum(),power.getPowerName(),
					                 power.getSpecialPowerKey(),power.getSpecialPowerValue()));
		});
		return list;
	}

	@Override
	public SpePowerVO selectSpePower(SpePowerDTO spePowerDTO) {
		
		SysSpePower sp = sysSpePower.selectSpePower(new SysSpePower(spePowerDTO.getUserCode(),spePowerDTO.getSpeNum()));
		if (sp!=null) {
			SpePowerVO	Power=new SpePowerVO(sp.getId(), sp.getUserCode(), sp.getSpeNum(), 
				     sp.getSpeName(), sp.getSpeKey(), sp.getSpeVal(), sp.getSpeType(), 
				     sp.getSpeUse(), sp.getSpeRemark());
			return Power;
		}	
		
		return null;
	}

	@Override
	public UserVO selectByPrimaryKey(UserDTO userDTO) {
		// TODO Auto-generated method stub
		SpUser spuser = spUserMapper.selectByPrimaryKey(userDTO.getId());
		UserVO user=new UserVO(spuser.getId(), spuser.getUserCode(), spuser.getName(), spuser.getPassword(), 
				               spuser.getCompanyCode(), spuser.getOrgCode(), spuser.getEmail(), spuser.getPhone(),
				               spuser.getMultiLogin(),spuser.getUserType(),spuser.getUpdateUser(),spuser.getUpdateTime());
		return user;
	}

	@Override
	public int insertUserAccount(UserAccountDTO record) {
		// TODO Auto-generated method stub
		return sysUserAccountMapper.insertSelective(new SysUserAccount(record.getId(),record.getUserId(), record.getAccount(), 
				                                                       record.getUsername(), record.getPlatType()));
	}

	@Override
	public int insertRole(RoleDTO roleDTO) {
		
		String roleNum =sysRoleMapper.selectRoleNum(roleDTO.getRoleUser());
		if (roleNum!=null) {
			 roleNum =String.valueOf((Integer.parseInt(roleNum) +1)) ;
		}else {
			 roleNum = roleDTO.getRoleUser()+"000";
		}
		
		return sysRoleMapper.insertSelective(new SysRole(iworkService.getNextId(),roleNum,roleDTO.getRoleName(),
				roleDTO.getRoleUser(), roleDTO.getRoleUpdateUser()));
	}
	
	
	@Transactional
	@Override
	public int deleteRole(RoleDTO roleDTO) {
		int sum =0;
		if (roleDTO.getRoleNum()!=null) {
			//删除角色
		    sum = sysRoleMapper.deleteByPrimaryKey(roleDTO.getRoleNum());
		    if (sum>0) {
		    	//删除角色下的权限
			    SysRolePower role = new SysRolePower(roleDTO.getRoleNum());
			    sysRolePowerMapper.deleteRower(role);
			}    
		}
		      
		return sum;
	}

	@Override
	public RoleVO selectRoleId(RoleDTO roleDTO) {
		SysRole role = sysRoleMapper.selectByPrimaryKey(roleDTO.getId());
		
		if (role!=null) {
			return  new RoleVO(role.getId(),role.getRoleNum(),role.getRoleName(), role.getRoleUser(),
					role.getRoleUpdateTime(), role.getRoleUpdateUser(), role.getIsUse(), role.getRoleRemark());	 
		}
		return new RoleVO();
	}

	@Override
	public int updateRoleId(RoleDTO roleDTO) {
		
		int sum = sysRoleMapper.updateByPrimaryKeySelective(
				new SysRole(roleDTO.getId(), roleDTO.getRoleNum(), roleDTO.getRoleName(),
						roleDTO.getRoleUser(),roleDTO.getRoleUpdateTime(),roleDTO.getRoleUpdateUser(),
						roleDTO.getIsUse(), roleDTO.getRoleRemark()));
		
		return sum;
	}

	@Override
	public List<UserPowerVO> listRolePower(RoleDTO reDto) {
		// TODO Auto-generated method stub
		List<UserPowerVO> power=new ArrayList<>();
		List<SysPower> list = sysPowerMapper.listRolePower(reDto.getRoleNum());
		list.forEach(sp->{
			UserPowerVO user= new UserPowerVO();
			user.setPowerNum(sp.getPowerNum());
			power.add(user);
		});
		return power;
	}
	//查询所有权限(不含数据权限)
	@Override
	public List<HtmlBtnVO> listMenuHtmlBtn() {
		
		List<HtmlBtnVO> list=new ArrayList<>();
		
		List<SysPower> listHtmlBtn = sysPowerMapper.listMenuHtmlBtn();
		listHtmlBtn.forEach(sp->{
		    list.add(new HtmlBtnVO(sp.getId(),sp.getPowerNum(),sp.getPowerName(),sp.getPowerFather(),sp.getPowerType()));
		});
		return list;
	}

	@Override
	public int deleteRoleAndRower(RoleDTO roleDTO) {   
		SysRolePower role = new SysRolePower(roleDTO.getRoleNum());
		return sysRolePowerMapper.deleteRower(role);
	}
	@Transactional
	@Override
	public int insertPower(UserPowerDTO userPowerDTO) {
		SysPower power=powerOrDto(userPowerDTO);
		power.setId(iworkService.getNextId());
		return sysPowerMapper.insertSelective(power);
	}
	
	
	
	

	@Override
	public UserPowerVO selectfingPower(UserPowerDTO userPowerDTO) {
		// TODO Auto-generated method stub

		SysPower sysPower = sysPowerMapper.selectSysPower(userPowerDTO.getId());
			 UserPowerVO mm=new UserPowerVO();
		     mm.setId(sysPower.getId());
		     mm.setPowerNum(sysPower.getPowerNum());
		     mm.setPowerName(sysPower.getPowerName());
		     mm.setPowerModule(sysPower.getPowerModule());
		     mm.setPowerType(sysPower.getPowerType());
		     mm.setPowerFather(sysPower.getPowerFather());
		     mm.setSort(sysPower.getSort());
		     mm.setPowerLevel(sysPower.getPowerLevel());
		     mm.setSrc(sysPower.getSrc());
		     mm.setUrl(sysPower.getUrl());
		     mm.setSpecialPowerKey(sysPower.getSpecialPowerKey());
		     mm.setSpecialPowerValue(sysPower.getSpecialPowerValue());
		     mm.setIsUse(sysPower.getIsUse());
		     mm.setPowerUpdateTime(sysPower.getPowerUpdateTime());
		return mm;
	}

	@Override
	public int updataSysPowerId(UserPowerDTO userPowerDTO) {
		// TODO Auto-generated method stub
		SysPower power = powerOrDto(userPowerDTO);
		return sysPowerMapper.updataSysPowerId(power);
	}

	//权限dto 转 权限实体SysPower
	public SysPower powerOrDto(UserPowerDTO dt) {		
		SysPower sysPower=new SysPower();
		sysPower.setId(dt.getId());
		sysPower.setPowerName(dt.getPowerName());
		sysPower.setPowerNum(dt.getPowerNum());
		sysPower.setPowerModule(dt.getPowerModule());
		sysPower.setPowerType(dt.getPowerType());
		sysPower.setPowerFather(dt.getPowerFather());
		sysPower.setPowerLevel(dt.getPowerLevel());
		sysPower.setIsUse(dt.getIsUse());
		sysPower.setPowerUpdateTime(dt.getPowerUpdateTime());
		sysPower.setSpecialPowerKey(dt.getSpecialPowerKey());
		sysPower.setSpecialPowerValue(dt.getSpecialPowerValue());
		sysPower.setUrl(dt.getUrl());
		sysPower.setIcon(dt.getIcon());
		sysPower.setSrc(dt.getSrc());
		return sysPower;
	}

	@Override
	public int delectPowerNumPower(UserPowerDTO userPowerDTO) {
		return sysPowerMapper.delectPowerNumPower(userPowerDTO.getPowerNum());
	}

	@Transactional
	@Override
	public int insertAll(List<RolePowerDTO> record) {
		
		List<SysRolePower> list=new ArrayList<>();
		record.forEach(sp->{
			list.add(new SysRolePower(iworkService.getNextId(),sp.getRoleNum(),sp.getPowerNum()));
		});
		// TODO Auto-generated method stub
		return sysRolePowerMapper.insertAll(list);
	}
	@Transactional
	@Override
	public int updataListRolePower(List<RolePowerDTO> record, RoleDTO roleDTO) {
	    //先删除
		sysRolePowerMapper.deleteRower(new SysRolePower(roleDTO.getRoleNum()));
		//在插入
		if (record.size()>0) {
			List<SysRolePower> list=new ArrayList<>();	
			for (int i = 0; i < record.size(); i++) {
				list.add(new SysRolePower(iworkService.getNextId(),record.get(i).getRoleNum(),record.get(i).getPowerNum()));
			}
			return sysRolePowerMapper.insertAll(list);
		}
		
		return 0;
	}

	@Override
	public String selectRoleNum(String roleUser) {
		// TODO Auto-generated method stub
		return sysRoleMapper.selectRoleNum(roleUser);
	}

	@Override
	public boolean powerNum(String powerNum) {
		return sysPowerMapper.powerNum(powerNum);
	}

	@Override
	public int totalCount(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		return sysRoleMapper.totalCount(new SysRole(roleDTO.getRoleNum()));
	}

	@Override
	public int deleteUser(String userCode) {
		// TODO Auto-generated method stub
		return sysUserRoleMapper.deleteUser(userCode);
	}

	@Override
	public int insertList(List<UserRoleDTO> list) {
		// TODO Auto-generated method stub
		List<SysUserRole> listData=new ArrayList<>();
		list.forEach(sp->{
			listData.add(new SysUserRole(sp.getId(),sp.getUserCode(),sp.getRoleNum()));
		});
		return sysUserRoleMapper.insertList(listData);
	}

	@Transactional
	@Override
	public int[] updataUserRole(String userCode, List<UserRoleDTO> list) {
	    int deleteUser = sysUserRoleMapper.deleteUser(userCode);
	    int insertList = insertList(list);
	    int[] sum= {deleteUser,insertList};
		return sum;
	}

	@Override
	public List<String> selectRoleNumsByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return sysUserRoleMapper.selectRoleNumsByUserCode(userCode);
	}

	@Override
	public int insertSysRolePower(RolePowerDTO rePowerDTO) {
		// TODO Auto-generated method stub
		SysRolePower role=new SysRolePower(iworkService.getNextId(), rePowerDTO.getRoleNum(), rePowerDTO.getPowerNum());
		return sysRolePowerMapper.insertSelective(role);
	}

	@Override
	public List<SysPower> selectHtmlMenu(String userCode) {
		
		return sysPowerMapper.selectHtmlMenu(userCode);
	}

	
	@Override
	public List<SysPower> listBtnSpecial(UserPowerDTO userPowerDTO) {
		
		return sysPowerMapper.listBtnSpecial(userPowerDTO);
	}

	@Override
	public List<HtmlMenuPowerVo> listPower(String userCode) {
		// TODO Auto-generated method stub
		
		List<HtmlMenuPowerVo> list=new ArrayList<>();
		
		List<SysPower> listPower = sysPowerMapper.listPower(userCode);
		
		listPower.forEach(sp->{
			list.add(new HtmlMenuPowerVo(sp.getPowerNum(), sp.getPowerName(), sp.getPowerFather(), sp.getPowerType(), 
					sp.getSrc(),sp.getIcon()));
		});
		return list;
	}

	@Override
	public List<HtmlMenuPowerVo> listAllPower(String userCode) {
		 List<HtmlMenuPowerVo> list=new ArrayList<>();
			List<SysPower> listPower = sysPowerMapper.listAllPower(userCode);
			
			listPower.forEach(sp->{
				list.add(new HtmlMenuPowerVo(sp.getPowerNum(), sp.getPowerName(), sp.getPowerFather(), sp.getPowerType(), 
						sp.getSrc(),sp.getIcon(),sp.getUrl()));
			});
			return list;
	}






	

	

	




	
	
	
	
	
	
	
	
	
	
}
