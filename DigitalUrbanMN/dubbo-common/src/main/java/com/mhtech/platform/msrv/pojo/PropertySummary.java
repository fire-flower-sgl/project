package com.mhtech.platform.msrv.pojo;

public class PropertySummary {

	private String category;
	private Short type;
	private Integer count;
	public PropertySummary() {
		super();
	}
	public PropertySummary(String category, Short type, Integer count) {
		super();
		this.category = category;
		this.type = type;
		this.count = count;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
