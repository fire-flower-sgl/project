package com.mhtech.platform.msrv.gateway.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.gateway.response.RespCode;
import com.mhtech.platform.msrv.gateway.response.RespObject;
import com.mhtech.platform.msrv.gateway.utils.RespUtils;
import com.mobile.model.Page;
import com.mobile.model.Result;
import com.mobile.model.SysPower;
import com.mobile.model.UnicomResponseEnums;
import com.mobile.model.User;
import com.mobile.service.EmailService;
import com.mobile.service.OrgFuncService;
import com.mobile.service.SysPowerService;
import com.mobile.service.SysRoleService;
import com.mobile.service.UserPowerService;
import com.mobile.service.UserService;
import com.mobile.utils.Utilis;


/**
 * @ClassName UserLoginController
 * @Description TODO
 * @Author admini
 * @Date 2019/10/15 16:43
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value="/MobileApi/user")
//@Api(value = "userPowerController", tags = "用户管理")
@SuppressWarnings("all")
public class MobileUserController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserPowerService userPowerService;
	@Autowired
	SysRoleService sysRoleService;
	@Autowired
	SysPowerService spsPowerService;
	@Autowired
	OrgFuncService orgFuncService;
	@Autowired
	EmailService emailService;

	private final Logger logger= LoggerFactory.getLogger(MobileUserController.class);
	

	/**
	 * @description		新增用户+角色+特殊权限
	 */
	@PostMapping(value="/addUser")
//    @ApiOperation(value = "新增用户(增加角色和特殊权限)")
    public RespObject<?> addUser(@RequestBody JSONObject reqObject){
        if(!reqObject.containsKey("newUser")||!reqObject.containsKey("roleList")||!reqObject.containsKey("spePowerList")||!reqObject.containsKey("funcList")) {
    		return RespUtils.build(RespCode.NULL_ARGUMENT);
        }
        boolean flag = userService.add(reqObject);
        return RespUtils.buildOKWithDataYg(flag);        
    }
	
	/**
	 * @description		更新用户+角色+特殊权限
	 */
	@PostMapping(value="/updateUser")
//    @ApiOperation(value = "更新用户(角色+特殊权限)")
    public RespObject<?> updateRole(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        if(!reqObject.containsKey("newUser")||!reqObject.containsKey("roleList")||!reqObject.containsKey("spePowerList")||!reqObject.containsKey("funcList")) {
    		return RespUtils.build(RespCode.NULL_ARGUMENT);
        }
        boolean flag = userService.update(reqObject);
        return RespUtils.buildOKWithDataYg(flag);  
    }
		
	
	/**
	 * @description		查找用户角色+特殊权限(如有)条状到修改页面
	 */
	@PostMapping(value="/goUpdataUserUi")
//    @ApiOperation(value = "去用户修改页面(查找该用户信息)")
    public RespObject<?> queryRole(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String userCode = reqObject.get("userCode").toString();
        if(reqObject==null||userCode==null||userCode.isEmpty()){
    		return RespUtils.build(RespCode.NULL_ARGUMENT);
        }
        //找到该用户的信息
        User user = userService.selectSysPower(userCode);
        userCode = user.getUserCode();        

        //找到该用户的角色list
        List<Map<String, Object>> userRoleList = userPowerService.queryRole(userCode);
        List<Map<String, String>> roleList = Utilis.mapObjToString2(userRoleList);
        
        //找到该用户的特殊权限list
        List<Map<String, Object>> SpePowerListMaps = userPowerService.querySpeRoleListMap(userCode);
        
        //把特殊权限的详情放进去,做下拉列表
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
        
        //System.err.println("SpePowerListMaps:"+SpePowerListMaps);
        
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
        List<Map<String, Object>> funcListAll = orgFuncService.queryFuncListByCompanycode(user.getCompanyCode());
        //查找该用户所有的职能
       List<Map<String, Object>> userFuncList = orgFuncService.funcListByUserCode(userCode);
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
        List<Map<String, Object>> spePowerList = spsPowerService.spePowerList();
        
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
        return RespUtils.buildOKWithDataYg(data);
    }
	
	/**
	 * @description		去新增用户页面
	 */
	@PostMapping(value="/goAddUserUi")
//    @ApiOperation(value = "去新增用户页面")
    public RespObject<?> goAddUserUi(@RequestBody JSONObject reqObject){
        boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        //查找所有角色
        List<Map<String, Object>> roleListAll = sysRoleService.roleList();
        //查找所有特殊权限
        List<Map<String, Object>> spePowerList = spsPowerService.spePowerList();
        for (int i = 0; i < spePowerList.size(); i++) {
        	//查询特殊权限的value,它是个sql
        	String sql = spePowerList.get(i).get("specialPowerValue").toString();
        	
        	
        	List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
        	
        	dataList = sysRoleService.querySpePower(sql);
			
			//根据特殊权限是value,执行slq执行,把结果重新放入对象
        	spePowerList.get(i).put("specialPowerValue", dataList);
		}    
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("roleList", roleListAll);
        data.put("spePowerList", spePowerList);
        return RespUtils.buildOKWithDataYg(data);
    }
	
	
	
	/**
	 * @description		验证邮箱手机是否存在
	 */
	@PostMapping(value="/isAccountExist")
