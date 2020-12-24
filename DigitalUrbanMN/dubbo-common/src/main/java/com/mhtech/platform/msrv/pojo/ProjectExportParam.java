package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@ApiModel("项目导出参数对象")
@SuppressWarnings("serial")
public class ProjectExportParam implements Serializable {

	@ApiModelProperty("项目编码")
	@NotNull(message = "项目编码不能为空")
	@NotBlank(message = "项目编码不能为空")
	@Length(max = 32, message = "项目编码错误")
	private String prjCode;
	@ApiModelProperty(value = "当前用户", hidden = true)
	private String userCode;
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
}
