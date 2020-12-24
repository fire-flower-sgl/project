package com.mhtech.platform.msrv.gateway.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "业务流程图新增实体")
public class ServerDiagramAdd {
	
	@Min(value = 0, message = "业务编号错误")
	@NotNull(message = "业务编号为空!")
	@ApiModelProperty(value="业务编号",required=true)
    private Long businessId;

	@Min(value = 0, message = "服务编号错误")
	@NotNull(message = "服务编号为空!")
	@ApiModelProperty(value="服务编号",required=true)
    private Long serverId;

	@Min(value = 0, message = "上级服务编号错误")
	@ApiModelProperty(value="上级服务编号",required=false)
    private Long nextId;

	@Length(max = 255, message = "操作说明过长!")
	@ApiModelProperty(value="操作说明",required=false)
    private String workDesc;

//	@NotNull(message = "节点标志为空!")
//	@Range(min = 0, max = 2,message = "节点标志错误")
//	@ApiModelProperty(value="节点标志;0 中间节点, 1 开始节点, 2 末尾节点",required=true)
//    private Short status;
	
	@ApiModelProperty(value="是否结束",required=true)
    private boolean isEnd;
	
	

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public Long getNextId() {
		return nextId;
	}

	public void setNextId(Long nextId) {
		this.nextId = nextId;
	}

	public String getWorkDesc() {
		return workDesc;
	}

	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}


   
}