package com.mhtech.platform.msrv.sharing.service.dubbo.filter;

import java.util.List;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.pojo.MsrvLogVO;
import com.mhtech.platform.msrv.pojo.MsrvMessage;
import com.mhtech.platform.msrv.sharing.utils.RedisUtils;
import com.mobile.utils.JsonUtils;

@Activate(group = Constants.PROVIDER)
public class RecordProviderFilter implements Filter {
	
	IworkService iworkService;
	
	RedisUtils	redisUtils;
	
	private final Logger logger= LoggerFactory.getLogger(RecordProviderFilter.class);
	
	public void setIworkService(IworkService iworkService) {  
	    this.iworkService = iworkService;  
	}
	
	public void setRedisUtils(RedisUtils redisUtils) {  
	    this.redisUtils = redisUtils;  
	}

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)throws RpcException {
		String traceId = RpcContext.getContext().getAttachment("traceId");//traceId
		try {
			if(!StringUtils.isEmpty(traceId)) {
				long nextId = iworkService.getNextId();
				URL url = invoker.getUrl();
				String host = url.getHost();//ip
				Boolean isProviderSide = RpcContext.getContext().isProviderSide();//是否提供者
				String application = url.getParameter("application");//应用名称
				String interfaceName = url.getParameter("interface");//接口名称
				String methods = RpcContext.getContext().getMethodName();//方法名称
				String arguments = JsonUtils.object2DefaultJson(invocation.getArguments());//参数
				//新建对象
				MsrvLogVO msrvLog = new MsrvLogVO(nextId, Long.parseLong(traceId), isProviderSide, application, interfaceName, methods, host, null, arguments);
				//通过traceId获取redis中的对象
				MsrvMessage msrvMessage = (MsrvMessage) redisUtils.hget("MsrvLog", traceId);
				if(msrvMessage!=null) {
					List<MsrvLogVO> msrvLogList = msrvMessage.getMsrvLogList();
					msrvLogList.add(msrvLog);
					//放入redis
					redisUtils.hset("MsrvLog", traceId, msrvMessage);
				}
			}
		} catch (NumberFormatException e) {
			logger.error("RecordProviderFilter error",e.getMessage());
		} catch (InvalidDefinitionException e) {
			e.printStackTrace();
		}
		return invoker.invoke(invocation);
	}
}
