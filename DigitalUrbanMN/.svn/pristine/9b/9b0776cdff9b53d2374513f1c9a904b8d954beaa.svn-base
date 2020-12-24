package com.mhtech.platform.log.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="预警指标结果集",description="预警指标结果集")
public class ParamAlertDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long serverId;

    private String paramName;

    private String paramAlias;

    private BigDecimal alterValue;
    
    private String alertCode;
    
    private String alertType;

    private Short status;

    private Date topTime;//开始时间
    
    private Date endTime;//结束时间
    
    @ApiModelProperty("分页-每页条数")
    @Min(0)    
    private int pageSize;// 分页-每页条数
    @ApiModelProperty("分页-当前页")
    @Min(0)    
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

	public String getParamAlias() {
		return paramAlias;
	}

	public void setParamAlias(String paramAlias) {
		this.paramAlias = paramAlias;
	}

	public BigDecimal getAlterValue() {
		return alterValue;
	}

	public void setAlterValue(BigDecimal alterValue) {
		this.alterValue = alterValue;
	}

	public String getAlertCode() {
		return alertCode;
	}

	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ParamAlertDTO(Long id, Long serverId, String paramName, String paramAlias, BigDecimal alterValue,
			String alertCode, String alertType, Short status, Date topTime, Date endTime) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.paramName = paramName;
		this.paramAlias = paramAlias;
		this.alterValue = alterValue;
		this.alertCode = alertCode;
		this.alertType = alertType;
		this.status = status;
		this.topTime = topTime;
		this.endTime = endTime;
	}

	public ParamAlertDTO() {
		super();
	}

	@Override
	public String toString() {
		return "ParamAlertDTO [id=" + id + ", serverId=" + serverId + ", paramName=" + paramName + ", paramAlias="
				+ paramAlias + ", alterValue=" + alterValue + ", alertCode=" + alertCode + ", alertType=" + alertType
				+ ", status=" + status + ", topTime=" + topTime + ", endTime=" + endTime + ", pageSize=" + pageSize
				+ ", pageNo=" + pageNo + "]";
	}

    
    
    
    
}
