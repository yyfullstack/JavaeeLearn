package com.yckjsoft.mail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @ClassName: Sendmail03
 * @Description: 发送包含附件的邮件
 */
public class Sendmail03 {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.163.com");
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
        ts.connect("smtp.163.com", "ahtl_yy", "love007");
        //4、创建邮件
        Message message = createAttachMail(session);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * @param session
     * @return
     * @throws Exception
     * @Method: createImageMail
     * @Description: 创建一封带附件的邮件
     */
    public static MimeMessage createAttachMail(Session session) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("ahtl_yy@163.com"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("980050148@qq.cn"));
        //邮件标题
        message.setSubject("带附件的邮件");

        // 准备邮件数据
        // 准备邮件正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");

        //创建邮件附件
        MimeBodyPart attach = new MimeBodyPart();
        String filepath = Sendmail03.class.getClassLoader().getResource("car.jpg").getFile();
        DataHandler dh = new DataHandler(new FileDataSource(filepath));
        attach.setDataHandler(dh);
        attach.setContentID(dh.getName());
        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(attach);
        mm.setSubType("mixed");

        message.setContent(mm);
        message.saveChanges();
        //将创建好的邮件写入到D盘以文件的形式进行保存
        message.writeTo(new FileOutputStream("D:\\attachMail.eml"));
        //返回创建好的邮件
        return message;
    }
}