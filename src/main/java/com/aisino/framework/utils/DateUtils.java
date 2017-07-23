package com.aisino.framework.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期常用函数，各种日期格式之间的转换，日期之间的比较，公历与农历转化
 *
 * @author zhoudl
 */

public class DateUtils {
  private static Log log = LogFactory.getLog(DateUtils.class);
  public static final String BUNDLE_KEY = "ApplicationResources";
  private static final String TIME_PATTERN = "HH:mm";
  private int lunarYear;
  private int lunarMonth;
  private int lunarDay;
  private boolean lunarLeap;
  private static SimpleDateFormat chineseDateFormat = new SimpleDateFormat(
      "yyyy年MM月dd日");
  public final static String FORMAT_DATE = "yyyy-MM-dd";
  public final static String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
 
  public final static String FORMAT_DATE_ZH = "yyyy年MM月dd日";
  public final static String FORMAT_DATETIME_ZH = "yyyy年MM月dd日 HH时mm分ss秒";

  public final static String TYPE_DATE = "date";
  public final static String TYPE_DATETIME = "datetime";
  private static int[] lunarInfo = {
      0x04bd8, 0x04ae0, 0x0a570, 0x054d5,
      0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0,
      0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2,
      0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40,
      0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,
      0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7,
      0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0,
      0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355,
      0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
      0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263,
      0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0,
      0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6, 0x095b0,
      0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46,
      0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50,
      0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954,
      0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0,
      0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,
      0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50,
      0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
      0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6,
      0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0,
      0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0, 0x14b63};

  public DateUtils() {
  }

  /**
   * Return default datePattern (MM/dd/yyyy);可以从系统中读取默认的pattern
   *
   */
  public static String getDatePattern() {
    String defaultDatePattern;
    try {
      defaultDatePattern = ResourceBundle.getBundle(BUNDLE_KEY).getString(
          "date.format"); //从资源文件中读取默认的日期格式
    }
    catch (MissingResourceException mse) {
      defaultDatePattern = "MM/dd/yyyy";
    }
    return defaultDatePattern;
  }

  /**
   * 返回系统中默认的时间格式 (MM/dd/yyyyHH:mm:ss.S);
   *
   */
  public static String getDateTimePattern() {
    return DateUtils.getDatePattern() + " HH:mm:ss.S";
  }

  /**
   * 获取格林时间
   *
   */
  public static Date getGreenwichDate() {
    Date date = new Date();
    try {
      TimeZone tz = TimeZone.getTimeZone("Etc/Greenwich");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      sdf.setTimeZone(tz);
      String date_tz = sdf.format(date);
      sdf.setTimeZone(TimeZone.getDefault());
      return sdf.parse(date_tz);
    }
    catch (ParseException e) {
      return date;
    }
  }

  /**
   * 获取系统时间
   *
   */
  public static Date getSystemTime() {
    Calendar cldCurrent = Calendar.getInstance();
    Date currentDate = cldCurrent.getTime();
    return currentDate;
  }

  /**
   * 根据日期格式将字符串日期转换为日期
   *
   */
  public static Date convertStringToDate(String aMask, String strDate) throws
      ParseException {
    SimpleDateFormat df;
    Date date;
    df = new SimpleDateFormat(aMask);
    if (log.isDebugEnabled()) {
      log.info("converting '" + strDate + "' to date with mask '" + aMask +
                "'");
    }
    try {
      date = df.parse(strDate);
    }
    catch (ParseException pe) {
      throw new ParseException(pe.getMessage(), pe.getErrorOffset());
    }
    return (date);
  }

  /**
   * 根据日期格式将日期转换为字符串日期
   *
   */
  public static String convertDateToString(String aMask, Date aDate) {
    return getDateTime(aMask, aDate);
  }

  /**
   * 根据日期时间返回时间，日期格式：MM/dd/yyyy HH:MM
   *
   */
  public static String getTimeNow(Date theTime) {
    SimpleDateFormat sdf = new SimpleDateFormat(TIME_PATTERN);
    String date_tz = sdf.format(theTime);
    return date_tz;
  }

  /**
   * This method attempts to convert an Oracle-formatted date in the form
   * dd-MMM-yyyy to mm/dd/yyyy.
   *
   * @param aDate
   *            date from database as a string
   * @return formatted string for the ui
   */
  public static String getDate(Date aDate) {
    SimpleDateFormat df;
    String returnValue = "";
    if (aDate != null) {
      df = new SimpleDateFormat(getDatePattern());
      returnValue = df.format(aDate);
    }
    return (returnValue);
  }

  /**
   * This method returns the current date in the format: MM/dd/yyyy
   *
   * @return the current date
   * @throws ParseException
   *             when String doesn't match the expected format
   */
  public static Calendar getToday() throws ParseException {
    Date today = new Date();
    SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

    // This seems like quite a hack (date -> string -> date),
    // but it works ;-)
    String todayAsString = df.format(today);
    Calendar cal = new GregorianCalendar();
    cal.setTime(convertStringToDate(todayAsString));
    return cal;
  }

