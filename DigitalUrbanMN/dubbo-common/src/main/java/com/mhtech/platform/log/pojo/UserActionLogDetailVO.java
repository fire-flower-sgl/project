package com.mhtech.platform.log.pojo;

import java.io.Serializable;


public class UserActionLogDetailVO implements Serializable{

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
	public UserActionLogDetailVO(Long id, Long usrActId, String actionType, String actionSqlId, String sqlParams) {
		super();
		this.id = id;
		this.usrActId = usrActId;
		this.actionType = actionType;
		this.actionSqlId = actionSqlId;
		this.sqlParams = sqlParams;
	}
	public String getSqlParams() {
		return sqlParams;
	}
	public void setSqlParams(String sqlParams) {
		this.sqlParams = sqlParams;
	}
	public UserActionLogDetailVO() {
		super();
	}
    
    
   
}
