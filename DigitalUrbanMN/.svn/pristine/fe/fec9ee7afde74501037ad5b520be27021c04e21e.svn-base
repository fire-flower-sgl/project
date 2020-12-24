package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "资源使用量", description = "资源使用量")
public class ComponentUsageVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4056426573129858967L;

	@ApiModelProperty("组件名称")
	private String compntName;
	
	@ApiModelProperty("CPU使用量")
	private double cpu;
	
	@ApiModelProperty("磁盘使用量")
	private double disk;
	
	@ApiModelProperty("内存使用量")
	private double ram;

	public String getCompntName() {
		return compntName;
	}

	public void setCompntName(String compntName) {
		this.compntName = compntName;
	}

	public double getCpu() {
		return cpu;
	}

	public void setCpu(double cpu) {
		this.cpu = cpu;
	}

	public double getDisk() {
		return disk;
	}

	public void setDisk(double disk) {
		this.disk = disk;
	}

	public double getRam() {
		return ram;
	}

	public void setRam(double ram) {
		this.ram = ram;
	}
}
