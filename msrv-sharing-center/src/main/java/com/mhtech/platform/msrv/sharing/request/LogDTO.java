package com.mhtech.platform.msrv.sharing.request;

/**
 * 日志
 * 请求参数
 * @author Administrator
 *
 */
public class LogDTO {
	
	private Long maxId;
	private Long minId;
	
	
	public Long getMaxId() {
		return maxId;
	}
	public void setMaxId(Long maxId) {
		this.maxId = maxId;
	}
	public Long getMinId() {
		return minId;
	}
	public void setMinId(Long minId) {
		this.minId = minId;
	}
	private String topTime;//开始时间
	private String endTime;//结束时间
	private int size;//从多少条开始向后取
	private int eachItem; //每次取多少条
	
	public String getTopTime() {
		return topTime;
	}
	public void setTopTime(String topTime) {
		this.topTime = topTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getEachItem() {
		return eachItem;
	}
	public void setEachItem(int eachItem) {
		this.eachItem = eachItem;
	}
	public LogDTO(String topTime, String endTime, int size) {
		super();
		this.topTime = topTime;
		this.endTime = endTime;
		this.size = size;
	}
	public LogDTO() {
		super();
	}
	public LogDTO(String topTime, String endTime, int size, int eachItem) {
		super();
		this.topTime = topTime;
		this.endTime = endTime;
		this.size = size;
		this.eachItem = eachItem;
	}
	public LogDTO(String topTime, String endTime) {
		super();
		this.topTime = topTime;
		this.endTime = endTime;
	}


	
	
}
