package com.mhtech.platform.msrv.auth.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import java.text.MessageFormat;

/**
 * @ClassName PageUtilToSql
 * @Description TODO 形成分页sql
 * @Author admini
 * @Date 2019/8/2 14:28
 * @Version 1.0
 */
@Repository
public class PageUtilToSql {
    @Value("${database.type}")
    private String databaseType;
    public static final String MYSQL = "MYSQL";
    public static final String ORACLE = "ORACLE";

    /**
     * 获取通用于MySQL和Oracle数据库的分页语句
     *
     * @param sql      需要分页的sql语句，参数采用{0}，{1}这样的方式，与params数组的数据一一对应
     * @param pageable
     * @return
     */
    public String getPageSql(String sql, Pageable pageable) {
        return getPageSql(sql, null, pageable, false);
    }

    /**
     * 获取通用于MySQL和Oracle数据库的分页语句
     *
     * @param sql      需要分页的sql语句，参数采用{0}，{1}这样的方式，与params数组的数据一一对应
     * @param params   参数数组
     * @param pageable
     * @param isOrder  是否生成排序 true:生成排序，false不生成
     * @return
     */
    public String getPageSql(String sql, Object[] params, Pageable pageable, boolean isOrder) {
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        /**
         * 根据数据库类型不同调用不同的方法
         */
        if (MYSQL.equals(databaseType.toUpperCase())) {
            return getMysqlSql(sql, params, pageable);
        } else if (ORACLE.equals(databaseType.toUpperCase())) {
            return getOracleSql(sql, params, pageable);
        }
        return null;
    }

    /**
     * mysql分页  limit
     *
     * @param sql      需要分页的sql语句，参数采用{0}，{1}这样的方式，与params数组的数据一一对应
     * @param params   参数数组
     * @param pageable
     * @return
     */
    private static String getMysqlSql(String sql, Object[] params, Pageable pageable) {
        /**
         * 格式化参数
         */
        if (params != null && params.length > 0) {
            MessageFormat messageFormat = new MessageFormat(sql);
            sql = messageFormat.format(params);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(sql);
        if (pageable != null) {
            sb.append(" LIMIT ");
            sb.append(pageable.getPageNumber() * pageable.getPageSize());
            sb.append(" , ");
            sb.append(pageable.getPageSize());
        }
        return sb.toString();
    }

    /**
     * oracle 分页  rownum
     *
     * @param sql      需要分页的sql语句，参数采用{0}，{1}这样的方式，与params数组的数据一一对应
     * @param params   参数数组
     * @param pageable
     * @return
     */
    private static String getOracleSql(String sql, Object[] params, Pageable pageable) {
        /**
         * 格式化参数
         */
        if (params != null && params.length > 0) {
            MessageFormat messageFormat = new MessageFormat(sql);
            sql = messageFormat.format(params);
        }
        if (pageable == null)
            return sql;
        StringBuilder sb = new StringBuilder();
        sb.append("select * from (")
                .append("select rownum rn, a.* from(")
                .append(sql)
                .append(") a where rownum <= ")
                .append((pageable.getPageNumber() + 1) * pageable.getPageSize())
                .append(") where rn > ")
                .append(pageable.getPageNumber() * pageable.getPageSize());
        return sb.toString();
    }

}
