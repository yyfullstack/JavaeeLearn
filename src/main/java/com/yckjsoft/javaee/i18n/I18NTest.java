package com.yckjsoft.javaee.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by yong on 2016/11/7 0007.
 * 根据国家地区自动获取与之对应的资源文件
 */
public class I18NTest {
    public static void main(String[] args) {

        String basename = "myproperties";
        //设置语言环境
        Locale zh_cn = new Locale("zh", "CN");
        Locale en_US = new Locale("en", "US");
        //根据基名和语言环境加载对应的语言资源文件
        //加载myproperties_zh_CN.properties
        ResourceBundle myResourcesCN = ResourceBundle.getBundle(basename, zh_cn);
        //加载myproperties_en_US.properties
        ResourceBundle myResourcesUS = ResourceBundle.getBundle(basename, en_US);
        //加载资源文件后， 程序就可以调用ResourceBundle实例对象的 getString方法获取指定的资源信息名称所对应的值。
        String usernameCN = myResourcesCN.getString("username");
        String passwordCN = myResourcesCN.getString("password");

        String usernameUS = myResourcesUS.getString("username");
        String passwordUS = myResourcesUS.getString("password");

        System.out.println(usernameCN + "--" + passwordCN);
        System.out.println(usernameUS + "--" + passwordUS);

    }
}
