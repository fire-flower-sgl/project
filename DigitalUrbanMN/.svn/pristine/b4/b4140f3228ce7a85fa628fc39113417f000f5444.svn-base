package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="预警规则结果集",description="预警规则结果集")
public class AlertRuleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 949411151141089459L;

	private Long id;
	@ApiModelProperty("服务主键")
	@NotNull(message = "所属服务KEY错误")
	private Long serverId;
	
	@ApiModelProperty("指标名称")
	@Length(max = 64, message = "指标名称错误")
	@NotBlank(message = "指标名称错误")
	private String paramName;
	@ApiModelProperty("预警上限")
	@NotNull(message = "预警上限不能为空")
	@Min(value = 1, message = "预警上限值错误")
	private Short alertLimit;
	@ApiModelProperty("间隔龄")
	@NotNull(message = "间隔周期不能为空")
	@Min(value = 1, message = "间隔周期错误")
	private Long duration;
	@ApiModelProperty("预警级别")
	private Short level;
	@ApiModelProperty("联系人")
	private String contacts;


	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date stopListenStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date stopListenEnd;


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public AlertRuleInfo(@Length(max = 64, message = "所属服务KEY错误") @NotBlank(message = "所属服务KEY错误") Long serverId,
			@Length(max = 64, message = "指标名称错误") @NotBlank(message = "指标名称错误") String paramName,
			@NotNull(message = "预警上限不能为空") @Min(value = 1, message = "预警上限值错误") Short alertLimit,
			@NotNull(message = "间隔周期不能为空") @Min(value = 1, message = "间隔周期错误") Long duration, Short level,
			String contacts, Date createdTime) {
		super();
		this.serverId = serverId;
		this.paramName = paramName;
		this.alertLimit = alertLimit;
		this.duration = duration;
		this.level = level;
		this.contacts = contacts;
		this.createdTime = createdTime;
	}

	public AlertRuleInfo(Long id,
			@Length(max = 64, message = "所属服务KEY错误") @NotBlank(message = "所属服务KEY错误") Long serverId,
			@Length(max = 64, message = "指标名称错误") @NotBlank(message = "指标名称错误") String paramName,
			@NotNull(message = "预警上限不能为空") @Min(value = 1, message = "预警上限值错误") Short alertLimit,
			@NotNull(message = "间隔周期不能为空") @Min(value = 1, message = "间隔周期错误") Long duration, Short level,
			String contacts, Date createdTime) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.alertLimit = alertLimit;
		this.duration = duration;
		this.level = level;
		this.contacts = contacts;
		this.createdTime = createdTime;
	}
	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}


	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getStopListenStart() {
		return stopListenStart;
	}

	public void setStopListenStart(Date stopListenStart) {
		this.stopListenStart = stopListenStart;
	}

	public Date getStopListenEnd() {
		return stopListenEnd;
	}

	public void setStopListenEnd(Date stopListenEnd) {
		this.stopListenEnd = stopListenEnd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Short getAlertLimit() {
		return alertLimit;
	}

	public void setAlertLimit(Short alertLimit) {
		this.alertLimit = alertLimit;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public AlertRuleInfo() {
		super();
	}

	@Override
	public String toString() {
		return "AlertRuleInfo [id=" + id + ", serverId=" + serverId + ", paramName=" + paramName + ", alertLimit="
				+ alertLimit + ", duration=" + duration + ", level=" + level + ", contacts=" + contacts
				+ ", createdTime=" + createdTime + ", stopListenStart=" + stopListenStart + ", stopListenEnd="
				+ stopListenEnd + "]";
	}
}
