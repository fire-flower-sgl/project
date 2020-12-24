package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class OperatorParamDefOption extends OperatorParamDef implements
		Serializable {

	private List<DictVO> options;

	public List<DictVO> getOptions() {
		return options;
	}

	public void setOptions(List<DictVO> options) {
		this.options = options;
	}
}
