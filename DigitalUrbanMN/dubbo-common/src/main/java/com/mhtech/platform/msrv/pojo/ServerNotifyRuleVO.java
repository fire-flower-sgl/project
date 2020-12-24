package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="通知规则-响应类",description="告警通知规则-响应类" )
public class ServerNotifyRuleVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5815393709632443774L;
	
		@ApiModelProperty(value="id主键")
	 	private Long id;
		
		@ApiModelProperty(value="服务编号")
	    private Long serverId;

		@ApiModelProperty(value="是否接收通知")
	    private Boolean isEnable;

		@ApiModelProperty(value="接收开始时间")
	    private String recvStartTime;
		
		@ApiModelProperty(value="接收停止时间")
	    private String recvEndTime;
		
		@ApiModelProperty(value="拒收开始时间")
	    private String refuseStartTime;
		
		@ApiModelProperty(value="拒收停止时间")
	    private String refuseEndTime;

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

		public ServerNotifyRuleVO(Long id, Long serverId, Boolean isEnable, String recvStartTime, String recvEndTime,
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

		public ServerNotifyRuleVO() {
			super();
		}
	    
	    

}
