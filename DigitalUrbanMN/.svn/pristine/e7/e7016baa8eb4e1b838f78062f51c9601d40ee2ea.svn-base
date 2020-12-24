package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("监控方法")
public class MonitorPlansVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5934051118378212837L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "任务名称")
    private String taskName;

	@ApiModelProperty(value = "任务频次")
    private String cron;

	@ApiModelProperty(value = "任务类")
    private String mainClass;

	@ApiModelProperty(value = "任务方法")
    private String method;

	@ApiModelProperty(value = "任务状态")
    private Integer status;

	@ApiModelProperty(value = "任务参数")
    private String params;

	@ApiModelProperty(value = "任务备注")
    private String memo;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "任务更新时间")
    private Date createdTime;
    
	@ApiModelProperty(value = "是否启动")
    private Integer isStart;
    
    

    public Integer getIsStart() {
		return isStart;
	}

	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}

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
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass == null ? null : mainClass.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
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
        this.params = params == null ? null : params.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

	public MonitorPlansVO(Long id, String taskName, String cron, String mainClass, String method, Integer status,
			String params, String memo, Date createdTime) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.cron = cron;
		this.mainClass = mainClass;
		this.method = method;
		this.status = status;
		this.params = params;
		this.memo = memo;
		this.createdTime = createdTime;
	}

	public MonitorPlansVO() {
		super();
	}

	@Override
	public String toString() {
		return "MonitorPlansVO [id=" + id + ", taskName=" + taskName + ", cron=" + cron + ", mainClass=" + mainClass
				+ ", method=" + method + ", status=" + status + ", params=" + params + ", memo=" + memo
				+ ", createdTime=" + createdTime + "]";
	}
    
	
}