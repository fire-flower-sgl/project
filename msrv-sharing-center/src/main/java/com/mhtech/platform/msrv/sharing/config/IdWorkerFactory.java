package com.mhtech.platform.msrv.sharing.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * @see com.mhtech.platform.msrv.sharing.config.IdWorker
 * @author GM
 */
@Component
public class IdWorkerFactory {

	@Value("${server.local.workerid}")
	private long workerId; // 机器id
	@Value("${server.local.datacenterid}")
	private long datacenterId; // 机房id
	@Value("${server.local.sequence}")
	private long sequence; // 一毫秒内生成的多个id的最新序号
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@Value("${server.local.start_time}")
	private Date startTime;
	
	@Bean
	public IdWorker idWorker() {
		IdWorker idWorker = new IdWorker(workerId,
				datacenterId, sequence);
		if(startTime.after(new Date())) {
			throw new IllegalArgumentException("设置的服务器运行时间不能大于当前时间");
		}
		idWorker.setTwepoch(startTime.getTime());
		return idWorker;
	}
}
