package com.mhtech.platform.msrv.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ImportResource(value = "classpath:dubbo-consumer.xml")
@ServletComponentScan
public class MsrvGatewayApplication {

	public static ApplicationContext applicationContext; 
	
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(MsrvGatewayApplication.class, args);
	}
	
}
