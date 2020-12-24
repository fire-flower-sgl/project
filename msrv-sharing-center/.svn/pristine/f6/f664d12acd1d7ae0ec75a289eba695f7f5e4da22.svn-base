package com.mhtech.platform.msrv.sharing.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mhtech.platform.msrv.pojo.AlertLogVO;
import com.mhtech.platform.msrv.pojo.ParamAlertVO;
import com.mhtech.platform.msrv.pojo.TypeAlter;
import com.mhtech.platform.msrv.sharing.dao.model.AlertLog;

@Mapper
public interface AlertLogMapper extends GenericMapper {
	int count(AlertLogVO alvo);
	
	/**
	 * 告警类型统计
	 * @param ta
	 * @return
	 */
	List<ParamAlertVO> totalParamAlert(TypeAlter ta);
	
	/**
	 * 告警类型占比统计
	 * @param ta
	 * @return
	 */
	List<ParamAlertVO> totalRateParamAlert(TypeAlter ta);
	
	/**
	 * 批量更新警告状态
	 * @param listAlertLog
	 */
	void batchUpdate(List<com.mhtech.platform.msrv.pojo.AlertLog> listAlertLog);

	/**
	 * 删除几天前的数据
	 * @param day 天数
	 * @return
	 */
    int delete(int day);
    
    /**
     * 查询最大最小id之间所有数据
     * @param max 最大id
     * @param min 最小id
     * @param eachItem 查询条数
     * @return
     */
	List<AlertLog> allLog(@Param("max") Long max ,@Param("min") Long min,@Param("eachItem") int eachItem);
	
	/**
	 * 获取时间段内的，总条数，最大id,最小id
	 * @param topTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	Map<String, Object> selectManMin(@Param("topTime")String topTime,@Param("endTime")String endTime);
  	
	//删除时间段数据
	int deleteDate(@Param("topTime")String topTime,@Param("endTime")String endTime);
  	
}