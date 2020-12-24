package com.mhtech.platform.msrv.gateway.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="新增服务自检",description="新增服务自检" )
public class ReqServerChkRule implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id不用传",required=false)
	private Long id;
	
	@NotNull(message = "serverId为空!")
	@Range(min = 0, message = "serverId值错误")
	@ApiModelProperty(value="服务编号",required=true)
    private Long serverId;
	
	@NotNull(message = "method为空!")
	@ApiModelProperty(value="自检方式",required=true)
    private Integer method;	

	@Length(max = 256, message = "uri过长!")
	@ApiModelProperty(value="路径",required=false)
    private String uri;	

	@Pattern(regexp = "^[1-9]\\d{3}$",message = "请输入4位数字端口号!")
	@ApiModelProperty(value="端口号",required=false)
    private String port;
	
	@Length(max = 256, message = "jsonFieldCheck过长!")
	@ApiModelProperty(value="json检查字段",required=false)
	private String jsonFieldCheck;
	
    @Length(max = 1000, message = "httpHeaders过长!")
    @ApiModelProperty(value="get请求参数",required=false)
    private String httpHeaders;
    
    @Length(max = 1000, message = "httpRequestBody过长!")
    @ApiModelProperty(value="post请求参数",required=false)
    private String httpRequestBody;
	
	@Range(min = 0, max = 1,message = "状态码错误")
	@ApiModelProperty(value="状态",required=false)
    private Integer status;
    
	@ApiModelProperty(value="创建时间",required=false)
    private Date createdTime;
    
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
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
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
	
    
    

}
