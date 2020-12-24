package com.mhtech.platform.msrv.gateway.request.algorithm;

import io.swagger.annotations.ApiModel;

/**
 * 通用id请求参数
 * @author Administrator
 *
 */
@ApiModel(value=" 通用id请求参数")
public class findId {

	private Integer teamCode;

	public Integer getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(Integer teamCode) {
		this.teamCode = teamCode;
	}

	
	
	
}
