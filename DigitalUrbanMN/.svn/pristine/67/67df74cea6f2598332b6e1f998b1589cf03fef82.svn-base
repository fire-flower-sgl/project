package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class PageBO implements Serializable {

	
	private int pageSize = 10; //每页显示条数
	private int pageNo = 1;//当前页
	
	private String orderBy;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		pageSize = pageSize < 1 || pageSize > 1000 ? this.pageSize : pageSize;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		pageNo = pageNo < 1 || pageNo > 1000 ? this.pageNo : pageNo;
		this.pageNo = pageNo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
