package com.mhtech.platform.msrv.algorithm.service;

import java.util.List;
import java.util.Map;

import com.mhtech.platform.msrv.pojo.CurrentUser;
import com.mhtech.platform.msrv.pojo.DataSetFile;
import com.mhtech.platform.msrv.pojo.DataSetPreview;
import com.mhtech.platform.msrv.pojo.DataSetUpload;
import com.mhtech.platform.msrv.pojo.DataSetRecordParam;
import com.mhtech.platform.msrv.pojo.DataSetRunner;
import com.mhtech.platform.msrv.pojo.DataSetUploadResult;
import com.mhtech.platform.msrv.pojo.DataSetVO;
import com.mhtech.platform.msrv.pojo.DictVO;
import com.mhtech.platform.msrv.pojo.PageVO;
import com.mhtech.platform.msrv.pojo.PageableDataSetBO;

/**
 * 数据集服务层
 * @author GM
 */
public interface DataSetRpcCaller {

	/**
	 * 上传数据集文件
	 * @param file 数据集字段、行记录、数据集名称
	 * @return 导入状态
	 */
	DataSetUploadResult dataSetUpload(DataSetFile file);
	
	/**
	 * 运行导入数据集
	 * @param dsr
	 */
	void loadDataSet(DataSetRunner dsr);
	
	/**
	 * 数据集分页
	 * @return
	 */
	PageVO<DataSetVO> pageableDataSet(PageableDataSetBO dsbo, CurrentUser user);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	PageVO<Map<Object, Object>> dataSetRecord(DataSetRecordParam param);
	
	/**
	 * 数据预览
	 * @param param
	 * @return
	 */
	DataSetPreview preview(DataSetUpload param);
	
	/**
	 * 保存数据集
	 * @param param
	 */
	void save(DataSetUpload param);
	
	/**
	 * 算子运行时保存数据集文件
	 * @param dataSetId
	 */
	void save(Long dataSetId, String dataSetName);
	
	/**
	 * 数据集字段列表
	 * @param userCode
	 * @param dataSetId
	 * @return
	 */
	List<DictVO> listDataSetField(String userCode, Long dataSetId);
	
	/**
	 * 删除数据集
	 * @param userCode
	 * @param dataSetId
	 */
	void delete(String userCode, List<Long> dataSetId);
	
	DataSetFile download(String userCode, Long dataSetId);
	
	/**
	 * 标记或取消标记星号
	 * @param userCode
	 * @param dataSetId
	 */
	void marking(String userCode, Long dataSetId);
}
