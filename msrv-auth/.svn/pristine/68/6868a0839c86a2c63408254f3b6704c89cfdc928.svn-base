package com.mhtech.platform.msrv.auth.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.msrv.auth.bean.pojo.PowerVO;
import com.mhtech.platform.msrv.auth.bean.pojo.UserPowerSearchDTO;
import com.mhtech.platform.msrv.auth.dao.model.SysPower;
import com.mhtech.platform.msrv.auth.dao.model.SysRole;
import com.mhtech.platform.msrv.auth.dao.model.SysRolePower;
import com.mhtech.platform.msrv.auth.dao.model.SysUserRole;
import com.mhtech.platform.msrv.auth.exception.BizException;
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

/**
 * 权限业务接口
 * @author Administrator
 *
 */
public interface IPrivilegeService {

	//查询用户权限
	List<PowerVO> listUserPrivis(UserPowerSearchDTO ups) throws BizException;
	//查询用户当前拥有的权限
    List<PowerVO> selectPowerNumUrl(UserPowerDTO userPowerDTO);
	//显示所有-分页+模糊权限列表
    Page listSysPower(UserPowerDTO userPowerDTO);
  	
  	//新增权限
  	int insertSelective(SysPower record);
    //显示所有数据data权限
  	List<UserPowerVO> listDataPower();
  	//查询所有html与btn权限
    List<HtmlBtnVO> listMenuHtmlBtn();
  	
  	//依据id查询用户信息
  	UserVO selectByPrimaryKey(UserDTO userDTO);
    
    //查询用户当前拥有的权限htmlMenu权限
    List<SysPower> selectHtmlMenu(String userCode);
  	
  	//查询角色-分页+模糊列表
  	Page listRole(RoleDTO roleDTO);
    //总条数
    int totalCount(RoleDTO roleDTO);
    //角色编码唯一性判断
  	boolean findSysPowerOne(RoleDTO reDto);
    //权限编码唯一性验证
    boolean powerNum(String powerNum);
    //查询角色所拥有权限
    List<UserPowerVO> listRolePower(RoleDTO reDto);
    
    
  	//依据用户userCode speNum-查询特殊权限关联表信息
    SpePowerVO selectSpePower(SpePowerDTO spePowerDTO);
  	
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
    int insertAll(List<RolePowerDTO> record);
    //权限更新按钮
    int updataListRolePower(List<RolePowerDTO> record,RoleDTO roleDTO);
    //角色表---依据role_user查询role——num
    String selectRoleNum(String roleUser);
    
    
    //删除用户下所有角色关联
  	int deleteUser(String userCode);
	//批量新增用户下的角色
	int insertList(List<UserRoleDTO> list);
	//先删除在插入---修改用户具有的角色
    int[]  updataUserRole(String userCode,List<UserRoleDTO> list);
    
    //查询用户角色代码
	List<String> selectRoleNumsByUserCode(String userCode);
	//新增角色权限
	int insertSysRolePower(RolePowerDTO record);
	
	//查询html权限下的btn权限
    List<SysPower> listBtnSpecial(UserPowerDTO userPowerDTO);
    
    //查询用户下的所有html menu权限--首页左侧列表
    List<HtmlMenuPowerVo> listPower(String userCode);
    
    //查询用户所有权限
    List<HtmlMenuPowerVo> listAllPower(String userCode);
}
