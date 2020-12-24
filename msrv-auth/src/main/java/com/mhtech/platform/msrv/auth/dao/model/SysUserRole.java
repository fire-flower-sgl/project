package com.mhtech.platform.msrv.auth.dao.model;
/**
 * 用户角色关联表
 * @author Administrator
 *
 */
public class SysUserRole {
    private Long id;

    private String userCode;

    private String roleNum;

  

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(String roleNum) {
        this.roleNum = roleNum == null ? null : roleNum.trim();
    }

	public SysUserRole() {
		super();
	}

	public SysUserRole(Long id, String userCode, String roleNum) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.roleNum = roleNum;
	}
    
}