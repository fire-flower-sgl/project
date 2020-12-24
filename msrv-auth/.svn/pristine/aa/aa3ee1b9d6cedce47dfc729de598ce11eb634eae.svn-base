package com.mhtech.platform.msrv.auth.login.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.auth.login.service.EmailService;
import com.mhtech.platform.msrv.auth.utils.PageUtilToSql;
import com.mobile.model.Email;
import com.mobile.model.EmailParameter;
import com.mobile.model.EmailRecord;
import com.mobile.model.Page;
import com.mobile.model.User;
import com.mobile.utils.EmailUtils;
import com.mobile.utils.Utilis;


//@Repository
@Service("loginEmailService")
public class EmailServiceImpl implements EmailService {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Value("${pageNo}")
	private int pageNo;
	@Value("${pageSize}")
	private int pageSize;
	@Autowired
	private PageUtilToSql pageUtilToSql;

	
	//根据用户名获取账号密码
	@Override
	public Email findNamePassword(String userName) {
		String sql=" SELECT  `name`,`password` from email where  user_name='"+userName+"'";
		RowMapper<Email> rowMapper=new BeanPropertyRowMapper<Email>(Email.class);
		Email email= jdbcTemplate.queryForObject(sql, rowMapper);	
		return email;
	}

	//根据用户名获取接收方数据
	@Override
	public Email findNoSys(String userName) {
		String sql=" SELECT  * from email where  user_name='"+userName+"' ";
		RowMapper<Email> rowMapper=new BeanPropertyRowMapper<Email>(Email.class);
		Email email= jdbcTemplate.queryForObject(sql, rowMapper);	
		return email;
	}

	@Override
	public Email find(String userName) {
		String sql=" SELECT  * from email where  user_name='"+userName+"'";
		RowMapper<Email> rowMapper=new BeanPropertyRowMapper<Email>(Email.class);
		Email email= jdbcTemplate.queryForObject(sql, rowMapper);	
		return email;
	}

	@Override
	public Email find2(String id) {
		String sql=" SELECT  * from email where  id='"+id+"' ";
		RowMapper<Email> rowMapper=new BeanPropertyRowMapper<Email>(Email.class);
		Email email= jdbcTemplate.queryForObject(sql, rowMapper);	
		return email;
	}

	@Override
	public List<Email> userList2() {
		String sql=" SELECT * from email where formatCode='user' ORDER BY email_time desc ";
		RowMapper<Email> rowMapper=new BeanPropertyRowMapper<Email>(Email.class);
		List<Email> list= jdbcTemplate.query(sql, rowMapper);
		return list;
	}
	@Override
	public List<User> userList1() {
		String sql=" select distinct user_code,`name`,email from sp_user  ";
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
		List<User> list= jdbcTemplate.query(sql, rowMapper);
		return list;
	}

	@Override
	public int insert(Email email) {
		   
		StringBuffer tos=new StringBuffer();
		String tox=null;
        if (email.getTo()!=null) {   	
        	String[] to=email.getTo();
        	for (int i = 0; i < email.getTo().length; i++) {
        		if (to[i]!=null) {
        			tos.append(JSONObject.toJSONString(to[i])+",");
				}		
    		}
        	if (tos.length()>0) {
        		tox= tos.substring(0, tos.length()-1);
			}       	
		}
			
		String sql=" INSERT INTO email VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,now()) ";
		int num= jdbcTemplate.update(sql,Utilis.getUUID(),email.getUserName()
				,email.getName(),email.getPassword(), tox , email.getSubject()
				,email.getContent(),email.getImgPath(),email.getImgId(),
				email.getCcUsers(),email.getFilePath(),email.getFormatCode(),email.getTemplate());	
		return num;
	}

	@Override
	public String name(String email) {
		String sql = "select `name` from sp_user where email='"+email+"' ";	
		try {
			return jdbcTemplate.queryForObject(sql, String.class);
		} catch (DataAccessException e) {
			return "";
		}			
	}

	@Override
	public List<Email> List() {
		String sql="SELECT id,user_name userName,`name`,`password`,`to`,`subject`,content from email where formatCode='groupSending' ORDER BY email_time desc ";
		RowMapper<Email> rowMapper=new BeanPropertyRowMapper<Email>(Email.class);
		List<Email> list= jdbcTemplate.query(sql, rowMapper);
		return list;
	}

