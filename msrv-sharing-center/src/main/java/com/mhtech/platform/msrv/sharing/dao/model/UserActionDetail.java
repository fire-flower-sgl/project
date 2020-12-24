package com.mhtech.platform.msrv.sharing.dao.model;

public class UserActionDetail {
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
        this.actionType = actionType == null ? null : actionType.trim();
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
	public UserActionDetail(Long id, Long usrActId, String actionType) {
		super();
		this.id = id;
		this.usrActId = usrActId;
		this.actionType = actionType;
	}
	public UserActionDetail() {
		super();
	}
    
}