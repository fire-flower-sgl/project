package com.mhtech.platform.msrv.sharing.config;

public class UserActionDetailParameter {

	 private int pageSize;// 分页-每页条数
	 private int pageNo;// 分页-当前页

	 private Long id;
     private Long usrActId;
     private String actionType;
     private String actionSqlId;
     private String sqlParams;
     
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
     
     
}


