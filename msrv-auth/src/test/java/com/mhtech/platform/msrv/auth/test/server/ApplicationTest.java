package com.mhtech.platform.msrv.auth.test.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhtech.platform.msrv.auth.MsrvAuthApplication;
import com.mhtech.platform.msrv.auth.dao.mapper.SysPowerMapper;
import com.mhtech.platform.msrv.auth.dao.model.SysPower;
import com.mhtech.platform.msrv.auth.service.IworkService;
import com.mhtech.platform.msrv.auth.utils.RedisUtils;
import com.mhtech.platform.msrv.pojo.MsrvMessage;
import com.mysql.fabric.xmlrpc.base.Data;

@SpringBootTest(classes = { MsrvAuthApplication.class })
@RunWith(SpringRunner.class)
public class ApplicationTest {

//	@Autowired
//	private SysPowerMapper sysPowerMapper;
//	@Autowired
//	private IworkService  iworkService;
//	
//	@Test
//	public void pageQuery() {
//		Page<SysPower> page = PageHelper.startPage(1, 10).setOrderBy(" id desc");
//		List<SysPower> list = sysPowerMapper.listSysPower(null);
//		
//		PageInfo<SysPower> pageinfo = new PageInfo<>(list);
//        System.err.println("-------------"+pageinfo);
//		
//		list.forEach(sp -> {
//			System.out.println(sp.getPowerNum());
//		});
//		System.out.println("page.getTotal() : " + page.getTotal());
//	}
//	@Test
//	public void id() {
//		
//		for (int i = 0; i < 10; i++) {
//			System.out.println("------------" + iworkService.getNextId()); 
//		}
//		
//		
//	}
	
