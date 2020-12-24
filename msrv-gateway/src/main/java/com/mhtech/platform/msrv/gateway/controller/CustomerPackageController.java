package com.mhtech.platform.msrv.gateway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.Utilis;
import com.mobile.model.CustomerPackage;
import com.mobile.model.Page;
import com.mobile.service.CustomerPackageService;
import com.mobile.utils.DateUtils;

/**
 * @ClassName CustomerPackageController 客户接口充值
 * @Description TODO
 * @Author admini
 * @Date 2019/7/25 15:25
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/MobileApi/CustomerPackage")
@SuppressWarnings("all")
public class CustomerPackageController {
//    @Reference(version = "1.0.0")
	@Autowired
    CustomerPackageService customerPackageService;

    @GetMapping(value="/add")
    @ResponseBody
    public RespObject<?> add(@RequestParam("customer_id") String customer_id,@RequestParam(value = "package_id",required = false) String package_id,@RequestParam("total") double total,
                      @RequestParam(value = "include",required = false) String include){
        CustomerPackage customerPackage=new CustomerPackage(Utilis.getUUID(),customer_id,package_id,total,0,0,include,DateUtils.getDateTime(),"0");
        return RespUtils.buildOKWithDataYg(customerPackageService.add(customerPackage));
    }
    @GetMapping(value="/del")
    @ResponseBody
    public RespObject<?> del(@RequestParam("id") String id){
        return RespUtils.buildOKWithDataYg(customerPackageService.delete(id));
    }
    @GetMapping(value="/modify")
    @ResponseBody
    public RespObject<?> update(@RequestParam("id") String id,@RequestParam("total") double total,@RequestParam("used") double used,@RequestParam("remaining") double remaining,
                         @RequestParam(value = "status",required = false) String status,@RequestParam(value = "include",required = false) String include){
        CustomerPackage customerPackage=new CustomerPackage(id,null,null,total,used,remaining,include,null,status);
        return RespUtils.buildOKWithDataYg(customerPackageService.update(customerPackage));
    }
    @GetMapping(value="/query")
    @ResponseBody
    public RespObject<?> query(@RequestParam("id") String id){
//        CustomerPackage customerPackage = customerPackageService.findCustomerPackage(id);
        Map<String, Object> customerPackageMap = customerPackageService.findCustomerPackageMap(id);
        List<Map<String, Object>> informationList = customerPackageService.findInformationList(id);
        customerPackageMap.put("include",informationList);
        return RespUtils.buildOKWithDataYg(customerPackageMap);
    }
    @GetMapping(value="/queryAll")
    @ResponseBody
    public RespObject<?> queryAll(@RequestParam(value = "customer_name",required = false) String customer_name,@RequestParam(value = "package_id",required = false) String package_id,
                           @RequestParam(value = "status",required = false) String status){
        Map<String,String> map=new HashMap<>();
        map.put("customer_name",customer_name);
        map.put("package_id",package_id);
        map.put("status",status);
        List<Map<String, Object>> customerPackageList = customerPackageService.findCustomerPackageList(map);
        return RespUtils.buildOKWithDataYg(customerPackageList);
    }
   
    @PostMapping(value="/topup")
    @ResponseBody
    public RespObject<?> topup(@RequestBody Map<String,String> map){
    	RespCode msg = RespCode.SUCCESS;
		if(map.get("topup")==null||map.get("customerid")==null||map.get("id")==null) {
			msg = RespCode.NULL_ARGUMENT;
			return RespUtils.build(msg);
		}
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher isNum = pattern.matcher(map.get("topup").toString());
		if(!isNum.matches()) {
			msg = RespCode.BAD_REQUEST;
			return RespUtils.build(msg);
		}
       int count=customerPackageService.updateTopup(map);
       if(count==0) {
			msg = RespCode.DATABASE_ERROR;
			return RespUtils.build(msg);
       }
       return RespUtils.build(msg);
    }
    @PostMapping(value = "/queryPageRecords")
    public RespObject<?> queryPageRecords(@RequestBody Map<String,Object> map){
    	Page findPrepaidRecords = customerPackageService.findPrepaidRecords(map);
    	return RespUtils.buildOKWithDataYg(findPrepaidRecords);
    }
    
    /**
     * 充值表分页查询
     * @param map
     * @return
     */
    @PostMapping(value="/queryPage")
    @ResponseBody
    public RespObject<?> queryPage(@RequestBody Map<String,Object> map){
         Page findInformationPage = customerPackageService.findInformationPage(map);
        return RespUtils.buildOKWithDataYg(findInformationPage);
    }
    
    /**
     * 客户充值卡分页查询
     * @param map
     * @return
     */
    @PostMapping(value="/queryCustomerCardPage")
    @ResponseBody
    public RespObject<?> queryCustomerCardPage(@RequestBody Map<String,Object> map){
        Page findInformationPage = customerPackageService.findCustomerCardPage(map);
        return RespUtils.buildOKWithDataYg(findInformationPage);
    }
    
    /**
     * 充值(new)
     * @param map
     * @return
     */
    @PostMapping(value="/topupNew")
    @ResponseBody
    public RespObject<?> topupNew(@RequestBody Map<String,Object> map){
		if(map.get("topup")==null) {
			return RespUtils.build(308, false, "充值次数不能为空");
		}
		if(!com.mobile.utils.Utilis.isNumber(map.get("topup").toString())) {
			return RespUtils.build(308, false, "充值次数不为数字");
		}
    	if(map.get("customerId")==null) {
    		return RespUtils.build(308, false, "客户编码不能为空");
		}
    	if(map.get("topupType")==null) {
    		return RespUtils.build(308, false, "充值类型不能为空");
		}
    	if(map.get("effectiveBegin")==null) {
    		return RespUtils.build(308, false, "有效开始日期不能为空");
		}
    	if(map.get("effectiveEnd")==null) {
    		return RespUtils.build(308, false, "有效结束日期不能为空");
		}
    	RespCode msg = RespCode.SUCCESS;
//		Pattern pattern = Pattern.compile("[0-9]{1,}");
//		Matcher isNum = pattern.matcher(map.get("topup").toString());
//		if(!isNum.matches()) {
//			msg = RespCode.BAD_REQUEST;
//			return RespUtils.build(msg);
//		}
       int customerTopUp = customerPackageService.customerTopUp(map);
       if(customerTopUp==0) {
			msg = RespCode.DATABASE_ERROR;
			return RespUtils.build(msg);
       }
       return RespUtils.build(msg);
    }
    
    /**
     * 充值表分页查询
     * @param map
     * @return
     */
    @PostMapping(value="/queryPk")
    @ResponseBody
    public RespObject<?> queryPk(@RequestBody Map<String,Object> map){
         Map<String, Object> findCustomerPackageMap = customerPackageService.findCustomerPackageMap(map);
        return RespUtils.buildOKWithDataYg(findCustomerPackageMap);
    }
}
