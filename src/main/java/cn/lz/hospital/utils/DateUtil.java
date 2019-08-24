package cn.lz.hospital.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jodd.util.StringUtil;

/**
* @Description:    时间工具类
* @Author:         zhuwc
* @CreateDate:     2019/1/17 16:54
* @Version:        1.0
*/
public class DateUtil {

	public static final DateFormat dateStringFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static final DateFormat dateTimeStringFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

	public static final DateFormat fileNameDateStringFromcat = new SimpleDateFormat("kk_mm_ss_SS");
	public static final DateFormat timeStringFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat noSecondsStringFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static final DateFormat monthStringFormat = new SimpleDateFormat("yyyy-MM");

	public static final DateFormat slashDateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private static final String str_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	private static final String str_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	private static final String str_MMddHHmmss = "MMddHHmmss";

	public static String getStrDate(Date date, String format) {
		if (date == null) {
			date = new Date();
		}
		if (format == null) {
			format = "yyyyMMddHHmmss";
		}
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public static Date getDateFormat(String strdata, String format) {
		if (ValidateUtil.isnullStr(strdata)) {
			return null;
		}
		try {
			return new SimpleDateFormat(format).parse(strdata);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		return dateStringFormat.format(date);
	}

	/**
	 * 获取月份字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthString(Date date) {
		return monthStringFormat.format(date);
	}

	/**
	 * 月份开始
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateWithOutTime(Date date) {
		try {
			return dateStringFormat.parse(dateStringFormat.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPostDateTime(Date date) {
		try {
			return timeStringFormat.parse(timeStringFormat.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 以友好的方式显示时间
	 *
	 * @param time
	 * @return
	 */
	public static String friendly_time(Date time) {
		if (time == null)
			return "未知时间";
		int ct = (int) ((System.currentTimeMillis() - time.getTime()) / 1000);
		if (ct < 3600)
			return Math.max(ct / 60, 1) + "分钟前";
		if (ct >= 3600 && ct < 86400)
			return ct / 3600 + "小时前";
		if (ct >= 86400 && ct < 2592000) { // 86400 * 30
			int day = ct / 86400;
			return day == 1 ? "昨天" : (day + "天前");
		}
		if (ct >= 2592000 && ct < 31104000) // 86400 * 30
			return ct / 2592000 + "个月前";
		return ct / 31104000 + "年前";
	}

	/**
	 * lja
	 * 
	 * @param time
	 * @return [今天||昨天||前天||更前] HH:mm;
	 */
	public static String friendtime(Date time) {
		String unknown = "未知时间";
		if (time == null)
			return unknown;
		try {
			int ct = (int) ((System.currentTimeMillis() - time.getTime()) / 1000);
			if (ct < 3600) {
				return Math.max(ct / 60, 1) + "分钟前";
			}
			String stringTime = format("yyyy-MM-dd").format(time);
			Calendar c = Calendar.getInstance();
			if (stringTime.equals(format("yyyy-MM-dd").format(c.getTime()))) {
				return format("今天 HH:mm").format(time);
			}
			c.add(Calendar.DATE, -1);
			if (stringTime.equals(format("yyyy-MM-dd").format(c.getTime()))) {
				return format("昨天 HH:mm").format(time);
			}
			c.add(Calendar.DATE, -1);
			if (stringTime.equals(format("yyyy-MM-dd").format(c.getTime()))) {
				return format("前天 HH:mm").format(time);
			}
			// 判断文章是否是今年的如不是就在前边加年份
			if (!format("yyyy").format(time).equals(Calendar.getInstance().get(Calendar.YEAR) + "")) {
				return format("yyyy-MM-dd HH:mm").format(time);
			}
			return format("MM-dd HH:mm").format(time);
		} catch (Exception e) {
			return unknown;
		}
	}

	/**
	 * 格式化日期
	 * 
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat format(String format) {
		return new SimpleDateFormat(format);
	}

	/**
	 * 获取时间差
	 * 
	 * @param comeDate
	 * @param toDate
	 * @return
	 */
	public static int timeDiffence(Date comeDate, Date toDate) {
		long timeDiffence = (comeDate.getTime() - toDate.getTime()) / 1000;
		return (int) timeDiffence;
	}

	/**
	 * 字符串转日期
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取周一日期
	 * 
	 * @return
	 */
	public static Date getMonday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(cal.getTime());
		try {
			return df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}
	/**
	 * 获取指定日期周一日期
	 * 
	 * @return
	 */
	public static Date getSpecifiedMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(cal.getTime());
		try {
			return df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 获取周日日期
	 * 
	 * @return
	 */
	public static Date getSunday() {
		Calendar cal = Calendar.getInstance();
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(cal.getTime());
		try {
			return df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 上周一日期
	 * 
	 * @return
	 */
	public static Date getUpMonday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String str = df.format(cal.getTime());
		try {
			return df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 获取上周日日期
	 * 
	 * @return
	 */
	public static Date getUpSunday() {
		Calendar cal = Calendar.getInstance();
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(cal.getTime());
		try {
			return df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 过去或将来几天时间的23：59：59
	 * 
	 * @return time 将来或者过去的几天
	 */
	public static Date getDay_23_59_59(int time) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, time);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(cal.getTime());

		try {
			return df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 过去或将来几天时间00：00：00
	 * 
	 * @return time 将来或者过去的几天
	 */
	public static Date getDay_00_00_00(int time) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, time);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String str = df.format(cal.getTime());

		try {
			return df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 获取日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToString(Date date) {
		return noSecondsStringFormat.format(date);
	}

	/**
	 * 获取日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return timeStringFormat.format(date);
	}

	/**
	 * 获取当前系统日期
	 * 
	 * @return
	 */
	public static String nowDateToString() {
		return timeStringFormat.format(new Date());
	}

	/**
	 * 获取日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToString(Date date, DateFormat dateFormat) {
		return dateFormat.format(date);
	}

	/**
	 * 过去几个小时的时间
	 * 
	 * @return
	 */
	public static Date getOverTime(int time) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, time);
		Date date = cal.getTime();
		return date;
	}

	/**
	 * 过去或将来几天时间
	 * 
	 * @return time 将来或者过去的几天
	 */
	public static Date getDayOverTime(int time) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, time);
		Date date = cal.getTime();
		return date;
	}

	/**
	 * 获取某一时间后五天
	 * 
	 * @param time
	 * @return
	 */
	public static Date getDayOverTime(Date date, int time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, time);
		Date date1 = cal.getTime();
		return date1;
	}

	/**
	 * 获取过去火将来某个月
	 * 
	 * @param time
	 * @return
	 */
	public static Date getMonthOverTime(int time) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, time);
		Date date1 = cal.getTime();
		return date1;
	}

	/**
	 * 获取过去火将来某个月
	 * 
	 * @param time
	 * @return
	 */
	public static Date getMonthOverTime(Date date, int time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, time);
		Date date1 = cal.getTime();
		return date1;
	}

