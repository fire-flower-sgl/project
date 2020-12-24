package com.mhtech.platform.msrv.gateway.request.algorithm;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

@ApiModel(value="DelectMember接口请求参数")
public class DelectMemberParams {

	@NotNull(message = "id编码无法为空")
	private Long id;

	@NotNull(message = "用户编码无法为空")
	private String userCode;
	
	public Long getId() {
		return id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
