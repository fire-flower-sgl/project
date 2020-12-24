package com.mhtech.platform.log.pojo;

import java.io.Serializable;
import java.util.Date;

public class ServerChkRuleDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long serverId;
    private Integer method;
    private String uri;
    private Integer port;
    private Integer status;
    private Date createdTime;
    private String httpHeaders;
    private String httpRequestBody;
    private String jsonFieldCheck;
    
	private  int pageSize;
	private  int pageNo;
    
    
    
	public String getJsonFieldCheck() {
		return jsonFieldCheck;
	}
	public void setJsonFieldCheck(String jsonFieldCheck) {
		this.jsonFieldCheck = jsonFieldCheck;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
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
				+ httpHeaders + ", httpRequestBody=" + httpRequestBody + ", jsonFieldCheck=" + jsonFieldCheck
				+ ", pageSize=" + pageSize + ", pageNo=" + pageNo + "]";
	}
	public ServerChkRuleDTO(Long id, Long serverId, Integer method, String uri, Integer port, Integer status,
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
	public ServerChkRuleDTO(Long id) {
		super();
		this.id = id;
	}
	public ServerChkRuleDTO() {
		super();
	}
	
    
    

}
