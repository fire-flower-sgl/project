package com.mobile.model;

import java.io.Serializable;

public class LoginUser implements Serializable{
	
	private String id;

	private String userCode;
	
    private String name;

    private String password;

    private String eMail;

    private String phone;

    private String multiLogin;
    
    private String companyCode;
    
    

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

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

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
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


	@Override
	public String toString() {
		return "LoginUser [id=" + id + ", userCode=" + userCode + ", name=" + name + ", password=" + password
				+ ", eMail=" + eMail + ", phone=" + phone + ", multiLogin=" + multiLogin + "]";
	}

	public LoginUser() {
		super();
	}

	public LoginUser(String id, String userCode, String name, String password, String eMail, String phone,
			String multiLogin) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.name = name;
		this.password = password;
		this.eMail = eMail;
		this.phone = phone;
		this.multiLogin = multiLogin;
	}

 
    
    
}

	