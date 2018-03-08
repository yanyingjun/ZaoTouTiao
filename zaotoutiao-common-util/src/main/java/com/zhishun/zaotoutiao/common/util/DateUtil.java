package com.zhishun.zaotoutiao.common.util;


import org.apache.commons.lang3.StringUtils;
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
     * 斜线日期
     */
    public static final String SLASH_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    /**
     * 根据传入的格式将日期对象转成对应格式的日期字符串
     *
     * @param date   日期对象
     * @param format 日期格式
     * @return 转换后的日期字符串
     */
    public static String toString(Date date, String format) {

        if ((date == null) || StringUtils.isBlank(format)) {
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
        if (StringUtils.isBlank(dateStr)
                || StringUtils.isBlank(format)
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

    /**
     * 日期格式化
     * @return
     */
    public static String getDateFormat(String time){
        Date date1 = DateUtil.toDate(time, DateUtil.SLASH_DATE_FORMAT);
        return DateUtil.toString(date1, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param startDate
     * @param endDate
     * @return
     */
    public static int differentDaysByMillisecond(String startDate, String endDate){
        Date date1 = DateUtil.toDate(startDate, DateUtil.SLASH_DATE_FORMAT);
        Date date2 = DateUtil.toDate(endDate, DateUtil.SLASH_DATE_FORMAT);
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    /**
     * 10位秒级时间戳
     * @param date
     * @return
     */
    public static Long getSenndTimestamp(Date date){
        if(null == date){
            return 0L;
        }else{
            String timestamp = String.valueOf(date.getTime()/1000);
            return Long.valueOf(timestamp);
        }
    }
}
