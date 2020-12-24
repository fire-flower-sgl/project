package com.mhtech.platform.msrv.pojo;

/**
 * INT;BIGINT;SMALLINT;VARCHAR;DECIMAL;TIMESTAMP;BIT
 * 
 * @author GM
 */
public enum FieldType {

	INT((short) 10, "int", (short) 10, (short) 0), BIGINT((short) 20, "bigint",
			(short) 20, (short) 0), SMALLINT((short) 30, "smallint", (short) 4,
			(short) 0), VARCHAR((short) 40, "varchar", (short) 255, (short) 0), DECIMAL(
			(short) 50, "decimal", (short) 20, (short) 8), TIMESTAMP(
			(short) 60, "timestamp", (short) 0, (short) 0), BIT((short) 70,
			"bit", (short) 1, (short) 0), DATETIME((short) 80, "datetime", (short) 0, (short) 0);

	private Short value;
	private String type;
	private Short length;
	private Short fixed;

	public static FieldType valuesOf(short value) {
		FieldType[] types = values();
		for (FieldType fieldType : types) {
			if (fieldType.value == value) {
				return fieldType;
			}
		}
		return null;
	}

	private FieldType(Short value, String type, Short length, Short fixed) {
		this.value = value;
		this.type = type;
		this.length = length;
		this.fixed = fixed;
	}

	public Short getLength() {
		return length;
	}

	public Short getFixed() {
		return fixed;
	}

	public Short getValue() {
		return value;
	}

	public String getType() {
		return type;
	}
}
