package com.mhtech.platform.msrv.gateway.request.algorithm;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * InsertWork接口请求参数
 * 
 * @author Administrator
 *
 */

@ApiModel(value = "InsertWork(团队详情)接口请求参数")
public class InsertWorkParams {

	@ApiModelProperty("团队编码")
	private Integer teamCode;

	@ApiModelProperty("团队名称")
	@NotBlank(message = "不能为空")
	private String teamName;

	@ApiModelProperty("联系方式")
	@NotBlank(message = "不能为空")
	private String mobile;

	@ApiModelProperty("邮箱")
	@NotBlank(message = "不能为空")
	private String email;

	@ApiModelProperty("时间")
	private Date createdTime;

	public Integer getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(Integer teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public InsertWorkParams(Integer teamCode, String teamName, String mobile, String email, Date createdTime) {
		super();
		this.teamCode = teamCode;
		this.teamName = teamName;
		this.mobile = mobile;
		this.email = email;
		this.createdTime = createdTime;
	}

	public InsertWorkParams() {
		super();
	}

}
