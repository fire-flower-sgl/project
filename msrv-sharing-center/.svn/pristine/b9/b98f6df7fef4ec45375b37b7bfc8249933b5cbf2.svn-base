package com.mhtech.platform.msrv.sharing.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public abstract class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * 异或运算对文件字节进行混淆，复制的文件位于源文件的同级目录
	 * @param file 文件绝对路径
	 * @param key 混淆码
	 */
	public static void XORCopy(String absoluteFileName, String newName, int key) {
		if(StringUtils.isEmpty(absoluteFileName) || StringUtils.isEmpty(newName)) {
			throw new RuntimeException(String.format("neither %s nor %s allowed empty", absoluteFileName, newName));
		}
		File file = new File(absoluteFileName);
		int idx = absoluteFileName.lastIndexOf(File.separator);
		String newAbsFileName = absoluteFileName.substring(0, idx + 1) + newName;
		byte[] buf = new byte[1024];
		try (
		InputStream is = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(is, buf.length);
		OutputStream os = new FileOutputStream(newAbsFileName);
		BufferedOutputStream bos = new BufferedOutputStream(os, buf.length);
				) {
			int len = 0;
			while((len = bis.read(buf)) > 0) {
				for (int i = 0; i < len; i++) {
					buf[i] = (byte) (buf[i] ^ key);
				}
				bos.write(buf);
			}
		} catch (Exception e) {
			logger.error("{} when copy file {}", e, absoluteFileName);
		}
	}
}
