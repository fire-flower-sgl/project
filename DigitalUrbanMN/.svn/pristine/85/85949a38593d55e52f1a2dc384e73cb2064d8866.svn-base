package com.mhtech.platform.log.pojo;

import java.io.Serializable;
import java.util.Date;

public class GatewayAccessLogDTO implements Serializable{

	@Override
	public String toString() {
		return "GatewayAccessLogDTO [id=" + id + ", ip=" + ip + ", url=" + url + ", params=" + params + ", topTime="
				+ topTime + ", endTime=" + endTime + ", requestBody=" + requestBody + ", pageSize=" + pageSize
				+ ", pageNo=" + pageNo + "]";
	}

	private static final long serialVersionUID = 1L;
	
	private Long id;

    private String ip;

    private String url;

    private String params;

    private Date topTime;//开始时间
    
    private Date endTime;//结束时间

    private String requestBody;

    private int pageSize;// 分页-每页条数
   	private int pageNo;// 分页-当前页
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

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GatewayAccessLogDTO(Long id, String ip, String url, String params, Date topTime, Date endTime,
			String requestBody) {
		super();
		this.id = id;
		this.ip = ip;
		this.url = url;
		this.params = params;
		this.topTime = topTime;
		this.endTime = endTime;
		this.requestBody = requestBody;
	}

	public GatewayAccessLogDTO(Long id, String ip, String url, String params, Date topTime, Date endTime,
			String requestBody, int pageSize, int pageNo) {
		super();
		this.id = id;
		this.ip = ip;
		this.url = url;
		this.params = params;
		this.topTime = topTime;
		this.endTime = endTime;
		this.requestBody = requestBody;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

	public GatewayAccessLogDTO() {
		super();
	}

    
    
    
}
