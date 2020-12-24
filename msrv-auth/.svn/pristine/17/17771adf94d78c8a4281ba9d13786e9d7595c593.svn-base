package com.mhtech.platform.msrv.auth.login.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.auth.dao.model.SpUser;
import com.mhtech.platform.msrv.auth.login.service.SpUserService;
import com.mhtech.platform.msrv.auth.utils.AliyunSmsUtils;
import com.mhtech.platform.msrv.auth.utils.PageUtilToSql;
import com.mobile.model.Page;
import com.mobile.model.User;
import com.mobile.service.UserPowerService;
import com.mobile.utils.Utilis;


/**
 *  mjl_
 *  SpUserImpl
 *  用户表实现类接口
 *  2019-10.22
 */

//@Repository
@Service("loginSpUserService")
public class SpUserServiceImpl implements SpUserService {

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.auth.login.service.SpUserService#listCheck()
	 */
	@Override
	public List<User> listCheck() {
		// TODO 自动生成的方法存根
		return null;
	}

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserPowerServiceImpl upImpl;
	@Autowired
	PageUtilToSql pageUtilToSql;

	@Value("${pageNo}")
	private int pageNo;
	@Value("${pageSize}")
	private int pageSize;

	
	@Override
	public User selectSysPower(String id) {
		//下划线转峰驼
		String sql_ft=AliyunSmsUtils.format("id,user_code,`name`,`company_code`,`password`,`org_code`,email,phone,multi_login,user_type");
		String sql=" select "+sql_ft+" from sp_user where  id='"+id+"'";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
		return jdbcTemplate.queryForObject(sql,rowMapper);
	}

	
	@Override
	public String insertSysPower(JSONObject object) {
		
		//添加用户
		String sql=" insert into sp_user (id,user_code,`name`,`password`,email,phone,multi_login,user_type) values(?,?,?,?,?,?,?,?)";	
		
		int sum=jdbcTemplate.update(sql,Utilis.getUUID(),object.get("userCode"),object.get("name"),object.get("password"),
	    		object.get("email"),object.get("phone"),object.get("multiLogin"),object.get("userType"));
	   
//		//添加用户的角色
//		List<String> spePowerList= (List<String>) object.get("roleNum");
//		boolean sum2=upImpl.addrole(object.get("userCode").toString(), spePowerList);
//	    //添加用户的特殊权限
//		List<SpePower> x=   (List<SpePower>) object.get("roleNum");
//	    boolean sum3=upImpl.addSpePower(object.get("userCode").toString(), x);
	    
	    String sumx="添加用户是否成功："+sum;	
		return sumx;
	}

	@Override
	public int updateSysPower(User spUser) {
	    String sql="UPDATE sp_user set user_code=?,`name`=?,`password`=?,email=?,phone=?,multi_login=?,user_type=? where id=? ";
	    int sum=jdbcTemplate.update(sql,spUser.getUserCode(),spUser.getName(),spUser.getPassword(),
	    		spUser.getEmail(),spUser.getPhone(),spUser.getMultiLogin(),spUser.getUserType(),spUser.getId());
		return sum;
	}

	@Transactional
	@Override
	public int deleteSysPower(String userCode) {
	    String sql="DELETE FROM sp_user where user_code=?";
	    //删除用户角色
	    upImpl.deleteRole(userCode);
	    //删除用户特殊权限
	    upImpl.deleteSpePower(userCode);
		return jdbcTemplate.update(sql,userCode);
	}

	@Override
	public List<User> list() {
		String sql_ft=AliyunSmsUtils.format("id,user_code,`name`,`password`,email,phone,multi_login,user_type");
		String sql=" select "+sql_ft+" from sp_user";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
		List<User> list= jdbcTemplate.query(sql, rowMapper);
		return list;
	}

//	@Override
//	public List<User> listCheck() {
//		String sql_ft=AliyunSmsUtils.format("id,user_code,`name`,`password`,email,phone,multi_login,user_type");
//		String sql=" select "+sql_ft+" from sp_user where user_code not in(select distinct user_code from user_check_trajectory)";
//		RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
//		List<User> list= jdbcTemplate.query(sql, rowMapper);
//		return list;
//	}
	
