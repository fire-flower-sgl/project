package com.mobile.model;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.stereotype.Component;

/**
 * 邮件请求参数实体类
 * @author Administrator
 *
 */
public class EmailParameter implements Serializable{

	private String name;   //邮件账号
	private String password;//邮件密码
	private String host;  //邮件服务方
	private String protocol;//邮件系统
	private int port;//端口
	
	private String[] to; //收件人
	private String subject;//主题
	private String content;//内容
	private String[] ccUsers;//抄送多人
	
	private String imgPath[];//图片路径
	private String imgId[];//图片编码;
	private String[] filePath;//文件路径
	
	private String imgHtml;// html模板
	
	
	public String getImgHtml() {
		return imgHtml;
	}
	public void setImgHtml(String imgHtml) {
		this.imgHtml = imgHtml;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getCcUsers() {
		return ccUsers;
	}
	public void setCcUsers(String[] ccUsers) {
		this.ccUsers = ccUsers;
	}
	public String[] getImgPath() {
		return imgPath;
	}
	public void setImgPath(String[] imgPath) {
		this.imgPath = imgPath;
	}
	public String[] getImgId() {
		return imgId;
	}
	public void setImgId(String[] imgId) {
		this.imgId = imgId;
	}
	public String[] getFilePath() {
		return filePath;
	}
	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "EmailParameter [name=" + name + ", password=" + password + ", host=" + host + ", protocol=" + protocol
				+ ", port=" + port + ", to=" + Arrays.toString(to) + ", subject=" + subject + ", content=" + content
				+ ", ccUsers=" + Arrays.toString(ccUsers) + ", imgPath=" + Arrays.toString(imgPath) + ", imgId="
				+ Arrays.toString(imgId) + ", filePath=" + Arrays.toString(filePath) + ", imgHtml=" + imgHtml + "]";
	}
	public EmailParameter(String name, String password, String host, String protocol, int port, String[] to,
			String subject, String content, String[] ccUsers, String[] imgPath, String[] imgId, String[] filePath) {
		super();
		this.name = name;
		this.password = password;
		this.host = host;
		this.protocol = protocol;
		this.port = port;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.ccUsers = ccUsers;
		this.imgPath = imgPath;
		this.imgId = imgId;
		this.filePath = filePath;
	}
	public EmailParameter(String name, String password, String host, String protocol, int port, String[] to,
			String subject, String content) {
		super();
		this.name = name;
		this.password = password;
		this.host = host;
		this.protocol = protocol;
		this.port = port;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}
	public EmailParameter(String name, String password, String host, String protocol, int port, String[] to,
			String subject, String content, String[] imgPath, String[] imgId, String imgHtml) {
		super();
		this.name = name;
		this.password = password;
		this.host = host;
		this.protocol = protocol;
		this.port = port;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.imgPath = imgPath;
		this.imgId = imgId;
		this.imgHtml = imgHtml;
	}
	public EmailParameter( String[] to,String subject,String imgHtml, String[] imgPath, String[] imgId,
			 String name, String password, String host, String protocol, int port) {
		super();
		this.name = name;
		this.password = password;
		this.host = host;
		this.protocol = protocol;
		this.port = port;
		this.to = to;
		this.subject = subject;
		this.imgPath = imgPath;
		this.imgId = imgId;
		this.imgHtml = imgHtml;
	}
	
	public EmailParameter( String[] to,String subject, String content, String[] filePath,
			               String name, String password, String host, String protocol, int port) {
		super();
		this.name = name;
		this.password = password;
		this.host = host;
		this.protocol = protocol;
		this.port = port;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.filePath = filePath;
	}
	
	public EmailParameter(String[] to,String[] ccUsers,String subject,String content,String name,String password,String host, String protocol,int port) {
		super();
		this.name = name;
		this.password = password;
		this.host = host;
		this.protocol = protocol;
		this.port = port;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.ccUsers = ccUsers;
	}
	public EmailParameter() {
		super();
	}
	public EmailParameter( String[] to,String subject, String content,
            String name, String password, String host, String protocol, int port) {
			super();
			this.name = name;
			this.password = password;
			this.host = host;
			this.protocol = protocol;
			this.port = port;
			this.to = to;
			this.subject = subject;
			this.content = content;
	}
	
	public EmailParameter( String[] to,String subject, String content,
            String name, String password) {
			super();
			this.name = name;
			this.password = password;
			this.to = to;
			this.subject = subject;
			this.content = content;
	}

	
	
	
	

}
