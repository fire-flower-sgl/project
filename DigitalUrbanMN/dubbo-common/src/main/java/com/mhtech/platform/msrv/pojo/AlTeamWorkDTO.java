package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

public class AlTeamWorkDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer teamCode;

	private String teamName;

	private String mobile;

	private String email;

	private Date createdTime;

	public Integer getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(Integer teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AlTeamWorkDTO(Integer teamCode, String teamName, String mobile, String email) {
		super();
		this.teamCode = teamCode;
		this.teamName = teamName;
		this.mobile = mobile;
		this.email = email;
	}

	public AlTeamWorkDTO(Integer teamCode, String teamName, String mobile, String email, Date createdTime) {
		super();
		this.teamCode = teamCode;
		this.teamName = teamName;
		this.mobile = mobile;
		this.email = email;
		this.createdTime = createdTime;
	}

	public AlTeamWorkDTO() {
		super();
	}

}