//    @ApiOperation(value = "验证邮箱手机是否存在")
    public RespObject<?> userTypeList(@RequestBody JSONObject reqObject){
		String phoneNo = reqObject.getString("phoneNo");
		String email = reqObject.getString("email");
		String userCode = reqObject.getString("updateUserCode");
		int flag = userService.isAccountExist(phoneNo,email,userCode);
	    return RespUtils.buildOKWithDataYg(flag);
	}
	
	/**
	 * @description		验证账户是否存在
	 */
	@PostMapping(value="/isUserCodeExist")
//    @ApiOperation(value = "验证账户是否存在")
    public RespObject<?> isUserCodeExist(@RequestBody JSONObject reqObject){
		String contect = reqObject.getString("contect");
		String userCode = reqObject.getString("updateUserCode");
		boolean flag = userService.isUserCodeExist(contect,userCode);
	    return RespUtils.buildOKWithDataYg(flag);
	}
	
	@PostMapping(value="/queryUserPage")
//	@ApiOperation(value = "分页查询")
	public RespObject<?> selectAll(@RequestBody JSONObject object ) {;
	    Page userPage = userService.findPage(object);
	    return RespUtils.buildOKWithDataYg(userPage); 
	}
	
	@PostMapping(value="/deleteUser")
//	@ApiOperation(value = "删除用户")
	public RespObject<?> deleteSpUser(@RequestBody User spUser) {
	    boolean success = true;
	    UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
	    boolean flag = userService.delete(spUser.getId())>0;
	    return RespUtils.buildOKWithDataYg(flag);
	}
	
	
	
	
	
	/**
	 * @description		查询用户所有的menu权限和html权限
	 */
	/*
	 * @PostMapping(value="/queryFristPower")
	 * 
	 * @ApiOperation(value = "查询用户所有的menu权限和html权限") public Result
	 * queryFristPower(@RequestBody JSONObject reqObject){ boolean success = true;
	 * UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION; String
	 * userCode=null; if (reqObject.get("userCode")!=null) { userCode =
	 * reqObject.get("userCode").toString(); }else { return new Result(success,
	 * "缺少userCode", msg); }
	 * 
	 * if(reqObject==null||userCode==null||userCode.isEmpty()){ success=false; msg =
	 * UnicomResponseEnums.NULL_ARGUMENT; return new Result(success, null, msg); }
	 * List<SysPower> queryPowerList =
	 * userPowerService.queryMenuAndHtmlPower(userCode);
	 * if(queryPowerList.size()==0) { success = true; msg =
	 * UnicomResponseEnums.No_Power; Result result = new Result(success, null, msg);
	 * return result; }
	 * 
	 * List<String> powerList = new ArrayList<String>(); for (int i = 0; i <
	 * queryPowerList.size(); i++) {
	 * powerList.add(queryPowerList.get(i).getPowerNum()); }
	 * 
	 * Result result = new Result(success, powerList, msg); return result; }
	 */
	
	/**
	 * @description		查询html权限下的btn权限和特殊权限
	 */
	@PostMapping(value="/queryBtnPower")
//    @ApiOperation(value = "查询html权限下的btn权限和特殊权限")
    public RespObject<?> queryBtnPower(@RequestBody JSONObject reqObject){
		boolean success = true;
        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
        String userCode = reqObject.getString("userCode");
        String powerNum = reqObject.getString("powerNum"); 
        if(reqObject==null||StringUtils.isEmpty(userCode)|StringUtils.isEmpty(powerNum)){
        	return RespUtils.build(RespCode.NULL_ARGUMENT);
        }	
		List<SysPower> queryPowerList = userPowerService.queryBtnPower(userCode,powerNum);
		
		//没有权限
		if(queryPowerList.size()==0) {
			return RespUtils.build(RespCode.NO_USER_RIGHTS);
		}

		List powerLists=new ArrayList<>();
		//btn权限编号集合
		for (int i = 0; i < queryPowerList.size(); i++) {	
			Map<String,Object> map = new HashMap<>();
			map.put("powerUrl",queryPowerList.get(i).getUrl());
			map.put("powerNum",queryPowerList.get(i).getPowerNum());		
			powerLists.add(map);	
		}
		
	
		//有btn权限的话,查询数据权限
//		List<SpePower> querysPePowerList = userPowerDao.querySpePower(userCode);
//		for (int i = 0; i < querysPePowerList.size(); i++) {
//			power_sub_name.add(querysPePowerList.get(i).getSpeNum());
//		}
//		List<Map<String, Object>> querysPePowerList = userPowerService.querySpePower2(userCode);
//		for (int i = 0; i < querysPePowerList.size(); i++) {
//			Map<String,Object> map = new HashMap<>();
//			map.put("powerUrl",querysPePowerList.get(i).get("url"));
//			map.put("powerNum",querysPePowerList.get(i).get("speNum"));		
//			powerLists.add(map);
//		}
		
//		System.err.println("=========按钮权限=============");
//		System.err.println(powerLists);
		
	 
	    return RespUtils.buildOKWithDataYg(powerLists);
	}
		
	/**
	 * @description		根据id查找特殊权限
	 */
	@PostMapping(value="/querySpePowerInfoByPowerNum")