	/**
	 * 获取下个月第一天
	 * @throws Exception
	 */
	public static  Date getNextFirstMonthdate(Date date) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 时间比较
	 * 
	 * @param date
	 * @return
	 */
	public static boolean complane(Date date) {
		long time = date.getTime() - getOverTime(-12).getTime();

		return time > 0 ? true : false;
	}

	/**
	 * 判断是否在60分钟内
	 * 
	 * @param date
	 * @return
	 */
	public static boolean checkIn60Minute(Date date) {
		long time = new Date().getTime() - date.getTime();

		long diffent = time / 1000;
		System.out.println(diffent);
		return time / 1000 < 3600 ? true : false;
	}

	/**
	 * 倒计时
	 */
	public static String countdown(Date date) {
		String dateStr = "";
		long nowTime = new Date().getTime();
		long dateTime = date.getTime() - nowTime;
		long day = dateTime / (1000 * 60 * 60 * 24);
		long hour = (dateTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		if (day > 0) {
			dateStr = String.valueOf(day) + "天";
		}
		if (hour > 0) {
			dateStr += String.valueOf(hour) + "小时";
		}
		return StringUtil.isNotBlank(dateStr) ? dateStr : "0天";
	}

	/**
	 * 支付时间离现在过去多久
	 * 
	 * @param date
	 * @return
	 */
	public static String countdownFromNow(Date date) {
		String dateStr = "";
		long nowTime = new Date().getTime();
		long dateTime = nowTime - date.getTime();
		long day = dateTime / (1000 * 60 * 60 * 24);
		long hour = (dateTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		if (day > 0) {
			dateStr = String.valueOf(day) + "天";
		}
		if (hour > 0) {
			dateStr += String.valueOf(hour) + "小时";
		}
		return StringUtil.isNotBlank(dateStr) ? dateStr : "0小时";
	}

	// 获得当天几点时间
	public static Date getTimesmorning(int time) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, time);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取日期中的月
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String month = cal.get(Calendar.YEAR) + "";
		return month;
	}

