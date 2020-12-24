package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

public class AlProjectLogDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long modelId;

    private String userCode;

    private String content;

    private Date createdTime;
    
	private String prjCode;//公司编码
    
    private String endTime;//结束时间


	public String getPrjCode() {
		return prjCode;
	}

	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AlProjectLogDTO(Long id, Long modelId, String userCode, String content, Date createdTime) {
		super();
		this.id = id;
		this.modelId = modelId;
		this.userCode = userCode;
		this.content = content;
		this.createdTime = createdTime;
	}


	public AlProjectLogDTO() {
		super();
	}

	public AlProjectLogDTO(Long modelId, String userCode, String content) {
		super();
		this.modelId = modelId;
		this.userCode = userCode;
		this.content = content;
	}

	public AlProjectLogDTO(Long modelId, String userCode, String content, String prjCode) {
		super();
		this.modelId = modelId;
		this.userCode = userCode;
		this.content = content;
		this.prjCode = prjCode;
	}

	public AlProjectLogDTO(Long id, Long modelId, String userCode, String content, String prjCode, String endTime) {
		super();
		this.id = id;
		this.modelId = modelId;
		this.userCode = userCode;
		this.content = content;
		this.prjCode = prjCode;
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "AlProjectLogDTO [id=" + id + ", modelId=" + modelId + ", userCode=" + userCode + ", content=" + content
				+ ", createdTime=" + createdTime + ", prjCode=" + prjCode + ", endTime=" + endTime + "]";
	}

}
