package com.mhtech.platform.msrv.gateway.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="告警通知模板结果集",description="告警通知模板结果集")
public class AlertNotifyTmplParams {

	private Long id;

	@ApiModelProperty("服务主键")
	@NotNull(message = "服务主键不能为空")
	@Min(value = 0, message = "服务主键错误")
    private Long serverId;

	@ApiModelProperty("模板编码")
	@NotBlank(message = "不能为空")
    private String tmplCode;
	
	@ApiModelProperty("自定义短文1")
	@NotBlank(message = "不能为空")
    private String ext1;
	
	@ApiModelProperty("自定义短文2")
	@NotBlank(message = "不能为空")
    private String ext2;
	
	@ApiModelProperty("自定义短文3")
	@NotBlank(message = "不能为空")
    private String ext3;
	
	@ApiModelProperty("自定义短文4")
	@NotBlank(message = "不能为空")
    private String ext4;
	
	@ApiModelProperty("自定义短文5")
	@NotBlank(message = "不能为空")
    private String ext5;
	
	@ApiModelProperty("状态")
    private Boolean status;
    
    @NotBlank(message = "模板内容")
    private String tmplConent;

	public AlertNotifyTmplParams(Long id, Long serverId, String tmplCode, String ext1, String ext2, String ext3,
			String ext4, String ext5, Boolean status, String tmplConent) {
		super();
		this.id = id;
		this.serverId = serverId;
		this.tmplCode = tmplCode;
		this.ext1 = ext1;
		this.ext2 = ext2;
		this.ext3 = ext3;
		this.ext4 = ext4;
		this.ext5 = ext5;
		this.status = status;
		this.tmplConent = tmplConent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getTmplCode() {
		return tmplCode;
	}

	public void setTmplCode(String tmplCode) {
		this.tmplCode = tmplCode;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	public String getExt5() {
		return ext5;
	}

	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getTmplConent() {
		return tmplConent;
	}

	public void setTmplConent(String tmplConent) {
		this.tmplConent = tmplConent;
	}

	public AlertNotifyTmplParams() {
		super();
	}

	@Override
	public String toString() {
		return "AlertNotifyTmplParams [id=" + id + ", serverId=" + serverId + ", tmplCode=" + tmplCode + ", ext1="
				+ ext1 + ", ext2=" + ext2 + ", ext3=" + ext3 + ", ext4=" + ext4 + ", ext5=" + ext5 + ", status="
				+ status + ", tmplConent=" + tmplConent + "]";
	}
    
    
    
    
}
