package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.auth.service.ISpUserService;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.ExcelUtil;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IPropertyService;
import com.mhtech.platform.msrv.monitor.service.IServerAdminService;
import com.mhtech.platform.msrv.monitor.service.ISpSpersonneService;
import com.mhtech.platform.msrv.monitor.service.ISysParameterService;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.PropertyVOId;
import com.mhtech.platform.msrv.pojo.ServerAdminInfo;
import com.mhtech.platform.msrv.pojo.ServerInfo;
import com.mhtech.platform.msrv.pojo.ServerPageInfo;
import com.mobile.utils.DateUtils;
import com.mobile.utils.StringUtils;

@Api(tags = "业务监控服务管理")
@RestController
@RequestMapping("monitor/serverInfo")
public class ServerAdminController {

	@Autowired
	private IServerAdminService serverAdminService;
	
	@Autowired
	ISysParameterService iSysParameterService;
	
	@Autowired
	ISpSpersonneService iSpSpersonneService;
	
	@Value("${local.download.path}")
	private String localDownloadPath;
	
	@Reference(version="0.0.1",group="monitor")
	private ISpUserService monitorISpUserService;
			
	@Autowired
	private IworkService iworkService;
	
	@Autowired
	private IPropertyService propertyService;
	
	private final Logger logger= LoggerFactory.getLogger(getClass());
	
	@ApiOperation("查询父服务")
	@ApiResponse(code = 200, message = "请求成功")
	@GetMapping(value = "/getParent")
	public RespObject<?> getParent(String parentName) {
		List<Map<String, Object>> list=serverAdminService.findServerInfoByServerName(parentName);
		return RespUtils.buildOKWithData(list);
	}
	
	@ApiOperation("查询父服务")
	@ApiResponse(code = 200, message = "请求成功")
	@GetMapping(value = "/getServerId")
	public RespObject<?> getServerId(String parentName) {
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> server=serverAdminService.findServerInfoByServerNameForId(parentName);
		List<Map<String, Object>> property=propertyService.getProperty(parentName);
		//服务器状态(硬件还是软件)
		List<Map<String, Object>> serverType = iSysParameterService.findParameterByParmType("serverType2");
		map.put("server", server);
		map.put("property", property);
		map.put("serverType", serverType);
		
		//把硬件和软件组装在一起
		JSONArray data=new JSONArray();
		if (server.size()>0) {
			JSONArray serverJs=(JSONArray) JSONArray.toJSON(server);
			data.addAll(serverJs);
		}
		if (property.size()>0) {
			JSONArray propertyJs=(JSONArray) JSONArray.toJSON(property);
			data.addAll(propertyJs);
		}
//		JSONObject jsServer=new JSONObject();
//		jsServer.put("value", "server");
//		jsServer.put("label", "软件");
//		JSONArray arrayServer=new JSONArray();
//		if (server.size()>0) {
//			for (int i = 0; i < server.size(); i++) {
//				JSONObject js=new JSONObject();
//				js.put("value", server.get(i).get("code"));
//				js.put("label", server.get(i).get("value"));
//				arrayServer.add(js);
//			}
//		}
//		jsServer.put("children", arrayServer);
//		data.add(jsServer);
//		JSONObject jsProperty=new JSONObject();
//		jsProperty.put("value", "property");
//		jsProperty.put("label", "硬件");
//		JSONArray arrayProperty=new JSONArray();
//		if (server.size()>0) {
//			for (int i = 0; i < server.size(); i++) {
//				JSONObject js=new JSONObject();
//				js.put("value", server.get(i).get("code"));
//				js.put("label", server.get(i).get("value"));
//				arrayProperty.add(js);
//			}
//		}
//		jsProperty.put("children", arrayProperty);
//		data.add(jsProperty);
		map.put("allserver", data);
		return RespUtils.buildOKWithData(map);
	}
	
