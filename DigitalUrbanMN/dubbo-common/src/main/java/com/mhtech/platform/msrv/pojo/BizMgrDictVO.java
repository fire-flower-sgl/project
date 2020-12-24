package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApiModel("业务参数字典")
public class BizMgrDictVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6130274133657601539L;

	@ApiModelProperty("告警级别")
	private List<Map<Object, Object>> alertLevel = new ArrayList<Map<Object, Object>>();

	@ApiModelProperty("所在服务器")
	private List<Map<Object, Object>> servers = new ArrayList<Map<Object, Object>>();
	
	@ApiModelProperty("所属业务")
	private List<Map<Object, Object>> bizServers = new ArrayList<Map<Object, Object>>();

	@ApiModelProperty("处理状态")
	private List<Map<Object, Object>> processe = new ArrayList<Map<Object, Object>>();

	@ApiModelProperty("是否通知")
	private List<Map<Object, Object>> notified = new ArrayList<Map<Object, Object>>();

	public List<Map<Object, Object>> getBizServers() {
		return bizServers;
	}

	public void setBizServers(List<Map<Object, Object>> bizServers) {
		this.bizServers = bizServers;
	}

	public List<Map<Object, Object>> getAlertLevel() {
		return alertLevel;
	}

	public void setAlertLevel(List<Map<Object, Object>> alertLevel) {
		this.alertLevel = alertLevel;
	}

	public List<Map<Object, Object>> getServers() {
		return servers;
	}

	public void setServers(List<Map<Object, Object>> servers) {
		this.servers = servers;
	}

	public List<Map<Object, Object>> getProcesse() {
		return processe;
	}

	public void setProcesse(List<Map<Object, Object>> processe) {
		this.processe = processe;
	}

	public List<Map<Object, Object>> getNotified() {
		return notified;
	}

	public void setNotified(List<Map<Object, Object>> notified) {
		this.notified = notified;
	}
}
