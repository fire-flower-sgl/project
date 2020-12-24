package com.mhtech.platform.msrv.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="人员结果集",description="人员结果集")
public class SpPersonneVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	    @ApiModelProperty("人员名称")
	    private String pname;
	    @ApiModelProperty("手机号")
	    private String mobileno;
	    @ApiModelProperty("时间")
	    private Date createDate;
	    @ApiModelProperty("所属班次")
	    private String shift;
	    @ApiModelProperty("分页-每页条数")
	    @Min(0)    
	    private int pageSize;// 分页-每页条数
	    @ApiModelProperty("分页-当前页")
	    @Min(0)     
	   	private int pageNo;// 分页-当前页
	    @ApiModelProperty("邮箱")
	    private String  email;
	    
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public String getPname() {
			return pname;
		}
		public void setPname(String pname) {
			this.pname = pname;
		}
		public String getMobileno() {
			return mobileno;
		}
		public void setMobileno(String mobileno) {
			this.mobileno = mobileno;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public String getShift() {
			return shift;
		}
		public void setShift(String shift) {
			this.shift = shift;
		}
		public SpPersonneVO(String pname, String mobileno, Date createDate, String shift) {
			super();
			this.pname = pname;
			this.mobileno = mobileno;
			this.createDate = createDate;
			this.shift = shift;
		}
		public SpPersonneVO() {
			super();
		}
		public SpPersonneVO(int pageSize, int pageNo) {
			super();
			this.pageSize = pageSize;
			this.pageNo = pageNo;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public SpPersonneVO(String pname, String mobileno, Date createDate, String shift, String email) {
			super();
			this.pname = pname;
			this.mobileno = mobileno;
			this.createDate = createDate;
			this.shift = shift;
			this.email = email;
		}
	    
	    
	    

}
