package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

public class IsSignVO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	
	private boolean isSign;
	
	public boolean getIsSign() {
		return isSign;
	}
	public void setIsSign(boolean isSign) {
		this.isSign = isSign;
	}
	
	



	private long id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public IsSignVO() {
		super();
	}
	
	
}
