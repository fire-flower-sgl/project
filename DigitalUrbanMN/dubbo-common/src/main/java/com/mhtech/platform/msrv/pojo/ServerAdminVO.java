package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("服务应用")
public class ServerAdminVO implements Serializable {

	private static final long serialVersionUID = -2541998133903620415L;

	@ApiModelProperty(value = "服务KEY", example = "10000")
	//@JsonInclude(Include.NON_NULL) 
	//@JsonFormat(shape = JsonFormat.Shape.STRING)
	private String id;

	@ApiModelProperty(value = "服务名称", example = "邮件服务器")
	//@JsonInclude(Include.NON_NULL) 
    private String serverName;
	
	@ApiModelProperty(value = "服务集合")
    //@JsonInclude(Include.NON_NULL) 
    private List<ServerAdminVO> serverList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	

	public List<ServerAdminVO> getServerList() {
		return serverList;
	}

	public void setServerList(List<ServerAdminVO> serverList) {
		this.serverList = serverList;
	}

	public ServerAdminVO(String id, String serverName) {
		super();
		this.id = id;
		this.serverName = serverName;
	}

	public ServerAdminVO() {
		super();
	}

	public ServerAdminVO(List<ServerAdminVO> serverList) {
		super();
		this.serverList = serverList;
	}
	
	

	
   
}
