package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户权限-返回数据对象
 * @author Administrator
 *
 */
public class UserPowerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private Long id;
	    private String powerNum;
	    private String powerName;
	    private String powerModule;
	    private String powerType;
	    
	    private String powerLevel;//权限级别
	   	private String powerFather;//上级权限名称
	    private String isUse;//是否使用
	    private Date powerUpdateTime;//更新时间
	    private String url;
	    
	    private String icon;
	    private String src;
	    
		private String powerTypeVal;//中文返回- 权限类型
	    private String powerLevelVal; //中文返回-权限级别
	    private String fatherPowerName;//中文返回-上级权限
	    
	    private String specialPowerKey;
	    private String specialPowerValue;
	    
	    private int sort;
	    private String remoteUrl;
	    
	 

	
		public UserPowerVO(Long id, String powerNum, String powerName) {
			super();
			this.id = id;
			this.powerNum = powerNum;
			this.powerName = powerName;
		}

		public UserPowerVO(String url) {
			super();
			this.url = url;
		}

		public UserPowerVO(String powerNum, String url) {
			super();
			this.powerNum = powerNum;
			this.url = url;
		}

		public int getSort() {
			return sort;
		}

		public void setSort(int sort) {
			this.sort = sort;
		}

		public String getRemoteUrl() {
			return remoteUrl;
		}

		public void setRemoteUrl(String remoteUrl) {
			this.remoteUrl = remoteUrl;
		}

		public UserPowerVO(Long id, String powerNum, String powerName, String specialPowerKey,
				String specialPowerValue) {
			super();
			this.id = id;
			this.powerNum = powerNum;
			this.powerName = powerName;
			this.specialPowerKey = specialPowerKey;
			this.specialPowerValue = specialPowerValue;
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

		public String getPowerTypeVal() {
			return powerTypeVal;
		}

		public void setPowerTypeVal(String powerTypeVal) {
			this.powerTypeVal = powerTypeVal;
		}

		public String getPowerLevelVal() {
			return powerLevelVal;
		}

		public void setPowerLevelVal(String powerLevelVal) {
			this.powerLevelVal = powerLevelVal;
		}

		public String getFatherPowerName() {
			return fatherPowerName;
		}

		public void setFatherPowerName(String fatherPowerName) {
			this.fatherPowerName = fatherPowerName;
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

		public String getPowerNum() {
			return powerNum;
		}

		public void setPowerNum(String powerNum) {
			this.powerNum = powerNum;
		}

		public String getPowerName() {
			return powerName;
		}

		public void setPowerName(String powerName) {
			this.powerName = powerName;
		}

		public String getPowerModule() {
			return powerModule;
		}

		public void setPowerModule(String powerModule) {
			this.powerModule = powerModule;
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

		public String getPowerFather() {
			return powerFather;
		}

		public void setPowerFather(String powerFather) {
			this.powerFather = powerFather;
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

		public UserPowerVO(Long id, String powerNum, String powerName, String powerModule, String powerType,
				String powerLevel, String powerFather, String isUse, Date powerUpdateTime) {
			super();
			this.id = id;
			this.powerNum = powerNum;
			this.powerName = powerName;
			this.powerModule = powerModule;
			this.powerType = powerType;
			this.powerLevel = powerLevel;
			this.powerFather = powerFather;
			this.isUse = isUse;
			this.powerUpdateTime = powerUpdateTime;
		}

		public UserPowerVO() {
			super();
		}



		public UserPowerVO(Long id, String powerNum, String powerName, String powerModule, String powerType,
				String powerLevel, String powerFather, String isUse, Date powerUpdateTime, String url, String icon,
				String src) {
			super();
			this.id = id;
			this.powerNum = powerNum;
			this.powerName = powerName;
			this.powerModule = powerModule;
			this.powerType = powerType;
			this.powerLevel = powerLevel;
			this.powerFather = powerFather;
			this.isUse = isUse;
			this.powerUpdateTime = powerUpdateTime;
			this.url = url;
			this.icon = icon;
			this.src = src;
		}

		@Override
		public String toString() {
			return "UserPowerVO [id=" + id + ", powerNum=" + powerNum + ", powerName=" + powerName + ", powerModule="
					+ powerModule + ", powerType=" + powerType + ", powerLevel=" + powerLevel + ", powerFather="
					+ powerFather + ", isUse=" + isUse + ", powerUpdateTime=" + powerUpdateTime + ", url=" + url
					+ ", icon=" + icon + ", src=" + src + ", powerTypeVal=" + powerTypeVal + ", powerLevelVal="
					+ powerLevelVal + ", fatherPowerName=" + fatherPowerName + ", specialPowerKey=" + specialPowerKey
					+ ", specialPowerValue=" + specialPowerValue + ", sort=" + sort + ", remoteUrl=" + remoteUrl + "]";
		}

		public UserPowerVO(Long id, String powerNum, String powerName, String powerModule, String powerType,
				String powerLevel, String powerFather, String isUse, Date powerUpdateTime, String url, String icon,
				String src, String powerTypeVal, String powerLevelVal, String fatherPowerName) {
			super();
			this.id = id;
			this.powerNum = powerNum;
			this.powerName = powerName;
			this.powerModule = powerModule;
			this.powerType = powerType;
			this.powerLevel = powerLevel;
			this.powerFather = powerFather;
			this.isUse = isUse;
			this.powerUpdateTime = powerUpdateTime;
			this.url = url;
			this.icon = icon;
			this.src = src;
			this.powerTypeVal = powerTypeVal;
			this.powerLevelVal = powerLevelVal;
			this.fatherPowerName = fatherPowerName;
		}
	    
	    
	    
}
