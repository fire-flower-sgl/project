package com.mhtech.platform.msrv.auth.exception;

import static com.mhtech.platform.msrv.auth.exception.BizErrorCode.SVR_DISPATCH_FAILED;

/**
 * 服务内部异常
 * @author GM
 *
 */
public class InternalException extends BizException {

	private static final long serialVersionUID = 1L;
	
	protected InternalException(int code, String message) {
		super(code, message);
	}
	
	public InternalException() {
		this(SVR_DISPATCH_FAILED.getCode(), SVR_DISPATCH_FAILED.getMessage());
	}
	
	public InternalException(String message) {
		this(SVR_DISPATCH_FAILED.getCode(), message);
	}
	
	public InternalException(Throwable t) {
		this(SVR_DISPATCH_FAILED.getCode(), t.getMessage());
	}
}
