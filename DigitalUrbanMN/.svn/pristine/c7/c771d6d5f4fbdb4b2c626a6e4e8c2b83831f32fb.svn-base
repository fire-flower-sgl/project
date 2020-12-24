package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@ApiModel("5G算法中心数据集>>>>>>数据集对象")
@SuppressWarnings("serial")
public class DataSetVO implements Serializable {

	@ApiModelProperty("数据集ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	@ApiModelProperty("数据集名称")
	private String dataName;
	@ApiModelProperty("创建人")
	private String creator;
	@ApiModelProperty("用户头像")
	private String icon;
	
	private String creatorName;
	

	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	@ApiModelProperty("状态")
	private Short state;
	@ApiModelProperty("创建时间")
	private Date createdTime;
	@ApiModelProperty("来源")
	private String resource;
	@ApiModelProperty("文件大小, 单位KB")
	private Double fileSize;
	@ApiModelProperty("是否标星")
	private Boolean isSign;
	@ApiModelProperty("描述")
	private String memo;
	private Integer rowCount = 0;
	@ApiModelProperty("最后修改时间")
	private Date latestUpdatedTime = new Date();
	
	private String logo;
	
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Double getFileSize() {
		return fileSize;
	}
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}
	public Date getLatestUpdatedTime() {
		return latestUpdatedTime;
	}
	public void setLatestUpdatedTime(Date latestUpdatedTime) {
		this.latestUpdatedTime = latestUpdatedTime;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Boolean getIsSign() {
		return isSign;
	}
	public void setIsSign(Boolean isSign) {
		this.isSign = isSign;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
}
