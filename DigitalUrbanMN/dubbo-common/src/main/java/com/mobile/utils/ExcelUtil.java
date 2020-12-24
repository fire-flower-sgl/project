package com.mobile.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ExcelUtil
 * @Description TODO Excel导出工具
 * @Author admini
 * @Date 2019/8/3 12:43
 * @Version 1.0
 */

public class ExcelUtil {
	
	private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);
	
    /*
     * @Author admini
     * @Description //TODO Excel 导出
     * @Date 13:16 2019/8/3
     * @Param [response, sheetName, fileName, headTitle, list_Data]
     * @return void
     **/
    public static void exportExcel(HttpServletResponse response, String sheetName, String fileName, String headTitle[], List<List<String>> list_Data) {
//        log.info("导出解析开始，fileName:{}",data.getFileName());
        try {
            //实例化HSSFWorkbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            if (sheetName == null || sheetName.trim().isEmpty())
                sheetName = "sheet";
            //创建一个Excel表单，参数为sheet的名字
            HSSFSheet sheet = workbook.createSheet(sheetName);
            //设置表头
            setTitle(workbook, sheet, headTitle);
            //设置单元格并赋值
            List<String[]> list = new ArrayList<>();
            setData(sheet, list_Data);
            //设置浏览器下载
            setBrowser(response, workbook, fileName);
        } catch (Exception e) {
            System.out.println("导出解析失败!");
            e.printStackTrace();
        }
    }

    /*
     * @Author admini
     * @Description //TODO 设置表头
     * @Date 13:16 2019/8/3
     * @Param [workbook, sheet, str]
     * @return void
     **/
    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str) {
        try {
            HSSFRow row = sheet.createRow(0);
            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            for (int i = 0; i <= str.length; i++) {
                sheet.setColumnWidth(i, 15 * 256);
            }
            //设置为居中加粗,格式化时间格式
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            //创建表头名称
            HSSFCell cell;
            for (int j = 0; j < str.length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(str[j]);
                cell.setCellStyle(style);
            }
        } catch (Exception e) {
            System.out.println("导出时设置表头失败！");
            e.printStackTrace();
        }
    }

    /*
     * @Author admini
     * @Description //TODO 数据内容设置
     * @Date 13:16 2019/8/3
     * @Param [sheet, data]
     * @return void
     **/
    private static void setData(HSSFSheet sheet,List<List<String>>datas) {
        try {
            int rowNum = 1;
           /* for (int i = 0; i < data.size(); i++) {
                HSSFRow row = sheet.createRow(rowNum);
                for (int j = 0; j < data.get(i).length; j++) {
                    row.createCell(j).setCellValue(data.get(i)[j]);
                }
                rowNum++;
            }
            List<String[]> data
            */

            for (int i=0;i<datas.size();i++){
                HSSFRow row = sheet.createRow(rowNum);
                for (int j=0;j<datas.get(i).size();j++){
                    row.createCell(j).setCellValue(datas.get(i).get(j));
                }
                rowNum++;
            }

        } catch (Exception e) {
            System.out.println("表格赋值失败！");
            e.printStackTrace();
        }
    }

    /*
     * @Author admini
     * @Description //TODO 设置浏览器下载
     * @Date 13:17 2019/8/3
     * @Param [response, workbook, fileName]
     * @return void
     **/
    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
        try {
            //清空response
            response.reset();
            //设置response的Header
//            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Access-Control-Allow-Origin", "*");
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            response.setContentType("application/json");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            System.out.println("设置浏览器下载失败！");
            e.printStackTrace();
        }
    }

	// 模板下载工具类
	public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
//			setBrowser(response,workbook,fileName);
//			response.setCharacterEncoding("UTF-8");
//			response.setHeader("content-Type", "application/vnd.ms-excel");
//			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//			workbook.write(response.getOutputStream());
			  try {
		            //清空response
		            response.reset();
		            //设置response的Header
//		            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		            response.addHeader("Access-Control-Allow-Origin", "*");
		            OutputStream os = new BufferedOutputStream(response.getOutputStream());
//		            response.setContentType("application/vnd.ms-excel;charset=gb2312");
		            response.setContentType("application/json");
		            //将excel写入到输出流中
		            workbook.write(os);
		            os.flush();
		            os.close();
		        } catch (Exception e) {
		            System.out.println("设置浏览器下载失败！");
		            e.printStackTrace();
		        }
	}
	
	
	
	 /**
     * 方法名：importExcel
     * 功能：导入
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 11:45
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    public static List<Object[]> importExcel(String fileName) {
        log.info("导入解析开始，fileName:{}",fileName);
        try {
            List<Object[]> list = new ArrayList<>();
            InputStream inputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //获取sheet的行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                //过滤表头行
                if (i == 0) {
                    continue;
                }
                //获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                for (Cell cell : row) {
                	int type=cell.getCellType();
                    if (cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC) {
                        objects[index] = (int) cell.getNumericCellValue();
                        if (DateUtil.isCellDateFormatted(cell)) {
                        	objects[index] =cell.getDateCellValue();
						}
                    }
                    if (cell.getCellType()==HSSFCell.CELL_TYPE_STRING) {
                        objects[index] = cell.getStringCellValue();
                    }
                    if (cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN) {
                        objects[index] = cell.getBooleanCellValue();
                    }
                    if (cell.getCellType()==HSSFCell.CELL_TYPE_ERROR) {
                        objects[index] = cell.getErrorCellValue();
                    }
                    index++;
                }
                list.add(objects);
            }
            log.info("导入文件解析成功！");
            return list;
        }catch (Exception e){
            log.info("导入文件解析失败！");
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
    	String fileName = "F:\\data\\模板.xls";
        List<Object[]> list = importExcel(fileName);
        System.err.println("==========="+list.toString());
        for (int i = 0; i < list.size(); i++) {
        	Object[] objects=list.get(i);
			String userCode=objects[0].toString();
			String ip=objects[1].toString();
			Date startDate=(Date) objects[2];
			Date endDate=(Date) objects[3];
			System.err.println(userCode+"==========="+ip+"========="+DateUtils.formatDate(startDate,"yyyy-MM-dd HH:mm:ss")+"==========="+endDate);
		}
	}
    
    
    
}
