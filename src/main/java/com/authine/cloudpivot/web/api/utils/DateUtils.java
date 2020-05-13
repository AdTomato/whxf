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

}
