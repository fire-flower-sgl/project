package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@SuppressWarnings("serial")
public class DataSetRunner implements Serializable {

	@NotNull(message = "数据集ID不能为空")
	@Min(value = 1, message = "数据集ID错误")
	private Long dataSetId;
	
	private String userCode;
	
//	@NotNull(message = "数据集定义名称不能为空")
//	@Length(max = 32, message = "数据及名称不合法")
	private String dataSetName;

	public String getDataSetName() {
		return dataSetName;
	}

	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getDataSetId() {
		return dataSetId;
	}

	public void setDataSetId(Long dataSetId) {
		this.dataSetId = dataSetId;
	}
}
