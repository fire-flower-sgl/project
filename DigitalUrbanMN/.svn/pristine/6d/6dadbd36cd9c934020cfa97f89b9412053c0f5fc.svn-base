package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@ApiModel("文件数据集")
@SuppressWarnings("serial")
public class DataSetUploadResult implements Serializable {

	@ApiModelProperty("数据集ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long dataSetId;
	
	@ApiModelProperty("文件sheet索引")
	private List<SheetIndex> sheets;

	public List<SheetIndex> getSheets() {
		return sheets;
	}

	public void setSheets(List<SheetIndex> sheets) {
		this.sheets = sheets;
	}

	public Long getDataSetId() {
		return dataSetId;
	}

	public void setDataSetId(Long dataSetId) {
		this.dataSetId = dataSetId;
	}
}
