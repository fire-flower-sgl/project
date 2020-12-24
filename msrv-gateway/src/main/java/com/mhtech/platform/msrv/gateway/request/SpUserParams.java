package com.mhtech.platform.msrv.gateway.request;

import java.util.Date;

public class SpUserParams {

	private Long id;

	private String userCode;

	private String name;

	private String password;

	private String companyCode;

	private String orgCode;

	private String email;

	private String phone;

	private String multiLogin;

	private String userType;

	private String updateUser;

	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getUpdateTime() {
		return updateTime;
	}

	@Override
	public String toString() {
		return "SpUserParams [id=" + id + ", userCode=" + userCode + ", name=" + name + ", password=" + password
				+ ", companyCode=" + companyCode + ", orgCode=" + orgCode + ", email=" + email + ", phone=" + phone
				+ ", multiLogin=" + multiLogin + ", userType=" + userType + ", updateUser=" + updateUser
				+ ", updateTime=" + updateTime + "]";
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public SpUserParams(Long id, String userCode, String name, String password, String companyCode, String orgCode,
			String email, String phone, String multiLogin, String userType, String updateUser, Date updateTime) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.name = name;
		this.password = password;
		this.companyCode = companyCode;
		this.orgCode = orgCode;
		this.email = email;
		this.phone = phone;
		this.multiLogin = multiLogin;
		this.userType = userType;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	public SpUserParams() {
		super();
	}

}
