package com.mhtech.platform.msrv.gateway.request.algorithm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

@ApiModel("数据集分页查询")
@SuppressWarnings("serial")
public class PageableDataSetParam implements Serializable {

	@ApiModelProperty("团队编码")
	@Length(max = 32, message= "团队编码错误")
	private String teamCode;
	
	@ApiModelProperty("数据集名称, 模糊搜索")
	@Length(max = 32, message= "数据集名称错误")
	private String dataName;
	
	@ApiModelProperty("显示条数")
	@Min(value = 1, message = "数量错误")
	private Integer pageSize = 10; //每页显示条数
	@ApiModelProperty("页码")
	@Min(value = 1, message = "页码错误")
	private Integer pageNo = 1;//当前页
	
	private String orderBy;

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

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
