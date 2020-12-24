package com.mhtech.platform.msrv.gateway.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="监控方法更新实体",description="监控方法更新实体" )
public class ReqMonitorPlanUpdate implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7445867855640775109L;

	
	@ApiModelProperty(value="主键id",required=true)
	@Range(min = 0, message = "id错误")
	@NotNull(message = "id为空")
	private Long id;
	
	@ApiModelProperty(value="任务名称",required=true)
	@Length(max = 256, message = "任务名称报错!")
	@NotEmpty(message = "任务名称为空")
	private String taskName;
	
	@ApiModelProperty(value="任务频率",required=true)
	@Length(max = 256, message = "任务频率报错!")
	@NotEmpty(message = "任务频率为空")
    private String cron;

	@ApiModelProperty(value="任务类",required=true)
	@Length(max = 256, message = "任务类报错!")
	@NotEmpty(message = "任务类为空")
    private String mainClass;

	@ApiModelProperty(value="任务方法",required=true)
	@NotEmpty(message = "任务方法为空")
	@Length(max = 256, message = "任务方法报错!")
    private String method;

	@ApiModelProperty(value="任务状态",required=true)
	@Range(min = 0, max = 1,message = "状态码错误")
	@NotNull(message = "任务状态为空")
    private Integer status;

	@ApiModelProperty(value="任务参数",required=false)
	@Length(max = 256, message = "任务参数报错!")
    private String params;

	@ApiModelProperty(value="任务备注",required=false)
	@Length(max = 256, message = "任务备注报错!")
    private String memo;

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

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
    
    

}
