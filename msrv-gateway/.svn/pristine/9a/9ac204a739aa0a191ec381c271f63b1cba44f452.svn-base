package com.mhtech.platform.msrv.gateway.login.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.stream.events.EndDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.google.common.primitives.UnsignedLong;
import com.mhtech.common.result.entity.Result;
import com.mhtech.common.result.entity.UnicomResponseEnums;
import com.mhtech.platform.msrv.auth.login.service.EmailService;
import com.mhtech.platform.msrv.auth.login.service.OrgFuncService;
//import com.mhtech.platform.msrv.auth.login.service.PowerService;
import com.mhtech.platform.msrv.auth.login.service.SpUserService;
import com.mhtech.platform.msrv.auth.login.service.SysPowerService;
import com.mhtech.platform.msrv.auth.login.service.SysRoleService;
import com.mhtech.platform.msrv.auth.login.service.UserPowerService;
import com.mhtech.platform.msrv.auth.login.service.UserService;
import com.mobile.model.Email;
import com.mobile.model.SpePower;
import com.mobile.model.SysPower;
import com.mobile.model.User;
import com.mobile.utils.MD5HashUtil;
import com.mobile.utils.Utilis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.expr.NewArray;

/**
 * @ClassName UserLoginController
 * @Description TODO
 * @Author admini
 * @Date 2019/10/15 16:43
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value="/auth/userPower")
@Api(value = "userPowerController", tags = "用户角色权限管理")
@SuppressWarnings("all")
public class UserPowerController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserPowerService userPowerService;
	@Autowired
	SpUserService spUserService;
	@Autowired
	SysRoleService sysRoleService;
	@Autowired
	SysPowerService sysPowerService;
	@Autowired
	OrgFuncService orgFuncService;
//	@Autowired
//	PowerService powerService;
	@Autowired
	EmailService emailService;

	private final Logger logger= LoggerFactory.getLogger(UserPowerController.class);


	/**
	 * @description		用户增加角色和特殊权限
	 */
	@PostMapping(value="/addRole")
    @ApiOperation(value = "用户增加角色和特殊权限(如有)")
	@Transactional(rollbackFor = Exception.class)
    public Result addRole(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        //接收新用户
        Map<String, Object> reqUser= (Map<String, Object>) reqObject.get("newUser");       
        String userCode = reqUser.get("userCode").toString();
        User spUser = new User();
        spUser.setUserCode(userCode);
        spUser.setName(reqUser.get("name").toString());
        spUser.setEmail(reqUser.get("email").toString());
        spUser.setPhone(reqUser.get("phone").toString());
        spUser.setMultiLogin(reqUser.get("multiLogin").toString());
        spUser.setUserType(reqUser.get("parmCode").toString());
        spUser.setUpdateUser(reqUser.get("updateUser").toString());
        spUser.setCompanyCode(reqUser.get("companyCode").toString());
        List<String> orgList= (List<String>) reqUser.get("orgCode");
   
        spUser.setOrgCode(orgList.get(orgList.size()-1));
        
        //查询系统初始密码
	    String originPwd = userService.queryParamByKey("originalPassword");
        originPwd = MD5HashUtil.md5hash(originPwd, userCode).toString();
        spUser.setPassword(originPwd);        
        List<String> roleList= (List<String>) reqObject.get("roleList");
        
        List<Map<String, Object>> spePowerList= (List<Map<String, Object>>) reqObject.get("spePowerList");
        if(reqObject==null||reqUser==null){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        //获取用户职能list
        List<String> funcList= (List<String>) reqObject.get("funcList");        

        //增加新用户
        boolean flag = false ;
     
        String id = spUserService.addNewUser(spUser);
       
        //增加角色
        if(roleList.size()>0) {
            flag = userPowerService.addrole(userCode, roleList);
        }
        //增加特权
        if(spePowerList.size()>0) {
            flag = userPowerService.addSpePower(userCode, spePowerList);
        }
        //增加职能
        if(funcList.size()>0) {
            flag = userPowerService.addFunc(userCode, funcList);
        }
        if (id!=null && flag==true) {
        	return new Result(success, id, msg);
		}
        Result result = new Result(success, flag, msg);
        return result;
    }
	
	/**
	 * @description		更新用户角色+特殊权限(如有)
	 */
	@PostMapping(value="/updateRole")
    @ApiOperation(value = "更新用户角色+特殊权限(如有)")
	@Transactional(rollbackFor = Exception.class)
    public Result updateRole(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;

        //获取用户
        Map<String, Object> reqUser= (Map<String,Object>) reqObject.get("newUser");
        System.err.println("更新用户:"+reqUser);
        String userCode = reqUser.get("userCode").toString();
        User spUser = new User();
        spUser.setUserCode(userCode);
        spUser.setName(reqUser.get("name").toString());
        spUser.setEmail(reqUser.get("email").toString());
     
        spUser.setPhone(reqUser.get("phone").toString());
        spUser.setMultiLogin(reqUser.get("multiLogin").toString());
        spUser.setUserType(reqUser.get("parmCode").toString());
        spUser.setUpdateUser(reqUser.get("updateUser").toString());
	    spUser.setCompanyCode(reqUser.get("companyCode").toString());
        List<String> orgList= (List<String>) reqUser.get("orgCode");
        System.err.println(orgList);
        spUser.setOrgCode(orgList.get(orgList.size()-1));
        
        //获取角色list
        List<String> roleList= (List<String>) reqObject.get("roleList");
        //获取特殊权限list
        List<Map<String, Object>> spePowerList= (List<Map<String, Object>>) reqObject.get("spePowerList");
        if(reqObject==null||spUser==null){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        
        //获取用户职能list
        List<String> funcList= (List<String>) reqObject.get("funcList");

        System.out.println(spUser);
        System.out.println(roleList);
        System.out.println(spePowerList);
        System.out.println(funcList);
        
        //测试数据
		List<String> tempList = new ArrayList<String>();
		tempList.add("0011");
		tempList.add("0021");
		tempList.add("0031");

		 List list=new ArrayList<>(); 
        //要更新的邮箱号
		String emailNew=reqUser.get("email").toString();
		String emailOld=userService.userEmail(userCode);
		if (!emailNew.equals(emailOld)) {
			//更新邮件表邮箱号
	        emailService.updateEmail(emailNew,emailOld);
	        //更新邮箱收件所有的-先查询，在替换，在更新
	        List<Email> listToEmail = emailService.listToEmail(emailOld);
	      
	        //转格式 xx->"xx"
	        String oldEmail= JSON.toJSONString(emailOld);
	        String newEmail= JSON.toJSONString(emailNew);

	        //当用户邮箱改变，将所有to收件人集合-邮箱-改为新用户邮箱
	        listToEmail.forEach(sp->{
	        	String[] to = sp.getTo();
	        	for (int i = 0; i < to.length; i++) {
					if (to[i].equals(oldEmail)) {
						to[i]=to[i].replace(to[i], newEmail);
					}				
				}
	        	sp.setTo(to);
	        });
	        //批量更新当前用户作为收件人邮箱号的集合
	        emailService.emailToUpdate(listToEmail);
	        list.add("您已经更新邮箱，为了使考核邮件群发功能正常使用，你移致邮件群发设置页面-更新密码，确保邮箱号与密码正确");
		}
      
		//更新用户
		boolean flag = spUserService.updateSysPowerInfo(spUser)>0;
		//更新角色
		flag = userPowerService.updateRole(userCode, roleList);
		//更新特殊权限
        flag = userPowerService.updateSpePower(userCode, spePowerList);
        //更新职能
        flag = userPowerService.updateFunc(userCode, funcList);
        
    
        Result result = new Result(success, list, msg);
        return result;
    }
	
	/**
	 * @description		删除用户角色All
	 */
	@PostMapping(value="/deleteAllRole")
    @ApiOperation(value = "删除用户角色All")
	@Transactional(rollbackFor = Exception.class)
    public Result deleteUserRole(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String userCode = reqObject.get("userCode").toString();
        if(reqObject==null||userCode==null||userCode.isEmpty()){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        boolean flag = userPowerService.deleteRole(userCode);

        return new Result(success, flag, msg);
    }
	
	/**
	 * @description		删除用户具体角色
	 */
	@PostMapping(value="/deleteRole")
    @ApiOperation(value = "删除用户具体角色")
	@Transactional(rollbackFor = Exception.class)
    public Result deleteRole(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String userCode = reqObject.get("userCode").toString();
        String roleNum = reqObject.get("roleNum").toString();
        if(reqObject==null||userCode==null||userCode.isEmpty()||roleNum==null||roleNum.isEmpty()){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        boolean flag = userPowerService.deleteRoleByRoleNum(userCode,roleNum);
        return new Result(success, flag, msg);
    }
	
	/**
	 * @description		查找用户角色+特殊权限(如有)条状到修改页面
	 */
	@PostMapping(value="/queryRole")
    @ApiOperation(value = "查找用户角色+特殊权限(如有),去修改页面")
	@Transactional(rollbackFor = Exception.class)
    public Result queryRole(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String userCode = reqObject.get("userCode").toString();
        if(reqObject==null||userCode==null||userCode.isEmpty()){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        //找到该用户的信息
        User user = spUserService.selectSysPower(userCode);
        userCode = user.getUserCode();        
        
        //找到该用户的角色list
        List<Map<String, Object>> userRoleList = userPowerService.queryRole(userCode);
        List<Map<String, String>> roleList = Utilis.mapObjToString2(userRoleList);
        
        //找到该用户的特殊权限list
        List<Map<String, Object>> SpePowerListMaps = userPowerService.querySpeRoleListMap(userCode);
  
        
        //把特殊权限的详情放进去,做下拉列表
        System.err.println("集合尺寸:"+SpePowerListMaps.size());
        if(SpePowerListMaps.size()>0) {
        	//System.err.println(SpePowerListMaps.size());
        	//把特殊权限值的父类找出来 添进去
            for (int i = 0; i < SpePowerListMaps.size(); i++) {
            	String string = SpePowerListMaps.get(i).get("speNum").toString();
            	//取到特殊权限
        		Map<String, Object> spePower = userPowerService.getSpePowerInfo(SpePowerListMaps.get(i).get("speNum").toString());
        		//取到特殊权限的值
        		String speValue = spePower.get("speValue").toString();
        		System.out.println("speValue:"+speValue);
        		List<Map<String, Object>> spePowerValuesList = userPowerService.querySpePowerValue(speValue);
        		SpePowerListMaps.get(i).put("fatherValue", spePowerValuesList);
        		System.err.println("b");
    		}
        }
        
        
        //查找所有角色
        List<Map<String, Object>> roleListAll = sysRoleService.roleList();
        //用户角色有的做标记
        for (int i = 0; i < roleListAll.size(); i++) {
        	roleListAll.get(i).put("state", "0");
        	for (int j = 0; j < roleList.size(); j++) {
        		if(roleListAll.get(i).get("roleNum").equals(roleList.get(j).get("roleNum"))) {
        			roleListAll.get(i).put("state", "1");
        		}
        	}
		}
        
        //查找该用户所对应公司的所以职能
        List<Map<String, Object>> funcListAll = orgFuncService.queryFuncList(user.getCompanyCode());
        //查找该用户所有的职能
       List<Map<String, Object>> userFuncList = orgFuncService.FuncListByUserCode(userCode);
        //用户角色有的做标记
        for (int i = 0; i < funcListAll.size(); i++) {
        	funcListAll.get(i).put("state", "0");
        	for (int j = 0; j < userFuncList.size(); j++) {
        		if(funcListAll.get(i).get("funcCode").equals(userFuncList.get(j).get("funcCode"))) {
        			funcListAll.get(i).put("state", "1");
        		}
        	}
		}
        
        //查找所有特殊权限
        List<Map<String, Object>> spePowerList = sysPowerService.spePowerList();
        
        //用户有的所标记
        for (int i = 0; i < spePowerList.size(); i++) {
        	spePowerList.get(i).put("powerInfo", null);
        	for (int j = 0; j < SpePowerListMaps.size(); j++) {
        		if(spePowerList.get(i).get("powerNum").equals(SpePowerListMaps.get(j).get("speNum"))) {
        			spePowerList.get(i).put("powerInfo", SpePowerListMaps.get(j));
        		}
        	}
        		
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", user);
        data.put("roleList", roleListAll);
        data.put("spePowerList", spePowerList);
        data.put("funcList", funcListAll);

        return new Result(success, data, msg);
    }

	
	
	//执行特殊权限sql
	@PostMapping(value="/selectUserTsPower")
    @ApiOperation(value = "执行特殊权限的sql-return Result")
    public Result selectUserTsPower(@RequestBody JSONObject reqObject){
		boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
      
        String userCode = reqObject.get("userCode").toString();
        //找到该用户的信息
        User user = spUserService.selectSysPower(userCode);
        userCode = user.getUserCode();        
        //找到该用户的特殊权限list
        List<Map<String, Object>> SpePowerListMaps = userPowerService.querySpeRoleListMap(userCode);
        //把特殊权限的详情放进去,做下拉列表
        if(SpePowerListMaps.size()>0) {
        	//把特殊权限值的父类找出来 添进去
            for (int i = 0; i < SpePowerListMaps.size(); i++) {
            	String string = SpePowerListMaps.get(i).get("speNum").toString();
            	//取到特殊权限
        		Map<String, Object> spePower = userPowerService.getSpePowerInfo(SpePowerListMaps.get(i).get("speNum").toString());
        		//取到特殊权限的值
        		String speValue = spePower.get("speValue").toString();
        		List<Map<String, Object>> spePowerValuesList = userPowerService.querySpePowerValue(speValue);
        		SpePowerListMaps.get(i).put("fatherValue", spePowerValuesList);
    		}
        }
        
     
        //查找所有特殊权限
        List<Map<String, Object>> spePowerList = sysPowerService.spePowerList();
        
        //用户有的所标记
        for (int i = 0; i < spePowerList.size(); i++) {
        	spePowerList.get(i).put("powerInfo", null);
        	for (int j = 0; j < SpePowerListMaps.size(); j++) {
        		if(spePowerList.get(i).get("powerNum").equals(SpePowerListMaps.get(j).get("speNum"))) {
        			spePowerList.get(i).put("powerInfo", SpePowerListMaps.get(j));
        		}
        	}
        		
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("spePowerList", spePowerList);
        return  new Result(success, data, msg);
    }

	
	
	
	
	
	
	/**
	 * @description		角色增加权限
	 */
	@PostMapping(value="/addPower")
    @ApiOperation(value = "角色增加权限")
	@Transactional(rollbackFor = Exception.class)
    public Result addPower(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;

        String roleNum = reqObject.get("roleNum").toString();
        List<String> powerList= (List<String>) reqObject.get("powerList");
        if(reqObject==null||roleNum==null||roleNum.isEmpty()||powerList==null||powerList.size()==0){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        System.out.println(roleNum);
        System.out.println(powerList);

        //测试数据
 		List<String> tempList = new ArrayList<String>();
 		tempList.add("001");
 		tempList.add("002");
 		tempList.add("003");
        
        boolean flag = userPowerService.addPower(roleNum, powerList);
        Result result = new Result(success, flag, msg);
        return result;
    }
	
	/**
	 * @description		更新角色权限
	 */
	@PostMapping(value="/updatePower")
    @ApiOperation(value = "更新角色权限")
	@Transactional(rollbackFor = Exception.class)
    public Result updatePower(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;

        String roleNum = reqObject.get("roleNum").toString();
        List<String> powerList= (List<String>) reqObject.get("powerList");
        if(reqObject==null||roleNum==null||roleNum.isEmpty()||powerList==null||powerList.size()==0){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        System.out.println(roleNum);
        System.out.println(powerList);
        
        //测试数据
		List<String> tempList = new ArrayList<String>();
		tempList.add("0011");
		tempList.add("0022");
		tempList.add("0033");

        boolean flag = userPowerService.updatePower(roleNum, powerList);
        Result result = new Result(success, flag, msg);
        return result;
    }
	
	/**
	 * @description		删除角色权限All
	 */
	@PostMapping(value="/deleteAllPower")
    @ApiOperation(value = "删除角色权限All")
	@Transactional(rollbackFor = Exception.class)
    public Result deleteAllPower(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String roleNum = reqObject.get("roleNum").toString();
        if(reqObject==null||roleNum==null||roleNum.isEmpty()){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        boolean flag = userPowerService.deletePower(roleNum);
        Result result = new Result(success, flag, msg);
        return result;
    }
	
	/**
	 * @description		删除角色具体权限
	 */
	@PostMapping(value="/deletePower")
    @ApiOperation(value = "删除角色具体权限")
	@Transactional(rollbackFor = Exception.class)
    public Result deletePower(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String roleNum = reqObject.get("roleNum").toString();
        String powerNum = reqObject.get("powerNum").toString();
        if(reqObject==null||roleNum==null||roleNum.isEmpty()||powerNum==null||powerNum.isEmpty()){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        boolean flag = userPowerService.deletePowerByPowerNum(roleNum,powerNum);
        Result result = new Result(success, flag, msg);
        return result;
    }
	
	/**
	 * @description		查找角色权限
	 */
	@PostMapping(value="/queryPower")
    @ApiOperation(value = "查找角色权限")
	@Transactional(rollbackFor = Exception.class)
    public Result queryPower(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String roleNum = reqObject.get("roleNum").toString();
        if(reqObject==null||roleNum==null||roleNum.isEmpty()){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        List<Map<String, Object>> userRoleList = userPowerService.queryPower(roleNum);
        List<Map<String, String>> roleList = Utilis.mapObjToString2(userRoleList);
        Result result = new Result(success, roleList, msg);
        return result;
    }
	
	/**
	 * @description		用户增加特殊权限
	 */
	@PostMapping(value="/addSpeRole")
    @ApiOperation(value = "增加特殊权限")
	@Transactional(rollbackFor = Exception.class)
    public Result addSpeRole(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String userCode = (String)reqObject.get("userCode");
        List<Map<String, Object>> spePowerList= (List<Map<String, Object>>) reqObject.get("spePowerList");
        if(reqObject==null||userCode==null||userCode.isEmpty()||spePowerList==null||spePowerList.size()==0){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        System.out.println(userCode);
        System.out.println(spePowerList);

        //测试数据
 		List<SpePower> tempList = new ArrayList<SpePower>();
 		for (int i = 0; i < 3; i++) {
 			SpePower s1 = new SpePower();
 	 		s1.setSpeNum("spenum"+i);
 	 		s1.setSpeName("spename"+i);
 	 		s1.setSpeKey("spekey"+i);
 	 		s1.setSpeVal("speval"+i);
 	 		s1.setSpeType("t"+i);
 	 		s1.setSpeUse("speuse"+i);
 	        s1.setSpeRemark("remark"+i);
 	       tempList.add(s1);
		}

        boolean flag = userPowerService.addSpePower(userCode, spePowerList);
        Result result = new Result(success, flag, msg);
        return result;
    }
	
	/**
	 * @description		更新用户特殊权限
	 */
	@PostMapping(value="/updateSpePower")
    @ApiOperation(value = "更新用户超级权限")
	@Transactional(rollbackFor = Exception.class)
    public Result updateSpePower(@RequestBody JSONObject reqObject){
		 boolean success = true;
	        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;

	        String userCode = reqObject.get("userCode").toString();
	        List<Map<String, Object>> spePowerList= (List<Map<String, Object>>) reqObject.get("spePowerList");
	        if(reqObject==null||userCode==null||userCode.isEmpty()||spePowerList==null||spePowerList.size()==0){
	            success=false;
	            msg = UnicomResponseEnums.NULL_ARGUMENT;
	            return  new Result(success, null, msg);
	        }
	        System.out.println(userCode);
	        System.out.println(spePowerList);

	        //测试数据
	 		List<SpePower> tempList = new ArrayList<SpePower>();
	 		for (int i = 0; i < 3; i++) {
	 			SpePower s1 = new SpePower();
	 	 		s1.setSpeNum("spenum"+i);
	 	 		s1.setSpeName("spename"+i);
	 	 		s1.setSpeKey("spekey"+i);
	 	 		s1.setSpeVal("speval"+i);
	 	 		s1.setSpeType("t"+i);
	 	 		s1.setSpeUse("speuse"+i);
	 	        s1.setSpeRemark("remark"+i);
	 	       tempList.add(s1);
			}

	        boolean flag = userPowerService.updateSpePower(userCode, spePowerList);
	        Result result = new Result(success, flag, msg);
	        return result;
    }
	
	/**
	 * @description		删除特殊权限All
	 */
	@PostMapping(value="/deleteAllSpeRole")
    @ApiOperation(value = "删除用户特殊权限All")
	@Transactional(rollbackFor = Exception.class)
    public Result deleteAllSpeRole(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String userCode = reqObject.get("userCode").toString();
        if(reqObject==null||userCode==null||userCode.isEmpty()){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        boolean flag = userPowerService.deleteSpePower(userCode);
        Result result = new Result(success, flag, msg);
        return result;
    }
	
	/**
	 * @description		查找用户特殊权限
	 */
	@PostMapping(value="/querySpePower")
    @ApiOperation(value = "查找用户特殊权限")
	@Transactional(rollbackFor = Exception.class)
    public Result querySpePower(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String userCode = reqObject.get("userCode").toString();
        if(reqObject==null||userCode==null||userCode.isEmpty()){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        List<SpePower> querySpeRole = userPowerService.querySpeRole(userCode);
        Result result = new Result(success, querySpeRole, msg);
        return result;
    }
	
	
	/**
	 * @description		查询用户所有的menu权限和html权限
	 */
	@PostMapping(value="/queryFristPower")
    @ApiOperation(value = "查询用户所有的menu权限和html权限")
	@Transactional(rollbackFor = Exception.class)
    public Result queryFristPower(@RequestBody JSONObject reqObject){
		boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        
        String userCode=null;
        if (reqObject.get("userCode")!=null) {
        	  userCode = reqObject.get("userCode").toString();
		}else {
			 return  new Result(success, "缺少userCode", msg);  
		}
        
        if(reqObject==null||userCode==null||userCode.isEmpty()){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }	
		List<SysPower> queryPowerList = userPowerService.queryMenuAndHtmlPower(userCode);
		if(queryPowerList.size()==0) {
			success = true;
		    msg = UnicomResponseEnums.No_Power;
			Result result = new Result(success, null, msg);
		    return result;
		}

		List<String> powerList = new ArrayList<String>();
		for (int i = 0; i < queryPowerList.size(); i++) {
			powerList.add(queryPowerList.get(i).getPowerNum());
		}
		
		Result result = new Result(success, powerList, msg);
	    return result;
	}
	
	/**
	 * @description		查询html权限下的btn权限和特殊权限
	 */
	@PostMapping(value="/queryBtnPower")
    @ApiOperation(value = "查询html权限下的btn权限和特殊权限")
	@Transactional(rollbackFor = Exception.class)
    public Result queryBtnPower(@RequestBody JSONObject reqObject){
		boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String userCode = reqObject.getString("userCode");
        String powerNum = reqObject.getString("powerNum"); 
        if(reqObject==null||StringUtils.isEmpty(userCode)|StringUtils.isEmpty(powerNum)){
            success=false;
            msg = UnicomResponseEnums.NULL_ARGUMENT;
            return  new Result(success, null, msg);
        }
        //获取该用户指定页面下的btn权限
		List<SysPower> queryPowerList = userPowerService.queryBtnPower(userCode,powerNum);
		List powerLists=new ArrayList<>();
		//btn权限编号集合
		for (int i = 0; i < queryPowerList.size(); i++) {	
			Map<String,Object> map = new HashMap<>();
			map.put("powerUrl",queryPowerList.get(i).getUrl());
			map.put("powerNum",queryPowerList.get(i).getPowerNum());		
			powerLists.add(map);	
		}
		//没有权限
		if(powerLists.size()==0) {
		    return new Result(true, powerLists, UnicomResponseEnums.No_Btn_Power);
		}
		List<Map<String, Object>> querysPePowerList = userPowerService.querySpePower2(userCode);
		for (int i = 0; i < querysPePowerList.size(); i++) {
			Map<String,Object> map = new HashMap<>();
			map.put("powerUrl",querysPePowerList.get(i).get("url"));
			map.put("powerNum",querysPePowerList.get(i).get("speNum"));		
			powerLists.add(map);
		}
	    return new Result(success, powerLists, msg);
	}
	

	/**
	 * @description		验证邮箱手机是否存在
	 */
	@PostMapping(value="/isAccountExist")
    @ApiOperation(value = "验证邮箱手机是否存在")
	@Transactional(rollbackFor = Exception.class)
    public Result userTypeList(@RequestBody JSONObject reqObject){
		boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		String contect = reqObject.getString("contect");
		boolean flag = userPowerService.isAccountExist(contect);
		return new Result(success, flag, msg);
	}
	
	/**
	 * @description		验证账户是否存在
	 */
	@PostMapping(value="/isUserCodeExist")
    @ApiOperation(value = "验证账户是否存在")
	@Transactional(rollbackFor = Exception.class)
    public Result isUserCodeExist(@RequestBody JSONObject reqObject){
		boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		String contect = reqObject.getString("contect");
		boolean flag = userPowerService.isUserCodeExist(contect);
		return new Result(success, flag, msg);
	}
	
	/**
	 * @description		根据id查找特殊权限
	 */
	@PostMapping(value="/querySpePowerInfoByPowerNum")
    @ApiOperation(value = "根据权限编号查找特殊权限详情")
	@Transactional(rollbackFor = Exception.class)
    public Result querySpePowerInfoById(@RequestBody JSONObject reqObject){
		boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
		String powerNum =  reqObject.get("powerNum").toString();
		//取到特殊权限
		Map<String, Object> spePower = userPowerService.getSpePowerInfo(powerNum);
		//取到特殊权限的值
		String speValue = spePower.get("speValue").toString();
		List<Map<String, Object>> spePowerValue = new ArrayList<Map<String,Object>>();
		try {
			spePowerValue = userPowerService.querySpePowerValue(speValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//在放进去map
		spePower.put("speValue", spePowerValue);
		Result result = new Result(success, spePower, msg);
		return result;
	}
}
	

