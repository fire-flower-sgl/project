package com.mhtech.platform.msrv.auth.login.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mhtech.platform.msrv.auth.login.service.UserPowerService;
import com.mobile.model.SpePower;
import com.mobile.model.SysPower;
import com.mobile.utils.Utilis;

/**
 * @ClassName UserPowerImpl
 * @Description TODO
 * @Author admini
 * @Date 2019/10/22 10:19
 * @Version 1.0
 */
//@Repository
@Service("loginUserPowerService")
public class UserPowerServiceImpl implements UserPowerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public boolean addrole(String userCode, List<String> roleList) {
		 List<Object[]> params = new ArrayList<>();
		 for (String roleNum : roleList) {
			 params.add(new Object[]{Utilis.getUUID(),userCode,roleNum});
		 }
		 String sql = "insert into sys_user_role values (?,?,?)";
		 int[] update = jdbcTemplate.batchUpdate(sql, params);
		 int sum = 0;
		 for (int i = 0; i < update.length; i++) {
			 sum++;
		 }
		 System.out.println("角色添加行数:"+sum);
		 return sum>0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateRole(String userCode, List<String> roleList) {
		boolean deleteRole = deleteRole(userCode);
		if(roleList.size()>0) {
			boolean addrole = addrole(userCode,roleList);
		}
		return deleteRole;
	}
	
	@Override
	public List<Map<String, Object>> querySpePower2(String userCode) {
		
//		String sql = "SELECT a.id,a.user_code userCode,a.spe_num speNum,b.url  "
//				  + " from sys_spe_power a LEFT JOIN sys_power b on a.spe_num=b.power_num where a.user_code = ? ";	
		String sql = "SELECT a.id,a.user_code userCode,a.spe_num speNum,b.url,b.icon,b.src  "
				  + " from sys_spe_power a LEFT JOIN sys_power b on a.spe_num=b.power_num where a.user_code = ? ";	
		return jdbcTemplate.queryForList(sql,userCode);
	}
	

	@Override
	public boolean deleteRole(String userCode) {
		String sql = "delete from sys_user_role where user_code = ?";
		return jdbcTemplate.update(sql,userCode)>0;
	}

	@Override
	public boolean deleteRoleByRoleNum(String userCode, String roleNum) {
		String sql = "delete from sys_user_role where user_code = ? and role_num = ?";
		return jdbcTemplate.update(sql,userCode,roleNum)>0;
	}

	@Override
	public List<Map<String, Object>> queryRole(String userCode) {
		String sql = "select u.user_code userCode,u.name userName,r.role_num roleNum,r.role_name roleName from  sp_user u,sys_role r,sys_user_role ur "
				+ "where u.user_code = ur.user_code and r.role_num = ur.role_num  and ur.user_code = ? and r.is_use = '1' order by  r.role_update_time ";
		return jdbcTemplate.queryForList(sql,userCode);
	}

