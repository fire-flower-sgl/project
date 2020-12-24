package com.mhtech.platform.msrv.auth.dao.model;

import java.util.Date;

public class SysBlackList {
    private String id;

    private String ip;

    private String memo;

    private Integer isLocked;

    private Date lockedTime;

    private Date createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Date getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Date lockedTime) {
        this.lockedTime = lockedTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

	@Override
	public String toString() {
		return "SysBlackList [id=" + id + ", ip=" + ip + ", memo=" + memo + ", isLocked=" + isLocked + ", lockedTime="
				+ lockedTime + ", createdTime=" + createdTime + "]";
	}
    
}