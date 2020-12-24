package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

public class HardwareTypeVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4969363413332727274L;

	private String typeCode;

    private String typeAlias;

    private String unit;

    private String memo;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getTypeAlias() {
        return typeAlias;
    }

    public void setTypeAlias(String typeAlias) {
        this.typeAlias = typeAlias == null ? null : typeAlias.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public HardwareTypeVO(String typeCode, String typeAlias, String unit, String memo) {
		super();
		this.typeCode = typeCode;
		this.typeAlias = typeAlias;
		this.unit = unit;
		this.memo = memo;
	}

	public HardwareTypeVO() {
		super();
	}
    
    
}