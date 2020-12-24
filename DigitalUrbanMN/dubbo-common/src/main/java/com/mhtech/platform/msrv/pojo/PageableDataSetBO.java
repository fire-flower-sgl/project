package com.mhtech.platform.msrv.pojo;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 数据集分页查询BO
 */
@ApiModel("数据集分页查询")
@SuppressWarnings("serial")
public class PageableDataSetBO extends PageBO {

	@ApiModelProperty("团队编码")
	@Length(max = 32, message= "团队编码错误")
	private String teamCode;
	
	private String dataName;
	
	private String dataTableName;
	
	private Boolean isOpen;
	
	private Short state;

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataTableName() {
		return dataTableName;
	}

	public void setDataTableName(String dataTableName) {
		this.dataTableName = dataTableName;
	}
}
