package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SpUserVO implements Serializable{
	private static final long serialVersionUID = 1L;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    
	public SpUserVO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SpUserVO(Long id, String userCode, String name, String password, String companyCode, String orgCode,
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

	public SpUserVO(String userCode, String name, String password, String companyCode, String orgCode, String email,
			String phone, String multiLogin, String userType, String updateUser, Date updateTime) {
		super();
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

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    
    
    
}
