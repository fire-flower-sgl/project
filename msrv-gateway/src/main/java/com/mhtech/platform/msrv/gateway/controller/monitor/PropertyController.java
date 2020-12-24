package com.mhtech.platform.msrv.gateway.controller.monitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.core.appender.rolling.action.IfAccumulatedFileCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.auth.service.ISpUserService;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.gateway.request.ReqPropertyAdd;
import com.mhtech.platform.msrv.gateway.request.ReqPropertyPage;
import com.mhtech.platform.msrv.gateway.request.ReqPropertyUpdate;
import com.mhtech.platform.msrv.gateway.request.reqProObj;
import com.mhtech.platform.msrv.gateway.request.reqPropertyHardware;
import com.mhtech.platform.msrv.gateway.request.reqPropertyHardwareAdd;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.GeneralConvertor;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mhtech.platform.msrv.monitor.service.IPropertyService;
import com.mhtech.platform.msrv.monitor.service.ISysParameterService;
import com.mhtech.platform.msrv.monitor.service.SpDeviceAdminService;
import com.mhtech.platform.msrv.pojo.Page;
import com.mhtech.platform.msrv.pojo.PropertyDTO;
import com.mhtech.platform.msrv.pojo.PropertyHardwareDTO;
import com.mhtech.platform.msrv.pojo.PropertyVO;
import com.mhtech.platform.msrv.pojo.PropertyVOId;
import com.mhtech.platform.msrv.pojo.SpDeviceAdmin;
import com.mobile.utils.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @Description 资产-服务器模块控制层
 * @Author admin
 * @Date 2020/3/19 10:10
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/monitor/property")
@Api(value = "PropertyController", tags = "资产-服务器模块")
public class PropertyController {
	
	@Autowired
	IPropertyService propertyService;
	
	@Autowired
	SpDeviceAdminService spDeviceAdminService;
	
	@Autowired
	IworkService iworkService;

	@Autowired
	ISysParameterService iSysParameterService;
	
	@Autowired
    private GeneralConvertor dozer ;
	
	@Reference(version="0.0.1",group="monitor")
	private ISpUserService monitorISpUserService;
	
	/**
	 * 分页模糊查询-资产 
	 * @param reqObjPage
	 * @return
	 */
	@PostMapping(value = "/queryPage")
	@ApiOperation(value = "分页查询")
    public RespObject<Page<PropertyVOId>> queryPage(@RequestBody @Valid ReqPropertyPage reqObjPage) {
		//PropertyDTO mt = reqObjToDTO(reqObjPage);
		PropertyDTO mt = dozer.convertor(reqObjPage, PropertyDTO.class);
		Page<PropertyVOId> page = propertyService.queryPage(mt);
		//JSONObject jsonObject=JSONObject.parseObject(page.toString());
		//Page<PropertyVO> bean = (Page<PropertyVO>) JSONObject.toBean(jsonObject, Page<PropertyVO>.class);
		StringUtils.isEmpty("");
        return RespUtils.buildOKWithData(page);
	}
	
	
	
