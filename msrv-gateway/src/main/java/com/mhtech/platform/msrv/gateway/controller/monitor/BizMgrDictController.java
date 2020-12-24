package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhtech.platform.msrv.gateway.request.ReqPropertyPage;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IPropertyService;
import com.mhtech.platform.msrv.monitor.service.IServerAdminService;
import com.mhtech.platform.msrv.monitor.service.IServerNotifyLogService;
import com.mhtech.platform.msrv.pojo.BizMgrDictVO;
import com.mhtech.platform.msrv.pojo.DictVO;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.PropertyDTO;
import com.mhtech.platform.msrv.pojo.PropertyVO;
import com.mhtech.platform.msrv.pojo.PropertyVOId;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerInfo;
import com.mhtech.platform.msrv.pojo.ServerPageInfo;

@Api(tags = "字典常量", description = "字典常量")
@RestController
@RequestMapping("monitor/dict")
public class BizMgrDictController {

	@Value("${alert_dict_types}")
	private String[] alertDictTypes;
	
	@Autowired
	private IServerNotifyLogService serverNotifyLogService;
	
	@Autowired
	private IServerAdminService serverAdminService;
	
	@Autowired
	IPropertyService propertyService;
	
	@ApiOperation("字典常量")
	@ApiResponse(code = 200, message = "请求成功")
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> get() {
		Map<String, List<DictVO>> dicts = new HashMap<>();
		List<DictVO> listDictByTypes = serverNotifyLogService.listDictByTypes(Arrays.asList(alertDictTypes));
		listDictByTypes.forEach(dict -> {
			List<DictVO> list = dicts.get(dict.getDictType());
			if(CollectionUtils.isEmpty(list)) {
				list = new ArrayList<DictVO>();
				list.add(dict);
				dicts.put(dict.getDictType(), list);
			} else {
				list.add(dict);
			}
		});
		PropertyDTO mt = new PropertyDTO();
		mt.setPageNo(1);
		mt.setPageSize(100);
		Page<PropertyVOId> page = propertyService.queryPage(mt);
		if(!CollectionUtils.isEmpty(page.getData())) {
			dicts.put("servers", page.getData().stream().map(pvo -> {
				DictVO dvo = new DictVO();
				dvo.setDictName(pvo.getName());
				dvo.setDictValue(pvo.getIp());
				return dvo;
			}).collect(Collectors.toList()));
		}
		
		ServerPageInfo spi = new ServerPageInfo();
		spi.setPageSize(100);
		Page<ServerAdminInfo> listPage = serverAdminService.listPage(spi);
		if(!CollectionUtils.isEmpty(listPage.getData())) {
			dicts.put("bizServers", listPage.getData().stream().map(sai -> {
				DictVO dvo = new DictVO();
				dvo.setDictName(sai.getServerName());
				dvo.setDictValue(sai.getServerCode());
				return dvo;
			}).collect(Collectors.toList()));
		}
		
		/*BizMgrDictVO vo = new BizMgrDictVO();
		List<Map<Object, Object>> alertLevel = new ArrayList<Map<Object,Object>>();
		Map<Object, Object> level1 = new HashMap<Object, Object>();
		level1.put("key", "一级");
		level1.put("value", 1);
		Map<Object, Object> level2 = new HashMap<Object, Object>();
		level2.put("key", "二级");
		level2.put("value", 2);
		Map<Object, Object> level3 = new HashMap<Object, Object>();
		level3.put("key", "三级");
		level3.put("value", 3);
		alertLevel.add(level1);
		alertLevel.add(level2);
		alertLevel.add(level3);
		
		
		List<Map<Object, Object>> notified = new ArrayList<Map<Object,Object>>();
		Map<Object, Object> n1 = new HashMap<Object, Object>();
		n1.put("key", "已通知");
		n1.put("value", 1);
		Map<Object, Object> n2 = new HashMap<Object, Object>();
		n2.put("key", "未通知");
		n2.put("value", 0);
		notified.add(n1);
		notified.add(n2);
		
		List<Map<Object, Object>> processe = new ArrayList<Map<Object,Object>>();
		Map<Object, Object> p1 = new HashMap<Object, Object>();
		p1.put("key", "已处理");
		p1.put("value", 20);
		Map<Object, Object> p2 = new HashMap<Object, Object>();
		p2.put("key", "未处理");
		p2.put("value", 10);
		processe.add(p1);
		processe.add(p2);
		
		List<Map<Object, Object>> servers = new ArrayList<Map<Object,Object>>();
		Map<Object, Object> s1 = new HashMap<Object, Object>();
		s1.put("key", "FTP服务器");
		s1.put("value", "192.168.1.105");
		Map<Object, Object> s2 = new HashMap<Object, Object>();
		s2.put("key", "文件服务器");
		s2.put("value", "192.168.3.198");
		Map<Object, Object> s3 = new HashMap<Object, Object>();
		s3.put("key", "图片服务器");
		s3.put("value", "127.0.0.1");
		servers.add(s1);
		servers.add(s2);
		servers.add(s3);
		
		List<Map<Object, Object>> bizServers = new ArrayList<Map<Object,Object>>();
		Map<Object, Object> b1 = new HashMap<Object, Object>();
		b1.put("key", "流程业务");
		b1.put("value", "lcyw001");
		Map<Object, Object> b2 = new HashMap<Object, Object>();
		b2.put("key", "数据业务");
		b2.put("value", "sjyw001");
		Map<Object, Object> b3 = new HashMap<Object, Object>();
		b2.put("key", "主机10000");
		b2.put("value", "sjyw009");
		bizServers.add(b1);
		bizServers.add(b2);
		bizServers.add(b3);
		
		vo.setAlertLevel(alertLevel);
		vo.setNotified(notified);
		vo.setProcesse(processe);
		vo.setServers(servers);
		vo.setBizServers(bizServers);*/
		
		return RespUtils.buildOKWithData(dicts);
	}
}
