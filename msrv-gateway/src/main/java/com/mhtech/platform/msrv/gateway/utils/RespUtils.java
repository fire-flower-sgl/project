package com.mhtech.platform.msrv.gateway.utils;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import static com.mhtech.platform.msrv.gateway.response.RespCode.SUCCESS;
import com.mhtech.platform.msrv.gateway.exception.BizException;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;

public class RespUtils {

	private RespUtils() {}
	
	public static RespObject<?> OK = buildOKWithData(null);
	
	public static RespObject<?> build(RespCode rc) {
		return build(rc.getCode(), rc.getMessage());
	}
	
	public static <T> RespObject<T> build(RespCode rc, T data) {
		return build(rc.getCode(), rc.getMessage(),data);
	}
	
	public static RespObject<?> build(int code, String message) {
		return build(code, message, null);
	}
	
	public static <T> RespObject<T> build(int code, String message, T data) {
		return new RespObject<T>(code, message, data);
	}
	//添加success
	public static <T> RespObject<T> build(int code,boolean success,String message, T data) {
		return new RespObject<T>(code, success,message, data);
	} 
	//添加success
	public static <T> RespObject<T> build(int code,boolean success,String message) {
		return new RespObject<T>(code,success,message);
	} 
	//success用于游鸽返回字段
	public static <T> RespObject<T> buildOKWithDataYg(T data) {
		if(Objects.isNull(data)) {
			return new RespObject<T>(SUCCESS.getCode(),false,SUCCESS.getMessage());
		}
		return new RespObject<T>(SUCCESS.getCode(),true,SUCCESS.getMessage(), data);
	}
	
	//success用于游鸽返回字段(返回增加success:true/false)
	public static <T> RespObject<T> buildOKWithDataYg(boolean flag,T data) {
		if(Objects.isNull(data)) {
			return new RespObject<T>(SUCCESS.getCode(),false,SUCCESS.getMessage());
		}
		if(Objects.isNull(flag)) {
			return new RespObject<T>(SUCCESS.getCode(),flag,SUCCESS.getMessage(), data);
		}
		return new RespObject<T>(SUCCESS.getCode(),true,SUCCESS.getMessage(), data);
	}
	
	public static <T> RespObject<T> buildOKWithData(T data) {
		if(Objects.isNull(data)) {
			return new RespObject<T>(SUCCESS.getCode(), SUCCESS.getMessage());
		}
		return new RespObject<T>(SUCCESS.getCode(), SUCCESS.getMessage(), data);
	}
	
	public static RespObject<?> create(RespCode rc) {
		return build(rc.getCode(), rc.getMessage());
	}
	
	public static RespObject<?> create(HttpStatus hs) {
		return build(hs.value(), hs.getReasonPhrase());
	}
	
	/**
	 * 根据业务异常创建响应信息
	 * @param ex
	 * @return
	 */
	public static <T extends BizException> RespObject<?> create(T ex) {
		return build(ex.getCode(), ex.getMessage());
	}
}
