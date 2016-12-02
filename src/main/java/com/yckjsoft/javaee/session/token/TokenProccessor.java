package com.yckjsoft.javaee.session.token;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by yong on 2016/10/25 0025.
 * 生成Token的工具类TokenProccessor
 */
public class TokenProccessor {
    /**
     * 单例设计模式（保证类的对象在内存中只有一个）
     * 1. 把类的构造函数私有
     * 2. 自己创建一个类的对象
     * 3. 对外提供一个公共的方法，返回类的对象
     */
    private TokenProccessor() {

    }

    private static final TokenProccessor instance = new TokenProccessor();

    public static TokenProccessor getInstance() {
        return instance;
    }


    public String makeToken() {
        String result = "";
        //数据指纹   128位长   16个字节  md5
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(token.getBytes());
            //base64编码--任意二进制编码明文字符
            BASE64Encoder encoder = new BASE64Encoder();
            result = encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
