package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AlTeamProjectVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String projectName;
	
	private Long id;

	private String prjCode;

	private String icon;

	public AlTeamProjectVO(String projectName, Long id, String prjCode, String icon, Boolean isOpen, String description,
			String[] tags, String version, String teamCode, Short state, Boolean isDeleted, String creator,
			Date createdTime, Long modelId) {
		super();
		this.projectName = projectName;
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
		this.creator = creator;
		this.createdTime = createdTime;
		this.modelId = modelId;
	}

	private Boolean isOpen;

	private String description;

	private String[] tags;

	private String version;

	private String teamCode;

	private Short state;

	private Boolean isDeleted;

	private String creator;
	
	private String creatorName;

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	private Date createdTime;
	
	private  List<AlTeamMemberVO> alTeamMemberVOs;//人员名称图片
	
	private  List<AlProjectMemberVO> alProjectMemberVOs;// 项目人员
	
	private String creatorImg;
	
	private Long modelId;

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public String getCreatorImg() {
		return creatorImg;
	}

	public void setCreatorImg(String creatorImg) {
		this.creatorImg = creatorImg;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public List<AlTeamMemberVO> getAlTeamMemberVOs() {
		return alTeamMemberVOs;
	}

	public void setAlTeamMemberVOs(List<AlTeamMemberVO> alTeamMemberVOs) {
		this.alTeamMemberVOs = alTeamMemberVOs;
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



	public List<AlProjectMemberVO> getAlProjectMemberVOs() {
		return alProjectMemberVOs;
	}

	public void setAlProjectMemberVOs(List<AlProjectMemberVO> alProjectMemberVOs) {
		this.alProjectMemberVOs = alProjectMemberVOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AlTeamProjectVO() {
		super();
	}

	public AlTeamProjectVO(String projectName, Long id, String prjCode, String icon, Boolean isOpen, String description,
			String[] tags, String version, String teamCode, Short state, Boolean isDeleted, String creator,
			Date createdTime, List<AlProjectMemberVO> alProjectMemberVOs) {
		super();
		this.projectName = projectName;
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
		this.creator = creator;
		this.createdTime = createdTime;
		this.alProjectMemberVOs = alProjectMemberVOs;
	}

	
	
	
	
	
	public AlTeamProjectVO(String projectName, Long id, String prjCode, String icon, Boolean isOpen, String description,
			String[] tags, String version, String teamCode, Short state, Boolean isDeleted, String creator,
			Date createdTime, List<AlProjectMemberVO> alProjectMemberVOs, Long modelId) {
		super();
		this.projectName = projectName;
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
		this.creator = creator;
		this.createdTime = createdTime;
		this.alProjectMemberVOs = alProjectMemberVOs;
		this.modelId = modelId;
	}

	
	
	
	
	
	public AlTeamProjectVO(String projectName, Long id, String prjCode, String icon, Boolean isOpen, String description,
			String[] tags, String version, String teamCode, Short state, Boolean isDeleted, String creator,
			String creatorName, Date createdTime, List<AlProjectMemberVO> alProjectMemberVOs, Long modelId) {
		super();
		this.projectName = projectName;
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
		this.creator = creator;
		this.creatorName = creatorName;
		this.createdTime = createdTime;
		this.alProjectMemberVOs = alProjectMemberVOs;
		this.modelId = modelId;
	}

	public AlTeamProjectVO(String projectName, Long id, String prjCode, String icon, Boolean isOpen, String description,
			String[] tags, String version, String teamCode, Short state, Boolean isDeleted, String creator,
			Date createdTime, String creatorImg, Long modelId) {
		super();
		this.projectName = projectName;
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
		this.creator = creator;
		this.createdTime = createdTime;
		this.creatorImg = creatorImg;
		this.modelId = modelId;
	}

	public AlTeamProjectVO(String projectName, Long id, String prjCode, String icon, Boolean isOpen, String description,
			String[] tags, String version, String teamCode, Short state, Boolean isDeleted, String creator,
			Date createdTime) {
		super();
		this.projectName = projectName;
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
		this.creator = creator;
		this.createdTime = createdTime;
	}

	public AlTeamProjectVO(Long id, String prjCode, String icon, Boolean isOpen, String description, String[] tags,
			String version, String teamCode, Short state, Boolean isDeleted, String creator, Date createdTime,
			 List<AlProjectMemberVO> alProjectMemberVOs) {
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
		this.creator = creator;
		this.createdTime = createdTime;
		this.alProjectMemberVOs = alProjectMemberVOs;
	}

	public AlTeamProjectVO(Long id, String prjCode, String icon, Boolean isOpen, String description, String[] tags,
			String version, String teamCode, Short state, Boolean isDeleted, String creator, Date createdTime) {
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
		this.creator = creator;
		this.createdTime = createdTime;
	}
	
	
	

}
