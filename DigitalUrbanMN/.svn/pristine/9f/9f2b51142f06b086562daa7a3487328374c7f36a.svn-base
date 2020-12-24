package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

/**
 * 字典类型查询对象
 * @author GM
 */
@ApiModel("字典类型查询对象")
@SuppressWarnings("serial")
public class DictTypeSearchParam extends PageBO implements Serializable {

	@ApiModelProperty("字典类型")
	@Length(max = 32, message = "类型错误")
	private String type;
	@ApiModelProperty("类型描述")
	@Length(max = 32, message = "类型解释描述错误")
	private String memo;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
