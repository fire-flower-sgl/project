package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

public class AlProjectMemberDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String prjCode;

	private String userCode;

	private Short teamRole;

	private Date createdTime;

	private String icon;
	public Long getId() {
		return id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrjCode() {
		return prjCode;
	}

	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Short getTeamRole() {
		return teamRole;
	}

	public void setTeamRole(Short teamRole) {
		this.teamRole = teamRole;
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

	public AlProjectMemberDTO(Long id, String prjCode, String userCode, Short teamRole, Date createdTime) {
		super();
		this.id = id;
		this.prjCode = prjCode;
		this.userCode = userCode;
		this.teamRole = teamRole;
		this.createdTime = createdTime;
	}

	public AlProjectMemberDTO(String prjCode, String userCode, Short teamRole) {
		super();
		this.prjCode = prjCode;
		this.userCode = userCode;
		this.teamRole = teamRole;
	}

	public AlProjectMemberDTO() {
		super();
	}

}
