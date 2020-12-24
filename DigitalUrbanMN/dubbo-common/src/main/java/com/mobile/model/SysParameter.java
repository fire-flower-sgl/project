package com.mobile.model;

import java.io.Serializable;
import java.util.Date;

public class SysParameter implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;        //序号
    private String parmType;  //参数类型
    private String parmDesc;  //参数描述
    private String parmCode;  //参数编码
    private String parmName;  //参数名称
    private String parmStatus;  //参数状态   0：不可用1：可用   默认可用
    private Date parmUpdateTime;  //更新时间
    private String parmParent;   //父级参数
    private String parmFunc;  //所属业务
    private int parmNum;  //参数排序
    private int parmIsuse; //是否使用
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParmType() {
		return parmType;
	}
	public void setParmType(String parmType) {
		this.parmType = parmType;
	}
	public String getParmDesc() {
		return parmDesc;
	}
	public void setParmDesc(String parmDesc) {
		this.parmDesc = parmDesc;
	}
	public String getParmCode() {
		return parmCode;
	}
	public void setParmCode(String parmCode) {
		this.parmCode = parmCode;
	}
	public String getParmName() {
		return parmName;
	}
	public void setParmName(String parmName) {
		this.parmName = parmName;
	}
	public String getParmStatus() {
		return parmStatus;
	}
	public void setParmStatus(String parmStatus) {
		this.parmStatus = parmStatus;
	}
	public Date getParmUpdateTime() {
		return parmUpdateTime;
	}
	public void setParmUpdateTime(Date parmUpdateTime) {
		this.parmUpdateTime = parmUpdateTime;
	}
	public String getParmParent() {
		return parmParent;
	}
	public void setParmParent(String parmParent) {
		this.parmParent = parmParent;
	}
	public String getParmFunc() {
		return parmFunc;
	}
	public void setParmFunc(String parmFunc) {
		this.parmFunc = parmFunc;
	}
	public int getParmNum() {
		return parmNum;
	}
	public void setParmNum(int parmNum) {
		this.parmNum = parmNum;
	}
	public int getParmIsuse() {
		return parmIsuse;
	}
	public void setParmIsuse(int parmIsuse) {
		this.parmIsuse = parmIsuse;
	}
	@Override
	public String toString() {
		return "SysParameter [id=" + id + ", parmType=" + parmType + ", parmDesc=" + parmDesc + ", parmCode=" + parmCode
				+ ", parmName=" + parmName + ", parmStatus=" + parmStatus + ", parmUpdateTime=" + parmUpdateTime
				+ ", parmParent=" + parmParent + ", parmFunc=" + parmFunc + ", parmNum=" + parmNum + ", parmIsuse="
				+ parmIsuse + "]";
	}
    
    
	
}
