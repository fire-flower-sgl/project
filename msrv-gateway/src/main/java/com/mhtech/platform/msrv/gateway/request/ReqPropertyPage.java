package com.mhtech.platform.msrv.gateway.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="资产-服务器-查询实体",description="资产-服务器-查询实体" )
public class ReqPropertyPage {
	
	@Min(value = 1, message = "页码错误")
	@NotNull(message = "查询页码错误")
	@ApiModelProperty(value="页码",required=true)
    private Integer pageNo;
    
	@Max(value = 500, message = "查询条数过大")
	@Min(value = 1, message = "查询条数错误")
	@NotNull(message = "查询条数错误")
	@ApiModelProperty(value="数据条数",required=true)
    private Integer pageSize;

	@ApiModelProperty(value="名称",required=false)
	private String name;
	
	@ApiModelProperty(value="类型",required=false)
	private String type;
	
	@ApiModelProperty(value="状态",required=false)
	private Integer state;
	
	@ApiModelProperty(value="是否监控",required=false)
	private Integer isMonitor;
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsMonitor() {
		return isMonitor;
	}

	public void setIsMonitor(Integer isMonitor) {
		this.isMonitor = isMonitor;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
