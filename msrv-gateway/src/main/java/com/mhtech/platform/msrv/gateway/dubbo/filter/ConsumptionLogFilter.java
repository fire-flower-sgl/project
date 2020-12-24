package com.mhtech.platform.msrv.gateway.dubbo.filter;



import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.mhtech.platform.msrv.gateway.controller.handler.ControllerAspect;
import com.mhtech.platform.msrv.gateway.utils.RedisUtils;
import com.mhtech.platform.msrv.pojo.MsrvLogVO;
import com.mhtech.platform.msrv.pojo.MsrvMessage;
import com.mobile.utils.JsonUtils;


@Activate(group = Constants.CONSUMER)
public class ConsumptionLogFilter implements Filter {

	
	 RedisUtils redisUtils;
	 
	 public void setRedisUtils(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	 }
	 
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
	
		if(ControllerAspect.traceId.get() == null) return invoker.invoke(invocation);	
		
		String traceId=ControllerAspect.traceId.get().toString();
		com.alibaba.dubbo.common.URL url = invoker.getUrl();		
		boolean side = RpcContext.getContext().isConsumerSide();
		String arguments = null;
		try {
			arguments = JsonUtils.object2DefaultJson(invocation.getArguments());
		} catch (InvalidDefinitionException e) {
			e.printStackTrace();
		}
		String application = url.getParameter("application");
		String interfaces = url.getParameter("interface");
		String methods = RpcContext.getContext().getMethodName();//方法名称
		String host = url.getHost();
	
		MsrvLogVO msrvLog = new MsrvLogVO(null, new Long(traceId), side, application, interfaces, methods, host, null, arguments);

		//redis获取对象
		MsrvMessage msrvMessage = (MsrvMessage) redisUtils.hget("MsrvLog", traceId);
		MsrvMessage msrv=new MsrvMessage();
		if (msrvMessage!=null) {
			//追加
			List<MsrvLogVO> msrvLogList = msrvMessage.getMsrvLogList();
			msrvLogList.add(msrvLog);
			msrv.setMsrvLogList(msrvLogList);
		}else {
			//新增
			List<MsrvLogVO> msrvLogList = new ArrayList<>();
			msrvLogList.add(msrvLog);
			msrv.setMsrvLogList(msrvLogList);	
		}
		
		//存入
		redisUtils.hset("MsrvLog", traceId, msrv);
		//放入traceId
		RpcContext.getContext().setAttachment("traceId",traceId.toString());
		
		return invoker.invoke(invocation);
	}



}
