package com.mhtech.platform.msrv.auth.utils;

import static com.mhtech.platform.msrv.auth.bean.pojo.response.RespCode.SUCCESS;

import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.mhtech.platform.msrv.auth.bean.pojo.response.RespCode;
import com.mhtech.platform.msrv.auth.bean.pojo.response.RespObject;
import com.mhtech.platform.msrv.auth.exception.BizErrorCode;
import com.mhtech.platform.msrv.auth.exception.BizException;

public class RespUtils {

	private RespUtils() {}
	
	public static RespObject<?> OK = buildOKWithData(null);
	
	public static RespObject<?> build(RespCode rc) {
		return build(rc.getCode(), rc.getMessage());
	}
	
	public static RespObject<?> build(int code, String message) {
		return build(code, message, null);
	}
	
	public static <T> RespObject<T> build(int code, String message, T data) {
		return new RespObject<T>(code, message, data);
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
	
	public static RespObject<?> create(BizErrorCode error) {
		return build(error.getCode(), error.getMessage());
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
