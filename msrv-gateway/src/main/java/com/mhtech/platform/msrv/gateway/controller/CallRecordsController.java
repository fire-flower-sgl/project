package com.mhtech.platform.msrv.gateway.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.Utilis;
import com.mobile.model.CallRecords;
import com.mobile.model.Page;
import com.mobile.service.CallRecordsService;
import com.mobile.service.ParameterService;
import com.mobile.utils.DateUtils;
import com.mobile.utils.ExcelUtil;

/**
 * @ClassName CallRecordsController
 * @Description TODO 手机接口调用记录
 * @Author admini
 * @Date 2019/7/23 11:40
 * @Version 1.0
 */
@RestController
@CrossOrigin
@SuppressWarnings("all")
@RequestMapping(value="/MobileApi/CallRecords")
public class CallRecordsController {
//    @Reference(version = "1.0.0")
	@Autowired
    private CallRecordsService callRecordsService;
//    @Reference(version = "1.0.0")
	@Autowired
    ParameterService parameter;
//    @Autowired
//    ExcelUtil excelUtil;
    @GetMapping(value="/add")
    @ResponseBody
    public RespObject<?> add(@RequestParam("customer_id") String customer_id,@RequestParam("mobileno") String mobileno,@RequestParam("operators") String operators,
                      @RequestParam("fasename") String fasename,@RequestParam("faceclass") String faceclass,@RequestParam("facetype") String facetype,
                      @RequestParam("result") String result, @RequestParam("resmsg") String resmsg, @RequestParam("resid") String resid) {
        CallRecords callRecords=new CallRecords(Utilis.getUUID(),customer_id,mobileno,operators,fasename,faceclass,facetype,DateUtils.getDateTime(),result,resmsg,resid);
        return RespUtils.buildOKWithDataYg(callRecordsService.add(callRecords));
    }
    @GetMapping(value="/query")
    @ResponseBody
    public RespObject<?> findCallRecords(@RequestParam("id") String id){
//    	CallRecords callRecords = callRecordsImpl.findCallRecords(id);

        Map<String, Object> callRecord = callRecordsService.findCallRecord(id);
        return RespUtils.buildOKWithDataYg(callRecord);
    }
    @GetMapping(value="/queryAll")
    @ResponseBody
    public RespObject<?> findCallRecordsList(@RequestParam(value = "customer_id",required = false) String customer_id,@RequestParam(value = "mobileno",required = false) String mobileno,
                                      @RequestParam(value = "operators",required = false) String operators,@RequestParam(value = "ctime",required = false) String ctime,
                                      @RequestParam(value = "customer_name",required = false) String customer_name,
                                      @RequestParam(value = "fasename",required = false) String fasename){
        Map<String,String> map=new HashMap<>();
        map.put("customer_id",customer_id);
        map.put("mobileno",mobileno);
        map.put("operators",operators);
        map.put("ctime",ctime);
        map.put("customer_name",customer_name);
        map.put("fasename",fasename);
        List<Map<String, Object>> callRecordsList = callRecordsService.findCallRecordsList(map);
        List<Map<String, String>> list = Utilis.mapObjToString(callRecordsList);
        return RespUtils.buildOKWithDataYg(list);
    }
    /**
     * @desc 调用日志 分页查询
     * @param map
     * @return
     */
    @PostMapping(value="/queryPage")
    @ResponseBody
    public RespObject<?> findCallRecordsPage(@RequestBody Map<String, String> map){
    
        Page callRecordsListPage = callRecordsService.findCallRecordsListPage(map);
//        List operator = parameter.findParameterList("operator", "0");
//        callRecordsListPage.setData2(operator);
        Object o = JSON.toJSON(callRecordsListPage);
        return RespUtils.buildOKWithDataYg(o);
    }
    @PostMapping(value = "/export")
    public void excel(HttpServletResponse response,@RequestBody Map<String, String> map) throws Exception {
        String title[]={"客户名称","手机号","接口名称","运营商","调用时间","经度","纬度","状态","结果"};//
        List<Map<String, Object>> callRecordsList = callRecordsService.findCallRecordsList(map);
        List<List<String>> list=new ArrayList<>();
        try {
			for (Map map1:callRecordsList){
			    List data=new ArrayList();
			    if(map1.get("result")==null)
			    	continue;
			    String result=map1.get("result").toString();
			    JSONObject object = (JSONObject) JSON.parse(result);
			    
			    String customerName="";
			    String mobileno="";
			    String fasename="";
			    String operatorsname="";
			    String ctime="";
			    if(map1.get("customerName")!=null)
			    	customerName=map1.get("customerName").toString();
			    if(map1.get("mobileno")!=null)
			    	mobileno=map1.get("mobileno").toString();
			    if(map1.get("fasename")!=null)
			    	fasename=map1.get("fasename").toString();
			    if(map1.get("parmName")!=null)
			    	operatorsname=map1.get("parmName").toString();
			    if(map1.get("ctime")!=null)
			    	ctime=map1.get("ctime").toString();
//			    System.err.println(customerName);
			    data.add(customerName);
			    data.add(mobileno);
			    data.add(fasename);
			    data.add(operatorsname);
			    data.add(ctime);
			    if(object!=null) {
			    	data.add(object.get("resmsg"));
			    	data.add(object.get("lng"));
			    	data.add(object.get("lat"));
			    }
			    data.add(result);
			    list.add(data);
			}
		} catch (Exception e) {
			System.err.println(1111);
			e.printStackTrace();
		}
        String fileName=DateUtils.getDate()+".xls";
        String sheetName="手机接口调用记录";
        ExcelUtil.exportExcel(response,sheetName,fileName,title,list);
    }

}
