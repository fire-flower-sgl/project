package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 算子属性常量
 * @author GM
 */
@ApiModel("算子属性常量")
public class OperatorPropConst {

	@ApiModelProperty("输入输出类型")
	private List<DictVO> paramType;
	@ApiModelProperty("参数渲染类型")
	private List<DictVO> formType;
	@ApiModelProperty("参数数据来源")
	private List<DictVO> bindData;
	@ApiModelProperty("参数是否可修改")
	private List<DictVO> isFixed;
	@ApiModelProperty("参数是否表填")
	private List<DictVO> required;
	@ApiModelProperty("字典key值")
	private List<DictVO> dictKeys;
	public List<DictVO> getDictKeys() {
		return dictKeys;
	}
	public void setDictKeys(List<DictVO> dictKeys) {
		this.dictKeys = dictKeys;
	}
	public List<DictVO> getParamType() {
		return paramType;
	}
	public void setParamType(List<DictVO> paramType) {
		this.paramType = paramType;
	}
	public List<DictVO> getFormType() {
		return formType;
	}
	public void setFormType(List<DictVO> formType) {
		this.formType = formType;
	}
	public List<DictVO> getBindData() {
		return bindData;
	}
	public void setBindData(List<DictVO> bindData) {
		this.bindData = bindData;
	}
	public List<DictVO> getIsFixed() {
		return isFixed;
	}
	public void setIsFixed(List<DictVO> isFixed) {
		this.isFixed = isFixed;
	}
	public List<DictVO> getRequired() {
		return required;
	}
	public void setRequired(List<DictVO> required) {
		this.required = required;
	}
}
