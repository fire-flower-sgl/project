package com.mobile.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.annotations.GenericGenerator;


/**
 *  mjl_
 *  SysPower
 *  系统权限类
 *  2019-10.22
 */

public class SysPower implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "id不能为空")
	private String id; //编码

	@NotNull(message = "powerNum不能为空")
	private String powerNum;//权限代码
	@NotNull(message = "powerName不能为空")
	private String powerName;//权限名称
	@NotNull(message = "powerModule不能为空")
	private String powerModule;//所属模块
	@NotNull(message = "powerType不能为空")
	private String powerType;//权限类型
	private String powerFather;//上级权限所在Power_num
	
	@NotNull(message = "powerLevel不能为空")
	private String powerLevel;//权限级别

	private String powerUpdateTime;//权限更新时间
	@NotNull(message = "isUse不能为空")
	private String isUse;//是否使用 1使用 0未使用
	
	private String specialPowerKey;//特殊权限key
	private String specialPowerValue;//特殊权限value
	
	private List<SysRolePower> sysRolePower ;
	
    private List<SysPower> children ;
	
	private String url;//路径
	
	private String icon;//加上--前端以后有用
	private String src;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getPowerFather() {
		return powerFather;
	}
	public void setPowerFather(String powerFather) {
		this.powerFather = powerFather;
	}
	public String getPowerLevel() {
		return powerLevel;
	}
	public void setPowerLevel(String powerLevel) {
		this.powerLevel = powerLevel;
	}
	public String getPowerUpdateTime() {
		return powerUpdateTime;
	}
	public void setPowerUpdateTime(String powerUpdateTime) {
		this.powerUpdateTime = powerUpdateTime;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public List<SysPower> getChildren() {
		return children;
	}
	public void setChildren(List<SysPower> children) {
		this.children = children;
	}
	public SysPower(String id, String powerNum, String powerName, String powerModule, String powerType,
			String powerFather, String powerLevel, String powerUpdateTime, String isUse) {
		super();
		this.id = id;
		this.powerNum = powerNum;
		this.powerName = powerName;
		this.powerModule = powerModule;
		this.powerType = powerType;
		this.powerFather = powerFather;
		this.powerLevel = powerLevel;
		this.powerUpdateTime = powerUpdateTime;
		this.isUse = isUse;
	}
	public SysPower() {
		super();
	
	}
	@Override
	public String toString() {
		return "SysPower [id=" + id + ", powerNum=" + powerNum + ", powerName=" + powerName + ", powerModule="
				+ powerModule + ", powerType=" + powerType + ", powerFather=" + powerFather + ", powerLevel="
				+ powerLevel + ", powerUpdateTime=" + powerUpdateTime + ", isUse=" + isUse + ", specialPowerKey="
				+ specialPowerKey + ", specialPowerValue=" + specialPowerValue + ", sysRolePower=" + sysRolePower
				+ ", children=" + children + "]";
	}
	
	
}