	@Override
	public int delete(String id) {
		String sql = "SELECT count(1) from email where template = (SELECT user_name from email WHERE id = '"+id+"')";
		
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		if(count>0) {
			return 2;
		}else {
			sql=" DELETE FROM email where id = '"+id+"'";
			return jdbcTemplate.update(sql);
		}	
	}

	@Override
	public int update(Email email) {
		String sql="update email set user_name='"+email.getUserName()+"',content='"+email.getContent()+"',email_time=now() "
				  + " where id='"+email.getId()+"' and formatCode='groupSending' ";
		return jdbcTemplate.update(sql);
	}
	
	@Override
	public int updateUser(Email email) {
		
		StringBuffer tos=new StringBuffer();
		String tox=null;
		if (email.getTo()!=null||!"".equals(email.getTo().toString())) {
			String[] to=email.getTo();
			for (int i = 0; i < email.getTo().length; i++) {
				tos.append(JSONObject.toJSONString(to[i])+",");
			}
			if (tos.length()>0) {
				tox=tos.substring(0, tos.length()-1);
			}
			
		}	
		String sql=" update email set user_name=?,`name`=? ,`password`=?, `to`=?,template=?,email_time=now() where  id=? ";	
		return jdbcTemplate.update(sql,email.getUserName(),email.getName(),email.getPassword(),
				tox,email.getTemplate(),email.getId());
	}

	@Override
	public boolean yzName(String userName) {
		String sql = " SELECT COUNT(id) from email where user_name='"+userName+"' and formatCode='user'  ";
		int sum = jdbcTemplate.queryForObject(sql, Integer.class);
		if (sum > 0) {
			// 存在
			return true;
		} else {
			// 不存在
			return false;
		}	
	}

