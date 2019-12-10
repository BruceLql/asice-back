package com.mbfw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
	private final static SimpleDateFormat sdfMonthNum = new SimpleDateFormat("MM");

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}
	/**
	 * 获取MM格式  月份数
	 * 
	 * @return
	 */
	public static String getMonthNum() {
		return sdfMonthNum.format(new Date());
	}
	/**
	 * 获取YYYY-MM格式
	 * 
	 * @return
	 */
	public static String getMonth() {
		return sdfMonth.format(new Date());
	}


	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa = 0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	
	/**
	 * 
	 * @Description 
	 * @param date Date类型<br>
	 * @param days String类型，天数，正数表示获得date之后的days天的日期；负数表示获得date之前的days天的日期<br>
	 * @return （date+days）后的日期
	 */
	public static Date getCalculateDateD(Date date,String days){
		int daysInt = Integer.parseInt(days);
		return new Date(date.getTime()+((long)daysInt * 24 * 60 * 60 * 1000));
	}
	/**
	 * 
	 * @Description 
	 * @param date Date类型<br>
	 * @param minute String类型，分钟，正数表示获得date时间之后minute分钟的日期时间；负数表示获得date时间之前minute分钟的日期时间<br>
	 * @return （date+days）后的日期
	 */
	public static Date getCalculateDateF(Date date,String minute){
		long l_minute = Long.parseLong(minute);
		return new Date(date.getTime()+((long)l_minute * 60 * 1000));
	}
	
	/**
	 * 获取前月的第一天(格式：yyyy-MM-dd)
	 * @Description 
	 * @return
	 */
	public static String getQyFirstDay() {
		String firstDay="";
        Calendar cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        firstDay = sdfDay.format(cal_1.getTime());
		return firstDay;
	}
	
	/**
	 * 获取前月的最后一天(格式：yyyy-MM-dd)
	 * @Description 
	 * @return
	 */
	public static String getQyLastDay() {
		String lastDay="";
		Calendar cale = Calendar.getInstance();   
		cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
		lastDay = sdfDay.format(cale.getTime());
		return lastDay;
	}
	/**
	 * 获取本月的第一天(格式：yyyy-MM-dd)
	 * @Description 
	 * @return
	 */
	public static String getByFirstDay() {
		String firstDay="";
		Calendar c = Calendar.getInstance();    
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		firstDay= sdfDay.format(c.getTime());
		return firstDay;
	}
	
	/**
	 * 获取本月的最后一天(格式：yyyy-MM-dd)
	 * @Description 
	 * @return
	 */
	public static String getByLastDay() {
		String lastDay="";
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		lastDay =sdfDay.format(ca.getTime());
		return lastDay;
	}
	
	
	
	/**
	 * 字符串的日期格式的计算，即：结束日期-开始日期=结果(天数)
	 * @Description 
	 * @param smdate 开始日期
	 * @param bdate 结束日期
	 * @return
	 */
   public static int daysBetween(String smdate,String bdate){ 
           try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			   Calendar cal = Calendar.getInstance(); 
			   cal.setTime(sdf.parse(smdate)); 
			   long time1 = cal.getTimeInMillis(); 
			   cal.setTime(sdf.parse(bdate)); 
			   long time2 = cal.getTimeInMillis(); 
			   long between_days=(time2-time1)/(1000*3600*24); 
			   return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			e.printStackTrace();
		} 
        return 0;
   }
	
	
	
	
	public static void main(String[] args) {
		System.out.println(getDays());
		System.out.println(getAfterDayWeek("3"));
	}

}
