package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="资产-服务器硬件",description="资产-服务器硬件信息" )
public class PropertyHardwareVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7586000342677452061L;

	/**
	 * 
	 */
	@ApiModelProperty(value="id主键")
	private Long id;

	@ApiModelProperty(value="资产编号")
    private String propertyCode;

	@ApiModelProperty(value="硬件名称")
    private String hardwareName;
	
	@ApiModelProperty(value="类型")
    private String hardwareType;

	@ApiModelProperty(value="硬件别称")
    private String alias;

	@ApiModelProperty(value="硬件尺寸")
    private Integer size;

	@ApiModelProperty(value="硬件描述")
	private String description;
    
	@ApiModelProperty(value="硬件状态")
    private Short state;
    
	@ApiModelProperty(value="硬件状态等级")
    private Short level;
    
    
    
    public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode == null ? null : propertyCode.trim();
    }

    public String getHardwareName() {
        return hardwareName;
    }

    public void setHardwareName(String hardwareName) {
        this.hardwareName = hardwareName == null ? null : hardwareName.trim();
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType == null ? null : hardwareType.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

	public PropertyHardwareVO(Long id, String propertyCode, String hardwareName, String hardwareType, String alias,
			Integer size, String description, Short state, Date createdTime) {
		super();
		this.id = id;
		this.propertyCode = propertyCode;
		this.hardwareName = hardwareName;
		this.hardwareType = hardwareType;
		this.alias = alias;
		this.size = size;
		this.description = description;
		this.state = state;
		this.createdTime = createdTime;
	}

	public PropertyHardwareVO() {
		super();
	}

	@Override
	public String toString() {
		return "PropertyHardwareVO [id=" + id + ", propertyCode=" + propertyCode + ", hardwareName=" + hardwareName
				+ ", hardwareType=" + hardwareType + ", alias=" + alias + ", size=" + size + ", description="
				+ description + ", state=" + state + ", createdTime=" + createdTime + "]";
	}
    
    
}