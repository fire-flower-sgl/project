package com.mhtech.platform.msrv.auth.login.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mhtech.platform.msrv.auth.login.service.SysPowerService;
import com.mhtech.platform.msrv.auth.utils.AliyunSmsUtils;
import com.mhtech.platform.msrv.auth.utils.PageUtilToSql;
import com.mobile.model.Page;
import com.mobile.model.SysPower;
import com.mobile.utils.Utilis;

/**
 *  mjl_
 *  SysPowerImpl
 *  系统权限实现类接口
 *  2019-10.22
 */

//@Repository
@Service("loginSysPowerService")
public class SysPowerServiceImpl implements SysPowerService  {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PageUtilToSql pageUtilToSql;
	
	@Value("${pageNo}")
	private int pageNo;
	@Value("${pageSize}")
	private int pageSize;
	
	@Value("${superManagerNo}")
	private String superManagerNo;
	
	@Override
	public SysPower selectSysPower(String id) {
		//下划线转峰驼
		String sql_ft=AliyunSmsUtils.format("id,power_num,power_name,power_module,power_type,power_father,power_level,power_update_time,is_use,special_power_key,special_power_value,url,src");
		String sql="select "+sql_ft+" from sys_power where  id='"+id+"' ";
		RowMapper<SysPower> rowMapper=new BeanPropertyRowMapper<SysPower>(SysPower.class);
		return jdbcTemplate.queryForObject(sql,rowMapper);
	}

	//新增权限-新-添加url路径的接口
	@Override
	public int insertSysPowers(SysPower sysPower) {	
			
		String sql="insert into sys_power "
				+ "(id,power_num,power_name,power_module,power_type,power_father,power_level,power_update_time,"
				+ "is_use,special_power_key,special_power_value,url,`icon`,`src`)"
				+ "values(?,?,?,?,?,?,?,now(),?,?,?,?,?,?)";	
		
	    int sum=jdbcTemplate.update(sql,Utilis.getUUID(),sysPower.getPowerNum(),sysPower.getPowerName(),sysPower.getPowerModule()
	    		,sysPower.getPowerType(),sysPower.getPowerFather(),sysPower.getPowerLevel(),sysPower.getIsUse()
	    		,sysPower.getSpecialPowerKey(),sysPower.getSpecialPowerValue(),sysPower.getUrl(),sysPower.getIcon(),sysPower.getSrc());
		
	    //给超级管理员加入改权限
	    if(!sysPower.getPowerType().equals("data")){
		    sql = "insert into sys_role_power values(?,?,?)";
		    int update = jdbcTemplate.update(sql,Utilis.getUUID(),superManagerNo,sysPower.getPowerNum());   
	    }
	    return sum;
	}
	
	//修改权限-新-添加url路径的接口
	@Override
	public int updateSysPowers(SysPower sysPower) {
		String sql="UPDATE sys_power set power_num=?,power_name=?,"
				 + "power_module=?,power_type=?,power_father=?,power_level=?,power_update_time=now(),is_use=? "
				 + ",special_power_key=?,special_power_value=? ,url=?,`icon`=?,`src`=? where id=? ";	
		int sum=jdbcTemplate.update(sql,sysPower.getPowerNum(),sysPower.getPowerName()
				,sysPower.getPowerModule(),sysPower.getPowerType(),sysPower.getPowerFather()
	    		,sysPower.getPowerLevel(),sysPower.getIsUse(),sysPower.getSpecialPowerKey()
	    		,sysPower.getSpecialPowerValue(),sysPower.getUrl(),sysPower.getIcon(),sysPower.getSrc(),sysPower.getId());
		return sum;
	}

	

	//新增权限
	@Override
	public int insertSysPower(SysPower sysPower) {	
			
		String sql="insert into sys_power "
				+ "(id,power_num,power_name,power_module,power_type,power_father,power_level,power_update_time,"
				+ "is_use,special_power_key,special_power_value)"
				+ "values(?,?,?,?,?,?,?,now(),?,?,?)";	
		
	    int sum=jdbcTemplate.update(sql,Utilis.getUUID(),sysPower.getPowerNum(),sysPower.getPowerName(),sysPower.getPowerModule()
	    		,sysPower.getPowerType(),sysPower.getPowerFather(),sysPower.getPowerLevel(),sysPower.getIsUse()
	    		,sysPower.getSpecialPowerKey(),sysPower.getSpecialPowerValue());
		return sum;
	}
	

	
	
