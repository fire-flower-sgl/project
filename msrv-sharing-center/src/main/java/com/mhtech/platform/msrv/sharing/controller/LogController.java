package com.mhtech.platform.msrv.sharing.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.log.pojo.GatewayAccessLogDTO;
import com.mhtech.platform.log.pojo.MsrvLogDTO;
import com.mhtech.platform.log.pojo.UserActionLogDTO;
import com.mhtech.platform.log.pojo.UserActionLogDetailDTO;
import com.mhtech.platform.msrv.sharing.config.GatewayAccessLogParameter;
import com.mhtech.platform.msrv.sharing.config.MsrvLogParameter;
import com.mhtech.platform.msrv.sharing.config.UserActionDetailParameter;
import com.mhtech.platform.msrv.sharing.config.UserActionLogParameter;
import com.mhtech.platform.msrv.sharing.response.RespObject;
import com.mhtech.platform.msrv.sharing.service.IGatewayAccessLogService;
import com.mhtech.platform.msrv.sharing.service.MsrvLogService;
import com.mhtech.platform.msrv.sharing.service.UserActionDetailService;
import com.mhtech.platform.msrv.sharing.service.UserActionLogService;
import com.mhtech.platform.msrv.sharing.utils.RespUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("Log")
@Api(value = "LogController", tags = "日志管理")
public class LogController {

	@Autowired
	private IGatewayAccessLogService gatewayAccesslog;
	@Autowired
	private MsrvLogService msrvLogService;
	@Autowired
	private UserActionDetailService userActionDetailService;
	@Autowired
	private UserActionLogService userActionLogParameter; 
	
    // 查询用户日志+条件分页
	@PostMapping(value = "/gatewayAccessLog")
	@ApiOperation(value = "查询用户日志+条件分页")
	public RespObject<?> gatewayAccessLog(@RequestBody GatewayAccessLogParameter  zh) {
		
		GatewayAccessLogDTO dto=new GatewayAccessLogDTO(zh.getId(),zh.getIp(),zh.getUrl(),zh.getParams(),
				zh.getTopTime(),zh.getEndTime(),zh.getRequestBody(),zh.getPageSize(),zh.getPageNo());
		return RespUtils.buildOKWithData(gatewayAccesslog.selectLog(dto));
	}

	//查询msrv日志+条件分页
	@PostMapping(value = "/msrvLog")
	@ApiOperation(value = "查询msrv日志+条件分页")
	public RespObject<?> msrvLog(@RequestBody MsrvLogParameter   zh) {
		
		MsrvLogDTO dto=new MsrvLogDTO(zh.getLogId(),zh.getTraceId(),zh.getSide(),zh.getApplication(),
				zh.getInterfaceName(),zh.getMethods(),zh.getHost(),zh.getTopTime(),zh.getEndTime(),
				zh.getArguments(),zh.getPageSize(),zh.getPageNo());
		return RespUtils.buildOKWithData(msrvLogService.selectLog(dto));
	}
	
	//查询sp_user_action_detail日志+条件分页
	@PostMapping(value = "/spUserDetailLog")
	@ApiOperation(value = "查询sp_user_action_detail日志+条件分页")
	public RespObject<?> spUserDetailLog(@RequestBody UserActionDetailParameter   zh) {
		
		UserActionLogDetailDTO dto=new UserActionLogDetailDTO(zh.getId(),zh.getUsrActId(),zh.getActionType(),
				zh.getActionSqlId(),zh.getSqlParams(),zh.getPageSize(),zh.getPageNo());
		return RespUtils.buildOKWithData(userActionDetailService.selectLog(dto));
	}
	//查询sp_user_action_log日志+条件分页
	@PostMapping(value = "/spUserLog")
	@ApiOperation(value = "查询sp_user_action_log日志+条件分页")
	public RespObject<?> spUserLog(@RequestBody UserActionLogParameter   zh) {
		
	    UserActionLogDTO dto=new UserActionLogDTO(zh.getId(),zh.getUserCode(),zh.getActionModule(),
	    		zh.getActionNum(),zh.getActionStartTime(),zh.getActionEndTime(),zh.getPageSize(),zh.getPageNo());
	    
		return RespUtils.buildOKWithData(userActionLogParameter.selectLog(dto));
	}
	
	
	//传入路径文件名  例： D:\\log\\allLog日志2020-02-24.xls
	@PostMapping(value = "/ecexlLog")
	@ApiOperation(value = "下载日志ecexl")
	public HttpServletResponse ecexlLog(String path, HttpServletResponse response) {
	    try {
	      // path是指欲下载的文件的路径。
	      File file = new File(path);
	      // 取得文件名。
	      String filename = file.getName();
	      // 取得文件的后缀名。
	      //String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
	 
	      // 以流的形式下载文件。
	      InputStream fis = new BufferedInputStream(new FileInputStream(path));
	      byte[] buffer = new byte[fis.available()];
	      fis.read(buffer);
	      fis.close();
	      // 清空response
	      response.reset();
	      // 设置response的Header
	      response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
	      response.addHeader("Content-Length", "" + file.length());
	      OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	      response.setContentType("application/octet-stream");
	      toClient.write(buffer);
	      toClient.flush();
	      toClient.close();
	    } catch (IOException ex) {
	      ex.printStackTrace();
	    }
	    return response;
	  }
	
	

}
