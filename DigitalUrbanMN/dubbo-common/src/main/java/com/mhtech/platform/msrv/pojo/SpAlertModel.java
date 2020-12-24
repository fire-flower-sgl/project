package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * @ClassName SysParameter
 * @Description TODO 图表参数配置模块
 * @Author admin
 * @Date 2019/8/28 13:53
 * @Version 1.0
 */
public class SpAlertModel implements Serializable{

	private String  id;//id
	private String  modelName ;//模板名称
	private String  paramName ;//监测字段-告警指标
	private String  alterValue ;//预警值-阈值-指标阈值
	private String  alertLimit ;//预警上限-频次/次数
	private String  level ;//预警级别-告警级别
	private String  duration;//间隔龄-频次/时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getAlterValue() {
		return alterValue;
	}
	public void setAlterValue(String alterValue) {
		this.alterValue = alterValue;
	}
	public String getAlertLimit() {
		return alertLimit;
	}
	public void setAlertLimit(String alertLimit) {
		this.alertLimit = alertLimit;
	}
	
	
}
