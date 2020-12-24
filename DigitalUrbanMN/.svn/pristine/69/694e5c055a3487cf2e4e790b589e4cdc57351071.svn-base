package com.mhtech.platform.msrv.algorithm.service;

import java.util.List;

import com.mhtech.platform.msrv.pojo.AlTeamProjectDTO;
import com.mhtech.platform.msrv.pojo.AlTeamProjectVO;
import com.mhtech.platform.msrv.pojo.CurrentUser;
import com.mhtech.platform.msrv.pojo.ProjectAnalyzeFlows;
import com.mhtech.platform.msrv.pojo.ProjectExportParam;
import com.mhtech.platform.msrv.pojo.ProjectFile;

/**
 * 
 * 项目详情首页 al_team_project表
 * 
 * @author mjl
 *
 */

public interface IProjectDetailsHomepage {

	// 依据id查询-项目详情与团员集合
	AlTeamProjectVO selectProjectAndMember(Long id);

	// 依据id查询-项目详情
	AlTeamProjectVO select(Long id);

	// 修改
	int updateProject(AlTeamProjectDTO record);

	// 新增
	int insertProject(AlTeamProjectDTO record);

	// 删除
	int deleteProject(Long... key);

	// 查询所有项目
	List<AlTeamProjectVO> list(AlTeamProjectDTO record);

	// 查询 tags所有标签
	List<String> tags();

	// 项目编码唯一性判断
	boolean prjCodeOnly(String prjCode);
	
	/**
	 * 项目导出, 序列化项目对象生成文件返回
	 * @param param
	 * @return 
	 */
	ProjectFile exportTeamProject(ProjectExportParam param);
	
	/**
	 * 项目导入
	 * @param prj
	 * @return
	 */
	void importTeamProject(ProjectAnalyzeFlows prj, CurrentUser user);
}
