package com.mhtech.platform.msrv.auth.exception;

/**
 * 认证失败异常
 * @author GM
 */
public class AuthenticationFailedException extends BizException {

	private static final long serialVersionUID = 1L;
	
	protected AuthenticationFailedException(int code, String message) {
		super(code, message);
	}
	
	public AuthenticationFailedException() {
		this(BizErrorCode.AUTHEN_FAILED.getCode(), BizErrorCode.AUTHEN_FAILED.getMessage());
	}
}
