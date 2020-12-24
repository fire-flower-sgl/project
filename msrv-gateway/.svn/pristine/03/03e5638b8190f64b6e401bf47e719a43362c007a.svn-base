package com.mhtech.platform.msrv.gateway.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="分页查询参数",description="服务自检分页查询参数" )
public class ReqServerPageableEntity {
	
	@Min(value = 1, message = "页码错误")
	@NotNull(message = "查询页码错误")
	@ApiModelProperty(value="页码",required=true)
    private Integer pageNo;
    
	@Max(value = 500, message = "查询条数过大")
	@Min(value = 1, message = "查询条数错误")
	@NotNull(message = "查询条数错误")
	@ApiModelProperty(value="数据条数",required=true)
    private Integer pageSize;
    
	@ApiModelProperty(value="自建方式",required=false)
    private Integer method;
    
    
    
	public Integer getMethod() {
		return method;
	}
	public void setMethod(Integer method) {
		this.method = method;
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
