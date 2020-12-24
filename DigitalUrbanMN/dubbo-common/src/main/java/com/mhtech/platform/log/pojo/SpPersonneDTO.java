package com.mhtech.platform.log.pojo;

import java.io.Serializable;
import java.util.Date;

public class SpPersonneDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
    private String pcode;

    private String pname;

    private String mobileno;

    private String unitId;

    private String unitName;

    private Date createDate;

    private Integer validFlag;

    private String shiftId;

    private String isLocalton;
    private String  email;

    private int pageSize;// 分页-每页条数
   	private int pageNo;// 分页-当前页
	public String getPcode() {
		return pcode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	public String getIsLocalton() {
		return isLocalton;
	}
	public void setIsLocalton(String isLocalton) {
		this.isLocalton = isLocalton;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public SpPersonneDTO() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public SpPersonneDTO(String pcode, String pname, String mobileno, String unitId, String unitName, Date createDate,
			Integer validFlag, String shiftId, String isLocalton, int pageSize, int pageNo) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.mobileno = mobileno;
		this.unitId = unitId;
		this.unitName = unitName;
		this.createDate = createDate;
		this.validFlag = validFlag;
		this.shiftId = shiftId;
		this.isLocalton = isLocalton;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}
	public SpPersonneDTO(int pageSize, int pageNo) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}
	public SpPersonneDTO(String id, String pcode, String pname, String mobileno, String unitId, String unitName,
			Date createDate, Integer validFlag, String shiftId, String isLocalton) {
		super();
		this.id = id;
		this.pcode = pcode;
		this.pname = pname;
		this.mobileno = mobileno;
		this.unitId = unitId;
		this.unitName = unitName;
		this.createDate = createDate;
		this.validFlag = validFlag;
		this.shiftId = shiftId;
		this.isLocalton = isLocalton;
	}
	public SpPersonneDTO(String pcode, String pname, String mobileno, String unitId, String unitName, Date createDate,
			Integer validFlag, String shiftId, String isLocalton) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.mobileno = mobileno;
		this.unitId = unitId;
		this.unitName = unitName;
		this.createDate = createDate;
		this.validFlag = validFlag;
		this.shiftId = shiftId;
		this.isLocalton = isLocalton;
	}
	public SpPersonneDTO(String pcode, String pname, String mobileno, String unitId, String unitName, Date createDate,
			Integer validFlag, String shiftId, String isLocalton, String email) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.mobileno = mobileno;
		this.unitId = unitId;
		this.unitName = unitName;
		this.createDate = createDate;
		this.validFlag = validFlag;
		this.shiftId = shiftId;
		this.isLocalton = isLocalton;
		this.email = email;
	}
	public SpPersonneDTO(String id, String pcode, String pname, String mobileno, String unitId, String unitName,
			Date createDate, Integer validFlag, String shiftId, String isLocalton, String email) {
		super();
		this.id = id;
		this.pcode = pcode;
		this.pname = pname;
		this.mobileno = mobileno;
		this.unitId = unitId;
		this.unitName = unitName;
		this.createDate = createDate;
		this.validFlag = validFlag;
		this.shiftId = shiftId;
		this.isLocalton = isLocalton;
		this.email = email;
	}
	
    
}
