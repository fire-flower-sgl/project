package com.mhtech.platform.msrv.gateway.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel(value="资产-服务器" ,description = "资产-服务器请求实体")
public class reqProObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4887688242440360371L;
	
	
//	@NotNull(message = "id为空!")
//	@ApiModelProperty(value="id主键",required=true)
//	private Long id;
	
	@NotNull(message = "code为空!")
	@ApiModelProperty(value="服务器编码",required=true)
	private String code;
	
	@ApiModelProperty(value="硬件名称集合",required=true)
	private List<String> hardWareNameList;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getHardWareNameList() {
		return hardWareNameList;
	}

	public void setHardWareNameList(List<String> hardWareNameList) {
		this.hardWareNameList = hardWareNameList;
	}
	
	
	
	


}
