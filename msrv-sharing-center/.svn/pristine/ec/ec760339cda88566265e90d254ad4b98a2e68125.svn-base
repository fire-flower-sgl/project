package com.mhtech.platform.msrv.sharing.dao.model;

import java.io.Serializable;

/**
 * 
 * sp_user_action_log整合类
 * @author mjl_
 * 2020-2-11
 * 
 */
public class SpUserLog implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long usrActId;
    private String actionType;
    private String actionSqlId;
    private String sqlParams;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUsrActId() {
		return usrActId;
	}
	public void setUsrActId(Long usrActId) {
		this.usrActId = usrActId;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getActionSqlId() {
		return actionSqlId;
	}
	public void setActionSqlId(String actionSqlId) {
		this.actionSqlId = actionSqlId;
	}
	public String getSqlParams() {
		return sqlParams;
	}
	public void setSqlParams(String sqlParams) {
		this.sqlParams = sqlParams;
	}
	public SpUserLog(Long id, Long usrActId, String actionType, String actionSqlId, String sqlParams) {
		super();
		this.id = id;
		this.usrActId = usrActId;
		this.actionType = actionType;
		this.actionSqlId = actionSqlId;
		this.sqlParams = sqlParams;
	}
	public SpUserLog() {
		super();
	}
	@Override
	public String toString() {
		return "SpUserLog [id=" + id + ", usrActId=" + usrActId + ", actionType=" + actionType + ", actionSqlId="
				+ actionSqlId + ", sqlParams=" + sqlParams + "]";
	}
    
    
}
