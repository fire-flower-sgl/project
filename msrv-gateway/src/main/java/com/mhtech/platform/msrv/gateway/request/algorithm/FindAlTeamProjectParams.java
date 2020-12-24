package com.mhtech.platform.msrv.gateway.request.algorithm;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

/**
 * FindAlTeamProject接口请求参数
 * @author Administrator
 *
 */
@ApiModel(value="FindAlTeamProject接口请求参数")
public class FindAlTeamProjectParams  {
	
	@NotNull(message = "id编码无法为空")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FindAlTeamProjectParams(@NotNull(message = "id编码无法为空") Long id) {
		super();
		this.id = id;
	}

	public FindAlTeamProjectParams() {
		super();
	}

	@Override
	public String toString() {
		return "FindAlTeamProjectParams [id=" + id + ", getId()=" + getId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
