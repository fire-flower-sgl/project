package com.mhtech.platform.log.pojo;

import java.io.Serializable;

public class UserActionLogDetailDTO implements Serializable{
	private static final long serialVersionUID = 1L;


	private Long id;
    private Long usrActId;
    private String actionType;
    private String actionSqlId;
    private String sqlParams;
    
    

    private int pageSize;// 分页-每页条数
   	private int pageNo;// 分页-当前页
   	
   	
   	
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
	public UserActionLogDetailDTO() {
		super();
	}
	public UserActionLogDetailDTO(Long id, Long usrActId, String actionType, String actionSqlId, String sqlParams,
			int pageSize, int pageNo) {
		super();
		this.id = id;
		this.usrActId = usrActId;
		this.actionType = actionType;
		this.actionSqlId = actionSqlId;
		this.sqlParams = sqlParams;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}
    
}
