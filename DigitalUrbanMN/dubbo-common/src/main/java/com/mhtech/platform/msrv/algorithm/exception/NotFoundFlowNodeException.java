package com.mhtech.platform.msrv.algorithm.exception;

@SuppressWarnings("serial")
public class NotFoundFlowNodeException extends AlgorithmException {

	public NotFoundFlowNodeException() {
		super(ErrorCode.NOT_FOUND_FLOW_NODE);
	}
}
