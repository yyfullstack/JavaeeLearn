package com.yckjsoft.javaee.response;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * 生成随机图片用作验证码
 */
public class ResponseDemo4 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置refresh响应头，控制浏览器每隔5秒钟刷新一次
        resp.setHeader("refresh", "5");
        //验证码基础
        //1.内存中创建一张图片
        BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
        //2.得到图片
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.WHITE); //设置图片背景色
        g.fillRect(0, 0, 100, 30);//填充背景色
        //3.向图上写数据
        g.setColor(Color.BLUE); //设置图片上字体颜色
        g.setFont(new Font(null, Font.BOLD, 20));
        g.drawString(makeNum(), 0, 30);
        //4.设置响应控制浏览器以图片的方式打开
        resp.setContentType("image/jpeg");
        //5.设置响应头，浏览器不缓存图片数据
        resp.setDateHeader("expries", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        //6.将图片写给浏览器
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    /**
     * 生成随机数
     *
     * @return
     */
    private String makeNum() {
        Random random = new Random();
        String num = random.nextInt(999999999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7 - num.length(); i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
