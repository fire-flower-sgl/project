package com.mhtech.platform.msrv.gateway.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="硬件类型",description="硬件类型查询实体" )
public class ReqHardwareTypePage {
	
	@Min(value = 1, message = "页码错误")
	@NotNull(message = "查询页码错误")
	@ApiModelProperty(value="页码",required=true)
    private Integer pageNo;
    
	@Max(value = 500, message = "查询条数过大")
	@Min(value = 1, message = "查询条数错误")
	@NotNull(message = "查询条数错误")
	@ApiModelProperty(value="数据条数",required=true)
    private Integer pageSize;

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
