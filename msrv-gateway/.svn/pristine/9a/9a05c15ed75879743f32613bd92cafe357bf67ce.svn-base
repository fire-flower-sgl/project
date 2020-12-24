package com.mhtech.platform.msrv.gateway.controller;


import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.gateway.utils.Utilis;
import com.mobile.model.FaceInformation;
import com.mobile.service.FaceInformationService;
import com.mobile.service.ParameterService;
import com.mobile.utils.DateUtils;

/**
 * @ClassName FaceInformationController
 * @Description TODO 接口信息
 * @Author admini
 * @Date 2019/7/25 15:45
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/MobileApi/FaceInformation")
@SuppressWarnings("all")

public class FaceInformationController {
//	@Reference(version = "1.0.0")
	@Autowired
    FaceInformationService faceInformationService;
//    @Reference(version = "1.0.0")
	@Autowired
    ParameterService parameter;

    @PostMapping(value = "/add")
    @ResponseBody
    public RespObject<?> add(@RequestBody Map<String,String> map) {

        FaceInformation faceInformation = new FaceInformation(Utilis.getUUID(), map.get("fasename"), map.get("faceclass"), map.get("facetype"), "0",Double.parseDouble(map.get("cost")) , DateUtils.getDateTime());
//        System.out.println("add");
        return RespUtils.buildOKWithDataYg(faceInformationService.add(faceInformation));
    }

    @GetMapping(value = "/del")
    @ResponseBody
    public RespObject<?> del(@RequestParam("id") String id) {
        return RespUtils.buildOKWithDataYg(faceInformationService.delete(id));
    }

    @PostMapping(value = "/modify")
    @ResponseBody
    public RespObject<?> update(@RequestBody Map<String,String> map) {
        FaceInformation faceInformation = new FaceInformation(map.get("id"), map.get("fasename"), map.get("faceclass"), map.get("facetype"), map.get("fasestatus"), Double.parseDouble(map.get("cost")), null);
//        System.out.println("update");
        return RespUtils.buildOKWithDataYg(faceInformationService.update(faceInformation));
    }

    @GetMapping(value = "/query")
    @ResponseBody
    public RespObject<?> query(@RequestParam("id") String id) {
//        FaceInformation faceInformation = faceInformationService.findFaceInformation(id);
        Map<String, Object> faceInformationMap = faceInformationService.findFaceInformationMap(id);
        return RespUtils.buildOKWithDataYg(faceInformationMap);
    }
    @PostMapping(value = "/queryAll")
    @ResponseBody
    public RespObject<?> queryAll(@RequestBody Map<String,String> map){
       /* Map<String,String> map=new HashMap<>();
        map.put("fasename",fasename);
        map.put("facetype",facetype);
        map.put("fasestatus",fasestatus);
        map.put("ctime",ctime);*/
        Map m=new Hashtable();
        List<Map<String, Object>> faceInformationList = faceInformationService.findFaceInformationList(map);
        List<Map<String, String>> list = Utilis.mapObjToString(faceInformationList);

        List facetype = parameter.findParameterList("facetype", "0");
        List fasestatus = parameter.findParameterList("status", "0");
        m.put("data",list);
        m.put("facetype",facetype);
        m.put("fasestatus",fasestatus);
        return RespUtils.buildOKWithDataYg(m);
    }
}
