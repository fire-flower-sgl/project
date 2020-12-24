package com.mobile.model;

import java.io.Serializable;
import java.util.List;


/**
 *  mjl_
 *  SysPower
 *  系统权限类
 *  2019-10.22
 */

public class OrgInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id; 

	private String orgCode;
	private String orgName;
	private String companyCode;
	private String companyName;
	private String parentOrgCode;
	private String parentOrgName;
	private String isLeaf;
	private String sort;
	private String isDelete;
	private String createdTime;
	private String hasSon;
	
	
	public String getHasSon() {
		return hasSon;
	}
	public void setHasSon(String hasSon) {
		this.hasSon = hasSon;
	}
	
    private List<OrgInfo> children ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getParentOrgCode() {
		return parentOrgCode;
	}
	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public List<OrgInfo> getChildren() {
		return children;
	}
	public void setChildren(List<OrgInfo> children) {
		this.children = children;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getParentOrgName() {
		return parentOrgName;
	}
	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	@Override
	public String toString() {
		return "OrgInfo [id=" + id + ", orgCode=" + orgCode + ", orgName=" + orgName + ", companyCode=" + companyCode
				+ ", companyName=" + companyName + ", parentOrgCode=" + parentOrgCode + ", parentOrgName="
				+ parentOrgName + ", isLeaf=" + isLeaf + ", sort=" + sort + ", isDelete=" + isDelete + ", createdTime="
				+ createdTime + ", children=" + children + "]";
	}

	

	
}
