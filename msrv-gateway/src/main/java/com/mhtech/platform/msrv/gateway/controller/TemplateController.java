package com.mhtech.platform.msrv.gateway.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.utils.ExcelUtil;

/**
 * @ClassName TemplateController
 * @Description TODO 模板下载
 * @Author admini
 * @Date 2019/8/29 13:09
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/MobileApi/Template")
@SuppressWarnings("all")
public class TemplateController {
	// 下载导入模板
		@PostMapping(value = "/fingDownloadMb")
		public void fingDownloadMb(HttpServletRequest request, HttpServletResponse response,
				@RequestBody Map<String, Object> map) throws IOException {
			String fileName = map.get("fileName").toString();
			// 获取资源中的模板文件 
		    ClassPathResource resource = new ClassPathResource("\\template\\"+fileName); 
		    InputStream inputStream = resource.getInputStream(); 
		    // 根据excel创建对象 
		    Workbook workbook;
			try {
				workbook = WorkbookFactory.create(inputStream);
				ExcelUtil.downLoadExcel(fileName, response, workbook);
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
