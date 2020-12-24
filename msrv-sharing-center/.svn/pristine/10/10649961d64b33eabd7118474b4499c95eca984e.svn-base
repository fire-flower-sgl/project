package com.mhtech.platform.msrv.sharing.dao.model;

import java.io.Serializable;
import java.util.Date;


public class UserActionLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String userCode;

    private String actionModule;

    private String actionNum;

    private Date actionStartTime;
    private Date actionEndTime;
    
    
	private SpUserLog spUserLog;
    
    public SpUserLog getSpUserLog() {
		return spUserLog;
	}

	public void setSpUserLog(SpUserLog spUserLog) {
		this.spUserLog = spUserLog;
	}


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

    public String getActionModule() {
        return actionModule;
    }
 
    //输出版本号
    public long getSerialVersionUID() {
    	  return serialVersionUID;
    }
    public void setActionModule(String actionModule) {
        this.actionModule = actionModule == null ? null : actionModule.trim();
    }

    public String getActionNum() {
        return actionNum;
    }

    public void setActionNum(String actionNum) {
        this.actionNum = actionNum == null ? null : actionNum.trim();
    }

    public Date getActionStartTime() {
        return actionStartTime;
    }

    public void setActionStartTime(Date actionStartTime) {
        this.actionStartTime = actionStartTime;
    }

    public Date getActionEndTime() {
        return actionEndTime;
    }

    public void setActionEndTime(Date actionEndTime) {
        this.actionEndTime = actionEndTime;
    }

	@Override
	public String toString() {
		return "UserActionLog [id=" + id + ", userCode=" + userCode + ", actionModule=" + actionModule + ", actionNum="
				+ actionNum + ", actionStartTime=" + actionStartTime + ", actionEndTime=" + actionEndTime + "]";
	}

	public UserActionLog(Long id, String userCode, String actionModule, String actionNum, Date actionStartTime,
			Date actionEndTime) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.actionModule = actionModule;
		this.actionNum = actionNum;
		this.actionStartTime = actionStartTime;
		this.actionEndTime = actionEndTime;
	}
	
	public UserActionLog() {
		super();

	}
	
	
    
}