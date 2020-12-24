package com.mhtech.platform.msrv.gateway.request.algorithm;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class ProjectFileParam implements Serializable {

	@NotNull(message = "项目文件不能为空")
	private MultipartFile uploadFile;
	private Boolean isOpen = false;
	@NotNull(message = "项目名称不能为空")
	@NotBlank(message = "项目名称不能为空")
	private String prjName;
	public String getPrjName() {
		return prjName;
	}
	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
}
