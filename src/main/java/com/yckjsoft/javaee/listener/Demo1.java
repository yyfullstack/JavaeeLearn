package com.yckjsoft.javaee.listener;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by yong on 2016/11/22 0022.
 * 监听window窗口的事件监听器
 */
public class Demo1 {
    /**
     * java的事件监听机制
     * 1、事件监听涉及到三个组件：事件源、事件对象、事件监听器
     * 2、当事件源上发生某一个动作时，它会调用事件监听器的一个方法，并在调用该方法时把事件对象传递进去，
     * 开发人员在监听器中通过事件对象，就可以拿到事件源，从而对事件源进行操作。
     */
    public static void main(String[] args) {
        Frame f = new Frame();
        f.setSize(400, 400);
        f.setVisible(true);

        //注册事件监听器
        f.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {

            }

            /**
             * 当window窗体关闭时就会WindowListener这个监听器监听到，
             * 监听器就会调用windowClosing方法处理window窗体关闭时的动作
             */
            public void windowClosing(WindowEvent e) {
                //通过事件对象e来获取事件源对象
                Frame f = (Frame) e.getSource();
                System.out.println(f + "窗体正在关闭");
                f.dispose();
            }

            public void windowClosed(WindowEvent e) {

            }

            public void windowIconified(WindowEvent e) {

            }

            public void windowDeiconified(WindowEvent e) {

            }

            public void windowActivated(WindowEvent e) {

            }

            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
