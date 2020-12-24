package com.mobile.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 *  Email
 *  邮件实体类
 */

public class Email implements Serializable{
	
	//发送者参数-对应数据库
	private String id;       //编码
	private String name;     //邮箱账号
	private String password; //邮箱密码
	private String userName; //使用者，用户名
    private String template; //用户使用的模板是那个
	
	//接收者参数-用于方便接收传入数据
    private String[] to;      //收件人
	private String subject;   //主题
    private String content;   //内容
    private String imgPath;   //图片路径
    private String imgId;     //图片编号
    private String[] ccUsers; //抄送
    private String filePath;  //文件（附件）路径
	
    private String emailTime;//插入更新时间
    

    public String getEmailTime() {
		return emailTime;
	}
	public void setEmailTime(String emailTime) {
		this.emailTime = emailTime;
	}
	private String formatCode;//邮箱格式类型编码

    
 
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getFormatCode() {
		return formatCode;
	}
	public void setFormatCode(String formatCode) {
		this.formatCode = formatCode;
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
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public String[] getCcUsers() {
		return ccUsers;
	}
	public void setCcUsers(String[] ccUsers) {
		this.ccUsers = ccUsers;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Email() {
		super();
	}
	@Override
	public String toString() {
		return "Email [id=" + id + ", name=" + name + ", password=" + password + ", userName=" + userName
				+ ", template=" + template + ", to=" + Arrays.toString(to) + ", subject=" + subject + ", content="
				+ content + ", imgPath=" + imgPath + ", imgId=" + imgId + ", ccUsers=" + Arrays.toString(ccUsers)
				+ ", filePath=" + filePath + ", emailTime=" + emailTime + ", formatCode=" + formatCode + "]";
	}
    

	
}
