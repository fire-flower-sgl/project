package com.mhtech.platform.msrv.gateway.request;

import java.util.Date;

/**
 * 角色请求参数
 * @author Administrator
 *
 */
public class RoleParams {
	
	private String roleNum;//角色编号
    private String roleName;//角色名称
    private Date roleUpdateTime;
    private String isUse;
    private String roleRemark;
	private	String roleUpdateUser;
    private	String roleUser;
    private	Long id;
	private  int pageSize;//分页-每页条数
	private  int pageNo;//分页-当前页
	
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
	public Date getRoleUpdateTime() {
		return roleUpdateTime;
	}
	public void setRoleUpdateTime(Date roleUpdateTime) {
		this.roleUpdateTime = roleUpdateTime;
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
	public String getRoleUpdateUser() {
		return roleUpdateUser;
	}
	public void setRoleUpdateUser(String roleUpdateUser) {
		this.roleUpdateUser = roleUpdateUser;
	}
	public String getRoleUser() {
		return roleUser;
	}
	public void setRoleUser(String roleUser) {
		this.roleUser = roleUser;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public RoleParams() {
		super();
	}
	public RoleParams(String roleNum, String roleName, Date roleUpdateTime, String isUse, String roleRemark,
			String roleUpdateUser, String roleUser, Long id, int pageSize, int pageNo) {
		super();
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.roleUpdateTime = roleUpdateTime;
		this.isUse = isUse;
		this.roleRemark = roleRemark;
		this.roleUpdateUser = roleUpdateUser;
		this.roleUser = roleUser;
		this.id = id;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}
	
	
	

}
