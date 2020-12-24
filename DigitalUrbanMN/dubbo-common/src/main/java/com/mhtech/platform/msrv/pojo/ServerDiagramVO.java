package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="业务图表流程类")
public class ServerDiagramVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6887164971000508526L;

    @ApiModelProperty("主键id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
	private String id;

    @ApiModelProperty("业务编号")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String businessId;
    
    @ApiModelProperty("服务编号")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String serverId;
    
    @ApiModelProperty("服务名称")
    private String serverName;
    
    @ApiModelProperty("服务描述")
    private String serverDesc;
    
    @ApiModelProperty("下级服务编号")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long nextId;
    
    @ApiModelProperty("下级服务名称")
    private String nextName;
    
    @ApiModelProperty("操作说明")
    private String workDesc;
    
    @ApiModelProperty("节点状态")
    private Short status;
    
    @ApiModelProperty("节点状态说明")
    private String statusName;
    
    @ApiModelProperty("节点状态说明")
    private boolean end;
    
   

	public boolean isEnd() {
		return end;
	}



	public void setEnd(boolean end) {
		this.end = end;
	}



	public String getServerName() {
		return serverName;
	}



	public void setServerName(String serverName) {
		this.serverName = serverName;
	}



	public String getNextName() {
		return nextName;
	}



	public void setNextName(String nextName) {
		this.nextName = nextName;
	}



	public Short getStatus() {
		return status;
	}



	public void setStatus(Short status) {
		this.status = status;
	}



	public String getStatusName() {
		return statusName;
	}



	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getBusinessId() {
		return businessId;
	}



	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}



	public String getServerId() {
		return serverId;
	}



	public void setServerId(String serverId) {
		this.serverId = serverId;
	}



	public String getServerDesc() {
		return serverDesc;
	}



	public void setServerDesc(String serverDesc) {
		this.serverDesc = serverDesc;
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



	public ServerDiagramVO() {
		super();
	}



	public ServerDiagramVO(String id, String businessId, String serverId, String serverDesc, Long nextId, String workDesc) {
		super();
		this.id = id;
		this.businessId = businessId;
		this.serverId = serverId;
		this.serverDesc = serverDesc;
		this.nextId = nextId;
		this.workDesc = workDesc;
	}



	@Override
	public String toString() {
		return "ServerDiagramVO [id=" + id + ", businessId=" + businessId + ", serverId=" + serverId + ", serverDesc="
				+ serverDesc + ", nextId=" + nextId + ", workDesc=" + workDesc + "]";
	}
    
	
    
}