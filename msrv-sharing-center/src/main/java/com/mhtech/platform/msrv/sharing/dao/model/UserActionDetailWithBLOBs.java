package com.mhtech.platform.msrv.sharing.dao.model;

public class UserActionDetailWithBLOBs extends UserActionDetail {
    private String actionSqlId;

    private String sqlParams;

    public String getActionSqlId() {
        return actionSqlId;
    }

    public void setActionSqlId(String actionSqlId) {
        this.actionSqlId = actionSqlId == null ? null : actionSqlId.trim();
    }

    public String getSqlParams() {
        return sqlParams;
    }

    public void setSqlParams(String sqlParams) {
        this.sqlParams = sqlParams == null ? null : sqlParams.trim();
    }

	public UserActionDetailWithBLOBs(String actionSqlId, String sqlParams) {
		super();
		this.actionSqlId = actionSqlId;
		this.sqlParams = sqlParams;
	}
	public UserActionDetailWithBLOBs() {
		super();

	}
    
    
}