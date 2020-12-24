package com.mhtech.platform.msrv.gateway.request;

import java.io.Serializable;

import org.springframework.lang.NonNull;

/**
 * 用户权限请求参数类
 * @author Administrator
 *
 */
public class UserPowerParams implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NonNull
	private  String userCode;//用户编码
	private  int pageSize;//分页-每页条数
	private  int pageNo;//分页-当前页
	private  String powerFather;//上级权限
	private  String powerNum;//权限编码
	private  String powerName;//权限名称
	private Long id;
	
	
	
	
	@Override
	public String toString() {
		return "UserPowerParams [userCode=" + userCode + ", pageSize=" + pageSize + ", pageNo=" + pageNo
				+ ", powerFather=" + powerFather + ", powerNum=" + powerNum + ", powerName=" + powerName + ", id=" + id
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserPowerParams(String userCode) {
		super();
		this.userCode = userCode;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getPowerFather() {
		return powerFather;
	}

	public void setPowerFather(String powerFather) {
		this.powerFather = powerFather;
	}

	public String getPowerNum() {
		return powerNum;
	}

	public void setPowerNum(String powerNum) {
		this.powerNum = powerNum;
	}

	public UserPowerParams() {
		super();
	}
	
	
}
