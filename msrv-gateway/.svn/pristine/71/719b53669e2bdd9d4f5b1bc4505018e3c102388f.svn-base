package com.mhtech.platform.msrv.gateway.request.algorithm;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * InsertProjectMember接口请求参数
 * 
 * @author Administrator
 *
 */
@ApiModel(value = "InsertProjectMember接口(项目成员)请求参数")
public class InsertProjectMemberParams {

	@ApiModelProperty("项目代码")
	@NotBlank(message = "不能为空")
	private String prjCode;

	@ApiModelProperty("用户编号")
	@NotBlank(message = "不能为空")
	private String userCode;

	@ApiModelProperty("成员角色, 10:管理者; 20:观察者; 30:开发者")
	@NotBlank(message = "不能为空")
	private Short teamRole;

	public String getPrjCode() {
		return prjCode;
	}

	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Short getTeamRole() {
		return teamRole;
	}

	public void setTeamRole(Short teamRole) {
		this.teamRole = teamRole;
	}

	public InsertProjectMemberParams(String prjCode, String userCode, Short teamRole) {
		super();
		this.prjCode = prjCode;
		this.userCode = userCode;
		this.teamRole = teamRole;
	}

	public InsertProjectMemberParams() {
		super();
	}


}
