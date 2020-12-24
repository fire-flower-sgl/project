package com.mhtech.platform.msrv.gateway.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="资产-服务器新增硬件",description="资产-服务器硬件新增实体" )
public class reqPropertyHardware {
	
	@NotBlank(message = "名称为空!")
	@ApiModelProperty(value="名称",required=true)
	private String hardwareName;

	@NotBlank(message = "类型为空!")
	@ApiModelProperty(value="类型",required=true)
	private String hardwareType;
	
	@NotBlank(message = "别称为空!")
	@ApiModelProperty(value="别称",required=true)
	private String alias;
	
	@NotNull(message = "size为空!")
	@ApiModelProperty(value="尺寸",required=true)
	private Integer size;
	
	@NotBlank(message = "描述为空!")
	@ApiModelProperty(value="描述",required=true)
	private String description;
	
	@NotNull(message = "state为空!")
	@ApiModelProperty(value="状态",required=true)
	private Integer state;

	public String getHardwareName() {
		return hardwareName;
	}

	public void setHardwareName(String hardwareName) {
		this.hardwareName = hardwareName;
	}

	public String getHardwareType() {
		return hardwareType;
	}

	public void setHardwareType(String hardwareType) {
		this.hardwareType = hardwareType;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	

}
