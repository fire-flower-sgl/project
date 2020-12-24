package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

public class AlTeamMemberVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;

	private String teamCode;

	private String userCode;

	private String nickname;

	private String email;

	private Date createdTime;
	private String icon;
	public Long getId() {
		return id;
	}

	public String getIcon() {
		return icon;
	}

	public AlTeamMemberVO(Long id, String teamCode, String userCode, String nickname, String email, Date createdTime,
			String icon) {
		super();
		this.id = id;
		this.teamCode = teamCode;
		this.userCode = userCode;
		this.nickname = nickname;
		this.email = email;
		this.createdTime = createdTime;
		this.icon = icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode == null ? null : teamCode.trim();
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode == null ? null : userCode.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public AlTeamMemberVO(Long id, String teamCode, String userCode, String nickname, String email, Date createdTime) {
		super();
		this.id = id;
		this.teamCode = teamCode;
		this.userCode = userCode;
		this.nickname = nickname;
		this.email = email;
		this.createdTime = createdTime;
	}

	public AlTeamMemberVO(String nickname, String icon) {
		super();
		this.nickname = nickname;
		this.icon = icon;
	}

	public AlTeamMemberVO() {
		super();
	}
	
	
	
	
}
