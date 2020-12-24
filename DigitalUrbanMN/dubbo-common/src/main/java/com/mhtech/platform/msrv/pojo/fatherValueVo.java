package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

/**
 * 权限查询区域数据-对象
 * @author Administrator
 *
 */
public class fatherValueVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String areaCode;
	private String areaName;
	private String id;
	private String parentCode;
	
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public fatherValueVo(String areaCode, String areaName, String id, String parentCode) {
		super();
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.id = id;
		this.parentCode = parentCode;
	}
	public fatherValueVo() {
		super();
	}
	
	
}
