package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.mhtech.platform.msrv.pojo.DataSetOperator.Preview;
import com.mhtech.platform.msrv.pojo.DataSetOperator.Save;

/**
 * 数据集预览对象
 * @author GM
 */
@ApiModel("数据集预览对象")
@SuppressWarnings("serial")
public class DataSetUpload implements Serializable {

	@ApiModelProperty("sheet名称")
	@NotNull(message = "sheet不能为空", groups = { Save.class, Preview.class})
	private Short sheetIdx;
	@ApiModelProperty("表头行索引位")
	@NotNull(message = "表头不能为空", groups = { Save.class, Preview.class})
	private Short headNum;
	@ApiModelProperty("预览行数")
	@NotNull(message = "预览行数错误", groups = { Preview.class})
	@Min(value = 1, message = "预览行数错误", groups = { Preview.class})
	@Max(value = 10, message = "预览行数错误", groups = { Preview.class})
	private Short previewRows;
	@ApiModelProperty("文件编码")
	@NotNull(message = "文件编码不能为空", groups = { Save.class, Preview.class})
	@NotBlank(message = "文件编码不能为空", groups = { Save.class, Preview.class})
	@Length(max = 32, message = "文件编码错误", groups = { Save.class, Preview.class})
	private String fileCode;
	@ApiModelProperty("数据集ID")
	@NotNull(message = "数据集ID不能为空", groups = { Save.class, Preview.class })
	@Min(value = 1, message = "数据集ID错误", groups = { Save.class, Preview.class })
	private Long dataSetId;
	@ApiModelProperty(hidden = true)
	private String userCode;
	
	@ApiModelProperty("字段类型")
	@NotNull(message = "字段类型不能为空", groups = Save.class)
	@Size(min = 1, max = 50, message = "字段数量过长", groups = Save.class)
	private List<DefineDataSetType> defineTypes;
	
	@ApiModelProperty("数据集名称, 保存时必填")
	@NotNull(message = "数据集不能为空", groups = Save.class)
	@NotBlank(message = "数据集不能为空", groups = Save.class)
	@Length(max = 64, message = "数据集名称超长", groups = Save.class)
	private String dataSetName;
	@ApiModelProperty("描述")
	@Length(max = 255, message = "描述内容过长", groups = Save.class)
	private String memo;
	
	@ApiModelProperty("数据集LOGO")
	@NotNull(message = "数据集logo不能为空", groups = Save.class)
	@NotBlank(message = "数据集logo不能为空", groups = Save.class)
	@Length(max = 255, message = "logo连接超长", groups = Save.class)
	private String logo;
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public List<DefineDataSetType> getDefineTypes() {
		return defineTypes;
	}
	public void setDefineTypes(List<DefineDataSetType> defineTypes) {
		this.defineTypes = defineTypes;
	}
	public Short getSheetIdx() {
		return sheetIdx;
	}
	public void setSheetIdx(Short sheetIdx) {
		this.sheetIdx = sheetIdx;
	}
	public Short getHeadNum() {
		return headNum;
	}
	public void setHeadNum(Short headNum) {
		this.headNum = headNum;
	}
	public Short getPreviewRows() {
		return previewRows;
	}
	public void setPreviewRows(Short previewRows) {
		this.previewRows = previewRows;
	}
	public String getFileCode() {
		return fileCode;
	}
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
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
	public String getDataSetName() {
		return dataSetName;
	}
	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
