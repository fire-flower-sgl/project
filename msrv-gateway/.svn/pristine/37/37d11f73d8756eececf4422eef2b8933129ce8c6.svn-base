package com.mhtech.platform.msrv.gateway.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="监控方法开始任务实体",description="监控方法开始任务实体" )
public class ReqMonitorPlanStartTask {
	
	@ApiModelProperty(value="主键id",required=true)
	@Range(min = 0, message = "id错误")
	@NotNull(message = "id为空")
	private Long id;
	
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

	@ApiModelProperty(value="任务参数",required=false)
	@Length(max = 256, message = "任务参数报错!")
    private String params;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
    
    
    


}
