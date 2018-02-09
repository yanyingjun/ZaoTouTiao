package com.zhishun.zaotoutiao.common.util;


import jodd.util.StringUtil;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 */
public class DateUtil {

    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认日期格式
     */
    public static final String FULL_DATE_FORMAT = "yyyyMMdd";

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 没有连接线的日期时间格式
     */
    public static final String SINGLE_DATETIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * 月份在前的日期格式
     */
    public static final String SINGLE_DATE_FORMAT = "MM-dd-yyyy";

    /**
     * 根据传入的格式将日期对象转成对应格式的日期字符串
     *
     * @param date   日期对象
     * @param format 日期格式
     * @return 转换后的日期字符串
     */
    public static String toString(Date date, String format) {

        if ((date == null) || StringUtil.isBlank(format)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 根据传入的格式将日期字符串转成日期对象
     *
     * @param dateStr 日期字符串
     * @param format  日期格式
     * @return 转换后的日期对象
     */
    public static Date toDate(String dateStr, String format) {
        if (StringUtil.isBlank(dateStr)
                || StringUtil.isBlank(format)
                ) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.setLenient(false);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(dateStr, pos);
    }

    /**
     * 添加日期
     *
     * @param srcDate 原始时间
     * @param days  需要增加的天数。
     * @return
     */
    public static Date add(Date srcDate, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(srcDate);
        c.add(Calendar.DAY_OF_MONTH, days);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 获取当前小时数
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(toString(add(new Date(), -8), SINGLE_DATE_FORMAT));
    }

    /**
     * 获取指定天数后的日期
     *
     * @param index 小于0表示几天前、等于0表示当天、大于0表示几天后
     * @return 2016-08-18
     */
    public static String getPastDateString(int index) {
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.DATE, index);
        nowDate = calendar.getTime();
        String yDate = DateUtil.toString(nowDate, "yyyy-MM-dd");
        return yDate;
    }
}