	/**
	 * 新增监控服务
	 * @param info
	 * @return 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation("新增和修改监控服务")
	@ApiResponse(code = 200, message = "请求成功")
	@PostMapping(value = "/addAndModify",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> post(@RequestBody @Validated ServerInfo info) {
		RespCode respCode=RespCode.SUCCESS;
		int flag=0;
		if (StringUtils.isEmpty(info.getParentServer())) {
			info.setParentServer("0");
		}
		//根据上级服务编码是否为空，判断有没有上级业务
		if(StringUtils.isEmpty(info.getIp()) && info.getParentServer().equals("0")) {
			throw new RuntimeException("IP跟父服务器KEY不能同时为空");
		} 
		String username=info.getUsername();
		//获取用户
		List<Map<String, Object>> user =monitorISpUserService.selectUserInfo(username);
		if (user.size()==1) {
			info.setMobile(user.get(0).get("phone").toString());
			info.setEmail(user.get(0).get("email").toString());
			List<String> pros=(List<String>) info.getProjects();
			//JSONArray pros=JSONArray.parseArray(info.getProjects().toString());
			String applyProjects="";
			if (pros.size()>0) {
				for (int i = 0; i < pros.size(); i++) {
					String parmCode=pros.get(i);
					//List<Map<String, Object>> list=iSysParameterService.findParameterByParmCodeOrParmName("projectName",parmCode,null);
					//System.err.println(list.get(0).get("value"));
					applyProjects+=parmCode+",";
				}
				applyProjects=applyProjects.substring(0,applyProjects.length()-1);
			}
			info.setApplyProjects(applyProjects);
			//根据是否有id判断是新增还是修改
			if (info.getId()==null||StringUtils.isEmpty(info.getId().toString())) {//为空则新增 
				respCode=RespCode.ADD_SUCCESS;
				info.setId(iworkService.getNextId());
				//随机生成编码：种类+时间+一位随机小数
				info.setServerCode("RJ"+DateUtils.getDate("yyyyMMddHHmmss")+(int)(Math.random()*11));
				info.setCreatedTime(DateUtils.getDate2());
				flag=serverAdminService.addEntity(info);
			}else {
				respCode=RespCode.UPDATE_SUCCESS;
				ServerAdminInfo infoOld=serverAdminService.getServerInfo(info.getId());
				info.setServerCode(infoOld.getServerCode());
				flag=serverAdminService.updateServerInfo(info);
			}
		}else {
			throw new RuntimeException("该管理人员不存在，请刷新页面！");
		}
		
		if (flag <= 0) {
			respCode=RespCode.DATABASE_ERROR;
			return RespUtils.build(respCode.getCode(),respCode.getMessage(),null);
		}
		JSONObject js=new JSONObject();
		js.put("id", info.getId()+"");
		js.put("code", info.getServerCode());
        return RespUtils.build(respCode.getCode(),respCode.getMessage(),js);
	}
	

	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/delById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "根据id删除")
    public RespObject<?> del(@RequestParam("id") @NotNull Long id ) {
		ServerAdminInfo oldMap = serverAdminService.getServerInfo(id);
		if(oldMap==null||oldMap.getId().equals("")) {
			throw new RuntimeException("这条信息不存在，请刷新页面！");
		}
		serverAdminService.delById(id.toString());
        //return RespUtils.buildOKWithData("");
		return RespUtils.build(RespCode.DEL_SUCCESS);
	}
	
	/**
	 * 详细查询
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "服务详情查询", hidden = true)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<ServerAdminInfo> get(@PathVariable("id") Long id) {
		ServerAdminInfo sai = serverAdminService.getServerInfo(id);
		return RespUtils.buildOKWithData(sai);
	}
	
	/**
	 * 分页查询
	 * @param spi
	 * @return
	 */
	@ApiOperation(hidden = true, value = "服务器列表查询")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> get(@Validated ServerPageInfo spi) {
		Page<ServerAdminInfo> listPage = serverAdminService.listPage(spi);
		return RespUtils.buildOKWithData(listPage);
	}
	
	@SuppressWarnings("deprecation")
//	@GetMapping(value = "/export/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@PostMapping(value = "/export")
	public void export(@RequestBody @NotNull ServerPageInfo spi, HttpServletResponse response,HttpServletRequest request) {
		try {
			int rows = 1;
			spi.setPageNo(1);
			spi.setPageSize(rows);
			Page<ServerAdminInfo> listPage = serverAdminService.listPage(spi);
			if(listPage.getTotalCount() == 0 || listPage.getTotalCount() > 60 * 1000) {
				response.setHeader("Content-Type", "application/json;charset=UTF-8");
				throw new RuntimeException("文件导出失败");
			} else {
				String fileName = getFilename();
				List<ServerAdminInfo> list = listPage.getData();
				while(true) {
					Page<ServerAdminInfo> data = serverAdminService.listPage(spi);
					list.addAll(data.getData());
					if(data.getTotalPages() == data.getPageNo()) {
						break;
					}
					spi.setPageNo(spi.getPageNo() + 1);
				}
				String fileUrl=request.getContextPath();//获取项目绝对路径根目录
				ExcelUtil.createExcel(list, fileUrl+localDownloadPath, fileName, createExcelHeader());
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName) + ".xls");
				response.setHeader("Content-Type", "application/vnd.ms-excel;charset=utf-8");
				response.getOutputStream().write(FileUtils.readFileToByteArray(new File(localDownloadPath + fileName + ".xls")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, Object> createExcelHeader() {
		Map<String, Object> header = new HashMap<>();
		header.put("id", "服务ID");
		header.put("serverCode", "服务编码");
		header.put("ip", "服务IP");
		header.put("username", "管理员");
		header.put("email", "联系邮箱");
		header.put("mobile", "联系电话");
		header.put("parentServer", "上级服务");
		header.put("serverType", "服务类型");
		header.put("applyProjects", "运行项目");
		header.put("status", "服务状态");
		header.put("endTime", "结束时间");
		header.put("createdTime", "服务创建时间");
		header.put("monitorStatus", "是否监控");
		return header;
	}
	
	private String getFilename() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return "业务管理_" + df.format(new Date());
	}
	
	@ApiOperation("字典常量")
	@ApiResponse(code = 200, message = "请求成功")
	@GetMapping(value = "/dict", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespObject<?> get() {
		Map<String, List<Map<String, Object>>> dict = new HashMap<>();
		//是否监控
		List<Map<String, Object>> monitorStatus = iSysParameterService.findParameterByParmType("logic");
		dict.put("monitorStatus", monitorStatus);
		//软件服务器状态
		List<Map<String, Object>> operator = iSysParameterService.findParameterByParmType("serviceStatus1");
		dict.put("operator", operator);
		//资产新增-项目选择
		List<Map<String, Object>> project = iSysParameterService.findParameterByParmType("projectName");
		dict.put("project", project);
		//获取用户列表
		List<Map<String, Object>> user =monitorISpUserService.selectAllUser("");
		dict.put("user", user);
		return RespUtils.buildOKWithData(dict);
	}
}
