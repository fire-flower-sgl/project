package com.mhtech.platform.msrv.gateway.request.algorithm;

public class UpdateSignParams {

	
	

	private String prjCode;

	private Boolean sign;


	public String getPrjCode() {
		return prjCode;
	}

	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
	}

	public Boolean getSign() {
		return sign;
	}

	public void setSign(Boolean sign) {
		this.sign = sign;
	}

	public UpdateSignParams( String prjCode, Boolean sign) {
		super();
		this.prjCode = prjCode;
		this.sign = sign;
	}

	public UpdateSignParams() {
		super();
	}

	
	
	
}
