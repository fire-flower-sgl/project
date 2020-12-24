package com.mhtech.platform.msrv.pojo;

/**
 * 20:数据处理算子; 30:代码算子; 40:机器学习算子; 50:深度学习算子
 * @author GM
 */
public enum OperatorType {

	OPERATOR_PROCESS((short) 20, "数据处理算子"), OPERATOR_CODE((short) 30, "代码算子"), OPERATOR_MACHINE((short) 40, "机器学习算子"), OPERATOR_DEEP((short) 50, "深度学习算子");
	
	public static OperatorType valuesOf(short val) {
		OperatorType[] values = values();
		for (OperatorType operatorType : values) {
			if(operatorType.getValue() == val) {
				return operatorType;
			}
		}
		return null;
	}
	
	private Short value;
	private String name;
	private OperatorType(Short value, String name) {
		this.value = value;
		this.name = name;
	}
	public Short getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
}
