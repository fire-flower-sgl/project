package com.mhtech.platform.msrv.algorithm.exception;

@SuppressWarnings("serial")
public class NotFoundDataSetException extends AlgorithmException {

	public NotFoundDataSetException() {
		super(ErrorCode.NOT_FOUND_DATA_SET);
	}
}
