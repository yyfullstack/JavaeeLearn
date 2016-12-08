package com.yckjsoft.javaee.i18n;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by yong on 2016/11/7 0007.
 * NumberFormat类测试
 */
public class NumberFormatTest {
    public static void main(String[] args) {
        int price = 89;
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String result = nf.format(price);
        System.out.println(result);

        String s = "￥89.00";
        nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
        Number n = null;
        try {
            n = nf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(n.doubleValue());

        double num = 0.5;
        nf = NumberFormat.getPercentInstance(Locale.CANADA);
        result = nf.format(num);
        System.out.println(result);

    }
}
