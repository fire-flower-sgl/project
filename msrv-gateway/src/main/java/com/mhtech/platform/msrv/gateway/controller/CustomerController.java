package com.mhtech.platform.msrv.gateway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.Utilis;
import com.mobile.model.Customer;
import com.mobile.model.Page;
import com.mobile.service.CustomerService;
import com.mobile.service.ParameterService;
import com.mobile.utils.DateUtils;
import com.mobile.utils.StringUtils;
import com.mobile.utils.UploadUtils;

/**
 * @ClassName CustomerController
 * @Description TODO 客户信息配置
 * @Author admini
 * @Date 2019/7/25 14:45
 * @Version 1.0
 */
@RequestMapping(value = "/MobileApi/Customer")
@RestController
@CrossOrigin
@SuppressWarnings("all")

public class CustomerController {
//    @Reference(version = "1.0.0")
	@Autowired
    CustomerService customerService;
//    @Reference(version = "1.0.0")
	@Autowired
    ParameterService parameter;
	
	/**
	 *客户信息新增
	 * @param customer
	 * @return
	 */
    @PostMapping(value = "/add")
    @ResponseBody
    public RespObject<?> add(@RequestBody Customer customer) {
//    	,@RequestParam("customer_name") String customer_name, @RequestParam("customer_ip") String customer_ip,@RequestParam("legal_person") String legal_person
//        Customer customer = new Customer(Utilis.getUUID(), customer_name, "0", customer_ip, DateUtils.getDateTime(), null, legal_person);
        return RespUtils.buildOKWithDataYg(customerService.add(customer));
    }
    /**
     * 客户信息删除
     * @param customer_id
     * @return
     */
    @PostMapping(value = "/del")
    @ResponseBody
    public RespObject<?> del(@RequestBody Customer customer) {
        return RespUtils.buildOKWithDataYg(customerService.delete(customer.getCustomerId()));
    }
   /**
    * 客户信息修改
    * @param customer
    * @return
    */
    @PostMapping(value = "/modify")
    @ResponseBody
    public RespObject<?> update(@RequestBody Customer customer) {
    	if(customer.getEmergencyContactMobile()!=null&&!StringUtils.isEmpty(customer.getEmergencyContactMobile())&&!Utilis.ValidateMobile(customer.getEmergencyContactMobile()))
    		return RespUtils.build(306, false, "手机号错误");
    	return RespUtils.buildOKWithDataYg(customerService.update(customer));
    }
    /**
     * 获取客户信息 显示客户编码，客户名称
     * @param map
     * @return
     */
    @PostMapping(value = "/queryAll")
    @ResponseBody
    public RespObject<?> queryAll(@RequestBody Map<String,Object> map) {
        List<Map<String, Object>> customerList = customerService.findCustomerList(map);
        return RespUtils.buildOKWithDataYg(customerList);
    }
    /**
     * 客户信息分页查询
     * @param map
     * @return
     */
    @PostMapping(value = "/queryPage")
    @ResponseBody
    public RespObject<?> queryPage(@RequestBody Map<String, String> map) {
        Page customerPage = customerService.findCustomerPage(map);
//        List status = parameter.findParameterList("status", "0");
//        customerPage.setData1(status);
        Object o = JSON.toJSON(customerPage);
        return RespUtils.buildOKWithDataYg(o);
    }

    @PostMapping(value = "/query")
    @ResponseBody
    public RespObject<?> query(@RequestBody Map maps) {
        Map<String, Object> customerMap = customerService.findCustomerMap(maps.get("customerId").toString(),"1");
//        List status = parameter.findParameterList("status", "0");
//        customerMap.put("status", status);
        return RespUtils.buildOKWithDataYg(customerMap);
    }

    /**
     * 功能需求：完成客户端的Excel表数据写入数据库功能
     *
     * @param file //用户上传的Excel文件
     * @param uploadUtils //上传文件的工具类 cn.zdxh.personnelmanage.utils.UploadUtils
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateExcel", method = RequestMethod.POST)
    @ResponseBody
    public RespObject<?> updateExcel(@RequestParam("file") MultipartFile file, UploadUtils uploadUtils) throws Exception {
        List<String[]> list =  uploadUtils.updateExcelUtils(file, 0);
       for (String []str:list){
           System.out.println(str.length);
       }
       return RespUtils.buildOKWithDataYg("tools/upload");
    }


}
