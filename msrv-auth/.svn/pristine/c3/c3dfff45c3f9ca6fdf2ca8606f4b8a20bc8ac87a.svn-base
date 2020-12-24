package com.mhtech.platform.msrv.auth.dubbo.filter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;

import com.mhtech.platform.msrv.auth.utils.JsonUtils;
import com.mhtech.platform.msrv.auth.utils.RedisUtils;
import com.mhtech.platform.msrv.pojo.MsrvLogVO;
import com.mhtech.platform.msrv.pojo.MsrvMessage;

@Activate(group = Constants.CONSUMER)
public class ConsumptionLogFilter implements Filter {

	 RedisUtils redisUtils;


	 public void setRedisUtils(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	 }
	 

	private final Logger logger= LoggerFactory.getLogger(ConsumptionLogFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		
		String traceId = RpcContext.getContext().getAttachment("traceId");
		//不存在时----不存信息--放行
	    if (StringUtils.isEmpty(traceId)) return invoker.invoke(invocation);
		
	    com.alibaba.dubbo.common.URL url = invoker.getUrl();		
		boolean side = RpcContext.getContext().isConsumerSide();
		String arguments = JsonUtils.object2DefaultJson(invocation.getArguments());
		String application = url.getParameter("application");
		String interfaces = url.getParameter("interface");
		String methods = RpcContext.getContext().getMethodName();//方法名称
		String host = url.getHost();
		MsrvLogVO msrvLog = new MsrvLogVO(null, new Long(traceId), side, application, interfaces, methods, host, null, arguments);
		
		//redis获取对象
		MsrvMessage msrvMessage = (MsrvMessage) redisUtils.hget("MsrvLog", traceId);	
	
		if (msrvMessage!=null) {
			//追加
			List<MsrvLogVO> msrvLogList = msrvMessage.getMsrvLogList();
			msrvLogList.add(msrvLog);
			msrvMessage.setMsrvLogList(msrvLogList);
			redisUtils.hset("MsrvLog", traceId, msrvMessage);
		}else {
			logger.error("--------权限日志存入出错（消费者） redis数据异常---------系统异常");
		}

		return invoker.invoke(invocation);
	}

	
}
