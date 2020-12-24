package com.mhtech.platform.msrv.auth.login.service.impl;

import com.mhtech.platform.msrv.auth.login.service.UserService;
import com.mobile.model.LoginUser;
import com.mobile.model.User;
import com.mobile.utils.MD5HashUtil;
import com.mobile.utils.Utilis;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserImpl
 * @Description TODO
 * @Author admini
 * @Date 2019/8/29 17:19
 * @Version 1.0
 */
//@Repository
@Service("loginUserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findUser(String name) {
        String sql = "select * from user where name='" + name + "'";
        try {
            RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);
            return jdbcTemplate.queryForObject(sql, userRowMapper);
        } catch (Exception e) {
            return null;
        }
    }

	@Override
	public LoginUser findUserByNameAndPwd(String userName, String password) {
		String sql = "select id ,user_code userCode,name,password,email,phone,Multi_login multiLogin from sp_user where user_code= '"+userName+"' and password = '"+password+"'";
        try {
            RowMapper<LoginUser> userRowMapper = new BeanPropertyRowMapper<>(LoginUser.class);
            return jdbcTemplate.queryForObject(sql, userRowMapper);
        } catch (Exception e) {
            return null;
        }
	}

	@Override
	public boolean addLoginUser(String userCode, String ip, String browserName) {
		String sql = "insert into sp_user_online values (?,?,?,?,now())";
		return jdbcTemplate.update(sql,Utilis.getUUID(),userCode,ip,browserName)>0;
	}
	

	
	@Override
	public boolean findIpIsExist(String ip) {
		String sql = "select * from sp_user_online where login_ip = ?";
		return jdbcTemplate.queryForList(sql, ip).size()>0;
	}

	@Override
	public boolean findWebIsExist(String browserName) {
		String sql = "select * from sp_user_online where login_web = ?";
		return jdbcTemplate.queryForList(sql, browserName).size()>0;
	}

	@Override
	public boolean removeUserByUserCode(String userCode) {
		String sql = "delete from sp_user_online where user_code = ?";
		return jdbcTemplate.update(sql, userCode)>0;
	}

	@Override
	public boolean removeUserByWebType(String userCode, String browserName) {
		String sql = "delete from sp_user_online where user_code = ? and login_web = ?";
		return jdbcTemplate.update(sql,userCode,browserName)>0;
	}

	@Override
	public boolean addLoginUserLog(String loginLogId,String userCode, String ip, String browserName, String IsNormal) {
		String sql = "insert into  sp_user_login_log values (?,?,?,?,now(),?,?)";
		return jdbcTemplate.update(sql,loginLogId,userCode,ip,browserName,null,IsNormal)>0;
	}

	@Override
	public boolean updatePwd(String userAccount ,String newPwd) {
		String usercode = findUserCode(userAccount);
		System.out.println("重置密码账户:"+usercode);
		String newPassword = MD5HashUtil.md5hash(newPwd, usercode).toString();
		System.out.println("重置后密码:"+newPassword);
		int email = userAccount.indexOf("@");
		String sql = "";
		if(email!=-1) {
			sql = "update sp_user set password = ? where email = ?";	
		}else {
			sql = "update sp_user set password = ? where phone = ?";	
		}
		return jdbcTemplate.update(sql,newPassword,userAccount)>0;	
	}

	@Override
	public String findUserCode(String account) {
		int email = account.indexOf("@");
		String sql = "";
		if(email!=-1) {
			sql = "select user_code userCode from sp_user where email = ?";	
		}else {
			sql = "select user_code userCode from sp_user where phone = ?";
		}
		return jdbcTemplate.queryForObject(sql,new Object[] {account},String.class);
	}

	@Override
	public boolean checkAccountExist(String account) {
		int email = account.indexOf("@");
		String sql = "";
		if(email!=-1) {
			sql = "select * from sp_user where email = ?";	
		}else {
			sql = "select * from sp_user where phone = ?";
		}
		return jdbcTemplate.queryForList(sql,account).size()!=0;	
	}

	@Override
	public String queryParamByKey(String key) {
		String sql = "SELECT value from  sys_configuration where name = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {key}, String.class);
	}

	@Override
	public boolean addLogOffDate(String  loginLogId) {
		String sql = " UPDATE sp_user_login_log set log_off_date=?  where  login_log_id=?  ";
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		  return jdbcTemplate.update(sql,df.format(new Date()),loginLogId)>0;
	}

	@Override
	public String userEmail(String userCode) {
		
		String sql = "  SELECT email from  sp_user where user_code='"+userCode+"' ";
		return jdbcTemplate.queryForObject(sql, String.class);
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.auth.login.service.UserService#queryUserIsNormal(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean queryUserIsNormal(String userCode, String ip) {
		String sql = "select * from sp_user_login_log where user_code = ? and log_ip = ?";
		return jdbcTemplate.queryForList(sql, userCode, ip).size() < 10;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.auth.login.service.UserService#findUserByNameAndPwdAndPlatForm(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public LoginUser findUserByNameAndPwdAndPlatForm(String userName, String password, String platForm) {
		String sql = "select a.id ,a.user_code userCode,a.name,a.password,a.email,a.phone,a.Multi_login multiLogin from sp_user a "
					+ " LEFT JOIN sys_user_account b on a.id=b.user_id"
					+ " where a.user_code= '"+userName+"' and a.password = '"+password+"' and plat_type='"+platForm+"'";
        System.err.println(sql);
		try {
            RowMapper<LoginUser> userRowMapper = new BeanPropertyRowMapper<>(LoginUser.class);
            return jdbcTemplate.queryForObject(sql, userRowMapper);
        } catch (Exception e) {
            return null;
        }
	}





	
}
