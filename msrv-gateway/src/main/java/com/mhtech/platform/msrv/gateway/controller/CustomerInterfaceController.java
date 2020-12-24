package com.mhtech.platform.msrv.gateway.controller;

import java.util.ArrayList;
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

import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mobile.service.CustomerInterfaceService;
import com.mobile.utils.DateUtils;
import com.mobile.utils.DoubleUtils;
import com.mobile.utils.Utilis;

/**
 * @ClassName CustomerInterfaceController
 * @Description TODO 客户接口配置
 * @Author admini
 * @Date 2019/8/2 13:10
 * @Version 1.0
 */
@RequestMapping(value = "/MobileApi/CustomerInterface")
@ResponseBody
@RestController
@CrossOrigin
@SuppressWarnings("all")
public class CustomerInterfaceController {
//    @Reference(version = "1.0.0")
	@Autowired
    CustomerInterfaceService customerInterfaceService;
    
    @RequestMapping(value = "/adds",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public RespObject<?> adds(@RequestBody Map<String,Object> map){
        //{"name":123,"age":[1,2,2]}  {"name":123,"age":["1","2","2"]}  {"customer_id":"b5decc00ac7d4e19","fasename":["1","2","2"],"faceclass":[2,1,2],"frequency":[4,2,4],"total":[8,9,0]}
        List<Map<String,String>> list=new ArrayList<>();
        String customer_id="";
        if(map!=null){
            customer_id=map.get("customer_id").toString();
            List list_fasename = (List) map.get("fasename");
            List list_faceclass = (List) map.get("faceclass");
            List list_frequency = (List) map.get("frequency");
            List list_total = (List) map.get("total");
            if(list_fasename!=null&&list_fasename.size()>0){
                for (int i=0;i<list_fasename.size();i++){
                    Map<String,String> map1=new HashMap<>();
                    map1.put("id",Utilis.getUUID());
                    map1.put("fasename",list_fasename.get(i).toString());
                    map1.put("faceclass",list_faceclass.get(i).toString());
                    map1.put("frequency",list_frequency.get(i).toString());
                    map1.put("total",list_total.get(i).toString());
                    list.add(map1);
                }
            }
        }
        if(list!=null&&list.size()>0){
            return RespUtils.buildOKWithDataYg(customerInterfaceService.add(list,customer_id));
        }
        return RespUtils.buildOKWithDataYg(0);
    }
    /**
     * 查询所有客户接口信息
     * @param map
     * @return
     */
    @PostMapping(value = "/queryAll")
    @ResponseBody
    public RespObject<?> queryAll(@RequestBody Map<String, String> map) {
        String customerId="";
        if(map!=null&&map.get("customerId")!=null){
            customerId=map.get("customerId").toString();
        }
        List<Map<String, String>> customerInterfaceList = customerInterfaceService.findCustomerInterfaceList(customerId);
        return RespUtils.buildOKWithDataYg(customerInterfaceList);
    }
    /**
     * 分页查询客户接口信息
     * @param map
     * @return
     */
    @PostMapping(value = "/queryPage")
    @ResponseBody
    public RespObject<?> queryPage(@RequestBody Map<String, String> map) {
        return RespUtils.buildOKWithDataYg(customerInterfaceService.findCustomerInterfacePage(map));
    }
    @PostMapping(value = "/del")
    @ResponseBody
    public RespObject<?> del(@RequestBody Map<String, String> map) {
        int delete = customerInterfaceService.delete(map.get("Id"));
        return RespUtils.buildOKWithDataYg(delete);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public RespObject<?> add(@RequestBody Map<String,String> map){
    	
    	if(map.get("frequency")!=null&&!map.get("frequency").toString().isEmpty()&&!Utilis.isNumber(map.get("frequency"))) {
    		return RespUtils.build(310, false, "接口频次必须为数字");
    	}
    	if(map.get("cost")!=null&&!map.get("cost").toString().isEmpty()&&!Utilis.isNumber(map.get("cost"))) {
    		return RespUtils.build(310, false, "接口费用必须为数字");
    	}
        return RespUtils.buildOKWithDataYg(customerInterfaceService.add(map));
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public RespObject<?> update(@RequestBody Map<String,String> map){
    	if(map.get("frequency")!=null&&!map.get("frequency").toString().isEmpty()&&!Utilis.isNumber(map.get("frequency"))) {
    		return RespUtils.build(310, false, "接口频次必须为数字");
    	}
    	if(map.get("cost")!=null&&!map.get("cost").toString().isEmpty()&&!Utilis.isNumber(map.get("cost"))) {
    		return RespUtils.build(310, false, "接口费用必须为数字");
    	}
        return RespUtils.buildOKWithDataYg(customerInterfaceService.update(map));
    }

    /**
     * 校验客户接口是否存在
     * @param map id,customer_id,Faceclass
     * @return
     */
    @PostMapping(value = "/validationInterface")
    @ResponseBody
    public RespObject<?> therefindeCustomerInterface(@RequestBody Map<String,Object> map){
    	boolean therefindeCustomerInterface = customerInterfaceService.therefindeCustomerInterface(map);
    	if(therefindeCustomerInterface) {
    		return RespUtils.build(307, !therefindeCustomerInterface, "客户接口已存在");
    	}   	
    	return RespUtils.build(307, !therefindeCustomerInterface, "客户接口不存在");
    }
    /**
     * 根据客户编码 查询客户下所有接口信息  下拉列表
     * @param map
     * @return
     */
    @PostMapping(value = "/queryById")
    @ResponseBody
    public RespObject<?> queryById(@RequestBody Map<String, String> map) {
        String customerId="";
        if(map!=null&&map.get("customerId")!=null){
            customerId=map.get("customerId").toString();
            List<Map<String, Object>> customerInterfaceList = customerInterfaceService.CustomerInterfaceList(customerId);
            return RespUtils.buildOKWithDataYg(customerInterfaceList);
        }
        return RespUtils.buildOKWithDataYg(null);
        
    }
}
