package com.mhtech.platform.msrv.sharing.request;


public class GatewayAccessLogVO {

	private Long id;

    private String ip;

    private String url;

    private String params;

    private String createdTime;

    private String requestBody;

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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public GatewayAccessLogVO(Long id, String ip, String url, String params, String createdTime, String requestBody) {
		super();
		this.id = id;
		this.ip = ip;
		this.url = url;
		this.params = params;
		this.createdTime = createdTime;
		this.requestBody = requestBody;
	}

	public GatewayAccessLogVO() {
		super();
	}

	@Override
	public String toString() {
		return "GatewayAccessLogVO [id=" + id + ", ip=" + ip + ", url=" + url + ", params=" + params + ", createdTime="
				+ createdTime + ", requestBody=" + requestBody + "]";
	}
    
    
    

}
