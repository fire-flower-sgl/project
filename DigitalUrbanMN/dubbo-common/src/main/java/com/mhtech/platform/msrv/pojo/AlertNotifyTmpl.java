package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

public class AlertNotifyTmpl implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7525541374280641836L;

	private Long id;

    private Long serverId;

    private String tmplCode;

    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;

    private String ext5;
    
    private String ext6;
    
    private String ext7;

    private Boolean status;

    private String tmplConent;

    public String getExt7() {
		return ext7;
	}

	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}

	public String getExt6() {
		return ext6;
	}

	public void setExt6(String ext6) {
		this.ext6 = ext6;
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
        this.tmplCode = tmplCode == null ? null : tmplCode.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5 == null ? null : ext5.trim();
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
        this.tmplConent = tmplConent == null ? null : tmplConent.trim();
    }

	public AlertNotifyTmpl(Long id, Long serverId, String tmplCode, String ext1, String ext2, String ext3, String ext4,
			String ext5, Boolean status, String tmplConent) {
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

	public AlertNotifyTmpl() {
		super();
	}

	public AlertNotifyTmpl(Long serverId, String tmplCode, String ext1, String ext2, String ext3, String ext4,
			String ext5, Boolean status, String tmplConent) {
		super();
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
    
    
    
}