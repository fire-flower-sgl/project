package com.mobile.model;

import java.io.Serializable;

/**
 *  mjl_
 *  SysRole
 *  用户角色类
 *  2019-10.22
 */
public class SysRole implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String roleNum;
	private String roleName;
	private String roleUser;
	private String roleUpdateTime;
	private String roleUpdateUser;
	private String isUse;
	private String roleRemark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleNum() {
		return roleNum;
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
	public String getRoleUpdateTime() {
		return roleUpdateTime;
	}
	public void setRoleUpdateTime(String roleUpdateTime) {
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
	public SysRole(String id, String roleNum, String roleName, String roleUser, String roleUpdateTime,
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
	
	public SysRole() {
		super();

	}
	@Override
	public String toString() {
		return "SysRole [id=" + id + ", roleNum=" + roleNum + ", roleName=" + roleName + ", roleUser=" + roleUser
				+ ", roleUpdateTime=" + roleUpdateTime + ", roleUpdateUser=" + roleUpdateUser + ", isUse=" + isUse
				+ ", roleRemark=" + roleRemark + "]";
	}
	
	
}
