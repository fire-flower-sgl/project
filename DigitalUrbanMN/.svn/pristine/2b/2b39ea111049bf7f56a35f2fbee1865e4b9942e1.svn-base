package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DictVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8416689803046095931L;

	@JsonProperty("key")
	@JSONField(name = "key")
	private String dictName;
	
	@JsonProperty("value")
	@JSONField(name = "value")
	private String dictValue;
	
	@JsonIgnore
	@JSONField(serialize = false)
	private String dictType;

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
}
