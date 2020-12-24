package com.mhtech.platform.msrv.gateway.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.Utilis;
import com.mobile.model.CallRecords;
import com.mobile.model.CustomerMobiles;
import com.mobile.model.Page;
import com.mobile.model.SysParameter;
import com.mobile.service.CallRecordsService;
import com.mobile.service.CustomerMobilesService;
import com.mobile.service.CustomerPackageService;
import com.mobile.service.CustomerService;
import com.mobile.service.ElementvaliDateService;
import com.mobile.service.MobileService;
import com.mobile.service.ParameterService;
import com.mobile.utils.DateUtils;
import com.mobile.utils.IpUtil;
import com.mobile.utils.MobileLocationTool;


/**
 * @ClassName MobileLocationController
 * @Description TODO 参数字典
 * @Author admini
 * @Date 2019/7/24 17:32
 * @Version 1.0
 */
@RestController
@CrossOrigin
@SuppressWarnings("all")
@RequestMapping(value = "/MobileApi/Parameter")
public class ParameterController {
	@Autowired
    ParameterService parameter;
	@PostMapping(value = "/queryParmeter")
	public RespObject<?> queryParmeter(@RequestBody Map<String,Object> map) {
		String status=null;
		String types=null;
		if(map.get("status")!=null)
			status=map.get("status").toString();
		if(map.get("types")!=null)
			types=map.get("types").toString();
		List findParameterList = parameter.findParameterList(types, status);
		return RespUtils.buildOKWithDataYg(findParameterList);
	}
	

	@PostMapping(value = "/queryParam")
    public RespObject<?> queryParameter(@RequestBody JSONObject reqObject) {
		//拿数据
		Map<String,Object> map =new HashMap<String, Object>();
		if(reqObject!=null)
			map=JSONObject.parseObject(reqObject.toJSONString());
		List<Map<String,Object>> findParameter = parameter.findParameter(map);
        return RespUtils.buildOKWithDataYg(findParameter);
    }
	
	
    
	@PostMapping(value = "/parmList")
    public RespObject<?> userType(@RequestBody JSONObject object) {
		List<Map<String, Object>> dataList = parameter.queryParmList(object.getString("parmType"));
	    return RespUtils.buildOKWithDataYg(dataList);
	}
	
	
	@PostMapping(value = "/queryPage")
    public RespObject<?> queryEventPage(@RequestBody Map<String,String> map ) {		
        Page eventPage = parameter.findAllPage(map);
        return RespUtils.buildOKWithDataYg(eventPage);
    }
	
	@PostMapping(value="/add")
    public RespObject<?> add(@RequestBody SysParameter sysParameter){	
		int sum=  parameter.add(sysParameter);
        return RespUtils.buildOKWithDataYg(sum);	
    }
	
	@PostMapping(value="/del")
    public RespObject<?> del(@RequestBody SysParameter sysParameter){
		int sum= parameter.delete(sysParameter.getId());
        return RespUtils.buildOKWithDataYg(sum);
    }
	
	@PostMapping(value="/update")
	public RespObject<?> update (@RequestBody SysParameter sysParameter) {
		int sum=parameter.update(sysParameter);
        return RespUtils.buildOKWithDataYg(sum);
	}
	
	@PostMapping(value="/isExist")
//    @ApiOperation(value = "参数类型+参数编码是否唯一",notes="ture 存在 false 不存在")
    public RespObject<?> queryTypeCode(@RequestBody SysParameter sysParameter){
		boolean sum= parameter.findTypeCode(sysParameter.getParmType(),sysParameter.getParmCode());
        return RespUtils.buildOKWithDataYg(sum);
    }
}
