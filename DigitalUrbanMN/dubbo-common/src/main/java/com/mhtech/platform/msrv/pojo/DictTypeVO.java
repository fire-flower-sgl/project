package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 字典类型对象
 * @author GM
 */
@SuppressWarnings("serial")
public class DictTypeVO implements Serializable {

	@ApiModelProperty("类型编码")
	private Integer code;

	@ApiModelProperty("类型名称")
    private String dictType;

	@ApiModelProperty("备注解释")
    private String memo;

	@ApiModelProperty("是否删除")
    private Boolean isDeleted;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
