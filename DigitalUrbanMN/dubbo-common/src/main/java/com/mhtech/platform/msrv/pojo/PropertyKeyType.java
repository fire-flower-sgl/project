package com.mhtech.platform.msrv.pojo;

/**
 * 资产搜索分类
 * @author GM
 */
public enum PropertyKeyType {

	ALL((short) 0, "全部"),
	PROJECT((short) 10, "按项目搜索"),
	TAG((short) 20, "按标签搜索"),
	CREATOR((short) 30, "按创建人搜索");
	
	private Short type;
	private String category;
	private PropertyKeyType(Short type, String category) {
		this.type = type;
		this.category = category;
	}
	public Short getType() {
		return type;
	}
	public String getCategory() {
		return category;
	}
}
