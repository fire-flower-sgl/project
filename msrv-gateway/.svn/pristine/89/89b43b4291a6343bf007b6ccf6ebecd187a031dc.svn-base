package com.mhtech.platform.msrv.gateway.request.algorithm;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class DataSetUploadParam implements Serializable {

	@NotNull(message = "数据集文件不能为空")
	private MultipartFile uploadFile;
	private Boolean isOpen = false;
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
