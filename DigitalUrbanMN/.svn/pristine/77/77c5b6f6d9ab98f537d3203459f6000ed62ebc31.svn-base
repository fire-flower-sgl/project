package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@ApiModel("流程节点")
@SuppressWarnings("serial")
public class FlowNodeBO implements Serializable {

	@ApiModelProperty(hidden = true)
	private Long flowId;
	
	@ApiModelProperty("节点ID, 样本或者算子")
	@NotNull(message = "节点ID不能为空")
	@Min(value = 1, message = "节点ID错误")
	private Long elementId;
	
	@ApiModelProperty(value = "自定义节点编码, 可等同 elementId")
	@Length(max = 32, message = "节点编码超出长度")
	private String nodeCode = "";
	
	@ApiModelProperty(value = "自定义节点名称", hidden = true)
	@Length(max = 32, message = "节点名称超出长度")
	private String nodeName = "";
	
	@ApiModelProperty("节点类型, 数据集样本或算子 10:数据集; 20:数据处理算子; 30:代码算子; 40:机器学习算子; 50:深度学习算子")
	@NotNull(message = "节点类型不能为空")
	@Min(value = 0, message = "节点类型错误")
	@Max(value = 10000, message = "节点类型错误")
	private Short nodeType;
	
	@ApiModelProperty("节点类型, 数据集样本或算子")
	@Length(max = 64, message = "上级节点错误")
	private String lastNode = "";
	
	@ApiModelProperty("节点坐标位置")
	@NotNull(message = "节点坐标不能为空")
	private NodeItemPosition pos;

	public NodeItemPosition getPos() {
		return pos;
	}

	public void setPos(NodeItemPosition pos) {
		this.pos = pos;
	}

	public Short getNodeType() {
		return nodeType;
	}

	public void setNodeType(Short nodeType) {
		this.nodeType = nodeType;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
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

	public String getLastNode() {
		return lastNode;
	}

	public void setLastNode(String lastNode) {
		this.lastNode = lastNode;
	}
}
