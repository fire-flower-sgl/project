package com.mhtech.platform.msrv.auth.bean.pojo.request;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserAuthenParams {

	@NotBlank(message = "用户名不能为空")
	@Size(min = 6, max = 64, message = "用户名格式错误")
	private String userName;
	
	@NotBlank(message = "密码不能为空")
	@Size(min = 6, max = 64, message = "密码格式错误")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
