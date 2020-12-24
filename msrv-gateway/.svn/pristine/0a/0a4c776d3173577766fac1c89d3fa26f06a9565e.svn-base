package com.mhtech.platform.msrv.gateway.utils;


import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.formula.functions.T;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName Utilis
 * @Description TODO 工具类
 * @Author admini
 * @Date 2019/7/22 9:19
 * @Version 1.0
 */
public class Utilis {
    /**
     * unicode编码转换中文
     *
     * @param unicode
     * @return
     */
    public static String unicodeToCn(String unicode) {
        return StringEscapeUtils.unescapeJava(unicode);
    }

    /**
     * 获取UUID 16位
     *
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String tmp = "";
        for (int i = 0; i < 16; i++) {
            tmp = tmp + uuid.charAt(i);
        }
        return tmp;
    }



    private static URI getIP(URI uri) {
        URI effectiveURI = null;

        try {
            // URI(String scheme, String userInfo, String host, int port, String
            // path, String query,String fragment)
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (Throwable var4) {
            effectiveURI = null;
        }
        return effectiveURI;
    }

    public static List<T> ss(String strResult, Class<T> entity) {
        List<T> list = new ArrayList<T>();
        list = JSONObject.parseArray(strResult, entity);
        return list;
    }

   public static enum column_types {
        String, Integer, Double, Boolean, Date
    }


    public static String format_sql(String table_name, Object model, Map<String, String> map_type, String type,String databaseType) {
        String sql = "";
        if ("insert".equals(type)) {
            sql = insert(table_name, model, map_type,databaseType);
        } else if ("update".equals(type)) {
            sql = update(table_name, model, map_type,databaseType);
        }
        return sql;
    }

    private static String insert(String table_name, Object model, Map<String, String> map_type,String databaseType) {
        String sql = "insert into " + table_name;
        String sql_cloum = "";
        String sql_values = "";
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                String GenericType = field.getGenericType().toString();
                GenericType = GenericType.substring(GenericType.lastIndexOf(".") + 1);
                GenericType = GenericType.toLowerCase();
                String cloum_name = field.getName();
                sql_cloum = sql_cloum + cloum_name + ",";
                String zdy_type = "";
                if (map_type.get(cloum_name) != null)
                    zdy_type = map_type.get(cloum_name).toLowerCase();
                if (GenericType.equals("date") || "date".equals(zdy_type)) {
                    if (field.get(model) == null || "".equals(field.get(model)) || "null".equals(field.get(model)))
                        sql_values = sql_values + "NULL,";
                    else {
                        if("ORACLE".equals(databaseType.toUpperCase()))
                            sql_values = sql_values + "to_date('" + field.get(model) + "','yyyy-mm-dd hh24:mi:ss'),";
                        else
                            sql_values = sql_values + "'" + field.get(model) + "',";
                    }
                } else if (GenericType.equals("double") || GenericType.equals("integer") || "double".equals(zdy_type) || "integer".equals(zdy_type)) {
                    if (field.get(model) == null || "".equals(field.get(model)) || "null".equals(field.get(model)))
                        sql_values = sql_values + "NULL,";
                    else
                        sql_values = sql_values + "" + field.get(model) + ",";
                } else {
                    if (field.get(model) == null || "".equals(field.get(model)) || "null".equals(field.get(model)))
                        sql_values = sql_values + "NULL,";
                    else
                        sql_values = sql_values + "'" + field.get(model) + "',";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        sql_cloum = sql_cloum.substring(0, sql_cloum.length() - 1);
        sql_values = sql_values.substring(0, sql_values.length() - 1);
        String tmp = sql + "(" + sql_cloum + ")values(" + sql_values + ")";
        return tmp;
    }

    private static String update(String table_name, Object model, Map<String, String> map_type,String databaseType) {
        String sql = "update " + table_name;
        String sql_values = " set ";
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                String GenericType = field.getGenericType().toString();
                GenericType = GenericType.substring(GenericType.lastIndexOf(".") + 1);
                GenericType = GenericType.toLowerCase();
                String cloum_name = field.getName();
                String zdy_type = "";
                if (map_type.get(cloum_name) != null)
                    zdy_type = map_type.get(cloum_name).toLowerCase();
                if (GenericType.equals("date") || "date".equals(zdy_type)) {
                    if (field.get(model) == null)
                        sql_values = sql_values;
                    else {

                        if("ORACLE".equals(databaseType.toUpperCase()))
                            sql_values = sql_values + " " + cloum_name + "= to_date('" + field.get(model) + "','yyyy-mm-dd hh24:mi:ss'),";
                        else
                            sql_values = sql_values + " " + cloum_name + "= '" + field.get(model) + "',";
                    }
                } else if (GenericType.equals("double") || GenericType.equals("integer") || "double".equals(zdy_type) || "integer".equals(zdy_type)) {
                    if (field.get(model) == null)
                        sql_values = sql_values;
                    else
                        sql_values = sql_values + cloum_name + "=" + field.get(model) + ",";
                } else {
                    if (field.get(model) == null)
                        sql_values = sql_values;
                    else
                        sql_values = sql_values + cloum_name + "='" + field.get(model) + "',";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        sql_values = sql_values.substring(0, sql_values.length() - 1);
        String tmp = sql + sql_values;
        return tmp;
    }

    /*
     * @Author admini
     * @Description //TODO List <Map Object> 转换为String
     * @Date 12:44 2019/7/28
     * @Param [maps]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    public static List<Map<String, String>> mapObjToString(List<Map<String, Object>> maps) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Map map : maps) {
            Map<String, String> map1 = new HashMap<>();
            Iterator<String> iter = map.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = null;
                if(map.get(key)!=null) {
                    value = map.get(key).toString();
                  //时间字段截取,不显示毫秒
                	if(value.endsWith(".0")) {
                		value = value.substring(0, value.length()-2);
                	}
                }
                map1.put(key.toUpperCase(), value);//大写key
                map1.put(key.toLowerCase(), value);//小写key
            }
            list.add(map1);
        }
        return list;
    }
    
    /*
     * @Author admini
     * @Description //TODO List <Map Object> 转换为String
     * @Date 12:44 2019/7/28
     * @Param [maps]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    public static List<Map<String, String>> mapObjToString2(List<Map<String, Object>> maps) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Map map : maps) {
            Map<String, String> map1 = new HashMap<>();
            Iterator<String> iter = map.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = null;
                if(map.get(key)!=null) {           	
                	value = map.get(key).toString();
                	//时间字段截取,不显示毫秒
                	if(value.endsWith(".0")) {
                		value = value.substring(0, value.length()-2);
                	}
                }
                map1.put(key, value);
            }
            list.add(map1);
        }
        return list;
    }
    /*
     * @Author admini
     * @Description //TODO Map Object 转换为String
     * @Date 12:44 2019/7/28
     * @Param [maps]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    public static Map<String, String> mapObjToString(Map<String, Object> map) {
            Map<String, String> map1 = new HashMap<>();
            Iterator<String> iter = map.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = null;
                if(map.get(key)!=null)
                    value = map.get(key).toString();
                map1.put(key.toUpperCase(), value);//大写key
                map1.put(key.toLowerCase(), value);//小写key
            }
        return map1;
    }
    
    /*
     * @Author admini
     * @Description //TODO Map Object 转换为String
     * @Date 12:44 2019/7/28
     * @Param [maps]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     **/
    public static Map<String, String> mapObjToString2(Map<String, Object> map) {
            Map<String, String> map1 = new HashMap<>();
            Iterator<String> iter = map.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = null;
                if(map.get(key)!=null) {
                    value = map.get(key).toString();
                    if(value.endsWith(".0")) {
                    	value = value.substring(0, value.length()-2);
                    }
                }
                map1.put(key, value);
            }
        return map1;
    }


    /**
     * 手机号验证
     * @param mobile
     * @return boolean
     */
    public static boolean ValidateMobile(String mobile) {
    	final String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
    	Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobile);
    	return m.matches();
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
