package com.mhtech.platform.msrv.pojo;

/**
 * 10:数据集; 20:数据处理算子; 30:代码算子; 40:机器学习算子; 50:深度学习算子
 * @author GM
 */
public enum NodeType {
	
	DATASET((short) 10), OPERATOR_PROCESS((short) 20), OPERATOR_CODE((short) 30), OPERATOR_MACHINE((short) 40), OPERATOR_DEEP((short) 50);

	private Short typeValue;
	
	public static NodeType valueOf(short typeValue) {
		NodeType[] nodeTypes = values();
		for (NodeType nodeType : nodeTypes) {
			if(nodeType.typeValue == typeValue) {
				return nodeType;
			}
		}
		return null;
	}
	
	private NodeType(Short typeValue) {
		this.typeValue = typeValue;
	}

	public Short getTypeValue() {
		return typeValue;
	}
}
