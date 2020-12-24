package com.mobile.utils;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mobile.utils.EncodeUtils;
import com.mobile.utils.ListUtils;
import com.mobile.utils.StringUtils;

/**
 * 文件操作工具类
 * 实现文件的创建、删除、复制、压缩、解压以及目录的创建、删除、复制、压缩解压等功能
 * @author ThinkGem
 * @version 2015-3-16
 */
public class FileUtil extends org.apache.commons.io.FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);



	/**
	 * 读取文件到字符串对象
	 * @param classResourcePath 资源文件路径加文件名
	 * @return 文件内容
	 * @author ThinkGem 2016-7-4
	 */
	public static String readFileToString(String classResourcePath){
		InputStream in = null;
		try {
			in = new ClassPathResource(classResourcePath).getInputStream();
            return IOUtils.toString(in, Charsets.toCharset("UTF-8"));
		} catch (IOException e) {
			logger.warn("Error file convert: {}", e.getMessage());
		}finally{
            IOUtils.closeQuietly(in);
		}
		return null;
	}
	
	public static String reader(String filePath) {
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(read);
				//String lineTxt = bufferedReader.readLine();
				String result="";
				
				/*while (lineTxt != null) {
					return lineTxt;
				}*/
				String lineTxt = null;
		     	while ((lineTxt = bufferedReader.readLine()) != null) {
		     		result+= lineTxt;
		     		//System.out.println(result);
		     	}
		     	bufferedReader.close();
		     	return result;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the file specified!");
			e.printStackTrace();
		 }catch (IOException e) {
			System.out.println("Error reading file content!");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String readerFile(File file) {
		try {
			//File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(read);
				//String lineTxt = bufferedReader.readLine();
				String result="";
				
				/*while (lineTxt != null) {
					return lineTxt;
				}*/
				String lineTxt = null;
		     	while ((lineTxt = bufferedReader.readLine()) != null) {
		     		result+= lineTxt;
		     		//System.out.println(result);
		     	}
		     	bufferedReader.close();
		     	return result;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the file specified!");
			e.printStackTrace();
		 }catch (IOException e) {
			System.out.println("Error reading file content!");
			e.printStackTrace();
		}
		return null;
	}
	


	/**
	 * 
	 * 删除单个文件
	 * 
	 * @param fileName 被删除的文件名
	 * @return 如果删除成功，则返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				logger.debug("删除文件 " + fileName + " 成功!");
				return true;
			} else {
				logger.debug("删除文件 " + fileName + " 失败!");
				return false;
			}
		} else {
			logger.debug(fileName + " 文件不存在!");
			return true;
		}
	}


	/**
	 * 创建单个文件
	 * @param descFileName 文件名，包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static boolean createFile(String descFileName) {
		File file = new File(descFileName);
		if (file.exists()) {
			logger.debug("文件 " + descFileName + " 已存在!");
			return false;
		}
		if (descFileName.endsWith(File.separator)) {
			logger.debug(descFileName + " 为目录，不能创建目录!");
			return false;
		}
		if (!file.getParentFile().exists()) {
			// 如果文件所在的目录不存在，则创建目录
			if (!file.getParentFile().mkdirs()) {
				logger.debug("创建文件所在的目录失败!");
				return false;
			}
		}

		// 创建文件
		try {
			if (file.createNewFile()) {
				logger.debug(descFileName + " 文件创建成功!");
				return true;
			} else {
				logger.debug(descFileName + " 文件创建失败!");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(descFileName + " 文件创建失败!");
			return false;
		}

	}

	/**
	 * 创建目录
	 * @param descDirName 目录名,包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static boolean createDirectory(String descDirName) {
		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);
		if (descDir.exists()) {
			logger.debug("目录 " + descDirNames + " 已存在!");
			return false;
		}
		// 创建目录
		if (descDir.mkdirs()) {
			logger.debug("目录 " + descDirNames + " 创建成功!");
			return true;
		} else {
			logger.debug("目录 " + descDirNames + " 创建失败!");
			return false;
		}

	}



	/**
	 * 将文件压缩到ZIP输出流
	 * @param dirPath 目录路径
	 * @param file 文件
	 * @param zouts 输出流
	 */
	public static void zipFilesToZipFile(String dirPath, File file, ZipOutputStream zouts) {
		FileInputStream fin = null;
		ZipEntry entry = null;
		// 创建复制缓冲区
		byte[] buf = new byte[4096];
		int readByte = 0;
		if (file.isFile()) {
			try {
				// 创建一个文件输入流
				fin = new FileInputStream(file);
				// 创建一个ZipEntry
				entry = new ZipEntry(getEntryName(dirPath, file));
				// 存储信息到压缩文件
				zouts.putNextEntry(entry);
				// 复制字节到压缩文件
				while ((readByte = fin.read(buf)) != -1) {
					zouts.write(buf, 0, readByte);
				}
				zouts.closeEntry();
				fin.close();
				logger.debug("添加文件 " + file.getAbsolutePath() + " 到zip文件中!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取待压缩文件在ZIP文件中entry的名字，即相对于跟目录的相对路径名
	 * @param dirPat 目录名
	 * @param file entry文件名
	 * @return
	 */
	private static String getEntryName(String dirPath, File file) {
		String dirPaths = dirPath;
		if (!dirPaths.endsWith(File.separator)) {
			dirPaths = dirPaths + File.separator;
		}
		String filePath = file.getAbsolutePath();
		// 对于目录，必须在entry名字后面加上"/"，表示它将以目录项存储
		if (file.isDirectory()) {
			filePath += "/";
		}
		int index = filePath.indexOf(dirPaths);

		return filePath.substring(index + dirPaths.length());
	}

	/**
	 * 根据文件名的后缀获取文件内容类型
	 * @return 返回文件类型
	 */
	public static String getContentType(String fileName) {
		return new MimetypesFileTypeMap().getContentType(fileName);
	}
	

	/**
	 * 向浏览器发送文件下载，支持断点续传
	 * @param file 要下载的文件
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 返回错误信息，无错误信息返回null
	 */
	public static String downFile(File file, HttpServletRequest request, HttpServletResponse response){
		 return downFile(file, request, response, null);
	}
	
	/**
	 * 向浏览器发送文件下载，支持断点续传
	 * @param file 要下载的文件
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param fileName 指定下载的文件名
	 * @return 返回错误信息，无错误信息返回null
	 */
	public static String downFile(File file, HttpServletRequest request, HttpServletResponse response, String fileName){
		long fileLength = file.length(); // 记录文件大小
		long pastLength = 0; 	// 记录已下载文件大小
		int rangeSwitch = 0; 	// 0：从头开始的全文下载；1：从某字节开始的下载（bytes=27000-）；2：从某字节开始到某字节结束的下载（bytes=27000-39000）
		long toLength = 0; 		// 记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
		long contentLength = 0; // 客户端请求的字节总量
		String rangeBytes = ""; // 记录客户端传来的形如“bytes=27000-”或者“bytes=27000-39000”的内容
		RandomAccessFile raf = null; // 负责读取数据
		OutputStream os = null; 	// 写出数据
		OutputStream out = null; 	// 缓冲
		byte b[] = new byte[1024]; 	// 暂存容器

		if (request.getHeader("Range") != null) { // 客户端请求的下载的文件块的开始字节
			response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);
			logger.debug("request.getHeader(\"Range\") = " + request.getHeader("Range"));
			rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");
			if (rangeBytes.indexOf('-') == rangeBytes.length() - 1) {// bytes=969998336-
				rangeSwitch = 1;
				rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
				pastLength = Long.parseLong(rangeBytes.trim());
				contentLength = fileLength - pastLength; // 客户端请求的是 969998336  之后的字节
			} else { // bytes=1275856879-1275877358
				rangeSwitch = 2;
				String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
				String temp2 = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length());
				pastLength = Long.parseLong(temp0.trim()); // bytes=1275856879-1275877358，从第 1275856879 个字节开始下载
				toLength = Long.parseLong(temp2); // bytes=1275856879-1275877358，到第 1275877358 个字节结束
				contentLength = toLength - pastLength; // 客户端请求的是 1275856879-1275877358 之间的字节
			}
		} else { // 从开始进行下载
			contentLength = fileLength; // 客户端要求全文下载
		}

		// 如果设设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。 响应的格式是:
		// Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
		// ServletActionContext.getResponse().setHeader("Content- Length", new Long(file.length() - p).toString());
		response.reset(); // 告诉客户端允许断点续传多线程连接下载,响应的格式是:Accept-Ranges: bytes
		if (pastLength != 0) {
			response.setHeader("Accept-Ranges", "bytes");// 如果是第一次下,还没有断点续传,状态是默认的 200,无需显式设置;响应的格式是:HTTP/1.1 200 OK
			// 不是从最开始下载, 响应的格式是: Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
			logger.debug("服务器即将开始断点续传...");
			switch (rangeSwitch) {
				case 1: { // 针对 bytes=27000- 的请求
					String contentRange = new StringBuffer("bytes ").append(new Long(pastLength).toString()).append("-")
							.append(new Long(fileLength - 1).toString()).append("/").append(new Long(fileLength).toString()).toString();
					response.setHeader("Content-Range", contentRange);
					break;
				}
				case 2: { // 针对 bytes=27000-39000 的请求
					String contentRange = rangeBytes + "/" + new Long(fileLength).toString();
					response.setHeader("Content-Range", contentRange);
					break;
				}
				default: {
					break;
				}
			}
		}
		
		try {
			response.addHeader("Content-Disposition", "attachment; filename=\"" + 
					EncodeUtils.encodeUrl(StringUtils.isBlank(fileName) ? file.getName() : fileName) + "\"");
			response.setContentType(getContentType(file.getName())); // set the MIME type.
			response.addHeader("Content-Length", String.valueOf(contentLength));
			os = response.getOutputStream();
			out = new BufferedOutputStream(os);
			raf = new RandomAccessFile(file, "r");
			try {
				switch (rangeSwitch) {
					case 0: { // 普通下载，或者从头开始的下载 同1
					}
					case 1: { // 针对 bytes=27000- 的请求
						raf.seek(pastLength); // 形如 bytes=969998336- 的客户端请求，跳过 969998336 个字节
						int n = 0;
						while ((n = raf.read(b, 0, 1024)) != -1) {
							out.write(b, 0, n);
						}
						break;
					}
					case 2: { // 针对 bytes=27000-39000 的请求
						raf.seek(pastLength); // 形如 bytes=1275856879-1275877358 的客户端请求，找到第 1275856879 个字节
						int n = 0;
						long readLength = 0; // 记录已读字节数
						while (readLength <= contentLength - 1024) {// 大部分字节在这里读取
							n = raf.read(b, 0, 1024);
							readLength += 1024;
							out.write(b, 0, n);
						}
						if (readLength <= contentLength) { // 余下的不足 1024 个字节在这里读取
							n = raf.read(b, 0, (int) (contentLength - readLength));
							out.write(b, 0, n);
						}
						break;
					}
					default: {
						break;
					}
				}
				out.flush();
				logger.debug("下载完成！" + file.getAbsolutePath());
			} catch (IOException ie) {
				/**
				 * 在写数据的时候， 对于 ClientAbortException 之类的异常，
				 * 是因为客户端取消了下载，而服务器端继续向浏览器写入数据时， 抛出这个异常，这个是正常的。
				 * 尤其是对于迅雷这种吸血的客户端软件， 明明已经有一个线程在读取 bytes=1275856879-1275877358，
				 * 如果短时间内没有读取完毕，迅雷会再启第二个、第三个。。。线程来读取相同的字节段， 直到有一个线程读取完毕，迅雷会 KILL
				 * 掉其他正在下载同一字节段的线程， 强行中止字节读出，造成服务器抛 ClientAbortException。
				 * 所以，我们忽略这种异常
				 */
				logger.debug("提醒：向客户端传输时出现IO异常，但此异常是允许的，有可能客户端取消了下载，导致此异常，不用关心！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
//					logger.error(e.getMessage(), e);
				}
			}
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
//					logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	/**
	 * 修正路径，将 \\ 或 / 等替换为 File.separator
	 * @param path 待修正的路径
	 * @return 修正后的路径
	 */
	public static String path(String path){
		String p = StringUtils.replace(path, "\\", "/");
		p = StringUtils.join(StringUtils.split(p, "/"), "/");
		if (!StringUtils.startsWithAny(p, "/") && StringUtils.startsWithAny(path, "\\", "/")){
			p += "/";
		}
		if (!StringUtils.endsWithAny(p, "/") && StringUtils.endsWithAny(path, "\\", "/")){
			p = p + "/";
		}
		if (path != null && path.startsWith("/")){
			p = "/" + p; // linux下路径
		}
		return p;
	}
	
	/**
	 * 获目录下的文件列表
	 * @param dir 搜索目录
	 * @param searchDirs 是否是搜索目录
	 * @return 文件列表
	 */
	public static List<String> findChildrenList(File dir, boolean searchDirs) {
		List<String> files = ListUtils.newArrayList();
		for (String subFiles : dir.list()) {
			File file = new File(dir + "/" + subFiles);
			boolean a=file.isDirectory();
			if (((searchDirs) && (file.isDirectory())) || ((!searchDirs) && (!file.isDirectory()))) {
				files.add(file.getName());
			}
		}
		return files;
	}

	/**
	 * 获取文件名(带扩展名)
	 * @param pathname 文件路径名
	 */
	public static String getFileName(String fileName) {
        return new File(fileName).getName();
	}

	/**
	 * 获取文件名，不包含扩展名
	 * @param fileName 文件名
	 * @return 例如：d:\files\test.jpg  返回：d:\files\test
	 */
	public static String getFileNameWithoutExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf(".") == -1)) {
			return null;
		}
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * 获取文件扩展名(返回小写)
	 * @param pathname 文件名
	 * @return 例如：test.jpg  返回：  jpg
	 */
	public static String getFileExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf(".") == -1) 
				|| (fileName.lastIndexOf(".") == fileName.length() - 1)) {
			return null;
		}
		return StringUtils.lowerCase(fileName.substring(fileName.lastIndexOf(".") + 1));
	}
	
	/**
	 * 根据图片Base64获取文件扩展名
	 * @param imageBase64
	 * @return
	 * @author ThinkGem
	 */
	public static String getFileExtensionByImageBase64(String imageBase64){
		String extension = null;
		String type = StringUtils.substringBetween(imageBase64, "data:", ";base64,");
		if (StringUtils.inStringIgnoreCase(type, "image/jpeg")){
			extension = "jpg";
		}else if (StringUtils.inStringIgnoreCase(type, "image/gif")){
			extension = "gif";
		}else{
			extension = "png";
		}
		return extension;
	}
	

    /*
	 * 读取配置文件
	*/	
	public static String readInputStream( InputStream inputStream ) throws IOException {
		
		StringBuilder builder = new StringBuilder();
		try {
			InputStreamReader reader = new InputStreamReader(inputStream , "UTF-8" );
			BufferedReader bfReader = new BufferedReader( reader );
			String tmpContent = null;
			while ( ( tmpContent = bfReader.readLine() ) != null ) {
				builder.append( tmpContent );
			}
			bfReader.close();
		} catch ( UnsupportedEncodingException e ) {
			// 忽略
		}
		// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
		return builder.toString().replaceAll( "/\\*[\\s\\S]*?\\*/", "" );
	}
	
}