  /**
   * This method generates a string representation of a date's date/time in
   * the format you specify on input
   *
   * @param aMask
   *            the date pattern the string is in
   * @param aDate
   *            a date object
   * @return a formatted string representation of the date
   *
   * @see SimpleDateFormat
   */
  public static String getDateTime(String aMask, Date aDate) {
    SimpleDateFormat df = null;
    String returnValue = "";
    if (aDate == null) {
      log.error("aDate is null!");
    }
    else {
      df = new SimpleDateFormat(aMask);
      returnValue = df.format(aDate);
    }
    return (returnValue);
  }

  /**
   * This method generates a string representation of a date based on the
   * System Property 'dateFormat' in the format you specify on input
   *
   * @param aDate
   *            A date to convert
   * @return a string representation of the date
   */
  public static String convertDateToString(Date aDate) {
    return getDateTime(getDatePattern(), aDate);
  }

  /**
   * This method converts a String to a date using the datePattern
   *
   * @param strDate
   *            the date to convert (in format MM/dd/yyyy)
   * @return a date object
   * @throws ParseException
   *             when String doesn't match the expected format
   */
  public static Date convertStringToDate(String strDate) throws ParseException {
    Date aDate = null;
    try {
      if (log.isDebugEnabled()) {
        log.info("converting date with pattern: " + getDatePattern());
      }
      aDate = convertStringToDate(getDatePattern(), strDate);
    }
    catch (ParseException pe) {
      log.error("Could not convert '" + strDate
                + "' to a date, throwing exception");
      pe.printStackTrace();
      throw new ParseException(pe.getMessage(), pe.getErrorOffset());
    }
    return aDate;
  }

  /**
   *
   * 将时间的小时转换成秒
   *
   */
  public static long hoursToMinSec(int hours) {
    return minutesToMinSec(hours * 60);
  }

  /**
   *
   * 将时间的分钟转换成秒
   *
   */
  public static long minutesToMinSec(int minutes) {
    return minutes * 60 * 1000;
  }

  /**
   *
   * 根据时区将日期转换为字符串
   *
   */
  public static String convertDateToString(Date date, Long tzAdjust) {
    Date tmpDate = getGreenwichDate();
    tmpDate.setTime(date.getTime() + hoursToMinSec(tzAdjust.intValue()));
    return getDateTime(getDatePattern(), tmpDate);
  }

  /**
   *
   * 根据时区返回当前系统日期时间
   *
   */
  public static Date GetAdjustDate(Long tzAdjust) {
    Date date = getGreenwichDate();
    date.setTime(date.getTime() + hoursToMinSec(tzAdjust.intValue()));
    return date;
  }

  /**
   *
   * 将日期转换为指定时区的日期
   *
   */
  public static Date GetAdjustDate(Long tzAdjust, Date date) {
    Date tmpDate = new Date();
    tmpDate.setTime(date.getTime() + hoursToMinSec(tzAdjust.intValue()));
    return tmpDate;
  }

