package com.company.news.rest.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.StringUtils;


public class TimeUtils
{

    public TimeUtils()
    {
    }

    public static Calendar date2Calendar(Date date)
    {
        Calendar cal = null;
        if(date != null)
        {
            cal = GregorianCalendar.getInstance();
            cal.setTime(date);
        }
        return cal;
    }

    public static Calendar timestamp2Calendar(Timestamp timestamp)
    {
        Calendar cal = null;
        if(timestamp != null)
        {
            cal = GregorianCalendar.getInstance();
            cal.setTime(timestamp);
        }
        return cal;
    }

    public static final Timestamp getDefaultTimestamp()
    {
        return new Timestamp(-5364691200000L);
    }

    public static Calendar getCurrentDate()
    {
        return Calendar.getInstance();
    }

    public static Timestamp getCurrentTimestamp()
    {
        long time = System.currentTimeMillis();
        return new Timestamp(time);
    }

    public static Timestamp getCurrentTimestamp(String format)
    {
        if(format == null)
            format = YYYY_MM_DD_FORMAT;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try
        {
            date = simpleDateFormat.parse(getCurrentTime(format));
        }
        catch(ParseException e)
        {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    public static String getCurrentTime(String parrten)
    {
        if(parrten == null || parrten.equals(""))
            parrten = YYYY_MM_DD_FORMAT;
        Date cday = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(parrten);
        String timestr = sdf.format(cday);
        return timestr;
    }

    public static boolean isDefaultTimestamp(Timestamp time)
    {
        return time.getTime() == -5364691200000L;
    }

    public static boolean isDefaultTimestamp(Object time)
    {
        if(time instanceof Timestamp)
        {
            Timestamp timeValue = (Timestamp)time;
            return timeValue.getTime() == -5364691200000L;
        } else
        {
            return false;
        }
    }

    public static final Timestamp calendar2Timestamp(Calendar cal)
    {
        Date date = null;
        date = cal.getTime();
        return new Timestamp(date.getTime());
    }

    public static String timestamp2String(String format, Timestamp time)
    {
        if(getDefaultTimestamp().equals(time))
            return "";
        if(format == null)
            format = YYYY_MM_DD_FORMAT;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        if(time == null)
            return null;
        else
            return formatter.format(time);
    }

    public static final Timestamp string2Timestamp(String format, String time)
    {
        if(format == null)
            format = YYYY_MM_DD_FORMAT;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try
        {
            date = simpleDateFormat.parse(time);
        }
        catch(ParseException e)
        {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    public static final Calendar string2Calendar(String format, String cal)
    {
        if(format == null)
            format = YYYY_MM_DD_FORMAT;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try
        {
            date = simpleDateFormat.parse(cal);
        }
        catch(ParseException e)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static final int getMonthDays(int year, int month)
    {
        switch(month)
        {
        case 1: // '\001'
            return 31;

        case 2: // '\002'
            return year % 4 != 0 || year % 100 == 0 && year % 400 != 0 ? 28 : 29;

        case 3: // '\003'
            return 31;

        case 4: // '\004'
            return 30;

        case 5: // '\005'
            return 31;

        case 6: // '\006'
            return 30;

        case 7: // '\007'
            return 31;

        case 8: // '\b'
            return 31;

        case 9: // '\t'
            return 30;

        case 10: // '\n'
            return 31;

        case 11: // '\013'
            return 30;

        case 12: // '\f'
            return 31;
        }
        return 0;
    }

    public static int getDaysDiff(String sdate1, String sdate2, String format, TimeZone tz)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date1 = null;
        Date date2 = null;
        try
        {
            date1 = df.parse(sdate1);
            date2 = df.parse(sdate2);
        }
        catch(ParseException pe)
        {
            return -1;
        }
        Calendar cal1 = null;
        Calendar cal2 = null;
        if(tz == null)
        {
            cal1 = Calendar.getInstance();
            cal2 = Calendar.getInstance();
        } else
        {
            cal1 = Calendar.getInstance(tz);
            cal2 = Calendar.getInstance(tz);
        }
        cal1.setTime(date1);
        long ldate1 = date1.getTime() + (long)cal1.get(15) + (long)cal1.get(16);
        cal2.setTime(date2);
        long ldate2 = date2.getTime() + (long)cal2.get(15) + (long)cal2.get(16);
        int hr1 = (int)(ldate1 / 3600000L);
        int hr2 = (int)(ldate2 / 3600000L);
        int days1 = hr1 / 24;
        int days2 = hr2 / 24;
        int dateDiff = days2 - days1;
        return dateDiff;
    }

    public static Calendar getWeek(int num)
    {
        Calendar date = Calendar.getInstance();
        int weekOfYear = date.get(3);
        weekOfYear += num;
        date.set(3, weekOfYear);
        return date;
    }

    public static Calendar getBeforeWeek(int num)
    {
        Calendar date = Calendar.getInstance();
        int weekOfYear = date.get(3);
        weekOfYear -= num;
        date.set(3, weekOfYear);
        return date;
    }

    public static Calendar getMonth(int num)
    {
        Calendar date = Calendar.getInstance();
        int monthOfYear = date.get(2);
        monthOfYear += num;
        date.set(2, monthOfYear);
        return date;
    }

    public static Calendar getBeforeMonth(int num)
    {
        Calendar date = Calendar.getInstance();
        int monthOfYear = date.get(2);
        monthOfYear += num;
        date.set(2, monthOfYear);
        return date;
    }

    public static Calendar getDay(int num)
    {
        Calendar date = Calendar.getInstance();
        int dayOfYear = date.get(5);
        dayOfYear += num;
        date.set(5, dayOfYear);
        return date;
    }

    public static Timestamp date2TimestampStart(Date date)
    {
        return new Timestamp(date.getTime());
    }

    
    public static String getDateTimeString(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULTFORMAT);
        return format.format(date);
    }
    public static String getDateString(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_FORMAT);
        return format.format(date);
    }
    public static String getDateFormatString(String date1)
    {
    	if(StringUtils.isBlank(date1))return "";
//    	 System.out.print("old date1="+date1);
    	 date1=date1.replaceAll("\\.", "-");
    	 date1=date1.replaceAll("\\/", "-");
    	 
    	 try{
    		 SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_FORMAT);
 	        Date date = (Date)format.parse(date1);
 	       date1=format.format(date);
// 	       System.out.println("=>"+date1);
 	        return date1;
 	    }catch(Exception e){
 	    	
 	    }
//    	  System.out.println("=>");
        return "";
    }
  
    public static void main(String[] date1){
    	String dd="2012-2-2";
    	getDateFormatString(dd);
    	dd="2012.2.";
    	getDateFormatString(dd);
    	dd="2012.2.2";
    	getDateFormatString(dd);
    	dd="2012/2/2";
    	getDateFormatString(dd);
    	dd="2012/2/222";
    	getDateFormatString(dd);
    	dd="2012/2.222";
    	getDateFormatString(dd);
    }

    public static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd";
    public static final long DEFAULT_DATE = -5364691200000L;
    public static final String DEFAULTFORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 当前时间本月最后一天
     * @param sDate1
     * @return
     */
    public static Date getLastDayOfMonth(Date sDate1) { 
//    	Calendar cDay1 = Calendar.getInstance(); 
//    	cDay1.setTime(sDate1); 
//    	final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH); 
//    	Date date = cDay1.getTime(); 
//    	date.setDate(lastDay);
//    	date.setHours(23);
//    	date.setMinutes(59);
//    	date.setSeconds(59);
    	
    	 Calendar calendar = Calendar.getInstance();     
    	    calendar.set(Calendar.DAY_OF_MONTH, calendar     
    	            .getActualMaximum(Calendar.DAY_OF_MONTH));   
    	return calendar.getTime(); 
    } 
    
    /**
     * 当前时间本月最后一天
     * @param sDate1
     * @return
     */
    public static Date getFirstDayOfMonth(Date sDate1) { 
//    	Calendar cDay1 = Calendar.getInstance(); 
//    	cDay1.setTime(sDate1); 
//    	final int first = cDay1.getActualMinimum(Calendar.DAY_OF_MONTH); 
//    	Date date = cDay1.getTime(); 
//    	date.setDate(first);
//    	date.setHours(0);
//    	date.setMinutes(0);
//    	date.setSeconds(0);
    	
    	Calendar calendar = Calendar.getInstance();     
        calendar.set(Calendar.DAY_OF_MONTH, calendar     
                .getActualMinimum(Calendar.DAY_OF_MONTH));   
        
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
    	return calendar.getTime(); 
    } 
    
    
    /**
     * 当前时间的00:00:00
     * @param sDate1
     * @return
     */
    public static Date getDate00(Date sDate1) { 
    	Calendar cDay1 = Calendar.getInstance(); 
    	cDay1.setTime(sDate1); 
    	cDay1.set(Calendar.HOUR_OF_DAY, 0);
    	cDay1.set(Calendar.MINUTE, 0);
    	cDay1.set(Calendar.SECOND, 0);
    	return cDay1.getTime();
    } 
    
    /**
     * 当前时间的23:59:59
     * @param sDate1
     * @return
     */
    public static Date getDate23(Date sDate1) { 
    	Calendar cDay1 = Calendar.getInstance(); 
    	cDay1.setTime(sDate1); 
    	cDay1.set(Calendar.HOUR_OF_DAY,23);
    	cDay1.set(Calendar.MINUTE, 59);
    	cDay1.set(Calendar.SECOND, 59);
    	return cDay1.getTime();
    } 
    
    

/**

  * @author jerry.chen

  * @param brithday

  * @return

  * @throws ParseException

  *             根据生日获取年龄;

  */

 public static String getCurrentAgeByBirthdate(Timestamp brithday) {
  try {
   Calendar cal = Calendar.getInstance();
   
   if (cal.before(brithday)) {
	   return 0+"";
	}
   int yearNow = cal.get(Calendar.YEAR);
	int monthNow = cal.get(Calendar.MONTH) + 1;
	int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

	cal.setTime(brithday);
	int yearBirth = cal.get(Calendar.YEAR);
	int monthBirth = cal.get(Calendar.MONTH) + 1;
	int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

	int age = yearNow - yearBirth;

	if (monthNow <= monthBirth) {
		if (monthNow == monthBirth) {
			// monthNow==monthBirth 
			if (dayOfMonthNow < dayOfMonthBirth) {
				age--;
			}
		} else {
			// monthNow>monthBirth 
			age--;
		}
	}
	return age+"";

  } catch (Exception e) {

   return 0+"";

  }

 }
 

/**

  * @param brithday

  * @return

  * @throws ParseException

  *             指定日期计算生日获取年龄;
	学生安装
	学年是指教育年度，即从每年的九月一日到第二年的八月三十一日。
  */

 public static int getStuentAgeByBirthdate(Timestamp brithday) {
  try {
   Calendar cal = Calendar.getInstance();
   cal.set(Calendar.MONTH, 9-1);
   cal.set(Calendar.DAY_OF_MONTH, 1);
   if (cal.before(brithday)) {
	   return 0;
	}
   int yearNow = cal.get(Calendar.YEAR);
	int monthNow = cal.get(Calendar.MONTH) + 1;
	int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

	cal.setTime(brithday);
	int yearBirth = cal.get(Calendar.YEAR);
	int monthBirth = cal.get(Calendar.MONTH) + 1;
	int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

	int age = yearNow - yearBirth;

	if (monthNow <= monthBirth) {
		if (monthNow == monthBirth) {
			// monthNow==monthBirth 
			if (dayOfMonthNow < dayOfMonthBirth) {
				age--;
			}
		} else {
			// monthNow>monthBirth 
			age--;
		}
	}
	return age;

  } catch (Exception e) {

   return 0;

  }

 }
 
 

 /**
  * 起始年月yyyy-MM与终止月yyyy-MM之间跨度的月数。
  * 
  * @param beginMonth
  *            格式为yyyy-MM
  * @param endMonth
  *            格式为yyyy-MM
  * @return 整数。
  */
 public static int getIntervalMonth(String beginMonth, String endMonth) {
	 String[] beginMonthArr=beginMonth.split("-");
	 String[] endMonthArr=endMonth.split("-");
	 
  int intBeginYear = Integer.parseInt(beginMonthArr[0]);
  int intBeginMonth = Integer.parseInt(beginMonthArr[1]);
  int intEndYear = Integer.parseInt(endMonthArr[0]);
  int intEndMonth = Integer.parseInt(endMonthArr[1]);

  return ((intEndYear - intBeginYear) * 12)+ (intEndMonth - intBeginMonth) ;
 }

}




/*
	DECOMPILATION REPORT

	Decompiled from: D:\workspace\cdrems\src\main\webapp\WEB-INF\lib\ADF-core2-2.3.5.jar
	Total time: 109 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/