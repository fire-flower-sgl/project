package com.springboot.dubbo.dao;

import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("rawtypes")
public interface DataFindDao
{
	/**
	* 查询总记录数
	* @param sql SQL查询语句
	* @return 总记录数
	*/
	public int getTotal(String sql);
	
	public int getTotal(String sql, Object[] param);
	
	/**
	* 分页查询
	* @param sql sql查询语句
	* @param CPage 显示第几条记录（即当前第几页）
	* @param psize 每页多少条记录
	* @return 分页后的数据
	*/
	public List pagingList(String sql,int firstPage,int pageSize,Class<?> beanClass);
	
	/**
	* 单页查询
	* @param hql 查询语句
	* @return 详细内容
	* @Transactional说明：事务注解，oracle中碰到clob,blob类型时要用
	*/
	public List getList(String sql,Class<?> beanClass);
	
	/**
	* 单页查询
	* @param sql SQL查询语句
	* @param param SQL查询语句中使用的参数
	* @return 详细内容
	* @Transactional说明：事务注解，oracle中碰到clob,blob类型时要用
	*/
	public List getList(String sql, Object[] param, Class<?> beanClass);
	/**
	* 单页查询
	* @param sql 查询语句
	* @return 详细内容
	* @Transactional说明：事务注解，oracle中碰到clob,blob类型时要用
	*/
	public List getMap(String sql);
	/**
     * 更新内容(此方法基本不用)
     * @param sql SQL语句
     */
	public String update(String sql);
	/**
     * 更新语句
     * @param sql SQL语句
     * @param param SQL语句使用的参数
     */
	public String update(String sql,Object[] param);
	
	/**
     * 更新内容
     * @param sql SQL语句
     * @return 返回新增记录的主键值
	 * @throws SQLException 
     */
	public String updateId(String sql, Object[] param);
	
	/**
     * 更新内容
     * @param list 需要批量上传的内容
     * @param param sql语句中需要使用的参数
     */
	public int saveBatch(List<Object[]> list,String sql);
}
