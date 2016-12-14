package com.yckjsoft.controller;

import com.yckjsoft.domain.User;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by yong on 2016/12/14 0014.
 */
public class Sendmail extends Thread {
    //用于给用户发送邮件的邮箱
    private String from = "ahtl_yy@163.com";
    //邮箱的用户名
    private String username = "ahtl_yy";
    //邮箱的密码
    private String password = "love007";
    //发送邮件的服务器地址
    private String host = "smtp.163.com";

    private User user;

    public Sendmail(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", host);
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            //使用JavaMail发送邮件的5个步骤
            //1、创建session
            Session session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、连上邮件服务器，需要发件人提供邮箱的用户名和密码进行验证
            ts.connect(host, username, password);
            //4、创建邮件
            Message message = createEmail(session, user);
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Message createEmail(Session session, User user) throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(from));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        //邮件的标题
        message.setSubject("用户注册邮件");
        //邮件的文本内容
        String info = "恭喜您注册成功，您的用户名：" + user.getUsername() + ",您的密码：" + user.getPassword()
                + "，请妥善保管，如有问题请联系网站客服!!";
        message.setContent(info, "text/html;charset=UTF-8");
        message.saveChanges();
        //返回创建好的邮件对象
        return message;
    }
}
