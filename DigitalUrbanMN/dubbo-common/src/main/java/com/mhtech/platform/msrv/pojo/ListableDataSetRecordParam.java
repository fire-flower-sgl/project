package com.mhtech.platform.msrv.pojo;

import java.util.List;

import org.springframework.lang.NonNull;

/**
 * 数据集对应表记录查询
 * @author GM
 */
@SuppressWarnings("serial")
public class ListableDataSetRecordParam extends PageBO {

	@NonNull
	private Long dataSetId;
	
	/**
	 * 数据集对应的表名
	 */
	@NonNull
	private String tableName;
	
	@NonNull
	private List<String> columns;
	private String teamCode;

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public Long getDataSetId() {
		return dataSetId;
	}

	public void setDataSetId(Long dataSetId) {
		this.dataSetId = dataSetId;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
