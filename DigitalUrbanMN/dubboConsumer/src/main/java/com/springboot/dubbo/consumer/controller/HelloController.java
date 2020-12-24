package com.springboot.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mobile.service.DemoService;
//import com.springboot.dubbo.service.DemoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
	@Reference(loadbalance="roundrobin")
	private DemoService demoService;
	

	@GetMapping(value = "/hello")
	public String index()
	{
		String value = demoService.hello("test");
		System.out.println("\n\n\n>>> "+value+" <<<\n\n\n");
		return value;
	}
}
