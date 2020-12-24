package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OperatorParamDef implements Serializable {

	private String paramName;
	private String paramAlias;
	private Short paramType;
	private String defaultValue;
	private Short formType;
	private Short bindData;
	private String dictKey;
	private Boolean required;
	private Boolean isFixed;
	private Short sort;
	
	//用与直接返回前端中文
	private String formTypeName;//类型, 10:输入;20:输出;30:设置
	private String paramTypeName;//填写方式,用于前端动态渲染控件,10:文本框;20:单选;30:多选;40:日期(yyyy-MM-dd);50:时间(yyyy-MM-dd HH:mm:ss)
	private String bindDataName;//绑定数据源, 0:用户填写; 10:数据集表名; 20:表列名; 30:字典数据
	private String dictKeyName;
	
	
	public String getDictKeyName() {
		return dictKeyName;
	}
	public void setDictKeyName(String dictKeyName) {
		this.dictKeyName = dictKeyName;
	}
	public String getFormTypeName() {
		return formTypeName;
	}
	public void setFormTypeName(String formTypeName) {
		this.formTypeName = formTypeName;
	}
	public String getBindDataName() {
		return bindDataName;
	}
	public void setBindDataName(String bindDataName) {
		this.bindDataName = bindDataName;
	}
	public String getParamTypeName() {
		return paramTypeName;
	}
	public void setParamTypeName(String paramTypeName) {
		this.paramTypeName = paramTypeName;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamAlias() {
		return paramAlias;
	}
	public void setParamAlias(String paramAlias) {
		this.paramAlias = paramAlias;
	}
	public Short getParamType() {
		return paramType;
	}
	public void setParamType(Short paramType) {
		this.paramType = paramType;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public Short getFormType() {
		return formType;
	}
	public void setFormType(Short formType) {
		this.formType = formType;
	}
	public Short getBindData() {
		return bindData;
	}
	public void setBindData(Short bindData) {
		this.bindData = bindData;
	}
	public String getDictKey() {
		return dictKey;
	}
	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	public Boolean getIsFixed() {
		return isFixed;
	}
	public void setIsFixed(Boolean isFixed) {
		this.isFixed = isFixed;
	}
	public Short getSort() {
		return sort;
	}
	public void setSort(Short sort) {
		this.sort = sort;
	}
	public OperatorParamDef() {
		super();
	}
	@Override
	public String toString() {
		return "OperatorParamDef [paramName=" + paramName + ", paramAlias=" + paramAlias + ", paramType=" + paramType
				+ ", defaultValue=" + defaultValue + ", formType=" + formType + ", bindData=" + bindData + ", dictKey="
				+ dictKey + ", required=" + required + ", isFixed=" + isFixed + ", sort=" + sort + ", formTypeName="
				+ formTypeName + ", paramTypeName=" + paramTypeName + ", bindDataName=" + bindDataName
				+ ", dictKeyName=" + dictKeyName + "]";
	}
}
