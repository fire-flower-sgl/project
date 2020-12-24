package com.mhtech.platform.msrv.gateway.request.algorithm;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * updateProject接口请求参数
 * 
 * @author Administrator
 *
 */
@ApiModel(value = "updateProject(项目详情)接口请求参数")
public class UpdateProjectParams {
	
	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("项目名称")
	@NotBlank(message = "不能为空")
	private String projectName;
	
	@ApiModelProperty("项目代码")
	@NotBlank(message = "项目编码不能为空")
	private String prjCode;

	@ApiModelProperty("项目图片")
	private String icon;

	@ApiModelProperty("是否开放, 1:是; 2:否")
	private Boolean isOpen;

	@ApiModelProperty("项目描述")
	private String description;

	@ApiModelProperty("项目标签")
	private String[] tags;
	
	@ApiModelProperty("版本号")
	private String version;
	
	@ApiModelProperty("所属团队")
	@NotBlank(message = "所属团队不能为空")
	private String teamCode;
	
	@ApiModelProperty("项目状态, 10:开发中; 20:测试中; 30:运行中")
	private Short state;
	
	@ApiModelProperty("是否删除, 0:否; 1:是")
	private Boolean isDeleted;
	
	@ApiModelProperty("用户编码")
	@NotBlank(message = "用户编码不能为空")
	private String userCode;
	
	@ApiModelProperty("时间")
	private Date createdTime;
	
	@ApiModelProperty("业务主键")
	private Long modelId;

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrjCode() {
		return prjCode;
	}

	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public UpdateProjectParams() {
		super();
	}

	public UpdateProjectParams(Long id, String prjCode, String icon, Boolean isOpen, String description, String[] tags,
			String version, String teamCode, Short state, Boolean isDeleted, String userCode, Date createdTime) {
		super();
		this.id = id;
		this.prjCode = prjCode;
		this.icon = icon;
		this.isOpen = isOpen;
		this.description = description;
		this.tags = tags;
		this.version = version;
		this.teamCode = teamCode;
		this.state = state;
		this.isDeleted = isDeleted;
		this.userCode = userCode;
		this.createdTime = createdTime;
	}

}
