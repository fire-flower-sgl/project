package com.mhtech.platform.log.pojo;

import java.io.Serializable;
import java.util.Date;

public class MsrvLogDTO  implements Serializable{

	private static final long serialVersionUID = 1L;


	private Long logId;

    private Long traceId;

    private Boolean side;

    private String application;

    private String interfaceName;

    private String methods;

    private String host;

    private Date topTime;//开始时间
    
    private Date endTime;//结束时间

    private String arguments;


    private int pageSize;// 分页-每页条数
   	private int pageNo;// 分页-当前页

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MsrvLogDTO() {
		super();
	}

	public MsrvLogDTO(Date topTime, Date endTime) {
		super();
		this.topTime = topTime;
		this.endTime = endTime;
	}

	public MsrvLogDTO(Long logId, Long traceId, Boolean side, String application, String interfaceName, String methods,
			String host, Date topTime, Date endTime, String arguments, int pageSize, int pageNo) {
		super();
		this.logId = logId;
		this.traceId = traceId;
		this.side = side;
		this.application = application;
		this.interfaceName = interfaceName;
		this.methods = methods;
		this.host = host;
		this.topTime = topTime;
		this.endTime = endTime;
		this.arguments = arguments;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

}
