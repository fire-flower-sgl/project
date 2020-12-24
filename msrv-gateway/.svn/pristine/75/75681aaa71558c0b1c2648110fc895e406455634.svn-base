package com.mhtech.platform.msrv.gateway.request.algorithm;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * UpdateProjectMember接口请求参数
 * 
 * @author Administrator
 *
 */

@ApiModel(value = "UpdateProjectMember接口(项目成员)请求参数")
public class UpdateProjectMemberParams {
	
	@ApiModelProperty("主键编码")
	private Long id;

	@ApiModelProperty("项目代码")
	@NotBlank(message = "不能为空")
	private String prjCode;

	@ApiModelProperty("用户编号")
	@NotBlank(message = "不能为空")
	private String userCode;

	@ApiModelProperty("成员角色, 10:管理者; 20:观察者; 30:开发者")
	@NotBlank(message = "不能为空")
	private Short teamRole;
	
	@ApiModelProperty("时间")
	private Date createdTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public UpdateProjectMemberParams(Long id, String prjCode, String userCode, Short teamRole, Date createdTime) {
		super();
		this.id = id;
		this.prjCode = prjCode;
		this.userCode = userCode;
		this.teamRole = teamRole;
		this.createdTime = createdTime;
	}
	public UpdateProjectMemberParams() {
		super();
	}

}
