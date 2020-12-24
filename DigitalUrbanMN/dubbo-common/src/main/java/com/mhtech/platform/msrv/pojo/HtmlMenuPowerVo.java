package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.List;

public class HtmlMenuPowerVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String index;
	private String title;
	private String powerFather;
	private String powerType;
	private String src;
	private String icon;
	
	private String url;
	public String getUrl() {
		return url;
	}
	public HtmlMenuPowerVo(String index, String title, String powerFather, String powerType, String src, String icon,
			String url) {
		super();
		this.index = index;
		this.title = title;
		this.powerFather = powerFather;
		this.powerType = powerType;
		this.src = src;
		this.icon = icon;
		this.url = url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private List<HtmlMenuPowerVo> subs;
	
	
	public List<HtmlMenuPowerVo> getSubs() {
		return subs;
	}
	public void setSubs(List<HtmlMenuPowerVo> subs) {
		this.subs = subs;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPowerFather() {
		return powerFather;
	}
	public void setPowerFather(String powerFather) {
		this.powerFather = powerFather;
	}
	public String getPowerType() {
		return powerType;
	}
	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public HtmlMenuPowerVo(String index, String title, String powerFather, String powerType, String src, String icon) {
		super();
		this.index = index;
		this.title = title;
		this.powerFather = powerFather;
		this.powerType = powerType;
		this.src = src;
		this.icon = icon;
	}
	public HtmlMenuPowerVo() {
		super();
	}
	
}
