package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@SuppressWarnings("serial")
public class AnalyzeFlowVO implements Serializable {

	@JsonSerialize(using=ToStringSerializer.class)
	private Long id;
	
	private String flowName;
	
	private String creator;
	
	private String creatorImg;
	
	public String getCreatorImg() {
		return creatorImg;
	}

	public void setCreatorImg(String creatorImg) {
		this.creatorImg = creatorImg;
	}

	private String creatorName;
	
	private String creatorLogo;
	
	private String memo = "Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization";

    private Date createdTime;
    
    private Date latestUpdatedTime = new Date();
    
    private String updator;
    
    private String updatorLogo;
    
    private Date latestBuildTime = new Date();
    
    private Short state;
    private String icon;//图片
    private Boolean isSign;
    
    

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getIsSign() {
		return isSign;
	}

	public void setIsSign(Boolean isSign) {
		this.isSign = isSign;
	}

	public String getCreatorLogo() {
		return creatorLogo;
	}

	public void setCreatorLogo(String creatorLogo) {
		this.creatorLogo = creatorLogo;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getUpdatorLogo() {
		return updatorLogo;
	}

	public void setUpdatorLogo(String updatorLogo) {
		this.updatorLogo = updatorLogo;
	}



	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getLatestUpdatedTime() {
		return latestUpdatedTime;
	}

	public void setLatestUpdatedTime(Date latestUpdatedTime) {
		this.latestUpdatedTime = latestUpdatedTime;
	}

	public Date getLatestBuildTime() {
		return latestBuildTime;
	}

	public void setLatestBuildTime(Date latestBuildTime) {
		this.latestBuildTime = latestBuildTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}
