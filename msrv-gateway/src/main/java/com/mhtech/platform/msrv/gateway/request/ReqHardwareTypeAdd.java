package com.mhtech.platform.msrv.gateway.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="硬件类型",description="硬件类型-实体" )
public class ReqHardwareTypeAdd implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1831738920282382860L;

	@NotBlank(message = "typeCode为空!")
	@ApiModelProperty(value="编号",required=true)
	private String typeCode;

	@NotBlank(message = "typeAlias为空!")
	@ApiModelProperty(value="别名",required=true)
    private String typeAlias;

	@NotBlank(message = "unit为空!")
	@ApiModelProperty(value="单位",required=true)
    private String unit;

	@NotBlank(message = "memo为空!")
	@ApiModelProperty(value="备注",required=true)
    private String memo;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeAlias() {
		return typeAlias;
	}

	public void setTypeAlias(String typeAlias) {
		this.typeAlias = typeAlias;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
    
    
}
