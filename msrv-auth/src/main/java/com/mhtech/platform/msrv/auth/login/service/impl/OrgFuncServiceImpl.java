package com.mhtech.platform.msrv.auth.login.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.auth.login.service.OrgFuncService;
import com.mhtech.platform.msrv.auth.utils.PageUtilToSql;
import com.mobile.model.Page;
import com.mobile.utils.Utilis;

/**
 * 
 * @ClassName: OrganizatImpl
 * @Description:组织管理实现类
 * @author: admin_
 * @date: 2019 10:29:45
 */
//@Repository
@Service("loginOrgFuncService")
public class OrgFuncServiceImpl implements OrgFuncService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	PageUtilToSql pageUtilToSql;


	@Value("${pageNo}")
	private int pageNo;
	@Value("${pageSize}")
	private int pageSize;

	@Override
	public Page findPage(JSONObject object) {
		String sql = "SELECT a.id,a.func_code funcCode,a.func_name funcName, b.company_name companyName ,c.parm_name rank,a.rank_title rankTitle,a.sort,a.created_time createdTime,a.updated_time updatedTime  from sp_org_func_info a LEFT JOIN sp_company_info b on a.company_code = b.company_code LEFT JOIN sys_parameter_copy c on c.parm_code = a.rank and c.parm_status = '1' where c.parm_type = 'rank' and a.is_delete = 0 ";
		String sql_count = "select count(1) from sp_org_func_info a LEFT JOIN sp_company_info b on a.company_code = b.company_code LEFT JOIN sys_parameter_copy c on c.parm_code = a.rank and c.parm_status = '1' where c.parm_type = 'rank' and a.is_delete = 0 ";
		String sql_where = "";
		
		if(object!=null) {
			if (object.get("funcCode") != null && !object.get("funcCode").toString().isEmpty()){
				String funcCode = object.get("funcCode").toString();
				sql_where = " and a.func_code like '%"+funcCode+"%'";
			}
			if (object.get("funcName") != null && !object.get("funcName").toString().isEmpty()){
				String funcName = object.get("funcName").toString();
				sql_where = " and a.func_name like '%"+funcName+"%'";
			}
			if (object.get("companyName") != null && !object.get("companyName").toString().isEmpty()){
				String companyName = object.get("companyName").toString();
				sql_where = " and b.company_name like '%"+companyName+"%'";
			}
			if (object.get("rank") != null && !object.get("rank").toString().isEmpty()){
				String rank = object.get("rank").toString();
				sql_where = " and c.parm_name like '%"+rank+"%'";
			}
			if (object.get("startTime") != null && !object.get("startTime").toString().isEmpty()&&object.get("endTime") != null && !object.get("endTime").toString().isEmpty()){
				String startTime = object.get("startTime").toString();
				String endTime = object.get("endTime").toString();
				sql_where = " and a.created_time >= '"+startTime+"' and a.created_time <='"+endTime+"'";
			}
		}
		
		int pageNo = this.pageNo;
		int pageSize = this.pageSize;
		if (object.get("pageNo") != null && ""!=object.get("pageNo")) {
			pageNo = Integer.parseInt(object.get("pageNo").toString());
		}
		if (object.get("pageSize") != null && ""!=object.get("pageSize")) {
			pageSize = Integer.parseInt(object.get("pageSize").toString());
		}
		Pageable pageable = new PageRequest(pageNo - 1, pageSize);
		String pageSql = pageUtilToSql.getPageSql(sql + sql_where + " ORDER BY a.sort,a.created_time desc ", pageable);
		System.err.println("组织职能=="+pageSql);
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(pageSql);
		List<Map<String, String>> list = Utilis.mapObjToString2(maps);

		int customerPageCount = findCount(sql_count + sql_where);
		Page page = new Page(customerPageCount);
		page.setData(list);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}
	
	@Override
	public int findCount(String sql_count) {
		return jdbcTemplate.queryForObject(sql_count, Integer.class);
	}


	@Override
	public boolean add(JSONObject reqObject) {
		String funcCode = reqObject.get("funcCode").toString();
		String funcName = reqObject.get("funcName").toString();
		String companyId = reqObject.get("companyCode").toString();
		Object rank = reqObject.get("rank");
		Object rankTitle = reqObject.get("rankTitle");
		String sort = reqObject.get("sort").toString();
		String sql = "insert into sp_org_func_info values(?,?,?,?,?,?,?,0,now(),null)";
		int update = jdbcTemplate.update(sql,Utilis.getUUID(),funcCode,funcName,companyId,rank,rankTitle,sort);
		return update>0;
	}

	@Override
	public int delByOrgCode(String funcCode) {
		System.err.println("funcCode===="+funcCode);
		String sql = "SELECT * from sp_user_func_relation where func_code = '"+funcCode+"'  and is_delete = '0'";
		List<Map<String, Object>>  queryForMap = null;;
		try {
			queryForMap = jdbcTemplate.queryForList(sql);
		} catch (DataAccessException e) {
			queryForMap = null;
		}
		if(queryForMap.size()>0) {
			return 2;
		}
		//删除单表
		sql = "update sp_org_func_info set is_delete = 1 where func_code = ? and is_delete = 0";
		return jdbcTemplate.update(sql,funcCode);
	}

	@Override
	public List<Map<String, Object>> queryById(String orgCode) {
		String sql = "SELECT * from sp_org_info where is_delete = 0 and parent_org_code = ?";
		return jdbcTemplate.queryForList(sql,orgCode);
	}

	@Override
	public Map<String, Object> queryByOrgCode(String funcCode) {
		String sql = "SELECT a.id,a.func_code funcCode,a.func_name funcName, b.company_name companyName ,c.parm_name rank,a.rank_title rankTitle,a.sort,a.created_time createdTime,a.updated_time updatedTime  from sp_org_func_info a LEFT JOIN sp_company_info b on a.company_code = b.company_code LEFT JOIN sys_parameter_copy c on c.parm_code = a.rank and c.parm_status = '1' where c.parm_type = 'rank' and a.is_delete = 0  and func_code = ?";
		Map<String, Object>  queryForMap = null;;
		try {
			queryForMap = jdbcTemplate.queryForMap(sql,funcCode);
		} catch (DataAccessException e) {
			queryForMap = null;
		}
		return queryForMap;
	}

	@Override
	public boolean updateByid(JSONObject reqObject) {
		String funcCode = reqObject.get("funcCode").toString();
		String funcName = reqObject.get("funcName").toString();
		String companyId = reqObject.get("companyCode").toString();
		Object rank = reqObject.get("rank");
		Object rankTitle = reqObject.get("rankTitle");
		String sort = reqObject.get("sort").toString();
		String sql = "update sp_org_func_info set func_name = ?,company_code = ?,rank = ? ,rank_title = ?,sort = ?,updated_time = now() where func_code = ?";
		int update = jdbcTemplate.update(sql,funcName,companyId,rank,rankTitle,sort,funcCode);
		return update>0;

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String updateAllByid(List<String> funcCodeList) {
		int sum = 0;
		for (String funcCode : funcCodeList) {
			try {
				//删除
				int update = delByOrgCode(funcCode);
				sum+=update;
			} catch (Exception e) {
				return funcCode+":系统错误, 删除失败";
			}
		}
		return sum+"";
	}

	@Override
	public List<Map<String, Object>> queryCompanyList() {
		String sql="SELECT company_code companyCode, company_name companyName from sp_company_info";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> queryFuncList(JSONObject object) {
		String sql="SELECT a.func_code funcCode,a.func_name funcName ,a.company_code companyCode,b.company_name companyName from sp_org_func_info a LEFT JOIN sp_company_info b ON a.company_code = b.company_code where a.is_delete = 0 ";
		String sql_where = "";
		if (object.containsKey("companyCode") && !object.get("companyCode").toString().isEmpty()){
			String companyCode = object.get("companyCode").toString();
			sql_where = " and a.company_code = '"+companyCode+"'";
		}
		if (object.containsKey("companyName") && !object.get("companyName").toString().isEmpty()){
			String companyName = object.get("companyName").toString();
			sql_where = " and b.company_name = '"+companyName+"'";
		}
		return jdbcTemplate.queryForList(sql+sql_where);
	}

	@Override
	public List<Map<String, Object>> queryFuncList(String companyCode) {
		String sql="SELECT a.func_code funcCode,a.func_name funcName ,a.company_code companyCode,b.company_name companyName from sp_org_func_info a "
				+ "LEFT JOIN sp_company_info b ON a.company_code = b.company_code where a.is_delete = 0 and a.company_code = ?";
		return jdbcTemplate.queryForList(sql,companyCode);
	}

	@Override
	public List<Map<String, Object>> FuncListByUserCode(String userCode) {
		String sql = "SELECT a.user_code userCode,a.func_code funcCode from sp_user_func_relation a LEFT JOIN sp_org_func_info b on a.func_code = b.func_code where a.is_delete = 0 and b.is_delete = 0 and a.user_code = ? ";
		return jdbcTemplate.queryForList(sql,userCode);
	}

}
