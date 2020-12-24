package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 处理返回值html_btn权限
 * @author Administrator
 *
 */
public class HtmlBtnVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String powerNum;
    private String powerName;
	private String powerFather;
    
    private HtmlBtnVO listbtn;
    private String powerType;
    private String userCode;
    
    public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	private List<HtmlBtnVO> children;
    
	 private String powerUrl;
    
    
    
    
	
	public String getPowerUrl() {
		return powerUrl;
	}
	public void setPowerUrl(String powerUrl) {
		this.powerUrl = powerUrl;
	}
	public List<HtmlBtnVO> getChildren() {
		return children;
	}
	public void setChildren(List<HtmlBtnVO> children) {
		this.children = children;
	}
	public String getPowerType() {
		return powerType;
	}
	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}
	
	public HtmlBtnVO getListbtn() {
		return listbtn;
	}
	public void setListbtn(HtmlBtnVO listbtn) {
		this.listbtn = listbtn;
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
	public HtmlBtnVO(Long id, String powerNum, String powerName) {
		super();
		this.id = id;
		this.powerNum = powerNum;
		this.powerName = powerName;
	}
	
	public HtmlBtnVO(Long id, String powerNum, String powerName, String powerFather, String powerType) {
		super();
		this.id = id;
		this.powerNum = powerNum;
		this.powerName = powerName;
		this.powerFather = powerFather;
		this.powerType = powerType;
	}
	public HtmlBtnVO(Long id, String powerNum, String powerName, String powerFather) {
		super();
		this.id = id;
		this.powerNum = powerNum;
		this.powerName = powerName;
		this.powerFather = powerFather;
	}
	public String getPowerFather() {
		return powerFather;
	}
	public void setPowerFather(String powerFather) {
		this.powerFather = powerFather;
	}
	
	public HtmlBtnVO() {
		super();
	}
    @Override
	public String toString() {
		return "HtmlBtnVO [id=" + id + ", powerNum=" + powerNum + ", powerName=" + powerName + ", powerFather="
				+ powerFather + ", listbtn=" + listbtn + "]";
	}
}
