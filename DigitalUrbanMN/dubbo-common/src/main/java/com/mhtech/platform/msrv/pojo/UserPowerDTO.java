package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户权限请求参数类
 * 
 * @author Administrator
 *
 */
public class UserPowerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userCode;// 用户编码
	private int pageSize;// 分页-每页条数
	private int pageNo;// 分页-当前页
	private String powerFather;// 上级权限
	private String powerModule;
	private String powerType;
	private String powerLevel;
	private String remoteUrl;
	private int sort;
	@Override
	public String toString() {
		return "UserPowerDTO [userCode=" + userCode + ", pageSize=" + pageSize + ", pageNo=" + pageNo + ", powerFather="
				+ powerFather + ", powerModule=" + powerModule + ", powerType=" + powerType + ", powerLevel="
				+ powerLevel + ", remoteUrl=" + remoteUrl + ", sort=" + sort + ", isUse=" + isUse + ", powerUpdateTime="
				+ powerUpdateTime + ", specialPowerKey=" + specialPowerKey + ", specialPowerValue=" + specialPowerValue
				+ ", url=" + url + ", icon=" + icon + ", src=" + src + ", powerNum=" + powerNum + ", powerName="
				+ powerName + ", id=" + id + "]";
	}

	private String isUse;
	private Date powerUpdateTime;
	private String specialPowerKey;
	private String specialPowerValue;
	private String url;
	private String icon;
	private String src;
	private String powerNum;// 权限编码
	private String powerName;// 权限名称
	private Long id;


	
	public UserPowerDTO(String powerFather, String powerModule, String powerType, String powerLevel, String remoteUrl,
			int sort, String isUse, Date powerUpdateTime, String specialPowerKey, String specialPowerValue, String url,
			String icon, String src, String powerNum, String powerName, Long id) {
		super();
		this.powerFather = powerFather;
		this.powerModule = powerModule;
		this.powerType = powerType;
		this.powerLevel = powerLevel;
		this.remoteUrl = remoteUrl;
		this.sort = sort;
		this.isUse = isUse;
		this.powerUpdateTime = powerUpdateTime;
		this.specialPowerKey = specialPowerKey;
		this.specialPowerValue = specialPowerValue;
		this.url = url;
		this.icon = icon;
		this.src = src;
		this.powerNum = powerNum;
		this.powerName = powerName;
		this.id = id;
	}

	public UserPowerDTO(String userCode, int pageSize, int pageNo, String powerFather, String powerModule,
			String powerType, String powerLevel, String remoteUrl, int sort, String isUse, Date powerUpdateTime,
			String specialPowerKey, String specialPowerValue, String url, String icon, String src, String powerNum,
			String powerName, Long id) {
		super();
		this.userCode = userCode;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.powerFather = powerFather;
		this.powerModule = powerModule;
		this.powerType = powerType;
		this.powerLevel = powerLevel;
		this.remoteUrl = remoteUrl;
		this.sort = sort;
		this.isUse = isUse;
		this.powerUpdateTime = powerUpdateTime;
		this.specialPowerKey = specialPowerKey;
		this.specialPowerValue = specialPowerValue;
		this.url = url;
		this.icon = icon;
		this.src = src;
		this.powerNum = powerNum;
		this.powerName = powerName;
		this.id = id;
	}

	public String getPowerModule() {
		return powerModule;
	}

	public void setPowerModule(String powerModule) {
		this.powerModule = powerModule;
	}

	public UserPowerDTO(Long id) {
		super();
		this.id = id;
	}

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	public String getPowerLevel() {
		return powerLevel;
	}

	public void setPowerLevel(String powerLevel) {
		this.powerLevel = powerLevel;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public Date getPowerUpdateTime() {
		return powerUpdateTime;
	}

	public void setPowerUpdateTime(Date powerUpdateTime) {
		this.powerUpdateTime = powerUpdateTime;
	}

	public String getSpecialPowerKey() {
		return specialPowerKey;
	}

	public void setSpecialPowerKey(String specialPowerKey) {
		this.specialPowerKey = specialPowerKey;
	}

	public String getSpecialPowerValue() {
		return specialPowerValue;
	}

	public void setSpecialPowerValue(String specialPowerValue) {
		this.specialPowerValue = specialPowerValue;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserPowerDTO(String userCode) {
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

	public UserPowerDTO() {
		super();
	}

	public UserPowerDTO(int pageSize, int pageNo, String powerNum) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.powerNum = powerNum;
	}

	public UserPowerDTO(String userCode, int pageSize, int pageNo, String powerFather, String powerNum,
			String powerName) {
		super();
		this.userCode = userCode;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.powerFather = powerFather;
		this.powerNum = powerNum;
		this.powerName = powerName;
	}

}
