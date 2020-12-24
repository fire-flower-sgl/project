package com.mhtech.common.tool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.*;

/**
 * @ClassName Utilis
 * @Description TODO 工具类
 * @Author admini
 * @Date 2019/7/22 9:19
 * @Version 1.0
 */
public class Utilis {
    static RestTemplate restTemplate = null;

    /**
     * unicode编码转换中文
     *
     * @param unicode
     * @return
     */
    /*public static String unicodeToCn(String unicode) {
        return StringEscapeUtils.unescapeJava(unicode);
    }*/

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
    public static JdbcTemplate getJdbcTemplate() {
    	 
		// com.alibaba.druid.pool.DruidDataSource
		DruidDataSource dataSource = new DruidDataSource();
 
		// 设置数据源属性参数
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://47.97.124.12:3306/mobileinterface?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true");
		dataSource.setUsername("root");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
 
		// 获取spring的JdbcTemplate
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		// 设置数据源
		jdbcTemplate.setDataSource(dataSource);
 
		return jdbcTemplate;
	}
    public static void main(String[] args) {
    	  //excel文件路径
        String excelPath = "G:\\项目\\游鸽\\排班.xls";

        try {
            //String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(excel);
                }else {
                    System.out.println("文件类型错误!");
                    return;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                
                System.out.println("firstRowIndex: "+firstRowIndex);
                System.out.println("lastRowIndex: "+lastRowIndex);
                String findPcode = "select pcode,pname from sp_personne ";
                List<Map<String, Object>> person = Utilis.getJdbcTemplate().queryForList(findPcode);
                System.out.println(person.toString());
                Map<String,String> mobileToPcode = new HashMap<String, String>();
                for(int i = 0 ;i<person.size();i++) {
                	mobileToPcode.put((person.get(i).get("pname")+"").trim(), person.get(i).get("pcode")+"");
                }
                //System.out.println(mobileToPcode.toString());
                List<String> sqls = new ArrayList<String>();
                System.out.println("记录sql");
                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                	
                   
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        for (int cIndex = 10; cIndex <= 10; cIndex++) {   //遍历列
                        	String pcode = mobileToPcode.get(row.getCell(2)+"");//手机号字段
                        	String day = cIndex+"";
                        	String areaId ="";
                        	String shfitId = "";
                        	if((row.getCell(4)+"").trim().equals("台江区")) {
                        		areaId ="2";
                        	}
                        	if((row.getCell(4)+"").trim().equals("鼓楼区")) {
                        		areaId ="1";
                        	}
                        	if((row.getCell(4)+"").trim().equals("仓山区")) {
                        		areaId ="3";
                        	}if((row.getCell(4)+"").trim().equals("晋安区")) {
                        		areaId ="5";
                        	}
                        	if((row.getCell(4)+"").trim().equals("马尾区")) {
                        		areaId ="4";
                        	}
                        	String Sql = "";
                        	if((row.getCell(2+cIndex)+"").indexOf("白班")!=-1) {
                        		shfitId = "11";
                            	Sql="insert into sp_duty_roster ( id ,pcode,year,month,day,shiftId,areaId) values (uuid(),'"+pcode+"','2019','11','"+day+"','"+shfitId+"','"+areaId+"');";
                       		 	System.out.println(Sql);
                        	}
                        	if((row.getCell(2+cIndex)+"").indexOf("晚班")!=-1) {
                        		shfitId = "12";
                            	Sql="insert into sp_duty_roster ( id ,pcode,year,month,day,shiftId,areaId) values (uuid(),'"+pcode+"','2019','11','"+day+"','"+shfitId+"','"+areaId+"');";
                       		 	System.out.println(Sql);
                        	}
                        	sqls.add(Sql);
                        }
                    }
                }
               // System.out.println(sqls.toString());
                
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * @Description //TODO 单条数据
     * @Date 19:31 2019/7/25
     * @Param [map]
     * @return java.lang.String
     **/
    public static String getMSGMap(Map<String, Object> map) {
        Map msg = new HashMap();
        if (map != null) {
            msg.put("data", map);
            msg.put("success", "true");
            msg.put("message", "请求成功");
            msg.put("status", "200");
            return JSON.toJSON(msg).toString();
        } else {
            msg.put("success", "false");
            msg.put("data", "");
            msg.put("status", "4005");
            msg.put("message", "请求异常");
            return JSON.toJSON(msg).toString();
        }
    }

    /*
     * @Author admini
     * @Description //TODO 多条数据
     * @Date 19:31 2019/7/25
     * @Param [map]
     * @return java.lang.String
     **/
    public static String getMSGList(List<Map<String, Object>> map) {
        Map msg = new HashMap();
        if (map != null) {
            msg.put("data", map);
            msg.put("success", "true");
            msg.put("message", "请求成功");
            msg.put("status", "200");
            return JSON.toJSON(msg).toString();
        } else {
            msg.put("success", "false");
            msg.put("data", "");
            msg.put("status", "4005");
            msg.put("message", "请求异常");
            return JSON.toJSON(msg).toString();
        }
    }
    /*
     * @Author admini
     * @Description //TODO 多条数据
     * @Date 19:31 2019/7/25
     * @Param [map]
     * @return java.lang.String
     **/
    public static String getMSGListString(List<Map<String, String>> map) {
        Map msg = new HashMap();
        if (map != null) {
            msg.put("data", map);
            msg.put("success", "true");
            msg.put("message", "请求成功");
            msg.put("status", "200");
        	String jsonString = JSON.toJSONString(msg, true);
            return JSON.toJSON(msg).toString();
        } else {
            msg.put("success", "false");
            msg.put("data", "");
            msg.put("status", "4005");
            msg.put("message", "请求异常");
            return JSON.toJSON(msg).toString();
        }
    }
    /*
     * @Author admini
     * @Description //TODO 新增、修改、删除
     * @Date 19:35 2019/7/25
     * @Param [count]
     * @return java.lang.String
     **/
    public static String getMSG(int count) {
        Map msg = new HashMap();
        if (count > 0) {
            msg.put("success", "true");
            msg.put("message", "请求成功");
            msg.put("status", "200");
            return JSON.toJSON(msg).toString();
        } else {
            msg.put("success", "false");
            msg.put("message", "请求异常");
            msg.put("status", "500");
            return JSON.toJSON(msg).toString();
        }
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
    public static String freemarkerProcess(Map input, String templateStr) {
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        String template = "content";
        stringLoader.putTemplate(template, templateStr);
        Configuration cfg = new Configuration();
        cfg.setTemplateLoader(stringLoader);
        try {
            Template templateCon = cfg.getTemplate(template);
            StringWriter writer = new StringWriter();
            templateCon.process(input, writer);
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    
}
