package com.mhtech.platform.msrv.auth.dao.model;

import java.util.Date;

import com.frameworkset.orm.annotation.ESId;
import com.mhtech.platform.msrv.auth.elasticsearch.domain.ESEntity;

public class SysGatewayAccessLog extends ESEntity{
	
	@ESId
    private long id;

    private String ip;

    private String url;

    private String params;

    private Date createdTime;

    private String requestBody;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

	@Override
	public String toString() {
		return "SysGatewayAccessLog [id=" + id + ", ip=" + ip + ", url=" + url + ", params=" + params + ", createdTime="
				+ createdTime + ", requestBody=" + requestBody + "]";
	}


	public SysGatewayAccessLog(long id, String ip, String url, String params, String requestBody) {
		super();
		this.id = id;
		this.ip = ip;
		this.url = url;
		this.params = params;
		this.requestBody = requestBody;
	}

	public SysGatewayAccessLog() {

	}

	@Override
	public String getIndexName() {
		return "sys_gateway_access_log";
	}

	@Override
	public String getIndexType() {
		return getClass().getSimpleName();
	}
    
}