	@PostMapping(value = "/queryPageParam")
	@ApiOperation(value = "分页查询参数接口",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RespObject<?> queryPageParam() {
		//资产所属分类
		List<Map<String, Object>> type = iSysParameterService.findParameterByParmType("serviceType");
		//资产当前状态
		List<Map<String, Object>> state = iSysParameterService.findParameterByParmType("serviceStatus");
		//资产是否监控
		List<Map<String, Object>> monitor = iSysParameterService.findParameterByParmType("logic");
		//资产新增-项目选择
		List<Map<String, Object>> project = iSysParameterService.findParameterByParmType("projectName");
		//获取用户列表
		List<Map<String, Object>> user =monitorISpUserService.selectAllUser("");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("state", state);
		map.put("monitor", monitor);
		map.put("project", project);
		map.put("user", user);
        return RespUtils.buildOKWithData(map);
	}

	@GetMapping(value = "/getUser")
	@ApiOperation(value = "根据用户名模糊查询用户")
    public RespObject<?> getUser(String userName) {
		//获取用户列表
		List<Map<String, Object>> user =monitorISpUserService.selectAllUser(userName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
        return RespUtils.buildOKWithData(map);
	}
	

	/**
	 * 校验ip是否存在
	 * @param reqObj
	 * @return
	 */
	@PostMapping(value = "/ipIsExist")
	@ApiOperation(value = "更新")
    public RespObject<?> ipIsExist(@RequestBody PropertyDTO mt) {
		RespCode respCode=RespCode.SUCCESS;
		//PropertyDTO mt=new PropertyDTO();
		//mt.setIp(ip);
		boolean flg=propertyService.ipIsExist(mt);//ip是否存在
		return RespUtils.build(respCode.getCode(),respCode.getMessage(),flg);
	}
	
	/**
	 * 修改和更新资产-服务器
	 * @param reqObj
	 * @return
	 */
	@PostMapping(value = "/addAndModify")
	@ApiOperation(value = "更新")
    public RespObject<?> modify(@RequestBody @Valid ReqPropertyUpdate reqObj) {
		RespCode respCode=RespCode.SUCCESS;
		//PropertyDTO mt = reqObjToDTO(reqObj);
		PropertyDTO mt = dozer.convertor(reqObj, PropertyDTO.class);
		Long id=reqObj.getId();
		int flg =0;
		if (id==null||StringUtils.isBlank(id+"")) {//id为空为新增
			respCode=RespCode.ADD_SUCCESS;
			//新增ip不能重复
			if(!propertyService.ipIsExist(mt)) {
				mt.setId(iworkService.getNextId());
				//随机生成编码：种类+时间+一位随机小数
				mt.setCode("YJ"+DateUtils.getDate("yyyyMMddHHmmss")+(int)(Math.random()*11));
				//维护时间插入当前时间，每次状态变更则改变维护时间，系统更改
				mt.setFixTime(DateUtils.parseDate(DateUtils.getDate2()));
				//插入硬件基本信息表
				flg = propertyService.save(Arrays.asList(mt));
			}else {
				respCode=RespCode.IP_ISEXIST;
				return RespUtils.build(respCode.getCode(),respCode.getMessage(),null);
			}
		}else {//id有值为修改
			respCode=RespCode.UPDATE_SUCCESS;
			PropertyVOId oldMap=propertyService.findEntityByKey(reqObj.getId());
			if(oldMap==null||oldMap.getId().equals("")) {
				throw new RuntimeException("这条信息不存在，请刷新页面！");
			}
			String state=mt.getState().toString();
			String stateOld=oldMap.getState();
			Date fixTime=oldMap.getFixTime();
			//维护时间插入当前时间，每次状态变更则改变维护时间，系统更改
			if (!state.equals(stateOld)) {
				fixTime=DateUtils.parseDate(DateUtils.getDate2());
			}
			mt.setFixTime(fixTime);
			mt.setCode(oldMap.getCode());
			flg = propertyService.modifyEntityByKey(mt);
		}
		
		//根据人员判断是否进入硬件监控管理表
		String userName=reqObj.getUserName();
		if (!StringUtils.isBlank(userName)) {
			//获取用户
			List<Map<String, Object>> user =monitorISpUserService.selectUserInfo(userName);
			String isMonitor=reqObj.getIsMonitor();
			if (!StringUtils.isBlank(isMonitor)&&user.size()==1) {
				SpDeviceAdmin spDeviceAdmin=new SpDeviceAdmin();
				spDeviceAdmin.setUsername(userName);
				spDeviceAdmin.setMobile(user.get(0).get("phone").toString());
				spDeviceAdmin.setEmail(user.get(0).get("email").toString());
				spDeviceAdmin.setStatus(isMonitor);
				spDeviceAdmin.setCreatedTime(DateUtils.getDate2());
				spDeviceAdmin.setIp(mt.getIp());
				spDeviceAdmin.setServerCode(mt.getCode());
				spDeviceAdmin.setServerName(mt.getName());
				spDeviceAdmin.setServerType(mt.getType());
				spDeviceAdmin.setId(mt.getId()+"");
				//根据id判断保存或者修改信息
				if (id==null||StringUtils.isBlank(id+"")) {
					flg=spDeviceAdminService.save(Arrays.asList(spDeviceAdmin));
				}else {
					flg=spDeviceAdminService.modifyEntityByKey(spDeviceAdmin);
				}
			}
		}
		if (flg <= 0) {
			respCode=RespCode.DATABASE_ERROR;
			return RespUtils.build(respCode.getCode(),respCode.getMessage(),null);
		}
		JSONObject js=new JSONObject();
		js.put("id", mt.getId()+"");
		js.put("code", mt.getCode());
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
		PropertyVOId oldMap = propertyService.findEntityByKey(id);
		if(oldMap==null||oldMap.getId().equals("")) {
			throw new RuntimeException("这条信息不存在，请刷新页面！");
		}
		int flag = propertyService.del(id);
        return RespUtils.build(RespCode.DEL_SUCCESS,flag);
	}

	/**
	 * 根据id查看详情
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id查询")
	@GetMapping(value = "/findById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RespObject<PropertyVO> findById(@RequestParam("id") @NotNull Long id ) {
		PropertyVO propertyVO = propertyService.findById(id);
        return RespUtils.buildOKWithData(propertyVO);
	}
	
	/**
	 * 给服务器添加硬件信息
	 * @param reqObj
	 * @return
	 */
	@PostMapping(value = "/addPreHardware")
	@ApiOperation(value = "服务器-增加硬件" )
    public RespObject<?> addPreHardware(@RequestBody @Valid reqPropertyHardwareAdd reqObj) {
		String propertyCode = reqObj.getPropertyCode();
		PropertyDTO mt = new PropertyDTO();
		mt.setCode(propertyCode);
		List<reqPropertyHardware> hardwareList = reqObj.getHardwareList();
		
		List<PropertyHardwareDTO> list = new ArrayList<PropertyHardwareDTO>();
		List<String> nameList =new ArrayList<String>();
		if(!CollectionUtils.isEmpty(hardwareList)) {
			for (reqPropertyHardware rph : hardwareList) {
				boolean result = nameList.contains(rph.getHardwareName());
				if(result) throw new RuntimeException("硬件名称存在重复");
				nameList.add(rph.getHardwareName());
				PropertyHardwareDTO ph = new PropertyHardwareDTO(iworkService.getNextId(), propertyCode, rph.getHardwareName(), rph.getHardwareType(),
						rph.getAlias(), rph.getSize(), rph.getDescription(), rph.getState(), null);
				list.add(ph);
			}
			mt.setHardWareList(list);
		}
		
//		List<PropertyHardwareDTO> aa = new ArrayList<PropertyHardwareDTO>();
//		PropertyHardwareDTO aDto = new PropertyHardwareDTO(10l, "001", "内存5", "ram", "内存", 6, "nechun", 1, null);
//		PropertyHardwareDTO bDto = new PropertyHardwareDTO(11l, "001", "内存6", "ram", "内存", 6, "nechun", 1, null);
//		aa.add(aDto);
//		aa.add(bDto);
//		
//		mt.setHardWareList(aa);
		
		propertyService.addPreHardware(mt);
        return RespUtils.OK;
	}
	
	/**
	 * 删除服务器下的指定硬件
	 * @param reqObj
	 * @return
	 */
	@PostMapping(value = "/delPreHardware")
	@ApiOperation(value = "服务器-删除硬件")
    public RespObject<?> delPreHardware(@RequestBody @Valid reqProObj reqObj) {
		PropertyDTO mt = new PropertyDTO();
		mt.setCode(reqObj.getCode());
		mt.setHardWareNameList(reqObj.getHardWareNameList());
		
//		mt.setCode("001");
//		List<String> hardWareIdList = new ArrayList<String>();
//		hardWareIdList.add("内存01");
//		hardWareIdList.add("内存02");
//		hardWareIdList.add("内存03");
//		mt.setHardWareNameList(hardWareIdList);
		
		int b =propertyService.delPreHardware(mt);
        return RespUtils.OK;
	}
	
	/**
	 * @return propertyService
	 */
	@ApiOperation("查询硬件服务下拉列表")
	@ApiResponse(code = 200, message = "请求成功")
	@PostMapping(value = "/getProperty")
	public RespObject<?> getProperty(@RequestBody String parms) {
		JSONObject js=JSONObject.parseObject(parms);
		String serverName="";
		if (js.containsKey("serverName")&&js.get("serverName")!=null) {
			serverName=js.getString("serverName");
		}
		List<Map<String, Object>> list=propertyService.getProperty(serverName);
		return RespUtils.buildOKWithData(list);
	}
	
	/**
	 * @return propertyService
	 */
	@ApiOperation("获取硬件名称和ip")
	@ApiResponse(code = 200, message = "请求成功")
	@PostMapping(value = "/getPropertyNameAndIp")
	@ResponseBody
	public RespObject<?> getPropertyNameAndIp(@RequestBody String parms) {
		JSONObject js=JSONObject.parseObject(parms);
		String serverName="";
		if (js.containsKey("serverName")&&js.get("serverName")!=null) {
			serverName=js.getString("serverName");
		}
		List<Map<String, Object>> list=propertyService.getPropertyNameAndIp(serverName);
		return RespUtils.buildOKWithData(list);
	}
	
}
