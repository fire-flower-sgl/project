package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Service;

import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.mhtech.common.tool.PageUtilToSql;
import com.mhtech.common.tool.Utilis;
import com.mhtech.platform.msrv.monitor.service.SpAlertNotifyTmplService;
import com.mhtech.platform.msrv.pojo.SpAlertNotifyTmpl;
import com.mobile.model.Page;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Service("spAlertNotifyTmplService")
public class SpAlertNotifyTmplServiceImpl implements SpAlertNotifyTmplService {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Value("${pageNo}")
	private int pageNo;
	
	@Value("${pageSize}")
	private int pageSize;
	
	@Value("${database.type}")
    private String databaseType;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcParameter;
	
	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.SpAlertNotifyTmplService#getList(java.util.Map)
	 * 查询告警通知模板
	 */
	@Override
	public Page getList(Map<String, Object> map) {
		String sql="select CONCAT(a.id,'') id,CONCAT(a.server_id,'') serverId,ifnull(b.server_name,'') serverName,a.tmpl_name tmplName,a.tmpl_code tmplCode,"
				+" a.tmpl_conent tmplConent,a.status||'' status,a.ext_1 ext1,a.ext_2 ext2,a.ext_3 ext3,b.server_type as serverType,"
				+" a.ext_4 ext4,a.ext_5 ext5,a.ext_6 ext6,a.ext_7 ext7 from sp_alert_notify_tmpl a"
				+" left join ("
				+" select id,server_code,ip,server_name, '2' as server_type from sp_server_admin"
				+" union select id,server_code,ip,server_name, '1' as server_type from sp_device_admin"
				+" ) b on a.server_id=b.id "
				+" where 1=1";
		if (map.get("tmplCode") != null && !map.get("tmplCode").equals("")) {
			sql+=" and tmpl_code like CONCAT('%',:tmplCode,'%')";
		}
		if (map.get("tmplName") != null && !map.get("tmplName").equals("")) {
			sql+=" and tmpl_name like CONCAT('%',:tmplName,'%')";
		}
		if (map.get("tmplConent") != null && !map.get("tmplConent").equals("")) {
			sql+=" and tmpl_conent like CONCAT('%',:tmplConent,'%')";
		}
		if (map.get("status") != null && !map.get("status").equals("")) {
			sql+=" and status =:status";
		}
		sql+=" order by a.id";
		
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

		//List<Map<String, String>> list = Utilis.mapObjToString2(maps);
	
		Page page = new Page(customerPageCount);
		page.setData(maps);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.SpAlertNotifyTmplService#add(com.mhtech.platform.msrv.pojo.SpAlertNotifyTmpl)
	 * 新增告警通知模板
	 */
	@Override
	public int add(SpAlertNotifyTmpl spAlertNotifyTmpl) {
		if (spAlertNotifyTmpl == null)
			return 0;
		String sql="insert into sp_alert_notify_tmpl(id,server_id,tmpl_name,tmpl_code,"
				+ " tmpl_conent,status,ext_1,ext_2,ext_3,ext_4,ext_5,ext_6,ext_7)"
				+ " values(:Id,:serverId,:tmplName,:tmplCode,:tmplConent,:status,"
				+ " :ext1,:ext2,:ext3,:ext4,:ext5,:ext6,:ext7)";
		return jdbcParameter.update(sql,new BeanPropertySqlParameterSource(spAlertNotifyTmpl));
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.SpAlertNotifyTmplService#modify(com.mhtech.platform.msrv.pojo.SpAlertNotifyTmpl)
	 * 修改告警通知模板
	 */
	@Override
	public int modify(SpAlertNotifyTmpl spAlertNotifyTmpl) {
		
		String sqlWhere = " where id=:id";
		String sqlUpdate = "update sp_alert_notify_tmpl set " 
		+ "server_id=:serverId"
		+ ",tmpl_name=:tmplName"
		+ ",tmpl_code=:tmplCode"
		+ ",tmpl_conent=:tmplConent"
		+ ",status=:status"
		+ ",ext_1=:ext1"
		+ ",ext_2=:ext2"
		+ ",ext_3=:ext3"
		+ ",ext_4=:ext4"
		+ ",ext_5=:ext5"
		+ ",ext_6=:ext6"
		+ ",ext_7=:ext7";
		return jdbcParameter.update(sqlUpdate + sqlWhere,new BeanPropertySqlParameterSource(spAlertNotifyTmpl));
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.SpAlertNotifyTmplService#delete(com.mhtech.platform.msrv.pojo.SpAlertNotifyTmpl)
	 */
	@Override
	public int delete(SpAlertNotifyTmpl spAlertNotifyTmpl) {
		String sql="delete from sp_alert_notify_tmpl where id=?";
		return jdbc.update(sql,spAlertNotifyTmpl.getId());
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.SpAlertNotifyTmplService#getEntity(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getEntity(String id) {
		String sql="select a.id,a.server_id serverId,ifnull(b.server_name,'') serverName,a.tmpl_name tmplName,a.tmpl_code tmplCode,"
				+" a.tmpl_conent tmplConent,a.status||'' status,a.ext_1 ext1,a.ext_2 ext2,a.ext_3 ext3,"
				+" a.ext_4 ext4,a.ext_5 ext5,a.ext_6 ext6,a.ext_7 ext7 from sp_alert_notify_tmpl a"
				+" left join sp_server_admin b on a.server_id=b.id "
				+" where a.id=?";
		List<Map<String, Object>> spAlertNotifyTmpl = jdbc.queryForList(sql,id);// TODO 自动生成的方法存根
		return spAlertNotifyTmpl;
	}

	
	
	
}