	@Override
	public boolean addPower(String roleNum, List<String> powerList) {
		 List<Object[]> params = new ArrayList<>();
		 for (String powerNum : powerList) {
			 params.add(new Object[]{Utilis.getUUID(),roleNum,powerNum});
		 }
		 String sql = "insert into sys_role_power values (?,?,?)";
		 int[] update = jdbcTemplate.batchUpdate(sql, params);
		 int sum = 0;
		 for (int i = 0; i < update.length; i++) {
			 sum++;
		 }
		 System.out.println("权限添加行数:"+sum);
		 return sum>0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updatePower(String roleNum, List<String> powerList) {
		boolean deleteRole = deletePower(roleNum);
		boolean addrole = addPower(roleNum,powerList);
		return addrole;
	}

	@Override
	public boolean deletePower(String roleNum) {
		String sql = "delete from sys_role_power where role_num = ?";
		return jdbcTemplate.update(sql,roleNum)>0;
	}

	@Override
	public boolean deletePowerByPowerNum(String roleNum, String powerNum) {
		String sql = "delete from sys_role_power where role_num = ? and power_num = ?";
		return jdbcTemplate.update(sql,roleNum,powerNum)>0;
	}

	@Override
	public List<Map<String, Object>> queryPower(String roleNum) {
		String sql = "SELECT r.role_num roleNum,r.role_name roleName,p.power_num powerNum,p.power_name powerName from sys_role r,sys_power p,sys_role_power rp "
				+ "where r.role_num = rp.role_num and p.power_num = rp.power_num and rp.role_num = ? order by p.power_update_time ";
		return jdbcTemplate.queryForList(sql,roleNum);
	}

	@Override
	public boolean addSpePower(String userCode, List<Map<String, Object>> spePowerList) {
		 List<Object[]> params = new ArrayList<>();
		 for (Map<String, Object> sp : spePowerList) {		
			 List<String> speVal = (List<String>) sp.get("speVal");
			 String speValue = "";
			 if(speVal.size()>0) {
				 for (int i = 0; i < speVal.size(); i++) {
					 speValue += speVal.get(i)+",";
				}
				 speValue = speValue.substring(0,speValue.length()-1);
			 }
			 System.err.println("speVal:"+speValue);
			 params.add(new Object[]{Utilis.getUUID(),userCode,sp.get("speNum"),"",sp.get("speKey"),speValue,sp.get("speType"),sp.get("speUse"),""});
			 //params.add(new Object[]{Utilis.getUUID(),userCode,sp.get("speNum"),"",sp.get("speKey"),sp.get("speVal"),sp.get("speType"),sp.get("speUse"),""});
		 }
		 String sql = "insert into sys_spe_power values (?,?,?,?,?,?,?,?,?)";
		 int[] update = jdbcTemplate.batchUpdate(sql, params);
		 int sum = 0;
		 for (int i = 0; i < update.length; i++) {
			 sum++;
		 }
		 System.out.println("特殊权限添加行数:"+sum);
		 return sum>0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateSpePower(String userCode, List<Map<String, Object>> spePowerList) {
		boolean deleteSpePower = deleteSpePower(userCode);
		if(spePowerList.size()>0) {
			boolean addSpePower = addSpePower(userCode,spePowerList);
		}
		
		return deleteSpePower;
	}

	@Override
	public boolean deleteSpePower(String userCode) {
		String sql = "delete from sys_spe_power where user_code = ?";
		return jdbcTemplate.update(sql,userCode)>0;
	}
	

	@Override
	public	List<SpePower> querySpeRole(String userCode) {
		String original = "sp.id id,sp.user_code userCode,sp.spe_num speNum,sp.spe_name speName,sp.spe_key speKey,sp.spe_val speVal,sp.spe_type speType,sp.spe_use speUse,sp.spe_remark speRemark";
		String sql=" select "+original+" from  sp_user u,sys_power p,sys_spe_power sp where u.user_code = sp.user_code and p.power_num = sp.spe_num and sp.user_code = ? order by p.power_update_time";
		RowMapper<SpePower> rowMapper=new BeanPropertyRowMapper<SpePower>(SpePower.class);
		System.out.println("===querySpeRole==="+sql);
		return jdbcTemplate.query(sql,rowMapper,userCode);
	}
	
	@Override
	public	List<Map<String, Object>> querySpeRoleListMap(String userCode) {
		String original = "sp.id id,sp.user_code userCode,sp.spe_num speNum,sp.spe_name speName,sp.spe_key speKey,sp.spe_val speVal,sp.spe_type speType,sp.spe_use speUse,sp.spe_remark speRemark";
		String sql=" select "+original+" from  sp_user u,sys_power p,sys_spe_power sp where u.user_code = sp.user_code and p.power_num = sp.spe_num and sp.user_code = ? order by p.power_update_time";
		System.out.println("===querySpeRoleListMap==="+sql);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,userCode);
		return jdbcTemplate.queryForList(sql,userCode);
	}
	

	@Override
	public List<SysPower> queryMenuAndHtmlPower(String userCode) {
		String sql = "SELECT DISTINCT u.power_num  powerNum from ( SELECT * from sys_role_power rp where rp.role_num in "
				+ " (SELECT ur.role_num from sys_user_role ur LEFT JOIN sys_role r on ur.role_num = r.role_num where "
				+ " user_code = '"+userCode+"' and r.is_use = '1') ) u LEFT JOIN sys_power p on p.power_num = u.power_num where p.is_use = '1' and p.power_type in ('html','menu') order by u.id";
		RowMapper<SysPower> rowMapper=new BeanPropertyRowMapper<SysPower>(SysPower.class);
		
		List<SysPower> queryForList = jdbcTemplate.query(sql, rowMapper);
		
//		System.err.println("-------------"+sql+"--------大小"+queryForList.size());
		return queryForList;
	}

	@Override
	public List<SysPower> queryBtnPower(String userCode,String powerNum) {
		
		//String sql = "SELECT DISTINCT u.power_num from ( SELECT * from sys_role_power rp where rp.role_num in (SELECT ur.role_num from sys_user_role ur LEFT JOIN sys_role r on ur.role_num = r.role_num where user_code = ? and r.is_use = '1') ) u LEFT JOIN sys_power p on p.power_num = u.power_num where p.is_use = '1' and p.power_type in ('btn') and p.power_father = ? ";
		String sql = "SELECT DISTINCT u.power_num ,p.url,p.icon,p.src from ( SELECT * from sys_role_power rp where rp.role_num in (SELECT ur.role_num from sys_user_role ur LEFT JOIN sys_role r on ur.role_num = r.role_num where user_code = ? and r.is_use = '1') ) u LEFT JOIN sys_power p on p.power_num = u.power_num where p.is_use = '1' and p.power_type in ('btn') and p.power_father = ? ";
		
		RowMapper<SysPower> rowMapper=new BeanPropertyRowMapper<SysPower>(SysPower.class);
		List<SysPower> queryForList = jdbcTemplate.query(sql, rowMapper,userCode,powerNum);
		return queryForList;
	}

	@Override
	public List<Map<String, Object>> userInfo() {
		String sql = "SELECT user_code userCode,email,phone from sp_user";	
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<SpePower> querySpePower(String userCode) {
		String sql = "SELECT id,user_code userCode,spe_num speNum from sys_spe_power where user_code = ? ";	
		RowMapper<SpePower> rowMapper=new BeanPropertyRowMapper<SpePower>(SpePower.class);
		List<SpePower> queryForList = jdbcTemplate.query(sql, rowMapper,userCode);
		return queryForList;
	}

	@Override
	public Map<String, Object> getSpePowerInfo(String powerNum) {
		String sql = "SELECT id, power_num powerNum,power_name powerName,special_power_key speKey,special_power_value speValue from sys_power where power_num = ? ";	
		return jdbcTemplate.queryForMap(sql,powerNum);
	}

	@Override
	public List<Map<String, Object>> querySpePowerValue(String speValue) {
		return jdbcTemplate.queryForList(speValue);
	}

	@Override
	public boolean addFunc(String userCode, List<String> funcList) {
		 List<Object[]> params = new ArrayList<>();
		 for (String funcCode : funcList) {
			 params.add(new Object[]{Utilis.getUUID(),userCode,funcCode});
		 }
		 String sql = "insert into sp_user_func_relation values (?,?,?,0,now())";
		 int[] update = jdbcTemplate.batchUpdate(sql, params);
		 int sum = 0;
		 for (int i = 0; i < update.length; i++) {
			 sum++;
		 }
		 System.out.println("用户职能添加行数:"+sum);
		 return sum>0;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateFunc(String userCode, List<String> funcList) {
		boolean deleteFunc = deleteFunc(userCode);
		if(funcList.size()>0) {
			boolean addrole = addFunc(userCode,funcList);
		}
		return deleteFunc;
	}

	public boolean deleteFunc(String userCode) {
		String sql = "update sp_user_func_relation set is_delete = 1 where user_code = ? ";
		return jdbcTemplate.update(sql,userCode)>0;
	}

	public boolean deleteEmail(String userCode) {
		String sql = "delete from email where name = (SELECT email FROM sp_user where user_code = ? ) and formatCode = 'user' ";
		return jdbcTemplate.update(sql,userCode)>0;
	}

	@Override
	public boolean isAccountExist(String contect) {
		String sql =  "SELECT count(1) from sp_user where email = ? or phone = ?";	
		return jdbcTemplate.queryForObject(sql, Integer.class, contect,contect)>0;
	}

	@Override
	public boolean isUserCodeExist(String contect) {
		String sql =  "SELECT count(1) from sp_user where user_code = ?";	
		return jdbcTemplate.queryForObject(sql, Integer.class, contect)>0;
	}
	
	
}
