package com.springboot.dubbo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

//import org.springframework.transaction.annotation.Transactional;
import com.springboot.dubbo.dao.DataFindDao;

/**
 *   方法的事务设置将被优先执行。
 *   例如： BusinessService类在类的级别上被注解为只读事务，
 *   但是，这个类中的 save 方法的@Transactional 注解的事
 *   务设置将优先于类级别注解的事务设置。
 *   默认的 @Transactional 设置如下：
 *       事务传播设置是 PROPAGATION_REQUIRED
 *       事务隔离级别是 ISOLATION_DEFAULT
 *       事务是 读/写
 *       事务超时默认是依赖于事务系统的，或者事务超时没有被支持
 *       任何 RuntimeException 将触发事务回滚，但是任何 checked Exception 将不触发事务回滚
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
//@Transactional(readOnly=true)
@Repository(value = "DataFindDao")
public class DataFindDaoImpl implements DataFindDao
{
	protected final String RAW_ID = "rawId";
	protected final String ID = "id";
	protected final String TABLE_NAME = "tableName";
	protected final String TYPE = "type";
	protected final String COLUMNS = "columns";
	protected final String TS_LIST = "tsList";
	protected final String COLUMN_TYPE = "columnType";//
	protected final Integer COLUMN_TYPE_DATE = 1;//日期类型
	protected final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	* 查询总记录数
	* @param sql SQL查询语句
	* @return 总记录数
	*/
	public int getTotal(String sql)
    {
		Integer no = jdbcTemplate.queryForObject(sql, Integer.class);
    	return no;
    }
	
	public int getTotal(String sql, Object[] param)
    {
		Integer no = jdbcTemplate.queryForObject(sql, param, Integer.class);
    	return no;
    }
	/**
	* 分页查询
	* @param sql sql查询语句
	* @param CPage 显示第几条记录（即当前第几页）
	* @param psize 每页多少条记录
	* @return 分页后的数据
	*/
	public List pagingList(String sql, int firstPage, int pageSize, Class<?> beanClass)
	{
		if(sql.indexOf("select ") == -1)
		{
			sql="select * "+sql;
		}
		//oracle数据库
//		pageSize = pageSize + firstPage;//分页结束的数字
//		sql = "SELECT * FROM ( SELECT OA.*,ROWNUM NUM FROM ( "+sql+" ) OA WHERE ROWNUM <= "+pageSize+" ) WHERE NUM >= "+firstPage;//分页语句
		//mysql数据库
		sql += " limit "+firstPage+","+pageSize;//Mysql数据库
		//List list = jdbcTemplate.queryForList(sql, beanClass);
		List list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(beanClass));

    	return list;
	}
	/**
	* 单页查询
	* @param hql 查询语句
	* @return 详细内容
	* @Transactional说明：事务注解，oracle中碰到clob,blob类型时要用
	*/
	public List getList(String sql, Class<?> beanClass)
	{
		List list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(beanClass));
    	return list;
	}
	/**
	* 单页查询
	* @param sql SQL查询语句
	* @param param SQL查询语句中使用的参数
	* @return 详细内容
	* @Transactional说明：事务注解，oracle中碰到clob,blob类型时要用
	*/
	public List getList(String sql, Object[] param, Class<?> beanClass)
	{
		List list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper(beanClass));
    	return list;
	}
	/**
	* 单页查询
	* @param sql 查询语句
	* @return 详细内容
	* @Transactional说明：事务注解，oracle中碰到clob,blob类型时要用
	*/
	public List getMap(String sql)
	{
		List list = jdbcTemplate.queryForList(sql);
    	return list;
	}
	/**
     * 更新内容(此方法基本不用)
     * @param sql SQL语句
     */
	public String update(String sql)
    {
		Integer no = jdbcTemplate.update(sql);
		return no.toString();
    }
	
	/**
     * 更新语句
     * @param sql SQL语句
     * @param param SQL语句使用的参数
     */
	//@Transactional(readOnly=true,isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public String update(String sql,Object[] param)
	{
		Integer no = jdbcTemplate.update(sql,param);
		return no.toString();
	}
	

	/**
     * 更新内容
     * @param sql SQL语句
     * @return 返回新增记录的主键值
	 * @throws SQLException 
     */
	public String updateId(String sql, Object[] param)
	{
		PreparedStatementCreator psc = new SimplePreparedStatementCreator(sql, param);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		String id = keyHolder.getKey().toString();
		keyHolder = null;
		psc = null;
		return id;
	}
	
	/**
     * 更新内容
     * @param list 需要批量上传的内容
     * @param param sql语句中需要使用的参数
     */
	public int saveBatch(List<Object[]> list,String sql)
	{
		final List item = list;
		int[] num = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() 
		{ 
		   public void setValues(PreparedStatement ps,int i)throws SQLException 
		   {
			   Object[] sp = (Object[])item.get(i);
			   int no = sp.length;
			   for(int m = 0;m<no;m++)
			   {
				   ps.setObject(m+1, sp[m]);
			   }
			   //ps.setString(1, sp[0]);
		   } 
		   public int getBatchSize() 
		   { 
			   return item.size(); 
		   }
		});
		return num[0];
	}
}