package com.mhtech.platform.msrv.gateway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.Utilis;
import com.mobile.model.CustomerContact;
import com.mobile.service.CustomerContactService;
import com.mobile.utils.DateUtils;


/**
 * 
 * @ClassName:  CustomerContactController   
 * @Description:客户联系人管理控制类
 * @author: admin
 * @date:   Aug 28, 2019 10:37:00 AM   
 * @version: 1.0
 */
@RequestMapping(value = "/MobileApi/CustomerContactController")
@RestController
@CrossOrigin
@SuppressWarnings("all")

public class CustomerContactController {
	
//	@Reference(version = "1.0.0")
	@Autowired
	CustomerContactService customerContactService;
	
	//添加客户联系人
	@PostMapping(value = "/add")
	@ResponseBody
	public RespObject<?> add(@RequestParam("customer_id") String customer_id, 		//客户id
						@RequestParam("contact") String contact,			//联系人
						@RequestParam("mobileno") String mobileno,			//手机号码
						@RequestParam("first_contact") String first_contact	//第一联系人
						) {
		//实例对象
		CustomerContact customerContact = new CustomerContact(Utilis.getUUID(), customer_id, contact, mobileno, first_contact, DateUtils.getDateTime(), null);
		return RespUtils.buildOKWithDataYg(customerContactService.add(customerContact));
	}
	
	//根据id删除
	@PostMapping(value = "/del")
	@ResponseBody
	public RespObject<?> del(@RequestParam("id") String id) {
		return RespUtils.buildOKWithDataYg(customerContactService.delete(id));
	}
	
	//更新联系人
	@PostMapping(value = "/modify")
	@ResponseBody
	public RespObject<?> update(@RequestParam("id") String id,//id不能为空
						@RequestParam(value = "customer_id",required = false) String customer_id, 	//客户id
						@RequestParam(value = "contact",required = false) String contact,			//联系人
						@RequestParam(value = "mobileno",required = false) String mobileno,			//手机号码
						@RequestParam(value = "first_contact",required = false) String first_contact//第一联系人
						) {
		//实例对象
		CustomerContact customerContact = new CustomerContact(id, customer_id, contact, mobileno, first_contact, null, DateUtils.getDateTime());
		return RespUtils.buildOKWithDataYg(customerContactService.update(customerContact));
	}
	
	//根据id查询
	@PostMapping(value = "/query")
    @ResponseBody
    public RespObject<?> query(@RequestParam("id") String id) {
		Map<String, Object> customerContactMap = customerContactService.findCustomerContactMap(id);
        return RespUtils.buildOKWithDataYg(customerContactMap);
    }
	
	//模糊查询
	@PostMapping(value = "/queryAll")
    @ResponseBody
    public RespObject<?> queryAll(@RequestParam(value = "customer_id", required = false) String customer_id,		//客户id
    					   @RequestParam(value = "contact", required = false) String contact, 				//联系人
    					   @RequestParam(value = "mobileno", required = false) String mobileno, 			//手机号码
    					   @RequestParam(value = "first_contact", required = false) String first_contact 	//第一联系人
    					   ) {
		//数据放入map
        Map<String, String> map = new HashMap<>();
        map.put("customer_id", customer_id);
        map.put("contact", contact);
        map.put("mobileno", mobileno);
        map.put("first_contact", first_contact);
           
        //调用方法
        List<Map<String, Object>> customerContactList = customerContactService.findCustomerContactList(map);
//        System.out.println(customerContactList==null);
        List<Map<String, String>> list = Utilis.mapObjToString(customerContactList);
        return RespUtils.buildOKWithDataYg(list);
    }
	

		

}
