package com.mhtech.platform.msrv.gateway.request.algorithm;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UpdateMember接口(团队成员)请求参数")
public class UpdateMemberParams {

	@ApiModelProperty("主键编码")
	private Long id;

	@ApiModelProperty("所属团队")
	@NotBlank(message = "不能为空")
	private String teamCode;

	@ApiModelProperty("用户编号")
	@NotBlank(message = "不能为空")
	private String userCode;

	@ApiModelProperty("用户昵称")
	@NotBlank(message = "不能为空")
	private String nickname;

	@ApiModelProperty("邮箱")
	@NotBlank(message = "不能为空")
	private String email;

	private Date createdTime;
	
	@ApiModelProperty("头像")
	private String icon;
	
	public Long getId() {
		return id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
	
}
