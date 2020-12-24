package com.mhtech.platform.msrv.algorithm.exception;

/**
 * 数据集内容错误异常
 * @author GM
 */
@SuppressWarnings("serial")
public class ErrorDataSetContentException extends AlgorithmException {

	public ErrorDataSetContentException() {
		super(ErrorCode.ERROR_DATA_SET_CONTENT);
	}
}
