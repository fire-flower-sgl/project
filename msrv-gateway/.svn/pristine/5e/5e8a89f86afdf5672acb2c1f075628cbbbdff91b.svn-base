package com.mhtech.platform.msrv.gateway.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "服务器硬件添加")
public class reqPropertyHardwareAdd {
	
	@NotBlank(message = "propertyCode为空!")
	@ApiModelProperty(value="编号",required=true)
	private  String propertyCode;
	
	@ApiModelProperty(value="硬件",required=true)
	private List<reqPropertyHardware> hardwareList;

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public List<reqPropertyHardware> getHardwareList() {
		return hardwareList;
	}

	public void setHardwareList(List<reqPropertyHardware> hardwareList) {
		this.hardwareList = hardwareList;
	}

	

}
