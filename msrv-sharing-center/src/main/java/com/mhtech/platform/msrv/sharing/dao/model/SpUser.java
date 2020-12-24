package com.mhtech.platform.msrv.sharing.dao.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SpUser implements Serializable {

	private String id;//编码
	private String userCode;//用户编码
	private String name;//用户姓名 
	private String password;//密码
	private String email;//邮箱地址
	private String phone;//手机号
	private String multiLogin;//多地点登录标示
	private String userType;//用户类型
	private String updateUser;//用户类型
	private String updateTime;//用户类型
	private String companyCode;//企业编码
	private String orgCode;//组织编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMultiLogin() {
		return multiLogin;
	}
	public void setMultiLogin(String multiLogin) {
		this.multiLogin = multiLogin;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
