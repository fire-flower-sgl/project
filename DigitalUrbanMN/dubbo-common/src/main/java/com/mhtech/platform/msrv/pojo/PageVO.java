package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class PageVO<T> implements Serializable {

	private long totalCount; //总条数
    private int start; //开始条数
    private int totalPages; //总页数
    private List<T> data;
    private Map<String, Object> param;
    
    public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	/**
     * 补充属性
     * @param name 
     * @param data
     * @return
     */
    public PageVO<T> append(String name, Object data) {
    	if(param == null) {
    		param = new HashMap<String, Object>();
    	}
    	param.put(name, data);
    	return this;
    }
    
	public Map<String, Object> getParam() {
		return param;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public PageVO() {
		super();
	}

	@Override
	public String toString() {
		return "PageVO [totalCount=" + totalCount + ", start=" + start + ", totalPages=" + totalPages + ", data=" + data
				+ ", param=" + param + "]";
	}
	
	
}
