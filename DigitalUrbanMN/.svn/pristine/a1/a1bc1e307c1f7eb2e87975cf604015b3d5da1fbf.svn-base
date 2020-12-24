package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "资源使用量", description = "资源使用量")
public class ComponentWrapperVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7701990842964690173L;

	@ApiModelProperty("CPU使用量")
	private List<UsageVO> cpu;
	
	@ApiModelProperty("磁盘使用量")
	private List<UsageVO> disk;
	
	@ApiModelProperty("内存使用量")
	private List<UsageVO> ram;

	public List<UsageVO> getCpu() {
		return cpu;
	}

	public void setCpu(List<UsageVO> cpu) {
		this.cpu = cpu;
	}

	public List<UsageVO> getDisk() {
		return disk;
	}

	public void setDisk(List<UsageVO> disk) {
		this.disk = disk;
	}

	public List<UsageVO> getRam() {
		return ram;
	}

	public void setRam(List<UsageVO> ram) {
		this.ram = ram;
	}
}
