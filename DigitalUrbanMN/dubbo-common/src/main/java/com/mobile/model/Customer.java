package com.mobile.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Customer
 * @Description TODO 客户信息
 * @Author admini
 * @Date 2019/7/23 10:35
 * @Version 1.0
 */
public class Customer implements Serializable{
    String customerId;//客户编码
    String customerName;//客户名称
    String customerStatus;//客户状态
    String customerIp;//客户ip地址
    String ctime;//创建日期
    String utime;//修改日期
    String legalPerson;//法人姓名
    String emergencyContact;//紧急联系人
    String emergencyContactMobile;//紧急联系人电话
    String socialCreditCode;//统一社会信用代码
    public Customer() {
    }
    
	public String getSocialCreditCode() {
		return socialCreditCode;
	}

	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public String getCustomerIp() {
		return customerIp;
	}
	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public String getEmergencyContactMobile() {
		return emergencyContactMobile;
	}
	public void setEmergencyContactMobile(String emergencyContactMobile) {
		this.emergencyContactMobile = emergencyContactMobile;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerStatus="
				+ customerStatus + ", customerIp=" + customerIp + ", ctime=" + ctime + ", utime=" + utime
				+ ", legalPerson=" + legalPerson + ", emergencyContact=" + emergencyContact
				+ ", emergencyContactMobile=" + emergencyContactMobile + ", socialCreditCode=" + socialCreditCode + "]";
	}
	
}
