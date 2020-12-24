package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AlProjectLogVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long modelId;

    private String userCode;

    private String content;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createdTime;

    private String nickname;
    
    private String icon;
    
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public AlProjectLogVO(Long id, Long modelId, String userCode, String content, Date createdTime, String nickname,
			String icon) {
		super();
		this.id = id;
		this.modelId = modelId;
		this.userCode = userCode;
		this.content = content;
		this.createdTime = createdTime;
		this.nickname = nickname;
		this.icon = icon;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public AlProjectLogVO(Long id, Long modelId, String userCode, String content, Date createdTime) {
		super();
		this.id = id;
		this.modelId = modelId;
		this.userCode = userCode;
		this.content = content;
		this.createdTime = createdTime;
	}

	public AlProjectLogVO(Long id, Long modelId, String userCode, String content, Date createdTime, String nickname) {
		super();
		this.id = id;
		this.modelId = modelId;
		this.userCode = userCode;
		this.content = content;
		this.createdTime = createdTime;
		this.nickname = nickname;
	}

	public AlProjectLogVO() {
		super();
	}

    
}
