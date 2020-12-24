package com.mhtech.platform.msrv.gateway.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="分页查询参数",description="任务模块分页查询参数" )
public class ReqMonitorPlanPageInfo {
	
	@Min(value = 1, message = "页码错误")
	@NotNull(message = "查询页码错误")
	@ApiModelProperty(value="页码",required=true)
    private Integer pageNo;
    
	@Max(value = 500, message = "查询条数过大")
	@Min(value = 1, message = "查询条数错误")
	@NotNull(message = "查询条数错误")
	@ApiModelProperty(value="数据条数",required=true)
    private Integer pageSize;
    
	@ApiModelProperty(value="任务名称",required=false)
	@Length(max = 256, message = "任务名称过长!")
    private String taskName;
    
	@ApiModelProperty(value="任务状态",required=false)
    private Integer Status;
    
    

	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
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
