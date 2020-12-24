package com.mhtech.platform.msrv.gateway.request.algorithm;

import java.util.List;

public class InsertListProjectMemberParams {

	private String prjCode;
	
	public String getPrjCode() {
		return prjCode;
	}

	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
	}

	private List<InsertProjectMemberParams> projectMember;

	public List<InsertProjectMemberParams> getProjectMember() {
		return projectMember;
	}

	public void setProjectMember(List<InsertProjectMemberParams> projectMember) {
		this.projectMember = projectMember;
	}
	
	
}
