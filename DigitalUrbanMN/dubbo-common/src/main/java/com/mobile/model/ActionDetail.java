package com.mobile.model;


/**
 * 操作详情
 * @author GM
 */
public class ActionDetail {

	private String id;
	
	private String usrActId;

	private String actionType;
	
	private String actionSqlId;
	
	private String sqlParams;

	public String getSqlParams() {
		return sqlParams;
	}

	public void setSqlParams(String sqlParams) {
		this.sqlParams = sqlParams;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsrActId() {
		return usrActId;
	}

	public void setUsrActId(String usrActId) {
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

	@Override
	public String toString() {
		return "ActionDetail [id=" + id + ", usrActId=" + usrActId + ", actionType=" + actionType + ", actionSqlId="
				+ actionSqlId + ", sqlParams=" + sqlParams + "]";
	}
	
	
}
