package com.yckjsoft.mail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @ClassName: Sendmail04
 * @Description: 发送包含内嵌图片和附件的复杂邮件
 */
public class Sendmail04 {

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
        Message message = createMixedMail(session);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * @param session
     * @return
     * @throws Exception
     * @Method: createImageMail
     * @Description: 生成一封带附件和带图片的邮件
     */
    public static MimeMessage createMixedMail(Session session) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("ahtl_yy@163.com"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("980050148@qq.cn"));
        //邮件标题
        message.setSubject("带附件和带图片的的邮件");

        // 准备邮件数据
        // 准备邮件正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是一封邮件正文带图片<img src='cid:xxx.jpg'>的邮件", "text/html;charset=UTF-8");

        //图片
        MimeBodyPart image = new MimeBodyPart();
        String imagePath = Sendmail04.class.getClassLoader().getResource("car.jpg").getFile();
        image.setDataHandler(new DataHandler(new FileDataSource(imagePath)));
        image.setContentID("xxx.jpg");

        //创建邮件附件
        //附件1
        MimeBodyPart attachOne = new MimeBodyPart();
        String attachOnePath = Sendmail04.class.getClassLoader().getResource("01.zip").getFile();
        DataHandler dhOne = new DataHandler(new FileDataSource(attachOnePath));
        attachOne.setDataHandler(dhOne);
        attachOne.setFileName(MimeUtility.encodeText(dhOne.getName()));
        //附件2
        MimeBodyPart attachTwo = new MimeBodyPart();
        String attachTwoPath = Sendmail04.class.getClassLoader().getResource("02.zip").getFile();
        DataHandler dhTwo = new DataHandler(new FileDataSource(attachTwoPath));
        attachTwo.setDataHandler(dhTwo);
        attachTwo.setFileName(MimeUtility.encodeText(dhTwo.getName()));

        // 描述关系:正文和图片
        MimeMultipart mpOne = new MimeMultipart();
        mpOne.addBodyPart(text);
        mpOne.addBodyPart(image);
        mpOne.setSubType("related");

        MimeMultipart mpTwo = new MimeMultipart();
        mpTwo.addBodyPart(attachOne);
        mpTwo.addBodyPart(attachTwo);

        //代表正文的bodypart
        MimeBodyPart content = new MimeBodyPart();
        content.setContent(mpOne);
        mpTwo.addBodyPart(content);
        mpTwo.setSubType("mixed");
        message.setContent(mpTwo);
        message.saveChanges();

        //将创建好的邮件写入到D盘以文件的形式进行保存
        message.writeTo(new FileOutputStream("D:\\mixedMail.eml"));
        //返回创建好的邮件
        return message;
    }
}