	@Override
	public int[] insertAllUser(List<User> spUsers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page findPage(JSONObject object) {
		String sql = "SELECT * from (SELECT u.id,u.user_code userCode,u.name userName,u.company_code,g.company_name companyName,u.org_code ,h.org_name orgName, u.email,u.phone,u.multi_login multiLogin,p.parm_name, d.lastLoginDate from sp_user u LEFT JOIN sys_parameter_copy p on p.parm_code = u.user_type and p.parm_type = 'userType' and p.parm_status = '1' LEFT JOIN (select u.user_code userCode,log_on_date lastLoginDate from (select * from sp_user_login_log order by log_on_date desc) u group by u.user_code ) d on d.userCode = u.user_code LEFT JOIN sp_company_info g on g.company_code = u.company_code LEFT JOIN sp_org_info h on h.org_code = u.org_code and h.is_delete = '0') u ";
		String sql_count = "select count(1) from (SELECT u.id,u.user_code userCode,u.name userName,u.company_code,g.company_name companyName,u.org_code ,h.org_name orgName, u.email,u.phone,u.multi_login multiLogin,p.parm_name, d.lastLoginDate from sp_user u LEFT JOIN sys_parameter_copy p on p.parm_code = u.user_type and p.parm_type = 'userType' and p.parm_status = '1' LEFT JOIN (select u.user_code userCode,log_on_date lastLoginDate from (select * from sp_user_login_log order by log_on_date desc) u group by u.user_code ) d on d.userCode = u.user_code LEFT JOIN sp_company_info g on g.company_code = u.company_code LEFT JOIN sp_org_info h on h.org_code = u.org_code and h.is_delete = '0') u ";
		String sql_where = "";	
		if (object.get("contect") != null && !object.get("contect").toString().isEmpty()){
			String contect = object.get("contect").toString();
			sql_where = " where u.userName like '%"+contect+"%' or u.email like '%"+contect+"%' or u.phone like '%"+contect+"%'";
		}

		int pageNo = this.pageNo;
		int pageSize = this.pageSize;
		if (object.get("pageNo") != null && !object.get("pageNo").toString().isEmpty()) {
			pageNo = Integer.parseInt(object.get("pageNo").toString());
		}
		if (object.get("pageSize") != null && !object.get("pageSize").toString().isEmpty()) {
			pageSize = Integer.parseInt(object.get("pageSize").toString());
		}

		
		
		//新增----分页，传入页码控制
		if (pageNo <= 0) pageNo = this.pageNo;
		if (pageSize <= 0) pageSize = this.pageSize;	
		// 总页数
		int customerPageCount = findEventPageCount(sql_count + sql_where);
		int pageCount = customerPageCount % pageSize == 0 ? (customerPageCount / pageSize):(customerPageCount / pageSize + 1);
		// 当前页数大于总页数 将当前页数等于总页数
		if (pageNo > pageCount) {
				pageNo = pageCount;
		}
		
		
		
		Pageable pageable = new PageRequest(pageNo -1>0 ?pageNo -1:0, pageSize);
		String pageSql = pageUtilToSql.getPageSql(sql + sql_where + " ORDER BY u.lastLoginDate desc", pageable);

		List<Map<String, Object>> maps = jdbcTemplate.queryForList(pageSql);
		List<Map<String, String>> list = Utilis.mapObjToString2(maps);
		//System.err.println(JSON.toJSONString(list, true));
		
		Page page = new Page(customerPageCount);
		page.setData(list);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	@Override
	public int findEventPageCount(String sql_count) {
		return jdbcTemplate.queryForObject(sql_count, Integer.class);
	}


	@Override
	public int updateSysPowerInfo(User spUser) {
	    String sql="UPDATE sp_user set name=?,email=?,phone=?,multi_login=?,user_type=? ,update_user = ?,update_time = now(),company_code = ?,org_code = ? where user_code=? ";
	    int sum=jdbcTemplate.update(sql,spUser.getName(),spUser.getEmail(),spUser.getPhone(),spUser.getMultiLogin(),spUser.getUserType(),spUser.getUpdateUser(),spUser.getCompanyCode(),spUser.getOrgCode(),spUser.getUserCode());
		return sum;
	}


	@Override
	public String addNewUser(User spUser) {
//		String sql="insert into sp_user values (?,?,?,?,?,?,?,?,?,now())";		
//		int sum=jdbcTemplate.update(sql,Utilis.getUUID(),spUser.getUserCode(),spUser.getName(),spUser.getPassword(),spUser.getEmail(),spUser.getPhone(),spUser.getMultiLogin(),spUser.getUserType(),spUser.getUpdateUser());
//		return sum>0;
		String sql="insert into sp_user values (?,?,?,?,?,?,?,?,?,?,?,now())";		
		String id=Utilis.getUUID();
		jdbcTemplate.update(sql,id,spUser.getUserCode(),spUser.getName(),spUser.getPassword(),spUser.getCompanyCode(),spUser.getOrgCode(),spUser.getEmail(),spUser.getPhone(),spUser.getMultiLogin(),spUser.getUserType(),spUser.getUpdateUser());
		return id;
	
	}


	public List<SpUser> EmailList() {
		String sql_ft=AliyunSmsUtils.format("user_code,name,email");
		String sql=" select "+sql_ft+" from sp_user where email not in (SELECT name FROM email where name is not null and formatCode='user' )";
		RowMapper<SpUser> rowMapper=new BeanPropertyRowMapper<SpUser>(SpUser.class);
		
		System.err.println("邮件下拉列表-=================="+sql);
		List<SpUser> list= jdbcTemplate.query(sql, rowMapper);
		return list;
	}


}
