package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="资产-服务器-响应类",description="资产-服务器信息-响应类" )
public class PropertyVOId implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4175442985921997529L;

	@ApiModelProperty(value="id主键")
	private String id;

	@ApiModelProperty(value="资产编号")
    private String code;

	@ApiModelProperty(value="资产类型")
    private String type;
	
	@ApiModelProperty(value="资产类型名称")
    private String typename;

	@ApiModelProperty(value="资产名称")
    private String name;

	@ApiModelProperty(value="资产ip")
    private String ip;

	@ApiModelProperty(value="维护时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date fixTime;

	@ApiModelProperty(value="资产状态")
    private String state;
	
	@ApiModelProperty(value="资产状态名称")
    private String statename;
	
	@ApiModelProperty(value="建立时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    
	@ApiModelProperty(value="所属项目")
    private String applyProjects;
    
	@ApiModelProperty(value="是否监控")
    private String isMonitor;
	
	@ApiModelProperty(value="管理人员")
    private String username;
	
	@ApiModelProperty(value="管理人员编码")
    private String usercode;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value="高警数")
    private Integer notifyCount;
    
    @ApiModelProperty(value="所属硬件集合")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<PropertyHardwareVO> hardwareList;
    
    
    
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public List<PropertyHardwareVO> getHardwareList() {
		return hardwareList;
	}

	public void setHardwareList(List<PropertyHardwareVO> hardwareList) {
		this.hardwareList = hardwareList;
	}

	public Integer getNotifyCount() {
		return notifyCount;
	}

	public void setNotifyCount(Integer notifyCount) {
		this.notifyCount = notifyCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getApplyProjects() {
		return applyProjects;
	}

	public void setApplyProjects(String applyProjects) {
		this.applyProjects = applyProjects;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getIsMonitor() {
		return isMonitor;
	}

	public void setIsMonitor(String isMonitor) {
		this.isMonitor = isMonitor;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public PropertyVOId() {
		super();
	}

	public PropertyVOId(String id, String code, String type, String name, String ip, Date fixTime, String state,
			Date createdTime, String applyProjects, String isMonitor) {
		super();
		this.id = id;
		this.code = code;
		this.type = type;
		this.name = name;
		this.ip = ip;
		this.fixTime = fixTime;
		this.state = state;
		this.createdTime = createdTime;
		this.applyProjects = applyProjects;
		this.isMonitor = isMonitor;
	}
	

	public PropertyVOId(String id, String code, String type, String name, String ip, Date fixTime, String state,
			Date createdTime) {
		super();
		this.id = id;
		this.code = code;
		this.type = type;
		this.name = name;
		this.ip = ip;
		this.fixTime = fixTime;
		this.state = state;
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return " {id:\"" + id + "\", code:\"" + code + "\", type:\"" + type + "\", name:\"" + name + "\", ip:\"" + ip + "\", fixTime:\""
				+ fixTime + "\", state:\"" + state + "\", createdTime:\"" + createdTime + "\", applyProjects:\"" + applyProjects
				+ "\", isMonitor:\"" + isMonitor + "\", notifyCount:\"" + notifyCount + "\", hardwareList:\"" + hardwareList + "\"}";
	}

	

}
