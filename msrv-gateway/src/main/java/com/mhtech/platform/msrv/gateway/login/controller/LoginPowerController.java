package com.mhtech.platform.msrv.gateway.login.controller;

import static com.mhtech.platform.msrv.gateway.response.RespCode.SUCCESS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.auth.service.IPowerService;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.gateway.request.RoleParams;
import com.mhtech.platform.msrv.gateway.request.RolePowerParams;
import com.mhtech.platform.msrv.gateway.request.UserParams;
import com.mhtech.platform.msrv.gateway.request.UserPowerParams;
import com.mhtech.platform.msrv.gateway.request.UserPowerUpdataParams;
import com.mhtech.platform.msrv.gateway.request.UserRoleParams;
import com.mhtech.platform.msrv.gateway.request.UserUpdataRoleParams;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.pojo.UserPowerVO;


import com.mhtech.platform.msrv.pojo.DataPowerVO;
import com.mhtech.platform.msrv.pojo.HtmlBtnVO;
import com.mhtech.platform.msrv.pojo.HtmlMenuPowerVo;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.RoleDTO;
import com.mhtech.platform.msrv.pojo.RolePowerDTO;

import com.mhtech.platform.msrv.pojo.UserPowerDTO;

@RestController
@RequestMapping("/auth/power")
public class LoginPowerController {

	@Autowired
	private IPowerService ipower;

	@Autowired
	IworkService iworkService;

    @Value("YgUserNum")  
	private String ygUserNum; //超级管理员角色编码
	
