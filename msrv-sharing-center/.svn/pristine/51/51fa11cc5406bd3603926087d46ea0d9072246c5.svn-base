package com.mhtech.platform.msrv.sharing.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mhtech.common.result.entity.CustomException;
import com.mhtech.common.result.entity.Result;
import com.mhtech.common.tool.PageUtilToSql;
import com.mhtech.platform.msrv.monitor.service.SpParamAlertService;
import com.mhtech.platform.msrv.pojo.SpParamAlert;
import com.mhtech.platform.msrv.pojo.SpUserShift;
import com.mobile.model.Page;
import com.mobile.utils.DateUtils;
import com.mobile.utils.StringUtils;

@Service("spParamAlertService")
//@Transactional(rollbackFor = {ClassCastException.class,Exception.class,CustomException.class, ParseException.class}) //事务回滚
public class SpParamAlertServiceImpl implements SpParamAlertService {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private NamedParameterJdbcTemplate jdbcParameter;
	
	@Value("${pageNo}")
	private int pageNo;
	
	@Value("${pageSize}")
	private int pageSize;
	
	@Value("${database.type}")
    private String databaseType;
	
	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.spParamAlertService#getList(java.util.Map)
	 * 查询告警规则设置
	 */
	@Override
	public Page getList(Map<String, Object> map) {
		String sql="select concat(a.id,'') id,concat(a.server_id,'') serverId,a.param_name paramName,"
				+ " a.param_alias paramAlias,a.alter_value alterValue,c.server_name serverName,"
				+ " a.alert_code alertCode,a.alert_type alertType,concat(a.status,'') status,b.alert_limit alertLimit,concat(b.level,'') level,"
				+ " b.contacts,GROUP_CONCAT(e.name) contactsName,DATE_FORMAT(b.stop_listen_start,'%Y-%m-%d %H:%i:%s') stopListenStart,"
				+ " DATE_FORMAT(b.stop_listen_end,'%Y-%m-%d %H:%i:%s') stopListenEnd,b.duration, "
				+ " b.notify_method notifyMethod,b.contacts_method contactsMethod,b.notify_status notifyStatus,"
				+ " a.server_type as serverType,d.parm_name as serverTypeName"
				+ " from sp_param_alert a"
				+ " left join sp_server_alert_rule b on a.id=b.id and a.server_id=b.server_id and a.param_name=b.param_name "
				+ " left join ("
				+ " select id,server_code,ip,server_name, '2' as server_type from sp_server_admin"
				+ " union select id,server_code,ip,server_name, '1' as server_type from sp_device_admin"
				+ " ) c on a.server_id=c.id "
				+ " left join sys_parameter d on a.server_type=d.parm_code and parm_type='serverType2'"
				+ " left join sp_user e on FIND_IN_SET(e.user_code,b.contacts)"
				+ " where 1=1 ";
		//告警名称
		if (map.get("paramAlias") != null && !map.get("paramAlias").equals("")) {
			sql+=" and a.param_alias like CONCAT('%',:paramAlias,'%')";
		}
		//告警对象
		/*if (map.get("serverName") != null && !map.get("serverName").equals("")) {
			sql+=" and c.server_name like '%"+map.get("serverName")+"%'";
		}*/
		//所属业务
		if (map.get("serverId") != null && !map.get("serverId").equals("")) {
			sql+=" and a.server_id = :serverId";
		}
		//告警级别
		if (map.get("level") != null && !map.get("level").equals("")) {
			sql+=" and b.level = :level";
		}
		//当前状态
		if (map.get("status") != null && !map.get("status").equals("")) {
			sql+=" and a.status = :status";
		}
		sql+=" GROUP BY b.id order by a.created_time desc";
		
		int pageNo = this.pageNo;
		int pageSize = this.pageSize;
		if (map.get("pageNo") != null && !map.get("pageNo").toString().isEmpty()) {
			pageNo = Integer.parseInt(map.get("pageNo").toString());
		}
		if (map.get("pageSize") != null && !map.get("pageSize").toString().isEmpty()) {
			pageSize = Integer.parseInt(map.get("pageSize").toString());
		}
		
		int customerPageCount = 0;
		try {
			List<Map<String, Object>> list=jdbcParameter.queryForList(sql,map);
			//customerPageCount = jdbc.queryForObject(sql, Integer.class);
			customerPageCount=list.size();
		} catch (Exception e) {
		}
		
		//新增----分页，传入页码控制
		if (pageNo <= 0) pageNo = this.pageNo;
		if (pageSize <= 0) pageSize = this.pageSize;	
		// 总页数
		
		int pageCount = customerPageCount % pageSize == 0 ? (customerPageCount / pageSize):(customerPageCount / pageSize + 1);
		// 当前页数大于总页数 将当前页数等于总页数
		if (pageNo > pageCount) {
				pageNo = pageCount;
		}
		
		Pageable pageable = new PageRequest(pageNo -1>0 ?pageNo -1:0, pageSize);
		String pageSql = PageUtilToSql.getPageSql(sql, pageable,databaseType);
		System.err.println(pageSql);
		List<Map<String, Object>> maps = jdbcParameter.queryForList(pageSql,map);
		for (int i = 0; i < maps.size(); i++) {
			Map<String, Object> map2 = maps.get(i);
			List<String> list=new ArrayList<String>();
			if (map2.get("contacts")!=null&&!StringUtils.isBlank(map2.get("contacts").toString())) {
				String contact=map2.get("contacts").toString();
				String contacts[]=contact.split(",");
				Collections.addAll(list, contacts);
			}
			map2.put("contactsList", list);
			maps.set(i, map2);
			
		}
		//List<Map<String, String>> list = Utilis.mapObjToString2(maps);
	
		Page page = new Page(customerPageCount);
		page.setData(maps);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.spParamAlertService#add(com.mhtech.platform.msrv.pojo.spParamAlert)
	 * 新增告警规则设置
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int add(SpParamAlert spParamAlert) {
		if (spParamAlert == null)
			return 0;
		String sql="insert into sp_param_alert(id,server_id,param_name,param_alias,alter_value,"
				+ "alert_code,alert_type,status,server_type,created_time"
				+ " )values("
				+ ":id,:serverId,:paramName,:paramAlias,:alterValue,:alertCode,:alertType,"
				+ ":status,:serverType,:createdTime)";
		String sql1="insert into sp_server_alert_rule(id,server_id,param_name,alert_limit,level,"
				+ " duration,created_time"
				+ " )values("
				+ ":id,:serverId,:paramName,:alertLimit,:level,:duration,:createdTime)";
		int flg=jdbcParameter.update(sql, new BeanPropertySqlParameterSource(spParamAlert));
		if (flg==0) {
			return 0;
		}else {
			return jdbcParameter.update(sql1, new BeanPropertySqlParameterSource(spParamAlert));
		}
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.spParamAlertService#modify(com.mhtech.platform.msrv.pojo.spParamAlert)
	 * 修改告警规则设置
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int modify(SpParamAlert spParamAlert) {
		String sqlWhere = " where id=:id" ;
		String sqlUpdate = "update sp_param_alert set " 
		+ "server_id=:serverId"
		+ ",param_name=:paramName"
		+ ",param_alias=:paramAlias"
		+ ",alter_value=:alterValue"
		+ ",alert_code=:alertCode"
		+ ",alert_type=:alertType"
		+ ",status=:status"
		+ ",server_type=:serverType";
						
		String sqlUpdate1 = "update sp_server_alert_rule set " 
				+ "server_id=:serverId"
				+ ",param_name=:paramName"
				+ ",alert_limit=:alertLimit"
				+ ",level=:level"
				+ ",duration=:duration";
		int flg=jdbcParameter.update(sqlUpdate + sqlWhere, new BeanPropertySqlParameterSource(spParamAlert));
		if (flg==0) {
			return 0;
		}else {
			return jdbcParameter.update(sqlUpdate1 + sqlWhere, new BeanPropertySqlParameterSource(spParamAlert));
		}
		
	}
	
	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.spParamAlertService#modify(com.mhtech.platform.msrv.pojo.spParamAlert)
	 * 修改告警规则设置
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int modifyRule(SpParamAlert spParamAlert) {
		
		String sqlWhere = " where id=:id" ;	
		String sqlUpdate = "update sp_server_alert_rule set " 
				+ "notify_method=:notifyMethod"
				+ ",contacts_method=:contactsMethod"
				+ ",notify_status=:notifyStatus"
				+ ",contacts=:contactArray"
				+ ",stop_listen_start=:stopListenStart"
				+ ",stop_listen_end=:stopListenEnd";
		
		return jdbcParameter.update(sqlUpdate + sqlWhere,new BeanPropertySqlParameterSource(spParamAlert));
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.spParamAlertService#delete(com.mhtech.platform.msrv.pojo.spParamAlert)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(SpParamAlert spParamAlert) {
		String sql="delete from sp_param_alert where id=:id";
		String sql1="delete from sp_server_alert_rule where id=:id";
		int flg=jdbcParameter.update(sql, new BeanPropertySqlParameterSource(spParamAlert));
		if (flg==0) {
			return 0;
		}else {
			return jdbcParameter.update(sql1, new BeanPropertySqlParameterSource(spParamAlert));
		}
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.spParamAlertService#getEntity(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getEntity(String id) {
		String sql="select concat(a.id,'') id,concat(a.server_id,'') serverId,a.param_name paramName,"
				+ " a.param_alias paramAlias,a.alter_value alterValue,c.server_name serverName,"
				+ " a.alert_code alertCode,a.alert_type alertType,a.status,b.alert_limit alertLimit,b.level,"
				+ " b.contacts,b.stop_listen_start stopListenStart,b.stop_listen_end stopListenEnd,b.duration,"
				+ " b.temp_stop_listen_start tempStoptimeStart,b.temp_stop_listen_end tempStoptimeEnd,"
				+ " a.server_type as serverType,d.parm_name as serverTypeName"
				+ " from sp_param_alert a"
				+ " left join sp_server_alert_rule b on a.id=b.id and a.server_id=b.server_id and a.param_name=b.param_name "
				+ " left join ("
				+ " select id,server_code,ip,server_name, '2' as server_type from sp_server_admin"
				+ " union select id,server_code,ip,server_name, '1' as server_type from sp_device_admin"
				+ " )  c on a.server_id=c.id "
				+ " left join sys_parameter d on a.server_type=d.parm_code and parm_type='serverType2'"
				+ " where 1=1"
				+ " and a.id=?";
		List<Map<String, Object>> spParamAlert = jdbc.queryForList(sql,id);// TODO 自动生成的方法存根
		return spParamAlert;
	}
	
	public JSONArray getChilds(JSONArray jsonArray,String value) {
		JSONArray re=new JSONArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject js=jsonArray.getJSONObject(i);
			if (js.get("parent")!=null&&!StringUtils.isBlank(js.getString("parent"))) {
				String parent=js.getString("parent");
				if (parent.equals(value)) {
					//js.remove("parent");
					re.add(js);
				}
			}
		}
		return re;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.SpParamAlertService#getAlertType()
	 */
	@Override
	public JSONArray getAlertType() {
		String sql="select type_code as value,type_alias as label,parent_code as parent from sp_param_alert_type";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		JSONArray re=new JSONArray();
		if (list.size()>0) {
			JSONArray jsonArray=(JSONArray) JSONArray.toJSON(list);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject js=jsonArray.getJSONObject(i);
				//System.err.println(js.toJSONString()+"=========="+js.get("parent"));
				if (js.get("parent")==null||StringUtils.isBlank(js.getString("parent"))) {	
					String value=js.getString("value");
					//js.remove("parent");
					js.put("children", getChilds(jsonArray,value));
					re.add(js);
				}
			}
		}
		return re;
	}

	/*//根据排班获取人员
	@Override
	public String getRangeUser(List<String> shitfId) {
		String shitfIds="";
		for (int i = 0; i < shitfId.size(); i++) {
			shitfIds+=",'"+shitfId.get(i)+"'";
		}
		String sql="select pcode from sp_personne where shift_id in ("+shitfIds.substring(1)+")";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		String pcodes="";
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				String pcode=list.get(i).get("pcode").toString();
				pcodes+=","+pcode;
			}
		}
		return pcodes.substring(1);
	}*/
	
	//根据排班日期和服务器获取人员
	@Override
	public JSONArray getRangeUser(String date,String ip) {
		String dayStart=DateUtils.formatDate(DateUtils.parseDate(date),"yyyy-MM-dd")+" 00:00:00";
		String dayEnd=DateUtils.addDateTime(dayStart, 0, 0, 1);
		String sql="select a.user_code as userCode,b.name as userName from sp_user_shift a "
				+ " left join sp_user b on a.user_code=b.user_code"
				+ " where ip =?"
				+ " and b.name is not null"
				+ " and a.status=1 "
				+ " and shift_time>=DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s')"
				+ " and shift_time<DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s')";
		System.err.println(sql);
		List<Map<String, Object>> list = jdbc.queryForList(sql,ip,dayStart,dayEnd);
		JSONArray re=new JSONArray();
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				//JSONObject js=new JSONObject();
				String userCode=list.get(i).get("userCode").toString();
				//String userName=list.get(i).get("userName").toString();
				//js.put("userCode", userCode);
				//js.put("userName", userName);
				re.add(userCode);
			}
		}
		return re;
	}
	
	//根据服务器获取人员
	//@Override
	public String getRangeUserByIp(String ip) {
		String sql="select distinct a.user_code as userCode from sp_user_shift a "
				+ " left join sp_user b on a.user_code=b.user_code"
				+ " where ip =?"
				+ " and b.name is not null"
				+ " and a.status=1 ";
		List<Map<String, Object>> list = jdbc.queryForList(sql,ip);
		String userCodes="";
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				String userCode=list.get(i).get("userCode").toString();
				userCodes+=","+userCode;
			}
		}
		return userCodes.substring(1);	
	}
	
	//根据服务器获取排班日期
	@Override
	public JSONArray getRangeByIp(String ip) {
		String sql="select distinct DATE_FORMAT(a.shift_time,'%Y-%m-%d') as shiftTime from sp_user_shift a "
				+ " where ip =?"
				+ " and a.status=1 ";
		List<Map<String, Object>> list = jdbc.queryForList(sql,ip);
		JSONArray shiftTimes=new JSONArray();
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				String shiftTime=list.get(i).get("shiftTime").toString();
				//shiftTimes.add(shiftTime.substring(0, shiftTime.indexOf(".")));
				shiftTimes.add(shiftTime);
			}
		}
		return shiftTimes;	
	}
		
	//根据id和serverType获取服务器ip
	@Override
	public String getIpByServerType(String id,String serverType) throws CustomException {
		String tableName="";
		if (serverType.equals("1")) {//硬件
			tableName="sp_device_admin";
		}else if (serverType.equals("2")){//软件
			tableName="sp_server_admin";
		}else {//服务器种类错误
			throw new CustomException("0000", "服务器种类错误，请联系管理员！");
		}
		String sql="select ip from sp_param_alert a"
				+ " left join "+tableName +" b on a.server_id=b.id " 
				+ " where a.id=:id";
		System.err.println("getIpByServerType:"+sql);
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		List<Map<String, Object>> list = jdbcParameter.queryForList(sql,map);
		String ip="";
		if (list.size()>0) {
			ip=list.get(0).get("ip").toString();
		}
		return ip;	
	}
		
	//获取排班列表
	/*@Override
	public List<Map<String, Object>> getRange() {
		String sql="select shiftId code,CONCAT(shift,'(',start_time,'~',end_time,')') value from sp_shift  ";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		return list;
	}*/
	
	//获取告警原因统计数据
	@Override
	public List<Map<String, Object>> getAlermReason(String id) {
		String sql="select a.error_code code,a.error_msg msg,CASE COUNT(b.id) when 0 then null else COUNT(b.id) end count from sp_biz_err_type a "
				+" left join sp_alert_log b on a.error_code=b.error_code and  b.alert_id=?" 
				+" left join sp_server_notify_log c on b.notify_id=c.id and c.id is not null "
				+" GROUP BY a.error_code,a.error_msg ";
		List<Map<String, Object>> list = jdbc.queryForList(sql,id);
		return list;
	}
	
	//获取告警处理结果
	@Override
	public List<Map<String, Object>> getAlermDeal(String id) {
		String sql="select a.parm_code,a.parm_name,CASE COUNT(b.id) when 0 then null else COUNT(b.id) end  count from sys_parameter a "
				+" left join sp_server_notify_log b on a.parm_code=b.status and b.alert_id=? "
				+" where parm_type='alarmStatus' "
				+" GROUP BY a.parm_code,a.parm_name ";
		List<Map<String, Object>> list = jdbc.queryForList(sql,id);
		return list;
	}

	//获取告警历史统计数据
	@Override
	public int getAlermHistroy(String id,String startTime,String endTime) {
		String sql="select * from sp_server_notify_log a  "
				+" where a.alert_id=?"
				+" and created_time >= DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s') "
				+" and created_time < DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s') ";
		List<Map<String, Object>> list = jdbc.queryForList(sql,id,startTime,endTime);
		return list.size();
	}
	
	//id找到对应告警规则，修改临时暂停时间
	@Override
	public int changeTempStopListenTime(String id,String startTime,String endTime) {
		String sqlWhere = " where id=:id" ;	
		String sqlUpdate = "update sp_server_alert_rule set " 
				+ "temp_stop_listen_start=:startTime"
				+ ",temp_stop_listen_end=:endTime";
		Map<String, Object> map =new HashMap<>();
		map.put("id", id);
		map.put("startTime",startTime);
		map.put("endTime", endTime);
		return jdbcParameter.update(sqlUpdate + sqlWhere,map);
	}
	
	
	/**
	 * TODO 删除排班人员信息  逻辑删除
	 * @return 
	 */
	@Override
	//@Transactional(rollbackFor = {ClassCastException.class,Exception.class,CustomException.class, ParseException.class}) //事务回滚
	public int delShiftUser(SpUserShift spUserShift) {
		String sql="update sp_user_shift set status='0'";
		String sqlPlus=" where 1=1";
		if (spUserShift.getUserCode()!=null&&!StringUtils.isBlank(spUserShift.getUserCode())) {
			sqlPlus+=" and user_code=:userCode()";
		}
		if (spUserShift.getIp()!=null&&!StringUtils.isBlank(spUserShift.getIp())) {
			sqlPlus+=" and ip=:ip()";
		}
		if (spUserShift.getShiftTime()!=null&&!StringUtils.isBlank(spUserShift.getShiftTime())) {
			sqlPlus+=" and shift_time=:shiftTime()";
		}
		if (spUserShift.getStatus()!=null&&!StringUtils.isBlank(spUserShift.getStatus())) {
			sqlPlus+=" and status=:status()";
		}
		if (spUserShift.getUpdateTime()!=null&&!StringUtils.isBlank(spUserShift.getUpdateTime())) {
			sqlPlus+=" and update_time=:updateTime()";
		}
		if (spUserShift.getId()!=null&&!StringUtils.isBlank(spUserShift.getId()+"")) {
			sqlPlus+=" and id=:id";
		}
		return jdbcParameter.update(sql+sqlPlus,new BeanPropertySqlParameterSource(spUserShift));
	}
	
	/**
	 * TODO 保存排班人员信息
	 * @return 
	 */
	@Override
	//@Transactional(rollbackFor = {ClassCastException.class,Exception.class,CustomException.class, ParseException.class}) //事务回滚
	public int saveShiftUser(SpUserShift spUserShift) {
		// 保存排班人员信息
		String sql="insert into sp_user_shift(id,user_code,ip,shift_time,status,update_time"
				+ ")values(:id,:userCode,:ip,:shiftTime,:status,:updateTime)";
		return jdbcParameter.update(sql,new BeanPropertySqlParameterSource(spUserShift));
	}
	
	/**
	 * TODO 更新排班人员信息
	 * @return 
	 */
	@Override
	//@Transactional(rollbackFor = {ClassCastException.class,Exception.class,CustomException.class, ParseException.class}) //事务回滚
	public int updateShiftUser(SpUserShift spUserShift) {
		// 保存排班人员信息
		String sql="update sp_user_shift set id=id";
		String sqlPlus="";
		if (spUserShift.getUserCode()!=null&&!StringUtils.isBlank(spUserShift.getUserCode())) {
			sqlPlus+=",user_code=:userCode";
		}
		if (spUserShift.getIp()!=null&&!StringUtils.isBlank(spUserShift.getIp())) {
			sqlPlus+=",ip=:ip";
		}
		if (spUserShift.getShiftTime()!=null&&!StringUtils.isBlank(spUserShift.getShiftTime())) {
			sqlPlus+=",shift_time=:shiftTime";
		}
		if (spUserShift.getStatus()!=null&&!StringUtils.isBlank(spUserShift.getStatus())) {
			sqlPlus+=",status=:status";
		}
		if (spUserShift.getUpdateTime()!=null&&!StringUtils.isBlank(spUserShift.getUpdateTime())) {
			sqlPlus+=",update_time=:updateTime";
		}
		if (spUserShift.getId()!=null&&!StringUtils.isBlank(spUserShift.getId()+"")) {
			sqlPlus+=" where id=:id";
		}
		return jdbcParameter.update(sql+sqlPlus,new BeanPropertySqlParameterSource(spUserShift));
	}

	@Override
	public void downloadExcel(HttpServletResponse response,HttpServletRequest request) {
        try {
            //获取文件的路径
            String excelPath = request.getSession().getServletContext().getRealPath("/Excel/"+"模板.xlsl");
            String fileName = "模板.xlsl".toString(); // 文件的默认保存名
            // 读到流中
            InputStream inStream = new FileInputStream(excelPath);//文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("模板.xlsl", "UTF-8"));
            // 循环取出流中的数据
            byte[] b = new byte[200];
            int len;
 
            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.SpParamAlertService#deleteByIds(java.util.List)
	 */
	@Override
	public int deleteByIds(List<String> idList) {
//		String idString="";
//		for(int i=0;i<idList.size();i++) {
//			idString+="'"+idList.get(i)+"',";
//		}
		String sql="delete sp_param_alert,sp_server_alert_rule from sp_param_alert left join sp_server_alert_rule on sp_param_alert.id=sp_server_alert_rule.id\r\n" + 
				"where sp_param_alert.id in (:idList)";
		System.err.println(sql);
		Map<String, Object> args  = new HashMap<>();
		args.put("idList", idList);
		int flg=jdbcParameter.update(sql,args);
		return flg;
	}
		
}
