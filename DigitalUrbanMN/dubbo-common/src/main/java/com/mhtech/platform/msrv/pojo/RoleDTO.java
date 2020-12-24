package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色请求参数对象
 * @author Administrator
 *
 */
public class RoleDTO implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
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
    
 
    
    
   
    public RoleDTO(String roleNum, String roleName, Date roleUpdateTime, String isUse, String roleRemark,
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

	public Date getRoleUpdateTime() {
		return roleUpdateTime;
	}

	public void setRoleUpdateTime(Date roleUpdateTime) {
		this.roleUpdateTime = roleUpdateTime;
	}

	public RoleDTO(String roleNum, String roleName, Date roleUpdateTime, String roleUpdateUser, String roleUser,
    		Long id) {
		super();
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.roleUpdateTime = roleUpdateTime;
		this.roleUpdateUser = roleUpdateUser;
		this.roleUser = roleUser;
		this.id = id;
	}

	public RoleDTO(String roleNum, String roleName, String roleUpdateUser, String roleUser, Long id, int pageSize,
			int pageNo) {
		super();
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.roleUpdateUser = roleUpdateUser;
		this.roleUser = roleUser;
		this.id = id;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public RoleDTO(String roleNum) {
		super();
		this.roleNum = roleNum;
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

	public RoleDTO(String roleNum, String roleName, int pageSize, int pageNo) {
		super();
		this.roleNum = roleNum;
		this.roleName = roleName;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
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

	public RoleDTO(String roleNum, String roleName) {
		super();
		this.roleNum = roleNum;
		this.roleName = roleName;
	}

	public RoleDTO(Long id) {
		super();
		this.id = id;
	}

	public RoleDTO() {
		super();
	}
    
    

}
