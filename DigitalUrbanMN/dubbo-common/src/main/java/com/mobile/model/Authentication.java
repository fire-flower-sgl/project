package com.mobile.model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
 /**
  * 处理163邮件发送
  * @author Administrator
  *
  */
public class Authentication extends Authenticator {
    private String username = null;
    private String password = null;
 
    public Authentication() {
 
    }
 
    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }
 
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}