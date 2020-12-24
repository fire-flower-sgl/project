package com.mhtech.platform.msrv.auth.login.service;

import java.util.List;
import java.util.Map;

import com.mobile.model.Email;
import com.mobile.model.EmailParameter;
import com.mobile.model.EmailRecord;
import com.mobile.model.Page;
import com.mobile.model.User;


/**
 * 
 * @ClassName:  EmailDao   
 * @Description: 邮件接口  
 * @author: mjl_
 * @date:  2019  10/8 13:00  
 */

public interface EmailService {
	
	//根据用户名获取账号密码
	Email findNamePassword(String userName);
	
	//根据用户名获取接收方数据
	Email findNoSys(String userName);
	
	//根据用户名获取接收方数据+账号密码
	Email find(String userName);
	
	//根据id查询对象
	Email find2(String id);
	
	//有权限发送邮件的用户列表
	List<User> userList1(); 
	List<Email> userList2(); 
	
	//新增一个用户
	int insert(Email email); 
	//依据邮箱查询用户表名称
	String name(String email);
	//群发的信息模板
	List<Email> List(); 
	//依据编码删除
	int delete(String id);
	//修改模板
	int update(Email email);
	//修改邮件用户
	int updateUser(Email email);
	//更新邮箱账号
	int updateEmail(String emailNew,String emailOld);
	//验证用户名是否唯一
	boolean yzName(String userName);
	
	//验证模板名是否唯一
	boolean yzNameMb(String userName);
	
	
	//验证用户邮箱是否唯一
	boolean yzEnailName(String name);
	
	
	//添加邮件发送记录
	int addEmailRecord(EmailRecord emailRecord);
	//查询所有邮件发送记录
	List<EmailRecord> emailRecordList();
	
	//查询一定时间段内所有邮件发送记录
	List<EmailRecord> emailRecordList(String startTime,String endTime);
	//显示所有-分页+模糊
	Page findEmailPage(Map<String, String> map);
	
	
	//查看用户的，收件人
	String [] to(String userName,String time);
	//详情
	EmailRecord fingXQ(String id);
	//依据邮箱查询用户信息
	Email fingName(String name);
	
	User fingUserName(String email);
	
	//依据用户编码查询用户表邮箱
	String emailName(String userCode);
	
	//查询所有用户收件人的邮箱集合
	List<Email> listToEmail(String to);
	//批量修改邮箱收件人to
	int[] emailToUpdate(List<Email> list);//批量修改

	
}