	/**
	 * 获取日期中的月
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String month = cal.get(Calendar.MONTH) + 1 + "";
		return month;
	}

	/**
	 * 获取日期中的日
	 * 
	 * @param date
	 * @return
	 */
	public static String getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String day = cal.get(Calendar.DAY_OF_MONTH) + "";
		return day;
	}

	/**
	 * 几年前或几年后
	 * 
	 * @param time
	 * @return
	 */
	public static Date getYearOverTime(int time) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, time);
		Date date = cal.getTime();
		return date;
	}

	public static String getYearMonthDay(Date date) {
		String str = getYear(date) + "年" + getMonth(date) + "月" + getDay(date) + "日";
		return str;
	}
	public static Date getMonthBeginDate() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.DAY_OF_MONTH, 1);
			String last = format.format(ca.getTime());
			return format2.parse(last + " 00:00:00:999");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date getMonthLastDate() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			String last = format.format(ca.getTime());
			return format2.parse(last + " 23:59:59:999");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取上月第一天的2016/02/01 00:00:00:000
	 *
	 * @return
	 */
	public static Date getLastMonthFirstDate() {
		// 获取前月的第一天
		// 获取当前日期
		Calendar cal_1 = Calendar.getInstance();
		cal_1.add(Calendar.MONTH, -1);
		// 设置为1号,当前日期既为本月第一天
		cal_1.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format2 = new SimpleDateFormat(str_yyyyMMddHHmmssSSS);
		String firstDay = format.format(cal_1.getTime());
		try {
			return format2.parse(firstDay + "000000000");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取上月最后时刻的2016/02/29 23:59:59:999
	 *
	 * @return
	 */
	public static Date getLastMonthEndDate() {
		// 获取前月的最后一天
		Calendar cale = Calendar.getInstance();
		// 设置为1号,当前日期既为本月第一天
		cale.set(Calendar.DAY_OF_MONTH, 0);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format2 = new SimpleDateFormat(str_yyyyMMddHHmmssSSS);
		String lastDay = format.format(cale.getTime());
		try {
			return format2.parse(lastDay + "235959999");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date preDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public static Date getStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date getYesterdayDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		return time;
	}

	/**
	 * 获取指定日期加减时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateAdditionSubtraction(Date date, Integer beforeDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, beforeDay);
		Date time = cal.getTime();
		return time;
	}
	/**
	 * 获取指定日期加减分钟
	 *
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateAdditionSubtractionMinute(Date date, Integer minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		Date time = cal.getTime();
		return time;
	}

	public static boolean isSunday(Date bdate) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else
			return false;
	}



	/** * 锁对象，可以为任意对象 */
	private static Object lockObj = "lockerOrder";
	/** * 订单号生成计数器 */
	private static long orderNumCount = 0L;

	/** * 每毫秒生成订单号数量最大值 */
	private static int maxPerMSECSize = 99;

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmsss为格式的当前系统时间
	 */
	public static String getOrderNum() {
		String addr = null;
		String host = "";

		String finOrderNum = "";
		synchronized (lockObj) {
			// try
			// {
			// addr = InetAddress.getLocalHost().getHostAddress();
			// if (addr != null)
			// {
			// String[] ip = addr.split("\\.");
			// StringBuffer suffix = new StringBuffer();
			// for (String sec : ip)
			// {
			// suffix.append(new
			// DecimalFormat("000").format(Integer.parseInt(sec)));
			// }
			// System.out.println("订单号ip---------------------------------------------："+suffix);
			// String ipstr = String.valueOf(suffix.toString());
			//
			// host = ipstr.substring(ipstr.length() - 3, ipstr.length());
			// }
			// }
			// catch (Exception e)
			// {
			// e.printStackTrace();
			// }

			// 取系统当前时间作为订单号变量前半部分，精确到毫秒
			long nowLong = Long.parseLong(new SimpleDateFormat(
					"yyMMddHHmmssSSS").format(new Date()));
			// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
			if (orderNumCount > maxPerMSECSize) {
				orderNumCount = 0L;
			} // 组装订单号
			if (orderNumCount < 10) {
				finOrderNum = nowLong + "0" + orderNumCount;
			}
			if (orderNumCount > 9 && orderNumCount < 100) {
				finOrderNum = nowLong + "" + orderNumCount;
			}
			orderNumCount++;
			// System.out.println(finOrderNum + "--" +
			// Thread.currentThread().getName() + "::");
			// Thread.sleep(1000);
			// 取系统时间（纳秒）1943758630813343
			String nanotime = String.valueOf(System.nanoTime());
			finOrderNum = finOrderNum
					+ nanotime.substring(nanotime.length() - 6,
							nanotime.length());
		}

		return finOrderNum;
	}
	/**
	 * 获取发票订单号
	 * @return
	 */
	public static String getEpiaoOrderNum() {

		String finOrderNum = "";
		synchronized (lockObj) {
			// 取系统当前时间作为订单号变量前半部分，精确到毫秒
			long nowLong = Long.parseLong(new SimpleDateFormat(
					"yyMMddHHmmssSSS").format(new Date()));
			// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
			if (orderNumCount > maxPerMSECSize) {
				orderNumCount = 0L;
			} // 组装订单号
			if (orderNumCount < 10) {
				finOrderNum = nowLong + "0" + orderNumCount;
			}
			if (orderNumCount > 9 && orderNumCount < 100) {
				finOrderNum = nowLong + "" + orderNumCount;
			}
			orderNumCount++;
			String nanotime = String.valueOf(System.nanoTime());
			finOrderNum = "L"+finOrderNum
					+ nanotime.substring(nanotime.length() - 6,
							nanotime.length());
		}

		return finOrderNum;
	}
	/**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
	/**
	 * 根据当前日期获得上周的日期区间（上周周一和周日日期）
	 *
	 * @param date
	 * @return
	 */
	public static String getLastWeek(Date date) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date);
		calendar2.setTime(date);
		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
		if(dayOfWeek==0){
			dayOfWeek=7;
		}
		int offset1 = 1 - dayOfWeek;
		int offset2 = 7 - dayOfWeek;
		calendar1.add(Calendar.DATE, offset1 - 7);
		calendar2.add(Calendar.DATE, offset2 - 7);
		String lastBeginDate = dateStringFormat.format(calendar1.getTime());
		String lastEndDate = dateStringFormat.format(calendar2.getTime());
		return lastBeginDate + "," + lastEndDate;
	}

	/**
	 * 获取上月开始日期和结束日期
	 *
	 * @param date
	 * @return
	 */
	public static String getLastMonth(Date date) {
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		startCalendar.setTime(date);
		endCalendar.setTime(date);
		startCalendar.add(Calendar.MONTH, -1);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		endCalendar.set(Calendar.DAY_OF_MONTH, 0);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		String startStr = dateStringFormat.format(startDate);
		String endStr = dateStringFormat.format(endDate);
		return startStr + "," + endStr;
	}

	/**
	 * 获取本周周一至周日 开始日期和结束日期
	 * @param date
	 * @return
	 */
	public static String getNowWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		String imptimeBegin = dateStringFormat.format(cal.getTime());
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = dateStringFormat.format(cal.getTime());
		// System.out.println("所在周星期日的日期：" + imptimeEnd);
		return imptimeBegin + "," + imptimeEnd;
	}

	/**
	 * 获取本月开始日期和结束日期
	 *
	 * @param date
	 * @return
	 */
	public static String getNowMonth(Date date) {
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		startCalendar.setTime(date);
		endCalendar.setTime(date);
		startCalendar.add(Calendar.MONTH, 0);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		endCalendar.set(Calendar.DAY_OF_MONTH, startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		String startStr = dateStringFormat.format(startDate);
		String endStr = dateStringFormat.format(endDate);
		return startStr + "," + endStr;
	}

	public static String timeStampToDate(long timeStamp,String format){
		if(ValidateUtil.isEmpty(timeStamp)){
			return "";
		}
		if(ValidateUtil.isEmpty(format)){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(timeStamp);
	}
	public static void main(String[] args) throws ParseException {

		System.out.println(timeStampToDate(1559194590000L,"yyyy-MM-dd HH:mm:ss"));
	}
}
