package com.mhtech.common.result.entity;

import java.io.IOException;
import java.io.Serializable;

/**
 * @Desc //TODO 添加描述
 * @author jlm
 * 
 * @Date 2019年12月23日 下午3:38:04
 */
public class CustomException extends IOException implements Serializable {

	private static final long serialVersionUID = -4964720993425752082L;

	private String code;

    private String message;
    
    public CustomException(String code,String message) {
		super();
		this.code=code;
		this.message=message;
	}
    
    public CustomException(UnicomResponseEnums msg) {
    	super();
		this.code=msg.getStatus();
		this.message=msg.getMessage();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomException() {
		super();
	}

	
    
    
}
