package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@ApiModel("算子执行参数")
@SuppressWarnings("serial")
public class OperatorRunner implements Serializable {

	@ApiModelProperty("算子ID")
	@NotNull(message = "算子ID不能为空")
	@Min(value = 1, message = "算子ID错误")
	private Long operatorId;
	
//	@ApiModelProperty("输入源")
//	@NotNull(message = "数据集不能为空")
//	@Min(value = 1, message = "数据集ID错误")
	private Long dataSetId;
	
//	@ApiModelProperty("运行输出路径(输出文件名)")
//	@NotNull(message = "输出路径不能为空")
//	@NotBlank(message = "输出路径不能为空")
//	@Length(max = 32, message = "路径名称错误")
	private String outputName;
	
	@ApiModelProperty("运行时参数")
	@NotNull(message = "运行时参数不能为空")
	@Size(min = 1, max = 20, message = "运行时参数个数错误")
	private List<OperatorExecParams> execParams;
	
	public List<OperatorExecParams> getExecParams() {
		return execParams;
	}

	public void setExecParams(List<OperatorExecParams> execParams) {
		this.execParams = execParams;
	}

	@ApiModelProperty(hidden = true)
	private String userCode;

	public Long getDataSetId() {
		return dataSetId;
	}

	public void setDataSetId(Long dataSetId) {
		this.dataSetId = dataSetId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOutputName() {
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
}
