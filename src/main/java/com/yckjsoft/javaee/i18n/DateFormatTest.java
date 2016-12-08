package com.yckjsoft.javaee.i18n;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yong on 2016/11/7 0007.
 * DateFormat类可以将一个日期/时间对象格式化为表示某个国家地区的日期/时间字符串
 */
public class DateFormatTest {
    public static void main(String[] args) {
        Date date = new Date();
        // 输出日期部分
        //Locale zh_CN = new Locale("zh", "CN");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
        String result = dateFormat.format(date);
        System.out.println(result);

        Locale zh_CN = new Locale("zh", "CN");
        dateFormat = DateFormat.getDateInstance(DateFormat.FULL, zh_CN);
        result = dateFormat.format(date);
        System.out.println(result);

        // 输出日期和时间
        dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG, Locale.SIMPLIFIED_CHINESE);
        result = dateFormat.format(date);
        System.out.println(result);

        // 把字符串反向解析成一个date对象
        String s = "2016年11月7日 星期一 下午04时00分55秒";
        dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG, Locale.SIMPLIFIED_CHINESE);
        Date d = null;
        try {
            d = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(d);


    }
}
