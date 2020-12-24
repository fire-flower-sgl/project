package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@ApiModel("自定义数据集字段类型")
@SuppressWarnings("serial")
public class DefineDataSetType implements Serializable {

	@ApiModelProperty("字段名称")
	@NotNull(message = "字段名称不能为空")
	@NotBlank(message = "字段名称不能为空")
	@Length(max = 64, message = "字段名称错误")
	private String colName;
	@ApiModelProperty("字段类型值")
	@NotNull(message = "字段类型不能为空")
	private Short type;
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
}
