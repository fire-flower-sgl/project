package com.mhtech.platform.msrv.sharing.dao.model;

import java.io.Serializable;
import java.util.Date;

public class MsrvLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long logId;

    private Long traceId;

    private Boolean side;

    private String application;

	private String interfaceName;

    private String methods;

    private String host;

    private Date createdTime;

    private String arguments;

//    private String maxId;
//    private String minId;

//	public String getMaxId() {
//		return maxId;
//	}
//
//	public void setMaxId(String maxId) {
//		this.maxId = maxId;
//	}
//
//	public String getMinId() {
//		return minId;
//	}
//
//	public void setMinId(String minId) {
//		this.minId = minId;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
        this.application = application == null ? null : application.trim();
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName == null ? null : interfaceName.trim();
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods == null ? null : methods.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments == null ? null : arguments.trim();
    }

	@Override
	public String toString() {
		return "MsrvLog [logId=" + logId + ", traceId=" + traceId + ", side=" + side + ", application=" + application
				+ ", interfaceName=" + interfaceName + ", methods=" + methods + ", host=" + host + ", createdTime="
				+ createdTime + ", arguments=" + arguments + "]";
	}

	public MsrvLog(Long logId, Long traceId, Boolean side, String application, String interfaceName, String methods,
			String host, Date createdTime, String arguments) {
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

	public MsrvLog() {
		super();
	}

	
    
    
}