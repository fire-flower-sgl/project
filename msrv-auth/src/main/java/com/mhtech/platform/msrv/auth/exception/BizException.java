package com.mhtech.platform.msrv.auth.exception;

/**
 * 基础业务异常
 * @author GM
 *
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;

	protected BizException(int code, String message) {
		super(message);
		this.code = code;
	}
	
	public BizException(BizErrorCode error) {
		this(error.getCode(), error.getMessage());
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCode() {
		return code;
	}
}
