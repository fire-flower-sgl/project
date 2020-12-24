package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@ApiModel("数据集预览对象")
@SuppressWarnings("serial")
public class DataSetPreview implements Serializable {

	@ApiModelProperty("数据集记录")
	private List<Map<String, String>> records;
	@ApiModelProperty(value = "数据集列头", hidden = true)
	@Deprecated
	@JsonIgnore
	private List<Map<String, ?>> header;  //保证列头有序, 不使用HashMap
	public List<Map<String, String>> getRecords() {
		return records;
	}
	public void setRecords(List<Map<String, String>> records) {
		this.records = records;
	}
	public List<Map<String, ?>> getHeader() {
		return header;
	}
	public void setHeader(List<Map<String, ?>> header) {
		this.header = header;
	}
}
