package com.mhtech.platform.msrv.sharing.dao.model;

import java.io.Serializable;
import java.util.Date;

public class GatewayAccessLog implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Long id;

    private String ip;

    private String url;

    private String params;

    private Date createdTime;

    private String requestBody;
    
    private String responseBody;
    
    

    public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
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
        this.requestBody = requestBody == null ? null : requestBody.trim();
    }

	public GatewayAccessLog() {
		super();
	}
	 //输出版本号
    public long getSerialVersionUID() {
    	  return serialVersionUID;
    }

	@Override
	public String toString() {
		return "GatewayAccessLog [id=" + id + ", ip=" + ip + ", url=" + url + ", params=" + params + ", createdTime="
				+ createdTime + ", requestBody=" + requestBody + ", responseBody=" + responseBody + "]";
	}

    


    
    
}