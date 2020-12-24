package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@ApiModel("资产搜索")
public class PropertyParam {

	@ApiModelProperty("关键词类型")
//	@NotNull(message = "关键词类型")
//	@Min(value = 0, message = "关键词类型错误")
	private Short key;
	
	@ApiModelProperty("搜索关键词")
//	@NotNull(message = "关键词不能为空")
//	@NotBlank(message = "关键词不能为空")
	@Length(max = 64, message = "关键词超长")
	private String keyword;
	
	@ApiModelProperty("资产类型")
//	@NotNull(message = "资产类型不能为空")
//	@Min(value = 0, message = "资产类型错误")
	private Short category;
	
	@ApiModelProperty(value = "用户编码", hidden = true)
	private String userCode;

	public Short getKey() {
		return key;
	}

	public void setKey(Short key) {
		this.key = key;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Short getCategory() {
		return category;
	}

	public void setCategory(Short category) {
		this.category = category;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
