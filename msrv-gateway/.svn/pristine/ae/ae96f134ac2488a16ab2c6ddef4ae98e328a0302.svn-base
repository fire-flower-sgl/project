package com.mhtech.platform.msrv.gateway.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mhtech.platform.msrv.pojo.PropertyHardwareDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="资产-服务器-新增实体",description="资产-服务器新增实体" )
public class ReqPropertyAdd {
	
	/*@NotBlank(message = "code为空!")
	@ApiModelProperty(value="编号",required=true)*/
    private String code;

	@NotBlank(message = "type为空!")
	@ApiModelProperty(value="类型",required=true)
    private String type;

	@NotBlank(message = "name为空!")
	@ApiModelProperty(value="名称",required=true)
    private String name;

	@NotBlank(message = "ip为空!")
	@ApiModelProperty(value="ip",required=true)
	@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$",message = "请输入正确ip!")
    private String ip;
	
	/*@NotNull(message = "维护时间为空!")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")*/
	@ApiModelProperty(value="维护时间",required=true)
    private Date fixTime;
	
    @NotNull(message = "当前状态state为空!")
    @ApiModelProperty(value="当前状态",required=true)
    private Integer state;
    
    @ApiModelProperty(value="管理员编码")
    private String userName;
    
   /* @ApiModelProperty(value="所属项目")
    private Object projects;*/

    @ApiModelProperty(value="是否监控")
    private String isMonitor;
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getFixTime() {
		return fixTime;
	}

	public void setFixTime(Date fixTime) {
		this.fixTime = fixTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*public Object getProjects() {
		return projects;
	}

	public void setProjects(Object projects) {
		this.projects = projects;
	}*/

	public String getIsMonitor() {
		return isMonitor;
	}

	public void setIsMonitor(String isMonitor) {
		this.isMonitor = isMonitor;
	}

	@Override
	public String toString() {
		return "ReqPropertyAdd [code=" + code + ", type=" + type + ", name=" + name + ", ip=" + ip + ", fixTime="
				+ fixTime + ", state=" + state + ", userName=" + userName + /*", projects=" + projects +*/ ", isMonitor="
				+ isMonitor + "]";
	}
    
	
    

}
