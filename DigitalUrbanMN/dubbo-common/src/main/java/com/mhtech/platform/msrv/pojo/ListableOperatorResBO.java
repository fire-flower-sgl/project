package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ListableOperatorResBO extends PageableOperatorResBO implements
		Serializable {

	private List<Long> operatorResIds;
	
	private String teamCode;

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public List<Long> getOperatorResIds() {
		return operatorResIds;
	}

	public void setOperatorResIds(List<Long> operatorResIds) {
		this.operatorResIds = operatorResIds;
	}
}
