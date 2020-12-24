package com.mhtech.platform.msrv.auth.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.msrv.auth.bean.pojo.PowerSearchDTO;

import com.mhtech.platform.msrv.auth.dao.model.SysPower;
import com.mhtech.platform.msrv.pojo.UserPowerDTO;



@Mapper
public interface SysPowerMapper {
	
	int insert(SysPower record);

	int insertSelective(SysPower record);

	List<SysPower> selectPowerList(PowerSearchDTO search);
	
	
	//显示所有-分页+模糊
  	List<SysPower> listSysPower(@Param("userPowerParams") UserPowerDTO userPowerDTO);
  	//总条数
  	int count(@Param("userPowerParams") UserPowerDTO userPowerDTO);
    //查询用户当前拥有的权限
    List<SysPower> selectPowerNumUrl(String userCode);
    
    //查询用户当前拥有的权限htmlMenu权限
    List<SysPower> selectHtmlMenu(String userCode);
	//显示所有数据data权限
	List<SysPower> listDataPower();
	
	
    //查询上级权限下的，所有权限的url
    List<SysPower> selectPowerFatherUrl(String powerFather);
  
	//依据编码删除权限
	boolean deletePowerNum (String powerNum);
	

	//显示对应用户下的menu与html权限
	List<SysPower> listPowerMenuAndHtml(String userCode); 
    
	//查询角色所拥有权限
    List<SysPower> listRolePower(@Param("roleNum")String roleNum);
    //查询所有权限(数据权限除外)
    List<SysPower> listMenuHtmlBtn();
    //依据id查询对象
    SysPower selectSysPower(Long id);
    
    //依据id修改 权限
    int  updataSysPowerId(SysPower sysPower);
    
    //依据powerNum删除权限
    int delectPowerNumPower(String powerNum);
    //权限编码唯一性验证
    boolean powerNum(String powerNum);
    //查询html权限下的btn权限和特殊权限
    List<SysPower> listBtnSpecial(@Param("userPowerParams")UserPowerDTO userPowerDTO);
    
    //查询用户下的所有权限
    List<SysPower> listPower(String userCode);
    
    //查询用户所有权限
    List<SysPower> listAllPower(String userCode);
    
}