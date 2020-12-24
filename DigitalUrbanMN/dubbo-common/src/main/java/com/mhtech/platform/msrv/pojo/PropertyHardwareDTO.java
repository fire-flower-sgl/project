package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PropertyHardwareDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3757677947407000230L;

	private Long id;

    private String propertyCode;

    private String hardwareName;

    private String hardwareType;

    private String alias;

    private Integer size;

    private String description;

    private Integer state;
    
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

	public PropertyHardwareDTO(Long id, String propertyCode, String hardwareName, String hardwareType, String alias,
			Integer size, String description, Integer state, Date createdTime) {
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

	public PropertyHardwareDTO() {
		super();
	}

	@Override
	public String toString() {
		return "PropertyHardwareVO [id=" + id + ", propertyCode=" + propertyCode + ", hardwareName=" + hardwareName
				+ ", hardwareType=" + hardwareType + ", alias=" + alias + ", size=" + size + ", description="
				+ description + ", state=" + state + ", createdTime=" + createdTime + "]";
	}
    
    
}