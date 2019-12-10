package com.mbfw.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	/**
	 * 随机生成六位数验证码
	 * 
	 * @return
	 */
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;// (Math.random()*(999999-100000)+100000)
	}

	/**
	 * 获取指定长度的随机正整数
	 * @param length 长度
	 * @return  随机正整数
	 */
    public static int getRandomNum(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
             random = random + 0.1;
        } for (int i = 0; i < length; i++) {
             num = num * 10;
        }
        return (int) ((random * num));
    }
    
    /**
	 * 生成时间戳(13位)和随机数。如1529461890080-387140
	 * @Description 
	 * @param separator 连接符。用于连接时间戳和随机数。null时连接符将为空字符串。
	 * @param n 随机数长度。如果为0则没有连接符和随机数。
	 * @return
	 */
	public static String getTimestamp(String separator,int n) {
		String id =""+new Date().getTime();
		if(n>0){
			if(separator==null)separator="";
			id+=separator+Tools.getRandomNum(n);
		}
		return id;
	}
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str 字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 得到当前日期时间（yyyy-MM-dd HH:mm:ss）字符串
	 */
	public static String getDateStr() {
		return date2Str(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 获取前月的第一天(格式：yyyy-MM-dd)
	 * @Description 
	 * @return
	 */
	public static String getQyFirstDay() {
		String firstDay="";
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
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
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
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
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
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
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
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
   /**
    * 返回两个时间间的秒数 
    * @param beginTime 开始时间
    * @param endTime   结束时间
    * @return
    */
   public static int getDistanceSec(Date beginTime, Date endTime) {
	   long a = endTime.getTime();
	   long b = beginTime.getTime();
	   int c = (int)((a - b) / 1000);
	   return c;
   }
   
	/**
	 * 把时间根据时、分、秒转换为时间段
	 * 
	 * @param StrDate
	 */
	public static String getTimes(String StrDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now;
		try {
			now = new Date();
			java.util.Date date = df.parse(StrDate);
			long times = now.getTime() - date.getTime();
			long day = times / (24 * 60 * 60 * 1000);
			long hour = (times / (60 * 60 * 1000) - day * 24);
			long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			StringBuffer sb = new StringBuffer();
			// sb.append("发表于：");
			if (hour > 0) {
				sb.append(hour + "小时前");
			} else if (min > 0) {
				sb.append(min + "分钟前");
			} else {
				sb.append(sec + "秒前");
			}
			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultTimes;
	}
	
	/**
	 * 将形如1515469588000的数字字符串转化成2018-01-09 11:46:28形式的日期时间字符串。<br>
	 * @return 返回转化的结果，如转化失败则返回入参本身<br>
	 */
	public static String getDateStrFromL(String LongStr){
		String s=LongStr;
		if(Tools.notEmpty(LongStr)){
			try {
				long l=Long.parseLong(LongStr.trim());
				s=date2Str(new Date(l));
			} catch (Exception e) {
				//System.out.println("Parameters are not Numbers and cannot be converted to dates.");
			}
		}
		return s;
	} 
	
	/**
	 * 将形如1515469588000的数字字符串转化成2018-01-09 11:46:28形式的日期时间。<br>
	 * @return null或转化的结果<br>
	 */
	public static Date getDateFromL(String LongStr){
		Date s=null;
		if(Tools.notEmpty(LongStr)){
			try {
				long l=Long.parseLong(LongStr.trim());
				s=new Date(l);
			} catch (Exception e) {
				//转化出错时
				//System.out.println("Parameters are not Numbers and cannot be converted to dates.");
				//出错，但入参是2018-01-09 11:46:28这种格式的字符串时
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					s=sdf.parse(LongStr.trim());
				} catch (Exception pe) {
				}
			}
		}
		return s;
	}
	
	/**
	 * 写txt里的单行内容
	 * 
	 * @param filePath 文件路径
	 * @param content 写入的内容
	 */
	public static void writeFile(String fileP, String content) {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if (filePath.indexOf(":") != 1) {
			filePath = File.separator + filePath;
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 判断是否为整数（包括负整数、0和正整数） 
	 * @param str 传入的字符串 
	 * @return 是整数返回true,否则返回false 
	 */
	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
	}
	
	/**
	 * 判断是否为数字（可有正负号。包括整数，小数） <br>
	 * @param str 传入的字符串  <br>
	 * @param isSign 是否可以含有正负号(-+) <br>
	 * @return 是数字返回true,否则返回false  <br>
	 */
	public static boolean isNum(String str,boolean isSign) {  
		String matche="^\\d+(\\.\\d+)?";
		if(isSign)matche="^(-|\\+)?\\d+(\\.\\d+)?";
		Pattern pattern = Pattern.compile(matche);  
		return pattern.matcher(str).matches();  
	}
	
	
	/**
	 * 检测KEY是否正确
	 * 
	 * @param paraname 传入参数
	 * @param FKEY 接收的 KEY
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean checkKey(String paraname, String FKEY) {
		paraname = (null == paraname) ? "" : paraname;
		return MD5.md5(paraname + DateUtil.getDays() + ",mbfw,").equals(FKEY);
	}

	/**
	 * 读取txt里的单行内容
	 * 
	 * @param filePath 文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}
	
	/**
	 * 读取txt里指定行数的内容
	 * 
	 * @param filePath 文件路径
	 * @param line 行数
	 */
	public static String readTxtFileByLine(String fileP,int line) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int n=0;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					n++;
					if(line==n){
						return lineTxt;
					}
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}
	
	/**
	 * 数字、字母和简体中文汉字时返回true，否则返回false。
	 */
    @SuppressWarnings("unused")
	private static boolean isValidChar(char ch) {
        if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z')|| (ch >= 'a' && ch <= 'z'))
            return true;
        if ((ch >= 0x4e00 && ch <= 0x7fff) || (ch >= 0x8000 && ch <= 0x952f))
            return true;// 简体中文汉字编码
        return false;
    }
    
	/**
	 * 过滤特殊字符。保留 ,.:%@_-和字母和数字和中文字符，其他的都替换为空字符串并去空格。
	 */
	public static String StringFilter(String str) throws Exception { 
		if(str==null)return null;
		// 只允许 .:%@_-和字母和数字和中文字符 
		String regEx ="[^ ,.:%@a-zA-Z0-9_-\u4E00-\u9FBF]";
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();//
	}
	/**
	 * str 待过滤的字符串。<br>
	 * regEx 正则式，满足该正则式的将被替换为空字符串。<br>
	 */
	public static String StringFilter(String str,String regEx) throws Exception { 
		if(str==null)return null;
		// 只允许字母和数字和中文字符 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();//
	}
	
	public static String StringFilter2(String str) throws Exception { 
		if(str==null)return null;
		// 清除掉所有特殊字符
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	/**
	 * 对int、long、float保留2位小数（四舍五入）
	 * @param obj 为int、long、float这些数字。但不能是字符串。
	 * @return null或格式后字符串
	 */
	public static String formatNumber(Object obj) { 
		if(obj==null)return null;
		DecimalFormat df = new DecimalFormat("0.00");
		String r=null;
		try {
			if(obj instanceof String){
				obj=Double.parseDouble(obj.toString());
			}
			r=df.format(obj);
		} catch (Exception e) {
		}
		return r;
	}
	public static Object formatNumber2(Object obj) { 
		if(obj==null)return null;
		DecimalFormat df = new DecimalFormat("0.00");
		String r=null;
		try {
			if(obj instanceof String){
				obj=Double.parseDouble(obj.toString());
			}
			r=df.format(obj);
		} catch (Exception e) {
		}
		if(r!=null){
			return Double.parseDouble(r);
		}
		return r;
	}
	
	
	/**
	 * 对int、long、float保留指定的小数位数（四舍五入）
	 * @param obj 为int、long、float这些数字。但不能是字符串。
	 * @param weiShu 指定的小数位数，大于等于0。
	 * @return null或格式后字符串
	 */
	public static String formatNumber(Object obj,int weiShu) { 
		if(obj==null)return null;
		String erg="#0";
		if(weiShu>0){
			erg="#0.";
			for(int i=0;i<weiShu;i++){
				erg=erg.concat("0");
			}
		}
		DecimalFormat df = new DecimalFormat(erg);
		String r=null;
		try {
			if(obj instanceof String){
				obj=Double.parseDouble(obj.toString());
			}
			r=df.format(obj);
		} catch (Exception e) {
		}
		return r;
	}
	public static Object formatNumber2(Object obj,int weiShu) { 
		if(obj==null)return null;
		String erg="#0";
		if(weiShu>0){
			erg="#0.";
			for(int i=0;i<weiShu;i++){
				erg=erg.concat("0");
			}
		}
		DecimalFormat df = new DecimalFormat(erg);
		String r=null;
		try {
			if(obj instanceof String){
				obj=Double.parseDouble(obj.toString());
			}
			r=df.format(obj);
		} catch (Exception e) {
		}
		if(r!=null){
			return Double.parseDouble(r);
		}
		return r;
	}
	
	
	/**
	 * 对int、long、float按照regEx格式化（四舍五入）
	 * @param obj 为int、long、float这些数字。但不能是字符串。
	 * @param regEx 格式后模板。"0.00"表示保留2位小数。"0.000"表示保留3位小数。
	 * @return null或格式后字符串
	 */
	public static String formatNumber(Object obj,String regEx) { 
		if(obj==null)return null;
		DecimalFormat df = new DecimalFormat(regEx);
		String r=null;
		try {
			r=df.format(obj);
		} catch (Exception e) {
		}
		return r;
	}
	
	/**
	 * 将含有逗号的字符串str以逗号分隔，每组用单引号包裹。<br>
	 * 字符串（we,34t,345）将被转化成字符串（'we','34t','345'）。<br>
	 * 适用于往Mapper.xml中in(${ids})函数传值。<br>
	 * @param str 字符串
	 * @return null或格式后字符串
	 */
	public static String formatInStr(String str) { 
		if(str!=null && !"".equals(str) && !"null".equals(str)){
			str=str.replaceAll("^,+|,+$", "");
			if(!"".equals(str)){
				return "'".concat(str.replaceAll("^,+|,+$", "").replaceAll(",\\s*", "','")).concat("'");
			}
		}
		return null;
	}
	
	/**
	 * 数据脱敏
	 * @Description 
	 * @param name
	 * @param i_
	 * @return
	 * @author suj
	 */
	public static String getInterval(String name,int i_){
		//concat((c.availableBalance div 500)*500,'-',((c.availableBalance div 500)+1)*500)
		String str=name;
		if(name!=null && !"".equals(name)){
			str="concat(("+name+" div "+i_+")*"+i_+",'至',(("+name+" div "+i_+")+1)*"+i_+")";
		}
		return str;
	}
	/**
	 * 数据脱敏
	 * @Description 
	 * @param name
	 * @param isInterval
	 * @param i_
	 * @return
	 * @author suj
	 */
	public static String getInterval2(String name,boolean isInterval,int i_){
		//concat((c.availableBalance div 500)*500,'-',((c.availableBalance div 500)+1)*500)
		String str=name;
		if(isInterval){
			if(name!=null && !"".equals(name)){
				str="concat(("+name+" div "+i_+")*"+i_+",'至',(("+name+" div "+i_+")+1)*"+i_+")";
			}
		}else{
			str=name;
		}
		return str;
	}
	
	/**
	 * 如果字符串中有%符，则对字符串进行decode
	 * @Description 
	 * @param str
	 * @return 
	 */
	public static String getURLDecoder(String str){
		if(str==null || str.isEmpty())return str;
		if(str.indexOf("%")>-1){
			try {
				str=java.net.URLDecoder.decode(str, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	
	public static void main(String[] args) {
		//System.out.println(getRandomNum());
		//System.out.println(getInterval("c.balance",220));
		Object ob=formatNumber2("-123.00000");
		System.out.println(ob);
	}

}
