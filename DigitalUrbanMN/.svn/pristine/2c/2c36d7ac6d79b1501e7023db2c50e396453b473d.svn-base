package com.mhtech.platform.msrv.algorithm.exception;

import com.mhtech.platform.msrv.exception.ServiceException;

@SuppressWarnings("serial")
public class AlgorithmException extends ServiceException {

	protected AlgorithmException(int code, String errorMsg) {
		super(code, errorMsg);
	}
	
	public AlgorithmException(ErrorCode ec) {
		this(ec.getCode(), ec.getMessage());
	}
	
	/**
	 * 自定义异常信息
	 * @param errorMsg 错误内容
	 */
	public AlgorithmException(String errorMsg) {
		this(ErrorCode.NORMAL.getCode(), errorMsg);
	}
}