	@Autowired
	RedisUtils redisUtils;
	
	
	@Test
	public void idyg() {
		
		
		MsrvMessage msrvMessage = (MsrvMessage) redisUtils.hget("MsrvLog","30641567572299776");
		
		System.err.println("----------"+msrvMessage.getStatus());
//		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		System.err.println("-----------当前系统时间"+df.format(new Date()));// new Date()为获取当前系统时间
//		//游鸽数据库字段长度限制
//		redisUtils.hset("UpdataLogTime", "logTime", df.format(new Date()));
	
//		redisUtils.hset("ygField", "actionModule", "64");
//		redisUtils.hset("ygField", "actionNum", "64");
//		redisUtils.hset("ygField", "actionType", "64");
//		redisUtils.hset("ygField", "address", "64");
//		redisUtils.hset("ygField", "addrDetail", "256");
//		
//		redisUtils.hset("ygField", "age", "64");
//		redisUtils.hset("ygField", "apiNum", "32");
//		redisUtils.hset("ygField", "ApiNumTotle", "128");
//		redisUtils.hset("ygField", "area", "64");
//		redisUtils.hset("ygField", "areaId", "64");
//		
//		redisUtils.hset("ygField", "areaname", "64");
//		redisUtils.hset("ygField", "areaCode", "64");
//		redisUtils.hset("ygField", "areaId", "64");
//		redisUtils.hset("ygField", "areaName", "64");
//		redisUtils.hset("ygField", "areaType", "64");
//
//		redisUtils.hset("ygField", "attendance", "64");
//		redisUtils.hset("ygField", "attendanceCoefficient", "64");
//		redisUtils.hset("ygField", "attendanceRate", "64");
//		redisUtils.hset("ygField", "baiduLat", "64");
//		redisUtils.hset("ygField", "baiduLng", "64");
//
//		redisUtils.hset("ygField", "batchid", "64");
//		redisUtils.hset("ygField", "beanName", "100");
//		redisUtils.hset("ygField", "businessId", "64");
//		redisUtils.hset("ygField", "businessName", "225");
//		redisUtils.hset("ygField", "callcode", "64");
//		
//		redisUtils.hset("ygField", "cellName", "64");
//		redisUtils.hset("ygField", "chartid", "64");
//		redisUtils.hset("ygField", "chartName", "64");
//		redisUtils.hset("ygField", "chartId", "64");
//		redisUtils.hset("ygField", "checkCore", "64");
//		
//		redisUtils.hset("ygField", "checkDate", "64");
//		redisUtils.hset("ygField", "checkNum", "32");
//		redisUtils.hset("ygField", "checkPeopleNum", "32");
//		redisUtils.hset("ygField", "checkPeriod", "32");
//		redisUtils.hset("ygField", "checkTime", "64");
//		
//		redisUtils.hset("ygField", "checkType", "64");
//		redisUtils.hset("ygField", "city", "64");
//		redisUtils.hset("ygField", "className", "64");
//		redisUtils.hset("ygField", "classNo", "64");
//		redisUtils.hset("ygField", "cls", "64");
//
//		redisUtils.hset("ygField", "college", "64");
//		redisUtils.hset("ygField", "company", "64");
//		redisUtils.hset("ygField", "companyCode", "64");
//		redisUtils.hset("ygField", "companyName", "128");
//		redisUtils.hset("ygField", "coordinateX", "64");
//
//		redisUtils.hset("ygField", "coordinateY", "64");
//		redisUtils.hset("ygField", "count", "64");
//		redisUtils.hset("ygField", "coverageRate", "64");
//		redisUtils.hset("ygField", "createdTime", "64");
//		redisUtils.hset("ygField", "creatTime", "30");
//
//		redisUtils.hset("ygField", "creatUserCode", "64");
//		redisUtils.hset("ygField", "CUPID", "64");
//		redisUtils.hset("ygField", "CUSTOMERID", "64");
//		redisUtils.hset("ygField", "CUSTOMERIP", "64");
//		redisUtils.hset("ygField", "CUSTOMERNAME", "225");
//		
//		redisUtils.hset("ygField", "CUSTOMERSTATUS", "10");
//		redisUtils.hset("ygField", "dataFlow", "64");
//		redisUtils.hset("ygField", "dataName", "64");
//		redisUtils.hset("ygField", "dateZone", "64");
//		redisUtils.hset("ygField", "dayShiftNum", "64");
//		
//		redisUtils.hset("ygField", "dayShiftTra", "64");
//		redisUtils.hset("ygField", "dealWith", "64");
//		redisUtils.hset("ygField", "demogra", "64");
//		redisUtils.hset("ygField", "departmentName", "64");
//		redisUtils.hset("ygField", "dept", "64");
//
//		redisUtils.hset("ygField", "detailid", "64");
//		redisUtils.hset("ygField", "dtArea", "64");
//		redisUtils.hset("ygField", "dtAreacode", "64");
//		redisUtils.hset("ygField", "dtCity", "64");
//		redisUtils.hset("ygField", "dtCitycode", "64");
//
//		redisUtils.hset("ygField", "dtId", "64");
//		redisUtils.hset("ygField", "dtProvince", "64");
//		redisUtils.hset("ygField", "dtProvincecode", "64");
//		redisUtils.hset("ygField", "edu", "64");
//		redisUtils.hset("ygField", "effect", "64");
//		
//		redisUtils.hset("ygField", "email", "64");
//		redisUtils.hset("ygField", "endTime", "64");
//		redisUtils.hset("ygField", "evensubtype", "64");
//		redisUtils.hset("ygField", "eventarea", "64");
//		redisUtils.hset("ygField", "eventdesc", "5000");
//		
//		redisUtils.hset("ygField", "eventId", "64");
//		redisUtils.hset("ygField", "eventname", "64");
//		redisUtils.hset("ygField", "eventsource", "64");
//		redisUtils.hset("ygField", "eventype", "64");
//		redisUtils.hset("ygField", "eventArea", "64");
//		
//		redisUtils.hset("ygField", "eventCoefficient", "64");
//		redisUtils.hset("ygField", "eventGradeName", "64");
//		redisUtils.hset("ygField", "eventNum", "64");
//		redisUtils.hset("ygField", "eventReportUnit", "64");
//		redisUtils.hset("ygField", "eventSource", "64");
//
//		redisUtils.hset("ygField", "eventState", "64");
//		redisUtils.hset("ygField", "eventType", "64");
//		redisUtils.hset("ygField", "ext", "64");
//		redisUtils.hset("ygField", "FACETYPE", "64");
//		redisUtils.hset("ygField", "FASESTATUS", "10");
//
//		redisUtils.hset("ygField", "feedback", "64");
//		redisUtils.hset("ygField", "filePath", "64");
//		redisUtils.hset("ygField", "firstContact", "10");
//		redisUtils.hset("ygField", "formatCode", "64");
//		redisUtils.hset("ygField", "funcCode", "64");
//
//		redisUtils.hset("ygField", "funcName", "128");
//		redisUtils.hset("ygField", "funcType", "64");
//		redisUtils.hset("ygField", "gaodeLat", "64");
//		redisUtils.hset("ygField", "gaodeLng", "64");
//		redisUtils.hset("ygField", "gdLat", "64");
//		
//		redisUtils.hset("ygField", "gdLng", "64");
//		redisUtils.hset("ygField", "gisLat", "64");
//		redisUtils.hset("ygField", "gisLng", "64");
//		redisUtils.hset("ygField", "highSchool", "64");
//		redisUtils.hset("ygField", "hobby", "64");
//		
//		redisUtils.hset("ygField", "host", "64");
//		redisUtils.hset("ygField", "icon", "255");
//		redisUtils.hset("ygField", "imgId", "64");
//		redisUtils.hset("ygField", "imgPath", "64");
//		redisUtils.hset("ygField", "INCLUDE", "255");
//
//		redisUtils.hset("ygField", "interface", "64");
//		redisUtils.hset("ygField", "interfaceName", "255");
//		redisUtils.hset("ygField", "interfaceName", "64");
//		redisUtils.hset("ygField", "ip", "64");
//		redisUtils.hset("ygField", "isnormal", "64");
//
//		redisUtils.hset("ygField", "isOpen", "8");
//		redisUtils.hset("ygField", "isopening", "8");
//		redisUtils.hset("ygField", "isCheck", "16");
//		redisUtils.hset("ygField", "isLocalton", "64");
//		redisUtils.hset("ygField", "isSuccess", "2");
//		
//		redisUtils.hset("ygField", "juniorCollege", "64");
//		redisUtils.hset("ygField", "lastStatus", "10");
//		redisUtils.hset("ygField", "lastTime", "30");
//		redisUtils.hset("ygField", "LegalPerson", "64");
//		redisUtils.hset("ygField", "lfPartyId", "32");
//		
//		redisUtils.hset("ygField", "loginId", "64");
//		redisUtils.hset("ygField", "loginIp", "64");
//		redisUtils.hset("ygField", "loginLogId", "64");
//		redisUtils.hset("ygField", "loginWeb", "64");
//		redisUtils.hset("ygField", "logId", "64");
//		
//		redisUtils.hset("ygField", "logIp", "64");
//		redisUtils.hset("ygField", "logWeb", "64");
//		redisUtils.hset("ygField", "masterHigh", "64");
//		redisUtils.hset("ygField", "middleSchool", "64");
//		redisUtils.hset("ygField", "modelId", "64");
//
//		redisUtils.hset("ygField", "modelName", "64");
//		redisUtils.hset("ygField", "month", "64");
//		redisUtils.hset("ygField", "multipleLevel", "64");
//		redisUtils.hset("ygField", "nightShiftNum", "64");
//		redisUtils.hset("ygField", "nightShiftTra", "64");
//
//		redisUtils.hset("ygField", "OPERATORS", "64");
//		redisUtils.hset("ygField", "operatorType", "64");
//		redisUtils.hset("ygField", "order", "64");
//		redisUtils.hset("ygField", "orgCode", "64");
//		redisUtils.hset("ygField", "parmFunc", "64");
//
//		redisUtils.hset("ygField", "parmName", "64");
//		redisUtils.hset("ygField", "parmParent", "64");
//		redisUtils.hset("ygField", "parmStatus", "64");
//		redisUtils.hset("ygField", "parmType", "64");
//		redisUtils.hset("ygField", "mapType", "8");
//		
//		redisUtils.hset("ygField", "memo", "128");
//		redisUtils.hset("ygField", "menuName", "128");
//		redisUtils.hset("ygField", "menuUrl", "128");
//		redisUtils.hset("ygField", "orgName", "128");
//		redisUtils.hset("ygField", "methodName", "100");
//		
//		redisUtils.hset("ygField", "multiLogin", "8");
//		redisUtils.hset("ygField", "PACKAGECONTAINS", "225");
//		redisUtils.hset("ygField", "PACKAGEID", "64");
//		redisUtils.hset("ygField", "PACKAGENAME", "225");
//		redisUtils.hset("ygField", "PACKAGEREMARK", "225");
//
//		redisUtils.hset("ygField", "param", "254");
//		redisUtils.hset("ygField", "parameters", "255");
//		redisUtils.hset("ygField", "params", "256");
//		redisUtils.hset("ygField", "paramInformation", "255");
//		redisUtils.hset("ygField", "parmDesc", "256");
//
//		redisUtils.hset("ygField", "partyName", "32");
//		redisUtils.hset("ygField", "phoneNumber", "255");
//		redisUtils.hset("ygField", "parentCode", "64");
//		redisUtils.hset("ygField", "parentCodes", "64");
//		redisUtils.hset("ygField", "parentOrgCode", "64");
//		
//		redisUtils.hset("ygField", "parmCode", "64");
//		redisUtils.hset("ygField", "password", "64");
//		redisUtils.hset("ygField", "phone", "64");
//		redisUtils.hset("ygField", "phoneNum", "64");
//		redisUtils.hset("ygField", "pname", "64");
//		
//		redisUtils.hset("ygField", "port", "64");
//		redisUtils.hset("ygField", "powerFather", "64");
//		redisUtils.hset("ygField", "powerLevel", "64");
//		redisUtils.hset("ygField", "powerModule", "64");
//		redisUtils.hset("ygField", "powerName", "64");
//		
//		redisUtils.hset("ygField", "powerNum", "64");
//		redisUtils.hset("ygField", "powerType", "64");
//		redisUtils.hset("ygField", "primarySchool", "64");
//		redisUtils.hset("ygField", "protocol", "64");
//		redisUtils.hset("ygField", "province", "64");
//
//		redisUtils.hset("ygField", "rankTitle", "64");
//		redisUtils.hset("ygField", "roleRemark", "255");
//		redisUtils.hset("ygField", "remoteUrl", "256");
//		redisUtils.hset("ygField", "roleName", "128");
//		redisUtils.hset("ygField", "speName", "128");
//
//		redisUtils.hset("ygField", "speRemark", "256");
//		redisUtils.hset("ygField", "roleNum", "64");
//		redisUtils.hset("ygField", "roleUpdateUser", "64");
//		redisUtils.hset("ygField", "roleUser", "64");
//		redisUtils.hset("ygField", "routePath", "64");
//		
//
//		redisUtils.hset("ygField", "routeTitle", "64");
//		redisUtils.hset("ygField", "rDepartment", "64");
//		redisUtils.hset("ygField", "rPerson", "64");
//		redisUtils.hset("ygField", "scoreName", "64");
//		redisUtils.hset("ygField", "sex", "64");
//		
//		redisUtils.hset("ygField", "shift", "64");
//		redisUtils.hset("ygField", "shiftId", "64");
//		redisUtils.hset("ygField", "shiftWeight", "64");
//		redisUtils.hset("ygField", "shortName", "64");
//		
//		redisUtils.hset("ygField", "simpleName", "64");
//		redisUtils.hset("ygField", "source", "64");
//		redisUtils.hset("ygField", "specialPowerKey", "64");
//		redisUtils.hset("ygField", "speed", "64");
//		redisUtils.hset("ygField", "speKey", "64");
//
//		redisUtils.hset("ygField", "speNum", "64");
//		redisUtils.hset("ygField", "speType", "8");
//		redisUtils.hset("ygField", "speUse", "64");
//		redisUtils.hset("ygField", "speVal", "256");
//		redisUtils.hset("ygField", "src", "255");
//
//		redisUtils.hset("ygField", "srcPath", "128");
//		redisUtils.hset("ygField", "startTime", "64");
//		redisUtils.hset("ygField", "stateName", "64");
//		redisUtils.hset("ygField", "taksDesc", "255");
//		redisUtils.hset("ygField", "taskCron", "100");
//		
//		redisUtils.hset("ygField", "taskId", "64");
//		redisUtils.hset("ygField", "taskName", "255");
////		redisUtils.hset("ygField", "template", "255");
//		redisUtils.hset("ygField", "time", "254");
//		redisUtils.hset("ygField", "times", "64");
//		
//		redisUtils.hset("ygField", "timeZone", "64");
//		redisUtils.hset("ygField", "trajectoryCoefficient", "64");
//		redisUtils.hset("ygField", "treeNames", "255");
//		redisUtils.hset("ygField", "type", "64");
//		redisUtils.hset("ygField", "typename", "64");
//		
//		redisUtils.hset("ygField", "UERID", "64");
//		redisUtils.hset("ygField", "unitCode", "64");
//		redisUtils.hset("ygField", "unitId", "64");
//		redisUtils.hset("ygField", "unitName", "64");
//		redisUtils.hset("ygField", "UNIXTIMESTAMP", "64");
//
//		redisUtils.hset("ygField", "updateTime", "30");
//		redisUtils.hset("ygField", "updateUserCode", "64");
//		redisUtils.hset("ygField", "userCode", "64");
//		redisUtils.hset("ygField", "userEmail", "255");
//		redisUtils.hset("ygField", "userType", "64");
//
//		redisUtils.hset("ygField", "usrActId", "64");
//		redisUtils.hset("ygField", "workHours", "64");
//		redisUtils.hset("ygField", "year", "64");
	}	
	
}
