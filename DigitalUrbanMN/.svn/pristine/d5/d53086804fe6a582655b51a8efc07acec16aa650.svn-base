package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@ApiModel("告警记录详情")
public class ServerNotifyLog implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3941956128995957832L;

	@ApiModelProperty(value = "记录ID", example = "55550000")
	@JsonSerialize(using=ToStringSerializer.class)
	private Long id;
	
	@ApiModelProperty(value = "服务KEY", example = "10000")
    private Long serverId;
	
	@ApiModelProperty("告警级别")
	private Short level;
	
	@ApiModelProperty("所属业务")
	private String serverBiz = "主机10000";
	
	@ApiModelProperty("所属服务器")
	private String serverName = "192.168.1.101";
	
	@ApiModelProperty("发生时间")
	private Date happenTime;
	
	@ApiModelProperty("持续时间（文本形式包含单位）")
	private String lastTimes = "3min";

	@ApiModelProperty(value = "管理员", example = "Jack")
    private String username;
	
	@ApiModelProperty(value = "告警指标", example = "磁盘")
	private String paramName;
	
	@ApiModelProperty(value = "通知联系人编码", example = "fzumo001")
	private String userCode;

    @ApiModelProperty(value = "**", hidden = true)
    private String alertLogs;

    @ApiModelProperty(value = "是否发送", example = "已发送")
    private Boolean isSend;
    
    @ApiModelProperty(value = "是否通知")
    private Short notified;

    @ApiModelProperty(value = "**", hidden = true)
    private Short status;

    @ApiModelProperty(value = "发生时间", example = "2020年3月10日16:21:51")
    private Date createdTime;

    @ApiModelProperty(value = "告警内容", example = "CPU占用过高")
    private String content;
    
    @ApiModelProperty(value = "告警规则ID", example = "3366984")
    private Long alertId;

    @ApiModelProperty(value = "临时停止监听开始时间", example = "2020-05-11 14:40:00")
    private String tempStoptimeStart;
    
    @ApiModelProperty(value = "临时停止监听暂停时间", example = "2020-05-11 14:50:00")
    private String tempStoptimeEnd;
    
    
    
	public String getTempStoptimeStart() {
		return tempStoptimeStart;
	}

	public void setTempStoptimeStart(String tempStoptimeStart) {
		this.tempStoptimeStart = tempStoptimeStart;
	}

	public String getTempStoptimeEnd() {
		return tempStoptimeEnd;
	}

	public void setTempStoptimeEnd(String tempStoptimeEnd) {
		this.tempStoptimeEnd = tempStoptimeEnd;
	}


	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Short getNotified() {
		return notified;
	}

	public void setNotified(Short notified) {
		this.notified = notified;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getServerBiz() {
		return serverBiz;
	}

	public void setServerBiz(String serverBiz) {
		this.serverBiz = serverBiz;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Date getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

	public String getLastTimes() {
		return lastTimes;
	}

	public void setLastTimes(String lastTimes) {
		this.lastTimes = lastTimes;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAlertLogs() {
        return alertLogs;
    }

    public void setAlertLogs(String alertLogs) {
        this.alertLogs = alertLogs == null ? null : alertLogs.trim();
    }

    public Boolean getIsSend() {
        return isSend;
    }

    public void setIsSend(Boolean isSend) {
        this.isSend = isSend;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}