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
import org.springframework.util.StringUtils;

import com.mhtech.platform.msrv.auth.login.service.SysRoleService;
import com.mhtech.platform.msrv.auth.utils.AliyunSmsUtils;
import com.mhtech.platform.msrv.auth.utils.PageUtilToSql;
import com.mobile.model.Page;
import com.mobile.model.SysRole;
import com.mobile.utils.Utilis;

//@Repository
@Service("loginSysRoleService")
public class SysRoleServiceImpl implements SysRoleService {


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
	
	@Autowired
	private UserPowerServiceImpl upImpl;
	
	@Override
	public SysRole selectSysPower(String id) {
		//下划线转峰驼
		String sql_ft=AliyunSmsUtils.format("id,role_num,role_name,role_user,role_update_time,role_update_user,is_use,role_remark");
		String sql="select "+sql_ft+" from sys_role where  id='"+id+"' ";
		RowMapper<SysRole> rowMapper=new BeanPropertyRowMapper<SysRole>(SysRole.class);
		SysRole sysRole=jdbcTemplate.queryForObject(sql,rowMapper);
		return sysRole;
	}

	@Override
	public int insertSysPower(SysRole sysRole) {
		
		String sql="insert into sys_role (id,role_num,role_name,role_user,role_update_time,role_update_user,is_use,role_remark)"
				+ " values(?,?,?,?,now(),?,'1',null) ";	
	    int sum=jdbcTemplate.update(sql,Utilis.getUUID(),sysRole.getRoleNum(),sysRole.getRoleName(),sysRole.getRoleUser(),
	    		sysRole.getRoleName());
		return sum;
	}

	@Override
	public int updateSysPower(SysRole sysRole) {
		String sql="UPDATE sys_role set role_name=?,"
				+ "role_user=?,role_update_time=now(),role_update_user=?,is_use=?,role_remark=? where id=? ";	
	    int sum=jdbcTemplate.update(sql,sysRole.getRoleName(),sysRole.getRoleUser(),
	    		sysRole.getRoleUpdateUser(),sysRole.getIsUse(),sysRole.getRoleRemark(),sysRole.getId());
	    
		return sum;
	}
	
	@Transactional
	@Override
	public int deleteSysPower(SysRole sysRole) {
		//and  is_use='0'
		String sql="DELETE FROM sys_role where role_num='"+sysRole.getRoleNum()+"'  ";
		//删除角色权限中间表
		upImpl.deletePower(sysRole.getRoleNum());	
		//删除用户角色中间表
		String deleteUserRole="delete from sys_user_role where role_num = '"+sysRole.getRoleNum()+"'";
		jdbcTemplate.update(deleteUserRole);
		return jdbcTemplate.update(sql);
	}

	//查询所有
	@Override
	public List<SysRole> list() {
		String sql_ft=AliyunSmsUtils.format("id,role_num,role_name,role_user,role_update_time,role_update_user,is_use,role_remark");
		String sql=" select "+sql_ft+" from sys_role";
		RowMapper<SysRole> rowMapper=new BeanPropertyRowMapper<SysRole>(SysRole.class);
		List<SysRole> list= jdbcTemplate.query(sql, rowMapper);
		return list;
	}

	@Override
	public List<Map<String, Object>> roleList() {
		String sql_ft=AliyunSmsUtils.format("role_num,role_name");
		String sql=" select "+sql_ft+" from sys_role where is_use = '1'";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> powerList(String roleNum) {		
		String sql=" SELECT a.role_name roleName,a.role_num roleNum,c.power_name powerName ,c.power_father powerFather,c.power_num powerNum from sys_role a "
				+ " LEFT JOIN sys_role_power b on a.role_num=b.role_num  "
				+ " LEFT JOIN sys_power c on b.power_num=c.power_num  "
				+ " where a.role_num=? ORDER BY c.power_type DESC,c.power_father  ";
		return jdbcTemplate.queryForList(sql,roleNum);
	}

	
	
	@Override
	public Page findSysConImplPage(Map<String, String> map) {
	
		String sql_ft=AliyunSmsUtils.format("id,role_num,role_name,role_user,role_update_time,role_update_user,is_use,role_remark");
		String sql=" select "+sql_ft+" from sys_role where 1=1";
		String sql_count = "select count(id) from sys_role where 1=1";
		String sql_where = "";
		
		if (map.get("id") != null && !map.get("id").isEmpty()) {
			sql_where = sql_where + " and id = '" + map.get("id") + "'";
		}
		if ( map.get("roleNum")!=null&&!map.get("roleNum").isEmpty() ) {
			sql_where = sql_where + " and role_num like '%" + map.get("roleNum") + "%' or  role_name like '%" + map.get("roleNum") + "%'";
		}
		if (map.get("roleUser") != null && !map.get("roleUser").isEmpty()) {
			sql_where = sql_where + " and role_user = '" + map.get("roleUser") + "'";
		}
		if (map.get("roleUpdateTime") != null && !map.get("roleUpdateTime").isEmpty()) {
			sql_where = sql_where + " and role_update_time = '" + map.get("roleUpdateTime") + "'";
		}
		if (map.get("roleUpdateUser") != null && !map.get("roleUpdateUser").isEmpty()) {
			sql_where = sql_where + " and role_update_user = '" + map.get("roleUpdateUser") + "'";
		}
		if (map.get("isUse") != null && !map.get("isUse").isEmpty()) {
			sql_where = sql_where + " and is_use = '" + map.get("isUse") + "'";
		}
		if (map.get("roleRemark") != null && !map.get("roleRemark").isEmpty()) {
			sql_where = sql_where + " and role_remark = '" + map.get("roleRemark") + "'";
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
		String pageSql = pageUtilToSql.getPageSql(sql + sql_where + " order by role_update_time desc ", pageable);
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
	public boolean findSysPowerOne(String roleNum) {
		 String sql = "SELECT COUNT(1) from sys_role where  role_num='"+roleNum+"'";
		 int sum = jdbcTemplate.queryForObject(sql, Integer.class);
		 if (sum>0) {
			 return true;
		 }
		 return false;
	}

	@Override
	public Integer powerNameIsExist(SysRole sysRole) {
		String sql = "SELECT count(1) from sys_role where  role_name='"+sysRole.getRoleName()+"'";
		 if(!StringUtils.isEmpty(sysRole.getId())) {
			 sql = sql + " and id != '"+sysRole.getId()+"' ";
		 }
		 int sum = jdbcTemplate.queryForObject(sql, Integer.class);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public String getIdByUserType(String type) {
		if(type.equals("0")){
			return "superManagerNo";
		}
		String sql = "SELECT role_num from sys_role where role_num like '"+type+"%' ORDER BY role_num desc LIMIT 0,1";
		Integer sum = jdbcTemplate.queryForObject(sql, Integer.class);
		if (sum!=null) {
			return sum+1+"";
		}else {			
			return Integer.parseInt(type)*1000+1+"";
		}

	}
	@Override
	public Integer addSysPower(SysRole sysRole) {
		String sql="insert into sys_role (id,role_num,role_name,role_user,role_update_time,role_update_user,is_use,role_remark)"
				+ " values(?,?,?,?,now(),?,'1',null) ";	
	    int sum=jdbcTemplate.update(sql,Utilis.getUUID(),sysRole.getRoleNum(),sysRole.getRoleName(),sysRole.getRoleUser(),
	    		sysRole.getRoleUpdateUser());
		return sum;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	

}
