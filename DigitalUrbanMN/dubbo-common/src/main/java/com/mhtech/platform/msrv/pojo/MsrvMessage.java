package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.List;

public class MsrvMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  String status;//状态码 10传输中，20已完成
	
	private List<MsrvLogVO> msrvLogList;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MsrvLogVO> getMsrvLogList() {
		return msrvLogList;
	}

	public void setMsrvLogList(List<MsrvLogVO> msrvLogList) {
		this.msrvLogList = msrvLogList;
	}

	public MsrvMessage(String status, List<MsrvLogVO> msrvLogList) {
		super();
		this.status = status;
		this.msrvLogList = msrvLogList;
	}
	public MsrvMessage() {
		super();

	}

	@Override
	public String toString() {
		return "MsrvMessage [status=" + status + ", msrvLogList=" + msrvLogList + "]";
	}


	
	
	
}
