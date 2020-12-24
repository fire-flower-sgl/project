package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@SuppressWarnings("serial")
public class FlowNodeVO implements Serializable {

	@JsonSerialize(using=ToStringSerializer.class)
	private Long id;

	@JsonSerialize(using=ToStringSerializer.class)
	private Long elementId;
	
	private String nodeCode;
	
	private String nodeName;
	
	private Short nodeType;
	
	private List<FlowNodeVO> childNodes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Short getNodeType() {
		return nodeType;
	}

	public void setNodeType(Short nodeType) {
		this.nodeType = nodeType;
	}

	public List<FlowNodeVO> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(List<FlowNodeVO> childNodes) {
		this.childNodes = childNodes;
	}
}
