package com.mhtech.platform.msrv.algorithm.service;

import com.mhtech.platform.msrv.pojo.AnalyzeFlowBO;
import com.mhtech.platform.msrv.pojo.AnalyzeFlowNodeParam;
import com.mhtech.platform.msrv.pojo.AnalyzeFlowVO;
import com.mhtech.platform.msrv.pojo.CurrentUser;
import com.mhtech.platform.msrv.pojo.PageVO;
import com.mhtech.platform.msrv.pojo.PageableAnalyzeFlowBO;
import com.mhtech.platform.msrv.pojo.PanelNode;

/**
 * 分析流RPC接口
 * @author GM
 */
public interface AnalyzeFlowRpcCaller {

	/**
	 * 查询视图面板的流程节点信息
	 * @param param 
	 * @return 节点属性, 包含面板坐标
	 */
	PanelNode findPanelNode(AnalyzeFlowNodeParam param);
	
	/**
	 * 保存分析流
	 * @param afbo
	 */
	void addAnalyzeFlow(AnalyzeFlowBO afbo);
	
	/**
	 * 分析流查询
	 * @param pageable
	 * @return
	 */
	PageVO<AnalyzeFlowVO> listAnalyzeFlow(PageableAnalyzeFlowBO pageable, CurrentUser user);
	
	//是否标星
	int updateIsSign(Boolean isSign,long  id);
	
	void deleteAnalyze(String userCode, AnalyzeFlowNodeParam param);
}
