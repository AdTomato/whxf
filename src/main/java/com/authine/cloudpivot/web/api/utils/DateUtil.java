package com.authine.cloudpivot.web.api.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weiyao
 * @date 2020-05-08
 * 日期操作类
 */
public class DateUtil {

    private static final int TWELVE = 12;
    private static final int EIGHTEEN = 18;
    private static final int TWENTY_FOUR = 24;

    /**
     * 日期格式YYYYMMDDhhmmss字符串常量
     */
    private static final String DATETIME_FORMAT_NO_SEPTUM = "YYYYMMDDhhmmss";

    /**
     * 日期格式yyyy-MM-dd HH:mm:ss字符串常量
     */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式yyyy-MM-dd HH:mm字符串常量
     */
    private static final String DATETIME_FORMAT_NO_S = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式yyyy-MM-dd HH:mm字符串常量
     */
    private static final String DATETIME_FORMAT_H_M = "HH:mm";

    /**
     * 日期格式yyyy-MM字符串常量
     */
    private static final String DATE_FORMAT_YEAR_MONTH = "yyyy-MM";

    /**
     * 日期格式yyyy-MM-dd字符串常量
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期格式yyyyMMdd字符串常量
     */
    private static final String DATE_FORMAT8 = "yyyyMMdd";
    /**
     * 日期格式HH:mm:ss字符串常量
     */
    private static final String HOUR_FORMAT = "HH:mm:ss";

    private static final String HOUR_MINITE_FORMAT = "HH:mm";

    private static final String NOW_STARTDATE_STRING = "T00:00:00.000+08:00[Asia/Shanghai]";
    private static final String NOW_ENDDATE_STRING = "T23:59:59.000+08:00[Asia/Shanghai]";
    private static final String ZONEID_SH = "Asia/Shanghai";

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static String getDateTime() {

        return new SimpleDateFormat(DATETIME_FORMAT).format(new Date());
    }

    /**
     * 根据 date 获取时间字符串
     * @param date 日期
     * @return
     */
    public static String getDateTimeStrByDate(Date date) {
        return new SimpleDateFormat(DATETIME_FORMAT).format(date);
    }

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static String getDatetimeFormatNoS() {
        return new SimpleDateFormat(DATETIME_FORMAT_NO_S).format(new Date());
    }

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static String getDatetimeFormatHourMinute() {
        return new SimpleDateFormat(DATETIME_FORMAT_H_M).format(new Date());
    }

    /**
     * 获取系统日期
     *
     * @return
     */
    public static String getDate() {
        return new SimpleDateFormat(DATE_FORMAT).format(new Date());
    }

    /**
     * 获取系统日期(12位日期：yyyyMMddHHmm)
     *
     * @return
     */
    public static String getDate12() {
        return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }

    /**
     * 获取系统日期(12位日期：yyyyMMdd)
     *
     * @return
     */
    public static String getDate8() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * 根据指定格式获取时间
     *
     * @param format
     * @return
     */
    public static String getDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getTime() {
        return new SimpleDateFormat(HOUR_FORMAT).format(new Date());
    }

    /**
     * 获取当前时间时间搓
     *
     * @return
     */
    public static String getTimestamp() {
        return System.currentTimeMillis() + "";
    }

