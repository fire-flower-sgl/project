package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

public class AlTeamMemberDTO implements Serializable{

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

	public AlTeamMemberDTO(Long id, String teamCode, String userCode, String nickname, String email, Date createdTime,
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

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public AlTeamMemberDTO(String teamCode, String userCode, String nickname, String email, String icon) {
		super();
		this.teamCode = teamCode;
		this.userCode = userCode;
		this.nickname = nickname;
		this.email = email;
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public AlTeamMemberDTO(Long id, String teamCode, String userCode, String nickname, String email, Date createdTime) {
		super();
		this.id = id;
		this.teamCode = teamCode;
		this.userCode = userCode;
		this.nickname = nickname;
		this.email = email;
		this.createdTime = createdTime;
	}

	public AlTeamMemberDTO(String teamCode, String userCode, String nickname, String email) {
		super();
		this.teamCode = teamCode;
		this.userCode = userCode;
		this.nickname = nickname;
		this.email = email;
	}

	public AlTeamMemberDTO() {
		super();
	}

}
