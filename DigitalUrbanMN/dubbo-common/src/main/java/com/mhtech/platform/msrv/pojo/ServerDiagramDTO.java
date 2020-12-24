package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class ServerDiagramDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6294438623321154399L;

	private Long id;

    private Long businessId;
    
    private Long serverId;
    
    private Long nextId;

    private String workDesc;

    private Short status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public ServerDiagramDTO(Long businessId, Long serverId, Long nextId, String workDesc) {
		super();
		this.businessId = businessId;
		this.serverId = serverId;
		this.nextId = nextId;
		this.workDesc = workDesc;
	}
	
	public ServerDiagramDTO(Long businessId, Long serverId) {
		super();
		this.businessId = businessId;
		this.serverId = serverId;
	}

	public ServerDiagramDTO() {
		super();
	}
    
    
	
	
    
    
}