    /**
     * 根据日期字符串获取日期格式(8位日期：yyyyMMdd)
     *
     * @param dateString
     * @return
     */
    public static Date getDateByStr(String dateString) {
        try {
            return new SimpleDateFormat(DATE_FORMAT8).parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据日期字符串(8位日期：yyyyMMdd)获取日期格式(10位日期：yyyy-MM-dd)
     *
     * @param dateString
     * @return
     */
    public static String getDateStrByStr(String dateString) {
        try {
            Date date = new SimpleDateFormat(DATE_FORMAT8).parse(dateString);
            return new SimpleDateFormat(DATE_FORMAT).format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据日期字符串获取日期格式(8位日期：yyyyMMdd)
     *
     * @param dateString
     * @return
     */
    public static String getDateByStr(Date dateString) {
        try {
            return new SimpleDateFormat(DATE_FORMAT8).format(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getHourMiniteStrByDate(Date date) {
        try {
            return new SimpleDateFormat(HOUR_MINITE_FORMAT).format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据日期字符串获取日期格式(8位日期：yyyyMMdd)
     *
     * @param date
     * @return
     */
    public static String getDateByStr(ZonedDateTime date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT8);
            return date.format(formatter);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据日期字符串获取日期格式(8位日期：yyyyMMdd)
     *
     * @param date
     * @return
     */
    public static String getDateByStr(LocalDate date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT8);
            return date.format(formatter);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getYearMonthByStr(LocalDate date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_YEAR_MONTH);
            return date.format(formatter);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDate getLocalDateByStrYearMonth(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_YEAR_MONTH);
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDate getLocalDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDate getLocalDate(String date, String dateFormat){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime getLocalDateTime(String datetime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
            return LocalDateTime.parse(datetime, formatter);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 根据传入进来的日期返回 8位日期
     *
     * @param dateString
     * @return
     */
    public static String getDateStrByDate(String dateString) {

        try {
            Date date = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
            return getDateByStr(date);
        } catch (ParseException e) {
            return null;
        }


        //return new SimpleDateFormat(DATE_FORMAT8).parse(dateString);
    }


    /**
     * 根据日期字符串获取日期格式(yyyy-MM-dd)
     *
     * @param dateString
     * @return
     */
    public static Date getDateByStr2(String dateString) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前一周的时间范围（周一 到 周日）
     *
     * @return
     */
    public static Date[] getWeek() {
        Date[] date = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        date[0] = cal.getTime();//周一时间
        cal.add(Calendar.DAY_OF_WEEK, 7);
        date[1] = cal.getTime();//周日时间
        return date;
    }

    /**
     * 获取当月的时间范围
     *
     * @return
     */
    public static Date[] getMonth() {
        Date[] date = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        date[0] = cal.getTime();
        cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        date[1] = cal.getTime();
        return date;
    }

    /**
     * 获取当月的时间范围
     *
     * @return
     */
    public static Date[] getLastMonth() {
        Date[] date = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.add(Calendar.MONTH, -1);//上个月
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        date[0] = cal.getTime();
        cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.add(Calendar.MONTH, -1);//上个月
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        date[1] = cal.getTime();
        return date;
    }


    /**
     * 根据当前日期，获取指定月数后的最后一天日期
     *
     * @return
     */
    public static LocalDate getSixMonthLastDay(Integer month) {
        LocalDate today = LocalDate.now();
        LocalDate sixLastDay = today.plusMonths(month).with(TemporalAdjusters.lastDayOfMonth());
        return sixLastDay;
    }

    public static LocalDate getSixMonthLastDay(LocalDate today, Integer month) {
        LocalDate sixLastDay = today.plusMonths(month).with(TemporalAdjusters.lastDayOfMonth());
        return sixLastDay;
    }

    /**
     * 根据当前日期，获取指定月数后的第一天日期
     *
     * @return
     */
    public static LocalDate getSixMonthFirstDay(Integer month) {
        LocalDate today = LocalDate.now();
        LocalDate sixFirstDay = today.plusMonths(month).with(TemporalAdjusters.firstDayOfMonth());
        return sixFirstDay;
    }

    /**
     * linkai 17-07-13 add
     *
     * @param info 类型  {"day","week","month","custom"};
     * @param obj  是否自定义日期范围
     * @return
     */
    public static Map<String, ZonedDateTime> getFormartZonedDateTime(String info, Object obj) {
        Map<String, ZonedDateTime> map = new HashMap<>();

        //获取时间
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime start = null;
        ZonedDateTime end = null;
        //格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //获取时间格式化时间
        String ymd = formatter.format(now);

        switch (info) {
            case "day":
                start = ZonedDateTime.parse(ymd + NOW_STARTDATE_STRING);
                map.put("start", start);
                map.put("end", now);
                break;
            case "week":
                DayOfWeek week = now.getDayOfWeek();
                int day = week.getValue() - 1;
                if (day == 1) {
                    start = ZonedDateTime.parse(ymd + NOW_STARTDATE_STRING);
                    map.put("start", start);
                    map.put("end", now);
                    break;
                } else {
                    start = ZonedDateTime.now().plusDays(-day);
                    ymd = formatter.format(start);
                    start = ZonedDateTime.parse(ymd + NOW_STARTDATE_STRING);
                    map.put("start", start);
                    map.put("end", now);
                    break;
                }
            case "month":
                Month month = now.getMonth();
                int dm = now.getDayOfMonth() - 1;
                if (dm == 0) {
                    start = ZonedDateTime.parse(ymd + NOW_STARTDATE_STRING);
                    map.put("start", start);
                    map.put("end", now);
                    break;
                } else {
                    start = ZonedDateTime.now().plusDays(-dm);
                    ymd = formatter.format(start);
                    start = ZonedDateTime.parse(ymd + NOW_STARTDATE_STRING);
                    map.put("start", start);
                    map.put("end", now);
                    break;
                }
            case "custom":
                String[] dates = obj.toString().split("/");
                if (dates.length == 1) {
                    start = ZonedDateTime.parse(dates[0] + NOW_STARTDATE_STRING);
                    map.put("start", start);
                    map.put("end", now);
                } else if (dates.length == 2) {
                    if (!"".equals(dates[0]) && !"".equals(dates[1])) {
                        start = ZonedDateTime.parse(dates[0] + NOW_STARTDATE_STRING);
                        end = ZonedDateTime.parse(dates[1] + NOW_ENDDATE_STRING);
                        map.put("start", start);
                        map.put("end", end);
                    } else if ("".equals(dates[0]) && !"".equals(dates[1])) {
                        start = ZonedDateTime.parse("1970-01-01" + NOW_STARTDATE_STRING);
                        end = ZonedDateTime.parse(dates[1] + NOW_ENDDATE_STRING);
                        map.put("start", start);
                        map.put("end", end);
                    }
                }
        }

        return map;
    }

    /**
     * 格式化时间,默认yyyy-MM-dd HH:mm:ss
     * 1.yyyyMMdd
     * 2.yyyy-MM-dd
     * 3.HH:mm:ss
     * 4.YYYYMMDDhhmmss
     * -- yyyy-MM-dd HH:mm:ss.
     *
     * @return
     */
    public static String formatDate(ZonedDateTime date, int num) {
        if (date == null || "".equals(date)) {
            date = ZonedDateTime.now();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        String appTimestr = date.format(formatter);
        switch (num) {
            case 1:
                formatter = DateTimeFormatter.ofPattern(DATE_FORMAT8);
                appTimestr = date.format(formatter);
                return appTimestr;
            case 2:
                formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
                appTimestr = date.format(formatter);
                return appTimestr;
            case 3:
                formatter = DateTimeFormatter.ofPattern(HOUR_FORMAT);
                appTimestr = date.format(formatter);
                return appTimestr;
            case 4:
                formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT_NO_SEPTUM);
                appTimestr = date.format(formatter);
                return appTimestr;
            default:
                return appTimestr;
        }
    }

    /**
     * 将localdate转换成数据库中的date
     *
     * @param localDate
     * @return
     */
    public static java.sql.Date toSQLDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        Long time = date.getTime();
        return new java.sql.Date(time);
    }

    /**
     * 将localdatetime转为数据库中的date
     * @param localDateTime
     * @return
     */
    public static java.sql.Date toSqlDate(LocalDateTime localDateTime){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return new java.sql.Date(date.getTime());
    }

    /**
     * 将字符串转为SQLDate
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static java.sql.Date toSQLDate(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(str);
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        return date1;
    }

    /**
     * string转ZonedDateTime
     *
     * @param str
     * @return
     */
    public static ZonedDateTime toZoneDateTime(String str) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT).withZone(ZoneId.systemDefault());
        if (StringUtils.isBlank(str)) {
            return null;
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(str, dateTimeFormatter);
        return zonedDateTime;
    }

    /**
     * ZonedDateTime转string
     *
     * @param zonedDateTime
     * @return
     */
    public static String ZonedDateTimeToStr(ZonedDateTime zonedDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        return dateTimeFormatter.format(zonedDateTime);
    }

    /**
     * ZonedDateTime转string
     *
     * @param date
     * @return
     */
    public static String dateTimeToStr(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT_NO_S);
        return simpleDateFormat.format(date);
    }

    /**
     * localDateTime转ZonedDateTime
     *
     * @param localDateTime
     * @return
     */
    public static ZonedDateTime toZoneDateTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return instant.atZone(zoneId);
    }

    /**
     * 根据字符串获取日期、时间，以及提前分钟
     * @param dateString 时间字符串
     * @param beforeMine 提前分钟
     * @return
     * @throws ParseException
     */
    public static Map getDateAndTimeByStr(String dateString, Integer beforeMine) throws ParseException {
        Map map = new HashMap();
        Date date = new SimpleDateFormat(DATETIME_FORMAT).parse(dateString);
        if (beforeMine != null) {
            date.setMinutes(date.getMinutes() - beforeMine);
        }
        String day = new SimpleDateFormat(DATE_FORMAT8).format(date);
        String time = new SimpleDateFormat("HHmmss").format(date);
        map.put("day", day);
        map.put("time", time);
        return map;
    }

    public static ZonedDateTime getZonedDateTime(String DateTimeStr){
        if(DateTimeStr.length() == 10){
            DateTimeStr = DateTimeStr + " 00:00:00";
        }
        DateTimeFormatter beijingFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT).withZone(ZoneId.of(ZONEID_SH));
        if(StringUtils.isBlank(DateTimeStr)){
            return null;
        }
        ZonedDateTime beijingDateTime = ZonedDateTime.parse(DateTimeStr, beijingFormatter);
        return beijingDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    }

    /**
     * 获取指定年月的第一天
     * @param year 年
     * @param month 月
     * @return
     */
    public static LocalDateTime getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        return LocalDateTime.of(year, month, firstDay, 0, 0, 0);
    }

    /**
     * 获取指定年月的最后一天
     * @param year 年
     * @param month 月
     * @return
     */
    public static LocalDateTime getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        return LocalDateTime.of(year, month, lastDay, 23, 59, 59);
    }

    /**
     *  java.time.LocalDateTime --> java.util.Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * java.time.LocalDate --> java.util.Date
     * @param localDate
     * @return
     */
    public static Date localDateToUdate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取起止日期
     * @param date 需要参照的日期
     * @param n 最近n周
     * @param option 0 开始日期；1 结束日期
     * @param k 0 包含本周 1 不包含本周
     * @return
     */
    public static Date getFromToDate(Date date, int n, int option, int k) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 0 == option ? 1 - dayOfWeek : 7 - dayOfWeek;
        int amount = 0 == option ? offset - (n - 1  + k) * 7 : offset - k * 7;
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    public static String getTimeSlot(Date date) {
        String timeSolt = "";
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String str = df.format(date);
        int a = Integer.parseInt(str);
        if (a >= 0 && a <= TWELVE) {
            timeSolt = "上午";
        }
        if (a > TWELVE && a <= EIGHTEEN) {
            timeSolt = "下午";
        }
        if (a > EIGHTEEN && a <= TWENTY_FOUR) {
            timeSolt = "晚上";
        }
        return timeSolt;
    }


    /**
     * 将毫秒值转为时分秒
     * @param time
     * @return
     */
    public static String getGapTime(long time){
        long hours = time / (1000 * 60 * 60);
        long minutes = (time-hours*(1000 * 60 * 60 ))/(1000* 60);
        String diffTime="";
        if(minutes<10){
            diffTime=hours+":0"+minutes;
        }else{
            diffTime=hours+":"+minutes;
        }
        return diffTime;
    }


    /**
     *获取三天钱的日期
     * @return
     */
    public static Date getCurrentDateTime(Date date)  {
        SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
     //   calendar.add(Calendar.DATE, -3);
        Date dateTime;
        try {
            dateTime= sj.parse(sj.format(calendar.getTime()));
            return dateTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

}
