package com.mhtech.platform.msrv.sharing.task.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mhtech.platform.msrv.sharing.utils.RedisUtils;

@Component
public class TestTask {
	
	
	
	@Value("${zookeeper.host}")
	String ip;
	
	@Autowired
	RedisUtils redisUtils;
	
	@Value("${task.effectiveTime}")
	private Long time;
	
	//@Scheduled(cron = "0 */1 * * * ?")
	public void method3() {			
		String key = "";
		try {
			key = this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean lock = redisUtils.lock(key,time);
			
			System.err.println(ip+"进来了..."+lock);
			if (lock) {
		      // 执行逻辑操作
		    	System.err.print("---"+ip+"---start");
		    	for (int i = 0; i < 9; i++) {
					try {
						Thread.sleep(1000);
						System.err.print("->");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		    	System.err.println("---end");
		    	
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			redisUtils.delete(key);
		}

		
	}
	
	
}
