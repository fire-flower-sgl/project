package com.mhtech.platform.msrv.gateway.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="停止/删除监控方法实体",description="停止/删除监控方法实体" )
public class ReqMonitorPlanCheckTaskName implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键id",required=true)
	@Range(min = 0, message = "id错误")
	private Long id;
	
	@ApiModelProperty(value="任务名称",required=true)
	@Length(max = 256, message = "任务名称报错!")
	@NotEmpty(message = "任务名称为空")
	private String taskName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	
    
    

}
