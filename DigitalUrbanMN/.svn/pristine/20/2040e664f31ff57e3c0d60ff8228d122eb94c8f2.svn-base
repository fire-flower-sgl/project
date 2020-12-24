package com.mhtech.platform.msrv.auth.login.service;

import java.util.List;

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


@SuppressWarnings("all")
public interface IPowerService {

	List<UserPowerVO> listPower();
	List<UserPowerVO> selectPowerNumUrl(UserPowerDTO userPowerDTO);

	//显示所有权限-分页+模糊
	Page listSysPower(UserPowerDTO userPowerDTO);
    //角色编码唯一性判断
  	boolean findSysPowerOne(RoleDTO reDto);
    //显示所有数据data权限
  	List<UserPowerVO> listDataPower();
    //查询所有html与btn权限
    List<HtmlBtnVO> listMenuHtmlBtn();    
    //查询html权限下的btn权限
    List<HtmlBtnVO> listBtnSpecial(UserPowerDTO userPowerDTO);	
    //查询角色所拥有权限
    List<UserPowerVO> listRolePower(RoleDTO reDto);	
    //查询角色-分页+模糊列表
    Page listRole(RoleDTO roleDTO);
    //依据用户userCode speNum-查询特殊权限关联表信息
    SpePowerVO selectSpePower(SpePowerDTO spePowerDTO);
    //依据id查询用户信息
  	UserVO selectUser(UserDTO userDTO);
  	//新增用户账号信息-权限服务平台
    int insertUserAccount(UserAccountDTO record);
  	//新增角色
    int insertRole(RoleDTO roleDTO);
    //删除角色
    int deleteRole(RoleDTO roleDTO);
	//依据id查询角色对象
    RoleVO selectRoleId(RoleDTO roleDTO);
    //依据id更新角色
    int updateRoleId(RoleDTO roleDTO); 
    //删除角色下的关联权限。
    int deleteRoleAndRower(RoleDTO roleDTO);
    //依据id查询权限
    UserPowerVO selectfingPower(UserPowerDTO userPowerDTO);
    //依据id修改 权限
    int  updataSysPowerId(UserPowerDTO userPowerDTO);
    //新增权限
    int insertPower(UserPowerDTO userPowerDTO);
    //依据powerNum删除权限
    int delectPowerNumPower(UserPowerDTO userPowerDTO);   
    //批量新增角色关联权限
    int insertRolePowerAll(List<RolePowerDTO> record);
    //权限更新按钮
    int updataListRolePower(List<RolePowerDTO> record,RoleDTO roleDTO);
    //权限编码唯一性验证
    boolean powerNum(String powerNum);
  	//修改用户角色
    int[]  updataUserRole(String userCode,List<UserRoleDTO> list); 
    //查询用户角色代码
   	List<String> selectRoleNumsByUserCode(String userCode);
	//新增角色权限
	int insertSysRolePower(RolePowerDTO record);
	//查询用户当前拥有的权限htmlMenu权限
    List<UserPowerVO> selectHtmlMenu(String userCode);
    //查询用户下的所有html menu权限--首页左侧列表
    List<HtmlMenuPowerVo> listPower(String userCode);
    //查询用户所有权限
    List<HtmlMenuPowerVo> listAllPower(String userCode);
   
    
}
