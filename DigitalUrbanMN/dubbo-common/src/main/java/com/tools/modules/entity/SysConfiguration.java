package com.tools.modules.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName SysConfiguration
 * @Description TODO 图表参数配置类
 * @Author admin
 * @Date 2019/8/28 13:53
 * @Version 1.0
 */
@ApiModel(value="图表参数配置类")
public class SysConfiguration {
	
	@ApiModelProperty(value="业务编码",name="businessId",required=false)
	private String businessId;		//业务id
	@ApiModelProperty(value="业务名称",name="businessName")
	private String businessName;	//业务name
	@ApiModelProperty(value="图表id",name="chartId")
	private String chartId;			//图表编码
	@ApiModelProperty(value="参数名",name="Name")
	private String Name;			//参数key
	@ApiModelProperty(value="参数值",name="Value")
	private String Value;			//参数value
	
	private String funcType; //是否是模板
	
	private String updateTime;//模板新增or更新时间
	private int select;//0：预览 1：编辑
	private int legendfontSize;//图例字体大小
	
	
	public int getLegendfontSize() {
		return legendfontSize;
	}
	public void setLegendfontSize(int legendfontSize) {
		this.legendfontSize = legendfontSize;
	}
	public SysConfiguration(String businessId, String businessName, String chartId, String name, String value,
			String funcType, String updateTime, int select) {
		super();
		this.businessId = businessId;
		this.businessName = businessName;
		this.chartId = chartId;
		Name = name;
		Value = value;
		this.funcType = funcType;
		this.updateTime = updateTime;
		this.select = select;
	}
	public int getSelect() {
		return select;
	}
	public void setSelect(int select) {
		this.select = select;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public SysConfiguration() {
		super();
	}
	
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getChartId() {
		return chartId;
	}
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getFuncType() {
		return funcType;
	}
	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}
	public SysConfiguration(String businessName, String chartId, String name, String value) {
		super();
		this.businessName = businessName;
		this.chartId = chartId;
		Name = name;
		Value = value;
	}
	public SysConfiguration(String businessName, String name, String value) {
		super();
		this.businessName = businessName;
		Name = name;
		Value = value;
	}
	

}
