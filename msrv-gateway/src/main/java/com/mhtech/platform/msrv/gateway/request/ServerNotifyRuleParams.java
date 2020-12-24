package com.mhtech.platform.msrv.gateway.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="通知规则结果集",description="通知规则结果集")
public class ServerNotifyRuleParams {

	private Long id;
	
	@ApiModelProperty("服务主键")
	@NotNull(message = "服务主键不能为空")
	@Min(value = 0, message = "服务主键错误")
    private Long serverId;

	@ApiModelProperty("是否接收通知")
    private Boolean isEnable;
    @NotBlank(message = "不能为空")
	@ApiModelProperty("每日接收开始的时间段")
    private String recvStartTime;
    @NotBlank(message = "不能为空")
	@ApiModelProperty("每日接收结束的时间段")
    private String recvEndTime;
    @NotBlank(message = "不能为空")
	@ApiModelProperty("每日拒收开始的时间段")
    private String refuseStartTime;
    @NotBlank(message = "不能为空")
    
	@ApiModelProperty("每日接收结束的时间段")
    private String refuseEndTime;

	public ServerNotifyRuleParams(Long id, Long serverId, Boolean isEnable, String recvStartTime, String recvEndTime,
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
		this.recvStartTime = recvStartTime;
	}

	public String getRecvEndTime() {
		return recvEndTime;
	}

	public void setRecvEndTime(String recvEndTime) {
		this.recvEndTime = recvEndTime;
	}

	public String getRefuseStartTime() {
		return refuseStartTime;
	}

	public void setRefuseStartTime(String refuseStartTime) {
		this.refuseStartTime = refuseStartTime;
	}

	public String getRefuseEndTime() {
		return refuseEndTime;
	}

	public void setRefuseEndTime(String refuseEndTime) {
		this.refuseEndTime = refuseEndTime;
	}

	public ServerNotifyRuleParams(Long serverId, Boolean isEnable, String recvStartTime, String recvEndTime,
			String refuseStartTime, String refuseEndTime) {
		super();
		this.serverId = serverId;
		this.isEnable = isEnable;
		this.recvStartTime = recvStartTime;
		this.recvEndTime = recvEndTime;
		this.refuseStartTime = refuseStartTime;
		this.refuseEndTime = refuseEndTime;
	}

	public ServerNotifyRuleParams() {
		super();
	}

	@Override
	public String toString() {
		return "ServerNotifyRuleParams [id=" + id + ", serverId=" + serverId + ", isEnable=" + isEnable
				+ ", recvStartTime=" + recvStartTime + ", recvEndTime=" + recvEndTime + ", refuseStartTime="
				+ refuseStartTime + ", refuseEndTime=" + refuseEndTime + "]";
	}

    
    
    
}