	@Override
	public int addEmailRecord(EmailRecord emailRecord) {
		
		String sql = " INSERT INTO email_record (id,user_name,sum,yes,no,time,user_email,`to`)VALUES "
				+ " ('"+Utilis.getUUID()+"','"+emailRecord.getUserName()+"'"
				+ ","+emailRecord.getSum()+","+emailRecord.getYes()+","+emailRecord.getNo()+",now(),"
				+ " '"+emailRecord.getUserEmail()+"','"+emailRecord.getTo()+"')";	
	
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<EmailRecord> emailRecordList() {
		//日志
		String sql=" SELECT * from email_record ORDER BY time desc ";
	
		RowMapper<EmailRecord> row=new BeanPropertyRowMapper<EmailRecord>(EmailRecord.class);

		List<EmailRecord> list= jdbcTemplate.query(sql, row);
		return list;
	}

	@Override
	public String[] to(String userName,String time) {
		String sql=" SELECT `to` from email_record where user_name='"+userName+"' and `time`='"+time+"'";
		
		try {
			String to= jdbcTemplate.queryForObject(sql, String.class);
			String[] ary=to.split(",");
			return ary;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean yzEnailName(String name) {
		String sql = " SELECT COUNT(id) from email where `name`='"+name+"' and formatCode='user'";
	
		int sum = jdbcTemplate.queryForObject(sql, Integer.class);
		if (sum > 0) {
			// 存在
			return true;
		} else {
			// 不存在
			return false;
		}	
	}
	
	@Override
	public List<EmailRecord> emailRecordList(String startTime, String endTime) {
		
        String sql=" SELECT * from email_record where  time>='"+startTime+"' and time<='"+endTime+"' ORDER BY time desc ";
		
		RowMapper<EmailRecord> row=new BeanPropertyRowMapper<EmailRecord>(EmailRecord.class);

		List<EmailRecord> list= jdbcTemplate.query(sql, row);
		return list;
	}

	@Override
	public EmailRecord fingXQ(String id) {
		String sql="SELECT * from email_record where id='"+id+"' ";
		RowMapper<EmailRecord> rowMapper=new BeanPropertyRowMapper<EmailRecord>(EmailRecord.class);
		EmailRecord x= jdbcTemplate.queryForObject(sql, rowMapper);	
		return x;
	}

	@Override
	public Email fingName(String name) {
		String sql=" SELECT  * from email where  name='"+name+"' and formatCode='user'";
	
		RowMapper<Email> rowMapper=new BeanPropertyRowMapper<Email>(Email.class);
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper);
		} catch (DataAccessException e) {
			return null;
		}
		
		
	}

	@Override
	public boolean yzNameMb(String userName) {
		String sql = " SELECT COUNT(id) from email where user_name='"+userName+"' and formatCode='groupSending' ";
		int sum = jdbcTemplate.queryForObject(sql, Integer.class);
		if (sum > 0) {
			// 存在
			return true;
		} else {
			// 不存在
			return false;
		}		
	}

	@Override
	public int updateEmail(String emailNew,String emailOld) {
		String sql=" update email set `name`='"+emailNew+"' where `name`='"+emailOld+"' and formatCode='user' ";
		return jdbcTemplate.update(sql);
	}

	@Override
	public String emailName(String userCode) {
		String sql="SELECT email from sp_user where user_code='"+userCode+"'";
		return jdbcTemplate.queryForObject(sql, String.class);
	}

	@Override
	public User fingUserName(String email) {
		String sql="  SELECT  * from sp_user where  `email`='"+email+"' ";
	
		RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper);
		} catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public Page findEmailPage(Map<String, String> map) {
		// TODO Auto-generated method stub

		 String sql=" SELECT id,user_name userName,sum,yes,`no`,time,`to`,user_email userEmail from email_record where 1=1  ";
		 String sql_count = "SELECT  COUNT(*) from email_record where  1=1 ";
		 String sql_where = "";
		 
		 if (map.containsKey("startTime") && map.containsKey("endTime")) {
				String startTime=map.get("startTime");
				String endTime=map.get("endTime");
				sql_where =sql_where+" and time>='"+startTime+"' and time<='"+endTime+"' ";
				sql_count=sql_count+" and time>='"+startTime+"' and time<='"+endTime+"' ";
				System.err.println("----------"+sql_count);
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
			int customerPageCount = findPageCount(sql_count);
			int pageCount = customerPageCount % pageSize == 0 ? (customerPageCount / pageSize):(customerPageCount / pageSize + 1);
			// 当前页数大于总页数 将当前页数等于总页数
			if (pageNo > pageCount) {
					pageNo = pageCount;
			}
			
				
			
			Pageable pageable = new PageRequest(pageNo -1>0 ?pageNo -1:0, pageSize);
			String pageSql = pageUtilToSql.getPageSql(sql + sql_where + " ORDER BY time desc ", pageable);
			List<Map<String, Object>> maps = jdbcTemplate.queryForList(pageSql);
			List<Map<String, String>> list = Utilis.mapObjToString2(maps);
			
			//去除to中的双引号
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).containsKey("to")) {
					String[] to = list.get(i).get("to").split(",");
					StringBuffer tos = new StringBuffer();
					for (int j = 0; j < to.length; j++) {
						to[j] = JSON.parse(to[j]).toString();
						tos.append(to[j] + ",");
					}
					list.get(i).put("to",tos.substring(0, tos.length() - 1));
				}
			}
			Page page = new Page(customerPageCount);
			page.setData(list);
			page.setPageSize(pageSize);
			page.setPageNo(pageNo);
		return page;
	}

	public int findPageCount(String sql_count) {
		return jdbcTemplate.queryForObject(sql_count, Integer.class);
	}

	@Override
	public List<Email> listToEmail(String to) {
	    String sql="SELECT id,`to` from email WHERE formatCode='user' and `to` LIKE '%"+to+"%'";
	    RowMapper<Email> row=new BeanPropertyRowMapper<Email>(Email.class);
		List<Email> list= jdbcTemplate.query(sql, row);
		return list;
	}

	@Override
	public int[] emailToUpdate(List<Email> list) {
		
		 List<Object[]> listEmail = new ArrayList<>();
		 for (Email e : list) {
			
			 listEmail.add(new Object[] {StringUtils.join(e.getTo(),","),e.getId()} );
		 }
	     final String sql = " update email set `to`=? WHERE formatCode='user' and id=? ";
	    
         int[] sum=  jdbcTemplate.batchUpdate(sql, listEmail);
         
		 return sum;
	}

}
