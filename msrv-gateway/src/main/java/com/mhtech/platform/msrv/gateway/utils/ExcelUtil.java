package com.mhtech.platform.msrv.gateway.utils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * excel 工具类
 * 
 * @since 0.1.0
 *
 */

public class ExcelUtil {

	/**
	 * 将list对象集合，存为Excel文件
	 * 
	 * @param list     对象集合
	 * @param path     路径
	 * @param fileName 文件名
	 * @param map      实体类属性对应解释（name,名称）
	 * @return 文件路径
	 */
	public static String createExcel(List<? extends Object> list, String path, String fileName,
			Map<String, Object> map) {
		String result = "";
		if (list.size() == 0 || list == null) {
			result = "没有对象信息";
		} else {
			Object o = list.get(0);
			Class<? extends Object> clazz = o.getClass();
			String className = clazz.getSimpleName(); // 获得类简写名称
			Field[] fields = clazz.getDeclaredFields(); // 这里通过反射获取字段数组
			File folder = new File(path);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			String name = fileName.concat(".xls");
			WritableWorkbook book = null;
			File file = null;
			try {
				file = new File(path.concat(File.separator).concat(name));
				book = Workbook.createWorkbook(file); // 创建xls文件
				WritableSheet sheet = book.createSheet(className, 0);
				//额外创建9个sheet页面-用于数据量，大，分页功能
				for (int i = 1; i <=9; i++) {
					 book.createSheet(className+i, i);
				}
				
				int i = 0; // 列
				int j = 0; // 行
				for (Field f : fields) {

					j = 0;
					String nameValue = "";
					for (Map.Entry<String, Object> a : map.entrySet()) {
						if (a.getKey().equals(f.getName())) {
							nameValue = a.getValue().toString();
						}
					}
					Label utfName = new Label(i, j, nameValue); // 字段对应中文名称写入第一行中
					sheet.addCell(utfName);

					j = 1;
					Label label = new Label(i, j, f.getName()); // 这里把字段名称写入excel第二行中
					sheet.addCell(label);
					j = 2;
					for (Object obj : list) {
						Object temp = getFieldValueByName(f.getName(), obj);
						String strTemp = "";
						if (temp != null) {
							strTemp = temp.toString();
						}
						sheet.addCell(new Label(i, j, strTemp)); // 把每个对象此字段的属性写入这一列excel中
						j++;
					}
					i++;
				}
				book.write();
				result = file.getPath();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result = "SystemException";
				e.printStackTrace();
			} finally {
				fileName = null;
				name = null;
				folder = null;
				file = null;
				if (book != null) {
					try {
						book.close();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						result = "WriteException";
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						result = "IOException";
						e.printStackTrace();
					}
				}
			}

		}

		return result; // 最后输出文件路径
	}

	/**
	 * 获取属性值
	 * 
	 * @param fieldName 字段名称
	 * @param o         对象
	 * @return Object
	 */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();//截取第1个，去除大小写
			String getter = "get" + firstLetter + fieldName.substring(1); // 获取方法名
			Method method = o.getClass().getMethod(getter, new Class[] {}); // 获取方法对象
			Object value = method.invoke(o, new Object[] {}); // 用invoke调用此对象的get字段方法
			return value; // 返回值
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 获取前天日期
	public static String fingTime() {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String dateString = formatter.format(date);
		return dateString;

	}

	// 今天日期
	public static String fingDayTime() {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 0);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 向ecexl追加数据
	 * @param excelPath 文件路径
	 * @param list 要添加的对象集合
	 * @param sheetId 页码，往第几页插入
	 * @return 
	 * @throws IOException
	 * @throws Exception
	 */
	public static  String addExcel(String excelPath, List<? extends Object> list,int sheetId) throws IOException, Exception {
		
		File file = new File(excelPath);
		Workbook workbook = Workbook.getWorkbook(file);
		WritableWorkbook writableWorkbook = Workbook.createWorkbook(file, workbook);
		//获取指定sheet
		WritableSheet sheet= writableWorkbook.getSheet(sheetId);
		int row= sheet.getRows();//最大行：使用变量，存下来，避免再次获取（动态变化）
		int i = 0;// 列
		int j = row; // 行： 从这里，开始向后追加数据
		String result = "";
		if (list.size() == 0 || list == null) {
			result = "没有对象信息";
		} else {
			Object o = list.get(0);
			Class<? extends Object> clazz = o.getClass();
			Field[] fields = clazz.getDeclaredFields(); // 这里通过反射获取字段数组--（即类定义的属性）

			for (Field f : fields) {
				//添加列数据
				for (Object obj : list) {
					Object temp = getFieldValueByName(f.getName(), obj);
					String strTemp = "";
					if (temp != null) {
						strTemp = temp.toString();
					}
					sheet.addCell(new Label(i, j, strTemp)); 
					j++;
				}
				j = row; // 重新回到指定行
				i++;
			}
			writableWorkbook.write();
		}
		writableWorkbook.close();
		workbook.close();
		return result;
	}

}