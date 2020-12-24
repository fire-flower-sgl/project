package com.mhtech.platform.log.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("自检规则")
public class ServerChkRuleVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "ID")
	private Long id;
	
	@ApiModelProperty(value = "服务ID")
    private Long serverId;
	
	@ApiModelProperty(value = "自检方法")
    private Integer method;
	
	@ApiModelProperty(value = "路径")
    private String uri;
	
	@ApiModelProperty(value = "端口")
    private Integer port;
	
	@ApiModelProperty(value = "状态")
    private Integer status;
	
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "更新日期")
    private Date createdTime;
    
	@ApiModelProperty(value = "get请求参数")
    private String httpHeaders;
	
	@ApiModelProperty(value = "POST请求参数")
    private String httpRequestBody; 
	
	@ApiModelProperty(value = "检查返回值字段")
    private String jsonFieldCheck;
    
    
    
	public String getJsonFieldCheck() {
		return jsonFieldCheck;
	}
	public void setJsonFieldCheck(String jsonFieldCheck) {
		this.jsonFieldCheck = jsonFieldCheck;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getServerId() {
		return serverId;
	}
	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}
	public Integer getMethod() {
		return method;
	}
	public void setMethod(Integer method) {
		this.method = method;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getHttpHeaders() {
		return httpHeaders;
	}
	public void setHttpHeaders(String httpHeaders) {
		this.httpHeaders = httpHeaders;
	}
	public String getHttpRequestBody() {
		return httpRequestBody;
	}
	public void setHttpRequestBody(String httpRequestBody) {
		this.httpRequestBody = httpRequestBody;
	}
	@Override
	public String toString() {
		return "ServerChkRuleDTO [id=" + id + ", serverId=" + serverId + ", method=" + method + ", uri=" + uri
				+ ", port=" + port + ", status=" + status + ", createdTime=" + createdTime + ", httpHeaders="
				+ httpHeaders + ", httpRequestBody=" + httpRequestBody + "]";
	}

	public ServerChkRuleVO(Long id, Long serverId, Integer method, String uri, Integer port, Integer status,
			Date createdTime, String httpHeaders, String httpRequestBody, String jsonFieldCheck) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.method = method;
		this.uri = uri;
		this.port = port;
		this.status = status;
		this.createdTime = createdTime;
		this.httpHeaders = httpHeaders;
		this.httpRequestBody = httpRequestBody;
		this.jsonFieldCheck = jsonFieldCheck;
	}
	public ServerChkRuleVO() {
		super();
	}
	
    
    

}
