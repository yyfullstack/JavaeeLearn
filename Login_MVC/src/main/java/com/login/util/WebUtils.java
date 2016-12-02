package com.login.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Created by yong on 2016/10/31 0031.
 */
public class WebUtils {
    //     泛型可以用"<T>"代表，那么前面的T就是泛型类型,后面的参数T表示的是自定义参数。
//    解释： “<T>”是泛型的默认值，可以被任意类型所代替，如：
//    List<String> T= new ArayList<String>()；这个就定义了一个String类型的”泛型“集合，那么T（前面的）的类型就是字符串。
//    List<T> T= new ArayList<T>()；
//    可以赋值给T：T.add("StringBatch");
//    可以获取到list的值：T.get(0),结果就是”StringBatch“；
//    这个时候T的类型也是String。也就是说T（前面的）是动态的，可以被任意指定类型。
    public static <T> T request2Bean(HttpServletRequest req, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            Enumeration<String> e = req.getParameterNames();
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String value = req.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String makeId() {
        return UUID.randomUUID().toString();
    }

    /**
     * md5加密方法
     *
     * @param password
     * @return
     */
    public static String md5(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把没一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}
