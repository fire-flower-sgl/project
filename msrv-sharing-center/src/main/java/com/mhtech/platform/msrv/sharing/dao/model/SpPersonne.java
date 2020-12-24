package com.mhtech.platform.msrv.sharing.dao.model;

import java.io.Serializable;
import java.util.Date;

public class SpPersonne implements Serializable{
	
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

    private String email;
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno == null ? null : mobileno.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
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
        this.shiftId = shiftId == null ? null : shiftId.trim();
    }

    public String getIsLocalton() {
        return isLocalton;
    }

    public void setIsLocalton(String isLocalton) {
        this.isLocalton = isLocalton == null ? null : isLocalton.trim();
    }

	public SpPersonne() {
		super();
	}

	public SpPersonne(String id, String pcode, String pname, String mobileno, String unitId, String unitName,
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

	public SpPersonne(String id, String pcode, String pname, String mobileno, String unitId, String unitName,
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