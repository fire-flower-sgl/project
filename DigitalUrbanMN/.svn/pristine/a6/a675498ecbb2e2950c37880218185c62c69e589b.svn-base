package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

/**
 * 服务同步结果
 * @author GM
 */
public class MonitorSyncResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9002980777738691710L;

	private int total;
	
	private int successes;
	
	private String message;
	
	public static MonitorSyncResult build() {
		return new MonitorSyncResult();
	}
	
	public MonitorSyncResult total(int total) {
		this.total = total;
		return this;
	}
	
	public MonitorSyncResult success(int successes) {
		this.successes = successes;
		return this;
	}
	
	public MonitorSyncResult message(String message) {
		this.message = message;
		return this;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSuccesses() {
		return successes;
	}

	public void setSuccesses(int successes) {
		this.successes = successes;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
