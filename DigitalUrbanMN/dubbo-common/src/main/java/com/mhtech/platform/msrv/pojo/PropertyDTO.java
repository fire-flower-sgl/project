package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class PropertyDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5747941799471318276L;
	
	private int pageSize;

	private int pageNo;
	
    private Long id;

    private String code;

    private String type;

    private String name;

    private String ip;

    private Date fixTime;

    private Integer state;
    
    private Integer isMonitor;
    
    private List<String> hardWareNameList;
    
    private List<PropertyHardwareDTO> hardWareList;



	public List<String> getHardWareNameList() {
		return hardWareNameList;
	}

	public void setHardWareNameList(List<String> hardWareNameList) {
		this.hardWareNameList = hardWareNameList;
	}


	public List<PropertyHardwareDTO> getHardWareList() {
		return hardWareList;
	}

	public void setHardWareList(List<PropertyHardwareDTO> hardWareList) {
		this.hardWareList = hardWareList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsMonitor() {
		return isMonitor;
	}

	public void setIsMonitor(Integer isMonitor) {
		this.isMonitor = isMonitor;
	}

	public PropertyDTO() {
		super();
	}

	public PropertyDTO(int pageSize, int pageNo, String type, String name, Integer state, Integer isMonitor) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.type = type;
		this.name = name;
		this.state = state;
		this.isMonitor = isMonitor;
	}

	public PropertyDTO(String code, String type, String name, String ip, Date fixTime, Integer state) {
		super();
		this.code = code;
		this.type = type;
		this.name = name;
		this.ip = ip;
		this.fixTime = fixTime;
		this.state = state;
	}
	

	public PropertyDTO(Long id,String code, String type, String name, String ip, Date fixTime, Integer state) {
		super();
		this.id = id;
		this.code = code;
		this.type = type;
		this.name = name;
		this.ip = ip;
		this.fixTime = fixTime;
		this.state = state;
	}

	@Override
	public String toString() {
		return "PropertyDTO [pageSize=" + pageSize + ", pageNo=" + pageNo + ", id=" + id + ", code=" + code + ", type="
				+ type + ", name=" + name + ", ip=" + ip + ", fixTime=" + fixTime + ", state=" + state + ", isMonitor="
				+ isMonitor + "]";
	}
    
    

}