	@Override
	public int updateSysPower(SysPower sysPower) {
		String sql="UPDATE sys_power set power_num=?,power_name=?,"
				 + "power_module=?,power_type=?,power_father=?,power_level=?,power_update_time=now(),is_use=? "
				 + ",special_power_key=?,special_power_value=?  where id=? ";	
		int sum=jdbcTemplate.update(sql,sysPower.getPowerNum(),sysPower.getPowerName()
				,sysPower.getPowerModule(),sysPower.getPowerType(),sysPower.getPowerFather()
	    		,sysPower.getPowerLevel(),sysPower.getIsUse(),sysPower.getSpecialPowerKey()
	    		,sysPower.getSpecialPowerValue(),sysPower.getId());
		return sum;
	}

	@Transactional
	@Override
	public boolean deletePowerNum(String powerNum) {
		
	    //-- 删除系统权限   and is_use='0' 
		String sql1="DELETE FROM sys_power where power_num='"+powerNum+"' ";
		//-- 特殊权限 and spe_use='0' 
		String sql2="DELETE FROM sys_spe_power where spe_num='"+powerNum+"'";
		//-- 删除角色权限 
		String sql3="DELETE FROM sys_role_power where power_num='"+powerNum+"'";
	    int x1=jdbcTemplate.update(sql1);
	    int x2=jdbcTemplate.update(sql2);
	    int x3=jdbcTemplate.update(sql3);
		if (x1>0) {
			return true;
		}else {
			if (x2>0) {
				 return true;
			}else {
				if (x3>0) {
					return true;
				} 
			} 
		}  
	    return false;	
	}

	
	//显示权限代码 权限名称
	@Override
	public List<SysPower> list() {
		String sql_ft=AliyunSmsUtils.format("id,power_num,power_name,power_module,power_type,power_father,power_level,power_update_time,is_use");
		String sql=" select "+sql_ft+"  from sys_power ";
		RowMapper<SysPower> rowMapper=new BeanPropertyRowMapper<SysPower>(SysPower.class);
		List<SysPower> list= jdbcTemplate.query(sql, rowMapper);
		return list;
	}
	
	//显示权限代码 权限名称
	@Override
	public List<SysPower> list2() {
		String sql_ft=AliyunSmsUtils.format("id,power_num,power_name,power_module,power_type,power_father,power_level,power_update_time,is_use");
		String sql=" select "+sql_ft+"  from sys_power where power_type='data' ";
		RowMapper<SysPower> rowMapper=new BeanPropertyRowMapper<SysPower>(SysPower.class);
		List<SysPower> list= jdbcTemplate.query(sql, rowMapper);
		return list;
	}
	

