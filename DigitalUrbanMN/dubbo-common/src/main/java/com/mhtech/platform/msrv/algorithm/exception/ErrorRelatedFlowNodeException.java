package com.mhtech.platform.msrv.algorithm.exception;

@SuppressWarnings("serial")
public class ErrorRelatedFlowNodeException extends AlgorithmException {

	public ErrorRelatedFlowNodeException() {
		super(ErrorCode.ERROR_RELATED_FLOW_NODE);
	}
}
