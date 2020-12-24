package com.mhtech.platform.msrv.gateway.request;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.mhtech.platform.msrv.pojo.AlertRuleInfo;
import com.mhtech.platform.msrv.pojo.ParamAlertInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 新增监控指标指标 预警规则 请求参数类
 * @author Administrator
 *
 */
@ApiModel(value="监控指标和预警规则结果集",description="监控指标和预警规则结果集")
public class ParamAlertParams {

	@ApiModelProperty("监控指标对象")
	private ParamAlertInfo paramAlertInfo;
	@ApiModelProperty("监控规则对象")
	private AlertRuleInfo alertRuleInfo;
	
	public ParamAlertInfo getParamAlertInfo() {
		return paramAlertInfo;
	}

	public void setParamAlertInfo(ParamAlertInfo paramAlertInfo) {
		this.paramAlertInfo = paramAlertInfo;
	}

	public AlertRuleInfo getAlertRuleInfo() {
		return alertRuleInfo;
	}

	public void setAlertRuleInfo(AlertRuleInfo alertRuleInfo) {
		this.alertRuleInfo = alertRuleInfo;
	}

//	//sp_param_alert 字段
//	@ApiModelProperty("服务主键")
//	@NotNull(message = "服务主键不能为空")
//	@Min(value = 0, message = "服务主键错误")
//	private Long serverId;
//	
//	@ApiModelProperty("监测字段")
//	@NotBlank(message = "监测字段不能为空")
//	@Length(max = 64, message = "监测字段错误")
//	private String paramName;
//
//	@ApiModelProperty("监测字段别名")
//	@NotBlank(message = "监测字段别名不能为空")
//	@Length(max = 64, message = "监测字段别名长度过长")
//	private String paramAlias;
//	
//	@ApiModelProperty("预警值")
//	@Min(value = 0, message = "预警值错误")
//	private BigDecimal alertValue;
//	
//	@ApiModelProperty("预警编码")
//	@Length(max = 64, message = "预警编码长度过长")
//	@NotBlank(message = "不能为空")
//	private String alertCode;
//	    
//	@ApiModelProperty("预警类型")
//	@Length(max = 64, message = "预警类型长度过长")
//	@NotBlank(message = "不能为空")
//	private String alertType;
//
//	@ApiModelProperty("状态")
//	@NotNull(message = "status不能为空")
//	private Short status;
//	
//	@ApiModelProperty("创建时间")
//	private Date createdTime;
//
//	//sp_server_alert_rule 字段
//	
//	@ApiModelProperty("预警上限")
//	@NotNull(message = "预警上限不能为空")
//	private Short alertLimit;
//	
//	@ApiModelProperty("间隔龄")
//	@NotNull(message = "间隔龄不能为空")
//	private Long duration;
//	
//	@ApiModelProperty("预警级别")
//	@NotNull(message = "预警级别不能为空")
//	private Short level;
//	
//	@ApiModelProperty("联系人")
//	@NotBlank(message = "联系人不能为空")
//	private String contacts;

	
//	
//	public Short getAlertLimit() {
//		return alertLimit;
//	}
//
//	public void setAlertLimit(Short alertLimit) {
//		this.alertLimit = alertLimit;
//	}
//
//	public Long getDuration() {
//		return duration;
//	}
//
//	public void setDuration(Long duration) {
//		this.duration = duration;
//	}
//
//	public Short getLevel() {
//		return level;
//	}
//
//	public void setLevel(Short level) {
//		this.level = level;
//	}
//
//	public String getContacts() {
//		return contacts;
//	}
//
//	public void setContacts(String contacts) {
//		this.contacts = contacts;
//	}
//
//	public Long getServerId() {
//		return serverId;
//	}
//
//	public void setServerId(Long serverId) {
//		this.serverId = serverId;
//	}
//
//	public String getParamName() {
//		return paramName;
//	}
//
//	public void setParamName(String paramName) {
//		this.paramName = paramName;
//	}
//
//	public String getParamAlias() {
//		return paramAlias;
//	}
//
//	public void setParamAlias(String paramAlias) {
//		this.paramAlias = paramAlias;
//	}
//
//	public BigDecimal getAlertValue() {
//		return alertValue;
//	}
//
//	public void setAlertValue(BigDecimal alertValue) {
//		this.alertValue = alertValue;
//	}
//
//	public String getAlertCode() {
//		return alertCode;
//	}
//
//	public void setAlertCode(String alertCode) {
//		this.alertCode = alertCode;
//	}
//
//	public String getAlertType() {
//		return alertType;
//	}
//
//	public void setAlertType(String alertType) {
//		this.alertType = alertType;
//	}
//
//	public Short getStatus() {
//		return status;
//	}
//
//	public void setStatus(Short status) {
//		this.status = status;
//	}
//
//	public Date getCreatedTime() {
//		return createdTime;
//	}
//
//	public void setCreatedTime(Date createdTime) {
//		this.createdTime = createdTime;
//	}
//
//	public ParamAlertParams(Long serverId, String paramName, String paramAlias, BigDecimal alertValue, String alertCode,
//			String alertType, Short status, Date createdTime) {
//		super();
//		this.serverId = serverId;
//		this.paramName = paramName;
//		this.paramAlias = paramAlias;
//		this.alertValue = alertValue;
//		this.alertCode = alertCode;
//		this.alertType = alertType;
//		this.status = status;
//		this.createdTime = createdTime;
//	}

	public ParamAlertParams() {
		super();
	}

	@Override
	public String toString() {
		return "ParamAlertParams [paramAlertInfo=" + paramAlertInfo + ", alertRuleInfo=" + alertRuleInfo + "]";
	}

//	@Override
//	public String toString() {
//		return "ParamAlertParams [serverId=" + serverId + ", paramName=" + paramName + ", paramAlias=" + paramAlias
//				+ ", alertValue=" + alertValue + ", alertCode=" + alertCode + ", alertType=" + alertType + ", status="
//				+ status + ", createdTime=" + createdTime + ", alertLimit=" + alertLimit + ", duration=" + duration
//				+ ", level=" + level + ", contacts=" + contacts + "]";
//	}


	
	
	
}
