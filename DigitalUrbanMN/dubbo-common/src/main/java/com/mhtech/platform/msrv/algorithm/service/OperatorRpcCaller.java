package com.mhtech.platform.msrv.algorithm.service;

import java.util.List;



import com.mhtech.platform.msrv.pojo.DataSetVO;
import com.mhtech.platform.msrv.pojo.ListableOperatorParam;
import com.mhtech.platform.msrv.pojo.OperatorParamDef;
import com.mhtech.platform.msrv.pojo.OperatorParamDefOption;
import com.mhtech.platform.msrv.pojo.OperatorResBO;
import com.mhtech.platform.msrv.pojo.OperatorResListBO;
import com.mhtech.platform.msrv.pojo.OperatorRunner;
import com.mhtech.platform.msrv.pojo.OperatorUploader;
import com.mhtech.platform.msrv.pojo.ParamSearch;
import com.mhtech.platform.msrv.pojo.ParamType;

/**
 * 算子资源远程服务接口
 * @author GM
 */
public interface OperatorRpcCaller {

	/**
	 * 按算子类型返回列表
	 * @param param
	 * @return
	 */
	List<OperatorResListBO> grpOperators(ListableOperatorParam param);
	
	/**
	 * 禁用算子
	 * @param userCode
	 * @param operatorId
	 */
	void disable(String userCode, Long operatorId);
	
	/**
	 * 运行算子
	 * @param runner 算子ID
	 * @return 生成数据集对象
	 */
	List<DataSetVO> execOperator(OperatorRunner runner);
	
	OperatorResBO uploadOperator(OperatorUploader uploader);
	
	/**
	 * 设置算子执行参数
	 * @param userCode 当前用户
	 * @param operatorId 算子ID
	 * @param defs 参数集合
	 */
	void setOperatorDefinition(String userCode, Long operatorId, List<OperatorParamDef> defs);
	
	List<ParamType> listParamType(ParamSearch param);
	
	List<ParamType> listParamValue(String paramType);
	
	/**
	 * 查询算子参数定义
	 * @param userCode
	 * @param operatorId
	 * @return
	 */
	List<OperatorParamDef> listParamDef(String userCode, Long operatorId);
	
	/**
	 * 查询算子定义的运行参数及对应的可选值选项
	 * @param userCode
	 * @param operatorId
	 * @return
	 */
	List<OperatorParamDefOption> listParamOption(String userCode, Long operatorId);
	
	String parmDesc( String parmType);
}
