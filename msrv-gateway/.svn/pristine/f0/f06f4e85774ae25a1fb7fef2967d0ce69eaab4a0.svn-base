package com.mhtech.platform.msrv.gateway.controller.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.mhtech.common.result.entity.CustomException;
import com.mhtech.platform.msrv.exception.ServiceException;
import com.mhtech.platform.msrv.gateway.exception.BizException;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;

/**
 * 请求未匹配到资源时全局异常处理
 * <li>请求<code>content-type</code>不匹配</li>
 * <li>资源未找到</li>
 * <li>POST / GET 方式不匹配</li>
 * <li>...</li>
 * @author GM
 *
 */
@RestControllerAdvice
public class RequestMismatchHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = { NoHandlerFoundException.class })
	@ResponseBody
	public RespObject<?> noHandlerFoundException(NoHandlerFoundException ex) {
		return RespUtils.create(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public RespObject<?> handle(BindException exp) {
		StringBuilder errorInfo = new StringBuilder();
        String errorMessage ;
        BindingResult br = exp.getBindingResult();
        List<ObjectError> errors = br.getAllErrors();
        for (ObjectError item : errors) {
            errorInfo.append(item.getDefaultMessage()).append(",");
        }
        errorMessage = errorInfo.toString().substring(0, errorInfo.toString().length()-1);
		return RespUtils.build(RespCode.ILLEGAL_ARGUMENT.getCode(), errorMessage);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public RespObject<?> handle(MethodArgumentNotValidException exp) {
		StringBuilder errorInfo = new StringBuilder();
        String errorMessage ;
        BindingResult br = exp.getBindingResult();
        List<ObjectError> errors = br.getAllErrors();
        for (ObjectError item : errors) {
            errorInfo.append(item.getDefaultMessage()).append(",");
        }
        errorMessage = errorInfo.toString().substring(0, errorInfo.toString().length()-1);
		return RespUtils.build(RespCode.ILLEGAL_ARGUMENT.getCode(), errorMessage);
	}
	
	@ExceptionHandler(value = { BizException.class })
	@ResponseBody
	public RespObject<?> bizException(BizException ex) {
		return RespUtils.create(ex);
	}
	
	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	@ResponseBody
	public RespObject<?> exception(HttpMessageNotReadableException ex) {
		logger.error(RespCode.UNKNOWN.getMessage(), ex);
		return RespUtils.build(RespCode.UNKNOWN);
	}
	
	@ExceptionHandler(value = { ServiceException.class })
	@ResponseBody
	public RespObject<?> exception(ServiceException ex) {
		return RespUtils.build(ex.getCode(), ex.getErrorMsg());
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public RespObject<?> exception(Exception ex) {
		logger.error(RespCode.UNKNOWN.getMessage(), ex);
		return RespUtils.build(RespCode.UNKNOWN);
	}
	
	@ExceptionHandler(value = { RuntimeException.class })
	@ResponseBody
	public RespObject<?> exception(RuntimeException ex) {
		return RespUtils.build(00,false, ex.getMessage());
	}
	
	@ExceptionHandler(value = { CustomException.class })
	@ResponseBody
	public RespObject<?> exception(CustomException ex) {
		return RespUtils.build(Integer.parseInt(ex.getCode()),false, ex.getMessage());
	}
}
