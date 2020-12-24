package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.mhtech.common.tool.PageUtilToSql;
import com.mhtech.common.tool.Utilis;
import com.mhtech.platform.msrv.monitor.service.ISysParameterService;
import com.mhtech.platform.msrv.sharing.dao.mapper.SysParameterMapper;
import com.mobile.model.Page;
import com.mobile.model.SysParameter;
import com.mobile.utils.DateUtils;

@Service("iSysParameterService")
public class SysParameterServiceImpl implements ISysParameterService {

	@Autowired
	private SysParameterMapper sysParameterMapper;

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.ISysParameterService#findParameterByParmType(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> findParameterByParmType(String parmType) {
		List<Map<String, Object>> list=sysParameterMapper.findParameterByParmType(parmType);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findParameterByParmCodeOrParmName(String parmType,String parmCode,String parmName) {
		List<Map<String, Object>> list=sysParameterMapper.findParameterByParmCodeOrParmName(parmType,parmCode,parmName);
		return list;
	}

	@Value("${pageNo}")
	private int pageNo;
	
	@Value("${pageSize}")
	private int pageSize;
	
//	@Autowired
//	PageUtilToSql pageUtilToSql;

	@Value("${database.type}")
    private String databaseType;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcParameter;
	
	//sql语句不要带 空格
	@Override
	public int add(SysParameter sysParameter) {
		String sql=" INSERT INTO sys_parameter"
				   + " (id,parm_type,parm_desc,parm_code,parm_name,parm_status,"
				   + "parm_update_time,parm_parent,parm_func,parm_num ,parm_isuse)"
				   + "VALUES ('"+Utilis.getUUID()+"',:parmType,:parmDesc,:parmCode(),"
				   + ":parmName,:parmStatus,'"+ DateUtils.getDateTime() +"',:parmParent,"
				   + ":parmFunc,:parmNum,0) ";
		return jdbcParameter.update(sql,new BeanPropertySqlParameterSource(sysParameter));
	}

	
	@Override
	public int delete(String id) {
		String sql =" delete from sys_parameter where id =?";
		return jdbcTemplate.update(sql,id);
	}


	@Transactional
	@Override
	public int update(SysParameter sysParameter) {	
		String sql="update sys_parameter set parm_type=?,parm_desc=?,parm_code=?,parm_name=?,parm_status=?,parm_parent=?,parm_func=?,parm_num=? where id = ? ";
		
		int num= jdbcTemplate.update(sql,sysParameter.getParmType(),sysParameter.getParmDesc(),sysParameter.getParmCode(),sysParameter.getParmName()
				 ,sysParameter.getParmStatus(),sysParameter.getParmParent(),sysParameter.getParmFunc(),sysParameter.getParmNum()
				 ,sysParameter.getId());
		
		//更改对应类型的所有时间
		try {
			String sql2=" update sys_parameter set  parm_update_time ='"+DateUtils.getDateTime()+"' where parm_type=? ";
			jdbcTemplate.update(sql2,sysParameter.getParmType());
		} catch (DataAccessException e) {
			//更改对应类型的所有时间出错
			e.printStackTrace();
		}
		return num;
	}


	

