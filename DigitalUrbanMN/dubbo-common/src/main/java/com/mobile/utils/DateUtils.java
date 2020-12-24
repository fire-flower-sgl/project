/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.mobile.utils;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;


/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2017-1-4
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH", "yyyy-MM",
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH", "yyyy.MM", 
		"yyyy年MM月dd日", "yyyy年MM月dd日 HH时mm分ss秒", "yyyy年MM月dd日 HH时mm分", "yyyy年MM月dd日 HH时", "yyyy年MM月",
		"yyyy"};
	
	/**
	 * 得到日期字符串 ，转换格式（yyyy-MM-dd）
	 */
	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}
	
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(long dateTime, String pattern) {
		return formatDate(new Date(dateTime), pattern);
	}
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatStringDate(String dateTime, String pattern) {
		return formatDate(new Date(dateTime), pattern);
	}
	public static String formatStringDate(String dateTime) {
		if(dateTime==null||dateTime.isEmpty())
			return null;
//		System.err.println(dateTime);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date parse = formatter.parse(dateTime);
			return formatter.format(parse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, String pattern) {
		String formatDate = null;
		if (date != null){
//			if (StringUtils.isNotBlank(pattern)) {
//				formatDate = DateFormatUtils.format(date, pattern);
//			} else {
//				formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
//			}
			if (StringUtils.isBlank(pattern)) {
				pattern = "yyyy-MM-dd";
			}
			formatDate = FastDateFormat.getInstance(pattern).format(date);
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}
    
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	public static String getDate2() {
		return getDate("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
//		return DateFormatUtils.format(new Date(), pattern);
		return FastDateFormat.getInstance(pattern).format(new Date());
	}
	
	/**
	 * 得到当前日期前后多少天，月，年的日期字符串
	 * @param pattern 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 * @param amont 数量，前为负数，后为正数
	 * @param type 类型，可参考Calendar的常量(如：Calendar.HOUR、Calendar.MINUTE、Calendar.SECOND)
	 * @return
	 */
	public static String getDate(String pattern, int amont, int type) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(type, amont);
//		return DateFormatUtils.format(calendar.getTime(), pattern);
		return FastDateFormat.getInstance(pattern).format(calendar.getTime());
	}
	
	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 * @param formatStr yyyyMMddHHmmss
	 * @return
	 */
	public static String getDateTime(String formatStr) {
		return formatDate(new Date(), formatStr);
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式   see to DateUtils#parsePatterns
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = System.currentTimeMillis()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = System.currentTimeMillis()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = System.currentTimeMillis()-date.getTime();
		return t/(60*1000);
	}
    
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		double a=afterTime - beforeTime;
		DecimalFormat df = new DecimalFormat("#0.000");
		return Double.parseDouble(df.format(a/(1000 * 60 * 60 * 24)));
	}
	
	/**
	 * 获取某月有几天
	 * @param date 日期
	 * @return 天数
	 */
	public static int getMonthHasDays(Date date){
//		String yyyyMM = new SimpleDateFormat("yyyyMM").format(date);
		String yyyyMM = FastDateFormat.getInstance("yyyyMM").format(date);
		String year = yyyyMM.substring(0, 4);
		String month = yyyyMM.substring(4, 6);
		String day31 = ",01,03,05,07,08,10,12,";
		String day30 = "04,06,09,11";
		int day = 0;
		if (day31.contains(month)) {
			day = 31;
		} else if (day30.contains(month)) {
			day = 30;
		} else {
			int y = Integer.parseInt(year);
			if ((y % 4 == 0 && (y % 100 != 0)) || y % 400 == 0) {
				day = 29;
			} else {
				day = 28;
			}
		}
		return day;
	}
	
	/**
	 * 获取日期是当年的第几周
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date){
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	public static String getWeekDay(String date) {
		Date weekDate=new Date();
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		//格式化当前日期
		try {
			weekDate=format.parse(date);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(weekDate);
		 // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
		int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
		if (1 == dayWeek) {  
			calendar.add(Calendar.DAY_OF_MONTH, -1);  
        } 
		calendar.setFirstDayOfWeek(Calendar.MONDAY);//美国是以周日为每周的第一天 现把周一设成第一天
		// 获得当前日期是一个星期的第几天  
        int day = calendar.get(Calendar.DAY_OF_WEEK); 
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);  
        String imptimeBegin = format.format(calendar.getTime());  
        System.out.println("所在周星期一的日期：" + imptimeBegin);  
        calendar.add(Calendar.DATE, 6);  
        String imptimeEnd = format.format(calendar.getTime());  
        System.out.println("所在周星期日的日期：" + imptimeEnd);  
        return imptimeBegin + "," + imptimeEnd;
	}

	/**
	 * 获取一天的开始时间（如：2015-11-3 00:00:00.000）
	 * @param date 日期
	 * @return
	 */
	public static Date getOfDayFirst(Date date) {
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取一天的最后时间（如：2015-11-3 23:59:59.999）
	 * @param date 日期
	 * @return
	 */
	public static Date getOfDayLast(Date date) {
		if (date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	/**
	 * 获取服务器启动时间
	 * @return date
	 */
	public static Date getServerStartDate(){
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return new Date(time);
	}
	
	/**
	 * 格式化为日期范围字符串
	 * @param beginDate 2018-01-01
	 * @param endDate 2018-01-31
	 * @return 2018-01-01 ~ 2018-01-31
	 * @author ThinkGem
	 */
	public static String formatDateBetweenString(Date beginDate, Date endDate){
		String begin = DateUtils.formatDate(beginDate);
		String end = DateUtils.formatDate(endDate);
		if (StringUtils.isNoneBlank(begin, end)){
			return begin + " ~ " + end;
		}
		return null;
	}
	
	/**
	 * 解析日期范围字符串为日期对象
	 * @param dateString 2018-01-01 ~ 2018-01-31
	 * @return new Date[]{2018-01-01, 2018-01-31}
	 * @author ThinkGem
	 */
	public static Date[] parseDateBetweenString(String dateString){
		Date beginDate = null; Date endDate = null;
		if (StringUtils.isNotBlank(dateString)){
			String[] ss = StringUtils.split(dateString, "~");
			if (ss != null && ss.length == 2){
				String begin = StringUtils.trim(ss[0]);
				String end = StringUtils.trim(ss[1]);
				if (StringUtils.isNoneBlank(begin, end)){
					beginDate = DateUtils.parseDate(begin);
					endDate = DateUtils.parseDate(end);
				}
			}
		}
		return new Date[]{beginDate, endDate};
	}
	
//	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
//		System.out.println(getWeekOfYear(new Date()));
//		System.out.println(formatDate(getOfDayFirst(parseDate("2015/3/6")),"yyyy-MM-dd HH:mm:ss.sss"));
//		System.out.println(formatDate(getOfDayLast(parseDate("2015/6/6")),"yyyy-MM-dd HH:mm:ss.sss"));
//	}
	
	//获取一个月的最后一天
	public static String getLastDayOfMonth(int year, int month) {     
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);     
        cal.set(Calendar.MONTH, month-1);     
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));  
        return  new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());  
    }   
	
	//获取一个月的第一天
    public static String getFirstDayOfMonth(int year, int month) {     
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);     
        cal.set(Calendar.MONTH, month-1);  
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));  
       return   new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());  
    }
    
    /* 
     * 将秒级时间戳转换为时间
     */
    public static String timeStampToDate(String time) {
    	long timeStamp = Long.parseLong(time);  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
    	String sd = sdf.format(new Date(timeStamp));   // 时间戳转换成时间
    	return sd;
	}
    
    /* 
     * 将毫秒级时间戳转换为时间
     */
    public static String secondTimeStampToDate(String time) {
    	long timeStamp = Long.parseLong(time)*1000;  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
    	String sd = sdf.format(new Date(timeStamp));   // 时间戳转换成时间
    	return sd;
	}
    
    /* 
     * 将时间转换为毫秒级时间戳
     */    
    public static String dateToTimeStamp(String time) throws ParseException{
        String stap;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(time);
        long ts = date.getTime();//获取时间的时间戳
        stap = String.valueOf(ts);
        return stap;
    }
    

	/** 
	 * 获取精确到秒的时间戳 
	 * @return 
	 */  
	public static int getSecondTimestamp(Date date){  
	    if (null == date) {  
	        return 0;  
	    }  
	    String timestamp = String.valueOf(date.getTime());  
	    int length = timestamp.length();  
	    if (length > 3) {  
	        return Integer.valueOf(timestamp.substring(0,length-3));  
	    } else {  
	        return 0;  
	    }  
	}
	
	/* 
     * 将时间转换为秒级时间戳
     */    
    public static String dateToSecondTimeStamp(String time) throws ParseException{
        String stap;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(time);
        long ts = date.getTime();//获取时间的时间戳
        stap = String.valueOf(ts/1000);
        return stap;
    }
    
    /**
     * 日期增加时间，年月日
     * TODO
     * @param date
     * @param year
     * @param month
     * @param day
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String addDateTime(String date,int year,int month,int day) {
    	Date dateOld =parseDate(date);
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTime(dateOld);
    	calendar.add(Calendar.YEAR, year); //把日期往后增加一年，整数往后推，负数往前移
    	calendar.add(Calendar.MONTH, month); //把日期往后增加一个月，整数往后推，负数往前移
    	calendar.add(Calendar.DAY_OF_MONTH, day); //把日期往后增加一天，整数往后推，负数往前移
    	Date dateNew = calendar.getTime();
    	return formatDateTime(dateNew);//date-->String
    }
    
    
    /**
     * 日期增加时间，年月日
     * TODO
     * @param date
     * @param year
     * @param month
     * @param day
     * @return yyyy-MM-dd
     */
    public static String addDate(String date,int year,int month,int day) {
    	Date dateOld =parseDate(date);
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTime(dateOld);
    	calendar.add(Calendar.YEAR, year); //把日期往后增加一年，整数往后推，负数往前移
    	calendar.add(Calendar.MONTH, month); //把日期往后增加一个月，整数往后推，负数往前移
    	calendar.add(Calendar.DAY_OF_MONTH, day); //把日期往后增加一天，整数往后推，负数往前移
    	Date dateNew = calendar.getTime();
    	return formatDate(dateNew);//date-->String
    }
    /**
     * 日期校验
     * @param date
     * @return
     */
    public static boolean check (String date) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyyMMddHHmmss");//括号内为日期格式，y代表年份，M代表年份中的月份（为避免与小时中的分钟数m冲突，此处用M），d代表月份中的天数
		try {
			sd.setLenient(false);//此处指定日期/时间解析是否不严格，在true是不严格，false时为严格
			sd.parse(date);//从给定字符串的开始解析文本，以生成一个日期
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
    /**
     * 两个日期相差秒数
     * @param date
     * @param data1
     * @return
     */
    public static long getNumberSeconds(String date,String data1){
		try {
			Date date3 = new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
			Date date4 = new SimpleDateFormat("yyyyMMddHHmmss").parse(data1);
			long l = date3.getTime()-date4.getTime()>0 ? date3.getTime()-date4.getTime():
				   date4.getTime()-date3.getTime();
			 return l/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    }
    
    /** 
     * 比较两个日期之间的大小 
     *  
     * @param d1 
     * @param d2 
     * @return 前者大于后者返回true 反之false 
     * @throws ParseException 
     */  
    public static boolean compareDate(String date1, String data2)  {  
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(data2);
	        if (dt1.getTime() >= dt2.getTime()) {
	            return true;  
	        }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;  
    }
    
    
    public static void main(String[] args) throws ParseException{
    	//boolean compareDate = compareDate(getDateTime(), "2020-7-12 0:00:00");
    	//System.err.println(compareDate);
//		try {
//			System.out.println(dateToSecondTimeStamp(("2019-11-06T11:34:59.57688").replace("T", " ")));
//			System.out.println(timeStampToDate("1572559844"));
//			System.out.println(getDistanceOfTwoDate(parseDate("2019-11-18"),parseDate("2019-11-19")));
//			System.out.println(addDateTime("2019-11-30",0,0,1));
//		} catch (ParseException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
    	//getWeekDay("2020-05-08");
    	double time=Double.parseDouble(dateToTimeStamp("2020-05-01 00:00:00"));
    	double time1=Double.parseDouble(dateToTimeStamp("2020-05-16 00:00:00"));
    	//System.err.println("0========"+compareDate("2020-05-11 01:00:00","2020-05-11 02:00:00"));
	}
}
