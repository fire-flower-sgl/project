package com.mhtech.platform.msrv.sharing.task.job;

import java.util.Map;

import org.springframework.stereotype.Component;


@Component("secondTask")
public class secondTask {
 public void start(Map map) {
	 System.err.println("第二个任务开始了~~~~~~~~~~~~"+map.toString());
//	 ModelToSQL modelToSQL = new ModelToSQL(SqlType.UPDATE, new Task());
//	 String sqlBuffer2 = modelToSQL.getSqlBuffer();
	System.err.println("");
 }
}
