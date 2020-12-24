package com.mhtech.platform.msrv.gateway.request;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="执行一次监控方法实体",description="执行一次监控方法实体" )
public class ReqMonitorPlanRunTask {

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
