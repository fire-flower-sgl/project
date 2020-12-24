package com.mhtech.platform.msrv.pojo;
/**
 * 5G算法验证码，返回实体类
 * @author Administrator
 *
 */
public class VerificationVO {

	private String uuid;
	private String imgName;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public VerificationVO(String uuid, String imgName) {
		super();
		this.uuid = uuid;
		this.imgName = imgName;
	}
	public VerificationVO() {
		super();
	}
	
	
	
	
}
