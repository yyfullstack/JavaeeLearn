package com.login.dao.impl;


import com.login.dao.IUserDao;
import com.login.domain.User;
import com.login.util.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by yong on 2016/10/31 0031.
 */
public class UserDaoImpl implements IUserDao {
    public User find(String userName, String userPwd) {
        try {
            Document document = XmlUtils.getDocument();
            //使用XPath表达式来操作XML节点
            Element e = (Element) document.selectSingleNode("//user[@userName='" + userName + "' and @userPwd='" + userPwd + "']");

            if (e == null) {
                return null;
            }

            User user = new User();
            user.setId(e.attributeValue("id"));
            user.setEmail(e.attributeValue("email"));
            user.setUserPwd(e.attributeValue("userPwd"));
            user.setUserName(e.attributeValue("userName"));
            String birth = e.attributeValue("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(sdf.parse(birth));
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void add(User user) {
        try {
            Document document = XmlUtils.getDocument();
            Element root = document.getRootElement();
            //创建user节点，并挂到root
            Element user_node = root.addElement("user");
            user_node.addAttribute("id", user.getId());
            user_node.addAttribute("userName", user.getUserName());
            user_node.addAttribute("userPwd", user.getUserPwd());
            user_node.addAttribute("email", user.getEmail());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user_node.addAttribute("birthday", sdf.format(user.getBirthday()));
            XmlUtils.write2Xml(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User find(String userName) {
        try {
            Document document = XmlUtils.getDocument();
            //使用XPath表达式来操作XML节点
            Element e = (Element) document.selectSingleNode("//user[@userName='" + userName + "']");

            if (e == null) {
                return null;
            }

            User user = new User();
            user.setId(e.attributeValue("id"));
            user.setEmail(e.attributeValue("email"));
            user.setUserPwd(e.attributeValue("userPwd"));
            user.setUserName(e.attributeValue("userName"));
            String birth = e.attributeValue("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(sdf.parse(birth));
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
