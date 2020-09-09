package com.limouren.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期时间工具类
 * 
 */

public class DateUtils {
	/**
	 * 常用时间格式
	 */
	public static final String Format_Date = "yyyy-MM-dd";
	public static final String Format_Time = "HH:mm:ss";
	public static final String Format_DateTime = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 默认的日期格式化器，格式为yyyy-MM-dd
	 */
	private final static SimpleDateFormat DEFAULT_DATE_FORMATER = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 默认的时间格式化器，格式为yyyy-MM-dd HH:mm:ss
	 */
	private final static SimpleDateFormat DEFAULT_DATETIME_FORMATER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 福特的时间格式化器，格式为yyyyMMdd-HH:mm:ss
	 */
	private final static SimpleDateFormat FORD_DATETIME_FORMATER = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSSSSS");

	/**
	 * 默认的日期格式化器，格式为yyyyMMddHHmmss
	 */
	private final static SimpleDateFormat DEFAULT_DATE_YMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 默认的日期格式化器，格式为yyyyMM
	 */
	private final static SimpleDateFormat DEFAULT_MONTH_YMDHMS = new SimpleDateFormat("yyyyMM");

	/**
	 * 默认的日期格式化器，格式为yyyyMMdd
	 */
	private final static SimpleDateFormat DEFAULT_DATE_YMD = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat DEFAULT_TIME_FORMATER = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");

	/**
	 * 用默认的日期格式，格式化一个Date对象
	 * 
	 * @param date
	 * @return yyyyMMddHHmmss字符串
	 * @throws ParseException
	 */
	public static String getStrYMDHMS(Date date) throws ParseException {
		return DEFAULT_DATE_YMDHMS.format(date);
	}

	/**
	 * 取得当前日期返回时间字符串格式为yyyyMMddHHmmss
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getYMDHMS() throws ParseException {
		Date date = new Date();
		return DEFAULT_DATE_YMDHMS.format(date);
	}

	/**
	 * 月份后推
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static String getMonthAfter(Date d, int month) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
//		now.set(Calendar.MONTH, month-1);
		now.add(Calendar.MONTH, month - 1);
		return DEFAULT_MONTH_YMDHMS.format(now.getTime());
	}

	/**
	 * 取得当前日期（只有日期，没有时间，或者可以说是时间为0点0分0秒）
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getCurrentDate() throws ParseException {
		Date date = new Date();
		date = DEFAULT_DATE_FORMATER.parse(DEFAULT_DATE_FORMATER.format(date));//
		return date;
	}

	/**
	 * 取得当前月时间(yyyyhh)
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getCurrentMonthDate() throws ParseException {
		Date date = new Date();
		date = DEFAULT_MONTH_YMDHMS.parse(DEFAULT_MONTH_YMDHMS.format(date));//
		return date;
	}

	/**
	 * 取得当前时间（包括日期和时间）
	 * 
	 * @return
	 */
	public static Date getCurrentDateTime() {
		Date date = new Date();
		return date;
	}

	/**
	 * 用默认的日期格式，格式化一个Date对象
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return date == null ? "" : DEFAULT_DATE_FORMATER.format(date);
	}

	public static String formatDateYMD(Date date) {
		return date == null ? "" : DEFAULT_DATE_YMD.format(date);
	}

	/**
	 * 根据传入的格式，将日期对象格式化为日期字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		String s = "";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			s = sdf.format(date);
		}

		return s;
	}

	/**
	 * 用默认的日期时间格式，格式化一个Date对象
	 *
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date) {
		return date == null ? "" : DEFAULT_DATETIME_FORMATER.format(date);
	}

	/**
	 * 用默认的日期时间格式，格式化一个Date对象
	 *
	 * @param date
	 * @return
	 */
	public static String formatTime(Long date) {
		return date == null ? "" : DEFAULT_DATETIME_FORMATER.format(date);
	}

