package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel("数据集记录查询")
@SuppressWarnings("serial")
public class DataSetRecordParam extends PageBO implements Serializable {

	@ApiModelProperty("数据集ID")
	@NotNull(message = "数据集ID不能为空")
	@Min(value = 1, message = "数据集ID错误")
	private Long dataSetId;
	
	@ApiModelProperty(hidden = true)
	private String userCode;

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
