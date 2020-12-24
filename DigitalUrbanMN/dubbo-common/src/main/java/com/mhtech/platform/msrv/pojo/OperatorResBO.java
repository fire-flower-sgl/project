package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@ApiModel("算子对象")
@SuppressWarnings("serial")
public class OperatorResBO implements Serializable {

	@ApiModelProperty("算子ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty("算子名称")
    private String operatorName;

	@ApiModelProperty("算子类型")
    private Short operatorType;
	
	@ApiModelProperty("算子logo")
	private String logo;
	@ApiModelProperty("创建人")
	private String creator;
	
	private Date createdTime;
	
	private String memo;

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Short getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Short operatorType) {
		this.operatorType = operatorType;
	}
}
