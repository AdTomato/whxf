package com.authine.cloudpivot.web.api.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author wangyong
 * @time 2020/5/11 15:19
 */
public class DateUtils {

    /**
     * 将日期装换成 year-month-date 00:00:00:00格式
     *
     * @param date 日期
     * @return year-month-date 00:00:00:00
     * @author wangyong
     */
    public static Date getYearMonthDateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 将日期转换成 year-month-1 00:00:00:00 格式
     *
     * @param date 日期
     * @return year-month-1 00:00:00:00
     * @author wangyong
     */
    public static Date getYearMonthTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 判断两个时间年、月、日是否相同
     *
     * @param d1 时间1
     * @param d2 时间2
     * @return true 相同 false 不同
     * @author wangyong
     */
    public static boolean YearMonthDateIsSame(Date d1, Date d2) {
        if ((d1 == null && d2 == null) || (d1 == null && d2 != null) || (d1 != null && d2 == null)) {
            return false;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1.get(Calendar.DATE) == c2.get(Calendar.DATE) ? true : false;
    }

}
