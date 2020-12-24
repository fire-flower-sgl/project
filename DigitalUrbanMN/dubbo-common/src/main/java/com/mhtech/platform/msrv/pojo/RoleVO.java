package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色返回对象
 * 
 * @author Administrator
 *
 */
public class RoleVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String roleNum;

	private String roleName;

	private String roleUser; 

	private Date roleUpdateTime;

	private String roleUpdateUser;

	private String isUse;

	private String roleRemark;

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public RoleVO() {
		super();
	}

	

	public String getRoleNum() {
		return roleNum;
	}

	public RoleVO(String roleNum, String roleName) {
		super();
		this.roleNum = roleNum;
		this.roleName = roleName;
	}

	
	
	
	
	public RoleVO(Long id, String roleNum, String roleName, Date roleUpdateTime, String isUse) {
		super();
		this.id = id;
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.roleUpdateTime = roleUpdateTime;
		this.isUse = isUse;
	}

	public RoleVO(Long id, String roleNum, String roleName, String roleUser, Date roleUpdateTime, String isUse) {
		super();
		this.id = id;
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.roleUser = roleUser;
		this.roleUpdateTime = roleUpdateTime;
		this.isUse = isUse;
	}



	public RoleVO(Long id, String roleNum, String roleName, String roleUser, Date roleUpdateTime, String roleUpdateUser,
			String isUse) {
		super();
		this.id = id;
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.roleUser = roleUser;
		this.roleUpdateTime = roleUpdateTime;
		this.roleUpdateUser = roleUpdateUser;
		this.isUse = isUse;
	}



	public void setRoleNum(String roleNum) {
		this.roleNum = roleNum;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(String roleUser) {
		this.roleUser = roleUser;
	}

	@Override
	public String toString() {
		return "RoleVO [id=" + id + ", roleNum=" + roleNum + ", roleName=" + roleName + ", roleUser=" + roleUser
				+ ", roleUpdateTime=" + roleUpdateTime + ", roleUpdateUser=" + roleUpdateUser + ", isUse=" + isUse
				+ ", roleRemark=" + roleRemark + "]";
	}



	public Date getRoleUpdateTime() {
		return roleUpdateTime;
	}

	public void setRoleUpdateTime(Date roleUpdateTime) {
		this.roleUpdateTime = roleUpdateTime;
	}

	public String getRoleUpdateUser() {
		return roleUpdateUser;
	}

	public void setRoleUpdateUser(String roleUpdateUser) {
		this.roleUpdateUser = roleUpdateUser;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public RoleVO(Long id, String roleNum, String roleName, String roleUser, Date roleUpdateTime,
			String roleUpdateUser, String isUse, String roleRemark) {
		super();
		this.id = id;
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.roleUser = roleUser;
		this.roleUpdateTime = roleUpdateTime;
		this.roleUpdateUser = roleUpdateUser;
		this.isUse = isUse;
		this.roleRemark = roleRemark;
	}
	
	
	
}
