package com.mhtech.platform.msrv.sharing.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mhtech.common.tool.PageUtilToSql;
import com.mhtech.common.tool.Utilis;
import com.mhtech.platform.msrv.monitor.service.TaskService;
import com.mhtech.platform.msrv.pojo.Task;
import com.mhtech.platform.msrv.sharing.task.manager.ScheduleManager;
import com.mobile.model.Page;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${pageNo}")
	private int pageNo;
	
	@Value("${pageSize}")
	private int pageSize;
	
	@Value("${database.type}")
    private String databaseType;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcParameter;
	
	@Autowired
	ScheduleManager scheduleConfig;

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sql = "select taskId,taskName,taskCron,taksDesc taskDesc,beanName,methodName,status,lastStatus,creatTime,updateTime,parameters,creatUserCode,updateUserCode from sys_task ";
		String sqlWhere = " where 1=1 ";
		if (map.get("status") != null && !map.get("status").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and status=:status";
		}
		if (map.get("taskName") != null && !map.get("taskName").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and taskName like CONCAT('%',:taskName,'%')";
		}
		if (map.get("lastStatus") != null && !map.get("lastStatus").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and lastStatus = :lastStatus'";
		}
		if (map.get("creatTimeStart") != null && !map.get("creatTimeStart").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and creatTime >= :creatTimeStart";
		}
		if (map.get("creatTimeEnd") != null && !map.get("creatTimeEnd").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and creatTime <= :creatTimeEnd";
		}
		if (map.get("updateTimeStart") != null && !map.get("updateTimeStart").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and updateTime >=:updateTimeStart";
		}
		if (map.get("updateTimeEnd") != null && !map.get("updateTimeEnd").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and updateTime <= :updateTimeEnd";
		}
		if (map.get("lastTimeStart") != null && !map.get("lastTimeStart").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and lastTime >= :lastTimeStart";
		}
		if (map.get("lastTimeEnd") != null && !map.get("lastTimeEnd").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and lastTime <= :lastTimeEnd";
		}
		return jdbcParameter.queryForList(sql + sqlWhere,map);
	}

	@Override
	public int updateTask(Task task) {
		// TODO Auto-generated method stub
		if (task == null || task.getTaskId().trim().isEmpty())
			return 0;
		String sqlUpdate = "update sys_task set 1=1 ";
		String sqlWhere = " where taskId=:taskId";
		if (task.getTaskName() != null && !task.getTaskName().isEmpty()) {
			sqlUpdate = sqlUpdate + ",taskName=:taskName";
		}
		if (task.getBeanName() != null && !task.getBeanName().isEmpty()) {
			sqlUpdate = sqlUpdate + ",beanName=:beanName()";
		}
		if (task.getMethodName() != null && !task.getMethodName().isEmpty()) {
			sqlUpdate = sqlUpdate + ",methodName=:methodName";
		}
		if (task.getTaskDesc() != null && !task.getTaskDesc().isEmpty()) {
			sqlUpdate = sqlUpdate + ",taksDesc=:taskDesc";
		}else {
			sqlUpdate = sqlUpdate + ",taksDesc=NULL";
		}
		if (task.getTaskCron() != null && !task.getTaskCron().isEmpty()) {
			sqlUpdate = sqlUpdate + ",taskCron=:taskCron";
		}
		if (task.getStatus() != null && !task.getStatus().isEmpty()) {
			sqlUpdate = sqlUpdate + ",status=:status";
		}
		if (task.getLastStatus() != null && !task.getLastStatus().isEmpty()) {
			sqlUpdate = sqlUpdate + ",lastStatus=:lastStatus";
		}
		if (task.getLastTime() != null && !task.getLastTime().isEmpty()) {
			sqlUpdate = sqlUpdate + ",lastTime=:lastTime";
		}
//		if (task.getUpdateTime() != null && !task.getUpdateTime().isEmpty()) {
			sqlUpdate = sqlUpdate + ",updateTime=now() ";
//		}
		if (task.getUpdateUserCode() != null && !task.getUpdateUserCode().isEmpty()) {
			sqlUpdate = sqlUpdate + ",updateUserCode=:updateUserCode";
		}

		sqlUpdate = sqlUpdate.replace("1=1 ,", "");
		return jdbcParameter.update(sqlUpdate + sqlWhere,new BeanPropertySqlParameterSource(task));
	}

	@Override
	public int insertTask(Task task) {
		if (task == null)
			return 0;
		String sqlInsert = "insert into sys_task(taskId,taskName,taskCron,taksDesc,beanName,methodName,status,creatTime,updateTime,creatUserCode)"
				+ "values(:taskId,:taskName,:taskCron,:taskDesc,:beanName,:methodName,'1',now(),now(),:creatUserCode)";
		System.err.println(sqlInsert);
		return jdbcParameter.update(sqlInsert,new BeanPropertySqlParameterSource(task));
	}

	@Override
	public Page queryPage(Map<String, Object> map) {
		String sql = "select a.taskId,a.taskName,a.taskCron,a.taksDesc taskDesc,a.beanName,a.methodName,a.status,a.lastStatus,a.creatTime,a.lastTime,a.updateTime,a.parameters,a.creatUserCode,a.updateUserCode,\r\n"
				+ "b.parm_name AS statusName,c.parm_name AS lastStatusName " + " from sys_task a "
				+ "LEFT JOIN sys_parameter b ON a.status=b.parm_code AND b.parm_type='taskStatus' and b.parm_status = '1' "
				+ "LEFT JOIN sys_parameter c ON a.lastStatus=c.parm_code AND c.parm_type='performStatus' and c.parm_status = '1' ";
		String sqlCount = "select count(1) from sys_task ";
		String sqlWhere = " where 1=1 ";
		if (map.get("status") != null && !map.get("status").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and a.status=:status";
		}
		if (map.get("taskName") != null && !map.get("taskName").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and a.taskName like CONCAT('%',:taskName,'%')";
		}
		if (map.get("lastStatus") != null && !map.get("lastStatus").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and a.lastStatus =:lastStatus";
		}
		if (map.get("creatTimeStart") != null && !map.get("creatTimeStart").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and a.creatTime >= :creatTimeStart";
		}
		if (map.get("creatTimeEnd") != null && !map.get("creatTimeEnd").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and a.creatTime <= :creatTimeEnd";
		}
		if (map.get("updateTimeStart") != null && !map.get("updateTimeStart").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and a.updateTime >=:updateTimeStart";
		}
		if (map.get("updateTimeEnd") != null && !map.get("updateTimeEnd").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and a.updateTime <=:updateTimeEnd";
		}
		if (map.get("lastTimeStart") != null && !map.get("lastTimeStart").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and a.lastTime >=:lastTimeStart";
		}
		if (map.get("lastTimeEnd") != null && !map.get("lastTimeEnd").toString().isEmpty()) {
			sqlWhere = sqlWhere + " and a.lastTime <= :lastTimeEnd";
		}
		String sqlOrder = " order by if(isnull(lastTime),updateTime,lastTime) desc ,if(isnull(updateTime),creatTime,updateTime) desc, creatTime desc ";
		int pageNo = this.pageNo;
		int pageSize = this.pageSize;
		if (map.get("pageNo") != null && !map.get("pageNo").toString().isEmpty()) {
			pageNo = Integer.parseInt(map.get("pageNo").toString());
		}
		if (map.get("pageSize") != null && !map.get("pageSize").toString().isEmpty()) {
			pageSize = Integer.parseInt(map.get("pageSize").toString());
		}
		
		int customerPageCount = 0;
		try {
			customerPageCount = jdbcParameter.queryForObject(sqlCount + sqlWhere,map, Integer.class);
		} catch (Exception e) {
		}
		
		//新增----分页，传入页码控制
		if (pageNo <= 0) pageNo = this.pageNo;
		if (pageSize <= 0) pageSize = this.pageSize;	
		// 总页数
		
		int pageCount = customerPageCount % pageSize == 0 ? (customerPageCount / pageSize):(customerPageCount / pageSize + 1);
		// 当前页数大于总页数 将当前页数等于总页数
		if (pageNo > pageCount) {
				pageNo = pageCount;
		}
		
		
		
		
		
		Pageable pageable = new PageRequest(pageNo -1>0 ?pageNo -1:0, pageSize);
		String pageSql = PageUtilToSql.getPageSql(sql + sqlWhere + sqlOrder, pageable,databaseType);
		System.err.println(pageSql);
		List<Map<String, Object>> maps = jdbcParameter.queryForList(pageSql,map);
		
		List<Map<String, String>> list = Utilis.mapObjToString2(maps);
	
		Map<Object, ScheduledTask> task = scheduleConfig.getTask();
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> mp : maps) {
			String taskId = mp.get("taskId").toString();
			if (task.get(taskId) != null) {
				mp.put("runStatus", "1");
			} else {
				mp.put("runStatus", "0");
			}
			results.add(mp);
		}

		Page page = new Page(customerPageCount);
		page.setData(results);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	@Override
	public int deleteTask(Task task) {
		// TODO Auto-generated method stub
		if (task == null || task.getTaskId() == null || task.getTaskId().isEmpty())
			return 0;
		try {
			return jdbcTemplate.update(" delete from sys_task where taskId=?" , task.getTaskId());
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}

	@Override
	public int queryTaskCount(Task task) {
		// TODO Auto-generated method stub
		
		if(task==null) {
			return 0;
		}
		String sqlCount="select count(1) from sys_task where 1=1";
		String sqlWhere="";
		if(task.getMethodName()!=null&&!task.getMethodName().trim().isEmpty())
			sqlWhere=sqlWhere+" and methodName=:methodName";
		if(task.getBeanName()!=null&&!task.getBeanName().trim().isEmpty())
			sqlWhere=sqlWhere+" and beanName=:beanName";
		if(task.getTaskId()!=null&&!task.getTaskId().trim().isEmpty())
			sqlWhere=sqlWhere+" and taskId<>:taskId";
		
		try {
			return jdbcParameter.queryForObject(sqlCount+sqlWhere,new BeanPropertySqlParameterSource(task),Integer.class);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.TaskService#addTask(com.mhtech.platform.msrv.pojo.Task)
	 */
	@Override
	public void addTask(Task task) {
		scheduleConfig.addTask(task);
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.TaskService#modifyTask(com.mhtech.platform.msrv.pojo.Task)
	 */
	@Override
	public void modifyTask(Task task) {
		scheduleConfig.modifyTask(task);
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.TaskService#cancelTask(java.lang.String)
	 */
	@Override
	public void cancelTask(String taskId) {
		scheduleConfig.cancelTask(taskId);
		
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.TaskService#existTask(java.lang.String)
	 */
	@Override
	public boolean existTask(String taskId) {
		// TODO 自动生成的方法存根
		return scheduleConfig.existTask(taskId);
	}

	/* （非 Javadoc）
	 * @see com.mhtech.platform.msrv.monitor.service.TaskService#ExecutedImmediately(com.mhtech.platform.msrv.pojo.Task)
	 */
	@Override
	public boolean ExecutedImmediately(Task task) {
		// TODO 自动生成的方法存根
		return scheduleConfig.ExecutedImmediately(task);
	}


}
