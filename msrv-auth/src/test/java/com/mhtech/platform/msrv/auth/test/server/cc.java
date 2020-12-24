package com.mhtech.platform.msrv.auth.test.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mhtech.platform.msrv.auth.MsrvAuthApplication;

@SpringBootTest(classes = { MsrvAuthApplication.class })
@RunWith(SpringRunner.class)
public class cc {
	
	@Test
	public void idyg() {
		 Date date=new Date();//取时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		 String dateString = formatter.format(date);
		 
		 System.out.println(dateString);
		
	}

}
