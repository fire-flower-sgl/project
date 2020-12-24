package com.mhtech.platform.msrv.algorithm.exception;


public enum ErrorCode {
	
	NOT_FOUND_TEAM_WORK(				100101, "未匹配到工作团队"),
	NOT_FOUND_PROJECT(					100201, "未匹配到项目组"),
	
	EMPTY_FLOW_NODES(					100301, "流程节点不能为空"),
	NOT_FOUND_FLOW_NODE(				100302, "未识别流程节点类型"),
	ERROR_RELATED_FLOW_NODE(			100303, "节点关系错误"),
	MISSING_NODE_POSITION(				100304, "节点坐标信息丢失"),
	
	NOT_FOUND_DATA_SET(					100401, "未找到数据集"),
	ERROR_DATA_SET_CONTENT(				100402, "数据集模板内容错误"),
	DATA_SET_HAD_LOADED(				100403, "数据集已导入, 请勿重复执行"),
	DATA_SET_INVALID(					100404, "数据集无效状态, 请运行数据集"),
	
	NOT_FOUND_OPERATOR_RES(				100501, "未找到算子资源"),
	OPERATOR_RUN_FAILED(				100502, "算子运行失败"),
	
	NOT_FOUND_TEAM_MEMBER(				100601, "未找到组成员"),
	
	NORMAL(-1, "");
	
	private int code;
	
	private String message;
	
	private ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
