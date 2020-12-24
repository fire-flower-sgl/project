package com.mhtech.platform.msrv.pojo;

/**
 * 资产类型
 * @author GM
 */
public enum PropertyCategory {

	PROJECT((short) 10, "项目"),
	DATASET((short) 20, "数据集"),
	ANALYZE((short) 30, "分析流"),
	IDE((short) 40, "IDE"),
	MODEL((short) 50, "模型"),
	REPORT((short) 60, "分析报告");
	
	private Short category;
	private String type;
	private PropertyCategory(Short category, String type) {
		this.category = category;
		this.type = type;
	}
	public Short getCategory() {
		return category;
	}
	public String getType() {
		return type;
	}
}
