package com.mobile.utils;

import java.io.File;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.alibaba.fastjson.JSON;
import com.mobile.model.Authentication;
import com.mobile.model.EmailParameter;

/**
 * 邮件工具类
 * 
 * @author mjl_
 * @version 2019-10.9
 */
public class EmailUtils {

	/**
	 * @ 创建发送器
	 * 
	 * @param name     邮箱账号
	 * @param password 邮箱密码
	 * @return
	 */
	public static JavaMailSenderImpl createMailSender(EmailParameter em) {

		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(em.getHost());
		sender.setProtocol(em.getProtocol());
		sender.setPort(em.getPort());
		sender.setUsername(em.getName());
		sender.setPassword(em.getPassword());
		sender.setDefaultEncoding("Utf-8");
		Properties p = new Properties();
		p.setProperty("mail.smtp.auth", "true");
		p.setProperty("mail.smtp.ssl.enable", "true");
		p.setProperty("mail.smtp.ssl.socketFactory.class", "com.sun.mail.util.MailSSLSocketFactory");
		p.setProperty("mail.smtp.ssl.socketFactory.fallback", "false");
		sender.setJavaMailProperties(p);
		return sender;
	}

	/**
	 * 单人发送-多人接收-文本
	 * 
	 * @param to       收件人
	 * @param subject  主题
	 * @param text     内容
	 * @param name     邮箱账号
	 * @param password 邮箱密码
	 */
	public static boolean sendSimpleMessage(EmailParameter em) {

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(em.getTo());
			message.setFrom(em.getName());
			message.setSubject(em.getSubject());
			message.setText(em.getContent());
			EmailUtils.createMailSender(em).send(message);
			// 成功
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	/**
	 * 单人发送-多人收件-多人抄送-文本
	 * 
	 * @param to       多个收件人
	 * @param subject  主题
	 * @param content  内容
	 * @param ccUsers  抄送给多人
	 * @param name     邮箱账号
	 * @param password 邮箱密码
	 */
	public static boolean sendSimpleMessage2(EmailParameter em) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();

			message.setTo(em.getTo());
			message.setBcc(em.getCcUsers());// 抄送人
			// message.setCc(); //秘密抄送（发件人，收件人，抄送人都不会看到抄送给谁了）
			message.setFrom(em.getName());
			message.setSubject(em.getSubject());
			message.setText(em.getContent());
			EmailUtils.createMailSender(em).send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 发送带静态资源的邮件:单人发送-多人收件-文本+图片
	 * 
	 * @param to       收件人
	 * @param subject  主题
	 * @param content  内容
	 * @param imgPath  图片路径
	 * @param imgId    图片编号
	 * @param name     邮箱账号
	 * @param password 邮箱密码
	 */
	public static boolean sendInlineResourceMail(EmailParameter em) {

		MimeMessage message = EmailUtils.createMailSender(em).createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(em.getName());
			helper.setTo(em.getTo());
			helper.setSubject(em.getSubject());
			helper.setText(em.getContent(), true); // 设置为true，没有的话图片时显示不了
			for (int i = 0; i < em.getImgPath().length; i++) {
				FileSystemResource file = new FileSystemResource(new File(em.getImgPath()[i]));
				helper.addInline(em.getImgId()[i], file);
			}
//		           FileSystemResource file=new FileSystemResource(new File(imgPath));
//		           helper.addInline(imgId,file);
			EmailUtils.createMailSender(em).send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 发送带附件的邮件:单人发送-多人收件-文本+附件
	 * 
	 * @param to       收件人
	 * @param subject  主题
	 * @param content  内容
	 * @param filePath 文件路径
	 * @param name     邮箱账号
	 * @param password 邮箱密码
	 */
	public static boolean sendAttachmentsMail(EmailParameter em) {
		MimeMessage message = EmailUtils.createMailSender(em).createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(em.getName());
			helper.setTo(em.getTo());
			helper.setSubject(em.getSubject());
			helper.setText(em.getContent());
			// 循环获取
			for (int i = 0; i < em.getFilePath().length; i++) {
				FileSystemResource file = new FileSystemResource(new File(em.getFilePath()[i]));
				String fileName = em.getFilePath()[i].substring(em.getFilePath()[i].lastIndexOf(File.separator));
				helper.addAttachment(fileName, file);
			}
			// 获取文件
			// FileSystemResource file=new FileSystemResource(new File(filePath));
			// 获取文件名
			// String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
			// helper.addAttachment(fileName,file);
			EmailUtils.createMailSender(em).send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 发送邮件 l63
	 * @param context 内容
	 * @param name    发件账号
	 * @param password  发件授权码
	 * @param subject   标题
	 * @param to        收件
	 * @return
	 * @throws MessagingException
	 */
	public static boolean send(EmailParameter em) throws MessagingException {
		
        //处理多个收件人
		StringBuilder sb = new StringBuilder();
		if (em.getTo() != null && em.getTo().length > 0) {
			for (int i = 0; i < em.getTo().length; i++) {
				if (i < em.getTo().length - 1) {
					sb.append(JSON.parse(em.getTo()[i])  + ",");
				} else {
					sb.append(JSON.parse(em.getTo()[i]) );
				}
			}
		}
		// 1
		Properties prop=new Properties();
		prop.put("mail.host",em.getHost() );
		prop.put("mail.smtp.host", em.getHost()); 
		prop.put("mail.transport.protocol", em.getProtocol());
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.localhost", "mail.digu.com"); //解决ip与域名不匹配
        // 2
		Authentication authentication=new Authentication(em.getName(),em.getPassword());
		Session session=Session.getInstance(prop,authentication);
		Transport ts=session.getTransport();
		
		//3.通过邮件用户名密码链接
		ts.connect(prop.getProperty(Integer.toString(em.getPort())), prop.getProperty(em.getName()), prop.getProperty(em.getPassword()));
		//4.创建邮件消息对象
		MimeMessage mess = new MimeMessage(session);
		try {
		
			mess.setFrom(em.getName()); // 设置邮件的发件人
			mess.setRecipients(Message.RecipientType.TO, sb.toString()); // 设置收件人
			//mess.addRecipients(MimeMessage.RecipientType.CC, em.getName());//抄送
			mess.setSubject(em.getSubject()); // 设置邮件标题---不能为空与敏感字--会被设置为-垃圾邮件-发送报错554 DT:SUM
			mess.setContent(em.getContent(), "text/html;charset=utf-8"); // 设置邮件内容和格式
			//5.发送邮件
			Transport.send(mess);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	
	
	

}