	@Override
	public Page findSysConImplPage(Map<String, String> map) {
		
		String sql=" SELECT c.id id,c.power_name powerName,c.power_num powerNum,c.url,c.src,c.icon,c.power_module "
				+ " powerModule,c.is_use isUse,c.power_update_time  powerUpdateTime,c.powerTypeVal ,c.power_type  powerType"
				+ " ,f.parm_name powerLevelVal,c.power_level powerLevel,c.power_father powerFather,d.power_name fatherPowerName from  "
				+ " (select a.*,parm_name powerTypeVal from sys_power a LEFT JOIN sys_parameter_copy b on"
				+ " a.power_type=b.parm_code and parm_type='powerType' and parm_status = '1')c "
				+ " LEFT JOIN sys_parameter_copy f ON c.power_level=f.parm_code and f.parm_status = '1' "
				+ " and parm_type='powerLevel' LEFT JOIN sys_power d on c.power_father=d.power_num "
				+ "  where 1=1 ";
		String sql_count = " SELECT  COUNT(c.id) from "
						+ " (select a.*,parm_name powerTypeVal from sys_power a LEFT JOIN sys_parameter_copy b on"
						+ " a.power_type=b.parm_code and parm_type='powerType' and parm_status = '1')c "
						+ " LEFT JOIN sys_parameter_copy f ON c.power_level=f.parm_code and f.parm_status = '1' "
						+ " and parm_type='powerLevel' LEFT JOIN sys_power d on c.power_father=d.power_num "
						+ "  where 1=1 ";
		String sql_where = "";
		if (map.get("id") != null && !map.get("id").isEmpty()) {
			sql_where = sql_where + " and c.id = '" + map.get("id") + "'";
		}	
		if (map.get("powerNum")!=null&&!map.get("powerNum").isEmpty()) {
			sql_where=sql_where+" and c.power_num  like '%"+map.get("powerNum")+"%' or  c.power_name like '%"+map.get("powerNum")+"%'  ";
		}
		if (map.get("url")!=null&&!map.get("url").isEmpty()) {
			sql_where=sql_where+" and c.url = '"+map.get("url")+"' ";
		}	
		int pageNo = this.pageNo;
		int pageSize = this.pageSize;
		if (map.get("pageNo") != null && !map.get("pageNo").isEmpty()) {
			pageNo = Integer.parseInt(map.get("pageNo"));
		}
		if (map.get("pageSize") != null && !map.get("pageSize").isEmpty()) {
			pageSize = Integer.parseInt(map.get("pageSize"));
		}
		
		//新增----分页，传入页码控制
		if (pageNo <= 0) pageNo = this.pageNo;
		if (pageSize <= 0) pageSize = this.pageSize;	
		// 总页数
		int customerPageCount = findCount(sql_count + sql_where);
		int pageCount = customerPageCount % pageSize == 0 ? (customerPageCount / pageSize):(customerPageCount / pageSize + 1);
		// 当前页数大于总页数 将当前页数等于总页数
		if (pageNo > pageCount) {
				pageNo = pageCount;
		}

		Pageable pageable = new PageRequest(pageNo -1>0 ?pageNo -1:0, pageSize);
		String pageSql = pageUtilToSql.getPageSql(sql + sql_where + "order by c.power_num  ", pageable);
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(pageSql);
		List<Map<String, String>> list = Utilis.mapObjToString2(maps);
	
		Page page = new Page(customerPageCount);
		page.setData(list);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}
	

	public int findCount(String sql_count) {
		return jdbcTemplate.queryForObject(sql_count, Integer.class);
	}

	@Override
	public List<Map<String, Object>> spePowerList() {
		String sql_ft=AliyunSmsUtils.format("id,power_num,power_name,special_power_key,special_power_value");
		String sql=" select "+sql_ft+"  from sys_power where power_type='data' ";
		return jdbcTemplate.queryForList(sql);
	}

	
	@Override
	public int deleteSysPower(String id) {
		String sql="DELETE FROM sys_power where id="+id+" ";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<SysPower> list3() {
		String sql="SELECT  power_num,power_name  from sys_power where power_type='html' or power_type='menu' or power_type='btn' ";
		RowMapper<SysPower> rowMapper=new BeanPropertyRowMapper<SysPower>(SysPower.class);
		List<SysPower> list= jdbcTemplate.query(sql, rowMapper);
		return list;
	}

	@Override
	public boolean findSysPowerOne(String powerNum) {
		 String sql = "SELECT COUNT(1) from sys_power where  power_num='"+powerNum+"'";
		 int sum = jdbcTemplate.queryForObject(sql, Integer.class);
		 if (sum>0) {
			 return true;
		 }
		 return false;
	}

	@Override
	public List<Map<String, Object>> list4(String userCode) {
	    String sql=" SELECT distinct power_num `index`,powerName title, powerFather,powerType,src,icon "
	    		  + " from (SELECT  a.`user_code` userCode,c.role_name roleName, f.power_name powerName, src,icon,f.power_num,f.power_father powerFather,f.power_type powerType "
	    		  + " from sp_user a LEFT JOIN sys_user_role b on a.user_code=b.user_code "
	    		  + " LEFT JOIN sys_role c ON b.role_num=c.role_num  "
	    		  + " LEFT JOIN sys_role_power d on c.role_num=d.role_num "
	    		  + " LEFT JOIN sys_power f ON f.power_num=d.power_num where a.`user_code`='"+userCode+"' and  f.power_type in ('menu','html') and f.is_use='1'"
	    		  		+ " ORDER BY f.id) k ";
		return jdbcTemplate.queryForList(sql);
	}
}