	/**
	 * 根据传入的格式，将日期对象格式化为时间字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatTime(Date date, String format) {
		String s = "";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			s = sdf.format(date);
		}

		return s;
	}

	/**
	 * 日期后推
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 日期前推
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 利用默认的格式（yyyy-MM-dd）将一个表示日期的字符串解析为日期对象
	 * 
	 * @param s
	 * @return
	 * @throws RuntimeException
	 */
	public static Date parseDate(String s) {
		Date date = null;
		try {
			date = DEFAULT_DATE_FORMATER.parse(s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	public static Date parseDateTime(String s) {
		Date date = null;
		try {
			date = DEFAULT_DATE_YMDHMS.parse(s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 利用默认的格式（yyyyMM）将一个表示日期的字符串解析为日期对象
	 * 
	 * @param s
	 * @return
	 * @throws RuntimeException
	 */
	public static Date parseStringtoDate(String s) {
		Date date = null;
		try {
			date = DEFAULT_DATE_YMD.parse(s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 利用默认的格式（yyyyMMdd）将一个表示日期的字符串解析为日期对象
	 * 
	 * @param s
	 * @return
	 * @throws RuntimeException
	 */
	public static Date parseMonth(String s) {
		Date date = null;
		try {
			date = DEFAULT_MONTH_YMDHMS.parse(s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 将一个字符串，按照特定格式，解析为日期对象
	 * 
	 * @param s
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String s, String format) throws ParseException {
		Date date = null;
		try {
			date = (new SimpleDateFormat(format)).parse(s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 利用默认的格式（yyyy-MM-dd HH:mm:ss）将一个表示时间的字符串解析为日期对象
	 * 
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTime(String s) throws ParseException {
		return DEFAULT_DATETIME_FORMATER.parse(s);
	}

	/**
	 * 得到当前年份
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 得到当前月份（1至12）
	 * 
	 * @return
	 */
	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取yyyy-MM-dd格式的当前系统日期
	 * 
	 * @return
	 */
	public static String getCurrentDateAsString() {
		return new SimpleDateFormat(Format_Date).format(new Date());
	}

	/**
	 * 获取指定格式的当前系统日期
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 获取HH:mm:ss格式的当前系统时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		return new SimpleDateFormat(Format_Time).format(new Date());
	}

	/**
	 * 获取指定格式的当前系统时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 获取格式为yyyy-MM-dd HH:mm:ss的当前系统日期时间
	 * 
	 * @return
	 */
	public static String getCurrentDateTimeAsString() {
		return getCurrentDateTime(Format_DateTime);
	}

	public static int getDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取星期X中文名称
	 * 
	 * @param date
	 * @return
	 */
	public static String getChineseDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return getChineseDayOfWeek(cal.getTime());
	}

	/**
	 * 获取星期X中文名称
	 * 
	 * @param date
	 * @return
	 */
	public static String getChineseDayOfWeek(String date) {
		return getChineseDayOfWeek(parseDate(date));
	}

	/**
	 * 获取星期X中文名称
	 * 
	 * @param date
	 * @return
	 */
	public static String getChineseDayOfWeek(Date date) {
		int dateOfWeek = getDayOfWeek(date);
		if (dateOfWeek == Calendar.MONDAY) {
			return "星期一";
		} else if (dateOfWeek == Calendar.TUESDAY) {
			return "星期二";
		} else if (dateOfWeek == Calendar.WEDNESDAY) {
			return "星期三";
		} else if (dateOfWeek == Calendar.THURSDAY) {
			return "星期四";
		} else if (dateOfWeek == Calendar.FRIDAY) {
			return "星期五";
		} else if (dateOfWeek == Calendar.SATURDAY) {
			return "星期六";
		} else if (dateOfWeek == Calendar.SUNDAY) {
			return "星期日";
		}
		return null;
	}

	public static int getDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMaxDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static String getFirstDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat(Format_Date).format(cal.getTime());
	}

	public static int getDayOfYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	public static int getDayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	public static int getDayOfWeek(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfYear(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取指定格式的当前系统日期时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDateTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	public static String toString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_Date).format(date);
	}

	public static String toDateTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_DateTime).format(date);
	}

	public static String toString(Date date, String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(date);
	}

	public static String toTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_Time).format(date);
	}

	/**
	 * 时间戳转换
	 * 
	 * @param time
	 * @return
	 */
	public static String longTimeToDateTimeString(Long time) {
		SimpleDateFormat format = new SimpleDateFormat(Format_DateTime);
		String d = format.format(time);
		return d;
	}

	/**
	 * 时间戳转换
	 * 
	 * @param time
	 * @return
	 */
	public static Date longTimeToDateTime(Long time) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(Format_DateTime);
		String d = format.format(time);
		return parseTime(d);
	}

	/**
	 * 时间戳转换
	 * 
	 * @param time
	 * @return
	 */
	public static String longTimeToDateString(Long time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(Format_DateTime);
			String d = format.format(time);
			return d;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 当天最后一秒
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date dateToString(Date date) throws ParseException {
		int dayMis = 1000 * 60 * 60 * 24;
		long curMillisecond = date.getTime();
		long resultMis = curMillisecond + (dayMis - 1);
		Date resultDate = new Date(resultMis);
		return resultDate;
	}

	/**
	 * 把Ford的日期格式，格式化一个正常时间格式，往后推n年
	 * 
	 * @param date
	 * @return
	 */
	public static String fordFormatDatetoDate(String dateTime, int n) {
		dateTime = dateTime.replace("T", " ").replace("Z", "");
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(dateTime);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.HOUR, 8);// 往后推8小时，北京时间
			if (n != 0) {
				calendar.add(calendar.YEAR, n);// 把年往后推n年.整数往后推,负数往前移动
			}
			dateTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTimeInMillis());
			System.out.println(dateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateTime;
	}

	/**
	 * 用Ford的日期格式，格式化一个Long 日期对象
	 * 
	 * @param date
	 * @return
	 */
	public static String fordFormatDateTime(Long dateTime) {
		TimeZone timeZoneGMT = TimeZone.getTimeZone("GMT");
		FORD_DATETIME_FORMATER.setTimeZone(timeZoneGMT);
		String strDate = dateTime == null ? "" : FORD_DATETIME_FORMATER.format(new Date(dateTime));
		if (strDate != null) {
			strDate = strDate.replace('_', 'T') + "Z";
		}
//		System.out.println(strDate);
		return strDate;
	}

	/**
	 * 小时期后推
	 * 
	 * @param d
	 * @param hour
	 * @return
	 */
	public static Date getHourAfter(Date d, int hour) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) - hour);
		return now.getTime();
	}

	// 比较2个时间的年月日是否相同
	public static boolean sameDate(Date d1, Date d2) {
		if (null == d1 || null == d2)
			return false;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d1);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d2);
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		return cal1.getTime().equals(cal2.getTime());
	}

	/**
	 * 计算两个时间相差多少秒      endDate-startDate
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int calLastedTime(Date startDate,Date endDate) {
		long a = endDate.getTime();
		long b = startDate.getTime();
		int c = (int) ((a - b) / 1000);
		return c;
	}
	
	/**
	 * 判断时间格式 格式必须为“yyyy-MM-dd HH:mm:ss”
	 * 2004-2-30 是无效的
	 * 2003-2-29 是无效的
	 * @param sDate
	 * @return  true 正确    false 不正确
	 */
	public static boolean isLegalDate(String sDate) {
	    int legalLen = 19;
	    if ((sDate == null) || (sDate.length() != legalLen)) {
	        return false;
	    }
	    try {
	        Date date = DEFAULT_DATETIME_FORMATER.parse(sDate);
	        return sDate.equals(DEFAULT_DATETIME_FORMATER.format(date));
	    } catch (Exception e) {
	        return false;
	    }
	}

	public static void main(String[] args) {
		try {
			System.out.println(getHourAfter(new Date(), 72));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
