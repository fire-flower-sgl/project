package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mhtech.platform.log.pojo.MsrvLogDTO;
import com.mhtech.platform.log.pojo.ServerAlertRuleDTO;
import com.mhtech.platform.msrv.pojo.AlertInfoUpdate;
import com.mhtech.platform.msrv.pojo.AlertRuleVO;
import com.mhtech.platform.msrv.sharing.dao.model.MsrvLog;
import com.mhtech.platform.msrv.sharing.dao.model.ServerAlertRule;

@Mapper
public interface ServerAlertRuleMapper extends GenericMapper {
	
	/**
	 * 修改服务暂停加监控时间
	 * @param api
	 */
	int updateAlertTime(AlertInfoUpdate aip);
	
	/**
	 * 通过时间级别来搜索
	 * @param ar
	 * @return
	 */
	List<ServerAlertRule> selectByDuration(AlertRuleVO ar);
	
	int deleteByPrimaryKey(Long id);
	com.mhtech.platform.msrv.sharing.dao.model.ServerAlertRule  selectByPrimaryKey(Long id);
	
	//依据条件查询+分页
	List<ServerAlertRule> selectList(ServerAlertRuleDTO dto);
	//总条数
	int selectSum(ServerAlertRuleDTO dto);
}