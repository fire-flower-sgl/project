package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProjectFile implements Serializable {

	private byte[] content;
	private String fileName;
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
