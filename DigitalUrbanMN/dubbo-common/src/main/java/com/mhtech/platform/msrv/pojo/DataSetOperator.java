package com.mhtech.platform.msrv.pojo;

/**
 * 数据集操作行为
 * @author GM
 */
public enum DataSetOperator {
	
	;
	/**
	 * 预览数据集
	 * @author GM
	 */
	public interface Preview {}
	/**
	 * 保存数据集
	 * @author GM
	 */
	public interface Save {}
	/**
	 * 运行数据集
	 * @author GM
	 */
	public interface Run {}
}
