package com.mhtech.platform.msrv.sharing.request;


public class MsrvLogVO {
	
	
	private Long logId;

    private Long traceId;

    private Boolean side;

    private String application;

	private String interfaceName;

    private String methods;

    private String host;

    private String createdTime;

    private String arguments;

	public MsrvLogVO(Long logId, Long traceId, Boolean side, String application, String interfaceName, String methods,
			String host, String createdTime, String arguments) {
		super();
		this.logId = logId;
		this.traceId = traceId;
		this.side = side;
		this.application = application;
		this.interfaceName = interfaceName;
		this.methods = methods;
		this.host = host;
		this.createdTime = createdTime;
		this.arguments = arguments;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getTraceId() {
		return traceId;
	}

	public void setTraceId(Long traceId) {
		this.traceId = traceId;
	}

	public Boolean getSide() {
		return side;
	}

	public void setSide(Boolean side) {
		this.side = side;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public MsrvLogVO() {
		super();
	}
    
    
    
    
}