	// 查询权限记录
	@PostMapping("list")
	public RespObject<?> listPower() {
		try {
			List<UserPowerVO> vos = ipower.listPower();
			return RespUtils.buildOKWithDataYg(vos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RespUtils.OK;
	}

	// 查询权限列表-模糊查询权限名称，权限编号+分页
	@PostMapping(value = "/queryPage")
	public RespObject<?> listSysPower(@RequestBody UserPowerParams userPowerParams) {
		UserPowerDTO userPowerDTO = find(userPowerParams);
		Page listSysPower = ipower.listSysPower(userPowerDTO);
		return RespUtils.buildOKWithDataYg(listSysPower);
	}

	// 角色编码唯一性判断
	@PostMapping(value = "/findSysPowerOne")
	public RespObject<?> findSysPowerOne(@RequestBody UserRoleParams userRoleParams) {
		boolean findSysPowerOne = ipower.findSysPowerOne(new RoleDTO(userRoleParams.getRoleNum()));
		return RespUtils.buildOKWithDataYg(findSysPowerOne);
	}

	// 查询角色列表——模糊+分页
	@PostMapping(value = "/listSysRole")
	public RespObject<?> listSysRole(@RequestBody UserRoleParams userPowerParams) {
		RoleDTO roleDTO = new RoleDTO(userPowerParams.getRoleNum(), userPowerParams.getRoleName(),
				userPowerParams.getPageSize(), userPowerParams.getPageNo());
		Page page = ipower.listRole(roleDTO);

		return RespUtils.buildOKWithDataYg(page);
	}

	// 查询所有特殊权限
	@PostMapping(value = "/listDataPower")
	public RespObject<?> listDataPower() {
		List<UserPowerVO> listDataPower = ipower.listDataPower();
		List<DataPowerVO> list = new ArrayList<>();
		// 返回的UserPowerVO字段转换
		listDataPower.forEach(data -> {
			list.add(new DataPowerVO(data.getId(), data.getPowerNum(), data.getPowerName(), data.getSpecialPowerKey(),
					data.getSpecialPowerValue()));
		});
		return RespUtils.buildOKWithDataYg(list);
	}

	// 角色新增
	@PostMapping(value = "/insertSysRole")
	public RespObject<?> insertSysRole(@RequestBody RoleParams r) {
		RoleDTO findRole = findRole(r);
		return RespUtils.buildOKWithDataYg(ipower.insertRole(findRole));
	}

	// 角色删除
	@PostMapping(value = "/delelRole")
	public RespObject<?> delelRole(@RequestBody RoleParams r) {
		RoleDTO findRole = findRole(r);
		int sum = ipower.deleteRole(findRole);
		if (sum>0) {
			return RespUtils.build(SUCCESS.getCode(),true,"删除成功");
		}
		return RespUtils.build(SUCCESS.getCode(),false,"删除失败，请确认未在使用");
	}

	// 角色修改
	@PostMapping(value = "/updataRole")
	public RespObject<?> updataRole(@RequestBody RoleParams r) {
		RoleDTO findRole = findRole(r);
		return RespUtils.buildOKWithDataYg(ipower.updateRoleId(findRole));
	}

	// 依据id查询角色
	@PostMapping(value = "/fingRoleId")
	public RespObject<?> fingRoleId(@RequestBody RoleParams r) {
		RoleDTO findRole = findRole(r);
		return RespUtils.buildOKWithDataYg(ipower.selectRoleId(findRole));
	}

	// 依据查询角色权限
	@PostMapping(value = "/fingRolePower")
	public RespObject<?> fingRolePower(@RequestBody RoleParams r) {
		RoleDTO findRole = findRole(r);
		List<UserPowerVO> power = ipower.listRolePower(findRole);
		List<String> list = new ArrayList<>();
		power.forEach(sp -> {
			list.add(sp.getPowerNum());
		});
		return RespUtils.buildOKWithDataYg(list);
	}

	// 权限更新
	@PostMapping(value = "/treePower")
	public RespObject<?> treePower(@RequestBody RoleParams r) {
		// 角色下的权限编码集合
		RoleDTO findRole = findRole(r);
		List<UserPowerVO> power = ipower.listRolePower(findRole);
		List<String> list = new ArrayList<>();
		power.forEach(sp -> {
			list.add(sp.getPowerNum());
		});
		
		// 所有权限集合
		List<HtmlBtnVO> list2 = ipower.listMenuHtmlBtn();
		// 定义父级权限列表
		List<HtmlBtnVO> power2 = new ArrayList<>();
		list2.forEach(sp -> {
			if (sp.getPowerType().equals("menu")) {
				power2.add(sp);
			}
		});

		power2.forEach(sp -> {
			// 循环将集合和权限编码传入-获取Listhtml属性
			List<HtmlBtnVO> listPower = findPower(list2, sp.getPowerNum());
			sp.setChildren(listPower);
		});
		Map<String, Object> map = new HashMap<>();
		map.put("power", power2);
		map.put("rolePower", list);

		return RespUtils.buildOKWithDataYg(map);
	}


	// 权限列表
	@PostMapping(value = "/treePowerList")
	public RespObject<?> treePowerList(@RequestBody RoleParams r) {
		List<HtmlBtnVO> list2 = ipower.listMenuHtmlBtn();
		List<HtmlBtnVO> power2 = new ArrayList<>();
		list2.forEach(sp -> {
			if (sp.getPowerType().equals("menu")) {
				power2.add(sp);
			}
		});
		power2.forEach(sp -> {
			List<HtmlBtnVO> listPower = findPower(list2, sp.getPowerNum());
			sp.setChildren(listPower);
		});
		return RespUtils.buildOKWithDataYg(power2);
	}
	
	// 删除角色下对应权限
	@PostMapping(value = "/delelRolePower")
	public RespObject<?> delelRolePower(@RequestBody RoleParams r) {
		RoleDTO findRole = findRole(r);
		return RespUtils.buildOKWithDataYg(ipower.deleteRoleAndRower(findRole));
	}

	// 用id查询权限
	@PostMapping(value = "/selectfingPowerId")
	public RespObject<?> selectfingPowerId(@RequestBody UserPowerParams userPowerParams) {
		return RespUtils.buildOKWithDataYg(ipower.selectfingPower(new UserPowerDTO(userPowerParams.getId())));
	}

	// 用id修改权限
	@PostMapping(value = "/updataSysPowerId")
	public RespObject<?> updataSysPowerId(@RequestBody UserPowerUpdataParams u) {
		UserPowerDTO findPower = findPower(u);
		return RespUtils.buildOKWithDataYg(ipower.updataSysPowerId(findPower));
	}

	// 新增权限
	@PostMapping(value = "/insertPower")
	public RespObject<?> insertPower(@RequestBody UserPowerUpdataParams u) {

		boolean success = false;
		String data = "";
		// 转换实体对象
		UserPowerDTO findPower = findPower(u);

		String powerType = findPower.getPowerType();
		String powerNum = findPower.getPowerNum();
		String name = powerNum.substring(0, powerNum.indexOf(":"));

		// 权限编码唯一性校验
		if (ipower.powerNum(powerNum)) {
			data = "权限编码已存在";
		} else {
			// 验证权限编码,与权限类型,一致性
			if (name.equals(powerType)) {
				if (powerType.equals("menu")) {
					if (findPower.getPowerFather() != null && !"".equals(findPower.getPowerFather())) {
						data = "权限上级输入有误";
					} else {
						if (ipower.insertPower(findPower) > 0) {
							//超级管理员，添加权限
							System.err.println("--超级管理员，添加权限-----------"+ygUserNum+"---"+powerNum);
							ipower.insertSysRolePower(new RolePowerDTO("1000",powerNum));
							success = true;
							data = "权限新增成功";
						}
					}
				} else {
					if (findPower.getPowerFather() != null && !"".equals(findPower.getPowerFather())) {
						if (ipower.insertPower(findPower) > 0) {
							//超级管理员，添加权限
							if (!powerType.equals("data")) {
								System.err.println("--超级管理员，添加权限-----------"+ygUserNum+"---"+powerNum);
								ipower.insertSysRolePower(new RolePowerDTO("1000",powerNum));
							}	
							success = true;
							data = "权限新增成功";
						}
					} else {
						data = "权限上级输入有误";
					}
				}
			} else {
				data = "权限编码  与权限类型 不一致性";
			}
		}
		return RespUtils.build(SUCCESS.getCode(), success, data);
	}

	// 删除权限
	@PostMapping(value = "/delectPower")
	public RespObject<?> delectPower(@RequestBody UserPowerParams u) {

		UserPowerDTO userPowerDTO = new UserPowerDTO();
		userPowerDTO.setPowerNum(u.getPowerNum());
		int sum = ipower.delectPowerNumPower(userPowerDTO);
		if (sum>0) {
			return RespUtils.build(SUCCESS.getCode(),true,"删除成功");
		}
		return RespUtils.build(SUCCESS.getCode(),false,"删除失败，请确认未在使用");
	}

	// 权限更新--先删除，在插入
	@PostMapping(value = "/updataRolePower")
	public RespObject<?> updataRolePower(@RequestBody RolePowerParams u) {

		
		RoleDTO roleDTO = new RoleDTO(u.getRoleNum());
		
		List<RolePowerDTO> xx = new ArrayList<>();
		List<String> rolePower = u.getRolePower();
		rolePower.forEach(sp -> {
			xx.add(new RolePowerDTO(u.getRoleNum(),sp));
		});
		return RespUtils.buildOKWithDataYg(ipower.updataListRolePower(xx, roleDTO));
	}

	// 查询所有权限，不包含数据权限
	@PostMapping(value = "/listNoDataPower")
	public RespObject<?> listNoDataPower() {

		List<HtmlBtnVO> list = ipower.listMenuHtmlBtn();
		// 定义父级权限列表
		List<HtmlBtnVO> power = new ArrayList<>();
		list.forEach(sp -> {
			if (sp.getPowerType().equals("menu")) {
				power.add(sp);
			}
		});

		power.forEach(sp -> {
			// 循环将集合和权限编码传入-获取Listhtml属性
			List<HtmlBtnVO> listPower = findPower(list, sp.getPowerNum());
			sp.setChildren(listPower);
		});

		return RespUtils.buildOKWithDataYg(power);
	}

	// 递归将子权限添加至父权限中
	public List<HtmlBtnVO> findPower(List<HtmlBtnVO> list, String powerNum) {

		List<HtmlBtnVO> power = new ArrayList<>();// 子集集合
		list.forEach(sp -> {
			if (sp.getPowerFather() != null) {
				if (sp.getPowerFather().equals(powerNum)) { // 终止条件-添加条件
					sp.setChildren(findPower(list, sp.getPowerNum()));
					power.add(sp);
				}
			}
		});
		return power;
	}

	//修改用户关联角色信息
	@PostMapping(value = "/updataUserRole")
	public RespObject<?> UpdataUserRole(UserUpdataRoleParams user){

		int[] updataUserRole = ipower.updataUserRole(user.getUserCode(), user.getList());
		return RespUtils.buildOKWithDataYg(updataUserRole);
	}
	
	//查询用户的html_与menu权限
	@PostMapping(value = "/selectHtmlMenu")
	@ResponseBody
	public RespObject<?> selectHtmlMenu(@RequestBody UserParams user ){
		
		List<UserPowerVO> selectHtmlMenu = ipower.selectHtmlMenu(user.getUserCode());
		//按照id排序
		Comparator<UserPowerVO> comparator = new Comparator<UserPowerVO>() {
            public int compare(UserPowerVO s1, UserPowerVO s2) {
                return (s1.getId()+"").compareTo(s2.getId()+"");
            }
        };
		Collections.sort(selectHtmlMenu,comparator);
		List<String> list=new ArrayList<>();
		selectHtmlMenu.forEach(sp ->{
			list.add(sp.getPowerNum());
		});
		return RespUtils.buildOKWithDataYg(list);
	}
	
	
	//查询用户的html权限下的btn权限
	@PostMapping(value = "/selectBtnPower")
	public RespObject<?> selectBtnPower(@RequestBody UserParams user ){
		
		UserPowerDTO d=new UserPowerDTO();
		d.setUserCode(user.getUserCode());
		d.setPowerNum(user.getPowerNum());
	
		List<HtmlBtnVO> listBtnSpecial = ipower.listBtnSpecial(d);
		return RespUtils.buildOKWithDataYg(listBtnSpecial);
	}
	
	
	//查询用户所有权限
	@PostMapping(value = "/selectAllPower")
	public RespObject<?> selectAllPower(@RequestBody UserParams user ){
		
	    List<HtmlMenuPowerVo> listAllPower = ipower.listAllPower(user.getUserCode());
	    if (listAllPower.size()>0) {
	    	return RespUtils.buildOKWithDataYg(listAllPower);
		}
	    //int code, boolean success, String message, T data
	    //RespUtils.build(SUCCESS.getCode(),false,"删除失败，请确认未在使用");
		return  new RespObject(4014,false,"没有页面权限","");
	}
	
	
	
	
	//查询用户权限（不包含数据与按钮权限）-游鸽首页左列表
	@PostMapping(value = "/selectPower")
	public RespObject<?> selectPower( @RequestBody UserParams user) {
	
		//结果
		List<HtmlMenuPowerVo> list = ipower.listPower(user.getUserCode());
	
		//定义父级权限列表
		List<HtmlMenuPowerVo>  fatheList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {	
			if (("menu").equals(list.get(i).getPowerType())) {
				String powerNum = list.get(i).getIndex();
				List<HtmlMenuPowerVo> xx=findPower(powerNum,list);
				if(xx.size()>0 && xx!=null) {
					list.get(i).setSubs(xx);
				}
				fatheList.add(list.get(i));
			}
		}
		return RespUtils.buildOKWithDataYg(fatheList);
	}
	
	private List<HtmlMenuPowerVo> findPower(String powerNum, List<HtmlMenuPowerVo> list) {
		List<HtmlMenuPowerVo>  sonList = new ArrayList<>();	
		//循环传入的集合
		for (int i = 0; i < list.size(); i++) {
			//当powerFather不为空的时候
			if( list.get(i).getPowerFather()!=null) {
				if(list.get(i).getPowerFather().equals(powerNum)) {	
					List<HtmlMenuPowerVo> xx = findPower(list.get(i).getIndex(),list);
					if (xx!=null&&xx.size()>0) {
						list.get(i).setSubs(xx);
					}		
					sonList.add(list.get(i));
				}
			}	
		}
		return sonList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 实体对象转换
	public UserPowerDTO find(UserPowerParams userPowerParams) {
		UserPowerDTO userPowerDTO = new UserPowerDTO(userPowerParams.getUserCode(), userPowerParams.getPageSize(),
				userPowerParams.getPageNo(), userPowerParams.getPowerFather(), userPowerParams.getPowerNum(),
				userPowerParams.getPowerName());
		return userPowerDTO;
	}

	public RoleDTO findRole(RoleParams r) {

		RoleDTO roleDTO = new RoleDTO(r.getRoleNum(), r.getRoleName(), r.getRoleUpdateTime(), r.getIsUse(),
				r.getRoleRemark(), r.getRoleUpdateUser(), r.getRoleUser(), r.getId(), r.getPageSize(), r.getPageNo());

		return roleDTO;
	}

	public UserPowerDTO findPower(UserPowerUpdataParams u) {

		UserPowerDTO userPowerDTO = new UserPowerDTO(u.getPowerFather(), u.getPowerModule(), u.getPowerType(),
				u.getPowerLevel(), u.getRemoteUrl(), u.getSort(), u.getIsUse(), u.getPowerUpdateTime(),
				u.getSpecialPowerKey(), u.getSpecialPowerValue(), u.getUrl(), u.getIcon(), u.getSrc(), u.getPowerNum(),
				u.getPowerName(), u.getId());
		return userPowerDTO;
	}

}
