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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mhtech.common.tool.PageUtilToSql;
import com.mhtech.common.tool.Utilis;
import com.mhtech.platform.msrv.monitor.service.SpAlertModelService;
import com.mhtech.platform.msrv.monitor.service.SpParamAlertService;
import com.mhtech.platform.msrv.pojo.SpAlertModel;
import com.mhtech.platform.msrv.pojo.SpParamAlert;
import com.mobile.model.Page;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Service("spAlertModelService")
public class SpAlertModelServiceImpl implements SpAlertModelService {
	
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
	 * @see com.mhtech.platform.msrv.monitor.service.spAlertModelService#getList(java.util.Map)
	 * 查询告警规则设置
	 */
	@Override
	public Page getList(Map<String, Object> map) {
		String sql="select id,model_name,param_name,alter_value,alert_limit,level,duration"
				+ " from sp_alert_model "
				+ " where 1=1";
		sql+=" order by id";
		
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
			List<Map<String, Object>> list=jdbc.queryForList(sql);
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
		List<Map<String, Object>> maps = jdbc.queryForList(pageSql);

		//List<Map<String, String>> list = Utilis.mapObjToString2(maps);
	
		Page page = new Page(customerPageCount);
		page.setData(maps);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.spAlertModelService#add(com.mhtech.platform.msrv.pojo.spAlertModel)
	 * 新增告警规则设置
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int add(SpAlertModel spAlertModel) {
		if (spAlertModel == null)
			return 0;
		String sql="insert into sp_alert_model(id,model_name,param_name,alter_value,alert_limit,level,duration"
				+ " )values(:id,:modelName,:paramName,:alterValue,:alertLimit,:level,:duration)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(spAlertModel);
		return jdbcParameter.update(sql,param);
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.spAlertModelService#modify(com.mhtech.platform.msrv.pojo.spAlertModel)
	 * 修改告警规则设置
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int modify(SpAlertModel spAlertModel) {
		String sqlWhere = " where id=:id";
//		String sqlUpdate = "update sp_alert_model set " 
//				+ "model_name=" + spAlertModel.getModelName() + ""
//				+ ",param_name='" + spAlertModel.getParamName() + "'"
//				+ ",alert_limit='" + spAlertModel.getAlertLimit() + "'"
//				+ ",level='" + spAlertModel.getLevel() + "'"
//				+ ",alter_value=" + spAlertModel.getAlterValue() + ""
//				+ ",duration='" + spAlertModel.getDuration() + "'";
		String sqlUpdate = "update sp_alert_model set " 
				+ "model_name=:modelName "
				+ ",param_name=:paramName "
				+ ",alert_limit=:alertLimit"
				+ ",level=:level"
				+ ",alter_value=:alterValue"
				+ ",duration=:duration";
		SqlParameterSource param = new BeanPropertySqlParameterSource(spAlertModel);
		return jdbcParameter.update(sqlUpdate + sqlWhere,param);
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.spAlertModelService#delete(com.mhtech.platform.msrv.pojo.spAlertModel)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(SpAlertModel spAlertModel) {
		String sql="delete from sp_param_alert where id=?";
		return jdbc.update(sql,spAlertModel.getId());
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.spAlertModelService#getEntity(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getEntity(String id) {
		String sql="select id,model_name modelName,param_name paramName,alter_value alterValue,alert_limit alertLimit,level,duration"
				+ " from sp_alert_model "
				+ " where 1=1"
				+ " and id=?";
		List<Map<String, Object>> spAlertModel = jdbc.queryForList(sql,id);// TODO 自动生成的方法存根
		return spAlertModel;
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.SpAlertModelService#getModelList(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getModelList(String modelName) {
		String sql="select id,model_name"
				+ " from sp_alert_model "
				+ " where 1=1"
				+ " and model_name like ?";
		//List<Map<String, Object>> spAlertModel = jdbc.queryForList(sql);// TODO 自动生成的方法存根
		List<Map<String, Object>> spAlertModel =jdbc.queryForList(sql, "%"+modelName+"%");
		return spAlertModel;
	}

	
	
	
}