//    @ApiOperation(value = "根据权限编号查找特殊权限详情")
    public RespObject<?> querySpePowerInfoById(@RequestBody JSONObject reqObject){
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
			System.err.println("特殊权限value sql错误....");
		}
		//在放进去map
		spePower.put("speValue", spePowerValue);
	    return RespUtils.buildOKWithDataYg(spePower);
	}
	
	
	//执行特殊权限sql
		@PostMapping(value="/selectUserTsPower")
//	    @ApiOperation(value = "执行特殊权限的sql-----新")
	    public RespObject<?> selectUserTsPower(@RequestBody JSONObject reqObject){
	        String userCode = reqObject.get("userCode").toString();
	        
	        //找到该用户的信息
	        User user = userService.selectSysPower(userCode);
	        userCode = user.getUserCode();        

	        
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
	        //查找所有特殊权限
	        List<Map<String, Object>> spePowerList = spsPowerService.spePowerList();
	        
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
		    return RespUtils.buildOKWithDataYg(data);
	    }

	
//	/**
//	 * @description		查找角色权限
//	 */
//	@PostMapping(value="/queryPower")
//   @ApiOperation(value = "查找角色权限")
//   public Result queryPower(@RequestBody JSONObject reqObject){
//       boolean success = true;
//       UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//       String roleNum = reqObject.get("roleNum").toString();
//       if(reqObject==null||roleNum==null||roleNum.isEmpty()){
//           success=false;
//           msg = UnicomResponseEnums.NULL_ARGUMENT;
//           return  new Result(success, null, msg);
//       }
//       List<Map<String, Object>> userRoleList = userPowerService.queryPower(roleNum);
//       List<Map<String, String>> roleList = Utilis.mapObjToString2(userRoleList);
//       Result result = new Result(success, roleList, msg);
//       return result;
//   }
	
//	/**
//	 * @description		用户增加特殊权限
//	 */
//	@PostMapping(value="/addSpeRole")
//   @ApiOperation(value = "增加特殊权限")
//   public Result addSpeRole(@RequestBody JSONObject reqObject){
//       boolean success = true;
//       UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//       String userCode = (String)reqObject.get("userCode");
//       List<Map<String, Object>> spePowerList= (List<Map<String, Object>>) reqObject.get("spePowerList");
//       if(reqObject==null||userCode==null||userCode.isEmpty()||spePowerList==null||spePowerList.size()==0){
//           success=false;
//           msg = UnicomResponseEnums.NULL_ARGUMENT;
//           return  new Result(success, null, msg);
//       }
//       System.out.println(userCode);
//       System.out.println(spePowerList);
//
//       boolean flag = userPowerService.addSpePower(userCode, spePowerList);
//       Result result = new Result(success, flag, msg);
//       return result;
//   }
	
//	/**
//	 * @description		更新用户特殊权限
//	 */
//	@PostMapping(value="/updateSpePower")
//   @ApiOperation(value = "更新用户超级权限")
//   public Result updateSpePower(@RequestBody JSONObject reqObject){
//		 boolean success = true;
//	        UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//
//	        String userCode = reqObject.get("userCode").toString();
//	        List<Map<String, Object>> spePowerList= (List<Map<String, Object>>) reqObject.get("spePowerList");
//	        if(reqObject==null||userCode==null||userCode.isEmpty()||spePowerList==null||spePowerList.size()==0){
//	            success=false;
//	            msg = UnicomResponseEnums.NULL_ARGUMENT;
//	            return  new Result(success, null, msg);
//	        }
//	        System.out.println(userCode);
//	        System.out.println(spePowerList);
//
//	        boolean flag = userPowerService.updateSpePower(userCode, spePowerList);
//	        Result result = new Result(success, flag, msg);
//	        return result;
//   }
	
	
//	/**
//	 * @description		查找用户特殊权限
//	 */
//	@PostMapping(value="/querySpePower")
//   @ApiOperation(value = "查找用户特殊权限")
//   public Result querySpePower(@RequestBody JSONObject reqObject){
//       boolean success = true;
//       UnicomResponseEnums msg = UnicomResponseEnums.SUCCESS_OPTION;
//       String userCode = reqObject.get("userCode").toString();
//       if(reqObject==null||userCode==null||userCode.isEmpty()){
//           success=false;
//           msg = UnicomResponseEnums.NULL_ARGUMENT;
//           return  new Result(success, null, msg);
//       }
//       List<SpePower> querySpeRole = userPowerService.querySpeRole(userCode);
//       Result result = new Result(success, querySpeRole, msg);
//       return result;
//   }
}
	

