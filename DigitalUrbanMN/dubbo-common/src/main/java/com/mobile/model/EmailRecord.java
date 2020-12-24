package com.mobile.model;

import java.io.Serializable;

/**
   *  邮件发送记录实现类
 * @author Administrator
 *
 */
public class EmailRecord implements Serializable{

	
	private String id;
	private String userName;
	private int sum;
	private int yes;
	private int no;
	private String time;
	
	private String name;//接收额外参数，无数据库字段
	
	private String userEmail;//发件人邮箱
	
    public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String to;
	
    
    
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getYes() {
		return yes;
	}
	public void setYes(int yes) {
		this.yes = yes;
	}

	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public EmailRecord(String userName, int sum, int yes, int no) {
		super();
		this.userName = userName;
		this.sum = sum;
		this.yes = yes;
		this.no = no;
	}

	public EmailRecord(String userName, int sum, int yes, int no, String to) {
		super();
		this.userName = userName;
		this.sum = sum;
		this.yes = yes;
		this.no = no;
		this.to = to;
	}

	public EmailRecord(String userName, int sum, int yes, int no, String userEmail, String to) {
		super();
		this.userName = userName;
		this.sum = sum;
		this.yes = yes;
		this.no = no;
		this.userEmail = userEmail;
		this.to = to;
	}

	public EmailRecord() {
		super();
	}


	
}