	//分页条件查询
	@Override 
	public Page findAllPage(Map<String, String> map) {
	
		String sql=" SELECT id,parm_type parmType ,parm_desc parmDesc ,parm_code  parmCode,"
				  + "parm_name parmName,parm_status parmStatus ,parm_update_time parmUpdateTime,"
				  + "parm_parent parmParent,parm_func parmFunc,parm_num parmNum ,parm_isuse  parmIsuse"
				  + " FROM `sys_parameter` where 1=1 ";
		String sql_count = "select count(1) from sys_parameter  where 1=1";
		String sql_where = "";
		
		if (map.get("id") != null && !map.get("id").isEmpty()) {
			sql_where = sql_where + " and id =:id ";
		}
		//参数种类-模糊查询
		if (map.get("parmType") != null && !map.get("parmType").isEmpty()) {
			sql_where = sql_where + " and parm_type like CONCAT('%',:parmType,'%') ";
		}
		if (map.get("parmDesc") != null && !map.get("parmDesc").isEmpty()) {
			sql_where = sql_where + " and parm_desc = :parmDesc ";
		}
		if (map.get("parmCode") != null && !map.get("parmCode").isEmpty()) {
			sql_where = sql_where + " and parm_code = :parmCode ";
		}
		//参数名称-模糊查询
		if (map.get("parmName") != null && !map.get("parmName").isEmpty()) {
			sql_where = sql_where + " and parm_name like CONCAT('%',:parmName,'%')";
		}
		if (map.get("parmStatus") != null && !map.get("parmStatus").isEmpty()) {
			sql_where = sql_where + " and parm_status =:parmStatus ";
		}
		if (map.get("parmUpdateTime") != null && !map.get("parmUpdateTime").isEmpty()) {
			sql_where = sql_where + " and parm_update_time = :parmUpdateTime ";
		}
		if (map.get("parmParent") != null && !map.get("parmParent").isEmpty()) {
			sql_where = sql_where + " and parm_parent = :parmParent ";
		}
		//参数所属业务-模糊查询
		if (map.get("parmFunc") != null && !map.get("parmFunc").isEmpty()) {
			sql_where = sql_where + " and parm_func like CONCAT('%',:parmFunc,'%')";
		}
		if (map.get("parmNum") != null && !map.get("parmNum").isEmpty()) {
			sql_where = sql_where + " and parm_num =:parmNum ";
		}
		if (map.get("parmIsuse") != null && !map.get("parmIsuse").isEmpty()) {
			sql_where = sql_where + " and parm_isuse = :parmIsuse ";
		}
		int pageNo = this.pageNo;
		int pageSize = this.pageSize;
		if (map.get("pageNo") != null && !map.get("pageNo").isEmpty()) {
			pageNo = Integer.parseInt(map.get("pageNo"));
		}
		if (map.get("pageSize") != null && !map.get("pageSize").isEmpty()) {
			pageSize = Integer.parseInt(map.get("pageSize"));
		}
			
		//新增----分页，传入页码控制
		if (pageNo <= 0) pageNo = this.pageNo;
		if (pageSize <= 0) pageSize = this.pageSize;	
		// 总页数
		//int customerPageCount = findPageCount(sql_count + sql_where);
		int customerPageCount =jdbcParameter.queryForObject(sql_count+ sql_where,map, Integer.class);
		int pageCount = customerPageCount % pageSize == 0 ? (customerPageCount / pageSize):(customerPageCount / pageSize + 1);
		// 当前页数大于总页数 将当前页数等于总页数
		if (pageNo > pageCount) {
				pageNo = pageCount;
		}
	
		Pageable pageable = new PageRequest(pageNo -1>0 ?pageNo -1:0, pageSize);	
		String pageSql = PageUtilToSql.getPageSql(sql + sql_where + " order by  parm_update_time desc  ", pageable,databaseType);
		List<Map<String, Object>> maps = jdbcParameter.queryForList(pageSql,map);
		List<Map<String, String>> list = Utilis.mapObjToString2(maps);
		Page page = new Page(customerPageCount);
		page.setData(list);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	@Override
	public int findPageCount(String sql_count) {
		return jdbcTemplate.queryForObject(sql_count, Integer.class);
	}


	@Override
	public boolean findTypeCode(String parmType, String parmCode) {
		  String sql="  SELECT COUNT(*) from sys_parameter where  parm_type=:parmType and parm_code=:parmCode ";
		  Map<String, Object> map=new HashMap<String, Object>();
		  map.put("parmType", parmType);
		  map.put("parmCode", parmCode);
		  int sum = jdbcParameter.queryForObject(sql,map, Integer.class);
		  if (sum>0) {
		    	//存在
		    	return true;
		   }else {
				//不存在
			    return false;	
	      }	
	}

	
    //依据id查询sysParameter对象
	@Override
	public SysParameter fingSysParameter(String id) {
		String sql=" SELECT * from sys_parameter where id=:id";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		SysParameter sysParameter=jdbcParameter.queryForObject(sql,map, SysParameter.class);
		return sysParameter;
	}

	
	//取参数字典对应业务参数
	@Override
	public List<SysParameter> fingList(String parm_func) {
		String sql=" SELECT * from sys_parameter  where parm_func=:parm_func";
		RowMapper<SysParameter> rowMapper=new BeanPropertyRowMapper<SysParameter>(SysParameter.class);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("parm_func", parm_func);
		List<SysParameter> sysParameters=jdbcParameter.query(sql,map, rowMapper);
		return sysParameters;
	}


	//更具parm_type查询 parm_desc
	@Override
	public String fingName(String parm_type) {
		String sql=" SELECT parm_desc from sys_parameter  where parm_type=:parm_type ";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("parm_type", parm_type);
		return jdbcParameter.queryForObject(sql,map,String.class);
	}
	
	//更具parm_type查询 parm_code
	@Override
	public List<Map<String, Object>> fingParmCode(String parm_type) {
		String sql=" SELECT parm_code parmCode from sys_parameter  where parm_type=? ";
		
		return  jdbcTemplate.queryForList(sql,parm_type);	
	}

	@Override
	public String getParmName(String parmType, String parmCode) {
		 String sql ="SELECT parm_name as parmName from sys_parameter  where parm_type='"+parmType+"' and parm_code='"+parmCode+"'";
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("parmType", parmType);
		 map.put("parmType", parmType);
		 return jdbcParameter.queryForObject(sql,map,String.class);
	}

	@Override
	public List<Map<String, Object>> getCodeType(String parmType) {
		String sql ="SELECT parm_name parmName ,parm_code parmCode from sys_parameter  where parm_status='1' and parm_type=? ";
		System.err.println(sql);
		List<Map<String, Object>> list= jdbcTemplate.queryForList(sql,parmType);	
		return list;
	}

	
}
