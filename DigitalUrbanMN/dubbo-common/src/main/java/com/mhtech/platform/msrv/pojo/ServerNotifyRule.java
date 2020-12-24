package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

public class ServerNotifyRule implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1661543722979282752L;
	
	private int pageSize;

	private int pageNo;

	private Long id;

    private Long serverId;

    private Boolean isEnable;

    private String recvStartTime;

    private String recvEndTime;

    private String refuseStartTime;

    private String refuseEndTime;
    
    

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

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public String getRecvStartTime() {
        return recvStartTime;
    }

    public void setRecvStartTime(String recvStartTime) {
        this.recvStartTime = recvStartTime == null ? null : recvStartTime.trim();
    }

    public String getRecvEndTime() {
        return recvEndTime;
    }

    public void setRecvEndTime(String recvEndTime) {
        this.recvEndTime = recvEndTime == null ? null : recvEndTime.trim();
    }

    public String getRefuseStartTime() {
        return refuseStartTime;
    }

    public void setRefuseStartTime(String refuseStartTime) {
        this.refuseStartTime = refuseStartTime == null ? null : refuseStartTime.trim();
    }

    public String getRefuseEndTime() {
        return refuseEndTime;
    }

    public void setRefuseEndTime(String refuseEndTime) {
        this.refuseEndTime = refuseEndTime == null ? null : refuseEndTime.trim();
    }

	public ServerNotifyRule(Long id, Long serverId, Boolean isEnable, String recvStartTime, String recvEndTime,
			String refuseStartTime, String refuseEndTime) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.isEnable = isEnable;
		this.recvStartTime = recvStartTime;
		this.recvEndTime = recvEndTime;
		this.refuseStartTime = refuseStartTime;
		this.refuseEndTime = refuseEndTime;
	}

	public ServerNotifyRule() {
		super();
	}

	public ServerNotifyRule(Long serverId, Boolean isEnable, String recvStartTime, String recvEndTime,
			String refuseStartTime, String refuseEndTime) {
		super();
		this.serverId = serverId;
		this.isEnable = isEnable;
		this.recvStartTime = recvStartTime;
		this.recvEndTime = recvEndTime;
		this.refuseStartTime = refuseStartTime;
		this.refuseEndTime = refuseEndTime;
	}
    
    
}