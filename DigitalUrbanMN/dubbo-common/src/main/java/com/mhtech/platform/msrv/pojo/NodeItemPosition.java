package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@ApiModel("节点坐标对象")
@SuppressWarnings("serial")
public class NodeItemPosition implements Serializable {

	@ApiModelProperty("坐标X")
	@NotNull(message = "坐标X不能为空")
	private Double x;
	@ApiModelProperty("坐标Y")
	@NotNull(message = "坐标Y不能为空")
	private Double y;
	
	public NodeItemPosition() {}
	
	public NodeItemPosition(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
}
