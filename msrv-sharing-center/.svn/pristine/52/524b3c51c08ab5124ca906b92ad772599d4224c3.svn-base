package com.mhtech.platform.msrv.sharing.task.manager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.mhtech.platform.msrv.monitor.service.IMonitorPlansService;
import com.mhtech.platform.msrv.pojo.MonitorPlansDTO;
import com.mhtech.platform.msrv.pojo.MonitorPlansVO;
import com.mhtech.platform.msrv.pojo.Task;
import com.mhtech.platform.msrv.sharing.utils.SpringUtil;

/**
 * 批处理 服务
 * 
 * @author admini
 *
 */
@Service
public class ScheduleManager implements SchedulingConfigurer {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IMonitorPlansService MPervice;
	
	@Value("${task.PoolSize}")
	int poolSize;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 受ScheduleManager管理的任务集合
	 */
	private final Map<Object, ScheduledTask> taskMap = new HashMap<>();
	/**
	 * 定时任务注册器
	 */
	private ScheduledTaskRegistrar taskRegistrar;

	/**
	 * 系统启动时获取任务注册对象
	 *
	 * @param taskRegistrar 任务注册对象
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		this.taskRegistrar = taskRegistrar;
		// 获取状态为1的所有任务
		MonitorPlansDTO mplan = new MonitorPlansDTO();
		mplan.setStatus(1);
		List<MonitorPlansVO> queryList = MPervice.getServerAlertRuleByParam(mplan);
		System.err.println("===任务size==="+queryList.size());
		
		for (MonitorPlansVO mp : queryList) {
			// 判断执行类和方法是否存在
			if(StringUtils.isEmpty(mp.getMainClass()) || StringUtils.isEmpty(mp.getMainClass()) || StringUtils.isEmpty(mp.getCron()))
				continue;
			Task task = new Task();// 初始化任务
			task.setTaskId(mp.getId().toString());
			task.setTaskCron(mp.getCron());
			task.setBeanName(mp.getMainClass());
			task.setMethodName(mp.getMethod());
			try {
				if (!StringUtils.isEmpty(mp.getParams())) {// 参数实例化为map
					Map parseObject = JSONObject.parseObject(mp.getParams(), Map.class);
					task.setParameters(parseObject);
				}
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("参数错误：" + e.getMessage() + " " + mp.getParams()+" "+mp.getId());
				Task tak = new Task(task.getTaskId(), "0", simpleDateFormat.format(new Date()));
//				taskDao.updateTask(tak);
				continue;
			}

			// 添加任务
			addTask(mp.getId().toString(), doTask(task), mp.getCron());
		}
		logger.info("scheduleManager has successfully acquired the taskRegistrar");
	}

	/**
	 * 业务执行方法
	 * 
	 * @return
	 */
	private Runnable doTask(Task task) {
		return new Runnable() {
			@Override
			public void run() {
				// 记录是否有错误
				boolean err = false;
				// 业务逻辑
				try {
					// 通过反射执行
					// 无参数执行方法
					if (task.getParameters() == null) {
						Object object = SpringUtil.getBean(task.getBeanName());
						Method method = object.getClass().getMethod(task.getMethodName());
						method.invoke(object);
					} else {// 有参数执行方法
						Object object = SpringUtil.getBean(task.getBeanName());
						Method method = object.getClass().getMethod(task.getMethodName(), Map.class);
						method.invoke(object, task.getParameters());
					}
					// 更新执行状态
					Task tk = new Task(task.getTaskId(), "1", simpleDateFormat.format(new Date()));
//					taskDao.updateTask(tk);
					logger.info("job:" + task.getTaskId() + " is running");
				} catch (NoSuchMethodException e) {
					err = true;
					logger.error(getExceptionInfo(e));
					e.printStackTrace();
				} catch (SecurityException e) {
					err = true;
					logger.error(getExceptionInfo(e));
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					err = true;
					logger.error(getExceptionInfo(e));
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					err = true;
					logger.error(getExceptionInfo(e));
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					err = true;
					logger.error(getExceptionInfo(e));
					e.printStackTrace();
				}
				// 错误操作
				if (err) {
					// 更新执行状态
					Task tk = new Task(task.getTaskId(), "0", simpleDateFormat.format(new Date()));
//					taskDao.updateTask(tk);
					logger.error("任务 " + task.getTaskId() + " 执行错误！！！！！！");
				}
			}
		};
	}

