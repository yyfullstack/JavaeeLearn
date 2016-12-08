package com.yckjsoft.javaee.i18n;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yong on 2016/11/7 0007.
 */
public class MessageFormatTest {
    public static void main(String[] args) {
        String pattern = "On {0}, a hurricance destroyed {1} houses and caused {2} of damage.";
        MessageFormat format = new MessageFormat(pattern, Locale.SIMPLIFIED_CHINESE);

        String pattern2 = "At {0, time, short} on {0, date}, a destroyed {1} houses and caused {2, number, currency} of damage";
        MessageFormat format2 = new MessageFormat(pattern2, Locale.US);

        Object[] arr = {new Date(), 99, 100000000};
        String result = format.format(arr);
        System.out.println(result);

        String result2 = format2.format(arr);
        System.out.println(result2);
    }
}
