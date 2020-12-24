package com.mhtech.platform.msrv.sharing.dao.model;

import java.io.Serializable;
import java.util.Date;

public class ServerChkRule  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long serverId;

    private Short method;

    private String uri;

    private Short port;

    private Short status;

    private Date createdTime;
    
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

    public Short getMethod() {
        return method;
    }

    public void setMethod(Short method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Short getPort() {
        return port;
    }

    public void setPort(Short port) {
        this.port = port;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

	@Override
	public String toString() {
		return "ServerChkRule [id=" + id + ", serverId=" + serverId + ", method=" + method + ", uri=" + uri + ", port="
				+ port + ", status=" + status + ", createdTime=" + createdTime + "]";
	}
    
}