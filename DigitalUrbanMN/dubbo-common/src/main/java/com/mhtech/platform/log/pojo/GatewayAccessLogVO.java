package com.mhtech.platform.log.pojo;

import java.io.Serializable;
import java.util.Date;

public class GatewayAccessLogVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String ip;

    private String url;

    private String params;

    private Date createdTime;

    private String requestBody;

	public GatewayAccessLogVO(Long id, String ip, String url, String params, Date createdTime, String requestBody) {
		super();
		this.id = id;
		this.ip = ip;
		this.url = url;
		this.params = params;
		this.createdTime = createdTime;
		this.requestBody = requestBody;
	}

	public Long getId() {
		return id;
	}

	public GatewayAccessLogVO() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    


}