	/**
	 * 并行任务使用策略：多线程处理（配置线程数等）
	 *
	 * @return ThreadPoolTaskScheduler 线程池
	 */
	@Bean(destroyMethod = "shutdown")
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(poolSize);
		scheduler.setThreadNamePrefix("taskJOB-"); // 设置线程名开头
		scheduler.setAwaitTerminationSeconds(60);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}

	public boolean ExecutedImmediately(Task task) {
		// 业务逻辑
		try {
			// 通过反射执行
			// 无参数执行方法
			if (task.getParameters() == null) {
				Object object = SpringUtil.getBean(task.getBeanName());
				Method method = object.getClass().getMethod(task.getMethodName());
				method.invoke(object);
			} else {// 有参数执行方法
				Object object = SpringUtil.getBean(task.getBeanName());
				Method method = object.getClass().getMethod(task.getMethodName(), Map.class);
				method.invoke(object, task.getParameters());
			}
			// 执行状态更改
			Task tk = new Task(task.getTaskId(), "1", simpleDateFormat.format(new Date()));
//			taskDao.updateTask(tk);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 执行状态更改
			Task tk = new Task(task.getTaskId(), "0", simpleDateFormat.format(new Date()));
//			taskDao.updateTask(tk);
			e.printStackTrace();
			logger.error("occurs error when execute method ,message {}", e);
			return false;
		}
	}

	/**
	 * 添加新任务，如果存在key一致的任务，则取消原任务的执行，但添加新任务失败时原任务不会停止
	 *
	 * @param key      任务key
	 * @param runnable 新任务执行代码
	 * @param cron     新任务cron表达式
	 */
	public void addTask(Task tk) {
		addTask(tk.getTaskId(), doTask(tk), tk.getTaskCron());
	}

	/**
	 * 添加新任务，如果存在key一致的任务，则取消原任务的执行，但添加新任务失败时原任务不会停止
	 *
	 * @param key      任务key
	 * @param runnable 新任务执行代码
	 * @param cron     新任务cron表达式
	 */
	public void addTask(String key, Runnable runnable, String cron) {
		if (runnable != null && !StringUtils.isEmpty(cron)) {
			ScheduledTask oldTask = taskMap.get(key);
			taskMap.put(key, taskRegistrar.scheduleCronTask(new CronTask(runnable, cron)));
			if (oldTask != null) {
				oldTask.cancel();
			}
		}
	}

	/**
	 * 重置任务的执行时机+执行类，修改失败时任务仍使用原有执行时机
	 *
	 * @param key  任务key
	 * @param cron 任务新cron表达式
	 */
	public void modifyTask(String key, String cron) {
		ScheduledTask oldTask = taskMap.get(key);
		if (oldTask != null && !StringUtils.isEmpty(cron)) {
			taskMap.put(key, taskRegistrar.scheduleCronTask(new CronTask(oldTask.getTask().getRunnable(), cron)));
			oldTask.cancel();
		}
	}

	/**
	 * 重置任务的执行时机，修改失败时任务仍使用原有执行时机
	 * 
	 * @param tk
	 */
	public void modifyTask(Task tk) {
		System.err.println(taskMap.toString());
		System.err.println(tk.getTaskId());
		ScheduledTask oldTask = taskMap.get(tk.getTaskId().toString());
		if (oldTask != null && !StringUtils.isEmpty(tk.getTaskCron()) && !StringUtils.isEmpty(tk.getBeanName())
				&& !StringUtils.isEmpty(tk.getMethodName())) {
			taskMap.put(tk.getTaskId().toString(),
					taskRegistrar.scheduleCronTask(new CronTask(doTask(tk), tk.getTaskCron().toString())));
			oldTask.cancel();
		}
	}

	/**
	 * 取消任务执行
	 *
	 * @param key 任务key
	 */
	public void cancelTask(String key) {
		ScheduledTask task = taskMap.remove(key);
		if (task != null) {
			task.cancel();
		}
	}

	/**
	 * 查看是否存在任务
	 *
	 * @param key 任务key
	 * @return 如果任务存在返回true，否则返回false
	 */
	public boolean existTask(String key) {
		System.err.println(taskMap.toString());
		return taskMap.get(key) != null;
	}

	/**
	 * 获取注册中的任务信息
	 * 
	 * @return
	 */
	public Map<Object, ScheduledTask> getTask() {
		return this.taskMap;
	}
	/**
	 * 获取详细错误信息
	 * @param e
	 * @return
	 */
	public static String getExceptionInfo(Exception e){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        return baos.toString();
	}
}
