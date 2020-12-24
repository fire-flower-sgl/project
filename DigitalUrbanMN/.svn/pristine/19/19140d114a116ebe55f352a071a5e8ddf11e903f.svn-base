package com.tools.modules.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @ClassName:  ChartDao   
 * @Description:	图表配置接口   
 * @Author: admin
 * @Date:   Sep 11, 2019 6:49:06 PM   
 * @Version: 1.0
 */
public interface FtpService {

	/**
	 * TODO	上传单个文件
	 * @param file
	 * @param request
	 * @param path 为空则存在根目录下
	 * @return
	 * @throws FTPConnectionClosedException
	 * @throws IOException
	 * @throws Exception
	 */
//	String uploadFile(MultipartFile file, HttpServletRequest request, String path)
//			throws FTPConnectionClosedException, IOException, Exception;

	/**
	 * TODO	上传多个文件
	 * @param files
	 * @param request
	 * @param path 为空则存在根目录下
	 * @return
	 * @throws FTPConnectionClosedException
	 * @throws IOException
	 * @throws Exception
	 */
	String uploadFiles(MultipartFile[] files, HttpServletRequest request, String path)
			throws FTPConnectionClosedException, IOException, Exception;

	/**
	 * TODO
	 * @param file
	 * @param request
	 * @param path
	 * @return
	 * @throws FTPConnectionClosedException
	 * @throws IOException
	 * @throws Exception
	 */
	String uploadFile(byte[] file, String path,String fileName)
			throws FTPConnectionClosedException, IOException, Exception;
	
	

	
}
