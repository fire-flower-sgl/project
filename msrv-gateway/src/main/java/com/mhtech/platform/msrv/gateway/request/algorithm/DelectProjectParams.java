package com.mhtech.platform.msrv.gateway.request.algorithm;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * delectProjectMember接口请求参数
 * @author Administrator
 *
 */
@ApiModel(value="delectProjectMember接口请求参数")
public class DelectProjectParams {
	
	private Long[] id;
	
	@ApiModelProperty("用户编码")
	private String userCode;
	
	List<DelectPM > list;
	
	
	
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	
	
	public List<DelectPM> getList() {
		return list;
	}

	public void setList(List<DelectPM> list) {
		this.list = list;
	}

	public Long[] getId() {
		return id;
	}

	public void setId(Long[] id) {
		this.id = id;
	}

	public DelectProjectParams() {
		super();
	}

	

}