  /**
   *
   * 获取相隔月数
   *
   */
  public static long getDistinceMonth(String beforedate, String afterdate) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    long monthCount = 0;
    try {
      Date d1 = df.parse(beforedate);
      Date d2 = df.parse(afterdate);
      monthCount = (d2.getYear() - d1.getYear()) * 12 + d2.getMonth()
          - d1.getMonth();
    }
    catch (ParseException e) {
       log.info("Date parse error!");
    }
    return monthCount;
  }

  /**
   *
   * 根据字符串格式日期获取相隔天数
   *
   */
  public static long getDistinceDay(String beforedate, String afterdate) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    long dayCount = 0;
    try {
      Date d1 = df.parse(beforedate);
      Date d2 = df.parse(afterdate);
      dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
    }
    catch (ParseException e) {
       log.info("Date parse error!");
    }
    return dayCount;
  }

  /**
   *
   * 获取相隔天数
   *
   */
  public static long getDistinceDay(Date beforedate, Date afterdate) {
    long dayCount = 0;
    try {
      dayCount = (afterdate.getTime() - beforedate.getTime()) /
          (24 * 60 * 60 * 1000);
    }
    catch (Exception e) {
       log.info("Date parse error!");
    }
    return dayCount;
  }

  /**
   *
   * 获取相隔时间数
   *
   */
  public static long getDistinceTime(String beforeDateTime,
                                     String afterDateTime) throws Exception {
    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long timeCount = 0;
    try {
      Date d1 = d.parse(beforeDateTime);
      Date d2 = d.parse(afterDateTime);
      timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);
    }
    catch (ParseException e) {
       log.info("Date parse error!");
      throw e;
    }
    return timeCount;
  }

  /**
   *
   * 获取相隔时间数
   *
   */

  public static long getDistinceTime(Date beforeDateTime,
                                     Date afterDateTime) throws Exception {
    long timeCount = 0;
    try {
      timeCount = (afterDateTime.getTime() - beforeDateTime.getTime()) /
          (60 * 60 * 1000);
    }
    catch (Exception e) {
       log.info("Date parse error!");
      throw e;
    }
    return timeCount;
  }

  /**
   *
   * 获取相隔时间数
   *
   */
  public static long getDistinceTime(String beforeDateTime) throws Exception {
    return getDistinceTime(beforeDateTime, convertDateToString(getGreenwichDate()));
  }

  /**
   *
   * 获取相隔时间数
   *
   */
  public static long getDistinceTime(Date beforeDateTime) throws Exception {
    return getDistinceTime(beforeDateTime, getGreenwichDate());
  }

  /**
   *
   * 获取相隔分钟数
   *
   */
  public static long getDistinceMinute(String beforeDateTime,
                                       String afterDateTime) throws Exception {
    long minutesCount = 0;
    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      Date d1 = d.parse(beforeDateTime);
      Date d2 = d.parse(afterDateTime);
      minutesCount = (d2.getTime() - d1.getTime()) / (60 * 60);
    }
    catch (ParseException e) {
       log.info("Date parse error!");
      throw e;
    }
    return minutesCount;
  }

  /**
   *
   * 获取相隔分钟数
   *
   */
  public static long getDistinceMinute(Date beforeDateTime,
                                       Date afterDateTime) throws Exception {
    long minutesCount = 0;
    try {
      minutesCount = (afterDateTime.getTime() - beforeDateTime.getTime()) /
          (60 * 60);
    }
    catch (Exception e) {
       log.info("Date parse error!");
      throw e;
    }
    return minutesCount;
  }

  /**
   *
   * 当前时间增加间隔分钟后的时间
   *
   */
  public static long addMinutes(long longCalendar, int intMin) {
    try {
      long longDate = 0;
      longDate = longCalendar  + intMin * (60 * 1000);
      return longDate;
    }
    catch (Exception Exp) {
      return -1;
    }

  }

  /**
   *
   * 当前时间增加间隔小时后的时间
   *
   */
  public static long addHours(long longCalendar, int intHour) {
    long longDate = 0;
    longDate = longCalendar + intHour * (60 * 60 * 1000);
    return longDate;
  }

  /**
   *
   * 当前时间增加间隔天后的时间
   *
   */
  public static long addDays(long longCalendar, int intDay) {
    long longDate = 0;
    longDate = longCalendar + intDay * (24 * 60 * 60 * 1000);
    return longDate;

  }

  /**
   *
   * 将Date日期转换为Calendar日期
   *
   */
  public static Calendar convertDateToCalendar(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }

  /**
   * 得到年
   *
   * @param c
   * @return int
   */
  public static int getYear(Calendar c) {
    if (c != null) {
      return c.get(Calendar.YEAR);
    }
    else {
      return Calendar.getInstance().get(Calendar.YEAR);
    }
  }

  /**
   * 得到月份 注意，这里的月份依然是从0开始的
   *
   * @param c
   * @return int
   */
  public static int getMonth(Calendar c) {
    if (c != null) {
      return c.get(Calendar.MONTH);
    }
    else {
      return Calendar.getInstance().get(Calendar.MONTH);
    }
  }

  /**
   * 得到日期
   *
   * @param c
   * @return int
   */
  public static int getDate(Calendar c) {
    if (c != null) {
      return c.get(Calendar.DATE);
    }
    else {
      return Calendar.getInstance().get(Calendar.DATE);
    }
  }

  /**
   * 得到星期
   *
   * @param c
   * @return int
   */
  public static int getDay(Calendar c) {
    if (c != null) {
      return c.get(Calendar.DAY_OF_WEEK);
    }
    else {
      return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
  }

  /**
   * 得到小时
   *
   * @param c
   * @return int
   */
  public static int getHour(Calendar c) {
    if (c != null) {
      return c.get(Calendar.HOUR);
    }
    else {
      return Calendar.getInstance().get(Calendar.HOUR);
    }
  }

  /**
   * 得到分钟
   *
   * @param c
   * @return int
   */
  public static int getMinute(Calendar c) {
    if (c != null) {
      return c.get(Calendar.MINUTE);
    }
    else {
      return Calendar.getInstance().get(Calendar.MINUTE);
    }
  }

  /**
   * 传回农历 year年的总天数
   *
   * @param year
   *            int
   * @return int
   */
  public int getYearDays(int year) {
    int i, sum = 348;
    for (i = 0x8000; i > 0x8; i >>= 1) {
      sum += (lunarInfo[year - 1900] & i) > 0 ? 1 : 0;
    }
    return (sum + getLeapDays(year));
  }

  /**
   * 传回农历 year年闰月的天数
   *
   * @param year
   *            int
   * @return int
   */
  public int getLeapDays(int year) {
    if (getLeapMonth(year) > 0) {
      return ( (lunarInfo[year - 1900] & 0x10000) > 0 ? 30 : 29);
    }
    else {
      return (0);
    }
  }

  /**
   * 取得农历year年闰哪个月
   *
   * @param year
   *            int
   * @return int 闰月的月份,不闰月返回0
   */
  public int getLeapMonth(int year) {
    return (lunarInfo[year - 1900] & 0xf);
  }

  /**
   * 取农历year年,month月的总天数
   *
   * @param year
   *            int
   * @param month
   *            int
   * @return int
   */
  public int getMonthDays(int year, int month) {
    return ( (lunarInfo[year - 1900] & (0x10000 >> month)) > 0 ? 30 : 29);
  }

  /**
   * 根据当前日期(公历)取得农历的日期 lunarYear,lunarMonth,lunarDay
   *
   * @param date
   *            Calendar
   */
  public void setLunar(Calendar date) {
    int yearCyl, dayCyl, monCyl;
    int year, month, day;
    boolean isLeap;
    int i, leap, temp = 0;

    Date baseDate = null;
    try {
      baseDate = chineseDateFormat.parse("1900年1月31日");
    }
    catch (Exception e) {
    }
    int offset = (int) ( (date.getTime().getTime() - baseDate.getTime()) /
                        86400000);

    dayCyl = offset + 40;
    monCyl = 14;
    for (i = 1900; i < 2050 && offset > 0; i++) {
      temp = getYearDays(i);
      offset -= temp;
      monCyl += 12;
    }
    if (offset < 0) {
      offset += temp;
      i--;
      monCyl -= 12;
    }
    year = i;
    yearCyl = i - 1864;

    leap = getLeapMonth(i); // 闰哪个月
    isLeap = false;

    for (i = 1; i < 13 && offset > 0; i++) {
      // 闰月
      if (leap > 0 && i == (leap + 1) && isLeap == false) {
        --i;
        isLeap = true;
        temp = getLeapDays(year);
      }
      else {
        temp = getMonthDays(year, i);
      }

      // 解除闰月
      if (isLeap == true && i == (leap + 1)) {
        isLeap = false;
      }
      offset -= temp;
      if (isLeap == false) {
        monCyl++;
      }
    }

    if (offset == 0 && leap > 0 && i == leap + 1) {
      if (isLeap) {
        isLeap = false;
      }
      else {
        isLeap = true;
        --i;
        --monCyl;
      }
    }
    if (offset < 0) {
      offset += temp;
      --i;
      --monCyl;
    }
    month = i;
    day = offset + 1;
    this.lunarYear = year;
    this.lunarMonth = month;
    this.lunarDay = day;
    this.lunarLeap = isLeap;
  }

  /**
   * 取得两个农历日期之间相差的天数
   *
   * @param minYear
   *            int
   * @param minMonth
   *            int
   * @param minDay
   *            int
   * @param isLeap
   *            boolean
   * @param maxYear
   *            int
   * @param maxMonth
   *            int
   * @param maxDay
   *            int
   * @return int scan 两个日期之间相差的天数
   */
  public int getScan(int minYear, int minMonth, int minDay, boolean isLeap,
                     int maxYear, int maxMonth, int maxDay) {
    int offset = 0;
    int yearScan = maxYear - minYear;
    int i;
    if (yearScan > 0) {
      // 不是同一年
      // 取得当年还剩的天数
      for (i = minMonth; i <= 12; i++) {
        offset += getMonthDays(minYear, i);
      }
      offset -= minDay;
      // 如果有闰月并且当前月不是闰月,应包含闰月的天数
      if (!isLeap && getLeapMonth(minYear) >= minMonth) {
        offset += getLeapDays(minYear);
      }
      // 取得相隔整年的天数
      for (i = 1; i < yearScan; i++) {
        offset += getYearDays(minYear + i);
      }
      // 取得最后一年的天数
      for (i = 1; i < maxMonth; i++) {
        offset += getMonthDays(maxYear, i);
      }
      // 如果有闰月在应包含闰月的天数
      if (getLeapMonth(maxYear) < maxMonth) {
        offset += getLeapDays(maxYear);
      }
      offset += maxDay;
    }
    else {
      // 同一年
      for (i = minMonth; i < maxMonth; i++) {
        offset += getMonthDays(minYear, i);
      }
      int leap = getLeapMonth(minYear);
      if (!isLeap && leap >= minMonth && leap < maxMonth) {
        offset += getLeapDays(maxYear);
      }
      offset -= minDay;
      offset += maxDay;
    }
    return offset;
  }

  public int getLunarYear() {
    return this.lunarYear;
  }

  public int getLunarMonth() {
    return this.lunarMonth;
  }

  public int getLunarDay() {
    return this.lunarDay;
  }

  public boolean getLunarLeap() {
    return this.lunarLeap;
  }
  
  /**
   * 得到星期
   *
   * @param sdate String
   * @param num String
   * @param dateFormat String
   * @return String
   */
  public static String getWeek(String sdate, String num, String dateFormat) {
		// 再转换为时间
		Date dd = null;
		try {
			dd = convertStringToDate(dateFormat, sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			c.add(Calendar.DATE, -7);
		}

		if (num.equals("1")) { // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		} else if (num.equals("2")) { // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		} else if (num.equals("3")) { // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		} else if (num.equals("4")) { // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		} else if (num.equals("5")) { // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		} else if (num.equals("6")) { // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		} else if (num.equals("0")) { // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			c.add(Calendar.DATE, 7);
		}
		return new SimpleDateFormat(dateFormat).format(c.getTime());
	}
  
  /**
	 * 取得指定日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}
	
  /**
	 * 取得指定日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 取得本月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 取得本月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	/**
	 * 取得上个月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfLastMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE,1);
		calendar.add(Calendar.MONTH,-1);
		return calendar.getTime();
	}
	
	/**
	 * 取得n个月前的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfNMonth(Date date, int n) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.MONTH, (n * -1));
		return calendar.getTime();
	}

	/**
	 * 取得上个月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfLastMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE,1);
		calendar.add(Calendar.MONTH,-1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	/**
	 * 取得上个月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayOfAddDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(Calendar.DAY_OF_YEAR, days);
	    return c.getTime();
	}
	
    
    /**
     * 得到某天是周几
     * @param strDay
     * @return 周几
     */
    public static int getWeekDay(String strDay) {
            Date day = DateUtils.dateAdd(strDay, -1);
            Calendar strDate = Calendar.getInstance();
            strDate.setTime(day);
            int meStrDate = strDate.get(Calendar.DAY_OF_WEEK);
            return meStrDate;
    }

	/**
	 * 得到某天是周几
	 * 
	 * @param strDay
	 * @return 周几
	 */
	public static int getWeekDay(Date date) {
		Date day = DateUtils.dateAdd(format(date, "date"), -1);
		Calendar strDate = Calendar.getInstance();
		strDate.setTime(day);
		int meStrDate = strDate.get(Calendar.DAY_OF_WEEK);
		return meStrDate;
	}

	/**
	 * 取得两个日期段的日期间隔
	 * 
	 * @author color
	 * @param t1
	 *            时间1
	 * @param t2
	 *            时间2
	 * @return t2 与t1的间隔天数
	 * @throws ParseException
	 *             如果输入的日期格式不是0000-00-00 格式抛出异常
	 */
	public static int getBetweenDays(String t1, String t2)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int betweenDays = 0;
		Date d1 = format.parse(t1);
		Date d2 = format.parse(t2);
		betweenDays = getBetweenDays(d1, d2);
		return betweenDays;
	}

	/**
	 * 取得两个日期段的日期间隔
	 * 
	 * @param d1
	 *            日期1
	 * @param d2
	 *            日期2
	 * @return t2 与t1的间隔天数
	 */
	private static int getBetweenDays(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return -1;
		}
		int betweenDays;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c2.setTime(d1);
			c1.setTime(d2);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR)
				- c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}

	/**
	 * 判断指定日期是否在一个日期范围内
	 * 
	 * @param fromDate
	 *            范围开始日期
	 * @param toDate
	 *            范围结束日期
	 * @param testDate
	 *            测试日期
	 * @return 在范围内true,否则false
	 */
	public static boolean betweenDays(java.sql.Date fromDate,
			java.sql.Date toDate, java.sql.Date testDate) {
		if (fromDate == null || toDate == null || testDate == null) {
			return false;
		}

		// 1、 交换开始和结束日期
		if (fromDate.getTime() > toDate.getTime()) {
			java.sql.Date tempDate = fromDate;
			fromDate = toDate;
			toDate = tempDate;
		}

		// 2、缩小范围
		long testDateTime = testDate.getTime();
		if ((testDateTime > fromDate.getTime() && testDateTime > toDate
				.getTime())
				|| testDateTime < fromDate.getTime()
				&& testDateTime < toDate.getTime()) {
			return false;
		}

		return true;
	}

	/**
	 * 得到指定年、月的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 本年月的最后一天，如果2009,10，返回结果：2009-10-31
	 */
	public static String getLastDateDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		// 某年某月的最后一天
		int lastDate = cal.getActualMaximum(Calendar.DATE);
		return year + "-" + (month + 1) + "-" + lastDate;
	}

	/**
	 * 判断两个日期是否为同一天
	 * 
	 * @param d1
	 *            日期一
	 * @param d2
	 *            日期二
	 * @return 同一天true，不是同一天false
	 */
	public static boolean isSameDate(Date d1, Date d2) {
		boolean result = false;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2
						.get(Calendar.DAY_OF_MONTH)) {
			result = true;
		}
		return result;
	}

	/**
	 * 是否为周末
	 * 
	 * @param strDate
	 * @return true|false
	 */
	public static boolean isWeekend(String strDate) {
		int weekDay = getWeekDay(strDate);
		if (weekDay == 6 || weekDay == 7) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否为周末
	 * 
	 * @param strDate
	 * @return true|false
	 */
	public static boolean isWeekend(Date date) {
		int weekDay = getWeekDay(format(date, "date"));
		if (weekDay == 6 || weekDay == 7) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @dateValue 日期对象，可以是java.util.Date和java.sql.Date
	 * @dateType 格式化的类型,date和datetime
	 */
	public static String format(Object dateValue, String dateType) {
		if (dateValue == null)
			return "";
		if (dateValue instanceof java.sql.Date) {
			return dateValue.toString();
		} else if (dateValue instanceof Date) {
			if (dateType.equals(TYPE_DATE)) {
				SimpleDateFormat sfdate = new SimpleDateFormat(
						FORMAT_DATE);
				return sfdate.format(dateValue);
			} else if (dateType.equals(TYPE_DATETIME)) {
				SimpleDateFormat sftime = new SimpleDateFormat(
						FORMAT_DATETIME);
				return sftime.format(dateValue);
			} else {
				return "非法日期格式[" + dateType + "]";
			}
		} else {
			return "非日期类型";
		}
	}

	/**
	 * 转换日期对象为中文化日期
	 * 
	 * @dateValue 日期对象，可以是java.util.Date和java.sql.Date
	 * @dateType 格式化的类型,date和datetime
	 */
	public static String formatZh(Date dateValue, String dateType) {
		if (dateValue == null)
			return "";
		if (dateValue instanceof java.sql.Date) {
			return dateValue.toString();
		} else if (dateValue instanceof Date) {
			if (dateType.equals(TYPE_DATE)) {
				SimpleDateFormat sfdate = new SimpleDateFormat(
						FORMAT_DATE_ZH);
				return sfdate.format(dateValue);
			} else if (dateType.equals(TYPE_DATETIME)) {
				SimpleDateFormat sftime = new SimpleDateFormat(
						FORMAT_DATETIME_ZH);
				return sftime.format(dateValue);
			} else {
				return "非法日期格式[" + dateType + "]";
			}
		} else {
			return "非日期类型";
		}
	}

	/**
	 *将日期加上某些天或减去天数)返回字符串
	 * 
	 * @param date
	 *            待处理日期
	 * @param to
	 *            加减的天数
	 * @return 日期
	 */
	public static Date dateAdd(String date, int to) {
		Date d = null;
		try {
			d = java.sql.Date.valueOf(date);
		} catch (Exception e) {
			e.printStackTrace();
			d = new Date();
		}
		Calendar strDate = Calendar.getInstance();
		strDate.setTime(d);
		strDate.add(Calendar.DATE, to); // 日期减 如果不够减会将月变动
		return strDate.getTime();
	}

	/**
	 *将日期加上某些天或减去天数)返回字符串
	 * 
	 * @param date
	 *            待处理日期
	 * @param to
	 *            加减的天数
	 * @return 日期
	 */
	public static java.sql.Date dateAdd(java.sql.Date date, int to) {
		Calendar strDate = Calendar.getInstance();
		strDate.setTime(date);
		strDate.add(Calendar.DATE, to); // 日期减 如果不够减会将月变动
		return new java.sql.Date(strDate.getTime().getTime());
	}

	/**
	 * 获取两个日期之间的所有日期
	 * @param d1
	 * @param d2
	 * @return GregorianCalendar[]
	 */
	public static GregorianCalendar[] getBetweenDate(Date d1, Date d2)
			throws ParseException {
		Vector<GregorianCalendar> v = new Vector<GregorianCalendar>();
		GregorianCalendar gc1 = new GregorianCalendar(), gc2 = new GregorianCalendar();
		gc1.setTime(d1);
		gc2.setTime(d2);
		do {
			GregorianCalendar gc3 = (GregorianCalendar) gc1.clone();
			v.add(gc3);
			gc1.add(Calendar.DAY_OF_MONTH, 1);
		} while (!gc1.after(gc2));
		return v.toArray(new GregorianCalendar[v.size()]);
	}

	/**
	 * @title 判断是否为工作日
	 * @detail 工作日计算: 1、正常工作日，并且为非假期 2、周末被调整成工作日
	 * @param date
	 *            日期
	 * @return 是工作日返回true，非工作日返回false
	 */
	@SuppressWarnings("unchecked")
	public static boolean isWeekday(GregorianCalendar calendar,
			List workDayList, List holiDayList, int holiday1,
			int holiday2) throws Exception {
 
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
 
		if (calendar.get(GregorianCalendar.DAY_OF_WEEK) != holiday1 + 1
				&& calendar.get(GregorianCalendar.DAY_OF_WEEK) != holiday2 + 1) {
			// 如果是周一到周五 给出的节假日列表中 包含这天 返回FALSE
			return !holiDayList
					.contains(myFormatter.format(calendar.getTime()));
		} else {
			// 如果是周六到周日 给出的工作日列表中 包含这天 返回TRUE
			return workDayList.contains(myFormatter.format(calendar.getTime()));
		}
 
	}
 
	/**
	 * 根据每天的工作的开始结束时间 计算工作时间 单位 秒
	 */
	public static float dayTime(String ams, String ame, String pms, String pme,
			String stime, String etime, String check) throws Exception {
 
		// 两个时间的格式都是HHmm
		SimpleDateFormat myFormatter = new SimpleDateFormat("HHmm");
 
		Date dateAmS = myFormatter.parse(ams);
		Date dateAmE = myFormatter.parse(ame);
 
		Date datePmS = myFormatter.parse(pms);
		Date datePmE = myFormatter.parse(pme);
 
		float spaceAM = (dateAmE.getTime() - dateAmS.getTime()) / 1000;
		float spacePM = (datePmE.getTime() - datePmS.getTime()) / 1000;
 
		// 两个时间段都在同一天
		if (check.equals("oneDay")) {
 
			Date dateStime = myFormatter.parse(stime);
			Date dateEtime = myFormatter.parse(etime);
 
			Date startTime = dateStime;
			Date endTime = dateEtime;
 
			// 确定开始时间
			if ((dateAmS.getTime() - dateStime.getTime()) >= 0) {
				startTime = dateAmS;
			} else if (dateAmS.getTime() < dateStime.getTime()
					&& dateStime.getTime() <= dateAmE.getTime()) {
				startTime = dateStime;
			} else if (dateAmE.getTime() < dateStime.getTime()
					&& dateStime.getTime() <= datePmS.getTime()) {
				startTime = datePmS;
			} else if (datePmS.getTime() < dateStime.getTime()
					&& dateStime.getTime() < datePmE.getTime()) {
				startTime = dateStime;
			} else if (datePmE.getTime() <= dateStime.getTime()) {
				startTime = datePmE;
			}
 
			// 确定结束时间
			if ((dateAmS.getTime() - dateEtime.getTime()) >= 0) {
				endTime = dateAmS;
			} else if (dateAmS.getTime() < dateEtime.getTime()
					&& dateEtime.getTime() <= dateAmE.getTime()) {
				endTime = dateEtime;
			} else if (dateAmE.getTime() < dateEtime.getTime()
					&& dateEtime.getTime() <= datePmS.getTime()) {
				endTime = datePmS;
			} else if (datePmS.getTime() < dateEtime.getTime()
					&& dateEtime.getTime() < datePmE.getTime()) {
				endTime = dateEtime;
			} else if (datePmE.getTime() <= dateEtime.getTime()) {
				endTime = datePmE;
			}
 
			if (dateAmE.getTime() >= startTime.getTime()
					&& endTime.getTime() >= datePmS.getTime()) {
				return (endTime.getTime() - startTime.getTime() - (datePmS
						.getTime() - dateAmE.getTime())) / 1000;
			} else {
				return (endTime.getTime() - startTime.getTime()) / 1000;
			}
 
			// 两个时间段不在同一天
			// 计算当前接收的工作时间是开始天的小时
		} else if (check.equals("start")) {
 
			Date dateStime = myFormatter.parse(stime);
 
			// 接收时间在工作时间之后 则不记入工作时间
			float t1 = (datePmE.getTime() - dateStime.getTime()) / 1000;
			if (t1 < 0) {
				return 0;
			}
 
			// 接收时间在工作时间之前 则工作时间就是每天的工作时间
			t1 = (dateAmS.getTime() - dateStime.getTime()) / 1000;
			if (t1 > 0) {
				return spaceAM + spacePM;
			}
 
			// 接收时间在上午工作时间结束之前
			t1 = (dateAmE.getTime() - dateStime.getTime()) / 1000;
			if (t1 > 0) {
				return t1 + spacePM;
			}
 
			// 接收时间在下午工作时间结束之前
			t1 = (datePmE.getTime() - dateStime.getTime()) / 1000;
			if (t1 > 0) {
				float tmpT = (datePmS.getTime() - dateStime.getTime()) / 1000;
				if (tmpT < 0) {
					tmpT = 0;
				}
				return t1 - tmpT;
			}
 
			return 0;
 
			// 计算当前的工作时间是结束天的小时
		} else if (check.equals("end")) {
 
			Date dateEtime = myFormatter.parse(etime);
 
			// 办理时间在工作时间之后 则为每天工作时间
			float t1 = (datePmE.getTime() - dateEtime.getTime()) / 1000;
			if (t1 < 0) {
				return spaceAM + spacePM;
			}
 
			// 办理时间在工作时间之前 则返回0
			t1 = (dateAmS.getTime() - dateEtime.getTime()) / 1000;
			if (t1 > 0) {
				return 0f;
			}
 
			// 办理时间在上午工作时间结束之前
			t1 = (dateAmE.getTime() - dateEtime.getTime()) / 1000;
			if (t1 > 0) {
				float tmpT = (dateEtime.getTime() - dateAmS.getTime()) / 1000;
				return tmpT;
			}
 
 
			// 办理时间在下午工作时间结束之前
			t1 = (datePmE.getTime() - dateEtime.getTime()) / 1000;
			if (t1 > 0) {
				float tmpT = (dateEtime.getTime() - datePmS.getTime()) / 1000;
				if (tmpT < 0) {
					return spaceAM;
				} else {
					return tmpT + spaceAM;
				}
			}
 
			return 0f;
 
		} else {
			return 0f;
		}
	}
	
	/***
     * 
     * 得到两个时间内的工作时间和工作日
     * 返回的工作日和工作时间用","相隔
     *
     * @param startTime 前时间 yyyyMMddHHmmss
     * @param endTime  后时间 yyyyMMddHHmmss
     */
	public static String getWorkDayAndTime(String startTime, String endTime, List<String> holiDayList, List<String> workDayList) throws Exception {
     
    	//可以根据以下取得当前年及结束年的工作日和节假日
    	//String startYear = startTime.substring(0, 4);
    	//String endYear = endTime.substring(0, 4);
     
    	// 工作日
    	int workDay = 0;
    	// 工作时间
    	float workTime = 0;
    	// 计算工作时间用
    	int workDayN = 0;
    	// 第一天的工作时间
    	float workTimeStart = 0;
    	// 最后一天的工作时间
    	float workTimeEnd = 0;
     
    	// 默认两天休息日 0 1 2 3 4 5 6 代表 周日 周一 到周六
    	int holiday1 = 0;
    	int holiday2 = 6;
     
    	// 默认每天工作时间 秒
    	float defaultWorkDayTime = 8 * 3600;
     
    	// 每天工作开始结束时间 类型HHmm
    	String ams = "0830";
    	String ame = "1200";
    	String pms = "0130";
    	String pme = "0600";
     
    	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
     
    	try {
    		Date startDate = myFormatter.parse(startTime.substring(0, 8));
    		Date endDate = myFormatter.parse(endTime.substring(0, 8));
     
    		// 日历
    		GregorianCalendar gc = new GregorianCalendar();
    		// 设定日历的时间
    		gc.setTime(startDate);
     
    		// 两个日期相差的天数
    		long time = endDate.getTime() - startDate.getTime();
    		if (time < 0) {
    			// 如果前日期大于后日期
    			return "0.0";
    		}
    		long day = time / 3600000 / 24 + 1;
     
    		for (int i = 0; i < day; i++) {
     
    			// 即每加一天比较一次是否为工作日
    			if (DateUtils.isWeekday(gc, workDayList, holiDayList,
    					holiday1, holiday2)) {
     
    				workDay++;
    				workDayN++;
     
    				if (day == 1) {
    					workDayN = 0;
    					workTime = DateUtils.dayTime(ams, ame, pms, pme,
    							startTime.substring(8, 12), endTime.substring(
    									8, 12), "oneDay");
    				} else {
     
    					// 计算第一天和最后一天的工作时间
    					if (i == 0) {
    						workDayN--;
    						workTimeStart = DateUtils.dayTime(ams, ame,
    								pms, pme, startTime.substring(8, 12), "",
    								"start");
    					} else if (i == (day - 1)) {
    						workDayN--;
    						workTimeEnd = DateUtils.dayTime(ams, ame, pms,
    								pme, "", endTime.substring(8, 12), "end");
    					}
    				}
    			}
    			// 往后加1天
    			gc.add(GregorianCalendar.DATE, 1);
    		}
     
    	} catch (Exception e) {
    		e.printStackTrace();
    		return "0.0";
    	}
    	
    	workTime += workDayN * defaultWorkDayTime + workTimeStart + workTimeEnd;
    	workTime = workTime/(60*60);
    	//System.out.println("==startTime=="+startTime+"==workDayN=="+workDayN+"==defaultWorkDayTime==="+defaultWorkDayTime+"===workTimeStart="+workTimeStart+"===workTimeEnd="+workTimeEnd+"===workTime="+workTime);
    	return workTime+"";
    }

	/**
	 * 两个日期大小比较
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compare_date(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
