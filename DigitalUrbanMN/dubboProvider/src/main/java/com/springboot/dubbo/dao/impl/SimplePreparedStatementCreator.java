package com.springboot.dubbo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class SimplePreparedStatementCreator implements PreparedStatementCreator
{
	private String sql;
	private Object[] param;

    public SimplePreparedStatementCreator(String sql,Object[] param)
    {
        this.sql = sql;
        this.param = param;
    }
    public SimplePreparedStatementCreator(String sql)
    {
        this.sql = sql;
        this.param = null;
    }
    public PreparedStatement createPreparedStatement(Connection con) throws SQLException
    {
    	//new int[]{1}代表数据表中的第一列。也可以直接使用列名new String[]{"id"}
    	PreparedStatement ps = con.prepareStatement(this.sql,new int[]{1});
    	if(param != null)
    	{
    		Integer num = param.length;
    		for(int i = 0;i<num;i++)
    		{
    			ps.setObject(i+1, param[i]);
    		}
    		num = null;
    	}
    	return ps;
    }
}
