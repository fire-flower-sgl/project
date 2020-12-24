package com.springboot.dubbo.service.impl;

import com.mobile.model.Manage;
import com.mobile.service.DemoService;
import org.springframework.stereotype.Service;

import com.springboot.dubbo.dao.DataFindDao;
//import com.springboot.dubbo.model.Manage;
//import com.springboot.dubbo.service.DemoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service(value="demoService")
public class DemoServiceImpl implements DemoService
{
	@Autowired
	DataFindDao dataFindDao;
	
	@Override
	public String hello(String name)
	{
//		String sql = "select count(*) from manage where id = ? ";
//		int value = dataFindDao.getTotal(sql, new Object[]{1});
//        System.out.println("\n\nlist.size()=====================================:" + value);
		
		String sql = "select * from manage where id < ? ";
		
		List<Manage> list = dataFindDao.getList(sql, new Object[]{3}, Manage.class);
        
        Manage manage = list.get(0);
        System.out.println("\nmanage.getMc()=====================================:" + manage.getMc());
        
		return "From Spring-Boot-Starter Provider, Hello " + name + "!";
	}
}
